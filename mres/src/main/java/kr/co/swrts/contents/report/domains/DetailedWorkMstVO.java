package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *세부업무실적VO
 *</pre>
 *
 *@ClassName : DetailedWorkMstVO.java 
 *@Description : 세부업무실적VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 7
 *@version 1.0
 *@see
 *@Modification Information
 */
public class DetailedWorkMstVO {

	private int work_seq 			= 0;			//세부업무실적번호
	private String sector 			= new String();	//부문
	private String work_date		= new String(); //기준년도
	private String fr_work 			= new String();	//예정업무
	private String to_work 			= new String();	//실시업무
	private String remark 			= new String();	//비고
	private String useflag			= new String(); //사용구분
	private String company_code 	= new String();	//매장코드
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
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
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
		return "DetailedWorkMstVO [work_seq=" + work_seq + ", sector=" + sector + ", work_date=" + work_date
				+ ", fr_work=" + fr_work + ", to_work=" + to_work + ", remark=" + remark + ", useflag=" + useflag
				+ ", company_code=" + company_code + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + "]";
	}
	
}
