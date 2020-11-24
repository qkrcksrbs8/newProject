package com.onlyReport.comm.model;

import java.util.ArrayList;
import java.util.List;

import com.onlyReport.report.model.Annuail_ScheduleVO;

/**
 *<pre>
 *연간스케쥴 스토어
 *</pre>
 *
 *@ClassName : Annuali_ScheduleStores.java 
 *@Description : 연간스케쥴에 대해 정의한 모델입니다.
 *@author user
 *@since 2020. 11. 24
 *@version 1.0
 *@see
 *@Modification Information
 *<pre>
 *2020. 11. 24   user   최초생성
 *</pre>
 */
public class Annuali_ScheduleStores {

	private ResHeader resHeader = new ResHeader();	//매개변수 헤더
	private List<Annuail_ScheduleVO> annuail_ScheduleVO = new ArrayList<Annuail_ScheduleVO>();	//연간스케쥴 리스트
	
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
