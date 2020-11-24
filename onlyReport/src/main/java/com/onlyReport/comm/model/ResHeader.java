package com.onlyReport.comm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *<pre>
 *매개변수 헤더
 *</pre>
 *
 *@ClassName : ResHeader.java 
 *@Description : 모든 매개변수의 헤더를 정의한 클레스입니다.
 *@author user
 *@since 2020. 11. 24
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 24   user   최초생성
 *</pre>
 */
public class ResHeader {

	@JsonProperty("reponseId")
	private int response_id = 0;	//헤더 아이디
	@JsonProperty("userId")
	private String user_id	= new String();	//유저 아이디
	
	public int getResponse_id() {
		return response_id;
	}
	public void setResponse_id(int response_id) {
		this.response_id = response_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ResHeader [response_id=" + response_id + ", user_id=" + user_id + "]";
	}

	
	
}
