package kr.co.swrts.contents.report.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.swrts.contents.report.domains.ContractMstVO;
import kr.co.swrts.contents.report.domains.DetailedWorkMstVO;
import kr.co.swrts.contents.report.domains.FileMstVO;
import kr.co.swrts.contents.report.domains.RepairMstVO;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.domains.TrainingMstVO;

/**
 *<pre>
 *연간데이터 DAO 상속 클레스.
 *</pre>
 *
 *@ClassName : ReportDaoImle.java 
 *@Description : 연간데이터 DAO의 상세 로직입니다. 
 *@author 박찬균 주임연구원
 *@since 2020. 12. 4
 *@version 1.0
 *@see
 *@Modification Information
 */
@Repository("reportDao")
public class ReportDaoImle implements ReportDao {

	/**
	 * sql 세션 연동 선언
	 */
	@Autowired
	SqlSessionDaoSupport sql;
	
	/**
	 * 연간스케쥴 리스트 개수
	 */
	@Override
	public int selectScheduleCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectScheduleCnt", map);
	}
	
	/**
	 * 연간스케쥴 리스트
	 */
	@Override
	public List<ScheduleMstVO> selectScheduleList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectScheduleList", map);
	}
	
	/**
	 * 연간스케쥴 수정
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().update("updateSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 생성
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().insert("insertSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 삭제
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().update("deleteSchdule", scheduleVO);
	}
	
	/**
	 * 세부업무 실적 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectDetailedWorkCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectDetailedWorkCnt", map);
	}
	
	/**
	 * 세부업무 실적 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DetailedWorkMstVO> selectDetailedWorkList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectDetailedWorkList", map);
	}
	
	/**
	 * 세무업무 실적 수정
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception {
		return sql.getSqlSession().update("updateDetailedWork", detailedWorkVO);
	}
	
	/**
	 * 세부업무 실적 생성
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception {
		return sql.getSqlSession().insert("insertDetailedWork", detailedWorkVO);
	}
	
	
	/**
	 * 세부업무 삭제
	 * @param Detailed_WorkVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception {
		return sql.getSqlSession().update("deleteDetailedWork", detailedWorkVO);
	}
	
	/**
	 * 하자보수 리스트 개수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectRepairCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectRepairCnt", map);
	}
	
	/**
	 * 하자보수 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RepairMstVO> selectRepairList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectRepairList", map);
	}
	
	/**
	 * 주요계약현황 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectContractCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectContractCnt", map);
	}
	
	/**
	 * 주요계약현황 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ContractMstVO> selectContractList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectContractList", map);
	}
	
	/**
	 * 주요계약현황 수정
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateContract(ContractMstVO contractVO) throws Exception {
		return sql.getSqlSession().update("updateContract", contractVO);
	}
	
	/**
	 * 주요계약현황 생성
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertContract(ContractMstVO contractVO) throws Exception {
		return sql.getSqlSession().insert("insertContract", contractVO);
	}
	
	/**
	 * 주요계약현황 삭제
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteContract(ContractMstVO contractVO) throws Exception {
		return sql.getSqlSession().update("deleteContract", contractVO);
	}
	
	
	
	//--------------------------------중간부터 개발 중
	
	/**
	 * 교육현황 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectTrainingCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectTrainingCnt", map);
	}
	
	/**
	 * 교육현황 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TrainingMstVO> selectTrainingList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectTrainingList", map);
	}


	/**
	 * 주요계약현황 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectFileSeq(FileMstVO fileMstVO) throws Exception {
		return sql.getSqlSession().selectOne("selectFileSeq", fileMstVO);
	}
	
	/**
	 * 파일 정보 저장
	 * @param fileMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertFile(FileMstVO fileMstVO) throws Exception {
		return sql.getSqlSession().insert("insertFile", fileMstVO);
	}
	
	/**
	 * 파일 단건 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public FileMstVO selectFile(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectFile", map);
	}
	
	/**
	 * 파일 개수 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectFileCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectFileCnt", map);
	}
	
	/**
	 * 파일 리스트 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<FileMstVO> selectFileList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectFileList", map);
	}
}
