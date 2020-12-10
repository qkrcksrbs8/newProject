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
import kr.co.swrts.contents.report.domains.RepairMstVO;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.domains.TrainingMstVO;
import javax.servlet.http.HttpServletResponse;
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
	@RequestMapping(value = "/scheduleList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView scheduleMst(HttpServletRequest request, Model model
			, @RequestParam(value="division", required =false,  defaultValue="AS01") String division
			, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {
		
		logger.info("================================ START ================================");						//scheduleList 시작
													//상태 값 선언
		HashMap<String, Object> resultMap = new HashMap<String, Object>();											//리턴해주는 데이터를 담을 map
		
		try{

			List<ScheduleMstVO> scheduleList = new ArrayList<ScheduleMstVO>();										//연간스케쥴VO List		
			
			
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
			
			logger.error("java.lang.Exception : ReportController.scheduleMst()");
			logger.error(e.toString());
			ModelAndView  mav = new ModelAndView("contents/report/scheduleMst.tiles");								//연간스케쥴 model
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
	public ResponseEntity<Map> InsertSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//insertSchedule 시작
		
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertSchedule(request);						//연간스케쥴 저장/수정
			
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
	public ResponseEntity<Map> DeleteSchedule(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");						//deleteSchedule 시작
		
		HttpStatus statusCode = HttpStatus.OK;							//status 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteSchedule(request);						//연간스케쥴 삭제
			
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
	public ModelAndView DetailedWorkMst(HttpServletRequest request, Model model
								, @RequestParam(value="workDate", required =false, defaultValue="0000") String workDate
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");						//detailWork 시작
		
		logger.info("param workDate : "+workDate);
		logger.info("param addList : "+addList);
		
		List<DetailedWorkMstVO> detailWorkList = new ArrayList<DetailedWorkMstVO>();								//테이블 리스트
			
		try {
		
			detailWorkList =  reportService.selectDetailedWorkList(request, workDate, addList);						//세부업무 리스트 조회
			
			//--------------------------------------- 
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(workDate)) {
				
				LocalDate now = LocalDate.now();																	//현재 날짜
				workDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));									//년월일 파싱 2020.10.10 
				workDate = workDate.substring(0,4);																	//년도 파싱 2020
				
			};//if
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("detailWorkList", detailWorkList);														//테이블 리스트
			resultMap.put("detailWorkCnt", detailWorkList.size());													//테이블 수 
			resultMap.put("addList", addList);																		//add:행추가 / normal:일반 출력
			resultMap.put("workDate", workDate);																	//기준년도
			
			ModelAndView  mav = new ModelAndView("contents/report/detailedWorkMst.tiles",resultMap);				//detailWork model 선언
			logger.info("================================ E N D ================================");					//scheduleList 종료
			return mav;																								//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("DetailedWorkList");												//detailWork model 선언
			mav.setViewName("contents/report/detailedWorkMst.tiles");			
			logger.error(e.toString());																				//오류메시지
			return mav;																								//mav리턴
			
		}//catch

	}
	
	
	/**
	 * 세부업무 실적 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertDetailedWork", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertDetailedWork(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");	//insertDetailedWork 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertDetailedWork(request);					//세부업무 실적 저장/수정
			
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
	public ResponseEntity<Map> DeleteDetailedWork(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");	//deleteDetailedWork 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteDetailedWork(request);					//세부업무 삭제
			
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
	public ModelAndView RepairList(HttpServletRequest request, Model model
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");		//repairList 시작
		
		logger.info("param addList : "+addList);
		
		List<RepairMstVO> repairList = new ArrayList<RepairMstVO>();		//테이블 리스트
			
		try {
		
			repairList =  reportService.selectRepairList(request, addList);	//하자보수 리스트 조회
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("selectRepairList", repairList);					//테이블 리스트
			resultMap.put("selectRepairList", repairList.size());			//테이블 수 
			resultMap.put("addList", addList);								//add:행추가 / normal:일반 출력
			
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
	 * 주요계약 현황
	 * 주요계약 현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/contractList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView ContractList(HttpServletRequest request, Model model
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");						//contractList 시작
		
		logger.info("param addList : "+addList);
		
		List<ContractMstVO> contractList = new ArrayList<ContractMstVO>();											//테이블 리스트
			
		try {
		
			contractList =  reportService.selectContractList(request, addList);										//주요계약현황 목록
	
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

	}//contractList
	
	
	/**
	 * 주요계약현황 저장/수정 메서드입니다.
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/insertContract", method = {RequestMethod.POST}) 
	public ResponseEntity<Map> InsertContract(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");	//insertContract 시작

		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.insertContract(request);						//주요계약현황 저장/수정
			
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
	public ResponseEntity<Map> DeleteContract(HttpServletRequest request, Model model) {

		logger.info("================================ START ================================");	//deleteContract 시작
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		
		try {
			
			resultMap.put("resultCode", "0000");						//0000:정상  / 9000:오류
			reportService.deleteContract(request);						//주요계약현황 삭제
			
		}catch(Exception e) {
			
			resultMap.put("resultCode", "9000");						//0000:정상  / 9000:오류
			logger.error(e.toString());									//오류메시지
			
		};
		
		logger.info("================================ E N D ================================");	//deleteContract 종료
		return new ResponseEntity<Map>(resultMap, statusCode);
		
	}
	
	
	/**
	 * 교육 현황
	 * 교육 현황 리스트를 출력
	 * @return
	 */
	@RequestMapping(value="/trainingList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView TrainingList(HttpServletRequest request, Model model
								, @RequestParam(value="trainingDate", required =false, defaultValue="0000") String trainingDate
								, @RequestParam(value="addList", required =false,  defaultValue="normal") String addList) {

		logger.info("================================ START ================================");						//trainingList 시작
		
		logger.info("param addList : "+addList);
		
		List<TrainingMstVO> trainingList = new ArrayList<TrainingMstVO>();											//trainingList 리스트
			
		try {
		
			trainingList =  reportService.selectTrainingList(request, trainingDate, addList);						//교육현황 목록
	
			//--------------------------------------- 
			//기준년도의 default는 "0000"
			//"0000"으로 값이 들어오면 현재 일자 기준으로 년도 추출
			//---------------------------------------
			if("0000".equals(trainingDate)) {
				
				LocalDate now = LocalDate.now();																	//현재 날짜
				trainingDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));								//년월일 파싱 2020.10.10 
				trainingDate = trainingDate.substring(0,4);															//년도 파싱 2020
				
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("trainingList", trainingList);															//테이블 리스트
			resultMap.put("trainingCnt", trainingList.size());														//테이블 수 
			resultMap.put("trainingDate", trainingDate);															//교육일자
			resultMap.put("addList", addList);																		//add:행추가 / normal:일반 출력
			
			ModelAndView  mav = new ModelAndView("contents/report/trainingMst.tiles",resultMap);					//trainingList model 선언
			logger.info("================================ E N D ================================");					//trainingList 종료
			return mav;																								//mav리턴
			
		}catch(Exception e) {
			
			ModelAndView  mav = new ModelAndView("TrainingList");													//trainingList model 선언
			mav.setViewName("contents/report/trainingMst.tiles");			
			logger.error(e.toString());																				//오류메시지
			return mav;																								//mav리턴
			
		}//catch

	}//contractList
	
	/**
	*파일 업로드
	*@param dto
	*@param file
	*@return
	*/
	@RequestMapping(value = "/fileUpload", method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<Map> fileSubmit(HttpServletRequest request, FileMstVO fileMstVO
							, @RequestParam("file") MultipartFile file
							, @RequestParam("table_seq") int table_seq) {

		logger.info("================================ START ================================");	
		
		HttpStatus statusCode = HttpStatus.OK;							//통신 상태 값
		Map<String, Object> resultMap = new HashMap<String, Object>();	//리턴해주는 데이터를 담을 map
		resultMap.put("resultCode", "0000");
		reportService.fileInsert(request, fileMstVO, file, table_seq);
		
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
	public void fileDownload( HttpServletResponse response, HttpServletRequest request) {

		logger.info("================================ START ================================");	
		
		
		try {
			
			reportService.fileDownload(response, request);//파일 다운로드
			
		}catch(Exception e) {
			
		}//try - catch
		
	}//fileDownLoad()
	
}
