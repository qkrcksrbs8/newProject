package kr.co.swrts.contents.report.services;

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
import java.lang.reflect.Type;									//파싱된 VO타입을 담을 변수

import kr.co.swrts.contents.report.daos.ReportDao;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;


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
			
			int selectScheduleCnt = 0;	//cnt
			selectScheduleCnt = reportDao.selectScheduleCnt(map);
			logger.info("selectScheduleCnt : "+selectScheduleCnt);
			
			//---------------
			//연간스케쥴 리스트 수 
			//---------------
			if(reportDao.selectScheduleCnt(map) > 0 ) {

				scheduleList = reportDao.selectScheduleList(map);							//연간스케쥴 리스트 조회
				
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
	
	
	
}
