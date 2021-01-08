package kr.co.swrts.contents.report.domains;

/**
 *<pre>
 *연간스케쥴VO
 *</pre>
 *
 *@ClassName : ScheduleMstVO.java 
 *@Description : 연간스케쥴VO
 *@author 박찬균 주임연구원
 *@since 2020. 12. 4
 *@version 1.0
 *@see
 *@Modification Information
 */
public class ScheduleMstVO {

	private int schedule_seq =			0;				//시퀀스
	private String division = 	 		new String();	//구분
	private String company_code = 		new String();	//사업장코드
	private String work_info = 			"";				//업무내용
	private String check_cycle = 		"0회/년";			//점검주기
	private String schedule_jan = 		"0";			//1월
	private String schedule_feb = 		"0";			//2월
	private String schedule_mar = 		"0";			//3월
	private String schedule_apr = 		"0";			//4월
	private String schedule_may = 		"0";			//5월
	private String schedule_jun = 		"0";			//6월
	private String schedule_jul = 		"0";			//7월
	private String schedule_aug = 		"0";			//8월
	private String schedule_sep = 		"0";			//9월
	private String schedule_oct = 		"0";			//10월
	private String schedule_nov = 		"0";			//11월
	private String schedule_dec = 		"0";			//12월
	private String entity 		= 		new String();	//관리주체
	private String contract 	=		new String();	//계약서
	private String file_name 	=		"파일업로드"; 		//파일이름
	private int file_seq 		=		0;				//파일시퀀스자
	private String useflag = 			"1";			//사용구분
	private String created_date = 		new String();	//최초생성일
	private String created_by = 		new String();	//최초생성자
	private String last_update_date = 	new String();	//최종수정일
	private String last_update_by = 	new String();	//최종수정일
	private String base_date	= 		new String();	//기준년도

	public int getSchedule_seq() {
		return schedule_seq;
	}

	public void setSchedule_seq(int schedule_seq) {
		this.schedule_seq = schedule_seq;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getWork_info() {
		return work_info;
	}

	public void setWork_info(String work_info) {
		this.work_info = work_info;
	}

	public String getCheck_cycle() {
		return check_cycle;
	}

	public void setCheck_cycle(String check_cycle) {
		this.check_cycle = check_cycle;
	}

	public String getSchedule_jan() {
		return schedule_jan;
	}

	public void setSchedule_jan(String schedule_jan) {
		this.schedule_jan = schedule_jan;
	}

	public String getSchedule_feb() {
		return schedule_feb;
	}

	public void setSchedule_feb(String schedule_feb) {
		this.schedule_feb = schedule_feb;
	}

	public String getSchedule_mar() {
		return schedule_mar;
	}

	public void setSchedule_mar(String schedule_mar) {
		this.schedule_mar = schedule_mar;
	}

	public String getSchedule_apr() {
		return schedule_apr;
	}

	public void setSchedule_apr(String schedule_apr) {
		this.schedule_apr = schedule_apr;
	}

	public String getSchedule_may() {
		return schedule_may;
	}

	public void setSchedule_may(String schedule_may) {
		this.schedule_may = schedule_may;
	}

	public String getSchedule_jun() {
		return schedule_jun;
	}

	public void setSchedule_jun(String schedule_jun) {
		this.schedule_jun = schedule_jun;
	}

	public String getSchedule_jul() {
		return schedule_jul;
	}

	public void setSchedule_jul(String schedule_jul) {
		this.schedule_jul = schedule_jul;
	}

	public String getSchedule_aug() {
		return schedule_aug;
	}

	public void setSchedule_aug(String schedule_aug) {
		this.schedule_aug = schedule_aug;
	}

	public String getSchedule_sep() {
		return schedule_sep;
	}

	public void setSchedule_sep(String schedule_sep) {
		this.schedule_sep = schedule_sep;
	}

	public String getSchedule_oct() {
		return schedule_oct;
	}

	public void setSchedule_oct(String schedule_oct) {
		this.schedule_oct = schedule_oct;
	}

	public String getSchedule_nov() {
		return schedule_nov;
	}

	public void setSchedule_nov(String schedule_nov) {
		this.schedule_nov = schedule_nov;
	}

	public String getSchedule_dec() {
		return schedule_dec;
	}

	public void setSchedule_dec(String schedule_dec) {
		this.schedule_dec = schedule_dec;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getFile_seq() {
		return file_seq;
	}

	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
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

	public String getBase_date() {
		return base_date;
	}

	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}

	@Override
	public String toString() {
		return "ScheduleMstVO [schedule_seq=" + schedule_seq + ", division=" + division + ", company_code="
				+ company_code + ", work_info=" + work_info + ", check_cycle=" + check_cycle + ", schedule_jan="
				+ schedule_jan + ", schedule_feb=" + schedule_feb + ", schedule_mar=" + schedule_mar + ", schedule_apr="
				+ schedule_apr + ", schedule_may=" + schedule_may + ", schedule_jun=" + schedule_jun + ", schedule_jul="
				+ schedule_jul + ", schedule_aug=" + schedule_aug + ", schedule_sep=" + schedule_sep + ", schedule_oct="
				+ schedule_oct + ", schedule_nov=" + schedule_nov + ", schedule_dec=" + schedule_dec + ", entity="
				+ entity + ", contract=" + contract + ", file_name=" + file_name + ", file_seq=" + file_seq
				+ ", useflag=" + useflag + ", created_date=" + created_date + ", created_by=" + created_by
				+ ", last_update_date=" + last_update_date + ", last_update_by=" + last_update_by + ", base_date="
				+ base_date + "]";
	}
	
}
