package kr.co.swrts.contents.report.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.swrts.contents.report.domains.ContractMstVO;
import kr.co.swrts.contents.report.domains.DetailedWorkMstVO;
import kr.co.swrts.contents.report.domains.FileMstVO;
import kr.co.swrts.contents.report.domains.LiftContentMstVO;
import kr.co.swrts.contents.report.domains.LiftMstVO;
import kr.co.swrts.contents.report.domains.MeetingLogMstVO;
import kr.co.swrts.contents.report.domains.PaymentStatusMstVO;
import kr.co.swrts.contents.report.domains.RepairMstVO;
import kr.co.swrts.contents.report.domains.ScheduleMstVO;
import kr.co.swrts.contents.report.domains.TrainingMstVO;

/**
 *<pre>
 *연간데이터 DAO 상속 클레스.
 *</pre>
 *
 *@ClassName : ReportDaoImple.java 
 *@Description : 연간데이터 DAO의 상세 로직입니다. 
 *@author 박찬균 주임연구원
 *@since 2020. 12. 4
 *@version 1.0
 *@see
 *@Modification Information
 */
@Repository("reportDao")
public class ReportDaoImple implements ReportDao {

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
	 * 연간스케쥴 기본값
	 */
	@Override
	public List<ScheduleMstVO> selectScheduleDefault(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectScheduleDefault", map);
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
	 * 하자보수 생성
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertRepair(RepairMstVO repairVO) throws Exception {
		return sql.getSqlSession().insert("insertRepair", repairVO);
	}
	
	/**
	 * 하자보수 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateRepair(RepairMstVO repairVO) throws Exception {
		return sql.getSqlSession().update("updateRepair", repairVO);
	}
	
	/**
	*하자보수현황 삭제
	*@param repairVO
	*@return
	*@throws Exception
	*/
	@Override
	public int deleteRepair(RepairMstVO repairVO) throws Exception {
		return sql.getSqlSession().update("deleteRepair", repairVO);
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
	
	/**
	 * 설비 및 수불현황 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectPaymentStatusCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectPaymentStatusCnt", map);
	}
	
	/**
	 * 설비 및 수불현황 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PaymentStatusMstVO> selectPaymentStatusList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectPaymentStatusList", map);
	}

	/**
	 * 설비 및 수불현황 수정
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatePaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception {
		return sql.getSqlSession().update("updatePaymentStatus", paymentStatusMstVO);
	}
	
	/**
	 * 설비 및 수불현황 생성
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertPaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception {
		return sql.getSqlSession().insert("insertPaymentStatus", paymentStatusMstVO);
	}
	
	/**
	 * 설비 및 수불현황 삭제
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deletePaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception {
		return sql.getSqlSession().update("deletePaymentStatus", paymentStatusMstVO);
	}
	
	/**
	 * 승강기 목록 개수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectLiftCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectLiftCnt", map);
	}
	
	/**
	 * 승강기 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LiftMstVO> selectLiftList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectLiftList", map);
	}
	
	/**
	 * 승강기 단건 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public LiftMstVO selectLift(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectLift", map);
	}

	/**
	 * 승강기 시퀀스 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectLiftSeq(LiftMstVO liftMstVO) throws Exception {
		return sql.getSqlSession().selectOne("selectLiftSeq", liftMstVO);
	}
	
	/**
	 * 승강기 목록 수정
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateLift(LiftMstVO liftMstVO) throws Exception {
		return sql.getSqlSession().update("updateLift", liftMstVO);
	}
	
	/**
	 * 승강기 목록 생성
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertLift(LiftMstVO liftMstVO) throws Exception {
		return sql.getSqlSession().insert("insertLift", liftMstVO);
	}
	
	/**
	 * 승강기 상세내용 개수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectLiftContentCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectLiftContentCnt", map);
	}
	
	/**
	 * 승강기 상세내용 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LiftContentMstVO> selectLiftContentList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectLiftContentList", map);
	}
	
	/**
	 * 승강기 상세내용 default 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LiftContentMstVO> selectLiftDefaultList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectLiftDefaultList", map);
	}
	
	/**
	 * 승강기 상세내용 수정
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateLiftContent(LiftContentMstVO liftContentMstVO) throws Exception {
		return sql.getSqlSession().update("updateLiftContent", liftContentMstVO);
	}
	
	/**
	 * 승강기 상세내용 생성
	 * @param paymentStatusMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertLiftContent(LiftContentMstVO liftContentMstVO) throws Exception {
		return sql.getSqlSession().insert("insertLiftContent", liftContentMstVO);
	}
	
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
	 * 교육현황 수정
	 * @param trainingMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateTraining(TrainingMstVO trainingMstVO) throws Exception {
		return sql.getSqlSession().update("updateTraining", trainingMstVO);
	}
	
	/**
	 * 교육현황 저장
	 * @param trainingMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertTraining(TrainingMstVO trainingMstVO) throws Exception {
		return sql.getSqlSession().insert("insertTraining", trainingMstVO);
	}
	
	/**
	 * 교육현황 삭제
	 * @param trainingMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteTraining(TrainingMstVO trainingMstVO) throws Exception {
		return sql.getSqlSession().update("deleteTraining", trainingMstVO);
	}
	
	/**
	 * 관리단회의록 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectMeetingLogCnt(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectOne("selectMeetingLogCnt", map);
	}
	
	/**
	 * 관리단회의록 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MeetingLogMstVO> selectMeetingLogList(Map<String, Object> map) throws Exception {
		return sql.getSqlSession().selectList("selectMeetingLogList", map);
	}
	
	/**
	 * 관리단회의록 수정
	 * @param MeetingLogMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception {
		return sql.getSqlSession().update("updateMeetingLog", meetingLogMstVO);
	}
	
	/**
	 * 관리단회의록 저장
	 * @param MeetingLogMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception {
		return sql.getSqlSession().insert("insertMeetingLog", meetingLogMstVO);
	}
	
	/**
	 * 관리단회의록 삭제
	 * @param trainingMstVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception {
		return sql.getSqlSession().update("deleteMeetingLog", meetingLogMstVO);
	}
	
	
	/**
	 * 파일 리스트
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
