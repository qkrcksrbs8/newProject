package com.onlyReport.report.service;

import java.util.List;
import java.util.Map;

import com.onlyReport.report.model.ReportVO;

/**
 * 게시판 인터페이스(조회, 생성, 수정, 삭제)
 *
 */
public interface ReportService {

	/**
	 * 게시글 리스트 수
	 * @param map
	 * @return
	 */
	public int selectReportCnt(Map<String, Object> map);
	
	/**
	 * 게시글 리스트
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map);
	
	/**
	 * 게시글 상세
	 */
	public ReportVO selectReport(int Report_seq);
	
	/**
	 * 게시글 업데이트
	 */
	public String updateReport(Map<String, Object> map);
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public String deleteReport(Map<String, Object> map);
	
	/**
	 * 게시글 작성
	 */
	public String insertReport(Map<String, Object> map);
}
