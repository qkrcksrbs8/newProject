package com.onlyBoard.board.dao;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * �Խ��� DAO ����
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	public int selectBoardCnt(Map<String, Object> map) {
		return getSqlSession().selectOne("selectBoardCnt",map);
	}

}
  