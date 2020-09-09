package com.onlyBoard.board.service;

import java.util.List;

import com.onlyBoard.board.model.BoardVO;

/**
 * 게시판 인터페이스(조회, 생성, 수정, 삭제)
 *
 */
public interface BoardService {

	/**
	 * 게시판 리스트 수
	 * @param map
	 * @return
	 */
	public int selectBoardCnt();
	
	/**
	 * 게시판 리스트
	 */
	public List<BoardVO> selectBoardList();
	
	/**
	 * 게시판 상세
	 */
	public BoardVO selectBoard(int board_seq);
	
}
