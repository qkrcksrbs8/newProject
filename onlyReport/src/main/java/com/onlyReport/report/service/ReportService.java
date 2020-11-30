package com.onlyReport.report.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.Detailed_WorkVO;
import com.onlyReport.report.model.ReportVO;

/**
 *<pre>
 *�������� �������̽�
 *</pre>
 *
 *@ClassName : ReportService.java 
 *@Description : �������� ��ȸ, ����, ���� ���� ����� ������ �������̽��Դϴ�.
 *@author user
 *@since 2020. 11. 26
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 26   user   ���ʻ���
 *</pre>
 */
public interface ReportService {

	/**
	 * �Խñ� ����Ʈ ��
	 * @param map
	 * @return
	 */
	public int selectReportCnt(Map<String, Object> map);
	
	/**
	 * �Խñ� ����Ʈ
	 * @param map
	 * @return
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map);
	
	/**
	 * �Խñ� ��
	 * @param Report_seq
	 * @return
	 */
	public ReportVO selectReport(int Report_seq);
	
	/**
	 * �Խñ� ������Ʈ
	 * @param map
	 * @return
	 */
	public String updateReport(Map<String, Object> map);
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 * @param map
	 * @return
	 */
	public String deleteReport(Map<String, Object> map);
	
	
	/**
	 * �Խñ� �ۼ�
	 * @param map
	 * @return
	 */
	public String insertReport(Map<String, Object> map);
	
	/**
	 * ���������� ����Ʈ ��ȸ
	 * @param map
	 * @return
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(HttpServletRequest request, String division, String addList);
	
	/**
	 * ���������� ����/����
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public void insertSchedule(HttpServletRequest request) throws Exception;
	
	/**
	 * ���������� ����
	 * @param map
	 * @return
	 */
	public void deleteSchedule(HttpServletRequest request);
	
	/**
	 * ���������� ����Ʈ ��ȸ
	 * @param map
	 * @return
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(HttpServletRequest request, String workDate);
}
