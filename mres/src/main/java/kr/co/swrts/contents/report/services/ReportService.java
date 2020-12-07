package kr.co.swrts.contents.report.services;

import java.lang.reflect.Type;									//파싱된 VO타입을 담을 변수
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;										//json Array
import org.slf4j.Logger;										//Logger
import org.slf4j.LoggerFactory;									//로그팩토리
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;									//파싱하기 위한 gson
import com.google.gson.reflect.TypeToken;						//VO타입을 gson에 적용

import kr.co.swrts.contents.report.daos.ReportDao;
import kr.co.swrts.contents.report.domains.ContractMstVO;
import kr.co.swrts.contents.report.domains.DetailedWorkMstVO;
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
	 * 연간스케쥴 저장 메서드입니다. 
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
	 * 연간스케쥴 삭제 메서드입니다.
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
	 * 세무업무 실적 리스트 조회
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
	 * 세부업무 실적 저장/수정
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
	 * 세부업무 실적 삭제
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
	 * 주요현황 리스트 조회
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
	 * 주요계약현황 저장/수정
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
	 * 주요계약현황 삭제
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
	 * 세무업무 실적 리스트 조회
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
		
	}
	
}
