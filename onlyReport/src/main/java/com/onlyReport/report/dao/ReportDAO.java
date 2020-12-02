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
 * 게시판 DAO 정의
 *
 */
@Repository
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
	
	/**
	 * 연간스케쥴 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectScheduleCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectScheduleCnt", map);
	}
	
	/**
	 * 연간스케쥴 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Annuail_ScheduleVO> selectScheduleList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectScheduleList", map);
	}
	
	/**
	 * 연간스케쥴 수정
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int updateSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().update("updateSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 생성
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int insertSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().insert("insertSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 삭제
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int deleteSchedule(Annuail_ScheduleVO scheduleVO) throws Exception {
		return getSqlSession().update("deleteSchdule", scheduleVO);
	}
	
	/**
	 * 세부업무 실적 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectDetailedWorkCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectDetailedWorkCnt", map);
	}
	
	/**
	 * 세부업무 실적 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Detailed_WorkVO> selectDetailedWorkList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectDetailedWorkList", map);
	}
	
	/**
	 * 세무업무 실적 수정
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	public int updateDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().update("updateDetailedWork", detailedWorkVO);
	}
	
	/**
	 * 세부업무 실적 생성
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	public int insertDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().insert("insertDetailedWork", detailedWorkVO);
	}
	
	/**
	 * 세부업무 삭제
	 * @param Detailed_WorkVO
	 * @return
	 * @throws Exception
	 */
	public int deleteDetailedWork(Detailed_WorkVO detailedWorkVO) throws Exception {
		return getSqlSession().update("deleteDetailedWork", detailedWorkVO);
	}
	
	/**
	 * 주요계약현황 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectContractCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectContractCnt", map);
	}
	
	/**
	 * 주요계약현황 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ContractVO> selectContractList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectContractList", map);
	}
	
	/**
	 * 주요계약현황 수정
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int updateContract(ContractVO contractVO) throws Exception {
		return getSqlSession().update("updateContract", contractVO);
	}
	
	/**
	 * 주요계약현황 생성
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int insertContract(ContractVO contractVO) throws Exception {
		return getSqlSession().insert("insertContract", contractVO);
	}
	
	/**
	 * 주요계약현황 삭제
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int deleteContract(ContractVO contractVO) throws Exception {
		return getSqlSession().update("deleteContract", contractVO);
	}
}
  