package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *교육현황VO
 *</pre>
 *
 *@ClassName : DetailedWorkMstVO.java 
 *@Description : 교육현황VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 15
 *@version 1.0
 *@see
 *@Modification Information
 */
public class FileMstVO {

	private int file_seq 			= 0;			//파일 번호
	private String file_date		= new String();	//업로드 일자
	private String file_content 	= new String();	//파일 내용
	private String file_name 		= new String();	//파일 이름
	private String file_path 		= new String();	//파일 경로
	private long file_size			= 0;			//파일 용량
	private String table_name		= new String(); //파일을 저장한 테이블 이름 ex.(schedule_mst)
	private int table_seq			= 0;			 //각 테이블의 시퀀스 ex. schedule_seq 정보
	private String useflag 			= new String();	//사용구분
	private String created_date		= new String();	//최초생성일
	private String created_by 		= new String();	//최초생성자
	private String last_update_date	= new String();	//최종수정일
	private String last_update_by	= new String();	//최종수정자
	
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public String getFile_date() {
		return file_date;
	}
	public void setFile_date(String file_date) {
		this.file_date = file_date;
	}
	public String getFile_content() {
		return file_content;
	}
	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getTable_seq() {
		return table_seq;
	}
	public void setTable_seq(int table_seq) {
		this.table_seq = table_seq;
	}
	public String getUseflag() {
		return useflag;
	}
	public void setUseflag(String useflag) {
		this.useflag = useflag;
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
		return "FileMstVO [file_seq=" + file_seq + ", file_date=" + file_date + ", file_content=" + file_content
				+ ", file_name=" + file_name + ", file_path=" + file_path + ", file_size=" + file_size + ", table_name="
				+ table_name + ", table_seq=" + table_seq + ", useflag=" + useflag + ", created_date=" + created_date
				+ ", created_by=" + created_by + ", last_update_date=" + last_update_date + ", last_update_by="
				+ last_update_by + "]";
	}
	
}
