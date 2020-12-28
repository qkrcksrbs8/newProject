package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *승강기안전점검표 상세내용VO
 *</pre>
 *
 *@ClassName : LiftContentMstVO.java 
 *@Description : 승강기안전점검표 상세내용 VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 18
 *@version 1.0
 *@see
 *@Modification Information
 */
public class LiftContentMstVO {

	private int lift_content_seq	= 0; 			//시퀀스
	private int lift_seq			= 0; 			//liftMst의 시퀀스
	private String division 		= new String(); //점검표구분
	private String item_check		= new String();	//점검항목
	private String result_check		= new String();	//점검결과
	private String fr_work 			= new String(); //현지시정
	private String to_work			= new String(); //조치계획
	private String schedule_date	= new String(); //예정일 
	private String remark 			= new String();	//비고
	private String useflag			= "1";			//사용구분
	private String company_code 	= new String();	//매장코드
	private String created_by 		= new String();	//최초생성자
	private String created_date 	= new String();	//최초생성일
	private String last_update_by	= new String();	//마지막수정자
	private String last_update_date = new String();	//마지막수정일
	
	public int getLift_content_seq() {
		return lift_content_seq;
	}
	public void setLift_content_seq(int lift_content_seq) {
		this.lift_content_seq = lift_content_seq;
	}
	public int getLift_seq() {
		return lift_seq;
	}
	public void setLift_seq(int lift_seq) {
		this.lift_seq = lift_seq;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getItem_check() {
		return item_check;
	}
	public void setItem_check(String item_check) {
		this.item_check = item_check;
	}
	public String getResult_check() {
		return result_check;
	}
	public void setResult_check(String result_check) {
		this.result_check = result_check;
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
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	@Override
	public String toString() {
		return "LiftContentMstVO [lift_content_seq=" + lift_content_seq + ", lift_seq=" + lift_seq + ", division="
				+ division + ", item_check=" + item_check + ", result_check=" + result_check + ", fr_work=" + fr_work
				+ ", to_work=" + to_work + ", schedule_date=" + schedule_date + ", remark=" + remark + ", useflag="
				+ useflag + ", company_code=" + company_code + ", created_by=" + created_by + ", created_date="
				+ created_date + ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + "]";
	}
	
}
