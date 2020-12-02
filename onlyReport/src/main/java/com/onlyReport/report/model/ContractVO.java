package com.onlyReport.report.model;

/**
 *<pre>
 *�ֿ�����Ȳ VO
 *</pre>
 *
 *@ClassName : ContractVO.java 
 *@Description : �ֿ�����Ȳ�� VO�� ������ Ŭ����.
 *@author user
 *@since 2020. 12. 2
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 12. 2   user   ���ʻ���
 *</pre>
 */
public class ContractVO {
	
	private int contract_seq		= 0;			//�ֿ�����Ȳ��ȣ
	private String contract_details = new String();	//��೻��
	private String contract_company = new String();	//��ü��
	private String total_date		= new String();	//������+������
	private String fr_day			= new String();	//������
	private String to_day			= new String();	//������ 
	private String total_years 		= new String();	//����� + ��౸��
	private String contract_years	= new String();	//�����
	private String contract_division= new String();	//��౸��
	private double payment			= 0.0;			//���ݾ�
	private String remark 			= new String();	//���
	private String useflag			= new String(); //��뱸��
	private String company_code 	= new String();	//�����ڵ�
	private String company_name 	= new String();	//�����̸�
	private String created_by 		= new String();	//���ʻ�����
	private String created_date 	= new String();	//���ʻ�����
	private String last_update_by	= new String();	//������������
	private String last_update_date = new String();	//������������
	
	public int getContract_seq() {
		return contract_seq;
	}
	public void setContract_seq(int contract_seq) {
		this.contract_seq = contract_seq;
	}
	public String getContract_details() {
		return contract_details;
	}
	public void setContract_details(String contract_details) {
		this.contract_details = contract_details;
	}
	public String getContract_company() {
		return contract_company;
	}
	public void setContract_company(String contract_company) {
		this.contract_company = contract_company;
	}
	public String getTotal_date() {
		return total_date;
	}
	public void setTotal_date(String total_date) {
		this.total_date = total_date;
	}
	public String getFr_day() {
		return fr_day;
	}
	public void setFr_day(String fr_day) {
		this.fr_day = fr_day;
	}
	public String getTo_day() {
		return to_day;
	}
	public void setTo_day(String to_day) {
		this.to_day = to_day;
	}
	public String getTotal_years() {
		return total_years;
	}
	public void setTotal_years(String total_years) {
		this.total_years = total_years;
	}
	public String getContract_years() {
		return contract_years;
	}
	public void setContract_years(String contract_years) {
		this.contract_years = contract_years;
	}
	public String getContract_division() {
		return contract_division;
	}
	public void setContract_division(String contract_division) {
		this.contract_division = contract_division;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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
		return "ContractVO [contract_seq=" + contract_seq + ", contract_details=" + contract_details
				+ ", contract_company=" + contract_company + ", total_date=" + total_date + ", fr_day=" + fr_day
				+ ", to_day=" + to_day + ", total_years=" + total_years + ", contract_years=" + contract_years
				+ ", contract_division=" + contract_division + ", payment=" + payment + ", remark=" + remark
				+ ", useflag=" + useflag + ", company_code=" + company_code + ", company_name=" + company_name
				+ ", created_by=" + created_by + ", created_date=" + created_date + ", last_update_by=" + last_update_by
				+ ", last_update_date=" + last_update_date + "]";
	}
	
}
