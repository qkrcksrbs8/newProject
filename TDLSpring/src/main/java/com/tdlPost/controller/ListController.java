package com.tdlPost.controller;

import java.util.*;

//---------로그에 출력할 수있도록 설정(Annotation)--
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//---------메서드호출 및 페이징 처리부분---------------
import com.tdlPost.dao.TDLPostDAO;//DB접속
import com.tdlPost.domain.TdlCommand;//DTO
import com.tdlPost.util.PagingUtil;//페이징처리 정보

@Controller
public class ListController {
	
	//private Logger log=Logger.getLogger(ListController.class); //로그를 처리할 클래스명
	private Logger log = Logger.getLogger(this.getClass());//this.getClass()->현재클래스명
	
	//자동으로 Setter Method호출X->의존성객체 넣어줌
	@Autowired
	private TDLPostDAO TDLPostDAO;//자동으로 Setter Method 호출X=>의존성객체 넣어줌(byType)
	
	@RequestMapping("/TDL_POST/TDLPostList.do")
	public ModelAndView process
	   (@RequestParam(value="pageNum",defaultValue="1")int currentPage,
	    @RequestParam(value="keyField",defaultValue="")String keyField,
	    @RequestParam(value="keyWord",defaultValue="")String keyWord){
		if(log.isDebugEnabled()){ //로그객체가 디버깅모드상태인지 아닌지를 체크
			System.out.println("여기는 보드 list.do");
			log.debug("currentPage : " + currentPage);//log.debug메서드 사용
			log.debug("keyField : " + keyField);
			log.debug("keyWord : " + keyWord);
		}
		//검색분야,검색어를 전달->parameteType="map"
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyField", keyField);//검색분야
		map.put("keyWord", keyWord);//검색어
		
		//총글의 갯수 또는 검색된 글의 갯수
		int count=TDLPostDAO.getRowCount(map);	    
		//페이징 처리  1.현재페이지 2.총레코드수 3.페이지당 게시물수 4.블럭당 페이지수 5.요청명령어
		PagingUtil page = new PagingUtil(currentPage, count, 10,3, "TDLPostList.do");	
		//start->페이지당 맨 첫번째 나오는 게시물번호
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());//마지막게시물번호
		
		List<TdlCommand> list = null;
		if(count > 0){
			System.out.println("여기는 DAO 호출");
			list = TDLPostDAO.list(map);
		}else{
			list = Collections.emptyList();
			System.out.println("ListController클래스의 count="+count);
		}
		ModelAndView  mav = new ModelAndView("TDLPostList");
		mav.setViewName("TDL_POST/TDLPostList");//TDLPostList.jsp
		mav.addObject("count", count);//총레코드수
		mav.addObject("list", list);//레코드전체값
		mav.addObject("pagingHtml", page.getPagingHtml());//링크문자열을 전달
		System.out.println("자유게시판 끝");
		return mav;
	}
}























