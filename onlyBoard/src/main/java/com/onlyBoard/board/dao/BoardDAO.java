package com.onlyBoard.board.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyBoard.board.model.BoardVO;

/**
 * �Խ��� DAO ����
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	/**
	 * �Խ��� ����Ʈ ��
	 */
	public int selectBoardCnt() throws Exception {
		return getSqlSession().selectOne("selectBoardCnt");
	}
	
	/**
	 * �Խ��� ����Ʈ
	 */
	public List<BoardVO> selectBoardList() throws Exception {
		return getSqlSession().selectList("selectBoardList");
	}
	
	/**
	 * �Խ��� ��
	 */
	public BoardVO selectBoard(int board_seq) throws Exception { 
		return (BoardVO) getSqlSession().selectOne("selectBoard", board_seq);
	} 

}
  