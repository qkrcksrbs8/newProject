package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *교육현황VO
 *</pre>
 *
 *@ClassName : TrainingMstVO.java 
 *@Description : 교육현황VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 15
 *@version 1.0
 *@see
 *@Modification Information
 */
public class TrainingMstVO {

	private int training_seq 		= 0;			//교육현황번호
	private String division			= new String(); //구분. 미화/보안:TM01, 전기/안전:TM02, 기타:TM03
	private String training_date	= new String(); //기준년도
	private String training_progress= new String();	//교육진행
	private String attend_count		= new String(); //참석인원
	private String training_content	= new String(); //교육내용
	private String company_code 	= new String();	//매장코드
	private String created_by 		= new String();	//최초생성자
	private String created_date 	= new String();	//최초생성일
	private String last_update_by	= new String();	//마지막수정자
	private String last_update_date = new String();	//마지막수정일
	
	private String fr_training_date = new String(); //검색 시작일
	private String to_training_date = new String(); //검색 종료일
	
	public int getTraining_seq() {
		return training_seq;
	}
	public void setTraining_seq(int training_seq) {
		this.training_seq = training_seq;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getTraining_date() {
		return training_date;
	}
	public void setTraining_date(String training_date) {
		this.training_date = training_date;
	}
	public String getTraining_progress() {
		return training_progress;
	}
	public void setTraining_progress(String training_progress) {
		this.training_progress = training_progress;
	}
	public String getAttend_count() {
		return attend_count;
	}
	public void setAttend_count(String attend_count) {
		this.attend_count = attend_count;
	}
	public String getTraining_content() {
		return training_content;
	}
	public void setTraining_content(String training_content) {
		this.training_content = training_content;
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
		return "TrainingMstVO [training_seq=" + training_seq + ", division=" + division + ", training_date="
				+ training_date + ", training_progress=" + training_progress + ", attend_count=" + attend_count
				+ ", training_content=" + training_content + ", company_code=" + company_code + ", created_by="
				+ created_by + ", created_date=" + created_date + ", last_update_by=" + last_update_by
				+ ", last_update_date=" + last_update_date + ", fr_training_date=" + fr_training_date
				+ ", to_training_date=" + to_training_date + "]";
	}
	
}
