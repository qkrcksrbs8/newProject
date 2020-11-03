package com.onlyReport.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyReport.report.dao.ReportDAO;
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
}
