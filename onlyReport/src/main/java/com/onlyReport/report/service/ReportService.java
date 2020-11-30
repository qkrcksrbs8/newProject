package com.onlyReport.report.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.Detailed_WorkVO;
import com.onlyReport.report.model.ReportVO;

/**
 *<pre>
 *관리보고서 인터페이스
 *</pre>
 *
 *@ClassName : ReportService.java 
 *@Description : 관리보고서 조회, 저장, 삭제 등의 기능을 정의한 인터페이스입니다.
 *@author user
 *@since 2020. 11. 26
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 26   user   최초생성
 *</pre>
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
	 * @param map
	 * @return
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map);
	
	/**
	 * 게시글 상세
	 * @param Report_seq
	 * @return
	 */
	public ReportVO selectReport(int Report_seq);
	
	/**
	 * 게시글 업데이트
	 * @param map
	 * @return
	 */
	public String updateReport(Map<String, Object> map);
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 * @param map
	 * @return
	 */
	public String deleteReport(Map<String, Object> map);
	
	
	/**
	 * 게시글 작성
	 * @param map
	 * @return
	 */
	public String insertReport(Map<String, Object> map);
	
	/**
	 * 연간스케쥴 리스트 조회
	 * @param map
	 * @return
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(HttpServletRequest request, String division, String addList);
	
	/**
	 * 연간스케쥴 저장/수정
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public void insertSchedule(HttpServletRequest request) throws Exception;
	
	/**
	 * 연간스케쥴 삭제
	 * @param map
	 * @return
	 */
	public void deleteSchedule(HttpServletRequest request);
	
	/**
	 * 연간스케쥴 리스트 조회
	 * @param map
	 * @return
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(HttpServletRequest request, String workDate);
}
