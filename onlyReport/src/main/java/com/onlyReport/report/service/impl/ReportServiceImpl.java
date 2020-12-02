package com.onlyReport.report.service.impl;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onlyReport.report.dao.ReportDAO;
import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ContractVO;
import com.onlyReport.report.model.Detailed_WorkVO;
import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;

/**
 * 게시판 ServiceImpl 정의
 *
 */
@Repository
public class ReportServiceImpl implements ReportService {

	Logger logger = Logger.getLogger(this.getClass());	//로그
	
	@Autowired
	private ReportDAO reportDAO;						//게시판 DAO
	
	/* 
	 * 게시글 리스트 수 조회	
	 */
	public int selectReportCnt(Map<String, Object> map) {
		
		int ReportCnt = 0;								//게시판 리스트 수
		
		try {
			
			ReportCnt = reportDAO.selectReportCnt(map);	//게시판 리스트 수 조회
		
		}catch(Exception e) {
			
			logger.error("selectReportCnt()");			//게시판 리스트 수
			logger.error(e.toString());					//에러 내용
			
		}//try
		
		return ReportCnt;
	}
	
	/**
	 * 게시글 리스트
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map) {	
		
		List<ReportVO> ReportList = new ArrayList<ReportVO>();	//게시판VO List
		
		try {
			
			ReportList = reportDAO.selectReportList(map);		//게시판 리스트 조회
			
		}catch(Exception e) {
			
			logger.error("selectReportList()");					//게시판 리스트 메서드
			logger.error(e.toString());							//에러 내용
			
		}//try
		
		return ReportList;
		
	}
	
	/**
	 * 게시글 상세
	 */
	public ReportVO selectReport(int Report_seq) {
		
		ReportVO ReportVO = new ReportVO();					//게시판VO
		
		try {
			
			ReportVO = reportDAO.selectReport(Report_seq);	//게시판 상세조회
			
		}catch(Exception e) {
			
			logger.error("selectReport()");					
			logger.error(e.toString());						//에러 내용
			
		}//try
		
		return ReportVO;
	
	}
	
	/**
	 * 게시글 업데이트
	 */
	public String updateReport(Map<String, Object> map) {

		String resultCode = "0000";				// 0000:정상 / 9000:에러
		
		try {

			reportDAO.updateReport(map);		//게시글 업데이트
			resultCode = "0000";				// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());			//에러 내용
			resultCode = "9000";				// 0000:정상 / 9000:에러
			
		}
		
		return resultCode;
	}
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public String deleteReport(Map<String, Object> map) {
		
		String resultCode = "0000";		// 0000:정상 / 9000:에러
		
		try {

			reportDAO.deleteReport(map);//게시글 업데이트
			resultCode = "0000";		// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());	// 에러 내용
			resultCode = "9000";		// 0000:정상 / 9000:에러
			
		}//try
		
		return resultCode;
	}
	
	/**
	 * 게시글 생성
	 */
	public String insertReport(Map<String, Object> map) {
		
		String resultCode = "0000";			// 0000:정상 / 9000:에러
		
		try {
			
			reportDAO.insertReport(map);	//게시글 생성
			resultCode = "0000";			// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());		//에러 내용
			resultCode = "9000";			// 0000:정상 / 9000:에러
			
		}//try
		
		return resultCode;
	}
	
	
	
	/**
	 *	연간스케쥴 조회 메서드입니다.
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(HttpServletRequest request, String division, String addList) {
		
		logger.info("================================ START ================================");
		List<Annuail_ScheduleVO> scheduleList = new ArrayList<Annuail_ScheduleVO>();	//연간스케쥴VO List
		
		try {
			logger.info("division : "+division);
			Map<String, Object> map = new HashMap<String, Object>();					//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");													//1:사용 / 0:미사용
			map.put("division", division);												//업무구분
			
			//---------------
			//연간스케쥴 리스트 수
			//---------------
			if(reportDAO.selectScheduleCnt(map) < 1 ) {
				return scheduleList;													// 리스트가 한 개도 없으면 바로 리턴
			};//if
			
			scheduleList = reportDAO.selectScheduleList(map);							//연간스케쥴 리스트 조회
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				Annuail_ScheduleVO addScheduleVO = new Annuail_ScheduleVO();			//연간스케쥴vo 선언
				addScheduleVO.setDivision(division);									//업무 구분 값 고정
				scheduleList.add(addScheduleVO);										//리스트에 추가

			};//if
			
			logger.info(scheduleList.toString());
			logger.info("================================ E N D ================================");	//리스트 로그 
			return scheduleList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectReportList() : ");					//게시판 리스트 메서드
			logger.error(e.toString());													//에러 내용
			return scheduleList;
			
		}//try
		
	}
	
	
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
			Type listType = new TypeToken<ArrayList<Annuail_ScheduleVO>>(){}.getType();				//연간스케쥴VO의 List.class 
			List<Annuail_ScheduleVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			Annuail_ScheduleVO scheduleVO = new Annuail_ScheduleVO();								//연간스케쥴 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//반복문으로 객체 가져오기
				if(reportDAO.updateSchedule(scheduleVO) == 0) {reportDAO.insertSchedule(scheduleVO);};//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertSchedule() : ");
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
			Type listType = new TypeToken<ArrayList<Annuail_ScheduleVO>>(){}.getType();				//연간스케쥴VO의 List.class 
			List<Annuail_ScheduleVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			Annuail_ScheduleVO scheduleVO = new Annuail_ScheduleVO();								//연간스케쥴 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//반복문으로 객체 가져오기
				reportDAO.deleteSchedule(scheduleVO);												//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteSchedule() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	/**
	 * 세무업무 실적 리스트 조회
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(HttpServletRequest request, String workDate, String addList) {
		
		logger.info("================================ START ================================");
		List<Detailed_WorkVO> detailedWorkList = new ArrayList<Detailed_WorkVO>();					//연간스케쥴VO List
		
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
			
			//-----------------
			//세무업무 실적 리스트 수 
			//-----------------
			if(reportDAO.selectDetailedWorkCnt(map) < 1 ) {
				return detailedWorkList;															// 리스트가 한 개도 없으면 바로 리턴
			};//if
			
			detailedWorkList = reportDAO.selectDetailedWorkList(map);								//연간스케쥴 리스트 조회
			
			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				Detailed_WorkVO detailed_WorkVO = new Detailed_WorkVO();							//vo 선언
				detailed_WorkVO.setWork_date(workDate);												//업무 구분 값 고정
				detailedWorkList.add(detailed_WorkVO);												//리스트에 추가

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
			Type listType = new TypeToken<ArrayList<Detailed_WorkVO>>(){}.getType();				//세부업무 실적VO의 List.class 
			List<Detailed_WorkVO> detailedWorkList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			Detailed_WorkVO detailed_WorkVO = new Detailed_WorkVO();								//세부업무 실적 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < detailedWorkList.size(); ++i) {
				
				detailed_WorkVO = detailedWorkList.get(i);																	//반복문으로 객체 가져오기
				if(reportDAO.updateDetailedWork(detailed_WorkVO) == 0) {reportDAO.insertDetailedWork(detailed_WorkVO);};	//업데이트 할 내용이 없으면 저장
				
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
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<Detailed_WorkVO>>(){}.getType();				//세부업무 실적VO의 List.class 
			List<Detailed_WorkVO> detailedWorkList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO로 파싱
			Detailed_WorkVO detailed_WorkVO = new Detailed_WorkVO();								//세부업무 실적 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < detailedWorkList.size(); ++i) {
				
				detailed_WorkVO = detailedWorkList.get(i);											//반복문으로 객체 가져오기
				reportDAO.deleteDetailedWork(detailed_WorkVO);										//연간스케쥴 삭제 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteDetailedWork() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	
	
	/**
	 * 세무업무 실적 리스트 조회
	 */
	public List<ContractVO> selectContractList(HttpServletRequest request, String addList) {
		
		logger.info("================================ START ================================");
		List<ContractVO> contractVOList = new ArrayList<ContractVO>();								//세무업무 실적VO List
		
		try {
													
			Map<String, Object> map = new HashMap<String, Object>();								//쿼리에 보낼 매개변수 map
			map.put("useflag", "1");																//1:사용 / 0:미사용
			
			//-----------------
			//세무업무 실적 리스트 수 
			//-----------------
			if(reportDAO.selectContractCnt(map) < 1 ) {
				logger.info("게시물 없음 : 0");
				return contractVOList;																//리스트가 한 개도 없으면 바로 리턴
			};//if

			contractVOList = reportDAO.selectContractList(map);										//주요계약현황 리스트 조회

			//-----------------------------
			//addList의 값이 add일 경우 리스트 추가
			//-----------------------------
			if("add".equals(addList)) {
			
				ContractVO contractVO = new ContractVO();											//vo 선언
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
		
	}

	
	
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
			Type listType = new TypeToken<ArrayList<ContractVO>>(){}.getType();						//주요계약현황 실적VO의 List.class 
			List<ContractVO> contractList = gson.fromJson(jsonArray.toString(), listType);			//jsonArray -> VO로 파싱
			ContractVO contractVO = new ContractVO();												//주요계약현황 VO			
			
			String total_date 	= "";		//전체 계약기간 변수
			String fr_day		= "";		//전체 계약기간에서 시작일을 담을 변수
			String to_day 		= "";		//전체 계약기간에서 종요일을 담을 변수
			String total_years 	= "";		//계약연수+계약구분
			String contract_years = "";		//계약연수를 담을 변수
			String contract_division = "";	//계약구분을 담을 변수
			String removeChar = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";						//특수문자 제거 
			
			//-----------------------------------
			//파싱된 VOList 출력
			//1. 계약기간 일자를 파싱하여 시작일, 종료일을 분리합니다.
			//2. 계약연수와 계약구분을 분리합니다. 
			//3. 저장 및 수정
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {
				
				contractVO = contractList.get(i);													//반복문으로 객체 가져오기
				
				total_date = contractVO.getTotal_date();
				total_date = total_date.replaceAll("~", "").replaceAll(" ","").replaceAll("/", "");
				
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
				
				logger.info(contractVO.toString());
				
				if(reportDAO.updateContract(contractVO) == 0) {reportDAO.insertContract(contractVO);};	//업데이트 할 내용이 없으면 저장
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertContract() : ");
			logger.error(e.toString());
			
		};//try
		
	}
	
	
	
	/**
	 * 세부업무 실적 삭제
	 */
	public void deleteContract(HttpServletRequest request) {

		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//매개변수 string으로 받기
			logger.info(str); 																		//매개변수 로그 츨략
			JSONArray jsonArray = new JSONArray(str);												//json배열 선언
			Gson gson = new Gson();																	//gson 선언
			Type listType = new TypeToken<ArrayList<ContractVO>>(){}.getType();				//주요계약현황VO의 List.class 
			List<ContractVO> contractList = gson.fromJson(jsonArray.toString(), listType);			//jsonArray -> VO로 파싱
			ContractVO contractVO = new ContractVO();												//주요계약현황 VO
			
			//-----------------------------------
			//파싱된 VOList 출력
			//-----------------------------------
			for(int i = 0; i < contractList.size(); ++i) {
				
				contractVO = contractList.get(i);													//반복문으로 객체 가져오기
				reportDAO.deleteContract(contractVO);												//주요계약현황 DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteContract() : ");
			logger.error(e.toString());
			
		}
		
	}
	
}
