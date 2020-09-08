package com.onlyBoard.board.dao;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 게시판 DAO 정의
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	public int selectBoardCnt(Map<String, Object> map) {
		return getSqlSession().selectOne("selectBoardCnt",map);
	}

}
  