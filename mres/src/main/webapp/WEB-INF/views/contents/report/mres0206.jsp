<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
$(function() {
	
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres02/mres0206.js"></script>

<iframe id="print0206_1" name="print0206_1" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<iframe id="print0206_2" name="print0206_2" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<iframe id="print0206_3" name="print0206_3" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<form name="frmPrint1" action="<%=request.getContextPath()%>/print/0206_1" method="POST">
	<input type="hidden" name="companyId" value="" />
</form>
<form name="frmPrint2" action="<%=request.getContextPath()%>/print/0206_2" method="POST">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="repairItemId" value="" />
	<input type="hidden" name="exclusiveArea" value="" />
	<input type="hidden" name="dateFr" value="" />
	<input type="hidden" name="dateTo" value="" />
	<input type="hidden" name="dong" value="" />
	<input type="hidden" name="ho" value="" />
	<input type="hidden" name="moveOutDt" value="" />
</form>
<form name="frmPrint3" action="<%=request.getContextPath()%>/print/0206_3" method="POST">
	<input type="hidden" name="companyId" value="" />
	<input type="hidden" name="repairItemId" value="" />
	<input type="hidden" name="exclusiveArea" value="" />
	<input type="hidden" name="dateFr" value="" />
	<input type="hidden" name="dateTo" value="" />
	<input type="hidden" name="dong" value="" />
	<input type="hidden" name="ho" value="" />
	<input type="hidden" name="moveOutDt" value="" />
</form>

<div class="search_wrap">
	<div class="search_con">
		<div class="search_title">수선항목</div>
		<div>
			<select class="default_select selRepairItem" id="selRepairItem" name="selRepairItem">
			</select>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">계산처리</div>
		<div>
			<input type="text" class="default_input" id="" name="" readOnly>
		</div>								
	</div>
	<div class="search_bar"></div>
		<div class="search_con">
		<div class="search_title">계산방법</div>
		<div>
			<input type="text" class="default_input" id="" name="" readOnly>
		</div>	
	</div>							
</div>

<form id="frmChk" name="frmChk" action="" method="post">
	<input type="hidden" name="companyId" />
	<input type="hidden" name="flagNum" value="0">
</form>
<div class="con_wrap con_wrap_100 margin_t_10">
	<nav class="nav_tabs">
		<ul>
			<li>
				<input type="radio" name="tabs" class="rd_tabs" id="tab1" checked> 
				<label for="tab1">계산하기</label>
				<div class="tab_con">
					<article>
						<div>
							<table class="view_table">
								<colgroup>
									<col width="5%">
									<col width="*">
									<col width="5%">
									<col width="7%">
									<col width="7%">
									<col width="12%">
									<col width="7%">
									<col width="8%">
									<col width="8%">
									<col width="20%">
								</colgroup>
								<tr class="h60">
									<th>동호</th>
									<td>
										<div class="search_wrap_dong">
											<input type="text" class="default_input" id="searchDong" name="searchDong" autocomplete="false" numberOnly>
											<span>동</span>
											<input type="text" class="default_input" id="searchHo" name="searchHo" autocomplete="false" numberOnly>
											<span>호</span>
											<a href="#;" class="basin_btn2 dong_search_btn" onclick="dongHoSearch()"><i class="fa fa-search"></i></a>
										</div>									
									</td>
									<th>면적</th>
									<td><span id="exclusiveArea"></span></td>
									<th>주거구분</th>
									<td><span id="residence"></span></td>
									<th>전입일자</th>
									<td><span id="enterDt"></span></td>
									<th>전출일자</th>
									<td>
										<div class="cal_wrap margin_t_5">
											<input type="text" class="date_input" id="moveOutDt" name="moveOutDt">
											<p class="cal_icon">
												<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
											</p>
										</div>		
										<div class="right">
											<a href="#;" class="basin_btn" onclick="execCalculation()">계산</a>
										</div>							
									</td>
								</tr>
							</table>					
						</div>
						<div>
							<div class="button_wrap">
								<a href="#;" class="basin_btn" onclick="printOption()">인쇄옵션</a>
							</div>
						</div>
						
						<div class="print_wrap">
							<div class="print_img"><p class="icon"><img src="<%=request.getContextPath()%>/resources/img/print.png" alt=""/></p><span>인쇄옵션</span></div>
							<div class="print_con_wrap">	
								<div class="print_con">
									<div class="print_title">출력형식</div>
									<div>
										<select class="default_select" id="selPrintFlag" name="selPrintFlag">
											<!-- <option value="1">구양식</option> -->
											<option value="2">관리소/전출</option>
											<option value="3">관리소/전출/소유</option>
										</select>
									</div>
								</div>
							</div>	
							<div class="print_button_wrap"><a href="#;" class="basin_btn" onclick="execPrintFlag()">인쇄</a></div>
						</div>													
<!-- 						<div class="con_wrap margin_t_10 con_wrap_100"> -->
<!-- 							<div> -->
<!-- 								<table class="view_top_table"> -->
<%-- 									<colgroup> --%>
<%-- 										<col width="10%"> --%>
<%-- 										<col width="10%"> --%>
<%-- 										<col width="*"> --%>
<%-- 										<col width="10%"> --%>
<%-- 										<col width="10%"> --%>
<%-- 										<col width="15%"> --%>
<%-- 										<col width="10%"> --%>
<%-- 										<col width="15%"> --%>
<%-- 									</colgroup> --%>
<!-- 									<tr class="h35"> -->
<!-- 										<th rowspan="2">산출기간</th> -->
<!-- 										<th>시작일자</th> -->
<!-- 										<td></td> -->
<!-- 										<th rowspan="2">단가</th> -->
<!-- 										<th>구분</th> -->
<!-- 										<td></td> -->
<!-- 										<th rowspan="2">월정액</th> -->
<!-- 										<td rowspan="2"></td> -->
<!-- 									</tr> -->
<!-- 									<tr class="h35"> -->
<!-- 										<th>종료일자</th> -->
<!-- 										<td></td> -->
<!-- 										<th>금액</th> -->
<!-- 										<td></td> -->
<!-- 									</tr> -->
<!-- 								</table> -->
<!-- 							</div> -->
<!-- 						</div>		 -->
						<div class="box_title_con margin_t_10">
							<div class="title_circle"></div>
							<div class="box_title">계산이력</div>
						</div>
						<div class="con_wrap margin_t_10 con_wrap_100">
							<div class="list_wrap">
								<table id="list1" class="jq_table"></table>
							</div>
						</div>							
					</article>
				</div>
			</li>
			<li>
				<input type="radio" name="tabs" class="rd_tabs" id="tab2">
				<label for="tab2">단가표</label>
				<div class="tab_con">
					<article>
						<div>
							<div class="button_wrap">
								<a href="#;" class="basin_btn" onclick="delRepairPrice()">수선단가삭제</a>								
								<a href="#;" class="basin_btn" onclick="regRepairPrice()">수선단가등록</a>								
								<a href="#;" class="basin_btn" onclick="regRepairItem()">수선항목등록</a>
							</div>
						</div>
						<div class="con_wrap margin_t_10 con_wrap_100">
							<div class="list_wrap">
								<table id="list2" class="jq_table"></table>
							</div>
						</div>														
					</article>
				</div>
			</li>
		</ul>
	</nav>
</div>

<!-- 수선단가 등록 팝업 -->
<div id="repair_price_reg_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">수선단가 등록</p>
			<a class="right repair_price_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frm1" name="frm1" action="insertRepairPrice" method="post" autocomplete="off">
		<input type="hidden" name="companyId" value="">
		<input type="hidden" name="flagNum" value="1">
		<input type="hidden" name="repairItemId" value="">
		<div class="pop_con">
			<table class="view_top_table">
				<tr>
					<th rowspan="2">동</th>
					<th>시작</th>
					<td><input type="text" class="default_input" id="dongFr" name="dongFr" numberOnly></td>				
				</tr>
				<tr>
					<th>종료</th>
					<td><input type="text" class="default_input" id="dongTo" name="dongTo" numberOnly></td>					
				</tr>
				<tr>
					<th rowspan="2">면적범위</th>
					<th>시작</th>
					<td><input type="text" class="default_input" id="areaFr" name="areaFr" numberOnly></td>				
				</tr>
				<tr>
					<th>종료</th>
					<td><input type="text" class="default_input" id="areaTo" name="areaTo" numberOnly></td>					
				</tr>
				<tr>
					<th rowspan="2">단가</th>
					<th>구분</th>
					<td>
						<select class="default_select" id="unit" name="unit">
							<option value="1">㎡</option>
							<option value="2">세대단가</option>
						</select>					
					</td>
				</tr>
				<tr>
					<th>금액</th>
					<td><input type="text" class="default_input" id="unitPrice" name="unitPrice" numberOnly></td>					
				</tr>
				<tr>
					<th rowspan="2">산출기간</th>
					<th>시작일자</th>
					<td>
						<div class="cal_wrap">
							<input type="text" class="date_input" id="dateFr" name="dateFr">
							<p class="cal_icon">
								<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
							</p>
						</div>					
					</td>				
				</tr>
				<tr>
					<th>종료일자</th>
					<td>
						<div class="cal_wrap">
							<input type="text" class="date_input" id="dateTo" name="dateTo">
							<p class="cal_icon">
								<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
							</p>
						</div>						
					</td>					
				</tr>
				<tr>
					<th colspan="2">적용방법</th>
					<td>
						<select class="default_select" id="applicationMethod" name="applicationMethod">
							<option value="0">십단위사사오입</option>
							<option value="1">십단위절사</option>
							<option value="2">십단위절상</option>
							<option value="3">원단위사사오입</option>
							<option value="4">원단위절사</option>
							<option value="5">원단위절상</option>
							<option value="6">소수1자리사사오입</option>
							<option value="7">소수1자리절사</option>
							<option value="8">소수1자리절상</option>
						</select>					
					</td>
				</tr>								
			</table>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="saveBtnRepairPrice()">저장</a>
			<a href="#;" class="basin_btn repair_price_reg_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 수선단가 등록 팝업 END-->

<!-- 수선항목 등록 팝업 -->
<div id="repair_item_reg_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">수선항목 등록</p>
			<a class="right repair_item_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frm2" name="frm2" action="insertRepairItem" method="post" autocomplete="off">
		<input type="hidden" name="companyId" value="">
		<input type="hidden" name="flagNum" value="1">
		<input type="hidden" id="id" name="id"/>
		<div class="pop_con">
			<div class="button_wrap">
				<!--<a href="#;" class="basin_btn" onclick="">신규등록</a>-->
				<a href="#;" class="basin_btn" onclick="delRepairItem()">선택삭제</a>
			</div>
			<div class="margin_t_10">
				<div class="con_wrap con_wrap_50 padding_r_10">
					<div class="list_wrap">
						<table id="list3" class="jq_table"></table>			
					</div>
				</div>
				<div class="con_wrap con_wrap_50 padding_l_10">
					<table class="view_table">
						<tr>
							<th>항목명</th>
							<td><input type="text" class="default_input" id="itemNm" name="itemNm"></td>
						</tr>	
						<tr>
							<th>면적구분</th>
							<td>
								<select class="default_select" id="areaDiv" name="areaDiv">
									<option value="1">분양면적</option>
									<option value="2">전용면적</option>
								</select>					
							</td>
						</tr>								
					</table>
				</div>
			</div>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="saveBtnRepairItem()">저장</a>
			<a href="#;" class="basin_btn repair_item_reg_popup_close">취소</a>
		</div>				
	</div>
</div>
<form id="frmRepairItemDel" name="frmRepairItemDel" action="deleteRepairItem" method="post">
	<input type="hidden" name="companyId" />
	<input type="hidden" name="id" />
</form>
<form id="frmRepairPriceDel" name="frmRepairPriceDel" action="deleteRepairPrice" method="post">
	<input type="hidden" name="companyId" />
	<input type="hidden" name="id" />
</form>
<!-- 수선항목 등록 팝업 END-->