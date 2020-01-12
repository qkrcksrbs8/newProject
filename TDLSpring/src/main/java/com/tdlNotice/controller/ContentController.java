package com.tdlNotice.controller;

import org.apache.log4j.Logger;//로그객체를 불러오기위한 클래스
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tdlNotice.dao.*;
import com.tdlNotice.domain.*;

@Controller
public class ContentController {

		//로그객체를 생성 ->매개변수전달,요청을 받아서 제대로 처리 -> 콘솔에 출력(디버깅용)
		private Logger log=Logger.getLogger(this.getClass());//검사할 클래스명을 등록
		
		@Autowired
		private TDLNoticeDAO TDLNoticeDAO;//byType을 이용해서 BoardDao 객체를 자동으로 의존성객체
		
		// /board/detail.do?seq=4 ->boardView.jsp
		@RequestMapping("/TDL_NOTICE/TDLNoticeContent.do")
		public ModelAndView process(@RequestParam("TN_num") int TN_num) {
		System.out.println("공지사항 ContentController 시작");
		//int seq=Integer.parseInt(request.getParameter("seq"));
		if(log.isDebugEnabled()) {//로그객체가 작동중이라면(디버그상태)
			log.debug("TN_num =>"+TN_num); //System.out.println("seq=>"+seq);
		}
		
		//1.조회수 증가
		TDLNoticeDAO.updateHitN(TN_num);
		
		noticeCommand TDLNotice=TDLNoticeDAO.selectTDLNotice(TN_num);//int -> Integer
		//글내용 \r\n aaaa \r\n -> 메서드로 구현 <pre></pre>
		
		/*
		ModelAndView mav=new ModelAndView("boardView"); // ~ setViewName("boardView");
		mav.addObject("board",board); // request.setAttribute("board",board);
		return mav;
		*/  //1.이동할 페이지명 2.전달할 키명 3.전달할값
		ModelAndView mav=new ModelAndView("TDLNoticeContent"); 
		mav.setViewName("TDL_NOTICE/TDLNoticeContent");
		mav.addObject("articleN",TDLNotice); 
		return mav;
	}		 
}


