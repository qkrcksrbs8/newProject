package com.onlyBoard.board.service;

import java.util.List;

import com.onlyBoard.board.model.BoardVO;

/**
 * �Խ��� �������̽�(��ȸ, ����, ����, ����)
 *
 */
public interface BoardService {

	/**
	 * �Խ��� ����Ʈ ��
	 * @param map
	 * @return
	 */
	public int selectBoardCnt();
	
	/**
	 * �Խ��� ����Ʈ
	 */
	public List<BoardVO> selectBoardList();
	
	/**
	 * �Խ��� ��
	 */
	public BoardVO selectBoard(int board_seq);
	
}
