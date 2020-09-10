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
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	Logger logger = Logger.getLogger(this.getClass());//�α�
	
	@Autowired
	private BoardDAO boardDAO;//�Խ��� DAO
	
	/* 
	 * �Խñ� ����Ʈ �� ��ȸ	
	 */
	public int selectBoardCnt(Map<String, Object> map) {
		
		int boardCnt = 0;//�Խ��� ����Ʈ ��
		
		try {
			
			boardCnt = boardDAO.selectBoardCnt(map);//�Խ��� ����Ʈ �� ��ȸ
		
		}catch(Exception e) {
			
			logger.error("selectBoardCnt()");
			logger.error(e.toString());
			
		}//try
		
		return boardCnt;
	}
	
	/**
	 * �Խñ� ����Ʈ
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//�Խ���VO List
		
		try {
			
			boardList = boardDAO.selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * �Խñ� ��
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
	
	/**
	 * �Խñ� ������Ʈ
	 */
	public String updateBoard(Map<String, Object> map) {

		String resultCode = "0000";// 0000:���� / 9000:����
		
		try {

			boardDAO.updateBoard(map);//�Խñ� ������Ʈ
			resultCode = "0000";// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
			
		}
		
		return resultCode;
	}
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public String deleteBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:���� / 9000:����
		
		try {

			boardDAO.deleteBoard(map);//�Խñ� ������Ʈ
			resultCode = "0000";// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
			
		}//try
		
		return resultCode;
	}
	
	public String insertBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:���� / 9000:����
		
		try {
			
			boardDAO.insertBoard(map);//�Խñ� ����
			resultCode = "0000";// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
			
		}//try
		
		return resultCode;
	}
}
