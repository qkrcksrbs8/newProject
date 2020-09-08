package com.onlyBoard.board.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyBoard.board.service.BoardService;
import com.onlyBoard.board.dao.BoardDAO;

/**
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	/* 
	 * �Խ��� ����Ʈ �� ��ȸ
	 */
	public int selectBoardCnt(Map<String, Object> map) {
		
		System.out.println("@@@@@ �������� ");
		
		return boardDAO.selectBoardCnt(map);
	}
	
}
