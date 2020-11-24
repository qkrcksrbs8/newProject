package com.onlyReport.report.model;

/**
*
*<pre>
*Statements
*</pre>
*
*@ClassName : tableTestVO.java
*@Description : 테이블을 리스트로 출력하기 위한 테스트 VO입니다.
*@author qkrck
*@since 2020. 11. 15.
*@version 1.0
*@see
*@Modification Information
*<pre>
* since			: 2020. 11. 15.
* author		: qkrck
* description	: 최초생성
*</pre>
*/
public class Annuail_ScheduleVO {
	
	private String job_content = new String();	//업무내용
	private int schedule_cycle = 0; 			//점검주기
	private int month1 = 0;						//1월
	private int month2 = 0;						//2월
	private int month3 = 0;						//3월
	private int month4 = 0;						//4월
	private int month5 = 0;						//5월
	private int month6 = 0;						//6월
	private int month7 = 0;						//7월
	private int month8 = 0;						//8월
	private int month9 = 0;						//9월
	private int month10 = 0;					//10월
	private int month11 = 0;					//11월
	private int month12 = 0;					//12월
	private String entity = new String();		//관리주체
	private String file_name = new String(); 	//파일
	
	public String getJob_content() {
		return job_content;
	}
	public void setJob_content(String job_content) {
		this.job_content = job_content;
	}
	public int getSchedule_cycle() {
		return schedule_cycle;
	}
	public void setSchedule_cycle(int schedule_cycle) {
		this.schedule_cycle = schedule_cycle;
	}
	public int getMonth1() {
		return month1;
	}
	public void setMonth1(int month1) {
		this.month1 = month1;
	}
	public int getMonth2() {
		return month2;
	}
	public void setMonth2(int month2) {
		this.month2 = month2;
	}
	public int getMonth3() {
		return month3;
	}
	public void setMonth3(int month3) {
		this.month3 = month3;
	}
	public int getMonth4() {
		return month4;
	}
	public void setMonth4(int month4) {
		this.month4 = month4;
	}
	public int getMonth5() {
		return month5;
	}
	public void setMonth5(int month5) {
		this.month5 = month5;
	}
	public int getMonth6() {
		return month6;
	}
	public void setMonth6(int month6) {
		this.month6 = month6;
	}
	public int getMonth7() {
		return month7;
	}
	public void setMonth7(int month7) {
		this.month7 = month7;
	}
	public int getMonth8() {
		return month8;
	}
	public void setMonth8(int month8) {
		this.month8 = month8;
	}
	public int getMonth9() {
		return month9;
	}
	public void setMonth9(int month9) {
		this.month9 = month9;
	}
	public int getMonth10() {
		return month10;
	}
	public void setMonth10(int month10) {
		this.month10 = month10;
	}
	public int getMonth11() {
		return month11;
	}
	public void setMonth11(int month11) {
		this.month11 = month11;
	}
	public int getMonth12() {
		return month12;
	}
	public void setMonth12(int month12) {
		this.month12 = month12;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	@Override
	public String toString() {
		return "Annuail_ScheduleVO [job_content=" + job_content + ", schedule_cycle=" + schedule_cycle + ", month1=" + month1
				+ ", month2=" + month2 + ", month3=" + month3 + ", month4=" + month4 + ", month5=" + month5
				+ ", month6=" + month6 + ", month7=" + month7 + ", month8=" + month8 + ", month9=" + month9
				+ ", month10=" + month10 + ", month11=" + month11 + ", month12=" + month12 + ", entity=" + entity
				+ ", file_name=" + file_name + "]";
	}
	
	

}
