package com.onlyReport.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;
import com.onlyReport.report.util.PagingUtil;

/**
 *<pre>
 *관리보고서 컨트롤러
 *</pre>
 *
 *@ClassName : ReportController.java 
 *@Description : 관리보고서에 관련된 기능을 정의한 컨트롤러입니다.
 *@author user
 *@since 2020. 11. 26
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 26   user   최초생성
 *</pre>
 */
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

		logger.info("================================ START ================================");								//menuList 시작
		
		
		ModelAndView  mav = new ModelAndView("MenuList");				//menuList model 선언
		mav.setViewName("main/menuList");								//jsp 경로
		
		logger.info("================================ E N D ================================");								//menuList 종료
		return mav;
	}
	
	/**
	 * 메인 페이지 
	 * 관리보고서 메인페이지입니다.
	 * @return
	 */
	@RequestMapping(value="/mainPage")
	public ModelAndView MainPage() {

		logger.info("================================ START ================================");								//mainPage 시작
		
		
		ModelAndView  mav = new ModelAndView("MainPage");				//mainPage model 선언
		mav.setViewName("main/mainPage");								//jsp 경로
		
		logger.info("================================ E N D ================================");								//mainPage 종료
		return mav;
	}
	
	/**
	 * 연간스케쥴 리스트
	 * 연간스케쥴 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/scheduleList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	public ModelAndView TableTest(HttpServletRequest request, Model model
								, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
								, @RequestParam(value="addList", required =false,  defaultValue="") String addList) {

		logger.info("================================ START ================================");							//scheduleList 시작
		List<Annuail_ScheduleVO> scheduleList = new ArrayList();		//테이블 리스트
			
		try {
		
			Annuail_ScheduleVO tableVO = new Annuail_ScheduleVO();		//테이블을 테스트하기 위한 리스트 VO	
			scheduleList =  reportService.selectScheduleList(request, division, addList);		//연간 스케쥴 목록
			Map resultMap = new HashMap();
			resultMap.put("resultCode", "0000");						//응답코드	 0000:정상  / 9000:비정상
			resultMap.put("scheduleList", scheduleList);				//테이블 리스트
			resultMap.put("scheduleCnt", scheduleList.size());			//테이블 수 
			resultMap.put("division", division);						//업무구분
			
			ModelAndView  mav = new ModelAndView("main/scheduleList",resultMap);			//Report model 선언
			logger.info("================================ E N D ================================");						//scheduleList 종료
			return mav;													//mav리턴
			
		}catch(Exception e) {
			ModelAndView  mav = new ModelAndView("ScheduleList");			//Report model 선언
			mav.setViewName("main/scheduleList");			
			logger.error(e.toString());									//오류메시지
			return mav;													//mav리턴
			
		}//catch
		

	}
	
	/**
	 * 연간스케쥴 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/insertSchedule", method = {RequestMethod.POST}) 
	public ModelAndView InsertSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//insertSchedule 시작

		HttpSession session = request.getSession();						//세션
		
		try {
			
			reportService.insertSchedule(request);						//연간스케쥴 저장/수정
			
		}catch(Exception e) {
			
			logger.error(e.toString());									//오류메시지
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");				//Report model 선언
		mav.setViewName("main/menuList");								//jsp 경로
		logger.info("================================ E N D ================================");						//insertSchedule 종료
		return mav;
	}
	
	/**
	 * 연간스케쥴 삭제
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSchedule", method = {RequestMethod.POST}) 
	public ModelAndView DeleteSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//deleteSchedule 시작

		HttpSession session = request.getSession();						//세션
		
		try {
			
			reportService.deleteSchedule(request);						//연간스케쥴 삭제
			
		}catch(Exception e) {
			
			logger.error(e.toString());									//오류메시지
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");				//Report model 선언
		mav.setViewName("main/menuList");								//jsp 경로
		mav.addObject("resultCode", "0000");							//반환코드
		logger.info("================================ E N D ================================");						//deleteSchedule 종료
		return mav;
		
	}
	
}
