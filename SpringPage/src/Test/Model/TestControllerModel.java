package Test.Model;

import java.sql.Timestamp;

public class TestControllerModel {
	private int user_no				= 0;	/* 유저번호		*/
	private String user_id			= "";	/* 유저아이디		*/
	private String user_password	= "";	/* 유저비밀번호		*/
	private String user_name		= ""; 	/* 유저이름		*/
	private String user_phone		= "";	/* 유저전화번호		*/ 
	private String created_by		= ""; 	/* 생성자			*/
	private Timestamp created_date; 		/* 생성일시		*/
	private String last_update_by	= "";	/* 마지막 수정자		*/
	private String last_update_date;		/* 마지막 수정일시	*/
	private int cnt					= 0;	/* 로그인 체크 카운트	*/
	private int fail_cnt			= 0;	/* 로그인 실패 카운트	*/
	private Timestamp list_login_date;		/* 마지막 로그인 일시	*/
	private String login_result		= "";	/* 로그인결과		*/
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getFail_cnt() {
		return fail_cnt;
	}
	public void setFail_cnt(int fail_cnt) {
		this.fail_cnt = fail_cnt;
	}
	public Timestamp getList_login_date() {
		return list_login_date;
	}
	public void setList_login_date(Timestamp list_login_date) {
		this.list_login_date = list_login_date;
	}
	public String getLogin_result() {
		return login_result;
	}
	public void setLogin_result(String login_result) {
		this.login_result = login_result;
	}
	
}
