package com.onlyBoard.board.dao;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 게시판 DAO 정의
 *
 */
public class BoardDAO {
	
	private SqlSessionDaoSupport sql;//마이바티스 쿼리 매핑
	
	public int selectBoardCnt(Map<String, Object> map) {
		return sql.getSqlSession().selectOne("selectBoardCnt",map);
	}

}
 