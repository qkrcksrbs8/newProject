package com.onlyBoard.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyBoard.board.model.BoardVO;

/**
 * �Խ��� DAO ����
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	/**
	 * �Խñ� ����Ʈ ��
	 */
	public int selectBoardCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectBoardCnt", map);
	}
	
	/**
	 * �Խñ� ����Ʈ
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectBoardList", map);
	}
	
	/**
	 * �Խñ� ��
	 */
	public BoardVO selectBoard(int board_seq) throws Exception { 
		return (BoardVO) getSqlSession().selectOne("selectBoard", board_seq);
	} 

	/**
	 * �Խñ� ������Ʈ
	 */
	public void updateBoard(Map<String, Object> map) throws Exception {
		getSqlSession().update("updateBoard", map);
	}
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public void deleteBoard(Map<String, Object> map) throws Exception {
		getSqlSession().update("deleteBoard", map);
	}
	
	/**
	 * �Խñ� ����
	 */
	public void insertBoard(Map<String, Object> map) throws Exception {
		getSqlSession().insert("insertBoard", map);
	}
}
  