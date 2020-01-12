package com.tdlNotice.controller;

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

import com.tdlNotice.dao.*;
import com.tdlNotice.domain.*;


@Controller
public class UpdateController {
        //Logger객체=>제대로 입력했는지 체크해서 그 결과를 콘솔에 출력
	   //                      시켜주는 객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private TDLNoticeDAO TDLNoticeDAO;
	 
	
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
		@RequestMapping(value="/TDL_NOTICE/TDLNoticeUpdate.do",method=RequestMethod.POST)
		public String submit(@ModelAttribute("commandN") noticeCommand commandN) {
			if(log.isDebugEnabled()) {
				log.debug("noticeCommand=>"+commandN);//입력받은 값을 출력
				//로그객체명.debug(출력대상자를 입력)
			}
			//유효성검사			

				//글쓰기 호출
				TDLNoticeDAO.update(commandN);
				System.out.println("공지사항 글수정 TN_title =>"+commandN.getTN_title());
				System.out.println("공지사항 글수정 TN_content =>"+commandN.getTN_content());

			//return "redirect:요청명령어"; =>return "이동할 페이지명"
				return "redirect:/TDL_NOTICE/TDLNoticeList.do";
		}
	
	 //1.글수정 폼으로 이동(Get방식)
		@RequestMapping(value="/TDL_NOTICE/TDLNoticeUpdate.do",method=RequestMethod.GET)
		public ModelAndView form(@ModelAttribute("commandN")  noticeCommand commandN) {  //메서드명은 임의로 작성
			System.out.println("업데이트 페이지 이동 UpdateController  호출됨!");
			int TN_num = commandN.getTN_num();
			if(log.isDebugEnabled()) {//로그객체가 작동중이라면(디버그상태)
				log.debug("TN_num =>"+TN_num); //System.out.println("seq=>"+seq);
			}
			//여기부터 다시하기
			TDLNoticeDAO.selectTDLNotice(TN_num);
			noticeCommand tdlNotice=TDLNoticeDAO.selectTDLNotice(TN_num);
			System.out.println("글 제목 => "+tdlNotice.getTN_title());
			System.out.println("글 내용 =>"+tdlNotice.getTN_content());
			
			//									1. 이동할 페이지           2. 키명     3. 키값
			return new ModelAndView("TDL_NOTICE/TDLNoticeUpdate","articleN",tdlNotice);// ${board}
		}
	
}

