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
import javax.servlet.http.HttpSession;

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
import kr.co.swrts.contents.report.domains.LiftContentMstVO;
import kr.co.swrts.contents.report.domains.LiftMstVO;
import kr.co.swrts.contents.report.domains.MeetingLogMstVO;
import kr.co.swrts.contents.report.domains.PaymentStatusMstVO;
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
	@SuppressWarnings("finally")
	public Map<String, Object> selectScheduleList(HttpServletRequest request, HttpSession session, String division, String addList, String selectCalDate){
		
		logger.info("================================ START ================================");
		
		List<ScheduleMstVO> scheduleList = new ArrayList<ScheduleMstVO>();	//연간스케쥴VO List
		Map<String, Object> resultMap = new HashMap<String, Object>();		//최종적으로 반환할 map
		
		try {
		
			String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			//사업장번호 세션도 받아야함
			
			logger.info("selectCalDate : "+selectCalDate);
			logger.info("division : "+division);
			logger.info("addList : "+addList);

			String frThisYear = selectCalDate+"0101";				//이번년도 01월 01일
			String toThisYear = selectCalDate+"1201";				//이번년도 12월01일
			
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");								//1:사용 / 0:미사용
			map.put("division", division);							//업무구분
			map.put("frThisYear", frThisYear);						//검색 시작일
			map.put("toThisYear", toThisYear);						//검색 종료일
			map.put("company_code", companyCode);					//사업장코드
			
			int selectScheduleCnt = 0;								//연간스케쥴 cnt 변수
			selectScheduleCnt = reportDao.selectScheduleCnt(map);	//연간스케쥴 cnt 조회
			logger.info("selectScheduleCnt : "+selectScheduleCnt);
			
			//----------------
			//연간스케쥴 리스트 개수 
			//----------------
			if(selectScheduleCnt > 0 ) {

				scheduleList = reportDao.selectScheduleList(map);	//연간스케쥴 리스트 조회
				
			}else { 
				
				map.remove("company_code");			//삭제 후 다시 넣기
				map.put("company_code", "0000");	//사업장코드
				scheduleList = reportDao.selectScheduleDefault(map);//기본값 반환
				
			}
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				ScheduleMstVO addScheduleVO = new ScheduleMstVO();	//연간스케쥴vo 선언
				addScheduleVO.setDivision(division);				//업무 구분 값 고정
				scheduleList.add(addScheduleVO);					//리스트에 추가

			};//if
			
			logger.info(scheduleList.toString());					//조회 결과 확인	
			logger.info("================================ E N D ================================");
			resultMap.put("selectScheduleCnt", selectScheduleCnt);	//리스트 개수
			resultMap.put("scheduleList", scheduleList);			//리스트
			resultMap.put("resultCode", "0000");	//0000:정상 | 8000:서비스로직오류 | 9000:컨트롤러오류
			
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ", "ReportService.scheduleMst()");
			logger.error(e.toString());
			resultMap.put("resultCode", "8000");	//0000:정상 | 8000:서비스로직오류 | 9000:컨트롤러오류
			
		} finally { 
			
			return resultMap;
			
		}
		
		
	};//selectScheduleList
	
	
	/**
	*연간스케쥴 저장 메서드입니다.
	*@param request
	*/
	public void insertSchedule(HttpServletRequest request, HttpSession session) {
		
		logger.info("================================ START ================================");
		
		try {
		
			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
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
				
				scheduleVO = scheduleList.get(i);		//반복문으로 객체 가져오기
				scheduleVO.setCreated_by(userId);		//생성자,수정자
				scheduleVO.setCompany_code(companyCode);//사업장아이디
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
	public void deleteSchedule(HttpServletRequest request, HttpSession session) {
		
		logger.info("================================ START ================================");
		
		try {
			
			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
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
				scheduleVO.setCreated_by(userId);		//생성자,수정자
				scheduleVO.setCompany_code(companyCode);//사업장아이디
				reportDao.deleteSchedule(scheduleVO);												//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportService.deleteSchedule() : ");
			logger.error(e.toString());
			
		}//try - catch
		
	}//deleteSchedule()
	
	
	/**
	*세무업무 실적 리스트 조회
	*@param request
	*@param workDate
	*@param addList
	*@return
	*/
	public List<DetailedWorkMstVO> selectDetailedWorkList(HttpServletRequest request, HttpSession session, String workDate, String addList) {
		
		logger.info("================================ START ================================");
		List<DetailedWorkMstVO> detailedWorkList = new ArrayList<DetailedWorkMstVO>();	//세부업무실적VO List
		
		try {
			
			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			String frThisYear = workDate+"0101";								//이번년도 01월 01일
			String toThisYear = workDate+"1201";								//이번년도 12월01일
			
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");								//1:사용 / 0:미사용
			map.put("frThisYear", frThisYear);						//검색 시작일
			map.put("toThisYear", toThisYear);						//검색 종료일
			map.put("company_code", companyCode);					//사업장코드
			
			int detailedWorkcnt = 0;								//세부업무실적 cnt 변수 선언
			detailedWorkcnt = reportDao.selectDetailedWorkCnt(map);	//세부업무실적 cnt 조회
			logger.info("cnt : "+detailedWorkcnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(detailedWorkcnt > 0 ) {
				detailedWorkList = reportDao.selectDetailedWorkList(map);//세부업무실적 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가 
			//-----------------------------
			if("add".equals(addList)) {
			
				DetailedWorkMstVO detailedMstWorkVO = new DetailedWorkMstVO();//vo 선언
				detailedMstWorkVO.setWork_date(workDate);	//업무 구분 값 고정
				detailedWorkList.add(detailedMstWorkVO);	//리스트에 추가

			};//if
			
			logger.info(detailedWorkList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return detailedWorkList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectDetailedWorkList() : ");	//세무업무 실적 리스트 메서드
			logger.error(e.toString());										//에러 내용
			return detailedWorkList;
			
		}//try
		
	}
	
	/**
	*세부업무 실적 저장/수정
	*@param request
	*@throws Exception
	*/
	public void insertDetailedWork(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");
		
		try {

			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
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
				
				detailedWorkVO = detailedWorkList.get(i);	//반복문으로 객체 가져오기
				detailedWorkVO.setCompany_code(companyCode);//사업장코드
				detailedWorkVO.setCreated_by(userId);		//생성자, 수정자
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
	public void deleteDetailedWork(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");
		
		try {

			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
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
				detailed_WorkVO.setCompany_code(companyCode);
				detailed_WorkVO.setCreated_by(userId);
				reportDao.deleteDetailedWork(detailed_WorkVO);											//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteDetailedWork() : ");
			logger.error(e.toString());
			
		}//try - catch
		
	}
	
	
	/**
	*하자보수 리스트 조회
	*@param request
	*@param workDate
	*@param addList
	*@return
	*/
	public List<RepairMstVO> selectRepairList(HttpServletRequest request, String addList, String frThisYear, String toThisYear, HttpSession session) {
		
		logger.info("================================ START ================================");
		List<RepairMstVO> selectRepairList = new ArrayList<RepairMstVO>();	//하자보수VO Listㅋ

		String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		
		try {
													
			Map<String, Object> map = new HashMap<String, Object>();	//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");				//1:사용 / 0:미사용
			map.put("frThisYear", frThisYear);		//날짜 검색 시작일
			map.put("toThisYear", toThisYear);		//날짝 검색 종료일
			map.put("created_by", userId);			//생성자
			map.put("company_code", companyCode);	//사업장아이디
			
			int selectRepairCnt = 0;							//하자보수리스트 cnt 변수 선언
			selectRepairCnt = reportDao.selectRepairCnt(map);	//하자보수리스트 cnt 조회
			logger.info("cnt : "+selectRepairCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(selectRepairCnt > 0 ) {
				selectRepairList = reportDao.selectRepairList(map);	//하자보수 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				RepairMstVO repairMstVO = new RepairMstVO();//vo 선언
				selectRepairList.add(repairMstVO);			//리스트에 추가

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
	public void insertRepair(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
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
				
				repairVO = repairList.get(i);			//하자보수현황 리스트
				repairVO.setCompany_code(companyCode);	//사업장아이디
				repairVO.setCreated_by(userId);			//생성자
				if(reportDao.updateRepair(repairVO) == 0) {reportDao.insertRepair(repairVO);};		//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertRepair() : ");
			logger.error(e.toString());
			
		}//try - catch
		
	}//insertRepair
	
	
	/**
	*하자보수현황 삭제
	*@param request
	*/
	public void deleteRepair(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		try {
			
			String str = request.getParameter("totalJson");	//매개변수 string으로 받기
			logger.info(str); 								//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);		//json배열 선언
			Gson gson = new Gson();							//gson 선언
			Type listType = new TypeToken<ArrayList<RepairMstVO>>(){}.getType();			//하자보수현황VO의 List.class 
			List<RepairMstVO> repairList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			RepairMstVO repairVO = new RepairMstVO();		//하자보수현황 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < repairList.size(); ++i) {
				
				repairVO = repairList.get(i);			//반복문으로 객체 가져오기
				repairVO.setCompany_code(companyCode);	//사업장코드
				repairVO.setCreated_by(userId);			//생성자,수정자
				reportDao.deleteRepair(repairVO);		//하자보수현황 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteDetailedWork() : ");
			logger.error(e.toString());
			
		}//try - catch
		
	}
	
	
	/**
	*주요현황 리스트 조회
	*@param request
	*@param addList
	*@return
	*/
	public List<ContractMstVO> selectContractList(HttpServletRequest request, String addList, HttpSession session) {
		
		logger.info("================================ START ================================");
		
		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		List<ContractMstVO> contractVOList = new ArrayList<ContractMstVO>();//주요계약현황VO List
		
		try {
													
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");								//1:사용 / 0:미사용
			map.put("company_code", companyCode);					//사업장코드
			
			int contractCnt = 0;							//주요계약현황 개수 변수
			contractCnt = reportDao.selectContractCnt(map);	//주요계역현황 개수 조회
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(contractCnt > 0 ) {
				contractVOList = reportDao.selectContractList(map);	//주요계약현황 리스트 조회
			};//if

 
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				ContractMstVO contractVO = new ContractMstVO();	//vo 선언
				contractVOList.add(contractVO);					//리스트에 추가

			};//if

			
			logger.info(contractVOList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return contractVOList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectContractList() : ");	//주요계약현황 리스트 메서드
			logger.error(e.toString());									//에러 내용
			return contractVOList;
			
		}//try
		
	}//contractList
	
	/**
	*주요계약현황 저장/수정
	*@param request
	*@throws Exception
	*/
	public void insertContract(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		try {
			
			String str = request.getParameter("totalJson");	//매개변수 string으로 받기
			logger.info(str); 								//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);		//json배열 선언
			Gson gson = new Gson();							//gson 선언
			Type listType = new TypeToken<ArrayList<ContractMstVO>>(){}.getType();				//주요계약현황 실적VO의 List.class 
			List<ContractMstVO> contractList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			
			//-----------------------------------
			//파싱된 VOList 출력
			//저장 및 수정
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {

				ContractMstVO contractVO = new ContractMstVO();	//주요계약현황 VO			
				contractVO = contractList.get(i);				//반복문으로 객체 가져오기		
				contractVO.setCreated_by(userId);				//생성자 박주임
				contractVO.setCompany_code(companyCode); 		//사업장코드
				
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
	public void deleteContract(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		try {
			
			String str = request.getParameter("totalJson");	//매개변수 string으로 받기
			logger.info(str); 								//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);		//json배열 선언
			Gson gson = new Gson();							//gson 선언
			Type listType = new TypeToken<ArrayList<ContractMstVO>>(){}.getType();				//주요계약현황VO의 List.class 
			List<ContractMstVO> contractList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			ContractMstVO contractVO = new ContractMstVO();	//주요계약현황 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {
				
				contractVO = contractList.get(i);			//반복문으로 객체 가져오기
				contractVO.setCompany_code(companyCode);	//사업장코드
				contractVO.setCreated_by(userId);			//생성자, 수정자
				reportDao.deleteContract(contractVO);		//주요계약현황 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteContract() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	
	/**
	*설비 및 수불 현황 리스트 조회
	*@param request
	*@param addList
	*@return
	*/
	public List<PaymentStatusMstVO> selectPaymentStatusList(HttpServletRequest request, HttpSession session, String addList, String selectDate) {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		List<PaymentStatusMstVO> paymentStatusVOList = new ArrayList<PaymentStatusMstVO>();	//설비 및 수불 현황VO List
		
		try {
			
			String frThisYear = selectDate+"0101";								//이번년도 01월 01일
			String toThisYear = selectDate+"1201";								//이번년도 12월01일
			String frLastYear = (Integer.parseInt(selectDate)-1)+"0101";		//전년도 01월 01일
			String toLastYear = (Integer.parseInt(selectDate)-1)+"1201";		//전년도 12월01일
			
			Map<String, Object> map = new HashMap<String, Object>();			//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");											//1:사용 / 0:미사용
			map.put("created_by", userId);			//생성자, 수정자
			map.put("company_code", companyCode);	//사업장코드
			map.put("frThisYear", frThisYear);		//금년 검색시작일
			map.put("toThisYear", toThisYear);		//금년 검색종료일
			map.put("frLastYear", frLastYear);		//작년 검색시작일
			map.put("toLastYear", toLastYear);		//작년 검색종료일
			
			int paymentStatusCnt = 0;											//설비 및 수불 현황 개수 변수
			paymentStatusCnt = reportDao.selectPaymentStatusCnt(map);			//설비 및 수불 현황 개수 조회
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(paymentStatusCnt > 0 ) {
				paymentStatusVOList = reportDao.selectPaymentStatusList(map);	//설비 및 수불 현황 리스트 조회
			};//if

			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				PaymentStatusMstVO paymentStatusVO = new PaymentStatusMstVO();	//vo 선언
				paymentStatusVO.setFrThisYear(frThisYear);						//이번년도 01월 01일 
				paymentStatusVO.setToThisYear(toThisYear);						//이번년도 12월01일
				paymentStatusVO.setFrLastYear(frLastYear);						//전년도 01월 01일 
				paymentStatusVO.setToLastYear(toLastYear);						//전년도 12월01일
				paymentStatusVOList.add(paymentStatusVO);						//리스트에 추가

			};//if

			logger.info(paymentStatusVOList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return paymentStatusVOList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectPaymentStatusList() : ");							//설비 및 수불 현황 리스트 메서드
			logger.error(e.toString());																//에러 내용
			return paymentStatusVOList;
			
		}//try
		
	}//contractList
	
	
	/**
	*설비 및 수불 현황 저장/수정
	*@param request
	*@throws Exception
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> insertPaymentStatus(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();//최종적으로 반환할 map
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<PaymentStatusMstVO>>(){}.getType();						//설비 및 수불 현황 실적VO의 List.class 
			List<PaymentStatusMstVO> paymentStatusList = gson.fromJson(jsonArray.toString(), listType);			//jsonArray -> VO로 파싱
			
			//-----------------------------------
			//파싱된 VOList 출력
			//저장 및 수정
			//-----------------------------------
			for(int i = 0; i < paymentStatusList.size(); ++i) {

				PaymentStatusMstVO paymentStatusMstVO = new PaymentStatusMstVO();//설비 및 수불현황 VO			
				paymentStatusMstVO = paymentStatusList.get(i);	//반복문으로 객체 가져오기		
				paymentStatusMstVO.setCreated_by(userId);		//생성자 박주임
				paymentStatusMstVO.setCompany_code(companyCode);//사업장코드
				
				logger.info("######"+paymentStatusMstVO.toString());
				
				if(reportDao.updatePaymentStatus(paymentStatusMstVO) == 0) {reportDao.insertPaymentStatus(paymentStatusMstVO); };	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			resultMap.put("resultCode", "0000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertPaymentStatus() : ");
			logger.error(e.toString());
			resultMap.put("resultCode", "8000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}
	
	/**
	*설비 및 수불 현황 삭제
	*@param request
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> deletePaymentStatus(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();				//최종적으로 반환할 map
		
		try {
			
			String str = request.getParameter("totalJson");							//매개변수 string으로 받기
			logger.info(str); 														//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);								//json배열 선언
			Gson gson = new Gson();													//gson 선언
			Type listType = new TypeToken<ArrayList<PaymentStatusMstVO>>(){}.getType();	//설비 및 수불 현황VO의 List.class 
			List<PaymentStatusMstVO> PaymentStatusMstList = gson.fromJson(jsonArray.toString(), listType);//jsonArray -> VO로 파싱
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < PaymentStatusMstList.size(); ++i) {

				PaymentStatusMstVO paymentStatusMstVO = new PaymentStatusMstVO();//설비 및 수불 현황 VO
				paymentStatusMstVO = PaymentStatusMstList.get(i);	//반복문으로 객체 가져오기
				paymentStatusMstVO.setCompany_code(companyCode);	//사업장코드
				paymentStatusMstVO.setCreated_by(userId);			//생성자, 수정자
				reportDao.deletePaymentStatus(paymentStatusMstVO);	//설비 및 수불 현황 DAO
				
			};//for
		
			resultMap.put("resultCode", "0000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "8000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error("ReportServiceImpl.deletePaymentStatus() : ");
			logger.error(e.toString());
			
		}finally {
			
			return resultMap;
			
		}
		
	}//deletePaymentStatus()
	
	
	/**
	*승강기 목록 조회
	*@param request
	*@param addList
	*@return
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> selectLiftList(HttpServletRequest request, HttpSession session,  String inspection_division, String selectDate) {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();	//최종적으로 반환할 map
		List<LiftMstVO> selectLiftVOList = new ArrayList<LiftMstVO>();	//승강기VO List
		int liftCnt = 0;												//승강기 개수 변수
		
		try {

			String frThisYear = selectDate+"0101";						//이번년도 01월 01일
			String toThisYear = selectDate+"1201";						//이번년도 12월01일
			
			Map<String, Object> map = new HashMap<String, Object>();	//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");									//1:사용 / 0:미사용
			map.put("inspection_division", inspection_division);		//1:승강기 2:화재예방
			map.put("company_code", companyCode);						//사업장코드
			map.put("created_by", userId);								//생성자, 수정자
			map.put("frThisYear", frThisYear);							//검색 시작일
			map.put("toThisYear", toThisYear);							//검색 종료일
			
			liftCnt = reportDao.selectLiftCnt(map);						//승강기 개수 조회
			
			//------------------
			//승강기 리스트 개수 
			//------------------
			if(liftCnt > 0 ) {
				selectLiftVOList = reportDao.selectLiftList(map);		//승강기 리스트 조회
			};//if


			resultMap.put("liftCnt", liftCnt);							//승강기 목록 수
			resultMap.put("selectLiftVOList", selectLiftVOList);		//승강기 목록
			resultMap.put("inspection_division", inspection_division);	//1:승강기 2:화재예방
			logger.info(selectLiftVOList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectLiftVOList() : ");	//승강기 리스트 메서드
			logger.error(e.toString());									//에러 내용
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}//selectLiftVOList

	
	/**
	*승강기 상세 조회
	*@param request
	*@param addList
	*@return
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> selectLiftContentList(HttpServletRequest request, HttpSession session, String addList, int lift_seq) {
		
		logger.info("================================ START ================================");

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();	//최종적으로 반환할 map
		LiftMstVO selectLiftVO = new LiftMstVO();	//승강기VO List
		List<LiftContentMstVO> selectLiftContentList = new ArrayList<LiftContentMstVO>();	//승강기VO List
		int liftContentCnt = 0;																//승강기 개수 변수
		String inspection_division = request.getParameter("inspection_division");			//1:승강기 2:화재예방
//		map.put("company_code","");	//@@@@@@@사업장 아이디 또는 사업장 코드
		System.out.println("##############################inspection_division : "+inspection_division);
		
		try {
										
			Map<String, Object> map = new HashMap<String, Object>();	//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");									//사용구분
			map.put("created_by", userId);								//생성자, 수정자
			map.put("company_code", companyCode);						//사업장코드
			
			if(0 != lift_seq) {
				System.out.println("########################## seq : "+lift_seq);
				map.put("lift_seq", lift_seq);							//승강기 목록 번호 - lift_mst
				selectLiftVO = reportDao.selectLift(map);				//승강기 목록 조회
				liftContentCnt = reportDao.selectLiftContentCnt(map);	//승강기 상세내용 개수 조회
				
				//------------------
				//승강기 리스트 개수 
				//------------------
				if(liftContentCnt > 0 ) {
					
					System.out.println("######################### 여기 진입");
					selectLiftContentList = reportDao.selectLiftContentList(map);	//승강기 상세내용 리스트 조회
				};//if
				
			}else {
				
				map.remove("company_code");//키 제거 -> 하단 로직에서 다시 넣어줌
				
				if("1".equals(inspection_division)){
				
					System.out.println("####################division :"+inspection_division);
					map.put("company_code","0000");										//0000:기본 설정 값
					selectLiftContentList = reportDao.selectLiftDefaultList(map);		//default 리스트 호출
					
				}else if("2".equals(inspection_division)) {

					System.out.println("####################division :"+inspection_division);
					map.put("company_code","00000");									//00000:기본 설정 값
					selectLiftContentList = reportDao.selectLiftDefaultList(map);		//default 화재예방 호출
					
				}//if - else if
				
			}//if - else
			
			System.out.println("############################ 중간 지점");
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				LiftContentMstVO liftContentMstVO = new LiftContentMstVO();		//vo 선언
				selectLiftContentList.add(liftContentMstVO);					//리스트에 추가

			};//if
			
			resultMap.put("selectLiftVO", selectLiftVO);						//승강기 목록
			resultMap.put("liftContentCnt", liftContentCnt);					//승강기 상세내용 개수
			resultMap.put("selectLiftContentList", selectLiftContentList);		//승강기 상세내용 리스트 
			logger.info(selectLiftVO.toString());
			logger.info(selectLiftContentList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectLiftVOList() : ");	//승강기 리스트 메서드
			logger.error(e.toString());									//에러 내용
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}//selectLiftContentList

	
	/**
	*승강기 저장/수정
	*@param request
	*@throws Exception
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> insertLift(HttpServletRequest request, HttpSession session, int lift_seq, String sub_manager) throws Exception {
		
		logger.info("================================ START ================================");
		Map<String, Object> resultMap = new HashMap<String, Object>();//최종적으로 반환할 map
		
		//-----------------------------------------
		//1. lift_mst 저장/수정 (승강기 목록)
		//2. lift_content_mst 저장/수정 (승강기 상세내용)	
		//-----------------------------------------
		try {

			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
			System.out.println("#####################"+request.getParameter("lift_seq"));
			System.out.println("#####################"+request.getParameter("inspection_field"));
			System.out.println("#####################"+request.getParameter("inspection_company"));
			System.out.println("#####################"+request.getParameter("inspection_date"));
			System.out.println("#####################"+request.getParameter("building_name"));
			System.out.println("#####################"+request.getParameter("main_manager"));
			System.out.println("#####################"+request.getParameter("inspection_division"));
			
			LiftMstVO liftMstVO = new LiftMstVO();											//시설물관리VO
			liftMstVO.setInspection_field(request.getParameter("inspection_field"));		//점검분약
			liftMstVO.setInspection_company(request.getParameter("inspection_company"));	//점검업체
			liftMstVO.setInspection_date(request.getParameter("inspection_date"));			//점검일자
			liftMstVO.setBuilding_name(request.getParameter("building_name"));				//건물명
			liftMstVO.setMain_manager(request.getParameter("main_manager"));				//메인 확인자
			liftMstVO.setSub_manager(sub_manager);											//서브 확인자
			liftMstVO.setLift_seq(lift_seq);												//시퀀스
			liftMstVO.setInspection_division(request.getParameter("inspection_division"));	//1:승강기 2:화재예방
			liftMstVO.setCompany_code(companyCode);											//사업장코드
			liftMstVO.setCreated_by(userId);												//생성자, 수정자
			if(reportDao.updateLift(liftMstVO) == 0) {reportDao.insertLift(liftMstVO);}		//수정/저장
			lift_seq = reportDao.selectLiftSeq(liftMstVO);									//시퀀스 조회
			
			System.out.println("##################### selectLiftSeq : "+lift_seq);
			//승강기 시퀀스 및 정보
			//저장/수정 
			//실패 시 리턴
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<LiftContentMstVO>>(){}.getType();				//설비 및 수불 현황 실적VO의 List.class 
			List<LiftContentMstVO> liftContentList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			System.out.println("################여기 나옴?");
			//----------------------------------- 
			//파싱된 VOList 출력
			//저장 및 수정
			//-----------------------------------
			if(lift_seq == 0) {
				for(int i = 0; i < liftContentList.size(); ++i) {

					LiftContentMstVO liftContentMstVO = new LiftContentMstVO();		//승강기 VO			
					liftContentMstVO = liftContentList.get(i);						//반복문으로 객체 가져오기		
					liftContentMstVO.setCreated_by(userId);							//@@@@세션에 있는 유저 아이디로 바꿔야함
					liftContentMstVO.setCompany_code(companyCode);					//@@@@사업장 식별 값이 들어가야 함
					
					logger.info("######"+liftContentMstVO.toString());
					
					reportDao.insertLiftContent(liftContentMstVO);	//업데이트 할 내용이 없으면 저장
					
				};//for
			}else {
				for(int i = 0; i < liftContentList.size(); ++i) {

					LiftContentMstVO liftContentMstVO = new LiftContentMstVO();		//승강기 화재예방 VO			
					liftContentMstVO = liftContentList.get(i);						//반복문으로 객체 가져오기	
					liftContentMstVO.setLift_seq(lift_seq); 						//lift_seq 셋팅
					liftContentMstVO.setCreated_by(userId);							//생성자 박주임
					liftContentMstVO.setCompany_code(companyCode);					//사업장코드
					
					logger.info("######"+liftContentMstVO.toString());
					
					if(reportDao.updateLiftContent(liftContentMstVO) == 0) {reportDao.insertLiftContent(liftContentMstVO); };	//업데이트 할 내용이 없으면 저장
					
				};//for
			}

			System.out.println("##################### put 전 lift_seq : "+lift_seq);
			resultMap.put("lift_seq", lift_seq); //승강기 목록 시퀀스
			resultMap.put("resultCode", "0000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertLiftContent() : "); 
			logger.error(e.toString());
			resultMap.put("resultCode", "8000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}//insertLift()
	
	
	/**
	*교육현황 리스트 조회
	*@param request
	*@param trainingDate
	*@param addList
	*@return
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> selectTrainingList(HttpServletRequest request, HttpSession session, String trainingDate, String addList) {
		
		logger.info("================================ START ================================");
		List<TrainingMstVO> trainingList = new ArrayList<TrainingMstVO>();	//교육내용VO List
		Map<String, Object> resultMap = new HashMap<String, Object>();		//최종적으로 반환할 map
		
		try {

			String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			//사업장번호 세션도 받아야함
			
			//---------------------------------------
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(trainingDate)) {
				
				LocalDate now = LocalDate.now();										//현재 날짜
				trainingDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));	//년월일 파싱 2020.10.10 
				trainingDate = trainingDate.substring(0,4);								//년도 파싱 2020
				
			};//if
			
			logger.info("trainingDate : "+trainingDate);			//기준년도 로그
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");								//1:사용 / 0:미사용
			map.put("fr_training_date", trainingDate+"0101");		//기준년도 시작일
			map.put("to_training_date", trainingDate+"1231");		//기준년도 종료일
			map.put("company_code", companyCode);					//사업장번호
			
			int trainingCnt = 0;									//교육내용 cnt 변수 선언
			trainingCnt = reportDao.selectTrainingCnt(map);			//교육내용 cnt 조회
			logger.info("cnt : "+trainingCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(trainingCnt > 0 ) {
				trainingList = reportDao.selectTrainingList(map);	//교육내용 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {

				System.out.println("################################################## addList");
				TrainingMstVO trainingMstVO = new TrainingMstVO();	//vo 선언
				trainingMstVO.setTraining_date(trainingDate);		//업무 구분 값 고정
				trainingList.add(trainingMstVO);					//리스트에 추가

			};//if
			
			System.out.println("################################################## map put");
			resultMap.put("trainingDate", trainingDate);
			resultMap.put("trainingCnt", trainingCnt);				//교육현황 리스트 개수
			resultMap.put("trainingList", trainingList);			//교육현황 리스트
//			resultMap.put("resultCode", "0000");					//0000:정상 9000:비정상
			logger.info(trainingList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectTrainingList() : ");	//교육내용 실적 리스트 메서드
			logger.error(e.toString());									//에러 내용
//			resultMap.put("resultCode", "8000");						//0000:정상 8000:서비스에러 9000:비정상
			
		}finally {
			
			return resultMap;
			
		}
		
	}//selectTrainingList

	
	/**
	*교육현황 저장/수정
	*@param request
	*@throws Exception
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> insertTraining(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");
		Map<String, Object> resultMap = new HashMap<String, Object>();//최종적으로 반환할 map
		
		try {
			
			String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
			
			String str = request.getParameter("totalJson");		//매개변수 string으로 받기
			logger.info(str); 									//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);			//json배열 선언
			Gson gson = new Gson();								//gson 선언
			Type listType = new TypeToken<ArrayList<TrainingMstVO>>(){}.getType();				//교육현황 VO의 List.class 
			List<TrainingMstVO> trainingList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			
			//-----------------
			//파싱된 VOList 출력
			//저장 및 수정
			//-----------------
			for(int i = 0; i < trainingList.size(); ++i) {

				TrainingMstVO trainingMstVO = new TrainingMstVO();	//교육현황 VO			
				trainingMstVO = trainingList.get(i);				//반복문으로 객체 가져오기		
				trainingMstVO.setCreated_by(userId);				//생성자 
				trainingMstVO.setLast_update_by(userId);			//최종수정자
				trainingMstVO.setCompany_code(companyCode);
				
				logger.info("######"+trainingMstVO.toString());
				
				if(reportDao.updateTraining(trainingMstVO) == 0) {reportDao.insertTraining(trainingMstVO); };	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			resultMap.put("resultCode", "0000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertTraining() : ");
			logger.error(e.toString());
			resultMap.put("resultCode", "8000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}
	
	
	/**
	*교육현황 삭제
	*@param request
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> deleteTraining(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");
		Map<String, Object> resultMap = new HashMap<String, Object>();				//최종적으로 반환할 map
		
		try {

			String userId = (String) session.getAttribute("userId");	//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
			String str = request.getParameter("totalJson");							//매개변수 string으로 받기
			logger.info(str); 														//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);								//json배열 선언
			Gson gson = new Gson();													//gson 선언
			Type listType = new TypeToken<ArrayList<TrainingMstVO>>(){}.getType();						//교육현황VO의 List.class 
			List<TrainingMstVO> TrainingList = gson.fromJson(jsonArray.toString(), listType);//jsonArray -> VO로 파싱
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < TrainingList.size(); ++i) {

				TrainingMstVO trainingMstVO = new TrainingMstVO();	//교육현황 VO
				trainingMstVO = TrainingList.get(i);				//반복문으로 객체 가져오기
				trainingMstVO.setLast_update_by(userId);			//최종수정자
				reportDao.deleteTraining(trainingMstVO);			//교육현황 DAO
				
			};//for
		
			resultMap.put("resultCode", "0000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "8000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error("ReportServiceImpl.deleteTraining() : ");
			logger.error(e.toString());
			
		}finally {
			
			return resultMap;
			
		}
		
	}//deleteTraining()
	
	
	/**
	*관리단회의록 조회
	*@param request
	*@param session
	*@param selectDate
	*@param addList
	*@return
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> selectMeetingLog(HttpServletRequest request, HttpSession session, String selectDate, String addList) {
		
		logger.info("================================ START ================================");
		List<MeetingLogMstVO> meetingLogList = new ArrayList<MeetingLogMstVO>();		//관리단회의록VO List
		Map<String, Object> resultMap = new HashMap<String, Object>();			//최종적으로 반환할 map
		
		try {

			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);//사업장번호 세션도 받아야함
			
			
			//---------------------------------------
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(selectDate)) {
				
				LocalDate now = LocalDate.now();									//현재 날짜
				selectDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));	//년월일 파싱 2020.10.10 
				selectDate = selectDate.substring(0,4);								//년도 파싱 2020
				
			};//if
			
			logger.info("selectDate : "+selectDate);			//기준년도 로그
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");							//1:사용 / 0:미사용
			map.put("fr_training_date", selectDate+"0101");		//기준년도 시작일
			map.put("to_training_date", selectDate+"1231");		//기준년도 종료일
			map.put("company_code", companyCode);					//사업장번호
			
			int meetingLogCnt = 0;									//관리단회의록 cnt 변수 선언
			meetingLogCnt = reportDao.selectMeetingLogCnt(map);		//관리단회의록 cnt 조회
			logger.info("cnt : "+meetingLogCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(meetingLogCnt > 0 ) {
				meetingLogList = reportDao.selectMeetingLogList(map);	//관리단회의록 리스트 조회
			};//if
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {

				System.out.println("################################################## addList");
				MeetingLogMstVO meetingLogMstVO = new MeetingLogMstVO();	//vo 선언
				meetingLogList.add(meetingLogMstVO);						//리스트에 추가

			};//if
			
			System.out.println("################################################## map put");
			resultMap.put("selectDate", selectDate);
			resultMap.put("meetingLogCnt", meetingLogCnt);				//관리단회의록 리스트 개수
			resultMap.put("meetingLogList", meetingLogList);			//관리단회의록 리스트
//			resultMap.put("resultCode", "0000");						//0000:정상 9000:비정상
			logger.info(meetingLogList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectMeetingLog() : ");	//관리단회의록 실적 리스트 메서드
			logger.error(e.toString());									//에러 내용
//			resultMap.put("resultCode", "8000");						//0000:정상 8000:서비스에러 9000:비정상
			
		}finally {
			
			return resultMap;
			
		}
		
	}//selectTrainingList
	
	
	/**
	*관리단회의록 저장/수정
	*@param request
	*@throws Exception
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> insertMeetingLog(HttpServletRequest request, HttpSession session) throws Exception {
		
		logger.info("================================ START ================================");
		Map<String, Object> resultMap = new HashMap<String, Object>();//최종적으로 반환할 map
		
		try {
			
			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
			
			String str = request.getParameter("totalJson");		//매개변수 string으로 받기
			logger.info(str); 									//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);			//json배열 선언
			Gson gson = new Gson();								//gson 선언
			Type listType = new TypeToken<ArrayList<MeetingLogMstVO>>(){}.getType();				//관리단회의록 VO의 List.class 
			List<MeetingLogMstVO> meetingLogList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			
			//-----------------
			//파싱된 VOList 출력
			//저장 및 수정
			//-----------------
			for(int i = 0; i < meetingLogList.size(); ++i) {

				MeetingLogMstVO meetingLogMstVO = new MeetingLogMstVO();//관리단회의록 VO			
				meetingLogMstVO = meetingLogList.get(i);				//반복문으로 객체 가져오기		
				meetingLogMstVO.setCreated_by(userId);					//생성자 
				meetingLogMstVO.setLast_update_by(userId);				//최종수정자
				meetingLogMstVO.setCompany_code(companyCode);			//사업장아이디
				
				logger.info("######"+meetingLogMstVO.toString());
				
				if(reportDao.updateMeetingLog(meetingLogMstVO) == 0) {reportDao.insertMeetingLog(meetingLogMstVO); };	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			resultMap.put("resultCode", "0000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertMeetingLog() : ");
			logger.error(e.toString());
			resultMap.put("resultCode", "8000"); //0000:정상 | 8000:디비오류 | 9000:비정상
			
		} finally {
		
			return resultMap;
			
		}//try - catch - finally
		
	}//insertMeetingLog()
	
	
	/**
	*관리단회의록 삭제
	*@param request
	*/
	@SuppressWarnings("finally")
	public Map<String, Object> deleteMeetingLog(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");
		Map<String, Object> resultMap = new HashMap<String, Object>();			//최종적으로 반환할 map
		
		try {

			String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
			String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
			System.out.println("########################## userId : "+userId);
			System.out.println("########################## companyCode : "+companyCode);
			
			String str = request.getParameter("totalJson");							//매개변수 string으로 받기
			logger.info(str); 														//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);								//json배열 선언
			Gson gson = new Gson();													//gson 선언
			Type listType = new TypeToken<ArrayList<MeetingLogMstVO>>(){}.getType();			//관리단회의록VO의 List.class 
			List<MeetingLogMstVO> meetingLogList = gson.fromJson(jsonArray.toString(), listType);//jsonArray -> VO로 파싱
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < meetingLogList.size(); ++i) {

				MeetingLogMstVO meetingLogMstVO = new MeetingLogMstVO();//관리단회의록 VO
				meetingLogMstVO = meetingLogList.get(i);				//반복문으로 객체 가져오기
				meetingLogMstVO.setLast_update_by(userId);				//최종수정자
				reportDao.deleteMeetingLog(meetingLogMstVO);			//관리단회의록 DAO
				
			};//for
		
			resultMap.put("resultCode", "0000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "8000");//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error("ReportServiceImpl.deleteTraining() : ");
			logger.error(e.toString());
			
		}finally {
			
			return resultMap;
			
		}
		
	}//deleteMeetingLog()
	
	
	/**
	*파일 업로드
	*@param request
	*@param fileMstVO
	*@param file
	*@param table_seq
	*/
	public Map<String, Object> fileInsert(HttpServletRequest request, HttpSession session, FileMstVO fileMstVO, MultipartFile file, int table_seq) {

		logger.info("================================ START ================================");	
		Map<String, Object> resultMap = new HashMap<String, Object>();			//최종적으로 반환할 map
		List<FileMstVO> fileList = new ArrayList<FileMstVO>();					//최종적으로 반환할 파일VO List
		
		//-------------------------
		//파일 내용이 null이 아닐 경우 저장
		//-------------------------
		if (file != null) {
			
			String fileName = file.getOriginalFilename();	//업로드 파일 이름
			fileMstVO.setFile_name(fileName);				//파일VO에 저장
			
			try {

				String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
				String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
				System.out.println("########################## userId : "+userId);
				System.out.println("########################## companyCode : "+companyCode);
				System.out.println("########################## fileMstVO.getTable_name() : "+fileMstVO.getTable_name());
				System.out.println("########################## table_seq : "+table_seq);
				System.out.println("########################## fileMstVO.getFile_name() : "+fileMstVO.getFile_name());

				String file_name = fileMstVO.getFile_name();
				
				//관리단 회의록 파일 업로드는 PDF 파일만 저장 가능
				//PDF파일이 아니면 리턴
				if("meetingLogMst".equals(fileMstVO.getTable_name()) && !"".equals(file_name)){
					if(!"PDF".equals(file_name.substring(file_name.lastIndexOf(".")+1)) && !"pdf".equals(file_name.substring(file_name.lastIndexOf(".")+1))) {
						resultMap.put("resultCode", "2100");// | 2100:pdf파일이 아님
						return resultMap; 
					}
				}else if("toRepairMst".equals(fileMstVO.getTable_name()) || "frRepairMst".equals(fileMstVO.getTable_name()) && !"".equals(file_name)){
						if(!"PNG".equals(file_name.substring(file_name.lastIndexOf(".")+1)) && !"png".equals(file_name.substring(file_name.lastIndexOf(".")+1))
						&& !"JPG".equals(file_name.substring(file_name.lastIndexOf(".")+1)) && !"jpg".equals(file_name.substring(file_name.lastIndexOf(".")+1))
						&& !"JPEG".equals(file_name.substring(file_name.lastIndexOf(".")+1)) && !"jpeg".equals(file_name.substring(file_name.lastIndexOf(".")+1))) {
							resultMap.put("resultCode", "2200");// | 2200:png파일이 아님
							return resultMap; 
						}//if
						
					}//if
				
				String mainPath = "";	//초기 경로 ex - C:/
				String subPath = "";//상세 경로
				mainPath =request.getSession().getServletContext().getRealPath("/");//초기 경로 ex - C:/
				subPath = "resources"+File.separator+"daonn"+File.separator+fileMstVO.getTable_name()+File.separator+companyCode+File.separator+table_seq+File.separator+fileMstVO.getFile_name();//상세 경로
				
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
						int file_seq = reportDao.selectFileSeq(fileMstVO);		//파일 시퀀스
						
						logger.info("###################### table_seq :"+table_seq);
						logger.info("###################### file_seq : "+file_seq);
						logger.info("table_name : "+fileMstVO.getTable_name());
						//----------------------
						//스케쥴 데이터에 파일 시퀀스 저장
						//----------------------
						if("scheduleMst".equals(fileMstVO.getTable_name())){

							ScheduleMstVO scheduleMstVO = new ScheduleMstVO();	//연간데이터VO
							scheduleMstVO.setSchedule_seq(table_seq);			//연간데이터seq
							scheduleMstVO.setFile_seq(file_seq);				//연간데이터 파일번호
							reportDao.updateSchedule(scheduleMstVO);			//연간데이터 업데이트
							
						}else if("meetingLogMst".equals(fileMstVO.getTable_name())) {
							
							MeetingLogMstVO meetingLogMstVO = new MeetingLogMstVO();//관리단회의록 VO		
							meetingLogMstVO.setMeeting_log_seq(table_seq);		//관리단회의록 시퀀스
							meetingLogMstVO.setFile_Seq(file_seq); 				//파일시퀀스
							meetingLogMstVO.setCreated_by(userId);				//생성자 
							meetingLogMstVO.setLast_update_by(userId);			//최종수정자
							meetingLogMstVO.setCompany_code(companyCode);		//사업장아이디
							reportDao.updateMeetingLog(meetingLogMstVO);		//업데이트 할 내용이 없으면 저장
							
						}else if("frRepairMst".equals(fileMstVO.getTable_name())) {
							RepairMstVO repairMstVO = new RepairMstVO();	//하자보수현황VO
							repairMstVO.setRepair_seq(table_seq);			//하자보수시퀀스
							repairMstVO.setFr_img_id(file_seq);				//예정업무 이미지 아이디
							repairMstVO.setLast_update_by(userId);			//최종수정자
							repairMstVO.setCompany_code(companyCode);		//사업장번호
							reportDao.updateRepair(repairMstVO);			//업데이트
							
						}else if("toRepairMst".equals(fileMstVO.getTable_name())) {
							RepairMstVO repairMstVO = new RepairMstVO();	//하자보수현황VO
							repairMstVO.setRepair_seq(table_seq);			//하자보수시퀀스
							repairMstVO.setTo_img_id(file_seq);				//실시업무 이미지 아이디
							repairMstVO.setLast_update_by(userId);			//최종수정자
							repairMstVO.setCompany_code(companyCode);		//사업장번호
							reportDao.updateRepair(repairMstVO);			//업데이트
							
						};
						
						System.out.println("######################## 파일저장 테이블 : "+fileMstVO.getTable_name());
						
						resultMap.put("resultCode", "0000");//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
						
					}catch(Exception e){

						logger.error("!fileSave.exists() : "+e.toString());
						resultMap.put("resultCode", "2000");//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
				    }//try - catch        
			         
				}//if
				
			} catch (Exception e) {//입출력 예외처리
				
				logger.error("file != null : "+e.toString());
				resultMap.put("resultCode", "3000");//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
			}// try - catch
		
		}else {
			resultMap.put("resultCode", "4000");//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
		}
		
		fileList = selectFileList(fileMstVO.getTable_name(), table_seq, session);
		resultMap.put("fileList", fileList);
		resultMap.put("fileCnt", fileList.size());
		
		return resultMap;
				
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
	public List<FileMstVO> selectFileList(String table_name, int table_seq, HttpSession session) {
		
		logger.info("================================ START ================================");
		
		List<FileMstVO> fileList = new ArrayList<FileMstVO>();				//파일VO List

		String userId = (String) session.getAttribute("userId");			//null처리 추가해야함
		String companyCode = (String) session.getAttribute("companyCode");	//임시 사업장 코드
		System.out.println("########################## userId : "+userId);
		System.out.println("########################## companyCode : "+companyCode);
		  
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//쿼리에 보낼 매개변수 map
			if("none".equals(table_name)) {
				table_name = "";
			}
			map.put("table_name", table_name);		//테이블 이름 ex. schedule_mst
			map.put("table_seq", table_seq);		//테이블 시퀀스 ex. schdeule_seq
			map.put("useflag", 1);					//사용구분
			map.put("created_by", userId);			//유저아이디
			map.put("last_update_by", userId);		//유저아이디
			
			int fileCnt = 0;						//테이블 cnt 변수 선언
			fileCnt = reportDao.selectFileCnt(map);	//테이블 cnt 조회
			logger.info("cnt : "+fileCnt);
			
			//------------------
			//세무업무 실적 리스트 개수 
			//------------------
			if(fileCnt > 0 ) {
				fileList = reportDao.selectFileList(map);//파일 리스트 조회
			};//if
			
			
			logger.info(fileList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return fileList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectFileList() : ");	//파일 실적 리스트 메서드
			logger.error(e.toString());								//에러 내용
			return fileList;
			
		}//try
		
	}//selectFileList
	
	
}
