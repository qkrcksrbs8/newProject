package com.onlyReport.report.service;

import java.util.List;
import java.util.Map;

import com.onlyReport.report.model.ReportVO;

/**
 * �Խ��� �������̽�(��ȸ, ����, ����, ����)
 *
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
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map);
	
	/**
	 * �Խñ� ��
	 */
	public ReportVO selectReport(int Report_seq);
	
	/**
	 * �Խñ� ������Ʈ
	 */
	public String updateReport(Map<String, Object> map);
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public String deleteReport(Map<String, Object> map);
	
	/**
	 * �Խñ� �ۼ�
	 */
	public String insertReport(Map<String, Object> map);
}
