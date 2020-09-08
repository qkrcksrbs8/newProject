package com.onlyBoard.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.onlyBoard.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private BoardService boardService;//�Խ��� �������̽�
	
	
	@RequestMapping(value="/test")
	public ModelAndView test() {

		System.out.println("@@@@@@ test() start");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", "admin");
		int cnt = boardService.selectBoardCnt(map);
		
		System.out.println("@@@@@@ cnt : "+cnt);
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp ���

		System.out.println("@@@@@@ test() end");
		return mav;
	}
	
}
