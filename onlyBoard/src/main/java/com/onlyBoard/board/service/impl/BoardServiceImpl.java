package com.onlyBoard.board.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyBoard.board.service.BoardService;
import com.onlyBoard.board.dao.BoardDAO;

/**
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	/* 
	 * �Խ��� ����Ʈ �� ��ȸ
	 */
	public int selectBoardCnt(Map<String, Object> map) {
		
		System.out.println("@@@@@ �������� ");
		
		return boardDAO.selectBoardCnt(map);
	}
	
	
//	-- �Խ��� ������
//	CREATE SEQUENCE BOARD_SEQUENCE
//	  START WITH 1
//	  INCREMENT BY 1
//	  MAXVALUE 100
//	  MINVALUE 1
//	  NOCYCLE;
//
//	--�Խ��� ���̺�
//	create table board_mst(
//	board_seq int primary key
//	,board_title varchar2(25) not null
//	, board_content varchar2(200)
//	, created_date date
//	, created_by varchar2(25)
//	, last_update_date date
//	, last_update_by varchar2(25)
//	);
//
//	insert into board_mst(
//	board_seq
//	, board_title
//	, board_content
//	, created_date
//	, created_by
//	, last_update_date
//	, last_update_by
//	)
//	values(
//	BOARD_SEQUENCE.NEXTVAL
//	, '�ȳ��ϼ���'
//	, '�ݰ����ϴ�'
//	, sysdate
//	, 'admin'
//	, sysdate
//	, 'admin'
//	);
	
}
