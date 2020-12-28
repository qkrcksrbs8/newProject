package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *하자보수관리VO
 *</pre>
 *
 *@ClassName : LiftMstVO.java 
 *@Description : 승강기안전점검표VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 18
 *@version 1.0
 *@see
 *@Modification Information
 */
public class LiftMstVO {

	private int lift_seq	= 0; 			//시퀀스
	private String inspection_division 	= new String(); //1:승강기 2:화재예방
	private String inspection_field 	= new String(); //점검분야
	private String building_name		= new String();	//건물명
	private String inspection_company	= new String();	//점검업체
	private String main_manager 		= new String(); //담당자
	private String sub_manager			= new String(); //담당자부
	private String inspection_date		= new String(); //점검일자 
	private String remark 				= new String();	//비고
	private String useflag				= "1";			//사용구분
	private String company_code 		= new String();	//매장코드
	private String created_by 			= new String();	//최초생성자
	private String created_date 		= new String();	//최초생성일
	private String last_update_by		= new String();	//마지막수정자
	private String last_update_date 	= new String();	//마지막수정일
	
	public int getLift_seq() {
		return lift_seq;
	}
	public void setLift_seq(int lift_seq) {
		this.lift_seq = lift_seq;
	}
	public String getInspection_division() {
		return inspection_division;
	}
	public void setInspection_division(String inspection_division) {
		this.inspection_division = inspection_division;
	}
	public String getInspection_field() {
		return inspection_field;
	}
	public void setInspection_field(String inspection_field) {
		this.inspection_field = inspection_field;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getInspection_company() {
		return inspection_company;
	}
	public void setInspection_company(String inspection_company) {
		this.inspection_company = inspection_company;
	}
	public String getMain_manager() {
		return main_manager;
	}
	public void setMain_manager(String main_manager) {
		this.main_manager = main_manager;
	}
	public String getSub_manager() {
		return sub_manager;
	}
	public void setSub_manager(String sub_manager) {
		this.sub_manager = sub_manager;
	}
	public String getInspection_date() {
		return inspection_date;
	}
	public void setInspection_date(String inspection_date) {
		this.inspection_date = inspection_date;
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
		return "LiftMstVO [lift_seq=" + lift_seq + ", inspection_division=" + inspection_division
				+ ", inspection_field=" + inspection_field + ", building_name=" + building_name
				+ ", inspection_company=" + inspection_company + ", main_manager=" + main_manager + ", sub_manager="
				+ sub_manager + ", inspection_date=" + inspection_date + ", remark=" + remark + ", useflag=" + useflag
				+ ", company_code=" + company_code + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + "]";
	}
	
}
