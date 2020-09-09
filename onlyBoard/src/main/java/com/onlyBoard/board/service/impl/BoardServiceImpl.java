package com.onlyBoard.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyBoard.board.dao.BoardDAO;
import com.onlyBoard.board.model.BoardVO;
import com.onlyBoard.board.service.BoardService;

/**
 * 게시판 ServiceImpl 정의
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	Logger logger = Logger.getLogger(this.getClass());//로그
	
	@Autowired
	private BoardDAO boardDAO;//게시판 DAO
	
	/* 
	 * 게시판 리스트 수 조회
	 */
	public int selectBoardCnt() {
		
		int boardCnt = 0;//게시판 리스트 수
		
		try {
			
			boardCnt = boardDAO.selectBoardCnt();//게시판 리스트 수 조회
		
		}catch(Exception e) {
			
			logger.error("selectBoardCnt()");
			logger.error(e.toString());
			
		}//try
		
		return boardCnt;
	}
	
	/**
	 * 게시판 리스트
	 */
	public List<BoardVO> selectBoardList() {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//게시판VO List
		
		try {
			
			boardList = boardDAO.selectBoardList();//게시판 리스트 조회
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * 게시판 상세
	 */
	public BoardVO selectBoard(int board_seq) {
		
		BoardVO boardVO = new BoardVO();//게시판VO
		
		try {
			
			boardVO = boardDAO.selectBoard(board_seq);//게시판 상세조회
			
		}catch(Exception e) {
			
			logger.error("selectBoard()");
			logger.error(e.toString());
			
		}//try
		
		return boardVO;
	
	}
	
}
