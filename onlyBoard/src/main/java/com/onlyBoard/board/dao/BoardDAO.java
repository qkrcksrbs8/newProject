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
	 * �Խ��� ����Ʈ ��
	 */
	public int selectBoardCnt() throws Exception {
		return getSqlSession().selectOne("selectBoardCnt");
	}
	
	/**
	 * �Խ��� ����Ʈ
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectBoardList", map);
	}
	
	/**
	 * �Խ��� ��
	 */
	public BoardVO selectBoard(int board_seq) throws Exception { 
		return (BoardVO) getSqlSession().selectOne("selectBoard", board_seq);
	} 

}
  