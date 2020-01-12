package com.tdlNotice.dao;

	import java.util.List;
	import java.util.Map;

	import org.mybatis.spring.support.SqlSessionDaoSupport;
	import org.springframework.stereotype.Repository;

	import com.tdlNotice.domain.noticeCommand;


	@Repository
	public class TDLNoticeDAOImpl  extends SqlSessionDaoSupport implements TDLNoticeDAO {

	//공지사항 리스트 수
	public int getRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
				System.out.println("getRowCount()호출됨!");
				return getSqlSession().selectOne("selectCountN",map);
	}
	//공지사항 게시물리스트 리스트로 받아오기
	public List<noticeCommand> list(Map<String, Object> map) {
				List<noticeCommand> list=getSqlSession().selectList("selectListN",map);
				System.out.println("TDL_NOTICE -> ListDaoImpl 테스트중입니다.~");
				return list;
		}
	
	//공지사항 넘버 최대값 구하기 => 최대값+1 => 새로운 게시물번호
	public int getNewNumN() {
		int newnumN=(Integer)getSqlSession().selectOne("getNewNumN");
		System.out.println("getNewNumN()의 newnumN=>"+newnumN);
		return newnumN;
		}
	
	//글쓰기
	public void insert(noticeCommand TDLNotice) {
			// TODO Auto-generated method stub
			getSqlSession().insert("insertTDLNotice",TDLNotice);
		}
	
	//조회수 증가
	public void updateHitN(Integer TN_num) {
			// TODO Auto-generated method stub
		getSqlSession().update("updateHitN",TN_num);
		}
	
	//글 상세보기
	public noticeCommand selectTDLNotice(Integer TN_num) {
			
		return (noticeCommand)getSqlSession().selectOne("selectTDLNotice",TN_num);
		}
	//글삭제
	public void delete(Integer TN_num) {
			// TODO Auto-generated method stub
		getSqlSession().delete("deleteN",TN_num);
		}

	public void update(noticeCommand TDLNotice) {
			// TODO Auto-generated method stub
		getSqlSession().update("updatePostN",TDLNotice);
		}
}
