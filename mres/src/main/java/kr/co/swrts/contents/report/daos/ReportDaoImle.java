package kr.co.swrts.contents.report.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.swrts.contents.report.domains.ScheduleMstVO;

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
	public int updateSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().update("updateSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 생성
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int insertSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().insert("insertSchedule", scheduleVO);
	}
	
	/**
	 * 연간스케쥴 삭제
	 * @param scheduleVO
	 * @return
	 * @throws Exception
	 */
	public int deleteSchedule(ScheduleMstVO scheduleVO) throws Exception {
		return sql.getSqlSession().update("deleteSchdule", scheduleVO);
	}
	
}
