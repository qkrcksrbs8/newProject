package com.onlyBoard.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyBoard.board.model.BoardVO;

/**
 * 게시판 DAO 정의
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	/**
	 * 게시판 리스트 수
	 */
	public int selectBoardCnt() throws Exception {
		return getSqlSession().selectOne("selectBoardCnt");
	}
	
	/**
	 * 게시판 리스트
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectBoardList", map);
	}
	
	/**
	 * 게시판 상세
	 */
	public BoardVO selectBoard(int board_seq) throws Exception { 
		return (BoardVO) getSqlSession().selectOne("selectBoard", board_seq);
	} 

}
  