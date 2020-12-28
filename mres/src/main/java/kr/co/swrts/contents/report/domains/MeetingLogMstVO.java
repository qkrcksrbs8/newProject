package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *관리단회의록VO
 *</pre>
 *
 *@ClassName : MeetingLogMstVO.java 
 *@Description : 관리단회의록VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 22
 *@version 1.0
 *@see
 *@Modification Information
 */
public class MeetingLogMstVO {

	private int meeting_log_seq 		= 0;			//관리단회의록번호
	private String meeting_log_date		= new String(); //기준년도
	private String meeting_log_progress	= new String(); //교육진행
	private String attend_count			= new String();	//참석자
	private String meeting_agenda		= new String(); //교육내용
	private int file_Seq				= 0;			//파일번호
	private String useflag				= new String(); //사용구분
	private String company_code 		= new String();	//매장코드
	private String created_by 			= new String();	//최초생성자
	private String created_date 		= new String();	//최초생성일
	private String last_update_by		= new String();	//마지막수정자
	private String last_update_date 	= new String();	//마지막수정일
	
	private String file_name			= new String();	//파일이름
	private String file_path			= new String(); //파일경로
	private String fr_training_date 	= new String(); //검색 시작일
	private String to_training_date 	= new String(); //검색 종료일
	
	public int getMeeting_log_seq() {
		return meeting_log_seq;
	}
	public void setMeeting_log_seq(int meeting_log_seq) {
		this.meeting_log_seq = meeting_log_seq;
	}
	public String getMeeting_log_date() {
		return meeting_log_date;
	}
	public void setMeeting_log_date(String meeting_log_date) {
		this.meeting_log_date = meeting_log_date;
	}
	public String getMeeting_log_progress() {
		return meeting_log_progress;
	}
	public void setMeeting_log_progress(String meeting_log_progress) {
		this.meeting_log_progress = meeting_log_progress;
	}
	public String getAttend_count() {
		return attend_count;
	}
	public void setAttend_count(String attend_count) {
		this.attend_count = attend_count;
	}
	public String getMeeting_agenda() {
		return meeting_agenda;
	}
	public void setMeeting_agenda(String meeting_agenda) {
		this.meeting_agenda = meeting_agenda;
	}
	public int getFile_Seq() {
		return file_Seq;
	}
	public void setFile_Seq(int file_Seq) {
		this.file_Seq = file_Seq;
	}
	public String getUseflag() {
		return useflag;
	}
	public void setUseflag(String useflag) {
		this.useflag = useflag;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
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
	public String getFr_training_date() {
		return fr_training_date;
	}
	public void setFr_training_date(String fr_training_date) {
		this.fr_training_date = fr_training_date;
	}
	public String getTo_training_date() {
		return to_training_date;
	}
	public void setTo_training_date(String to_training_date) {
		this.to_training_date = to_training_date;
	}
	
	@Override
	public String toString() {
		return "MeetingLogMstVO [meeting_log_seq=" + meeting_log_seq + ", meeting_log_date=" + meeting_log_date
				+ ", meeting_log_progress=" + meeting_log_progress + ", attend_count=" + attend_count
				+ ", meeting_agenda=" + meeting_agenda + ", file_Seq=" + file_Seq + ", useflag=" + useflag
				+ ", company_code=" + company_code + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + ", file_name="
				+ file_name + ", file_path=" + file_path + ", fr_training_date=" + fr_training_date
				+ ", to_training_date=" + to_training_date + "]";
	}
		
}
