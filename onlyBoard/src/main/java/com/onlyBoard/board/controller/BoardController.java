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

	Logger logger = Logger.getLogger(this.getClass());//로그

	@Autowired
	private BoardService boardService;//게시판 인터페이스
	
	/**
	 * 게시판 조회
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {

		logger.info("start");
		
		List<BoardVO> boardList = null;//게시판 리스트
		int count = 0;//게시판 리스트 수
		
		count = boardService.selectBoardCnt();//게시판 리스트 수
		
		logger.info("count : "+count);
		
		if(0 < count) {
			
			boardList = boardService.selectBoardList();//게시판 리스트 조회
			
		}//if
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp 경로
		mav.addObject("count", count);//총레코드수
		mav.addObject("boardList", boardList);//게시판 리스트
	
		logger.info(boardList.toString());
		
		logger.info("end");
		return mav;
	}
	
	/**
	 * 게시판 상세
	 * @param board_seq
	 * @return
	 */
	@RequestMapping(value="/boardDetail")
	public ModelAndView boardDetail(@RequestParam(value="board_seq") int board_seq) {
		
		logger.info("start");
		
		logger.info("board_seq : "+board_seq);
		BoardVO boardList = boardService.selectBoard(board_seq);//게시판 상세 조회
		
		logger.info(boardList.toString());
		
		ModelAndView  mav = new ModelAndView("boardDetail");
		mav.setViewName("main/boardDetail");//jsp 경로
		mav.addObject("boardList", boardList);//게시판 리스트
		
		logger.info("end");
		
		return mav;
	}

	/**
	 * 게시판 업데이트
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
