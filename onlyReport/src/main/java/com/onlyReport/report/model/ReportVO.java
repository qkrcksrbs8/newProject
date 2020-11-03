package com.onlyReport.report.model;

import com.onlyReport.report.dao.ReportDAO;

/**
 * 게시판 VO
 *
 */
public class ReportVO {
	
	private int report_seq;//게시판 번호
	private String report_title;//게시판 제목
	private String report_content;//게시판 내용
	private String created_date;//생성일
	private String created_by;//생성자
	private String last_update_date;//최종수정일
	private String last_update_by;//최종수정자
	private int rnum;//rownum

	
	public int getreport_seq() {
		return report_seq;
	}
	public void setreport_seq(int report_seq) {
		this.report_seq = report_seq;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getreport_title() {
		return report_title;
	}
	public void setreport_title(String report_title) {
		this.report_title = report_title;
	}
	public String getreport_content() {
		return report_content;
	}
	public void setreport_content(String report_content) {
		this.report_content = report_content;
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
		return "ReportVO [report_seq=" + report_seq + ", report_title=" + report_title + ", report_content="
				+ report_content + ", created_date=" + created_date + ", created_by=" + created_by
				+ ", last_update_date=" + last_update_date + ", last_update_by=" + last_update_by + ", rnum=" + rnum
				+ "]";
	}
	

	
	

	

}
