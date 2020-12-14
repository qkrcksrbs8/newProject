package kr.co.swrts.contents.report.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;									//파싱된 VO타입을 담을 변수
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;										//json Array
import org.slf4j.Logger;										//Logger
import org.slf4j.LoggerFactory;									//로그팩토리
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;									//파싱하기 위한 gson
import com.google.gson.reflect.TypeToken;						//VO타입을 gson에 적용

import kr.co.swrts.contents.report.daos.ReportDao;
import kr.co.swrts.contents.report.domains.ContractMstVO;
import kr.co.swrts.contents.report.domains.DetailedWorkMstVO;
import kr.co.swrts.contents.report.domains.FileMstVO;
import kr.co.swrts.contents.report.domains.RepairMstVO;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.domains.TrainingMstVO;


/**
 *<pre>
 *연간데이터 보고서 서비스로직
 *</pre>
 *
 *@ClassName : ReportService.java 
 *@Description : 연간데이터 보고서 상세 로직입니다.
 *@author 박찬균 주임연구원
 *@since 2020. 12. 4
 *@version 1.0
 *@see
 *@Modification Information
 */
@Service
public class ReportService {
	
	/**
	 * 로그
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 연간데이터 보고서 DAO
	 */
	@Autowired
	private ReportDao reportDao;

	/**
	*연간데이터 보고서 리스트 조회
	*@param request
	*@param division
	*@param addList
	*@return
	*/
	public List<ScheduleMstVO> selectScheduleList(HttpServletRequest request, String division, String addList){
		
		logger.info("================================ START ================================");
		
		List<ScheduleMstVO> scheduleList = new ArrayList<ScheduleMstVO>();				//연간스케쥴VO List
		
		try {
		
			logger.info("division : "+division);
			logger.info("addList : "+addList);
			
			Map<String, Object> map = new HashMap<String, Object>();					//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");													//1:사용 / 0:미사용
			map.put("division", division);												//업무구분
			
			int selectScheduleCnt = 0;													//연간스케쥴 cnt 변수
			selectScheduleCnt = reportDao.selectScheduleCnt(map);						//연간스케쥴 cnt 조회
			logger.info("selectScheduleCnt : "+selectScheduleCnt);
			
			//----------------
			//연간스케쥴 리스트 개수 
			//----------------
			if(selectScheduleCnt > 0 ) {

				scheduleList = reportDao.selectScheduleList(map);						//연간스케쥴 리스트 조회
				
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				ScheduleMstVO addScheduleVO = new ScheduleMstVO();						//연간스케쥴vo 선언
				addScheduleVO.setDivision(division);									//업무 구분 값 고정
				scheduleList.add(addScheduleVO);										//리스트에 추가

			};//if
			
			logger.info(scheduleList.toString());										//조회 결과 확인	
			logger.info("================================ E N D ================================");
			return scheduleList;
		
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ", "ReportService.scheduleMst()");
			logger.error(e.toString());
			return scheduleList;
			
		}//try
		
		
	};//selectScheduleList
	
	
	/**
	*연간스케쥴 저장 메서드입니다.
	*@param request
	*/
	public void insertSchedule(HttpServletRequest request) {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<ScheduleMstVO>>(){}.getType();					//연간스케쥴VO의 List.class 
			List<ScheduleMstVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);		//jsonArray -> VO로 파싱
			ScheduleMstVO scheduleVO = new ScheduleMstVO();											//연간스케쥴 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//반복문으로 객체 가져오기
				if(reportDao.updateSchedule(scheduleVO) == 0) {reportDao.insertSchedule(scheduleVO);};//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportService.insertSchedule() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	
	/**
	*연간스케쥴 삭제 메서드입니다.
	*@param request
	*/
	public void deleteSchedule(HttpServletRequest request) {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<ScheduleMstVO>>(){}.getType();					//연간스케쥴VO의 List.class 
			List<ScheduleMstVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);		//jsonArray -> VO로 파싱
			ScheduleMstVO scheduleVO = new ScheduleMstVO();											//연간스케쥴 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//반복문으로 객체 가져오기
				reportDao.deleteSchedule(scheduleVO);												//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportService.deleteSchedule() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	/**
	*세무업무 실적 리스트 조회
	*@param request
	*@param workDate
	*@param addList
	*@return
	*/
	public List<DetailedWorkMstVO> selectDetailedWorkList(HttpServletRequest request, String workDate, String addList) {
		
		logger.info("================================ START ================================");
		List<DetailedWorkMstVO> detailedWorkList = new ArrayList<DetailedWorkMstVO>();				//세부업무실적VO List
		
		try {
			
			//---------------------------------------
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(workDate)) {
				
				LocalDate now = LocalDate.now();													//현재 날짜
				workDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));					//년월일 파싱 2020.10.10 
				workDate = workDate.substring(0,4);													//년도 파싱 2020
				
			};//if
			
			logger.info("work_date : "+workDate);													//기준년도 로그
			Map<String, Object> map = new HashMap<String, Object>();								//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");																//1:사용 / 0:미사용
			map.put("work_date", workDate);															//기준년도
			
			int detailedWorkcnt = 0;																//세부업무실적 cnt 변수 선언
			detailedWorkcnt = reportDao.selectDetailedWorkCnt(map);									//세부업무실적 cnt 조회
			logger.info("cnt : "+detailedWorkcnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(detailedWorkcnt > 0 ) {
				detailedWorkList = reportDao.selectDetailedWorkList(map);							//세부업무실적 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				DetailedWorkMstVO detailedMstWorkVO = new DetailedWorkMstVO();						//vo 선언
				detailedMstWorkVO.setWork_date(workDate);											//업무 구분 값 고정
				detailedWorkList.add(detailedMstWorkVO);											//리스트에 추가

			};//if
			
			logger.info(detailedWorkList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return detailedWorkList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectDetailedWorkList() : ");							//세무업무 실적 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return detailedWorkList;
			
		}//try
		
	}
	
	/**
	*세부업무 실적 저장/수정
	*@param request
	*@throws Exception
	*/
	public void insertDetailedWork(HttpServletRequest request) throws Exception {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<DetailedWorkMstVO>>(){}.getType();				//세부업무 실적VO의 List.class 
			List<DetailedWorkMstVO> detailedWorkList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			DetailedWorkMstVO detailedWorkVO = new DetailedWorkMstVO();								//세부업무 실적 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < detailedWorkList.size(); ++i) {
				
				detailedWorkVO = detailedWorkList.get(i);																//반복문으로 객체 가져오기
				if(reportDao.updateDetailedWork(detailedWorkVO) == 0) {reportDao.insertDetailedWork(detailedWorkVO);};	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertDetailedWork() : ");
			logger.error(e.toString());
		}
		
	}
	
	/**
	*세부업무 실적 삭제
	*@param request
	*/
	public void deleteDetailedWork(HttpServletRequest request) {

		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");												//매개변수 string으로 받기
			logger.info(str); 																			//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);													//json배열 선언
			Gson gson = new Gson();																		//gson 선언
			Type listType = new TypeToken<ArrayList<DetailedWorkMstVO>>(){}.getType();					//세부업무 실적VO의 List.class 
			List<DetailedWorkMstVO> detailedWorkList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			DetailedWorkMstVO detailed_WorkVO = new DetailedWorkMstVO();								//세부업무 실적 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < detailedWorkList.size(); ++i) {
				
				detailed_WorkVO = detailedWorkList.get(i);												//반복문으로 객체 가져오기
				reportDao.deleteDetailedWork(detailed_WorkVO);											//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteDetailedWork() : ");
			logger.error(e.toString());
			
		}//try
		
	}
	
	
	/**
	*하자보수 리스트 조회
	*@param request
	*@param workDate
	*@param addList
	*@return
	*/
	public List<RepairMstVO> selectRepairList(HttpServletRequest request, String addList, String fr_cal, String to_cal) {
		
		logger.info("================================ START ================================");
		List<RepairMstVO> selectRepairList = new ArrayList<RepairMstVO>();	//하자보수VO Listㅋ
		
		try {
													
			Map<String, Object> map = new HashMap<String, Object>();	//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");									//1:사용 / 0:미사용
			map.put("fr_cal", fr_cal);									//날짜 검색 시작일
			map.put("to_cal", to_cal);									//날짝 검색 종료일
			
			int selectRepairCnt = 0;									//하자보수리스트 cnt 변수 선언
			selectRepairCnt = reportDao.selectRepairCnt(map);			//하자보수리스트 cnt 조회
			logger.info("cnt : "+selectRepairCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(selectRepairCnt > 0 ) {
				selectRepairList = reportDao.selectRepairList(map);		//하자보수 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				RepairMstVO repairMstVO = new RepairMstVO();			//vo 선언
				selectRepairList.add(repairMstVO);						//리스트에 추가

			};//if
			
			logger.info(selectRepairList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return selectRepairList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectRepairList() : ");								//세무업무 실적 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return selectRepairList;
			
		}//try
		
	}//selectRepairList
	
	
	/**
	*하자보수 저장/수정
	*@param request
	*@throws Exception
	*/
	public void insertRepair(HttpServletRequest request) throws Exception {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<RepairMstVO>>(){}.getType();					//하자보수VO의 List.class 
			List<RepairMstVO> repairList = gson.fromJson(jsonArray.toString(), listType);			//jsonArray -> VO로 파싱
			RepairMstVO repairVO = new RepairMstVO();												//하자보수 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < repairList.size(); ++i) {
				
				repairVO = repairList.get(i);														//반복문으로 객체 가져오기
				if(reportDao.updateRepair(repairVO) == 0) {reportDao.insertRepair(repairVO);};		//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertRepair() : ");
			logger.error(e.toString());
			
		}//try - catch
		
	}//insertRepair
	
	/**
	*주요현황 리스트 조회
	*@param request
	*@param addList
	*@return
	*/
	public List<ContractMstVO> selectContractList(HttpServletRequest request, String addList) {
		
		logger.info("================================ START ================================");
		List<ContractMstVO> contractVOList = new ArrayList<ContractMstVO>();						//주요계약현황VO List
		
		try {
													
			Map<String, Object> map = new HashMap<String, Object>();								//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");																//1:사용 / 0:미사용
			
			int contractCnt = 0;																	//주요계약현황 개수 변수
			contractCnt = reportDao.selectContractCnt(map);											//주요계역현황 개수 조회
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(contractCnt > 0 ) {
				contractVOList = reportDao.selectContractList(map);									//주요계약현황 리스트 조회
			};//if

 
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				ContractMstVO contractVO = new ContractMstVO();										//vo 선언
				contractVOList.add(contractVO);														//리스트에 추가

			};//if

			
			logger.info(contractVOList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return contractVOList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectContractList() : ");								//주요계약현황 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return contractVOList;
			
		}//try
		
	}//contractList
	
	/**
	*주요계약현황 저장/수정
	*@param request
	*@throws Exception
	*/
	public void insertContract(HttpServletRequest request) throws Exception {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<ContractMstVO>>(){}.getType();						//주요계약현황 실적VO의 List.class 
			List<ContractMstVO> contractList = gson.fromJson(jsonArray.toString(), listType);			//jsonArray -> VO로 파싱
			
			String total_date 	= "";		//전체 계약기간 변수
			String fr_day		= "";		//전체 계약기간에서 시작일을 담을 변수
			String to_day 		= "";		//전체 계약기간에서 종요일을 담을 변수
			String total_years 	= "";		//계약연수+계약구분
			String contract_years = "";		//계약연수를 담을 변수
			String contract_division = "";	//계약구분을 담을 변수
			String removeChar = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";						//특수문자 제거 
			String justNum = "[^0-9]";													//숫자 이외 제거
			
			//-----------------------------------
			//파싱된 VOList 출력
			//1. 계약기간 일자를 파싱하여 시작일, 종료일을 분리합니다.
			//2. 계약연수와 계약구분을 분리합니다. 
			//3. 저장 및 수정
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {

				ContractMstVO contractVO = new ContractMstVO();										//주요계약현황 VO			
				contractVO = contractList.get(i);													//반복문으로 객체 가져오기
				
				total_date = contractVO.getTotal_date().replaceAll(justNum, "");					//숫자 외 문자 제거
				logger.info("######total_date : "+total_date);
				
				//-----------------------------
				//total_date 길이가 16이 아니면 리턴
				//-----------------------------
				if(16 != total_date.length()) {               
					return;
				};//if
				

				total_years = contractVO.getTotal_years();									//계약년수 ex. 1년(자동연장)		
				total_years = total_years.replaceAll("년", "").replaceAll(removeChar, "");	//년, 특수문자 제거
				
				fr_day = total_date.substring(0,8);											//시작일 ex. 1993/09/02
				to_day = total_date.substring(8);											//종료일 ex. 2993/09/02
				contract_years =  total_years.substring(0,1);								//첫 자리만 년수 인식. 2자리 수의 계약 기간은 없음
				contract_division = total_years.substring(1);								//첫 자리를 제외한 나머지
				
				contractVO.setFr_day(fr_day);												//파싱된 시작일 ex. 19930902
				contractVO.setTo_day(to_day);												//파싱된 종료일 ex. 29930902
				contractVO.setContract_years(contract_years);								//파싱된 년수 ex. 1
				contractVO.setContract_division(contract_division);							//파싱된 구분 ex. 자동연장
				contractVO.setCreated_by("박주임");											//생성자 박주임
				
				logger.info("######"+contractVO.toString());
				
				if(reportDao.updateContract(contractVO) == 0) {reportDao.insertContract(contractVO);};	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertContract() : ");
			logger.error(e.toString());
			
		};//try
		
	}
	
	/**
	*주요계약현황 삭제
	*@param request
	*/
	public void deleteContract(HttpServletRequest request) {

		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<ContractMstVO>>(){}.getType();					//주요계약현황VO의 List.class 
			List<ContractMstVO> contractList = gson.fromJson(jsonArray.toString(), listType);		//jsonArray -> VO로 파싱
			ContractMstVO contractVO = new ContractMstVO();											//주요계약현황 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {
				
				contractVO = contractList.get(i);													//반복문으로 객체 가져오기
				reportDao.deleteContract(contractVO);												//주요계약현황 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteContract() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	/**
	*세무업무 실적 리스트 조회
	*@param request
	*@param trainingDate
	*@param addList
	*@return
	*/
	public List<TrainingMstVO> selectTrainingList(HttpServletRequest request, String trainingDate, String addList) {
		
		logger.info("================================ START ================================");
		List<TrainingMstVO> trainingList = new ArrayList<TrainingMstVO>();							//교육내용VO List
		
		try {
			
			//---------------------------------------
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(trainingDate)) {
				
				LocalDate now = LocalDate.now();													//현재 날짜
				trainingDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));				//년월일 파싱 2020.10.10 
				trainingDate = trainingDate.substring(0,4);											//년도 파싱 2020
				
			};//if
			
			logger.info("work_date : "+trainingDate);												//기준년도 로그
			Map<String, Object> map = new HashMap<String, Object>();								//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");																//1:사용 / 0:미사용
			map.put("work_date", trainingDate);														//기준년도
			
			int trainingCnt = 0;																	//교육내용 cnt 변수 선언
			trainingCnt = reportDao.selectDetailedWorkCnt(map);										//교육내용 cnt 조회
			logger.info("cnt : "+trainingCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(trainingCnt > 0 ) {
				trainingList = reportDao.selectTrainingList(map);									//교육내용 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				TrainingMstVO trainingMstVO = new TrainingMstVO();									//vo 선언
				trainingMstVO.setTraining_date(trainingDate);										//업무 구분 값 고정
				trainingList.add(trainingMstVO);													//리스트에 추가

			};//if
			
			logger.info(trainingList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return trainingList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectTrainingList() : ");								//교육내용 실적 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return trainingList;
			
		}//try
		
	}//selectTrainingList

	
	/**
	*파일 업로드
	*@param request
	*@param fileMstVO
	*@param file
	*@param table_seq
	*/
	public List<FileMstVO> fileInsert(HttpServletRequest request, FileMstVO fileMstVO, MultipartFile file, int table_seq) {

		logger.info("================================ START ================================");	
		
		//-------------------------
		//파일 내용이 null이 아닐 경우 저장
		//-------------------------
		if (file != null) {
			
			String fileName = file.getOriginalFilename();	//업로드 파일 이름
			fileMstVO.setFile_name(fileName);				//파일VO에 저장
			
			try {
				
				String mainPath = "";	//초기 경로 ex - C:/
				String subPath = "";//상세 경로
				mainPath =request.getSession().getServletContext().getRealPath("/");//초기 경로 ex - C:/
				subPath = "images"+File.separator+fileMstVO.getTable_name()+File.separator+table_seq+File.separator+fileMstVO.getFile_name();//상세 경로
				
				String file_path = mainPath+subPath;	//파일경로
				File fileSave = new File(file_path);	//파일경로 지정
				Long file_size = fileSave.length();		//파일 사이즈 구하기부터
				fileMstVO.setFile_path(subPath);		//파일 경로
				fileMstVO.setFile_size(file_size);		//파일 용량
				fileMstVO.setTable_seq(table_seq);		//각 테이블의 시퀀스 번호 ex. schedule_seq 정보 등...
				logger.info(" path : "+mainPath);
				logger.info("file_path : "+subPath);
				logger.info("file_size : "+file_size);
				logger.info(fileMstVO.toString());
				
				//----------------------------------
				//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
				//생성 되었을 경우 파일도 생성합니다.
				//----------------------------------
				if (!fileSave.exists()) {
					
					logger.info("파일 있음");
					
					try{//해당 경로에 파일이 없을 경우
						
						fileSave.mkdirs();				 						//폴더 생성합니다.
						file.transferTo(fileSave);								//파일 업로드.
						reportDao.insertFile(fileMstVO);						//파일 정보 저장  
						int file_Seq = reportDao.selectFileSeq(fileMstVO);		//파일 시퀀스
						

						logger.info("table_name : "+fileMstVO.getTable_name());
						//----------------------
						//스케쥴 데이터에 파일 시퀀스 저장
						//----------------------
						if("schedule_mst".equals(fileMstVO.getTable_name())){

							ScheduleMstVO scheduleMstVO = new ScheduleMstVO();	//연간데이터VO
							scheduleMstVO.setSchedule_seq(table_seq);			//연간데이터seq
							scheduleMstVO.setFile_seq(file_Seq);				//연간데이터 파일번호
							reportDao.updateSchedule(scheduleMstVO);			//연간데이터 업데이트
							
						};//if
						
						
					}catch(Exception e){

						logger.error("!fileSave.exists() : "+e.toString());
					
				    }//try - catch        
			         
				}//if
				
			} catch (Exception e) {//입출력 예외처리
				
				logger.error("file != null : "+e.toString());
			
			}// try - catch
		
		}// if
		
		return selectFileList(fileMstVO.getTable_name(), table_seq);
				
	}//fileInsert()
	
	/**
	*파일 다운로드
	*@param request
	*@param fileMstVO
	*@param file
	*@param table_seq
	*/
	public void fileDownload(HttpServletResponse response, HttpServletRequest request, int file_seq) {
		
		logger.info("================================ START ================================");	
		
	    FileInputStream fileInputStream = null;
	    ServletOutputStream servletOutputStream = null;
	 
	    try{
	    	
	    	FileMstVO fileMstVO = new FileMstVO();		//파일 VO	
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			
			logger.info("file_seq : "+file_seq);
			
			map.put("file_seq", file_seq);				//파일 시퀀스
			fileMstVO = reportDao.selectFile(map);		//파일 단건 조회
			
	    	String mainPath = "";						//초기 경로 ex - C:/
			String subPath = "";						//상세 경로
			mainPath =request.getSession().getServletContext().getRealPath("/");//초기 경로 ex - C:/
			subPath = fileMstVO.getFile_path();			//상세 경로
	    	 
			logger.info("mainPath : "+mainPath);	
			logger.info("subPath : "+subPath);	
			
			String path = mainPath+subPath; 			//파일 전체 경로 
		    String fileName = fileMstVO.getFile_name();	//파일명

			logger.info("path : "+path);	
			logger.info("fileName : "+fileName);	
			
		    File file = new File(path);					//파일 생성
		    
	    	
	        String downName = null;								//다운로드 파일 명
	        String browser = request.getHeader("User-Agent");	//사용자 에이전트
	        //파일 인코딩
	        if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){//브라우저 확인 파일명 encode  

				logger.info("브라우저 명 확인 ");	
	            downName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");	//다운로드 파일 명
	            
	        }else{

	        	logger.info("브라우저 명 확인안됨");
	            downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");			//다운로드 파일 명
	            
	        }//if - else
	        
	        response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");  //헤더           
	        response.setContentType("application/octer-stream");								//type
	        response.setHeader("Content-Transfer-Encoding", "binary;");							//헤더	
	 
	        fileInputStream = new FileInputStream(file);		//파일 읽어오기
	        servletOutputStream = response.getOutputStream();	//파일 출력 데이터
	 
	        byte b [] = new byte[1024];
	        int data = 0;
	 
	        //읽어온 파일을 출력객체에 담기
	        while((data=(fileInputStream.read(b, 0, b.length))) != -1){
	            
	            servletOutputStream.write(b, 0, data);
	            
	        }//while
	 
	        servletOutputStream.flush();		//출력
	        
	    }catch (Exception e) {
	    	
	        e.printStackTrace();				//오류로그
	        
	    }finally{
	    	
	        if(servletOutputStream!=null){		//출력할 데이터가 없을 경우
	        	
	            try{
	            	
	                servletOutputStream.close();//출력기능 종료
	                
	            }catch (IOException e){
	            	
	                e.printStackTrace();		//오류 로그
	                
	            }//try - catch
	            
	        }//if
	        
	        if(fileInputStream!=null){			//읽어올 파일이 없는경우
	        	
	            try{
	            	
	                fileInputStream.close();	//파일 읽어오는 기능 닫기
	                
	            }catch (IOException e){
	            	
	                e.printStackTrace();		//오류 로그
	                
	            }//try - catch
	            
	        }//if
	        
	    }//try - catch - finaily
	    
	}//fileDownLoad()
	
	
	
	/**
	*파일 리스트 조회
	*@param table_name
	*@param table_seq
	*@return
	*/
	public List<FileMstVO> selectFileList(String table_name, int table_seq) {
		
		logger.info("================================ START ================================");
		List<FileMstVO> fileList = new ArrayList<FileMstVO>();							//파일VO List
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();								//쿼리에 보낼 매개변수 map
			map.put("table_name", table_name);														//테이블 이름 ex. schedule_mst
			map.put("table_seq", table_seq);														//테이블 시퀀스 ex. schdeule_seq
			map.put("useflag", 1);																	//사용구분
			
			int fileCnt = 0;																		//테이블 cnt 변수 선언
			fileCnt = reportDao.selectFileCnt(map);													//테이블 cnt 조회
			logger.info("cnt : "+fileCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(fileCnt > 0 ) {
				fileList = reportDao.selectFileList(map);											//파일 리스트 조회
			};//if
			
			
			logger.info(fileList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return fileList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectFileList() : ");								//파일 실적 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return fileList;
			
		}//try
		
	}//selectFileList
	
	
}
