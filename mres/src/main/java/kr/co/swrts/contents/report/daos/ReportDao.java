package kr.co.swrts.contents.report.daos;


import java.util.List;
import java.util.Map;

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


@Repository
public interface ReportDao {

	/**
	 * 연간스케쥴 리스트 개수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectScheduleCnt(Map<String, Object> map) throws Exception;
	
	/**
	* 연간스케쥴 기본값
	*@param map
	*@return
	*@throws Exception
	*/
	public List<ScheduleMstVO> selectScheduleDefault(Map<String, Object> map) throws Exception;
	
	/**
	 * 연간스케쥴 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ScheduleMstVO> selectScheduleList(Map<String, Object> map) throws Exception;
	
	/**
	 * 연간스케쥴 수정
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int updateSchedule(ScheduleMstVO scheduleVO) throws Exception;
	
	/**
	 * 연간스케쥴 생성
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int insertSchedule(ScheduleMstVO scheduleVO) throws Exception;
	
	/**
	 * 연간스케쥴 삭제
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int deleteSchedule(ScheduleMstVO scheduleVO) throws Exception;

	/**
	*세부업무실적 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectDetailedWorkCnt(Map<String, Object> map) throws Exception; 
	
	/**
	*세부업무실적 리스트
	*@param map
	*@return
	*@throws Exception
	*/
	public List<DetailedWorkMstVO> selectDetailedWorkList(Map<String, Object> map) throws Exception;
	
	/**
	 *세무업무 실적 수정
	 * @param detailedWorkVO
	 * @return
	 * @throws Exception
	 */
	public int updateDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception;
	
	/**
	*세부업무실적 생성
	*@param detailedWorkVO
	*@return
	*@throws Exception
	*/
	public int insertDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception;
	
	/**
	*세부업무실적 삭제
	*@param detailedWorkVO
	*@return
	*@throws Exception
	*/
	public int deleteDetailedWork(DetailedWorkMstVO detailedWorkVO) throws Exception;

	/**
	*하자보수 리스트 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectRepairCnt(Map<String, Object> map) throws Exception; 
	
	/**
	*하자보수 리스트 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<RepairMstVO> selectRepairList(Map<String, Object> map) throws Exception;
	
	/**
	 *하자보수 수정
	 * @param RepairVO
	 * @return
	 * @throws Exception
	 */
	public int updateRepair(RepairMstVO repairVO) throws Exception;
	
	/**
	*하자보수 생성
	*@param RepairVO
	*@return
	*@throws Exception
	*/
	public int insertRepair(RepairMstVO repairVO) throws Exception;
	
	/**
	*하자보수현황 삭제
	*@param repairVO
	*@return
	*@throws Exception
	*/
	public int deleteRepair(RepairMstVO repairVO) throws Exception;
	
	/**
	*주요계약현황 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectContractCnt(Map<String, Object> map) throws Exception;
	
	/**
	*주요계약현황 리스트 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<ContractMstVO> selectContractList(Map<String, Object> map) throws Exception;
	
	/**
	*주요계역현황 수정
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int updateContract(ContractMstVO contractVO) throws Exception;
	
	/**
	*주요계약현황 생성
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int insertContract(ContractMstVO contractVO) throws Exception;
	
	/**
	*주요계약현황 삭제
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int deleteContract(ContractMstVO contractVO) throws Exception;
	
	/**
	*설비 및 수불 현황 리스트 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectPaymentStatusCnt(Map<String, Object> map) throws Exception;
	
	/**
	*설비 및 수불 현황 리스트 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<PaymentStatusMstVO> selectPaymentStatusList(Map<String, Object> map) throws Exception;
	
	/**
	*설비 및 수불 현황 수정
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int updatePaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception;
	
	/**
	*설비 및 수불 현황 생성
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int insertPaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception;
	
	/**
	*설비 및 수불 현황  삭제
	*@param paymentStatusMstVO
	*@return
	*@throws Exception
	*/
	public int deletePaymentStatus(PaymentStatusMstVO paymentStatusMstVO) throws Exception;

	/**
	*승강기 목록 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectLiftCnt(Map<String, Object> map) throws Exception; 
	
	/**
	*승강기 목록 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<LiftMstVO> selectLiftList(Map<String, Object> map) throws Exception;
	
	/**
	*승강기 목록 단건 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public LiftMstVO selectLift(Map<String, Object> map) throws Exception;
	
	/**
	*승강기 시퀀스 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectLiftSeq(LiftMstVO liftMstVO) throws Exception;
	
	/**
	*승강기 목록 수정
	*@param liftMstVO
	*@return
	*@throws Exception
	*/
	public int updateLift(LiftMstVO liftMstVO) throws Exception;
	
	/**
	*승강기 목록 생성
	*@param liftMstVO
	*@return
	*@throws Exception
	*/
	public int insertLift(LiftMstVO liftMstVO) throws Exception;
	
	/**
	*승강기 상세내용 개수
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectLiftContentCnt(Map<String, Object> map) throws Exception;
	
	/**
	*승강기 상세내용 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<LiftContentMstVO> selectLiftContentList(Map<String, Object> map) throws Exception;
	
	/**
	*승상기 상세내용 default 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<LiftContentMstVO> selectLiftDefaultList(Map<String, Object> map) throws Exception;
	
	/**
	*승강기 상세내용 수정
	*@param liftContentMstVO
	*@return
	*@throws Exception
	*/
	public int updateLiftContent(LiftContentMstVO liftContentMstVO) throws Exception;
	
	/**
	*승강기 상세내용 생성(제거 예정)
	*@param liftContentMstVO
	*@return
	*@throws Exception
	*/
	public int insertLiftContent(LiftContentMstVO liftContentMstVO) throws Exception;
	
	/**
	 *교육현황 리스트 수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectTrainingCnt(Map<String, Object> map) throws Exception;
	
	/**
	*교육현황 리스트 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<TrainingMstVO> selectTrainingList(Map<String, Object> map) throws Exception;
	
	/**
	*교육현황 수정
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int updateTraining(TrainingMstVO trainingMstVO) throws Exception;
	
	/**
	*교육현황 생성
	*@param contractVO
	*@return
	*@throws Exception
	*/
	public int insertTraining(TrainingMstVO trainingMstVO) throws Exception;
	
	/**
	*교육현황 삭제
	*@param trainingMstVO
	*@return
	*@throws Exception
	*/
	public int deleteTraining(TrainingMstVO trainingMstVO) throws Exception;
	
	/**
	*관리단회의록 리스트 개수
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectMeetingLogCnt(Map<String, Object> map) throws Exception;
	
	/**
	*관리단회의록 리스트
	*@param map
	*@return
	*@throws Exception
	*/
	public List<MeetingLogMstVO> selectMeetingLogList(Map<String, Object> map) throws Exception;

	/**
	*관리단회의록 수정
	*@param meetingLogMstVO
	*@return
	*@throws Exception
	*/
	public int updateMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception;
	
	/**
	*관리단회의록 저장
	*@param meetingLogMstVO
	*@return
	*@throws Exception
	*/
	public int insertMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception;
	
	/**
	*관리단회의록 삭제
	*@param meetingLogMstVO
	*@return
	*@throws Exception
	*/
	public int deleteMeetingLog(MeetingLogMstVO meetingLogMstVO) throws Exception;
	
	/**
	 * 파일 정보 저장
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int insertFile(FileMstVO fileMstVO) throws Exception;
	
	/**
	 * 파일 시퀀스 조회
	 * @param contractVO
	 * @return
	 * @throws Exception
	 */
	public int selectFileSeq(FileMstVO fileMstVO) throws Exception;
	
	/**
	*파일 단건 조회
	*@param fileMstVO
	*@return
	*@throws Exception
	*/
	public FileMstVO selectFile(Map<String, Object> map) throws Exception;
	
	/**
	*파일 개수 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public int selectFileCnt(Map<String, Object> map) throws Exception;
	
	/**
	*주요계약현황 리스트 조회
	*@param map
	*@return
	*@throws Exception
	*/
	public List<FileMstVO> selectFileList(Map<String, Object> map) throws Exception;
}
