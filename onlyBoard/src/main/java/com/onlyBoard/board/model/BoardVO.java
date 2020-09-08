package com.onlyBoard.board.model;

/**
 * 게시판 VO
 *
 */
public class BoardVO {
	
	private String board_title;//게시판 제목
	private String board_content;//게시판 내용
	private String created_date;//생성일
	private String created_by;//생성자
	private String last_update_date;//최종수정일
	private String last_update_by;//최종수정자
	
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
	
	@Override
	public String toString() {
		return "BoardVO [board_title=" + board_title + ", board_content=" + board_content + ", created_date="
				+ created_date + ", created_by=" + created_by + ", last_update_date=" + last_update_date
				+ ", last_update_by=" + last_update_by + "]";
	}
	
	

}
