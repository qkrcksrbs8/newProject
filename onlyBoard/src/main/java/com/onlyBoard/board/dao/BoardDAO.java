package com.onlyBoard.board.dao;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * �Խ��� DAO ����
 *
 */
public class BoardDAO {
	
	private SqlSessionDaoSupport sql;//���̹�Ƽ�� ���� ����
	
	public int selectBoardCnt(Map<String, Object> map) {
		return sql.getSqlSession().selectOne("selectBoardCnt",map);
	}

}
 