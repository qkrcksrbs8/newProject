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

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private BoardService boardService;//�Խ��� �������̽�
	
	/**
	 * �Խ��� ��ȸ
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(@RequestParam(value="pageNum",defaultValue="1")int currentPage
								, @RequestParam(value="keyField", required =false) String keyField
								, @RequestParam(value="keyWord", required =false) String keyWord
								, @RequestParam(value="pagee", defaultValue="1") int pagee) {

		logger.info("start");
		logger.info("keyField : "+keyField);
		logger.info("keyWord : "+keyWord);
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//�Խ��� ����Ʈ
		Map<String, Object> map = new HashMap<String, Object>();//����¡ map
		map.put("keyField", keyField);//�˻��о�
//		map.put("keyWord", keyWord);//�˻���
		map.put("keyWord", keyWord);//�˻���
		
		int count = boardService.selectBoardCnt(map);//�Խ��� ����Ʈ ��		
		logger.info("count : "+count);		
		
		PagingUtil page;
		
		if(keyWord == null) {
			page = new PagingUtil(currentPage, count, 5,2, "boardList.do");
		}else {
			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "boardList.do",null);
		}
		

		
		BoardVO pagingg = new BoardVO();
		
		int blockStartNum = pagingg.getBlockStartNum();
		int blockLastNum = pagingg.getBlockLastNum();
		int lastPageNum = pagingg.getLastPageNum();
		int pageCount= pagingg.getPagecount();
		
		int blockNum = 0;

		// ���� �������� ���� block�� ���� ��ȣ, �� ��ȣ�� ���
        blockStartNum = (pageCount * pagee) + 1;
        blockLastNum = blockStartNum + (pageCount-1);
		
        // �� �������� ������ ��ȣ
		if( count % pageCount == 0 ) {
            lastPageNum = (int)Math.floor(count/pageCount);
        }
        else {
            lastPageNum = (int)Math.floor(count/pageCount) + 1;
        }
		
		if(keyWord != null) {//�˻� Ű���尡 ���� ��
			
			if( count % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(count/pageCount);
	        }else {
	            lastPageNum = (int)Math.floor(count/pageCount) + 1;
	        }
			
		}//if
		
		System.out.println("start : "+blockStartNum+", end : "+blockLastNum);
		
		if(0 < count) {
		
//			map.put("start", page.getStartCount());//start->�������� �� ù��° ������ �Խù���ȣ
//			map.put("end", page.getEndCount());//�������Խù���ȣ
			map.put("start", blockStartNum);//start->�������� �� ù��° ������ �Խù���ȣ
			map.put("end", blockLastNum);//�������Խù���ȣ
			
			boardList = boardService.selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
			
		}//if
		
		ModelAndView  mav = new ModelAndView("board");
		mav.setViewName("main/board");//jsp ���
		mav.addObject("count", count);//�ѷ��ڵ��
		mav.addObject("boardList", boardList);//�Խ��� ����Ʈ
		mav.addObject("pagingHtml", page.getPagingHtml());//��ũ���ڿ��� ����
		
		mav.addObject("blockStartNum", blockStartNum);//�ѷ��ڵ��
		mav.addObject("blockLastNum", blockLastNum);//�Խ��� ����Ʈ
		mav.addObject("lastPageNum", lastPageNum);//��ũ���ڿ��� ����
		mav.addObject("keyWord", keyWord);
		
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
		boardList.getBoard_seq()l
		
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
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ������Ʈ map
			map.put("board_seq", board_seq);//�Խñ� ������
			map.put("board_title", board_title.replace("\r\n","<br>"));//�Խñ� ����
			map.put("board_content", board_content.replace("\r\n","<br>"));//�Խñ� ����
			map.put("user_name", user_name);//����������
			 
			resultCode = boardService.updateBoard(map);//�Խñ� ������Ʈ
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	
	/**
	 * �Խ��� ����
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
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("board_seq", board_seq);//�Խñ� ������
			map.put("user_name", user_name);//����������
			
			resultCode = boardService.deleteBoard(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	/**
	 * �Խñ� �ۼ� ������ �̵�
	 * @return
	 */
	@RequestMapping(value="/writeBoard")
	public ModelAndView boardWrite() {
				
		ModelAndView  mav = new ModelAndView("boardWrite");
		mav.setViewName("main/boardWrite");//jsp ���
		
		return mav;
	}
	
	/**
	 * �Խñ� �ۼ�
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
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("board_title", board_title);//�Խñ� ����
			map.put("board_content", board_content);//�Խñ� ����
			map.put("created_by", user_name);//������
			map.put("last_update_by", user_name);//����������
			
			resultCode = boardService.insertBoard(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
				
		logger.info("end");
		
		return resultCode;
		
	}
	
}
