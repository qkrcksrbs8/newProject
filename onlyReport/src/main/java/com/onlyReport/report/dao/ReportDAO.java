package com.onlyReport.report.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyReport.report.model.ReportVO;

/**
 * 게시판 DAO 정의
 *
 */
public class ReportDAO extends SqlSessionDaoSupport {
	
	/**
	 * 게시글 리스트 수
	 */
	public int selectReportCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectReportCnt", map);
	}
	
	/**
	 * 게시글 리스트
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectReportList", map);
	}
	
	/**
	 * 게시글 상세
	 */
	public ReportVO selectReport(int report_seq) throws Exception { 
		return (ReportVO) getSqlSession().selectOne("selectReport", report_seq);
	} 

	/**
	 * 게시글 업데이트
	 */
	public void updateReport(Map<String, Object> map) throws Exception {
		getSqlSession().update("updateReport", map);
	}
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public void deleteReport(Map<String, Object> map) throws Exception {
		getSqlSession().update("deleteReport", map);
	}
	
	/**
	 * 게시글 생성
	 */
	public void insertReport(Map<String, Object> map) throws Exception {
		getSqlSession().insert("insertReport", map);
	}
}
  