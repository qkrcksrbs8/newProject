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

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private BoardService boardService;//�Խ��� �������̽�
	
	/**
	 * �Խ��� ��ȸ
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(@RequestParam(value="pageNum",defaultValue="1")int currentPage) {

		logger.info("start");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//�Խ��� ����Ʈ
		Map<String, Object> map = new HashMap<String, Object>();//����¡ map
		
		int count = boardService.selectBoardCnt();//�Խ��� ����Ʈ ��		
		logger.info("count : "+count);		
		PagingUtil page = new PagingUtil(currentPage, count, 10,3, "boardList.do");
		map.put("start", page.getStartCount());//start->�������� �� ù��° ������ �Խù���ȣ
		map.put("end", page.getEndCount());//�������Խù���ȣ

		if(0 < count) {
			
			boardList = boardService.selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
			
		}//if
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp ���
		mav.addObject("count", count);//�ѷ��ڵ��
		mav.addObject("boardList", boardList);//�Խ��� ����Ʈ
		mav.addObject("pagingHtml", page.getPagingHtml());//��ũ���ڿ��� ����
		
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
