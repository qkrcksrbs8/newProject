package com.onlyBoard.board.service;

import java.util.Map;

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
	public int selectBoardCnt(Map<String,Object>map);
	
}
