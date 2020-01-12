package com.tdlNotice.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tdlNotice.dao.TDLNoticeDAO;
import com.tdlNotice.domain.noticeCommand;


@Controller
public class WriterController {
        //Logger객체=>제대로 입력했는지 체크해서 그 결과를 콘솔에 출력
	   //                      시켜주는 객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private TDLNoticeDAO TDLNoticeDAO;
	 
	/*
	 * 하나의 요청명령어=>하나의 컨트롤러만 사용X
	 * 하나의 컨트롤러->여러개의 요청명령어를 등록해서 사용이 가능.
	 * 같은 요청명령어를 GET or POST으로 전송할지를 결정하는 속성
	 * method=RequestMethod.GET | method=RequestMethod.POST
	 */
	 //1.글쓰기 폼으로 이동(Get방식)
	@RequestMapping(value="/TDL_NOTICE/TDLNoticeWrite.do",method=RequestMethod.GET)
	public String form() {  //메서드명은 임의로 작성
		System.out.println("공지사항 글쓰기 -> 다시 처음부터 값을 입력받기위해서 form()호출됨!");
		return "TDL_NOTICE/TDLNoticeWrite";// return "이동할 페이지명'//definition name과 동일
	}
	
	//2.에러메세지 출력=>다시 초기화가 가능하게 설정->@ModelAttribute("커맨드객체 별칭명")
	@ModelAttribute("commandN")
	public noticeCommand forBacking() { //반환형 (DTO형) 임의의 메서드명
		System.out.println("공지사항 -> forBacking()호출됨!");
		return new noticeCommand();
	}
	//3.입력해서 유효성검사->에러발생
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
	@RequestMapping(value="/TDL_NOTICE/TDLNoticeWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("commandN") noticeCommand commandN,
			                       BindingResult result) {
		if(log.isDebugEnabled()) {
			log.debug("noticeCommand=>"+commandN);//입력받은 값을 출력
			//로그객체명.debug(출력대상자를 입력)
		}
		//유효성검사			
			//최대값+1
			int newNumN=TDLNoticeDAO.getNewNumN()+1;
			System.out.println("newNumN=>"+newNumN);//1->2
			//게시물번호->계산->저장
			commandN.setTN_num(newNumN);//2
			//글쓰기 호출
			TDLNoticeDAO.insert(commandN);
			System.out.println("공지사항 글쓰기 TN_id =>"+commandN.getTN_id());
			System.out.println("공지사항 글쓰기 TN_num =>"+commandN.getTN_num());
			System.out.println("공지사항 글쓰기 TN_date =>"+commandN .getTN_date());
			System.out.println("공지사항 글쓰기 TN_readcont =>"+commandN.getTN_readcount());
			System.out.println("공지사항 글쓰기 TN_title =>"+commandN.getTN_title());
			System.out.println("공지사항 글쓰기 TN_content =>"+commandN.getTN_content());

		//return "redirect:요청명령어"; =>return "이동할 페이지명"
		return "redirect:/TDL_NOTICE/TDLNoticeList.do";
	}
}

