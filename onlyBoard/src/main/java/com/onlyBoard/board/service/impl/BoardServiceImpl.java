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
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	Logger logger = Logger.getLogger(this.getClass());//�α�
	
	@Autowired
	private BoardDAO boardDAO;//�Խ��� DAO
	
	/* 
	 * �Խ��� ����Ʈ �� ��ȸ
	 */
	public int selectBoardCnt() {
		
		int boardCnt = 0;//�Խ��� ����Ʈ ��
		
		try {
			
			boardCnt = boardDAO.selectBoardCnt();//�Խ��� ����Ʈ �� ��ȸ
		
		}catch(Exception e) {
			
			logger.error("selectBoardCnt()");
			logger.error(e.toString());
			
		}//try
		
		return boardCnt;
	}
	
	/**
	 * �Խ��� ����Ʈ
	 */
	public List<BoardVO> selectBoardList() {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//�Խ���VO List
		
		try {
			
			boardList = boardDAO.selectBoardList();//�Խ��� ����Ʈ ��ȸ
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * �Խ��� ��
	 */
	public BoardVO selectBoard(int board_seq) {
		
		BoardVO boardVO = new BoardVO();//�Խ���VO
		
		try {
			
			boardVO = boardDAO.selectBoard(board_seq);//�Խ��� ����ȸ
			
		}catch(Exception e) {
			
			logger.error("selectBoard()");
			logger.error(e.toString());
			
		}//try
		
		return boardVO;
	
	}
	
}
