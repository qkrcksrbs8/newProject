package com.tdlPost.controller;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tdlPost.dao.TDLPostDAO;
import com.tdlPost.domain.TdlCommand;


@Controller
public class UpdateController {
        //Logger객체=>제대로 입력했는지 체크해서 그 결과를 콘솔에 출력
	   //                      시켜주는 객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private TDLPostDAO TDLPostDAO;
	 
	
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
		@RequestMapping(value="/TDL_POST/TDLPostUpdate.do",method=RequestMethod.POST)
		public String submit(@ModelAttribute("command") TdlCommand command) {
			if(log.isDebugEnabled()) {
				log.debug("TdlCommand=>"+command);//입력받은 값을 출력
				//로그객체명.debug(출력대상자를 입력)
			}
			//유효성검사			

				//글쓰기 호출
				TDLPostDAO.update(command);
				System.out.println("자유게시판 글수정 TP_title =>"+command.getTP_title());
				System.out.println("자유게시판 글수정 TP_content =>"+command.getTP_content());

			//return "redirect:요청명령어"; =>return "이동할 페이지명"
			return "redirect:/TDL_POST/TDLPostList.do";
		}
	
	 //1.글수정 폼으로 이동(Get방식)
		@RequestMapping(value="/TDL_POST/TDLPostUpdate.do",method=RequestMethod.GET)
		public ModelAndView form(@ModelAttribute("command")  TdlCommand command) {  //메서드명은 임의로 작성
			System.out.println("업데이트 페이지 이동 UpdateController  호출됨!");
			int TP_num = command.getTP_num();
			if(log.isDebugEnabled()) {//로그객체가 작동중이라면(디버그상태)
				log.debug("TP_num =>"+TP_num); //System.out.println("seq=>"+seq);
			}
			TDLPostDAO.selectTDLPost(TP_num);
			TdlCommand TDLPost=TDLPostDAO.selectTDLPost(TP_num);
			System.out.println("글 제목 => "+TDLPost.getTP_title());
			System.out.println("글 내용 =>"+TDLPost.getTP_content());
			
			//									1. 이동할 페이지           2. 키명     3. 키값
			return new ModelAndView("TDL_POST/TDLPostUpdate","article",TDLPost);// ${board}
		}
	
}

