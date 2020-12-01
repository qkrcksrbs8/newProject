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
import com.onlyReport.report.model.Detailed_WorkVO;
import com.onlyReport.report.model.ReportVO;
import com.onlyReport.report.service.ReportService;

/**
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class ReportServiceImpl implements ReportService {

	Logger logger = Logger.getLogger(this.getClass());	//�α�
	
	@Autowired
	private ReportDAO reportDAO;						//�Խ��� DAO
	
	/* 
	 * �Խñ� ����Ʈ �� ��ȸ	
	 */
	public int selectReportCnt(Map<String, Object> map) {
		
		int ReportCnt = 0;								//�Խ��� ����Ʈ ��
		
		try {
			
			ReportCnt = reportDAO.selectReportCnt(map);	//�Խ��� ����Ʈ �� ��ȸ
		
		}catch(Exception e) {
			
			logger.error("selectReportCnt()");			//�Խ��� ����Ʈ ��
			logger.error(e.toString());					//���� ����
			
		}//try
		
		return ReportCnt;
	}
	
	/**
	 * �Խñ� ����Ʈ
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map) {	
		
		List<ReportVO> ReportList = new ArrayList<ReportVO>();	//�Խ���VO List
		
		try {
			
			ReportList = reportDAO.selectReportList(map);		//�Խ��� ����Ʈ ��ȸ
			
		}catch(Exception e) {
			
			logger.error("selectReportList()");					//�Խ��� ����Ʈ �޼���
			logger.error(e.toString());							//���� ����
			
		}//try
		
		return ReportList;
		
	}
	
	/**
	 * �Խñ� ��
	 */
	public ReportVO selectReport(int Report_seq) {
		
		ReportVO ReportVO = new ReportVO();					//�Խ���VO
		
		try {
			
			ReportVO = reportDAO.selectReport(Report_seq);	//�Խ��� ����ȸ
			
		}catch(Exception e) {
			
			logger.error("selectReport()");					
			logger.error(e.toString());						//���� ����
			
		}//try
		
		return ReportVO;
	
	}
	
	/**
	 * �Խñ� ������Ʈ
	 */
	public String updateReport(Map<String, Object> map) {

		String resultCode = "0000";				// 0000:���� / 9000:����
		
		try {

			reportDAO.updateReport(map);		//�Խñ� ������Ʈ
			resultCode = "0000";				// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());			//���� ����
			resultCode = "9000";				// 0000:���� / 9000:����
			
		}
		
		return resultCode;
	}
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public String deleteReport(Map<String, Object> map) {
		
		String resultCode = "0000";		// 0000:���� / 9000:����
		
		try {

			reportDAO.deleteReport(map);//�Խñ� ������Ʈ
			resultCode = "0000";		// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());	// ���� ����
			resultCode = "9000";		// 0000:���� / 9000:����
			
		}//try
		
		return resultCode;
	}
	
	/**
	 * �Խñ� ����
	 */
	public String insertReport(Map<String, Object> map) {
		
		String resultCode = "0000";			// 0000:���� / 9000:����
		
		try {
			
			reportDAO.insertReport(map);	//�Խñ� ����
			resultCode = "0000";			// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());		//���� ����
			resultCode = "9000";			// 0000:���� / 9000:����
			
		}//try
		
		return resultCode;
	}
	
	
	
	/**
	 *	���������� ��ȸ �޼����Դϴ�.
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(HttpServletRequest request, String division, String addList) {
		
		logger.info("================================ START ================================");
		List<Annuail_ScheduleVO> scheduleList = new ArrayList<Annuail_ScheduleVO>();	//����������VO List
		
		try {
			logger.info("division : "+division);
			Map<String, Object> map = new HashMap<String, Object>();					//������ ���� �Ű����� map
			map.put("useflag", "1");													//1:��� / 0:�̻��
			map.put("division", division);												//��������
			
			//---------------
			//���������� ����Ʈ ��
			//---------------
			if(reportDAO.selectScheduleCnt(map) < 1 ) {
				return scheduleList;													// ����Ʈ�� �� ���� ������ �ٷ� ����
			};//if
			
			scheduleList = reportDAO.selectScheduleList(map);							//���������� ����Ʈ ��ȸ
			
			//-----------------------------
			//addList�� ���� add�� ��� ����Ʈ �߰�
			//-----------------------------
			if("add".equals(addList)) {
			
				Annuail_ScheduleVO addScheduleVO = new Annuail_ScheduleVO();			//����������vo ����
				addScheduleVO.setDivision(division);									//���� ���� �� ����
				scheduleList.add(addScheduleVO);										//����Ʈ�� �߰�

			};//if
			
			logger.info(scheduleList.toString());
			logger.info("================================ E N D ================================");	//����Ʈ �α� 
			return scheduleList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectReportList() : ");					//�Խ��� ����Ʈ �޼���
			logger.error(e.toString());													//���� ����
			return scheduleList;
			
		}//try
		
	}
	
	
	/**
	 * ���������� ���� �޼����Դϴ�.
	 */
	public void insertSchedule(HttpServletRequest request) {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//�Ű����� string���� �ޱ�
			logger.info(str); 																		//�Ű����� �α� ����
			JSONArray jsonArray = new JSONArray(str);												//json�迭 ����
			Gson gson = new Gson();																	//gson ����
			Type listType = new TypeToken<ArrayList<Annuail_ScheduleVO>>(){}.getType();				//����������VO�� List.class 
			List<Annuail_ScheduleVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO�� �Ľ�
			Annuail_ScheduleVO scheduleVO = new Annuail_ScheduleVO();								//���������� VO
			
			//-----------------------------------
			//�Ľ̵� VOList ���
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//�ݺ������� ��ü ��������
				if(reportDAO.updateSchedule(scheduleVO) == 0) {reportDAO.insertSchedule(scheduleVO);};//������Ʈ �� ������ ������ ����
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertSchedule() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	
	/**
	 * ���������� ���� �޼����Դϴ�.
	 */
	public void deleteSchedule(HttpServletRequest request) {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//�Ű����� string���� �ޱ�
			logger.info(str); 																		//�Ű����� �α� ����
			JSONArray jsonArray = new JSONArray(str);												//json�迭 ����
			Gson gson = new Gson();																	//gson ����
			Type listType = new TypeToken<ArrayList<Annuail_ScheduleVO>>(){}.getType();				//����������VO�� List.class 
			List<Annuail_ScheduleVO> scheduleList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO�� �Ľ�
			Annuail_ScheduleVO scheduleVO = new Annuail_ScheduleVO();								//���������� VO
			
			//-----------------------------------
			//�Ľ̵� VOList ���
			//-----------------------------------
			for(int i = 0; i < scheduleList.size(); ++i) {
				
				scheduleVO = scheduleList.get(i);													//�ݺ������� ��ü ��������
				reportDAO.deleteSchedule(scheduleVO);												//���������� ���� DAO
				
			};//for
		
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.deleteSchedule() : ");
			logger.error(e.toString());
			
		}
		
	}
	
	/**
	 * �������� ���� ����Ʈ ��ȸ
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(HttpServletRequest request, String workDate, String addList) {
		
		logger.info("================================ START ================================");
		List<Detailed_WorkVO> detailedWorkList = new ArrayList<Detailed_WorkVO>();					//����������VO List
		
		try {
			
			//---------------------------------------
			//���س⵵�� default�� "0000"
			//"0000"���� ���� ������ ���� ���� �������� �⵵ ����
			//---------------------------------------
			if("0000".equals(workDate)) {
				
				LocalDate now = LocalDate.now();													//���� ��¥
				workDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));					//����� �Ľ� 2020.10.10 
				workDate = workDate.substring(0,4);													//�⵵ �Ľ� 2020
				
			};//if
			
			logger.info("work_date : "+workDate);													//���س⵵ �α�
			Map<String, Object> map = new HashMap<String, Object>();								//������ ���� �Ű����� map
			map.put("useflag", "1");																//1:��� / 0:�̻��
			map.put("work_date", workDate);															//���س⵵
			
			//-----------------
			//�������� ���� ����Ʈ �� 
			//-----------------
			if(reportDAO.selectDetailedWorkCnt(map) < 1 ) {
				return detailedWorkList;															// ����Ʈ�� �� ���� ������ �ٷ� ����
			};//if
			
			detailedWorkList = reportDAO.selectDetailedWorkList(map);								//���������� ����Ʈ ��ȸ
			
			//-----------------------------
			//addList�� ���� add�� ��� ����Ʈ �߰�
			//-----------------------------
			if("add".equals(addList)) {
			
				Detailed_WorkVO detailed_WorkVO = new Detailed_WorkVO();							//vo ����
				detailed_WorkVO.setWorkd_date(workDate);											//���� ���� �� ����
				detailedWorkList.add(detailed_WorkVO);												//����Ʈ�� �߰�

			};//if
			
			logger.info(detailedWorkList.toString());
			logger.info("================================ E N D ================================");	//����Ʈ �α� 
			return detailedWorkList;
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.selectDetailedWorkList() : ");							//�������� ���� ����Ʈ �޼���
			logger.error(e.toString());																//���� ����
			return detailedWorkList;
			
		}//try
		
	}
	
	
	public void insertDetailedWork(HttpServletRequest request) throws Exception {
		
		logger.info("================================ START ================================");
		
		try {
			
			String str = request.getParameter("totalJson");											//�Ű����� string���� �ޱ�
			logger.info(str); 																		//�Ű����� �α� ����
			JSONArray jsonArray = new JSONArray(str);												//json�迭 ����
			Gson gson = new Gson();																	//gson ����
			Type listType = new TypeToken<ArrayList<Detailed_WorkVO>>(){}.getType();				//���ξ��� ����VO�� List.class 
			List<Detailed_WorkVO> detailedWorkList = gson.fromJson(jsonArray.toString(), listType);	//jsonArray -> VO�� �Ľ�
			Detailed_WorkVO detailed_WorkVO = new Detailed_WorkVO();								//���ξ��� ���� VO
			
			//-----------------------------------
			//�Ľ̵� VOList ���
			//-----------------------------------
			for(int i = 0; i < detailedWorkList.size(); ++i) {
				
				detailed_WorkVO = detailedWorkList.get(i);																	//�ݺ������� ��ü ��������
				if(reportDAO.updateDetailedWork(detailed_WorkVO) == 0) {reportDAO.insertDetailedWork(detailed_WorkVO);};	//������Ʈ �� ������ ������ ����
				
			};//for
			
			logger.info("================================ E N D ================================");
			
		}catch(Exception e) {
			
			logger.error("ReportServiceImpl.insertDetailedWork() : ");
			logger.error(e.toString());
			
		}
		
	}
	
}
