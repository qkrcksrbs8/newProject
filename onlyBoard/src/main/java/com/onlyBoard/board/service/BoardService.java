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
	 * �Խñ� ����Ʈ ��
	 * @param map
	 * @return
	 */
	public int selectBoardCnt(Map<String, Object> map);
	
	/**
	 * �Խñ� ����Ʈ
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map);
	
	/**
	 * �Խñ� ��
	 */
	public BoardVO selectBoard(int board_seq);
	
	/**
	 * �Խñ� ������Ʈ
	 */
	public String updateBoard(Map<String, Object> map);
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public String deleteBoard(Map<String, Object> map);
	
	/**
	 * �Խñ� �ۼ�
	 */
	public String insertBoard(Map<String, Object> map);
}
