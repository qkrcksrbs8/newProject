package com.onlyReport.report.model;

public class Detailed_WorkVO {
	
	private int work_seq 			= 0;			//세부업무실적번호
	private String sector 			= new String();	//부문
	private String fr_work 			= new String();	//예정업무
	private String to_work 			= new String();	//실시업무
	private String remark 			= new String();	//비고
	private String company_code 	= new String();	//매장코드
	private String company_name 	= new String();	//매장이름
	private String created_by 		= new String();	//최초생성자
	private String created_date 	= new String();	//최초생성일
	private String last_update_by	= new String();	//마지막수정자
	private String last_update_date = new String();	//마지막수정일
	
	public int getWork_seq() {
		return work_seq;
	}
	public void setWork_seq(int work_seq) {
		this.work_seq = work_seq;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getFr_work() {
		return fr_work;
	}
	public void setFr_work(String fr_work) {
		this.fr_work = fr_work;
	}
	public String getTo_work() {
		return to_work;
	}
	public void setTo_work(String to_work) {
		this.to_work = to_work;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getLast_update_by() {
		return last_update_by;
	}
	public void setLast_update_by(String last_update_by) {
		this.last_update_by = last_update_by;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	@Override
	public String toString() {
		return "Detailed_WorkVO [work_seq=" + work_seq + ", sector=" + sector + ", fr_work=" + fr_work + ", to_work="
				+ to_work + ", remark=" + remark + ", company_code=" + company_code + ", company_name=" + company_name
				+ ", created_by=" + created_by + ", created_date=" + created_date + ", last_update_by=" + last_update_by
				+ ", last_update_date=" + last_update_date + "]";
	}
	
}
