package com.onlyBoard.board.service;

import java.util.List;
import java.util.Map;

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
	public List<BoardVO> selectBoardList(Map<String, Object> map);
	
	/**
	 * �Խ��� ��
	 */
	public BoardVO selectBoard(int board_seq);
	
}
