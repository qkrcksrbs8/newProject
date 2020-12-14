<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
var selCompanyId = '';
var selRowId = '';
var sDong1, sHo1, sDong2, sHo2, sSubscribe, sEnter, sEnterDt1, sEnterDt2, sAvailability;

$(function() {


});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres02/mres0201.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<iframe id="print0201_1" name="print0201_1" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<form name="frmPrint1" action="<%=request.getContextPath()%>/print/0201_1" method="POST">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="companyNm" value="" />
	<input type="hidden" name="sDong1" value="" />
	<input type="hidden" name="sHo1" value="" />
	<input type="hidden" name="sDong2" value="" />
	<input type="hidden" name="sHo2" value="" />
	<input type="hidden" name="sSubscribe" value="" />
	<input type="hidden" name="sEnter" value="" />
	<input type="hidden" name="sEnterDt1" value="" />
	<input type="hidden" name="sEnterDt2" value="" />
	<input type="hidden" name="sAvailability" value="" />
</form>

<div class="search_wrap">
	<div class="search_con">
		<div class="search_title">동호</div>
		<div class="search_dong">
			<input type="text" class="default_input" id="sDong1" name="sDong1" numberOnly maxlength="4">
			<span>동</span>
			<input type="text" class="default_input" id="sHo1" name="sHo1" numberOnly maxlength="4">
			<span>호</span>
			<span>~</span>
			<input type="text" class="default_input" id="sDong2" name="sDong2" numberOnly maxlength="4">
			<span>동</span>
			<input type="text" class="default_input" id="sHo2" name="sHo2" numberOnly maxlength="4">
			<span>호</span>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">분양구분</div>
		<div>
			<select class="default_select" id="sSubscribe" name="sSubscribe">
				<option value="" selected="selected">전체</option>
				<option value="1">분양</option>
				<option value="2">미분양</option>
			</select>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">입주구분</div>
		<div>
			<select class="default_select" id="sEnter" name="sEnter">
				<option value="" selected="selected">전체</option>
				<option value="1">미입주</option>
				<option value="2">입주</option>
				<option value="3">퇴거</option>
			</select>
		</div>
	</div>
	<div class="search_con clear">
		<div class="search_title">입주기간</div>
		<div>
			<div class="cal_wrap">
				<input type="text" class="date_input" id="sEnterDt1" name="sEnterDt1">
				<p class="cal_icon">
					<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
				</p>
			</div>
			<p class="cal_to">~</p>
			<div class="cal_wrap">
				<input type="text" class="date_input" id="sEnterDt2" name="sEnterDt2">
				<p class="cal_icon">
					<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
				</p>
			</div>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">사용여부</div>
		<div>
			<select class="default_select" id="sAvailability" name="sAvailability">
				<option value="">전체</option>	
				<option value="1" selected="selected">사용</option>
				<option value="2">미사용</option>		
			</select>
		</div>
	</div>
	<div class="right">
		<a href="#;" class="basin_btn" onclick="searchBtn()">검색</a>
	</div>
</div>

<div class="margin_t_10 con_wrap con_wrap_100">
	<div class="con_wrap con_wrap_40 padding_r_10">
		<div class="search_wrap_dong">
			<input type="text" class="default_input" id="searchDong" name="searchDong" autocomplete="false" numberOnly>
			<span>동</span>
			<input type="text" class="default_input" id="searchHo" name="searchHo" autocomplete="false" numberOnly>
			<span>호</span>
			<a href="#;" class="basin_btn2 dong_search_btn" onclick="dongHoSearch()"><i class="fa fa-search"></i></a>
		</div>
		<div class="margin_t_10 list_wrap">
			<table id="list1" class="jq_table"></table>
		</div>
	</div>
	<div class="con_wrap con_wrap_60 padding_l_10">
		<div class="button_wrap right margin_t_m10">
			<a href="#;" class="basin_btn" onclick="saveBillSum()">합산동호저장</a>
			<a href="#;" class="basin_btn" onclick="regDonghoAdd()">합산동호등록</a>
			<a href="#;" class="basin_btn" onclick="excelPop()">엑셀업로드</a>
			<a href="#;" class="basin_btn" onclick="execPrint('print0201_1')">인쇄</a>
			<!-- <a href="#;" class="basin_btn" onclick="excelPop()">엑셀업로드</a> -->
<!-- 			<a href="#;" class="basin_btn" onclick="excelDownloadBtn()">엑셀다운로드</a> -->
			
<%-- 			<form:form commandName="frmExcelUpload" id = "frmExcelUpload" enctype="multipart/form-data" method="post" action="excelUploadAjax"> --%>
<%-- 				<form:input id = "excelFile" type="file" name="excelFile" path="file" class="none" onchange="changeExcelValue('excelFile','frmExcelUpload')"/>				 --%>
<!-- 				<input id = "companyIdVal" name="companyIdVal" class="none" /> -->
<!-- 				<input id = "flag" name="flag" class="none" value="3"/> -->
<%-- 			</form:form> --%>
			
			<form id ="frmExcel" name ="frmExcel" action="excelDownload" method="post">
				<input type="hidden" name="companyId" value="" />
				<input type="hidden" name="sDong1" value="" />
				<input type="hidden" name="sHo1" value="" />
				<input type="hidden" name="sDong2" value="" />
				<input type="hidden" name="sHo2" value="" />
				<input type="hidden" name="sSubscribe" value="" />
				<input type="hidden" name="sEnter" value="" />
				<input type="hidden" name="sEnterDt1" value="" />
				<input type="hidden" name="sEnterDt2" value="" />
				<input type="hidden" name="sAvailability" value="" />
				<input type="hidden" name="companyNm" value="" />
				<input type="hidden" name="fileNm" value="입주현황" />			
			</form>
		</div>
		<div class="margin_t_10 con_wrap con_wrap_100">
			<nav class="nav_tabs">
				<ul>				
					<li>
						<input type="radio" name="tabs" class="rd_tabs" id="tab1" checked> 
						<label for="tab1">입주자</label>
						<div class="tab_con">
							<form id="frm1" name="frm1" action="insertRegistrationTenant" method="post">
							<input type="hidden" name="companyId" />
							<input type="hidden" name="flagNum" value="0">
							<input type="hidden" id="rowid" name="rowid" />
							<input type="hidden" name="id" id="id" />
							<input type="hidden" name="tenantId" id="tenantId"/>
							<input type="hidden" name="chkId" id="chkId"/>
							<article>
								<div>
									<table class="view_table" id="view_top_table">
										<tr>
											<th style="width: 30px;"></th>
											<th>동</th>
											<th>호</th>
											<th>특성</th>
											<th>메인</th>
											<th>분양면적</th>
											<th>전용면적</th>
										</tr>
										
									</table>
								</div>
								<div class="box_title_con con_wrap_100">
									<div class="title_circle"></div>
									<div class="box_title">입주</div>
									<div class="right margin_b_5">
	<!-- 									<div class="margin_r_10 left"> -->
	<!-- 										<a href="#;" class="basin_btn" id="add_dongho_set">세대주 동일호수 추가</a> -->
	<!-- 									</div> -->
										<label class="control control_checkbox right margin_t_10">
											<span>합산동호 적용</span>
											<input type="checkbox" id="billSum" name="billSum" onclick="return false;"/>
											<div class="control_indicator"></div>
										</label>
									</div>									
								</div>							
								<div>
									<table class="view_table">
										<tr>
											<th>동호</th>
											<td class="dongho_td">
												<div class="dongho_set">
													<div class="left"><input type="text" class="default_input w55" id="dong" name="dong" readonly="readonly"><span class="margin_l_5">동</span></div>
													<div class="left margin_l_10"><input type="text" class="default_input w55" id="ho" name="ho" readonly="readonly"><span class="margin_l_5">호</span></div>											
												</div>
											</td>
											<th>입주일</th>
											<td>
												<div class="cal_wrap">
													<input type="text" class="date_input" id="enterDt" name="enterDt">
													<p class="cal_icon">
														<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
													</p>
												</div>										
											</td>
											<th>가구수</th>
											<td><input type="text" class="default_input w55" id="famNum" name="famNum" numberOnly maxlength="4"></td>
										</tr>
										<tr>
											<th>분양</th>
											<td>
												<div class="left">
													<select class="default_select" id="subscribe" name="subscribe">
														<option value="1">분양</option>
														<option value="2">미분양</option>
													</select>
												</div>
											</td>
											<th>입주</th>
											<td>	
												<div class="left">
													<select class="default_select" id="enter" name="enter">
														<option value="1">미입주</option>
														<option value="2">입주</option>
														<option value="3">퇴거</option>
														<option value="4">사용안함</option>
													</select>
												</div>										
											</td>
											<th>주거</th>
											<td>
												<div>
													<select class="default_select" id="residence" name="residence">
														<option value="0">없음</option>
														<option value="1">자가</option>
														<option value="2">전세</option>
														<option value="3">월세</option>
														<option value="4">임대</option>
														<option value="5">사택</option>
														<option value="6">기타</option>
														<option value="7">수급권자</option>
														<option value="8">국가유공자</option>
														<option value="9">모,부모가정</option>
														<option value="10">소년소녀가장</option>
														<option value="11">장애인</option>
														<option value="12">일군위안부</option>
														<option value="13">청약저축</option>
														<option value="14">공가</option>
														<option value="15">새터민</option>
														<option value="16">세입자</option>
														<option value="17">부양의료특례</option>
														<option value="18">재활특례</option>
														<option value="19">조건부</option>
														<option value="20">장기전세</option>
														<option value="21">국민임대</option>
														<option value="22">직계존비속</option>
														<option value="23">소유자의배우자</option>
														<option value="24">군관사</option>
														<option value="25">독거노인</option>
														<option value="26">청년근로자</option>
														<option value="27">생보</option>
														<option value="28">불법</option>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th>특성</th>
											<td>
												<select class="default_select" id="property" name="property">
													<option value="1">아파트</option>
													<option value="2">오피스텔</option>
													<option value="3">상가</option>
													<option value="4">빌딩</option>
													<option value="5">기숙사</option>
													<option value="6">공장</option>
												</select>										
											</td>
											<th>임대보증금</th>
											<td><input type="text" class="default_input w100" id="securityDeposit" name="securityDeposit" numberOnly></td>
											<th>임대료</th>
											<td><input type="text" class="default_input w100" id="rentalFee" name="rentalFee" numberOnly></td>
										</tr>
										<tr>
											<th>키불출일</th>
											<td>
												<div class="cal_wrap">
													<input type="text" class="date_input" id="keyDispatchDay" name="keyDispatchDay">
													<p class="cal_icon">
														<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
													</p>
												</div>										
											</td>
											<th>임대기간</th>
											<td colspan="3">
												<div class="cal_wrap">
													<input type="text" class="date_input" id="leaseperiodFr" name="leaseperiodFr">
													<p class="cal_icon">
														<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
													</p>
												</div>
												<p class="cal_to">~</p>
												<div class="cal_wrap">
													<input type="text" class="date_input" id="leaseperiodTo" name="leaseperiodTo">
													<p class="cal_icon">
														<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
													</p>
												</div>										
											</td>
										</tr>
									</table>
								</div>
								
								<div class="box_title_con con_wrap_100">
									<div class="title_circle"></div>
									<div class="box_title">세대주</div>
									<div class="right margin_b_5">
										<label class="control control_checkbox right margin_t_10">
											<span>사업자 여부</span>
											<input type="checkbox" id="cbBusinessFlag" name="cbBusinessFlag"/>
											<div class="control_indicator"></div>
										</label>
									</div>	
								</div>
								<div>
									<table class="view_table">
										<tr>
											<th>성명</th>
											<td><input type="text" class="default_input w100" id="householdNm" name="householdNm"></td>
											<th>생년월일</th>
											<td>
												<div class="cal_wrap">
													<input type="text" class="date_input" id="birthday" name="birthday">
													<p class="cal_icon">
														<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
													</p>
												</div>										
											</td>
											<th>성별</th>
											<td>
												<select class="default_select" id="gender" name="gender">
													<option value="1">남</option>
													<option value="2">여</option>
												</select>										
											</td>
										</tr>
										<tr>
											<th>전화번호</th>
											<td><input type="text" class="default_input w100" id="tel" name="tel" numberOnly numberTel maxlength="13"></td>
											<th>연락처(자택)</th>
											<td><input type="text" class="default_input w100" id="telHome" name="telHome" numberOnly numberTel maxlength="13"></td>
											<th>휴대폰</th>
											<td><input type="text" class="default_input" id="cellphone" name="cellphone" numberPhone maxlength="13"></td>
										</tr>
										<tr>
											<th>메모</th>
											<td colspan="3"><input type="text" class="default_input con_wrap_100" id="memo" name="memo"></td>
											<th>직업</th>
											<td><input type="text" class="default_input w100" id="job" name="job"></td>
										</tr>
										<tr>
											<th>이메일</th>
											<td colspan="3">
												<input type="text" class="default_input left" id="email" name="email">
												<div class="check_con margin_t_5 margin_l_10">
													<label class="control control_checkbox">
														<span>전자고지서여부 </span>
														<input type="checkbox" id="availabilityBill" name="availabilityBill"/>
														<div class="control_indicator"></div>
													</label>
												</div>	
											</td>
											<th>근무처</th>
											<td><input type="text" class="default_input" id="office" name="office"></td>
										</tr>
										<tr>
											<th>주소</th>
											<td colspan="5">
												<input type="text" placeholder="" class="default_input w70" id="zipcode1" name="zipcode1" readonly="readonly" onclick="execDaumPostcode('zipcode1', 'address1')">
												<a href="#;" class="basin_btn margin_0" onclick="execDaumPostcode('zipcode1', 'address1')">우편번호</a>							
												<input type="text" placeholder="" class="default_input con_wrap_70" id="address1" name="address1">											
											</td>
										</tr>
									</table>
								</div>
								
								<div id="business_con" style="display: none">
									<div class="box_title_con">
										<div class="title_circle"></div>
										<div class="box_title">사업자</div>
									</div>
									<div>
										<table class="view_table">
											<tr>
												<th><p class="p_required">*</p>사업자번호</th>
												<td><input type="text" class="default_input w120" id="businessNum" name="businessNum" maxlength="12"></td>
												<th><p class="p_required">*</p>상호</th>
												<td><input type="text" class="default_input w100" id="compNm" name="compNm"></td>
												<th><p class="p_required">*</p>대표자</th>
												<td><input type="text" class="default_input w100" id="ceoNm" name="ceoNm"></td>
											</tr>
											<tr>
												<th>주민등록번호</th>
												<td><input type="text" class="default_input w120" id="residentRegistration" name="residentRegistration" numberOnly maxlength="14"></td>
												<th><p class="p_required">*</p>업태</th>
												<td><input type="text" class="default_input w100" id="compCondi" name="compCondi"></td>
												<th><p class="p_required">*</p>종목</th>
												<td><input type="text" class="default_input w100" id="especie" name="especie"></td>
											</tr>
											<tr>
												<th>휴대폰</th>
												<td colspan="5">
													<div class="left"><input type="text" class="default_input" id="businessCellphone" name="businessCellphone" numberphone maxlength="13"></div>
													<div class="check_con padding_l_10 padding_t_5">
														<label class="control control_checkbox">
															<span>문자전송(SMS)</span>
															<input type="checkbox" id="sendMessage" name="sendMessage" value="1"/>
															<div class="control_indicator"></div>
														</label>
													</div>											
												</td>
											</tr>
											<tr>
												<th><p class="p_required">*</p>이메일</th>
												<td colspan="3">
													<div class="left"><input type="text" class="default_input" id="businessEmail" name="businessEmail"></div>
													<div class="check_con padding_l_10 padding_t_5">
														<label class="control control_checkbox">
															<span>이메일고지</span>
															<input type="checkbox" id="sendEmail" name="sendEmail"/>
															<div class="control_indicator"></div>
														</label>
													</div>											
												</td>
												<th>계산서발행</th>
												<td>
													<select class="default_select" id="receiptIssue" name="receiptIssue">
														<option value="0">안함</option>
														<option value="1">전자계산서</option>
														<option value="2">종이계산서</option>
													</select>										
												</td>	
											</tr>								
											<tr>
												<th>주소</th>
												<td colspan="5">		
													<input type="text" placeholder="" class="default_input w70" id="zipcode2" name="zipcode2" readonly="readonly" onclick="execDaumPostcode('zipcode2', 'address2')">
													<a href="#;" class="basin_btn margin_0" onclick="execDaumPostcode('zipcode2', 'address2')">우편번호</a>									
													<input type="text" placeholder="" class="default_input con_wrap_70" id="address2" name="address2">											
												</td>
											</tr>
											<tr>
												<th>사업장전화</th>
												<td><input type="text" class="default_input w120" id="businessTel" name="businessTel" numberTel maxlength="13"></td>
												<th>업무담당자</th>
												<td><input type="text" class="default_input w100" id="staffNm" name="staffNm"></td>
												<th>담당자연락처</th>
												<td><input type="text" class="default_input w120" id="staffTel" name="staffTel" numberPhone maxlength="13"></td>
											</tr>
											<tr>
												<th>부서</th>
												<td><input type="text" class="default_input w100" id="departments" name="departments"></td>
												<th>담당자이메일</th>
												<td colspan="3"><input type="text" class="default_input" id="staffEmail" name="staffEmail"></td>
											</tr>
										</table>
									</div>
								</div>
	
								
								<div class="box_title_con con_wrap_100">
									<div class="title_circle"></div>
									<div class="box_title">세대구성원</div>
									<div class="right margin_b_5"><a href="#;" class="basin_btn" onclick="regResidentAdd()">구성원추가</a></div>
									<div class="button_wrap">
									<a href="#;" class="basin_btn" onclick="saveBtn1()">저장</a>
								</div>
								</div>
									<div class="list_wrap clear">
										<table id="list2" class="jq_table"></table>
									</div>
								<div class="box_title_con con_wrap_100">
									<div class="title_circle"></div>
									<div class="box_title">차량정보</div>
									<div class="right margin_b_5"><a href="#;" class="basin_btn" onclick="carPlus()">차량 추가</a></div>
								</div>
								<div class="list_wrap clear">
									<table id="list3" class="jq_table"></table>
								</div>
																	
							</article>
							</form>
						</div>
					</li>
							
					<li>
						<input type="radio" name="tabs" class="rd_tabs" id="tab2">
						<label for="tab2">소유주</label>
						<div class="tab_con">
							
							<article>
								<form id="frm4" name="frm4" action=insertRegistrationProprietor method="post">
								<input type="hidden" name="id" id = "ownerId" value="">
								<input type="hidden" name="rowid" value="">
								<input type="hidden" name="flagNum" value="">
								<input type="hidden" name="tenantId" value="">
								<input type="hidden" name="dong" value="">
								<input type="hidden" name="ho" value="">
								<input type="hidden" name="companyId" value="">
								
								<div class="box_title_con con_wrap_100">
									<div class="right margin_l_10">
										<label class="control control_checkbox">
											<span>세대주와 동일</span>
											<input type="checkbox" id="tenantCopy" name="tenantCopy"/>
											<div class="control_indicator"></div>
										</label>
									</div>
									<div class="right">
										<label class="control control_checkbox">
											<span>사업자 여부</span>
											<input type="checkbox" id="cbBusinessFlag2" name="cbBusinessFlag2"/>
											<div class="control_indicator"></div>
										</label>
									</div>										
								</div>
									
								<div id="person_con">
									<div class="box_title_con con_wrap_100">
										<div class="title_circle"></div>
										<div class="box_title">소유주</div>									
									</div>
									<div>
										<table class="view_table">
											<tr>
												<th><p class="p_required">*</p>성명</th>
												<td><input type="text" class="default_input w100" id="houseMemberNm" name="houseMemberNm"></td>
												<th>생년월일</th>
												<td>
													<div class="cal_wrap">
														<input type="text" class="date_input" id="proprietorBirthday" name="proprietorBirthday">
														<p class="cal_icon">
															<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
														</p>
													</div>											
												</td>
												<th>성별</th>
												<td>
													<select class="default_select" id="proprietorGender" name="proprietorGender">
														<option value="1">남</option>
														<option value="2">여</option>
													</select>											
												</td>
											</tr>
											<tr>
												<th>Fax번호</th>
												<td><input type="text" class="default_input w100" id="faxNum" name="faxNum" numberTel maxlength="13"></td>
												<th><p class="p_required">*</p>소유일</th>
												<td>
													<div class="cal_wrap">
														<input type="text" class="date_input" id="ownershipDt1" name="ownershipDt1">
														<p class="cal_icon">
															<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
														</p>
													</div>										
												</td>
												<th>근무처</th>
												<td><input type="text" class="default_input w120" id="proprietorOffice" name="proprietorOffice" ></td>
											</tr>
											<tr>
												<th>휴대폰</th>
												<td><input type="text" class="default_input w120" id="proprietorCellphone" name="proprietorCellphone" numberphone maxlength="13"></td>
												<th>연락처(자택)</th>
												<td><input type="text" class="default_input w100" id="proprietorTelHome" name="proprietorTelHome" numberTel maxlength="13"></td>
												<th>연락처</th>
												<td><input type="text" class="default_input w100" id="proprietorTel" name="proprietorTel" numberphone maxlength="13"></td>
											</tr>
											<tr>
												<th>메모</th>
												<td colspan="5"><input type="text" class="default_input con_wrap_100" id="proprietorMemo" name="proprietorMemo"></td>
											</tr>
											<tr>
												<th>고지서배송지</th>
												<td colspan="5">	
													<input type="text" placeholder="" class="default_input w70" id="zipcode3" name="zipcode3" readonly="readonly" onclick="execDaumPostcode('zipcode3', 'address3')">
													<a href="#;" class="basin_btn margin_0" onclick="execDaumPostcode('zipcode3', 'address3')">우편번호</a>											
													<input type="text" placeholder="" class="default_input con_wrap_70" id="address3" name="address3">												
												</td>
											</tr>
										</table>
									</div>
								</div>
								
								<div id="business_con2"  style="display: none">
									<div class="box_title_con">
										<div class="title_circle"></div>
										<div class="box_title">사업자</div>
									</div>
									<div>
										<table class="view_table">
											<tr>
												<th><p class="p_required">*</p>사업자번호</th>
												<td><input type="text" class="default_input w120" placeholder=""  id="proprietorBusinessNum" name="proprietorBusinessNum"></td>
												<th><p class="p_required">*</p>상호</th>
												<td><input type="text" class="default_input w100" id="proprietorCompNm" name="proprietorCompNm"></td>
												<th><p class="p_required">*</p>대표자</th>
												<td><input type="text" class="default_input w100" id="proprietorCeoNm" name="proprietorCeoNm"></td>
											</tr>
											<tr>
												<th><p class="p_required">*</p>소유일</th>
												<td>
													<div class="cal_wrap">
														<input type="text" class="date_input" id="ownershipDt2" name="ownershipDt2">
														<p class="cal_icon">
															<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
														</p>
													</div>										
												</td>
												<th>업태</th>
												<td><input type="text" class="default_input w100" id="proprietorCompCondi" name="proprietorCompCondi"></td>
												<th>종목</th>
												<td><input type="text" class="default_input w100" id="proprietorEspecie" name="proprietorEspecie"></td>
											</tr>
											<tr>
												<th>주민등록번호</th>
												<td><input type="text" class="default_input w120" id="proprietorResidentRegistration" name="proprietorResidentRegistration" maxlength="14"></td>
												<th>휴대폰</th>
												<td colspan="3">
													<div class="left"><input type="text" class="default_input" id="proprietorBCellphone" name="proprietorBCellphone" numberphone maxlength="13"></div>
													<div class="check_con padding_l_10 padding_t_5">
														<label class="control control_checkbox">
															<span>문자전송(SMS)</span>
															<input type="checkbox" id="proprietorSendSms" name="proprietorSendSms"/>
															<div class="control_indicator"></div>
														</label>
													</div>												
												</td>	
											</tr>
											<tr>
												<th>계산서발행</th>
												<td>
													<select class="default_select" id="proprietorReceiptIssue" name="proprietorReceiptIssue">
														<option value="0">안함</option>
														<option value="1">전자계산서</option>
														<option value="2">종이계산서</option>
													</select>										
												</td>	
												<th><p class="p_required">*</p>이메일</th>
												<td colspan="3">
													<div class="left"><input type="text" class="default_input" id="taxEmail" name="taxEmail"></div>
													<div class="check_con padding_l_10 padding_t_5">
														<label class="control control_checkbox">
															<span>이메일고지</span>
															<input type="checkbox" id="sendEmail2" name="sendEmail2"/>
															<div class="control_indicator"></div>
														</label>
													</div>												
												</td>
											</tr>
											<tr>
												<th><p class="p_required">*</p>고지서배송지</th>
												<td colspan="5">		
													<input type="text" placeholder="" class="default_input w70" id="zipcode4" name="zipcode4" readonly="readonly" onclick="execDaumPostcode('zipcode4', 'address4')">
													<a href="#;" class="basin_btn margin_0" onclick="execDaumPostcode('zipcode4', 'address4')">우편번호</a>										
													<input type="text" placeholder="" class="default_input con_wrap_70" id="address4" name="address4">										
												</td>
											</tr>
											<tr>
												<th>사업장전화</th>
												<td><input type="text" class="default_input w100" id="proprietorBusinessTel" name="proprietorBusinessTel" numberTel maxlength="13"></td>
												<th>업무담당자</th>
												<td><input type="text" class="default_input w100" id="proprietorStaffNm" name="proprietorStaffNm"></td>
												<th>담당자연락처</th>
												<td><input type="text" class="default_input w120" id="proprietorStaffTel" name="proprietorStaffTel" numberPhone maxlength="13"></td>
											</tr>
											<tr>
												<th>부서</th>
												<td><input type="text" class="default_input w100" id="proprietorDepartment" name="proprietorDepartment"></td>
												<th>담당자이메일</th>
												<td colspan="3"><input type="text" class="default_input" id="proprietorStaffEmail" name="proprietorStaffEmail"></td>
											</tr>
										</table>
									</div>
								</div>
								</form>
								<div class="button_wrap">
									<a href="#;" class="basin_btn" onclick="saveBtn2()">저장</a>
								</div>						
							</article>
							
						</div>
					</li>				
				</ul>
			</nav>
		</div>
	</div>
</div>

<!-- 우편번호 주소 팝업 -->
<div id="zipcode_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">주소 검색</p>
			<a class="right zipcode_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<div class="pop_con">
			<div id="layer"></div>
		</div>
	</div>
</div>
<!-- 우편번호 주소 팝업 END-->

<!-- 구성원 추가 팝업 -->
<div id="resident_add_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">구성원 추가</p>
			<a class="right resident_add_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<div class="pop_con">
			<div class="con_wrap_100">
				<form id="frm5" name="frm5" action="insertMemberHousehold" method="post">
				<input type="hidden" name="id" id="vehicleId" value="" />
				<input type="hidden" name="name" value="" />
				<input type="hidden" name="companyId" value="" />
				<input type="hidden" name="tenantId" value="" />
				<input type="hidden" name="conttt" id="conttt" value="0" />
				
				<table class="view_top_table">
					<tr>
						<th>성명</th>
						<td><input type="text" class="default_input" id="memberName" name="memberName"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<div class="cal_wrap">
								<input type="text" class="date_input" id="birthday3" name="birthday3">
								<p class="cal_icon">
									<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
								</p>
							</div>							
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<select class="default_select" id="gender3" name="gender3">
								<option value="1">남</option>
								<option value="2">여</option>
							</select>						
						</td>
					</tr>
					<tr>
						<th>관계</th>
						<td><input type="text" class="default_input" id="relationship" name="relationship"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" class="default_input" id="memberTel" name="memberTel" numberTel maxlength="13"></td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td><input type="text" class="default_input" id="memberCellphone" name="memberCellphone" numberphone maxlength="13"></td>
					</tr>
					<tr>
						<th>근무처</th>
						<td><input type="text" class="default_input" id="memberOffice" name="memberOffice"></td>
					</tr>
					<tr>
						<th>직업</th>
						<td><input type="text" class="default_input" id="memberJob" name="memberJob"></td>
					</tr>
					<tr>
						<th>메모</th>
						<td><input type="text" class="default_input con_wrap_100" id="memberMemo" name="memberMemo"></td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn add" onclick="memberSev()">추가</a>
			<a href="#;" class="basin_btn modify" style="display: none;" onclick="memberSev()">수정</a>
			<a href="#;" class="basin_btn del" style="display: none;" onclick="memberDel()">삭제</a>
			<a href="#;" class="basin_btn resident_add_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 구성원 추가 팝업 END-->

<!-- 차량 추가 팝업 -->
<div id="car_add_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">차량 추가</p>
			<a class="right car_add_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<div class="pop_con">
			<div class="con_wrap_100">
				<form id="frm6" name="frm6" action="tenantInsertVehicle" method="post">
				<input type="hidden" name="id" value="carId" value="" />
				<input type="hidden" name="name" value="" />
				<input type="hidden" name="tenantId" value="" />
				<input type="hidden" name="flagNum" id="flagNum" value="1" />
				
				<table class="view_top_table">
			<tr>
				<th colspan="2"><p class="p_required">*</p>동</th>
				<td><input type="text" class="default_input w50" id="cardong" name="cardong" readonly="readonly"></td>
				<th><p class="p_required">*</p>호</th>
				<td><input type="text" class="default_input w50" id="carho" name="carho" readonly="readonly"></td>
			</tr>
			<tr>
				<th colspan="2"><p class="p_required">*</p>차량번호</th>
				<td><input type="text" class="default_input" id="carNum" name="carNum"></td>
				<th><p class="p_required">*</p>입주일</th>
				<td>
					<div class="cal_wrap">
						<input type="text" class="default_input" id="carenterDt" name="carenterDt" readonly="readonly">
						<p class="cal_icon">
							<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
						</p>
					</div>					
				</td>
			</tr>
			<tr>
				<th colspan="2"><p class="p_required">*</p>등록일</th>
				<td>
					<div class="cal_wrap">
						<input type="text" class="date_input" id="registrationDt" name="registrationDt">
						<p class="cal_icon">
							<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
						</p>
					</div>					
				</td>
				<th>해지일</th>
				<td>
					<div class="cal_wrap">
						<input type="text" class="date_input" id="cancelDt" name="cancelDt">
						<p class="cal_icon">
							<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
						</p>
					</div>					
				</td>
			</tr>
			<tr>
				<th colspan="2"><p class="p_required">*</p>등록인</th>
				<td><input type="text" class="default_input" id="registrant" name="registrant"></td>
				<th><p class="p_required">*</p>연락처</th>
				<td><input type="text" class="default_input" id="cartel" name="cartel" numberphone maxlength="13"></td>
			</tr>
			<tr>
				<th colspan="2">차종</th>
				<td><input type="text" class="default_input" id="carType" name="carType"></td>
				<th>고유번호</th>
				<td><input type="text" class="default_input" id="serialNum" name="serialNum" numberOnly></td>
			</tr>
			<tr>
				<th colspan="2">스티커발급번호</th>
				<td><input type="text" class="default_input" id="stickerIssueNum" name="stickerIssueNum" numberOnly></td>
				<th>스티커발급일</th>
				<td>
					<div class="cal_wrap">
						<input type="text" class="date_input" id="stickerIssueDt" name="stickerIssueDt">
						<p class="cal_icon">
							<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
						</p>
					</div>					
				</td>
			</tr>
			<tr>
				<th rowspan="4">사양</th>
				<th>색상</th>
				<td><input type="text" class="default_input" id="specColor" name="specColor"></td>
				<th>구분</th>
				<td>
					<select class="default_select" id="division" name="division">
						<option value="1">무료</option>
						<option value="2">A</option>
						<option value="3">B</option>
						<option value="4">C</option>
					</select>				
				</td>
			</tr>
			<tr>
				<th>년식</th>
				<td><input type="text" class="default_input" id="specYear" name="specYear" numberOnly></td>
				<th><p class="p_required">*</p>주차비부과예정금액</th>
				<td><input type="text" class="default_input" id="parkingFee" name="parkingFee" numberOnly></td>
			</tr>
			<tr>
				<th>배기량</th>
				<td><input type="text" class="default_input" id="specDisplacement" name="specDisplacement"></td>
				<th>비고</th>
				<td><input type="text" class="default_input" id="comment" name="comment"></td>
			</tr>
			<tr>
				<th>분류</th>
				<td><input type="text" class="default_input" id="specClassification" name="specClassification"></td>
				<th>제조사</th>
				<td><input type="text" class="default_input" id="specManufacturer" name="specManufacturer"></td>
			</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn add" onclick="carSev()">추가</a>
			<a href="#;" class="basin_btn modify" style="display: none;" onclick="carSev()">수정</a>
			<!-- <a href="#;" class="basin_btn del" style="display: none;" onclick="carDel()">삭제</a> -->
			<a href="#;" class="basin_btn car_add_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 차량 추가 팝업 END-->

<!-- 합산동호 등록 팝업 -->
<div id="dongho_add_popup" class="mres_popup">
	<div class="pop" style="min-width:400px !important; width:400px">
		<div class="pop_top">
			<p class="popup_title">합산동호 등록</p>
			<a class="right dongho_add_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<div class="pop_con">
			<div class="list_wrap">
				<table id="list4" class="jq_table"></table>
			</div>
		</div>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="saveBtn3()">저장</a>
			<a href="#;" class="basin_btn dongho_add_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 합산동호 등록 팝업 END-->

<!-- 우편번호 주소 팝업 -->
<div id="zipcode_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">주소 검색</p>
			<a class="right zipcode_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<div class="pop_con">
			<div id="layer"></div>
		</div>
	</div>
</div>
<!-- 우편번호 주소 팝업 END-->

<form id="frmBill" name="frmBill" action="updateBillSum" method="post">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="ids" value="" />
	<input type="hidden" name="rowId" value="" />
	<input type="hidden" name="billSumChk" value="" />
	<input type="hidden" name="billSumId" value="" />
</form>
<form id="frmDelBill" name="frmDelBill" action="deleteBillSum" method="post">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="id" value="" />
	<input type="hidden" name="dong" value="" />
	<input type="hidden" name="ho" value="" />
	<input type="hidden" name="rowId" value="" />
	
</form>