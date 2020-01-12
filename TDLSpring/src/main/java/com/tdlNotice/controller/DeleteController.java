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

import com.tdlNotice.dao.TDLNoticeDAO;
import com.tdlNotice.domain.noticeCommand;


@Controller
public class DeleteController {
        //Logger객체=>제대로 입력했는지 체크해서 그 결과를 콘솔에 출력
	   //                      시켜주는 객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private TDLNoticeDAO TDLNoticeDAO;
	 

	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
	@RequestMapping(value="/TDL_NOTICE/TDLNoticeDelete.do",method=RequestMethod.GET)
	public String submit(@ModelAttribute("commandN") noticeCommand commandN,
			                       BindingResult result) {
		if(log.isDebugEnabled()) {
			log.debug("noticeCommand=>"+commandN);//입력받은 값을 출력
			//로그객체명.debug(출력대상자를 입력)
		}
		int TN_num = commandN.getTN_num();
		//글삭제
			System.out.println("삭제할 TN_num =>"+TN_num);
			TDLNoticeDAO.delete(TN_num);
			System.out.println("공지사항 삭제 성공! ");
		//return "redirect:요청명령어"; =>return "이동할 페이지명"
			return "redirect:/TDL_NOTICE/TDLNoticeList.do";
	}
}

