package com.onlyBoard.board.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlyBoard.board.model.BoardVO;
import com.onlyBoard.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private BoardService boardService;//�Խ��� �������̽�
	
	/**
	 * �Խ��� ��ȸ
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {

		logger.info("start");
		
		List<BoardVO> boardList = null;//�Խ��� ����Ʈ
		int count = 0;//�Խ��� ����Ʈ ��
		
		count = boardService.selectBoardCnt();//�Խ��� ����Ʈ ��
		
		logger.info("count : "+count);
		
		if(0 < count) {
			
			boardList = boardService.selectBoardList();//�Խ��� ����Ʈ ��ȸ
			
		}//if
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp ���
		mav.addObject("count", count);//�ѷ��ڵ��
		mav.addObject("boardList", boardList);//�Խ��� ����Ʈ
	
		logger.info(boardList.toString());
		
		logger.info("end");
		return mav;
	}
	
	/**
	 * �Խ��� ��
	 * @param board_seq
	 * @return
	 */
	@RequestMapping(value="/boardDetail")
	public ModelAndView boardDetail(@RequestParam(value="board_seq") int board_seq) {
		
		logger.info("start");
		
		logger.info("board_seq : "+board_seq);
		BoardVO boardList = boardService.selectBoard(board_seq);//�Խ��� �� ��ȸ
		
		logger.info(boardList.toString());
		
		ModelAndView  mav = new ModelAndView("boardDetail");
		mav.setViewName("main/boardDetail");//jsp ���
		mav.addObject("boardList", boardList);//�Խ��� ����Ʈ
		
		logger.info("end");
		
		return mav;
	}

	/**
	 * �Խ��� ������Ʈ
	 * @param board_seq
	 * @return
	 */
	@RequestMapping(value="/boardUpdate")
	public String boardUpdate(@RequestParam(value="board_seq") int board_seq
									, @RequestParam(value="board_title") int board_title
									, @RequestParam(value="board_content") int board_content) {
		
		logger.info("start");
		
		logger.info("board_seq : "+board_seq);
		logger.info("board_title : "+board_title);
		logger.info("board_content : "+board_content);
		
		
		logger.info("end");
		
		return "0000";
	}
	
}
