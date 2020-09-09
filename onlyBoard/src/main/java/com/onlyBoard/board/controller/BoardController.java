package com.onlyBoard.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlyBoard.board.model.BoardVO;
import com.onlyBoard.board.service.BoardService;
import com.onlyBoard.board.util.PagingUtil;

@Controller
public class BoardController {

	Logger logger = Logger.getLogger(this.getClass());//로그

	@Autowired
	private BoardService boardService;//게시판 인터페이스
	
	/**
	 * 게시판 조회
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(@RequestParam(value="pageNum",defaultValue="1")int currentPage) {

		logger.info("start");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//게시판 리스트
		Map<String, Object> map = new HashMap<String, Object>();//페이징 map
		
		int count = boardService.selectBoardCnt();//게시판 리스트 수		
		logger.info("count : "+count);		
		PagingUtil page = new PagingUtil(currentPage, count, 10,3, "boardList.do");
		map.put("start", page.getStartCount());//start->페이지당 맨 첫번째 나오는 게시물번호
		map.put("end", page.getEndCount());//마지막게시물번호

		if(0 < count) {
			
			boardList = boardService.selectBoardList(map);//게시판 리스트 조회
			
		}//if
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp 경로
		mav.addObject("count", count);//총레코드수
		mav.addObject("boardList", boardList);//게시판 리스트
		mav.addObject("pagingHtml", page.getPagingHtml());//링크문자열을 전달
		
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
//	@RequestMapping(value="/boardUpdate")
//	public ResponseEntity<Object> boardUpdate(@RequestParam(value="board_seq") int board_seq
//									, @RequestParam(value="board_title") int board_title
//									, @RequestParam(value="board_content") int board_content) {
//		
//		logger.info("start");
//		
//		HttpStatus statusCode = HttpStatus.OK;
//		
//		
//		logger.info("board_seq : "+board_seq);
//		logger.info("board_title : "+board_title);
//		logger.info("board_content : "+board_content);
//		
//		
//		logger.info("end");
//		
//		return ;
//	}
	
}
