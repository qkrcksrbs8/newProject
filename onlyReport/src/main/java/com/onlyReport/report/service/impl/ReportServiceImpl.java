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
}
