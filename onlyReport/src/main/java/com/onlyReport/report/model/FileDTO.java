package com.onlyReport.report.model;

import org.springframework.web.multipart.MultipartFile;

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
public class FileDTO {
	  private String name, pwd, title, content, fileName;
	  private MultipartFile uploadfile;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	@Override
	public String toString() {
		return "FileDTO [name=" + name + ", pwd=" + pwd + ", title=" + title + ", content=" + content + ", fileName="
				+ fileName + ", uploadfile=" + uploadfile + "]";
	}
	 
	
	}

