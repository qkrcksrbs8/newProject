package com.onlyBoard.board.model;

import com.onlyBoard.board.dao.BoardDAO;

/**
 * �Խ��� VO
 *
 */
public class BoardVO {
	
	private int board_seq;//�Խ��� ��ȣ
	private String board_title;//�Խ��� ����
	private String board_content;//�Խ��� ����
	private String created_date;//������
	private String created_by;//������
	private String last_update_date;//����������
	private String last_update_by;//����������
	private int rnum;//rownum
	
    private final static int pageCount = 5;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
	
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLast_update_by() {
		return last_update_by;
	}
	public void setLast_update_by(String last_update_by) {
		this.last_update_by = last_update_by;
	}
	public int getBlockStartNum() {
		return blockStartNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}
	public int getBlockLastNum() {
		return blockLastNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public static int getPagecount() {
		return pageCount;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_seq=" + board_seq + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", created_date=" + created_date + ", created_by=" + created_by + ", last_update_date="
				+ last_update_date + ", last_update_by=" + last_update_by + ", rnum=" + rnum + ", blockStartNum="
				+ blockStartNum + ", blockLastNum=" + blockLastNum + ", lastPageNum=" + lastPageNum + "]";
	}
	

	
	

	

}
