package com.tdlPost.controller;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;//로그객체를 불러오기위한 클래스
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//자유게시판 DB 연결
import com.tdlPost.dao.TDLPostDAO;
import com.tdlPost.domain.TdlCommand;

//댓글DB연결
import com.tdlComment.dao.*;
import com.tdlComment.domain.*;

//좋아요DB연결
import com.tdlLike.dao.*;
import com.tdlLike.domain.*;

@Controller
public class ContentController {

		//로그객체를 생성 ->매개변수전달,요청을 받아서 제대로 처리 -> 콘솔에 출력(디버깅용)
		private Logger log=Logger.getLogger(this.getClass());//검사할 클래스명을 등록
		
		@Autowired
		private TDLPostDAO TDLPostDAO;//byType을 이용해서 BoardDao 객체를 자동으로 의존성객체
		
		@Autowired
		private TDLCommentDAO TDLCommentDAO;//댓글 DAO
		
		@Autowired
		private TDLLikeDAO TDLLikeDAO;//좋아요 DAO
		
		// 자유게시판 게시물
		@RequestMapping("/TDL_POST/TDLPostContent.do")
		public ModelAndView process(@ModelAttribute("commandL") likeCommand commandL,BindingResult result,
													@RequestParam(value="TP_num",defaultValue="1") int TP_num,
													@RequestParam(value="TU_id",defaultValue="false") String TU_id, HttpServletRequest request) {
		System.out.println("자유게시판 ContentController 시작");
		System.out.println("TP_NUM=>"+TP_num);
		if(TP_num==1) {
			System.out.println("TP_NUM=>"+TP_num);
			TP_num=(Integer)request.getAttribute("TP_num");
			
		}
		if(TU_id=="false") {
			TU_id=(String)request.getAttribute("TU_id");
		}
		//int seq=Integer.parseInt(request.getParameter("seq"));
		if(log.isDebugEnabled()) {//로그객체가 작동중이라면(디버그상태)
			log.debug("TP_num =>"+TP_num); //System.out.println("seq=>"+seq);
		}
		//1.조회수 증가
		TDLPostDAO.updateHit(TP_num);
		TdlCommand TDLPost=TDLPostDAO.selectTDLPost(TP_num);//int -> Integer
		//글내용 \r\n aaaa \r\n -> 메서드로 구현 <pre></pre>
		
		
		//여기부터는 댓글
		int TPC_num=TP_num;
		 
		int countC=TDLCommentDAO.getRowCountC(TPC_num);
				 
		List<CommentCommand> listC = null;
		if(countC > 0){
			System.out.println("여기는 DAO 호출 countC =>"+countC);
			listC = TDLCommentDAO.listC(TPC_num);
		}else{
			listC = Collections.emptyList();
			System.out.println("댓글 ListController클래스의 count="+countC);
		}
		
		//좋아요 체크
		System.out.println("좋아요체크 아이디 =>"+TU_id);
		System.out.println("좋아요체크 게시물번호 =>"+TU_id);
		commandL.setTL_id(TU_id);
		commandL.setTL_PNUM(TP_num);
		List<likeCommand> likeCheck=TDLLikeDAO.likeCheck(commandL);
		
		
		/*
		ModelAndView mav=new ModelAndView("boardView"); // ~ setViewName("boardView");
		mav.addObject("board",board); // request.setAttribute("board",board);
		return mav;
		*/  //1.이동할 페이지명 2.전달할 키명 3.전달할값
		ModelAndView mav=new ModelAndView("TDLPostContent"); 
		mav.setViewName("TDL_POST/TDLPostContent");
		mav.addObject("article",TDLPost); 
		mav.addObject("listC",listC);
		mav.addObject("sumCountC",countC);
		mav.addObject("likeCheck",likeCheck);
		return mav;
	}		 
}


