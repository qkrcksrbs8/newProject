<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres07/mres0707.js"></script>

<iframe id="print0707_1" name="print0707_1" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<form name="frmPrint1" action="<%=request.getContextPath()%>/print/0707_1" method="POST">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="purchaseDt" value="" />
	<input type="hidden" name="imposeDt" value="" />
	<input type="hidden" name="companyName" value="" />
</form>
<iframe id="print0707_2" name="print0707_2" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<form name="frmPrint2" action="<%=request.getContextPath()%>/print/0707_2" method="POST">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="purchaseDt" value="" />
	<input type="hidden" name="imposeDt" value="" />
	<input type="hidden" name="companyName" value="" />
</form>

<div class="search_wrap">
	<div class="search_con">
		<div class="search_title">회계기수</div>
		<div>
			<select class="default_select w70" id="selAccountNum" name="selAccountNum" style="min-width: auto;">
				<option value="2">2</option>
			</select>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">회계년월</div>
		<div>
			<div class="cal_wrap">
				<input type="text" class="date_input" id="accountDt" name="accountDt">
				<p class="cal_icon">
					<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
				</p>
			</div>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">부과년월</div>
		<div>
			<div class="cal_wrap">
				<input type="text" class="date_input" id="imposeDt" name="imposeDt">
				<p class="cal_icon">
					<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
				</p>
			</div>
		</div>
	</div>
	<div class="right">
		<a href="#;" class="basin_btn" onclick="searchBtn()">조회 </a>
	</div>
</div>

<div class="search_wrap">
	<div class="con_wrap con_wrap_60 padding_r_10">
		<div class="search_con">
			<div class="search_title">아파트고유번호</div>
			<div>
				<input type="text" class="default_input w120" id="apartmentNum" name="apartmentNum">
			</div>
		</div>
		<div class="search_bar"></div>
		<div class="search_con">
			<div class="search_title">비용 등</div>
			<div>
				<input type="text" placeholder="" class="default_input w130" id="totalPrice1" name="totalPrice1" readonly="readonly">
			</div>
		</div>
		<div class="search_bar"></div>
		<div class="search_con">
			<div class="search_title">잡수입</div>
			<div>
				<input type="text" placeholder="" class="default_input w130" id="totalPrice2" name="totalPrice2" readonly="readonly">
			</div>
		</div>
	</div>	
	<div class="con_wrap con_wrap_40 padding_l_10">
		<div class="button_wrap">
			<a href="#;" class="basin_btn" onclick="dataTrans()">전송</a>
			<a href="#;" class="basin_btn" onclick="execPrintChk()">인쇄</a>
		</div>
	</div>
</div>

<div class="margin_t_10 con_wrap con_wrap_100">
	<form id="dataForm">
		<nav class="nav_tabs">
			<ul>
				<li>
					<input type="radio" name="tabs" class="rd_tabs" id="tab1" checked> 
					<label for="tab1">공용관리비</label>
					<div class="tab_con">
						<article>
							<table class="view_top_center_table">
								<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="50%"/>     
								</colgroup>
								<tr>
									<th>구분</th>
									<th>대항목</th>
									<th>중항목</th>
									<th>세항목</th>
									<th>금액</th>
									<th>설명</th>
								</tr>
								<tr>
									<td rowspan="35">
										공용관리비
									</td>
									<td rowspan="25">
										일반관리비
									</td>
									<td rowspan="9">
										인건비
									</td>
									<td class="align_l">
										1.급여
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110101" name="pay" readonly="readonly">
									</td>
									<td class="align_l">
										미화원과 경비원을 제외한 관리사무소 직원에 대해 급여 지급기준에 의해 지급하는 급여를 말한다.<br/>
										☞ 주의사항 : 경비원의 급여는 경비비에 포함되고 청소원의 급여는 청소비에 포함된다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										2.제수당
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110102" name="sundry_cost" readonly="readonly">
									</td>
									<td class="align_l">
										제수당이란 급여지급 기준에 의거하여 발생하는 인건 비중 기본급여 이외의 모든 개별수당을 총칭한다.<br/>
										실무에서는 자격수당(주택관리사, 전기기사, 소방안전관리관리자, 방화관리자 등), 직책수당, 근속수당, 회계담당수당(출납수당), 야간근무수당, 휴일근무수당, 관리사무소장에게 매월 지급되는 업무추진비 등이 있다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										3.상여금
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110103" name="bonus" readonly="readonly">
									</td>
									<td class="align_l">
										정기적으로 지급하는 상여금과 특별성과에 지급하는 특별 상여금을 말한다.<br/>
										☞ 주의사항 : 실무에서 많이 사용하는 명절 떡값, 하계 휴가비는 복리후생비로 분류한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										4.퇴직금
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110104" name="pension" readonly="readonly">
									</td>
									<td class="align_l">
										근로기준법에 의거하여 직원 퇴직 시 지급될 퇴직급여충당금상당액을 계산하여 이를 월할 안분하여 충당금으로 설정하는 경우에 발생하는 비용 계산액을 말한다. 
									</td>
								</tr>
								<tr>
									<td class="align_l">
										5.산재보험료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110105" name="accident_premium" readonly="readonly">
									</td>
									<td class="align_l">
										산재보험료를 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										6.고용보험료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110106" name="employ_premium" readonly="readonly">
									</td>
									<td class="align_l">
										고용보험료 중 사업자분을 말한다
									</td>
								</tr>
								<tr>
									<td class="align_l">
										7.국민연금
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110107" name="national_pension" readonly="readonly">
									</td>
									<td class="align_l">
										국민연금 중 사업자분을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										8.국민건강보험료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110108" name="health_premium" readonly="readonly">
									</td>
									<td class="align_l">
										국민건강보험료 중 사업자분을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										9.식대 등 복기후생비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r" id="110109" name="welfare_benefit" readonly="readonly">
									</td>
									<td class="align_l">
										관리사무소 직원의 근로환경 개선과 근무의욕의 향상 등을 위해 지출하는 비용 등을 말한다.<br/>
										복리후생비에는 식대, 회식비, 경조회비, 체력단련비, 명절 떡값, 하계 휴가비 등이 있다.
									</td>
								</tr>
								<tr>
									<td rowspan="3">
										제사무비
									</td>
									<td class="align_l">
										10.일반사무용품비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110201" name="office_supply" readonly="readonly">
									</td>
									<td class="align_l">
										관리사무소에서 사무에 직접 소모되는 복사 용지, 문구류 등을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										11.도서인쇄비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110202" name="book_supply" readonly="readonly">
									</td>
									<td class="align_l">
										전산 프로그램(회계 프로그램, 관리비 고지서 인쇄 등 포함) 사용료, 인쇄비, 신문구독료, 도서구입비, 인장제작비, 사진현상비, 복사비 등이 있다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										12.교통통신비 등
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110203" name="transport_cost" readonly="readonly">
									</td>
									<td class="align_l">
										관리사무소 업무 수행을 위해 외부 출장 시 지급된 여비와 교통비 발생액을 말하며, 일반적으로는 대중교통비 등이 해당한다.<br/>
										☞  주의사항 : 개인차량을 업무용으로 이용한 경우에는 주차비와 연료비 상당액 등을 교통통신비(여비교통비)로 처리가능하다. 통신비는 여비교통비로 처리하지 않고 제세공과금의 통신료로 처리
									</td>
								</tr>
								<tr>
									<td rowspan="4">
										제세공과금
									</td>
									<td class="align_l">
										13.전기료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110301" name="elec_cost" readonly="readonly">
									</td>
									<td class="align_l">
										공동전기료에 포함되지 않는 전기료 등을 의미하나 실무상으로는 거의 없는 것으로 파악.<br/>
										☞   주의사항 : 공동 전기료에 포함되는 경우에는 관리비가 아니라 사용료 중 전기료(공동으로 사용되는 시설의 전기료를 포함한다)로 처리.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										14.통신료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110302" name="comunication_cost" readonly="readonly">
									</td>
									<td class="align_l">
										미화원과 경비원을 제외한 관리사무소 직원에 대해 급여 지급기준에 의해 지급하는 급여를 말한다.<br/>
										☞ 주의사항 : 경비원의 급여는 경비비에 포함되고 청소원의 급여는 청소비에 포함된다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										15.우편료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110303" name="poatage_cost" readonly="readonly">
									</td>
									<td class="align_l">
										관리기구에서 사용한 우편등기료, 택배비 등을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										16.세금등
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110304" name="tax" readonly="readonly">
									</td>
									<td class="align_l">
										세금, 공과금, 기타 : 세금, 공과금, 노인정의 개별 난방비 등 기타사항.<br/>
										☞ 주의사항 : 노인정 개별난방비는 관리소 지원이면 포함, 아니면 개별 사용료 처리.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="2">
										17.피복비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110401" name="clothes_cost" readonly="readonly">
									</td>
									<td class="align_l">
										피복비란 관리업무 수행을 위하여 동절기와 하절기용 근무복과 작업복 등을 구입하는 경우 소요되는 비용을 말한다.<br/>
										☞  주의사항 : 미화원과 경비원의 피복비는 포함하지 않음(청소비와 경비비에 각각 포함)
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="2">
										18.교육훈련비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110501" name="edu_cost" readonly="readonly">
									</td>
									<td class="align_l">
										교육훈련비란 관리사무소 직원에 대한 법정교육 참가비 및 관리효율, 관리비 절감 등을 위한 직무향상 교육 등에 소용되는 비용을 말한다.
									</td>
								</tr>
								<tr>
									<td rowspan="4">
										차량유지비
									</td>
									<td class="align_l">
										19.연료비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110601" name="fuel_cost" readonly="readonly">
									</td>
									<td class="align_l">
										관리기구에서 운영하는 차량의 연료비를 말한다.<br/>
										☞  주의사항 : 비상발전기에 사용되는 연료비는 수선유지비로 분류하는 것이 타당.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										20.수리비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110602" name="refair_cost" readonly="readonly">
									</td>
									<td class="align_l">
										관리기구에서 운영하는 차량의 수리비를 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										21.보험료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110603" name="car_insurance" readonly="readonly">
									</td>
									<td class="align_l">
										관리기구에서 운영하는 차량의 보험료를 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										22.차량유지에 직접소요되는 비용
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110604" name="car_etc" readonly="readonly">
									</td>
									<td class="align_l">
										관리기구에서 운영하는 차량에 발생하는 기타 모든 비용을 말한다.
									</td>
								</tr>
								<tr>
									<td rowspan="3">
										그 밖의 부대비용
									</td>
									<td class="align_l">
										23.관리용품구입
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110701" name="care_item_cost" readonly="readonly">
									</td>
									<td class="align_l">
										소모성 용품비나 부품의 구입비용을 통칭한다.<br/>
										☞  주의사항 : 실무에서는 제사무비의 일반사무용품비와 구분이 모호할 때가 많다. 그 구분은 사용의 주체가 관리실이면 일반사무용품비로, 기계전기실 또는 경비실이면 관리용품구입비로 구분하는 것이 타당하다.
									</td>
								</tr>
								<tr>
									<td class="align_l">
										24.회계감사비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110702" name="accounting_cost" readonly="readonly">
									</td>
									<td class="align_l">
										회계감사비, 변호사·법무사·노무사 수임료 등 전문가의 자문(감사) 비용
									</td>
								</tr>
								<tr>
									<td class="align_l">
										25.그 밖에 관리업무에 소요되는 비용
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="110703" name="hidden_cost" readonly="readonly">
									</td>
									<td class="align_l">
										송금수수료, 인지대 등 기타 발생하는 지출을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										26.청소비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="120001" name="clean_cost" readonly="readonly">
									</td>
									<td class="align_l">
										청소비란 용역 시에는 용역업체와 계약된 금액, 직영 시에는 청소원의 인건비, 피복비, 청소용품비 등 청소작업에 직접 소요되는 경비를 말한다.<br/>
										☞  주의사항 : 직영으로 청소업무를 수행하는 단지의 경우, 청소원에 대한 4대 보험의 사업주 부담분 등은 청소비에 포함한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										27.경비비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="120002" name="guard_cost" readonly="readonly">
									</td>
									<td class="align_l">
										경비비란 공동주택 단지의 공용부분에 대한 경비업무 수행 시 필요한 비용으로, 용역 시에는 용역금액, 직영 시에는 경비원 인건비와 피복비, 경비용품비 등 경비에 직접 소요되는 비용을 말한다.<br/>
										☞ 주의사항 : 직영으로 경비업무를 수행하는 단지의 경우, 경비원에 대한 4대 보험의 사업주 부담분 등은 경비비에 포함한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										28.소득비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="120003" name="disinf_cost" readonly="readonly">
									</td>
									<td class="align_l">
										소독비란 소독 작업을 수행할 경우, 용역 시에는 용역금액, 직영 시에는 소독요원 인건비, 피복비, 양품비, 소독용품 등 소독작업에 직접 소요된 비용을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										29.승강기유지비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="120004" name="elev_cost" readonly="readonly">
									</td>
									<td class="align_l">
										승강기유지비란 용역 시에는 용역금액, 직영 시에는 자재 및 인건비, 제부대비 등을 말한다.<br/>
										다만, 전기료는 공동으로 사용되는 시설의 전기료에 포함한다.<br/>
										☞  주의사항 : 승강기의 효율성을 높이거나 고장발생 시 소요되는 제 비용을 실무에서 수선유지비로 처리하는 경우가 많으나, 승강기유지비로 처리하여야 한다.<br/>
										☞ 주의사항 : 승강기 운행에 소요되는 전기료는 관리비중 승강기유지비가 아니라 사용료 중 전기료임.<br/>
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										30.지능형 홈네트워크 설비유지비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="120005" name="hnetw_cost" readonly="readonly">
									</td>
									<td class="align_l">
										용역 시에는 용역금액, 직영 시에는 지능형 홈네트워크 설비 관련 인건비, 자재비 등 지능형 홈네트워크 설비의 유지 및 관리에 직접 소요되는 비용, 다만, 전기료는 공동으로 사용되는 시설의 전기료에 포함한다.
									</td>
								</tr>
								<tr>
									<td rowspan="4">
										수선유지비
									</td>
									<td class="align_l" colspan="2">
										31.수선비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="131001" name="lref_cost1" readonly="readonly">
									</td>
									<td class="align_l">
										장기수선계획에서 제외되는 공용부분의 수선·보수에 소요되는 비용으로 보수용역 시에는 용역금액, 직영시에는 자재 인건비.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="2">
										32.시설유지비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="132001" name="lref_cost2" readonly="readonly">
									</td>
									<td class="align_l">
										어린이놀이시설 안전검사비, 수질검사비, 승강기안전점검비용, 전기안전관리비(대행료), 소방안전관리비(대행료), 전기시설물 안전검사비 등을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="2">
										33.안전점검비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="133001" name="lref_cost3" readonly="readonly">
									</td>
									<td class="align_l">
										건축물의 안전점검 비용을 말한다.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="2">
										34.재해예방비
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="134001" name="lref_cost4" readonly="readonly">
									</td>
									<td class="align_l">
										재난 및 재해를 예방하기 위해 지출하는 비용.
									</td>
								</tr>
								<tr>
									<td class="align_l" colspan="3">
										35.위탁관리 수수료
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="140001" name="manage_cost" readonly="readonly">
									</td>
									<td class="align_l">
										주택관리업자에게 위탁하여 관리하는 경우로서 입주자대표회의와 주택관리자 간의 계약으로 정한 월간비용을 말한다.
									</td>
								</tr>
							</table>					
						</article>
					</div>
				</li>
				<li>
					<input type="radio" name="tabs" class="rd_tabs" id="tab2">
					<label for="tab2">공용/개별 사용료</label>
					<div class="tab_con">
						<article>
							<div>
								<table class="view_top_center_table">
									<colgroup>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="50%"/>     
									</colgroup>
									<tr>
										<th>구분</th>
										<th>대항목</th>
										<th>중항목</th>
										<th>세항목</th>
										<th>금액</th>
										<th>설명</th>
									</tr>
									<tr>
										<td rowspan="10">
											공동사용료
										</td>
										<td rowspan="2" colspan="2" class="align_l">
											36.급탕비
										</td>
										<td>
											급탕사용료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210002" name="water_hot">
										</td>
										<td class="align_l" rowspan="2">
											급탕비란 급탕용 유루대 및 급탕용수비를 말한다
										</td>
									</tr>
									<tr>
										<td>
											급탕사용량<br/>(ton)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="hwater_hot" name="hwater_hot">
										</td>
									</tr>
									<tr>
										<td rowspan="2" colspan="2" class="align_l">
											37.난방비
										</td>
										<td>
											난방사용료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210001" name="heat">
										</td>
										<td class="align_l" rowspan="2">
											난방비란 중앙집중식 난방방식에서 난방 및 급탕에 소요된 원가(우루대, 난방비 및 급탕용수비)에서 급탕비를 뺀 금액을 말한다.<br/>
											☞ 참고사항 : 지역난방 방식의 난방비는 관리비가 아니라 사용료임.
										</td>
									</tr>
									<tr>
										<td>
											난방사용량<br/>(Mcal)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="hheat" name="hheat">
										</td>
									</tr>
									<tr>
										<td rowspan="2" colspan="2" class="align_l">
											38.가스사용료
										</td>
										<td>
											가스사용료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210003" name="gas">
										</td>
										<td class="align_l" rowspan="2">
											취사에 사용하는 가스나 개별난방 시 사용하는 가스에 대한 사용료를 말한다.<br/>
											☞ 주의사항 : 중앙집중식 난방방식에서 가스를 연료로 사용하면 사용된 가스료는 난방비로 분류하여야 한다.
										</td>
									</tr>
									<tr>
										<td>
											가스사용량<br/> (㎡)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="hgas" name="hgas">
										</td>
									</tr>
									<tr>
										<td rowspan="2" colspan="2" class="align_l">
											39.전기료
										</td>
										<td>
											전기사용료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210004" name="elect">
										</td>
										<td class="align_l" rowspan="2">
											전용 : 한전에서 부과하는 전기요금중 공동전기료를 제외한 전기료.<br/>
											공용 : 한전에서 부과하는 전기요금중 공동전기료.
										</td>
									</tr>
									<tr>
										<td>
											전기사용량<br/>(Kwh)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="helect" name="helect">
										</td>
									</tr>
									<tr>
										<td rowspan="2" colspan="2" class="align_l">
											40.수도료
										</td>
										<td>
											수도사용료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210005" name="water_cool">
										</td>
										<td class="align_l" rowspan="2">
											전용 : 수도사업소에서 부과하는 수도요금중 공동수도료를 제외한 수도료.<br/>
											공용 : 수도사업소에서 부과하는 수도요금중 공동수도료.
										</td>
									</tr>
									<tr>
										<td>
											수도사용량<br/>(㎥)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="hwater_cool" name="hwater_cool">
										</td>
									</tr>
									<tr>
										<td colspan="4" class="align_l">
											41.정화조오물수수료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210006" name="purifi" readonly="readonly">
										</td>
										<td class="align_l">
											매년 정화조 청소 시 발생하는 수수료를 말한다.<br/>
											☞ 주의사항 : 정화조시설 유지관리 대행비는 수선유지비.
										</td>
									</tr>
									<tr>
										<td colspan="4" class="align_l">
											42.생활폐기물 수수료
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210007" name="scrap" readonly="readonly">
										</td>
										<td class="align_l">
											관리규약으로 정한, 입주자대표회의 운영 비용.
										</td>
									</tr>
									<tr>
										<td colspan="4" class="align_l">
											43.입주자대표회의 운영비
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210008" name="pre_meet" readonly="readonly">
										</td>
										<td class="align_l">
											건물 화재보험과 승강기, 어린이놀이시설, 지하주차장, 독서실 운동시설 등에 소요되는 보험료를 말한다.
										</td>
									</tr>
									<tr>
										<td colspan="4" class="align_l">
											44.건물보험료(종합 보험료)
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210009" name="build_insu" readonly="readonly">
										</td>
										<td class="align_l">
											건물 화재보험과 승강기, 어린이놀이시설, 지하주차장, 독서실 운동시설 등에 소요되는 보험료를 말한다.
										</td>
									</tr>
									<tr>
										<td rowspan="2" colspan="3" class="align_l">
											45.선서관리위원회운영비
										</td>
										<td>
											운영비
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="210010" name="elec_mng" readonly="readonly">
										</td>
										<td class="align_l" rowspan="2">
											관리규약으로 정한, 선거관리위원회 운영 비용.
										</td>
									</tr>
									<tr>
										<td>
											기타
										</td>
										<td>
											<input type="text" placeholder="" class="default_input align_r"  id="etc" name="etc">
										</td>
									</tr>
								</table>
							</div>
							<div>
								<div class="box_title_con">
									<div class="title_circle"></div>
									<div class="box_title">개별 사용료 합계</div>
								</div>
								<div class="margin_t_10">
									<table class="view_top_center_table" id="indivFeeTotal">
										<colgroup>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
										</colgroup>
										<tbody>
											<tr>
												<th>면적</th>
												<th>세대수</th>
												<th>구분</th>
												<th>
													<label class="control control_checkbox" style="display:inline-block">
														<span>급탕</span>
														<input type="checkbox" id="water_hotChk" name="water_hotChk">
														<div class="control_indicator"></div>
													</label>
												</th>
												<th>
													<label class="control control_checkbox" style="display:inline-block">
														<span>난방</span>
														<input type="checkbox" id="heatChk" name="heatChk">
														<div class="control_indicator"></div>
													</label>
												</th>
												<th>
													<label class="control control_checkbox" style="display:inline-block">
														<span>가스</span>
														<input type="checkbox" id="gasChk" name="gasChk">
														<div class="control_indicator"></div>
													</label>
												</th>
												<th>
													<label class="control control_checkbox" style="display:inline-block">
														<span>전기</span>
														<input type="checkbox" id="electChk" name="electChk">
														<div class="control_indicator"></div>
													</label>
												</th>
												<th>
													<label class="control control_checkbox" style="display:inline-block">
														<span>수도</span>
														<input type="checkbox" id="water_coolChk" name="water_coolChk">
														<div class="control_indicator"></div>
													</label>
												</th>
											</tr>
											<tr>
												<td rowspan="2" id="areaTotal" name="space"></td>
												<td rowspan="2" id="famNumTotal" name="occupant_count"></td>
												<td id="defalutTd1">사용료</td>
												<td class="align_r" id="hotwaterFeeTotal" name="water_hot"></td>
												<td class="align_r" id="heatingFeeTotal"name="heat"></td>
												<td class="align_r" id="gasFeeTotal" name="gas"></td>
												<td class="align_r" id="elecFeeTotal" name="elect"></td>
												<td class="align_r" id="waterFeeTotal" name="water_cool"></td>
											</tr>
											<tr>
												<td id="defalutTd2">사용량</td>
												<td class="align_r" id="hotwaterUsageTotal" name="hwater_hot"></td>
												<td class="align_r" id="heaterUsageTotal" name="hheat"></td>
												<td class="align_r" id="gasUsageTotal" name="hgas"></td>
												<td class="align_r" id="elecUsageTotal" name="helect"></td>
												<td class="align_r" id="waterUsageTotal" name="hwater_cool"></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div>
								<div class="box_title_con">
									<div class="title_circle"></div>
									<div class="box_title">면적별 사용료 </div>
								</div>
								<div class="margin_t_10">
									<table class="view_top_center_table" id="areaFee">
										<colgroup>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
											<col width="12.5%"/>
										</colgroup>
										<tbody>
											<tr>
												<th>면적</th>
												<th>세대수</th>
												<th>구분</th>
												<th>급탕</th>
												<th>난방</th>
												<th>가스</th>
												<th>전기</th>
												<th>수도</th>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</article>
					</div>
				</li>
				<li>
					<input type="radio" name="tabs" class="rd_tabs" id="tab3">
					<label for="tab3">장기수선충당금</label>
					<div class="tab_con">
						<article>
							<table class="view_top_center_table">
								<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="50%"/>     
								</colgroup>
								<tr>
									<th>구분</th>
									<th>대항목</th>
									<th>중항목</th>
									<th>세항목</th>
									<th>금액</th>
									<th>설명</th>
								</tr>
								<tr>
									<td rowspan="4" colspan="3" class="align_l">
										46.장기수선충당금
									</td>
									<td>
										월부과액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="300000" name="s_levy">
									</td>
									<td class="align_l">
										관리주체가 매월 입주자에게 부과 징수하는 장기수선충당금을 말한다.
									</td>
								</tr>
								<tr>
									<td>
										월사용금액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="s_use" name="s_use">
									</td>
									<td class="align_l">
										월합계잔액시산표의 장기수선충당금(부채)의 감소 (당월 차변금액)
									</td>
								</tr>
								<tr>
									<td>
										충당금잔액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="s_tot" name="s_tot">
									</td>
									<td class="align_l">
										공동주택에서 적립한 총금액에서 사용 금액을 제외한 나머지<br/>
										즉, 장기수선충당금 잔액 또는 장기수선충당금 예치금액을 말한다.<br/>
										월합계잔액시산표의 장기수선충당금(부채)의 잔액
									</td>
								</tr>
								<tr>
									<td>
										적립율(%)
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="s_per" name="s_per">
									</td>
									<td class="align_l">
										관리규약에 정해져 있는 적립율(적립율은 소수점 1자리)
									</td>
								</tr>
							</table>
						</article>
					</div>
				</li>
				<li>
					<input type="radio" name="tabs" class="rd_tabs" id="tab4">
					<label for="tab4">잡수입</label>
					<div class="tab_con">
						<article>
							<table class="view_top_center_table">
								<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="50%"/>     
								</colgroup>
								<tr>
									<th>구분</th>
									<th>대항목</th>
									<th>중항목</th>
									<th>세항목</th>
									<th>금액</th>
									<th>설명</th>
								</tr>
								<tr>
									<td rowspan="4" colspan="3" class="align_l">
										47.잡수입
									</td>
									<td>
										월수입금액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="400000" name="other_income">
									</td>
									<td class="align_l">
										모든 관리외수익은 잡수입에 해당함<br/>
										월합계잔액시산표의 관리외수익의 당월 대변합계
									</td>
								</tr>
								<tr>
									<td>
										월사용금액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="other_expens" name="other_expens">
									</td>
									<td class="align_l">
										당월 관리외비용(다만, 잉여금을 처분한 달에는 그 처분금액도 포함하여 기재)<br/>
										월합계잔액시산표의 관리외비용의 당월 차변합계+이익잉여금 당월 차변합계 
									</td>
								</tr>
								<tr>
									<td>
										잔액
									</td>
									<td>
										<input type="text" placeholder="" class="default_input align_r"  id="other_tot" name="other_tot">
									</td>
									<td class="align_l">
										잡수입잔액(=미처분이익잉여금 +당기이익잉여금 처분가능액)<br/>
										월합계잔액시산표의 관리외수익 대변잔액-관리외비용 차변잔액+미처분이익잉여금 대변잔액
									</td>
								</tr>
							</table>
						</article>
					</div>
				</li>
			</ul>
		</nav>
	</form>
</div>