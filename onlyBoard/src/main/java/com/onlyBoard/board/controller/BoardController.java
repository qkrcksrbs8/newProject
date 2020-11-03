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
								) {

		logger.info("boardList() : start");						//boardList ����
		logger.info("keyField : "+keyField);					
		logger.info("keyWord : "+keyWord);
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();		//�Խ��� ����Ʈ
		Map<String, Object> map = new HashMap<String, Object>();//����¡ map
		map.put("keyField", keyField);							//�˻��о�
		map.put("keyWord", keyWord);							//�˻���
		
		int count = boardService.selectBoardCnt(map);			//�Խ��� ����Ʈ �� ��ȸ
		logger.info("count : "+count);							//�Խ��� ����Ʈ �� �α׷� ���

		PagingUtil page;//����¡ ó���� ���� ��ü ����
		
		if(keyWord == null) {
			page = new PagingUtil(currentPage, count, 5,2, "boardList.do");							//�˻�� �ִٸ�
		}else {
			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "boardList.do",null);	//�˻�� ���ٸ�
		}
		

		//--------------------------------
		//start->�������� �� ù��° ������ �Խù���ȣ
		//--------------------------------
		map.put("start", page.getStartCount());	//���� �Խù���ȣ
		map.put("end", page.getEndCount());		//�������Խù���ȣ

		//---------------------------
		//�Խù��� 1�� �̻� �����ϸ� ����Ʈ ��ȸ
		//---------------------------
		if(count > 0) {
			
			boardList = boardService.selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
			
		};//if
		
		ModelAndView  mav = new ModelAndView("board");		//board model ����
		mav.setViewName("main/board");						//jsp ���
		mav.addObject("count", count);						//�ѷ��ڵ��
		mav.addObject("boardList", boardList);				//�Խ��� ����Ʈ
		mav.addObject("pagingHtml", page.getPagingHtml());	//��ũ���ڿ��� ����
		mav.addObject("keyWord", keyWord);					//�˻��� ����
		
		logger.info(boardList.toString());					//VO �α׷� ����
		logger.info("boardList() : end");					//board ����
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
		boardList.getBoard_seq();
		
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
