package com.onlyReport.report.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ReportVO;

/**
 * �Խ��� DAO ����
 *
 */
public class ReportDAO extends SqlSessionDaoSupport {
	
	/**
	 * �Խñ� ����Ʈ ��
	 */
	public int selectReportCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectReportCnt", map);
	}
	
	/**
	 * �Խñ� ����Ʈ
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectReportList", map);
	}
	
	/**
	 * �Խñ� ��
	 */
	public ReportVO selectReport(int report_seq) throws Exception { 
		return (ReportVO) getSqlSession().selectOne("selectReport", report_seq);
	} 

	/**
	 * �Խñ� ������Ʈ
	 */
	public void updateReport(Map<String, Object> map) throws Exception {
		getSqlSession().update("updateReport", map);
	}
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public void deleteReport(Map<String, Object> map) throws Exception {
		getSqlSession().update("deleteReport", map);
	}
	
	/**
	 * �Խñ� ����
	 */
	public void insertReport(Map<String, Object> map) throws Exception {
		getSqlSession().insert("insertReport", map);
	}
	
	/**
	 * ���������� ����Ʈ ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectScheduleList", map);
	}
	
	/**
	 * ���������� ����
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int updateSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().update("updateSchedule", scheduleVO);
	}
	
	/**
	 * ���������� ����
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int insertSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().insert("insertSchedule", scheduleVO);
	}
	
	/**
	 * ���������� ����
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int deleteSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().update("deleteSchdule", scheduleVO);
	}
	
}
  