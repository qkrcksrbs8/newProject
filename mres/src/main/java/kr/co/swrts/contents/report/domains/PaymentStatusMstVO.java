package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *하자보수관리VO
 *</pre>
 *
 *@ClassName : PaymentStatusMstVO.java 
 *@Description : 하자보수관리VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 16
 *@version 1.0
 *@see
 *@Modification Information
 */
public class PaymentStatusMstVO {

	private int payment_status_seq	= 0; 			//시퀀스
	private String product_name 	= new String(); //상품이름
	private String product_unit		= new String();	//단위 
	private String carried_forward	= new String();	//수량 
	private String enter 			= new String(); //입고
	private String exodus			= new String(); //출고
	private String this_month		= new String(); //금월재고 
	private String remark 			= new String();	//비고
	private String useflag			= "1";			//사용구분
	private String company_code 	= new String();	//매장코드
	private String created_by 		= new String();	//최초생성자
	private String created_date 	= new String();	//최초생성일
	private String last_update_by	= new String();	//마지막수정자
	private String last_update_date = new String();	//마지막수정일
	
	private String frThisYear		= new String(); //이번년도 시작일
	private String toThisYear		= new String(); //이번년도 종료일
	private String frLastYear		= new String(); //전년도 시작일
	private String toLastYear		= new String(); //전년도 종료일
	private String base_date		= new String();	//기준년도
	
	public int getPayment_status_seq() {
		return payment_status_seq;
	}


	public void setPayment_status_seq(int payment_status_seq) {
		this.payment_status_seq = payment_status_seq;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProduct_unit() {
		return product_unit;
	}


	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}


	public String getCarried_forward() {
		return carried_forward;
	}


	public void setCarried_forward(String carried_forward) {
		this.carried_forward = carried_forward;
	}


	public String getEnter() {
		return enter;
	}


	public void setEnter(String enter) {
		this.enter = enter;
	}


	public String getExodus() {
		return exodus;
	}


	public void setExodus(String exodus) {
		this.exodus = exodus;
	}


	public String getThis_month() {
		return this_month;
	}


	public void setThis_month(String this_month) {
		this.this_month = this_month;
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


	public String getFrThisYear() {
		return frThisYear;
	}


	public void setFrThisYear(String frThisYear) {
		this.frThisYear = frThisYear;
	}


	public String getToThisYear() {
		return toThisYear;
	}


	public void setToThisYear(String toThisYear) {
		this.toThisYear = toThisYear;
	}


	public String getFrLastYear() {
		return frLastYear;
	}


	public void setFrLastYear(String frLastYear) {
		this.frLastYear = frLastYear;
	}


	public String getToLastYear() {
		return toLastYear;
	}


	public void setToLastYear(String toLastYear) {
		this.toLastYear = toLastYear;
	}


	public String getBase_date() {
		return base_date;
	}


	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}


	@Override
	public String toString() {
		return "PaymentStatusMstVO [payment_status_seq=" + payment_status_seq + ", product_name=" + product_name
				+ ", product_unit=" + product_unit + ", carried_forward=" + carried_forward + ", enter=" + enter
				+ ", exodus=" + exodus + ", this_month=" + this_month + ", remark=" + remark + ", useflag=" + useflag
				+ ", company_code=" + company_code + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + ", frThisYear="
				+ frThisYear + ", toThisYear=" + toThisYear + ", frLastYear=" + frLastYear + ", toLastYear="
				+ toLastYear + ", base_date=" + base_date + "]";
	}
	
}
