package com.onlyBoard.board.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * 게시글 리스트 수 조회	
	 */
	public int selectBoardCnt(Map<String, Object> map) {
		
		int boardCnt = 0;//게시판 리스트 수
		
		try {
			
			boardCnt = boardDAO.selectBoardCnt(map);//게시판 리스트 수 조회
		
		}catch(Exception e) {
			
			logger.error("selectBoardCnt()");
			logger.error(e.toString());
			
		}//try
		
		return boardCnt;
	}
	
	/**
	 * 게시글 리스트
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//게시판VO List
		
		try {
			
			boardList = boardDAO.selectBoardList(map);//게시판 리스트 조회
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * 게시글 상세
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
	
	/**
	 * 게시글 업데이트
	 */
	public String updateBoard(Map<String, Object> map) {

		String resultCode = "0000";// 0000:정상 / 9000:에러
		
		try {

			boardDAO.updateBoard(map);//게시글 업데이트
			resultCode = "0000";// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
			
		}
		
		return resultCode;
	}
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public String deleteBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:정상 / 9000:에러
		
		try {

			boardDAO.deleteBoard(map);//게시글 업데이트
			resultCode = "0000";// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
			
		}//try
		
		return resultCode;
	}
	
	public String insertBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:정상 / 9000:에러
		
		try {
			
			boardDAO.insertBoard(map);//게시글 생성
			resultCode = "0000";// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
			
		}//try
		
		return resultCode;
	}
}
