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
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView boardList(@RequestParam(value="pageNum",defaultValue="1")int currentPage
								, @RequestParam(value="keyField", required =false) String keyField
								, @RequestParam(value="keyWord", required =false) String keyWord
								) {

		logger.info("boardList() : start");						//boardList 시작
		logger.info("keyField : "+keyField);					
		logger.info("keyWord : "+keyWord);
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();		//게시판 리스트
		Map<String, Object> map = new HashMap<String, Object>();//페이징 map
		map.put("keyField", keyField);							//검색분야
		map.put("keyWord", keyWord);							//검색어
		
		int count = boardService.selectBoardCnt(map);			//게시판 리스트 수 조회
		logger.info("count : "+count);							//게시판 리스트 수 로그로 찍기

		PagingUtil page;//페이징 처리를 위한 객체 선언
		
		if(keyWord == null) {
			page = new PagingUtil(currentPage, count, 5,2, "boardList.do");							//검색어가 있다면
		}else {
			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "boardList.do",null);	//검색어가 없다면
		}
		

		//--------------------------------
		//start->페이지당 맨 첫번째 나오는 게시물번호
		//--------------------------------
		map.put("start", page.getStartCount());	//시작 게시물번호
		map.put("end", page.getEndCount());		//마지막게시물번호

		//---------------------------
		//게시물이 1개 이상 존재하면 리스트 조회
		//---------------------------
		if(count > 0) {
			
			boardList = boardService.selectBoardList(map);//게시판 리스트 조회
			
		};//if
		
		ModelAndView  mav = new ModelAndView("board");		//board model 선언
		mav.setViewName("main/board");						//jsp 경로
		mav.addObject("count", count);						//총레코드수
		mav.addObject("boardList", boardList);				//게시판 리스트
		mav.addObject("pagingHtml", page.getPagingHtml());	//링크문자열을 전달
		mav.addObject("keyWord", keyWord);					//검색어 전달
		
		logger.info(boardList.toString());					//VO 로그로 찍어보기
		logger.info("boardList() : end");					//board 종료
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
		boardList.getBoard_seq();
		
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
	@ResponseBody
	@RequestMapping(value="/updateBoard")
	public String updateBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="board_title") String board_title
							, @RequestParam(value="board_content") String board_content
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("board_seq : "+board_seq);
		logger.info("board_title : "+board_title);
		logger.info("board_content : "+board_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 업데이트 map
			map.put("board_seq", board_seq);//게시글 시퀀스
			map.put("board_title", board_title.replace("\r\n","<br>"));//게시글 제목
			map.put("board_content", board_content.replace("\r\n","<br>"));//게시글 내용
			map.put("user_name", user_name);//최종수정자
			 
			resultCode = boardService.updateBoard(map);//게시글 업데이트
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	
	/**
	 * 게시판 삭제
	 * @param board_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBoard")
	public String deleteBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("board_seq : "+board_seq);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("board_seq", board_seq);//게시글 시퀀스
			map.put("user_name", user_name);//최종수정자
			
			resultCode = boardService.deleteBoard(map);//게시글 삭제
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	/**
	 * 게시글 작성 페이지 이동
	 * @return
	 */
	@RequestMapping(value="/writeBoard")
	public ModelAndView boardWrite() {
				
		ModelAndView  mav = new ModelAndView("boardWrite");
		mav.setViewName("main/boardWrite");//jsp 경로
		
		return mav;
	}
	
	/**
	 * 게시글 작성
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertBoard")
	public String boardInsert(@RequestParam(value="board_title", required =false) String board_title
							  , @RequestParam(value="board_content", required =false) String board_content
							  , @RequestParam(value="user_name", required =false) String user_name) {
		
		logger.info("start");
		logger.info("board_title : "+board_title);
		logger.info("board_content : "+board_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:정상 / 9000:에러
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("board_title", board_title);//게시글 제목
			map.put("board_content", board_content);//게시글 내용
			map.put("created_by", user_name);//생성자
			map.put("last_update_by", user_name);//최종수정자
			
			resultCode = boardService.insertBoard(map);//게시글 삭제
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
				
		logger.info("end");
		
		return resultCode;
		
	}
	
}
