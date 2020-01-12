package com.tdlNotice.dao;

import java.util.List;
import java.util.Map;

import com.tdlNotice.domain.*;
import com.tdlPost.domain.TdlCommand;


public interface TDLNoticeDAO {

	//1.목록보기
	public List<noticeCommand> list(Map<String,Object>map);
			
	//2.총레코드수
	public int getRowCount(Map<String,Object>map);

	//3.최대값 번호 구하기
	public int getNewNumN();
		
	//4.게시판의 글쓰기
	public void insert(noticeCommand TDLNotice);
	
	//5.게시물번호에 해당하는 조회수 증가
	public void updateHitN(Integer TN_num);
	
	//6.글상세보기
	public noticeCommand selectTDLNotice(Integer TN_num);//~(int seq);

	//7.글삭제
	public void delete(Integer TN_num);
	
	//8.글수정
	public void update(noticeCommand TDLNotice);
}
