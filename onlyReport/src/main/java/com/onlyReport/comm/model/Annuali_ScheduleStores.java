package com.onlyReport.comm.model;

import java.util.ArrayList;
import java.util.List;

import com.onlyReport.report.model.Annuail_ScheduleVO;

/**
 *<pre>
 *���������� �����
 *</pre>
 *
 *@ClassName : Annuali_ScheduleStores.java 
 *@Description : ���������쿡 ���� ������ ���Դϴ�.
 *@author user
 *@since 2020. 11. 24
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 24   user   ���ʻ���
 *</pre>
 */
public class Annuali_ScheduleStores {

	private ResHeader resHeader = new ResHeader();	//�Ű����� ���
	private List<Annuail_ScheduleVO> annuail_ScheduleVO = new ArrayList<Annuail_ScheduleVO>();	//���������� ����Ʈ
	
	public ResHeader getResHeader() {
		return resHeader;
	}
	public void setResHeader(ResHeader resHeader) {
		this.resHeader = resHeader;
	}
	public List<Annuail_ScheduleVO> getAnnuail_ScheduleVO() {
		return annuail_ScheduleVO;
	}
	public void setAnnuail_ScheduleVO(List<Annuail_ScheduleVO> annuail_ScheduleVO) {
		this.annuail_ScheduleVO = annuail_ScheduleVO;
	}
	
	@Override
	public String toString() {
		return "Annuali_ScheduleStores [resHeader=" + resHeader + ", annuail_ScheduleVO=" + annuail_ScheduleVO + "]";
	}
	
	
}
