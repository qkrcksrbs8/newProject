package com.onlyReport.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlyReport.comm.model.Annuali_ScheduleStores;
import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;
import com.onlyReport.report.util.PagingUtil;

@Controller
public class ReportController {

	Logger logger = Logger.getLogger(this.getClass());//로그

	@Autowired
	private ReportService reportService;//게시판 인터페이스
	
	/**
	 * 게시판 조회
	 */
	@RequestMapping(value="/reportList")
	public ModelAndView ReportList(@RequestParam(value="pageNum",defaultValue="1")int currentPage
								, @RequestParam(value="keyField", required =false) String keyField
								, @RequestParam(value="keyWord", required =false) String keyWord
								) {

		logger.info("ReportList() : start");						//ReportList 시작
		logger.info("keyField : "+keyField);					
		logger.info("keyWord : "+keyWord);
		
		List<ReportVO> reportList = new ArrayList<ReportVO>();		//게시판 리스트
		Map<String, Object> map = new HashMap<String, Object>();//페이징 map
		map.put("keyField", keyField);							//검색분야
		map.put("keyWord", keyWord);							//검색어
		
		int count = reportService.selectReportCnt(map);			//게시판 리스트 수 조회
		logger.info("count : "+count);							//게시판 리스트 수 로그로 찍기

		PagingUtil page;//페이징 처리를 위한 객체 선언
		
		if(keyWord == null) {
			page = new PagingUtil(currentPage, count, 5,2, "reportList.do");							//검색어가 있다면
		}else {
			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "reportList.do",null);	//검색어가 없다면
		}
		

		//--------------------------------
		//start->페이지당 맨 첫번째 나오는 게시물번호
		//--------------------------------
		map.put("start", page.getStartCount());	//시작 게시물번호
		map.put("end", page.getEndCount());		//마지막게시물번호

		//---------------------------
		//게시물이 1개 이상 존재하면 리스트 조회
		//---------------------------
		if(count > 0) {
			
			reportList = reportService.selectReportList(map);//게시판 리스트 조회
			
		};//if
		
		ModelAndView  mav = new ModelAndView("Report");		//Report model 선언
		mav.setViewName("main/report");						//jsp 경로
		mav.addObject("count", count);						//총레코드수
		mav.addObject("reportList", reportList);				//게시판 리스트
		mav.addObject("pagingHtml", page.getPagingHtml());	//링크문자열을 전달
		mav.addObject("keyWord", keyWord);					//검색어 전달
		
		logger.info(reportList.toString());					//VO 로그로 찍어보기
		logger.info("reportList() : end");					//Report 종료
		return mav;
	}
	
	/**
	 * 게시판 상세
	 * @param Report_seq
	 * @return
	 */
	@RequestMapping(value="/reportDetail")
	public ModelAndView ReportDetail(@RequestParam(value="report_seq") int report_seq) {
		
		logger.info("start");	
		logger.info("report_seq : "+report_seq);
		ReportVO reportList = reportService.selectReport(report_seq);//게시판 상세 조회
		reportList.getreport_seq();
		
		logger.info(reportList.toString());
		
		ModelAndView  mav = new ModelAndView("ReportDetail");
		mav.setViewName("main/reportDetail");//jsp 경로
		mav.addObject("reportList", reportList);//게시판 리스트
		
		logger.info("end");
		
		return mav;
	}

	/**
	 * 게시판 업데이트
	 * @param Report_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateReport")
	public String updateReport(@RequestParam(value="report_seq") int report_seq
							, @RequestParam(value="report_title") String report_title
							, @RequestParam(value="report_content") String report_content
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("report_seq : "+report_seq);
		logger.info("report_title : "+report_title);
		logger.info("report_content : "+report_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 업데이트 map
			map.put("report_seq", report_seq);//게시글 시퀀스
			map.put("report_title", report_title.replace("\r\n","<br>"));//게시글 제목
			map.put("report_content", report_content.replace("\r\n","<br>"));//게시글 내용
			map.put("user_name", user_name);//최종수정자
			 
			resultCode = reportService.updateReport(map);//게시글 업데이트
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	
	/**
	 * 게시판 삭제
	 * @param Report_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteReport")
	public String deleteReport(@RequestParam(value="report_seq") int report_seq
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("report_seq : "+report_seq);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("report_seq", report_seq);//게시글 시퀀스
			map.put("user_name", user_name);//최종수정자
			
			resultCode = reportService.deleteReport(map);//게시글 삭제
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	/**
	 * 게시글 작성 페이지 이동
	 * @return
	 */
	@RequestMapping(value="/reportWrite")
	public ModelAndView ReportWrite() {
				
		ModelAndView  mav = new ModelAndView("ReportWrite");
		mav.setViewName("main/reportWrite");//jsp 경로
		
		return mav;
	}
	
	/**
	 * 게시글 작성
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertReport")
	public String ReportInsert(@RequestParam(value="report_title", required =false) String report_title
							  , @RequestParam(value="report_content", required =false) String report_content
							  , @RequestParam(value="user_name", required =false) String user_name) {
		
		logger.info("start");
		logger.info("report_title : "+report_title);
		logger.info("report_content : "+report_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("report_title", report_title);//게시글 제목
			map.put("report_content", report_content);//게시글 내용
			map.put("created_by", user_name);//생성자
			map.put("last_update_by", user_name);//최종수정자
			
			resultCode = reportService.insertReport(map);//게시글 삭제
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
				
		logger.info("end");
		
		return resultCode;
		
	}

	
	/**
	 * 메인 메뉴 
	 * 메뉴를 고를 수 있는 페이지
	 * @return
	 */
	@RequestMapping(value="/menuList")
	public ModelAndView MenuList() {

		logger.info("menuList() : start");						//menuList 시작
		
		
		ModelAndView  mav = new ModelAndView("MenuList");		//Report model 선언
		mav.setViewName("main/menuList");						//jsp 경로
		
		logger.info("menuList() : end");					//Report 종료
		return mav;
	}
	
	/**
	 * 테이블테스트
	 * 테이블을 리스트로 출력할거임
	 * @return
	 */
	@RequestMapping(value="/tableTest")
	public ModelAndView TableTest() {

		logger.info("tableTest() : start");		//tableTest 시작
		
		List<Annuail_ScheduleVO> tableList = new ArrayList();//테이블 리스트
		Annuail_ScheduleVO tableVO = new Annuail_ScheduleVO();//테이블을 테스트하기 위한 리스트 VO	
		int tableCnt = 0;						//테이블 총 개수
		
		for(int i = 0; i < 5; ++i) {
			
			tableVO = new Annuail_ScheduleVO();//테이블을 테스트하기 위한 리스트 VO
			tableVO.setJob_content("업무계약 "+i);	//업무내용
			tableVO.setSchedule_cycle(2);		//점검주기
			tableVO.setMonth2(1);				//3월   체크:1/논체크:0
			tableVO.setMonth3(1);				//3월   체크:1/논체크:0
			tableVO.setEntity("관리주체 "+i);		//관리주체
			tableVO.setFile_name("파일이름 "+i);	//파일이름
			tableList.add(i, tableVO);			//리스트 증가
			tableCnt++;							//테이블 개수 1씩 증가
			
		};//for
		
		logger.info(tableList.toString());		//테이블 데이터 확인
		
		ModelAndView  mav = new ModelAndView("TableTest");	//Report model 선언
		mav.setViewName("main/tableTest");					//jsp 경로
		mav.addObject("tableList", tableList);	//테이블 리스트
		mav.addObject("tableCnt", tableCnt);	//테이블 수 
		
		logger.info("tableTest() : end");					//tableTest 종료
		return mav;
	}
	
	/**
	 * 테이블테스트 저장
	 * 테이블 리스트 저장
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/tableInsert", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8") 
	public ModelAndView TableInsert(HttpServletRequest request, HttpServletResponse response, ModelMap model){

		logger.info("tableInsert() : start");		//tableTest 시작
		
		Annuali_ScheduleStores res = new Annuali_ScheduleStores();	//연간스케쥴 스토어
		HttpStatus statusCode = HttpStatus.OK;						//성공 여부 코드
		
		try {
			
			res.getResHeader().setUser_id("swrts");				//사용자 이름
			ObjectMapper m = new ObjectMapper();				//오브젝트
			String json_data = m.writeValueAsString(request);	//매개변수 데이터 파싱
			
			
			
			
		}catch(Exception e) {
			
		}
		
//		  매개변수 받아서 출력
		  Set<String> keySet = request.getParameterMap().keySet();
		  
		  System.out.println("keySet : "+keySet.toString());
	
		 
		
		/*
		 * Enumeration names = request.getParameterNames();
		 * while(names.hasMoreElements()) { String key = (String) names.nextElement();
		 * System.out.println(key + ": " + request.getParameter(key)); };
		 */
		
		
		System.out.println("반복문 여기까지 입니다.");
		
		
		ModelAndView  mav = new ModelAndView("MenuList");	//Report model 선언
		mav.setViewName("main/menuList");					//jsp 경로

		logger.info("tableInsert() : end");					//tableTest 종료
		return mav;
	}
	
}
