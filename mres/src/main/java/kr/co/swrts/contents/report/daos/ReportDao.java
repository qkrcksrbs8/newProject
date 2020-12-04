package kr.co.swrts.contents.report.daos;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.swrts.contents.report.domains.ScheduleMstVO;


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
	
}
