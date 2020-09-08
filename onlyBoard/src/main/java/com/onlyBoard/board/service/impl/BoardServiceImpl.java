package com.onlyBoard.board.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyBoard.board.service.BoardService;
import com.onlyBoard.board.dao.BoardDAO;

/**
 * 게시판 ServiceImpl 정의
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	/* 
	 * 게시판 리스트 수 조회
	 */
	public int selectBoardCnt(Map<String, Object> map) {
		
		System.out.println("@@@@@ 서비스임플 ");
		
		return boardDAO.selectBoardCnt(map);
	}
	
}
