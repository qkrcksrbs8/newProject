package com.onlyReport.report.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.onlyReport.report.model.Annuail_ScheduleVO;
import com.onlyReport.report.model.ContractVO;
import com.onlyReport.report.model.Detailed_WorkVO;
import com.onlyReport.report.model.ReportVO;

/**
 * �Խ��� DAO ����
 *
 */
@Repository
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
	public int selectScheduleCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectScheduleCnt", map);
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
	
	/**
	 * ���ξ��� ���� ����Ʈ ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectDetailedWorkCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectDetailedWorkCnt", map);
	}
	
	/**
	 * ���ξ��� ���� ����Ʈ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectDetailedWorkList", map);
	}
	
	/**
	 * �������� ���� ����
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	public int updateDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().update("updateDetailedWork", detailedWorkVO);
	}
	
	/**
	 * ���ξ��� ���� ����
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	public int insertDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().insert("insertDetailedWork", detailedWorkVO);
	}
	
	/**
	 * ���ξ��� ����
	 * @param Detailed_WorkVO
	 * @return
	 * @throws Exception
	 */
	public int deleteDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().update("deleteDetailedWork", detailedWorkVO);
	}
	
	/**
	 * �ֿ�����Ȳ ����Ʈ ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectContractCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectContractCnt", map);
	}
	
	/**
	 * �ֿ�����Ȳ ����Ʈ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ContractVO> selectContractList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectContractList", map);
	}
	
	/**
	 * �ֿ�����Ȳ ����
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int updateContract(ContractVO contractVO) throws Exception {
		return getSqlSession().update("updateContract", contractVO);
	}
	
	/**
	 * �ֿ�����Ȳ ����
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int insertContract(ContractVO contractVO) throws Exception {
		return getSqlSession().insert("insertContract", contractVO);
	}
	
	/**
	 * �ֿ�����Ȳ ����
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int deleteContract(ContractVO contractVO) throws Exception {
		return getSqlSession().update("deleteContract", contractVO);
	}
}
  