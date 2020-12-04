package kr.co.swrts.contents.report.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.services.ReportService;

/**
 *<pre>
 *연간데이터 보고서 컨트롤러
 *</pre>
 *
 *@ClassName : ReportController.java 
 *@Description : 연간데이터 보고서의 POJO 클레스입니다.
 *@author 박찬균 주임연구원
 *@since 2020. 12. 4
 *@version 1.0
 *@see
 *@Modification Information
 */
@Controller
public class ReportController {
	
	/**
	 * 로그
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 연간데이터 보고서 서비스 로직
	 */
	@Autowired
	private ReportService reportService;
	
	
	/**
	*메인페이지
	*@param locale
	*@param model
	*@return
	*/
	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Locale locale, Model model) {
		 return "contents/main/main.tiles";
	}
	 
	 
	/**
	*연간스케쥴 조회
	*@param locale
	*@param model
	*@param session
	*@return
	*/
	@RequestMapping(value = "/scheduleMst", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView scheduleMst(HttpServletRequest request, Model model
			, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
			, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {
		
		logger.info("================================ START ================================");						//scheduleList 시작
		
		try{
	
			List<ScheduleMstVO> scheduleList = new ArrayList<ScheduleMstVO>();										//연간스케쥴VO List		
			Map<String, Object> resultMap = new HashMap<String, Object>();											//리턴해주는 데이터를 담을 map
			
			scheduleList = reportService.selectScheduleList(request, division, addList);							//연간스케쥴 조회
			resultMap.put("resultCode", "0000");																	//응답코드	 0000:정상  / 9000:비정상
			resultMap.put("scheduleList", scheduleList);															//테이블 리스트
			resultMap.put("scheduleCnt", scheduleList.size());														//테이블 수 
			resultMap.put("addList", addList);																		//add:행추가 / normal:일반 출력
			resultMap.put("division", division);																	//업무구분	
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles",resultMap);					//연간스케쥴 model
			
			logger.info("================================ E N D ================================");					//scheduleList 종료
			return mav;
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ", "ReportController.scheduleMst()");
			logger.error(e.toString());
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles");									//연간스케쥴 model
			return mav;
		}//try
		
	};//scheduleMst
	
	
	
	/**
	 * 연간스케쥴 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/insertSchedule", method = {RequestMethod.POST}) 
	public ModelAndView InsertSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//insertSchedule 시작

		try {
			
			reportService.insertSchedule(request);																	//연간스케쥴 저장/수정
			
		}catch(Exception e) {
			
			logger.error(e.toString());																				//오류메시지
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");															//Report model 선언
		mav.setViewName("contents/report/scheduleMst.tiles");																			//jsp 경로
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

		try {
			
			reportService.deleteSchedule(request);																	//연간스케쥴 삭제
			
		}catch(Exception e) {
			
			logger.error(e.toString());																				//오류메시지
			
		};
		
		ModelAndView  mav = new ModelAndView("MenuList");															//Report model 선언
		mav.setViewName("contents/report/scheduleMst.tiles");														//jsp 경로
		logger.info("================================ E N D ================================");						//deleteSchedule 종료
		return mav;
		
	}
	
	
	/**
	*연간스케쥴 조회
	*@param locale
	*@param model
	*@param session
	*@return
	*/
	@RequestMapping(value = "/detailedWorkMst", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView detailedWorkMst(HttpServletRequest request, Model model
			, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
			, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {
		
		logger.info("================================ START ================================");						//detailedWorkMst 시작
		
		try{
	
			List<ScheduleMstVO> scheduleList = new ArrayList<ScheduleMstVO>();										//세부업무실적VO List		
			Map<String, Object> resultMap = new HashMap<String, Object>();											//리턴해주는 데이터를 담을 map
			
			scheduleList = reportService.selectScheduleList(request, division, addList);							//세부업무실적 조회
			resultMap.put("resultCode", "0000");																	//응답코드	 0000:정상  / 9000:비정상
			resultMap.put("scheduleList", scheduleList);															//테이블 리스트
			resultMap.put("scheduleCnt", scheduleList.size());														//테이블 수 
			resultMap.put("addList", addList);																		//add:행추가 / normal:일반 출력
			resultMap.put("division", division);																	//업무구분	
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles",resultMap);					//세부업무실적 model
			
			logger.info("================================ E N D ================================");					//detailedWorkMst 종료
			return mav;
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ", "ReportController.scheduleMst()");
			logger.error(e.toString());
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles");								//세부업무실적 model
			return mav;
		}//try
		
	};//scheduleMst
	
	
}
