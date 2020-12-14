package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *하자보수관리VO
 *</pre>
 *
 *@ClassName : DetailedWorkMstVO.java 
 *@Description : 하자보수관리VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 10
 *@version 1.0
 *@see
 *@Modification Information
 */
public class RepairMstVO {

	private int repair_seq 			= 0;			//하자보수관리번호
	private String fr_work 			= new String();	//예정업무
	private String to_work 			= new String();	//실시업무
	private String fr_img_id 		= new String();	//예정업무 이미지
	private String fr_img_path		= new String();	//예정업무 이미지 경로
	private String to_img_id 		= new String();	//실시업무 이미지
	private String to_img_path		= new String();	//실시업무 이미지 경로
	private String remark 			= new String();	//비고
	private String useflag			= new String(); //사용구분
	private String company_code 	= new String();	//매장코드
	private String created_by 		= new String();	//최초생성자
	private String created_date 	= new String();	//최초생성일
	private String last_update_by	= new String();	//마지막수정자
	private String last_update_date = new String();	//마지막수정일
	private String fr_cal			= new String();	//날짜 검색 시작일
	private String to_cal			= new String(); //날짜 검색 종료일
	
	public int getRepair_seq() {
		return repair_seq;
	}
	public void setRepair_seq(int repair_seq) {
		this.repair_seq = repair_seq;
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
	public String getFr_img_id() {
		return fr_img_id;
	}
	public void setFr_img_id(String fr_img_id) {
		this.fr_img_id = fr_img_id;
	}
	public String getFr_img_path() {
		return fr_img_path;
	}
	public void setFr_img_path(String fr_img_path) {
		this.fr_img_path = fr_img_path;
	}
	public String getTo_img_id() {
		return to_img_id;
	}
	public void setTo_img_id(String to_img_id) {
		this.to_img_id = to_img_id;
	}
	public String getTo_img_path() {
		return to_img_path;
	}
	public void setTo_img_path(String to_img_path) {
		this.to_img_path = to_img_path;
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
	public String getFr_cal() {
		return fr_cal;
	}
	public void setFr_cal(String fr_cal) {
		this.fr_cal = fr_cal;
	}
	public String getTo_cal() {
		return to_cal;
	}
	public void setTo_cal(String to_cal) {
		this.to_cal = to_cal;
	}
	
	@Override
	public String toString() {
		return "RepairMstVO [repair_seq=" + repair_seq + ", fr_work=" + fr_work + ", to_work=" + to_work
				+ ", fr_img_id=" + fr_img_id + ", fr_img_path=" + fr_img_path + ", to_img_id=" + to_img_id
				+ ", to_img_path=" + to_img_path + ", remark=" + remark + ", useflag=" + useflag + ", company_code="
				+ company_code + ", created_by=" + created_by + ", created_date=" + created_date + ", last_update_by="
				+ last_update_by + ", last_update_date=" + last_update_date + ", fr_cal=" + fr_cal + ", to_cal="
				+ to_cal + "]";
	}
	
}
