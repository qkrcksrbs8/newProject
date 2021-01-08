package kr.co.swrts.contents.report.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.swrts.contents.report.domains.ContractMstVO;
import kr.co.swrts.contents.report.domains.DetailedWorkMstVO;
import kr.co.swrts.contents.report.domains.FileMstVO;
import kr.co.swrts.contents.report.domains.LiftMstVO;
import kr.co.swrts.contents.report.domains.PaymentStatusMstVO;
import kr.co.swrts.contents.report.domains.RepairMstVO;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.domains.TrainingMstVO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	//--------------------------------------------------------------------------------------------
	@RequestMapping(value = "/mres0103", method = { RequestMethod.GET, RequestMethod.POST })
	public String mres0103(Locale locale, Model model) {
		 return "contents/report/mres0103.tiles";
	}
	@RequestMapping(value = "/mres0201", method = { RequestMethod.GET, RequestMethod.POST })
	public String mres0201(Locale locale, Model model) {
		 return "contents/report/mres0201.tiles";
	}
	@RequestMapping(value = "/mres0206", method = { RequestMethod.GET, RequestMethod.POST })
	public String mres0206(Locale locale, Model model) {
		 return "contents/report/mres0206.tiles";
	}
	@RequestMapping(value = "/mres0602", method = { RequestMethod.GET, RequestMethod.POST })
	public String mres0602(Locale locale, Model model) {
		 return "contents/report/mres0602.tiles";
	}
	@RequestMapping(value = "/mres0707", method = { RequestMethod.GET, RequestMethod.POST })
	public String mres0707(Locale locale, Model model) {
		 return "contents/report/mres0707.tiles";
	}
	

	//--------------------------------------------------------------------------------------------
	
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
	@RequestMapping(value = "/scheduleList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView scheduleMst(HttpServletRequest request, Model model, HttpSession session
			, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
			, @RequestParam(value="selectDate", required =false,  defaultValue="0000") String selectDate
			, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {
		
		logger.info("================================ START ================================");	//scheduleList 시작
													
		Map<String, Object> resultMap = new HashMap<String, Object>();			//리턴해주는 데이터를 담을 map
		
		try{

			selectDate = checkCalDate(selectDate);		//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(selectDate);	//기준년도 배열 만들기
			 
			resultMap = reportService.selectScheduleList(request, session, division, addList, selectDate);//연간스케쥴 조회
			resultMap.put("resultCode", resultMap.get("resultCode"));			//응답코드	 0000:정상  / 9000:비정상
			resultMap.put("scheduleList", resultMap.get("scheduleList"));		//테이블 리스트
			resultMap.put("scheduleCnt", resultMap.get("selectScheduleCnt"));	//테이블 수 
			resultMap.put("addList", addList);									//add:행추가 / normal:일반 출력
			resultMap.put("division", division);								//업무구분	
			resultMap.put("selectCalDate", selectDate);	//선택한 년도
			resultMap.put("calDate", calDateStr);		//달력 배열
			
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles",resultMap);//연간스케쥴 model
			
			logger.info("================================ E N D ================================");//scheduleList 종료
			return mav;
			
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ReportController.scheduleMst()");
			logger.error(e.toString());
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles");	//연간스케쥴 model
			return mav;
			
		}//try
		
	};//scheduleMst
	
	
	/**
	 * 연간스케쥴 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertSchedule", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertSchedule(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");						//insertSchedule 시작
		
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertSchedule(request, session);						//연간스케쥴 저장/수정
			
		}catch(Exception e) {

			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error("java.lang.Exception : ReportController.insertSchedule()");
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");						//insertSchedule 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
	}

	
	/**
	 * 연간스케쥴 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteSchedule", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeleteSchedule(HttpServletRequest request, Model model, HttpSession session) {

		logger.info("================================ START ================================");						//deleteSchedule 시작
		
		HttpStatus statusCode = HttpStatus.OK;							//status 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteSchedule(request, session);						//연간스케쥴 삭제
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");						//deleteSchedule 종료
		
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	
	/**
	 * 세무업무 실적
	 * 세무업무 실적 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/detailedWorkList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView DetailedWorkMst(HttpServletRequest request, HttpSession session
								, @RequestParam(value="selectDate", required =false, defaultValue="0000") String selectDate
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");	//detailWork 시작
		
		logger.info("param workDate : "+selectDate); 
		logger.info("param addList : "+addList);
		
		List<DetailedWorkMstVO> detailWorkList = new ArrayList<DetailedWorkMstVO>();			//테이블 리스트
			
		try {

			selectDate = checkCalDate(selectDate);//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(selectDate);	//기준년도 배열 만들기
			
			detailWorkList =  reportService.selectDetailedWorkList(request, session, selectDate, addList);	//세부업무 리스트 조회
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("detailWorkList", detailWorkList);				//테이블 리스트
			resultMap.put("detailWorkCnt", detailWorkList.size());			//테이블 수 
			resultMap.put("addList", addList);								//add:행추가 / normal:일반 출력
			resultMap.put("selectDate", selectDate);						//선택한 년도
			resultMap.put("calDate", calDateStr);//달력
			//기준년도 달력 보내기. 금년 기준으로 +1년, -5년까지 ex. 금년 : 2020 | 보여줄 년도 : 2021 ~ 2015 
			
			ModelAndView  mav = new ModelAndView("contents/report/detailedWorkMst.tiles",resultMap);//detailWork model 선언
			logger.info("================================ E N D ================================");	//scheduleList 종료
			return mav;																				//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("DetailedWorkList");	//detailWork model 선언
			mav.setViewName("contents/report/detailedWorkMst.tiles");			
			logger.error(e.toString());									//오류메시지
			return mav;													//mav리턴
			
		}//catch

	}
	
	
	/**
	 * 세부업무 실적 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertDetailedWork", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertDetailedWork(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//insertDetailedWork 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertDetailedWork(request, session);					//세부업무 실적 저장/수정
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertDetailedWork 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
	}

	
	/**
	 * 세부업무 실적 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteDetailedWork", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeleteDetailedWork(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deleteDetailedWork 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteDetailedWork(request, session);					//세부업무 삭제
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteDetailedWork 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	
	/**
	 * 하자보수현황
	 * 하자보수현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/repairList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView RepairList(HttpServletRequest request, Model model, HttpSession session
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList
								, @RequestParam(value="fr_cal", required =false,  defaultValue="20200101") String frThisYear
								, @RequestParam(value="to_cal", required =false,  defaultValue="20201231") String toThisYear) {

		logger.info("================================ START ================================");		//repairList 시작
		
		logger.info("param addList : "+addList);
		logger.info("param fr_cal : "+frThisYear);
		logger.info("param to_cal : "+toThisYear);
		
		List<RepairMstVO> repairList = new ArrayList<RepairMstVO>();		//테이블 리스트
			
		try {
		
			repairList =  reportService.selectRepairList(request, addList, frThisYear, toThisYear, session);	//하자보수 리스트 조회
			
			String mainPath =request.getSession().getServletContext().getRealPath("/");//초기 경로 ex - C:/
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("selectRepairList", repairList);					//테이블 리스트
			resultMap.put("selectRepairCnt", repairList.size());			//테이블 수 
			resultMap.put("addList", addList);								//add:행추가 / normal:일반 출력
			resultMap.put("mainPath", mainPath);							//파일경로 앞 부분 ex. C:/
			resultMap.put("fr_cal", stringToDate(frThisYear));				//검색 시작일
			resultMap.put("to_cal", stringToDate(toThisYear));				//검색 종료일
			
			ModelAndView  mav = new ModelAndView("contents/report/repairMst.tiles",resultMap);		//repairList model 선언
			logger.info("================================ E N D ================================");	//repairList 종료
			return mav;														//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("RepairList");				//repairList model 선언
			mav.setViewName("contents/report/repairMst.tiles");			
			logger.error(e.toString());										//오류메시지
			return mav;														//mav리턴
			
		}//catch

	}//repairList()
	
	
	/**
	 * 하자보수 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertRepair", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertRepair(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//insertRepair 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertRepair(request, session);						//하자보수 저장/수정
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertRepair 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
	}
	
	
	/**
	 * 하자보수현황 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteRepair", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> deleteRepair(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deleteRepair 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteRepair(request, session);						//하자보수현황 삭제
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteRepair 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	
	/**
	 * 주요계약 현황
	 * 주요계약 현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/contractList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView ContractList(HttpServletRequest request, HttpSession session
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");						//contractList 시작
		
		logger.info("param addList : "+addList);
		
		List<ContractMstVO> contractList = new ArrayList<ContractMstVO>();											//테이블 리스트
			
		try {
		
			contractList =  reportService.selectContractList(request, addList, session);							//주요계약현황 목록
	
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("contractList", contractList);															//테이블 리스트
			resultMap.put("contractCnt", contractList.size());														//테이블 수 
			resultMap.put("addList", addList);																		//add:행추가 / normal:일반 출력
			
			ModelAndView  mav = new ModelAndView("contents/report/contractMst.tiles",resultMap);					//contractList model 선언
			logger.info("================================ E N D ================================");					//contractList 종료
			return mav;																								//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("ContractList");													//contractList model 선언
			mav.setViewName("contents/report/contractMst.tiles");			
			logger.error(e.toString());																				//오류메시지
			return mav;																								//mav리턴
			
		}//catch

	}//contractList()
	
	
	/**
	 * 주요계약현황 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertContract", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertContract(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//insertContract 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertContract(request, session);				//주요계약현황 저장/수정
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertContract 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
	}
	
	
	/**
	 * 주요계약현황 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteContract", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeleteContract(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deleteContract 시작
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteContract(request, session);				//주요계약현황 삭제
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteContract 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	
	/**
	 * 설비 및 수불 현황
	 * 설비 및 수불 현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/paymentStatusList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView PaymentStatusList(HttpServletRequest request, HttpSession session
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList
								, @RequestParam(value="selectDate", required =false,  defaultValue="0000") String selectDate) {

		logger.info("================================ START ================================");	//paymentStatusList 시작
		
		logger.info("param addList : "+addList);
		
		List<PaymentStatusMstVO> paymentStatusList = new ArrayList<PaymentStatusMstVO>();	//테이블 리스트
			
		try {

			selectDate = checkCalDate(selectDate);		//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(selectDate);	//기준년도 배열 만들기
			
			paymentStatusList =  reportService.selectPaymentStatusList(request, session, addList, selectDate);	//설비 및 수불 현황 목록
	
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("paymentStatusList", paymentStatusList);			//테이블 리스트
			resultMap.put("paymentStatusCnt", paymentStatusList.size());	//테이블 수 
			resultMap.put("addList", addList);								//add:행추가 / normal:일반 출력
			resultMap.put("selectDate", selectDate);						//기준년도
			resultMap.put("calDate", calDateStr);							//달력 -> 추후 변경예정 
			
			ModelAndView  mav = new ModelAndView("contents/report/paymentStatusMst.tiles",resultMap);	//paymentStatusList model 선언
			logger.info("================================ E N D ================================");		//paymentStatusList 종료
			return mav;																		//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("PaymentStatusMst");						//paymentStatusList model 선언
			mav.setViewName("contents/report/paymentStatusMst.tiles");			
			logger.error(e.toString());														//오류메시지
			return mav;																		//mav리턴
			
		}//catch

	}//paymentStatusList
	

	/**
	 * 설비 및 수불 현황 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertPaymentStatus", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertPaymentStatus(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//insertPaymentStatus 시작

		HttpStatus statusCode = HttpStatus.OK;								//통신 상태 값
		Map<String, Object> resultMap	= new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();	//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.insertPaymentStatus(request, session);			//설비 및 수불현황 저장/수정
			resultMap.put("resultCode", statusMap.get("resultCode"));		//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");							//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());										//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertPaymentStatus 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//InsertPaymentStatus()
	
	
	/**
	 * 설비 및 수불 현황 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deletePaymentStatus", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeletePaymentStatus(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deletePaymentStatus 시작
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.deletePaymentStatus(request, session);		//설비 및 수불 현황 삭제
			resultMap.put("resultCode", statusMap.get("resultCode"));	//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deletePaymentStatus 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	/**
	 * 승강기/화재예방 리스트
	 * 승강기/화재예방 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/liftList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView LiftList(HttpServletRequest request, HttpSession session
								, @RequestParam(value="inspection_division", required =false,  defaultValue="1") String inspection_division
								, @RequestParam(value="selectDate", required =false,  defaultValue="0000") String selectDate) {

		logger.info("================================ START ================================");	//lift 시작
		
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
			
		try {

			selectDate = checkCalDate(selectDate);				//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(selectDate);	//기준년도 배열 만들기
			
			statusMap = reportService.selectLiftList(request, session, inspection_division, selectDate);	//승강기 목록
	
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("liftList", statusMap.get("selectLiftVOList"));//테이블 리스트
			resultMap.put("liftCnt", statusMap.get("liftCnt"));			//테이블 수 
			resultMap.put("inspection_division", statusMap.get("inspection_division"));	//1:승강기 2:화재예방
			resultMap.put("selectCalDate", selectDate);
			resultMap.put("calDate", calDateStr);
			
			ModelAndView  mav = new ModelAndView("contents/report/liftMst.tiles",resultMap);	//lift model 선언
			logger.info("================================ E N D ================================");	//lift 종료
			return mav;													//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("LiftListMst");		//liftList model 선언
			mav.setViewName("contents/report/liftMst.tiles");			
			logger.error(e.toString());									//오류메시지
			return mav;													//mav리턴
			
		}//catch

	}//liftList
	
	
	/**
	 * 승강기/화재예방 세부내용 리스트
	 * 승강기/화재예방 세부내용 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/liftContnet", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView LiftContent(HttpServletRequest request, HttpSession session
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList
								, @RequestParam(value="inspection_division", required =false,  defaultValue="") String inspection_division
								, @RequestParam(value="lift_seq", required =false,  defaultValue="0") int lift_seq) {
		
		logger.info("================================ START ================================");	//liftContnet 시작
		
		logger.info("param addList : "+addList);
		logger.info("param lift_seq : "+lift_seq);
		logger.info("param inspection_division : "+inspection_division);
		
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
		Map<String, Object> resultMap = new HashMap<String, Object>();	//반환할 map
		
		try {
		 
			statusMap =  reportService.selectLiftContentList(request, session, addList, lift_seq);	//승강기 목록
			resultMap.put("addList", addList);												//add:행추가 / normal:일반 출력
			resultMap.put("LiftVO", statusMap.get("selectLiftVO"));							//승강기 내용
			resultMap.put("liftContentCnt", statusMap.get("liftContentCnt"));	 			//승강기 상세내용 개수
			resultMap.put("liftContentList", statusMap.get("selectLiftContentList"));		//승강기 상세내용 리스트
			resultMap.put("lift_seq", lift_seq);											//승강기 목록 번호가 있으면 바로 리턴
			resultMap.put("inspection_division", inspection_division);		//1:승강기 2:화재예방
			
			ModelAndView  mav = new ModelAndView("contents/report/liftContentMst.tiles",resultMap);	//liftContnet model 선언
			logger.info("================================ E N D ================================");	//liftContnet 종료
			return mav;													//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("LiftContent");	//liftContnet model 선언
			mav.setViewName("contents/report/liftContentMst.tiles");			
			logger.error(e.toString());									//오류메시지
			return mav;													//mav리턴
			
		}//catch

	}//liftList
	
	
	/**
	 * 승강기/화재예방 저장 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertLift", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertLift(HttpServletRequest request, HttpSession session
			, @RequestParam(value="lift_seq", required =false,  defaultValue="0") int lift_seq
			, @RequestParam(value="sub_manager", required =false,  defaultValue="") String sub_manager) {

		logger.info("================================ START ================================");	//insertLift 시작 

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.insertLift(request, session, lift_seq, sub_manager);						//주요계약현황 저장/수정
			resultMap.put("resultCode", statusMap.get("resultCode"));						//0000:정상  / 9000:오류
			if(0 == lift_seq) {
				resultMap.put("lift_seq", statusMap.get("lift_seq"));						//승강기 목록 번호가 없으면 조회 후 리턴
			}else {
				resultMap.put("lift_seq", lift_seq);										//승강기 목록 번호가 있으면 바로 리턴
			}
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertLift 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
	}	
	
	
	/**
	 * 교육 현황
	 * 교육 현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/trainingList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView TrainingList(HttpServletRequest request, Model model, HttpSession session
								, @RequestParam(value="trainingDate", required =false, defaultValue="0000") String trainingDate
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");	//trainingList 시작
		
		logger.info("param addList : "+addList);
		logger.info("param trainingDate : "+trainingDate);
		
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
			
		try {
	
			trainingDate = checkCalDate(trainingDate);			//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(trainingDate);	//기준년도 배열 만들기
			
			statusMap =  reportService.selectTrainingList(request, session, trainingDate, addList);	//교육현황 목록
	
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("trainingList", statusMap.get("trainingList"));	//테이블 리스트
			resultMap.put("trainingCnt", statusMap.get("trainingCnt"));		//테이블 수 
			resultMap.put("trainingDate", statusMap.get("trainingDate"));	//교육일자
			resultMap.put("addList", addList);			//add:행추가 / normal:일반 출력
			resultMap.put("trainingDate", trainingDate);	//선택한 년도
			resultMap.put("calDate", calDateStr);		//달력
			
			ModelAndView  mav = new ModelAndView("contents/report/trainingMst.tiles",resultMap);//trainingList model 선언
			logger.info("================================ E N D ================================");	//trainingList 종료
			return mav;	//mav리턴
			
		}catch(Exception e) {
			 
			ModelAndView  mav = new ModelAndView("TrainingList");	//trainingList model 선언
			mav.setViewName("contents/report/trainingMst.tiles");			
			logger.error(e.toString());								//오류메시지
			return mav;												//mav리턴
			
		}//catch

	}//contractList
	

	/**
	 * 교육현황 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertTraining", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertTraining(HttpServletRequest request, HttpSession session, Model model) {

		logger.info("================================ START ================================");	//insertTraining 시작

		HttpStatus statusCode = HttpStatus.OK;								//통신 상태 값
		Map<String, Object> resultMap	= new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();	//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.insertTraining(request, session);		//교육현황 저장/수정
			resultMap.put("resultCode", statusMap.get("resultCode"));		//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");							//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());										//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertTraining 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//InsertPaymentStatus()
	
	
	/**
	 * 교육현황 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteTraining", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeleteTraining(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deleteTraining 시작
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.deleteTraining(request, session);			//설비 및 수불 현황 삭제
			resultMap.put("resultCode", statusMap.get("resultCode"));	//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteTraining 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//deleteTraining()
	
	
	/**
	 * 관리단회의록 
	 * 관리단회의록 조회
	 * @return
	 */
	@RequestMapping(value = "/meetingLogList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView MeetingLogList(HttpServletRequest request, HttpSession session
			, @RequestParam(value="selectDate", required =false, defaultValue="0000") String selectDate
			, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {
		
		logger.info("================================ START ================================");			//meetingLogList 시작
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();	//상태 값을 담은 map
		
		try{
			
			selectDate = checkCalDate(selectDate);		//기준년도 있는지 체크 -> 2020
			String[] calDateStr = createCalDate(selectDate);	//기준년도 배열 만들기
			 
			statusMap = reportService.selectMeetingLog(request, session, selectDate, addList);		//meetingLogList 조회
			resultMap.put("meetingLogCnt", statusMap.get("meetingLogCnt"));		//테이블 리스트
			resultMap.put("meetingLogList", statusMap.get("meetingLogList"));	//테이블 수 
			resultMap.put("addList", addList);									//add:행추가 / normal:일반 출력
			resultMap.put("selectDate", selectDate);							//선택한 년도
			resultMap.put("calDate", calDateStr);								//달력
			ModelAndView  mav = new ModelAndView("contents/report/meetingLogMst.tiles",resultMap);	//meetingLogList model
			
			System.out.println("#########################"+statusMap.get("meetingLogList").toString());
			
			logger.info("================================ E N D ================================");	//meetingLogList 종료
			return mav;
			
		}catch(Exception e) {
			
			logger.error("java.lang.Exception : ReportController.meetingLogList()");
			logger.error(e.toString());
			ModelAndView  mav = new ModelAndView("contents/report/meetingLogMst.tiles");		//meetingLogList model
			return mav;
		}//try
		
	};//meetingLogList
	
	
	/**
	 * 관리단회의록 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertMeetingLog", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertMeetingLog(HttpServletRequest request, HttpSession session, Model model) {

		logger.info("================================ START ================================");	//insertMeetingLog 시작

		HttpStatus statusCode = HttpStatus.OK;								//통신 상태 값
		Map<String, Object> resultMap	= new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();	//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.insertMeetingLog(request, session);		//교육현황 저장/수정
			resultMap.put("resultCode", statusMap.get("resultCode"));		//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");							//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());										//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//insertMeetingLog 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//insertMeetingLog()
	
	
	/**
	 * 관리단회의록 삭제
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteMeetingLog", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> DeleteMeetingLog(HttpServletRequest request, HttpSession session) {

		logger.info("================================ START ================================");	//deleteMeetingLog 시작
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 map
		
		try {
			
			statusMap = reportService.deleteMeetingLog(request, session);//관리단회의록 삭제
			resultMap.put("resultCode", statusMap.get("resultCode"));	//0000:정상 | 8000:디비오류 | 9000:비정상
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상 | 8000:디비오류 | 9000:비정상
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteMeetingLog 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//deleteMeetingLog()
	
	
	/**
	*파일 업로드
	*@param dto
	*@param file
	*@return
	*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/fileUpload", method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<Map> fileSubmit(HttpServletRequest request, HttpSession session
							, FileMstVO fileMstVO
							, @RequestParam("file") MultipartFile file
//							, @RequestParam(value="file", required =false, defaultValue="") MultipartFile file
							, @RequestParam(value="table_seq", required =false, defaultValue="0") int table_seq) {

		logger.info("================================ START ================================");	
		
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		Map<String, Object> statusMap 	= new  HashMap<String, Object>();//상태 값을 담은 mapstatusMap
		
		try {

			statusMap = reportService.fileInsert(request, session, fileMstVO, file, table_seq);	//파일업로드
			resultMap.put("resultCode", statusMap.get("resultCode"));//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
			resultMap.put("fileList", statusMap.get("fileList"));	//파일 리스트 개수
			resultMap.put("fileCnt", statusMap.get("fileCnt"));		//파일 리스트
				
		}catch(Exception e) {
			
			logger.error("fileUpload() : "+e.toString());
			resultMap.put("resultCode", "9000");//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
			
		}//try - catch 
		
		/* 데이터 베이스 처리를 현재 위치에서 처리*/
//		return "redirect:scheduleList";
		
		return new ResponseEntity<Map>(resultMap, statusCode);
	}//

	
	/**
	*파일 다운로드
	*@param response
	*@param request
	*@param paramMap
	*/
	@RequestMapping(value="/fileDownload")
	public void fileDownload( HttpServletResponse response, HttpServletRequest request
							, @RequestParam(value="table_name", required =false, defaultValue="none") String table_name
							, @RequestParam(value="table_seq", required =false, defaultValue="0") int table_seq
							, @RequestParam(value="file_seq", required =false, defaultValue="0") int file_seq) {

		logger.info("================================ START ================================");	
		
		
		try {
			
			reportService.fileDownload(response, request, file_seq);//파일 다운로드
			
		}catch(Exception e) {
			
		}//try - catch
		
	}//fileDownLoad()
	
	
	/**
	*파일 리스트
	*@param response
	*@param request
	*@param paramMap
	*/
	@RequestMapping(value="/selectFileList")
	public ResponseEntity<Map> selectFileList( HttpServletResponse response, HttpServletRequest request
							, HttpSession session
							, @RequestParam(value="table_name", required =false, defaultValue="none") String table_name
							, @RequestParam(value="table_seq", required =false, defaultValue="0") int table_seq) {

		logger.info("================================ START ================================");	
		HttpStatus statusCode = HttpStatus.OK;								//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();		//리턴해주는 데이터를 담을 map
		List<FileMstVO> fileList = new ArrayList<FileMstVO>();				//파일VO List
		
		try {

			fileList = reportService.selectFileList(table_name, table_seq, session);	//파일 리스트
			resultMap.put("resultCode", "0000");							//0000:정상  9000:비정상
			resultMap.put("fileCnt", fileList.size());						//파일 리스트 개수
			resultMap.put("fileList", fileList);							//파일 리스트
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");							//0000:정상  9000:비정상
			logger.error("selectFileList() : "+e.toString());
			
		}//try - catch
		
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}//selectFileList()
	
	
	/**
	*숫자를 날짜로 변환
	*@param cal
	*@return
	*/
	public String stringToDate(String cal) {
		
		if(cal.length() == 8) {

			StringBuffer sb = new StringBuffer();//스트링 버퍼
			String yyyy = cal.substring(0,4);//년 2020
			String mm	= cal.substring(4,6);//월 09
			String dd	= cal.substring(6,8);//일 02
			sb.append(yyyy);	//2020
			sb.append("-");		//2020-
			sb.append(mm);		//2020-09
			sb.append("-");		//2020-09-
			sb.append(dd);		//2020-09-02
	
			return sb.toString();

		}else {
			return cal;
		}//if - else
		
	}//intToDate
	
	
	/**
	*기준년도 값 유무 체크
	*@param calDate
	*@return
	*/
	public String checkCalDate(String calDate) {
		
		//--------------------------------------- 
		//기준년도의 default는 "0000"
		//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
		//---------------------------------------
		if("0000".equals(calDate)) {
			
			LocalDate now = LocalDate.now();									//현재 날짜
			calDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));	//년월일 파싱 2020.10.10 
			calDate = calDate.substring(0,4);									//년도 파싱 2020
			
		};//if
	
		return calDate;
		
	}
	
	
	/**
	*기준년도 배열 만들기
	*@param calDate
	*@return
	*/
	public String[] createCalDate(String calDate) {
		   
		int calDateInt = Integer.parseInt(calDate) + 5;	//지금년도에 +5. 내년 일정을 정할 수 있어서. -> 추후 달력 기능 변경 예정
		String[] calDateStr = new String[11];			//반환할 배열
		
		// 2021~2015 | 6년치 년도 | 추후 달력기능 변경예정
		for(int i = 0; i < 11; ++i) {
			calDateStr[i] = Integer.toString(calDateInt--); 
		}//for
		
		return calDateStr;
		
	}
}
