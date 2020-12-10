<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
var selCompanyId = '';
var selRowId = '';
$(function() {
	selCompanyId = '${selCompanyId}';
	selRowId = '${selRowId}';
	
	if(selCompanyId != ''){
		$("#selOfficeList").val(selCompanyId);
	}else{
		selCompanyId = $("#selOfficeList").val(); 
	}
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres01/mres0103.js"></script>

<iframe id="print0103_1" name="print0103_1" frameborder="0" width="1250px" height="100px" scrolling="yes" style="display:none"></iframe>
<form name="frmPrint1" action="<%=request.getContextPath()%>/print/0103_1" method="POST">
	<input type="hidden" name="residentGroupId" value="" />
	<input type="hidden" name="companyNm" value="" />
	<input type="hidden" name="residentGroupNm" value="" />
</form>
<div class="margin_t_10">
	<div class="con_wrap con_wrap_30 padding_r_10">
		<div class="button_wrap">
			<div class="box_title_con">
				<div class="title_circle"></div>
				<div class="box_title">단체명</div>
			</div>
			<a href="#;" class="basin_btn" onclick="groupPop()">단체관리</a>
		</div>
		<div class="list_wrap margin_t_10">
			<table id="list" class="jq_table"></table>
		</div>
	</div>
	<div class="con_wrap con_wrap_70 padding_l_10">
		<div class="button_wrap">
			<div class="box_title_con">
				<div class="title_circle"></div>
				<div class="box_title">임원</div>
			</div>
			<a href="#;" class="basin_btn" onclick="historyPop()">임원이력</a>
			<a href="#;" class="basin_btn" onclick="regPop()">임원등록</a>
			<a href="#;" class="basin_btn" onclick="selectDel()">선택삭제</a>
			<a href="#;" class="basin_btn" onclick="execPrint('print0103_1')">인쇄</a>
 			<a href="#;" class="basin_btn" onclick="excelDownloadBtn()">엑셀다운로드</a> 
			
			<form id ="frmExcel" name ="frmExcel" action="excelDownload" method="post">
				<input type="hidden" name="residentGroupId" value="" />	
				<input type="hidden" name="companyNm" value="" />
				<input type="hidden" name="residentGroupNm" value="" />			
				<input type="hidden" name="fileNm" value="입주자단체" />			
			</form>
		</div>
		<div class="list_wrap margin_t_10">
			<table id="list2" class="jq_table"></table>
		</div>
	</div>
</div>

<form id="frmDel" name="frmDel" action="deleteExecutives" method="post">
	<input type="hidden" name="companyId" />
	<input type="hidden" name="rowid" />
	<input type="hidden" name="residentGroupId" />
</form>

<form id="frmResidentDel" name="frmResidentDel" action="deleteResidentGroup" method="post">
	<input type="hidden" name="companyId" />
	<input type="hidden" name="residentGroupId" />
</form>

<!-- 입주자 단체 관리 팝업 -->
<div id="group_popup" class="mres_popup">
	<div class="pop" style="width:880px">
		<div class="pop_top">
			<p class="popup_title">입주자 단체 관리</p>
			<a class="right group_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frmGroup" name="frmGroup" action="insertResidentGroup" method="POST"  autocomplete="false" >
		<input type="hidden" name="flagNum" value="0">
		<input type="hidden" name="companyId" >
		<input type="hidden" name="residentGroupId">
		<div class="pop_con">
			<div class="con_wrap con_wrap_50 padding_r_10">
				<div class="button_wrap">
					<a href="#;" class="basin_btn" onclick="regResidentGroup()">단체 등록</a>
					<a href="#;" class="basin_btn" onclick="selectResidentGroupDel()">삭제</a>
				</div>
				<div class="list_wrap margin_t_10">
					<table id="list3" class="jq_table"></table>
				</div>
			</div>
			<div class="con_wrap con_wrap_50 padding_l_10">
				<div class="box_title_con">
					<div class="title_circle"></div>
					<div class="box_title">단체명</div>
				</div>
				<div>
					<table class="view_top_table">
						<tr>
							<th>단체명</th>
							<td><input type="text" placeholder="" id = "groupNm" class="default_input con_wrap_100" name="groupNm" readonly="readonly"></td>
						</tr>
					</table>
				</div>
				<div class="box_title_con">
					<div class="title_circle"></div>
					<div class="box_title">직책</div>
				</div>
				<div>
					<table class="view_top_center_table" id="positionTb">
						<thead>
							<tr>
<!-- 								<th>코드</th> -->
								<th>직책</th>
							</tr>
						</thead>
						<tbody>
							<tr>
<!-- 								<td><input type="text" placeholder="" class="default_input w70" name="positionCd[]" readonly="readonly"></td> -->
								<td>
									<input type="hidden" name="positionCd">
									<input type="text" placeholder="" class="default_input w250" name="positionNm">
									<a href="#;" class="type1_btn" onclick="positionPlus()"><i class="fa fa-plus"></i></a>
									<a href="#;" class="type1_btn positionMinus" style="display:none"><i class="fa fa-minus"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="box_title_con">
					<div class="title_circle"></div>
					<div class="box_title">계좌용도</div>
				</div>
				<div>
					<table class="view_top_center_table" id="accountTb">
						<thead>
							<tr>
<!-- 								<th>코드</th> -->
								<th>계좌용도</th>
							</tr>
						</thead>
						<tbody>
							<tr>
<!-- 								<td><input type="text" placeholder="" class="default_input w70" name="accountCd[]" readonly="readonly"></td> -->
								<td>
									<input type="hidden" name="accountCd">
									<input type="text" placeholder="" class="default_input w250" name="accountNm">
									<a href="#;" class="type1_btn" onclick="accountPlus()"><i class="fa fa-plus"></i></a>
									<a href="#;" class="type1_btn accountMinus" style="display:none"><i class="fa fa-minus"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="saveGroupBtn()">저장</a>
			<a href="#;" class="basin_btn group_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 입주자 단체 관리 팝업 END-->

<!-- 임원 등록 팝업 -->
<div id="reg_popup" class="mres_popup">
	<div class="pop">
		<div class="pop_top">
			<p class="popup_title">임원 등록</p>
			<a class="right reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frm" name="frm" action="insertExecutives" method="post" autocomplete="off">
		<input type="hidden" name="companyId" value="">
		<input type="hidden" name="flagNum" value="">
		<input type="hidden" id="residentGroupIdChk" name="residentGroupIdChk" value="">
		<input type="hidden" id="dutyChk" name="dutyChk" value="">
		<input type="hidden" id="id" name="id" value="">
		<div class="pop_con">
			<div>
				<table class="view_top_table">
					<tr>
						<th>단체명</th>
						<td>
							<div>
								<select class="default_select selResidentGroup" id="residentGroupId" name="residentGroupId" onchange="regResidentGroupChg(this.value)">
								<option>-----------</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>직책</th>
						<td>
							<div>
								<select class="default_select" id="duty" name="duty">
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>동</th>
						<td><input type="text" placeholder="" class="default_input" id="dong" name="dong" numberOnly></td>
					</tr>
					<tr>
						<th>호</th>
						<td><input type="text" placeholder="" class="default_input" id="ho" name="ho" numberOnly></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" placeholder="" class="default_input" id="name" name="name"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" placeholder="" class="default_input" id="tel" name="tel" numberTel></td>
					</tr>
					<tr>
						<th>임기시작일</th>
						<td>
							<div class="cal_wrap">
								<input type="text" class="date_input" id="periodFr" name="periodFr">
								<p class="cal_icon">
									<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<th>임기만료일</th>
						<td>
							<div class="cal_wrap">
								<input type="text" class="date_input" id="periodTo" name="periodTo">
								<p class="cal_icon">
									<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" placeholder="" class="default_input con_wrap_100" id="comment" name="comment"></td>
					</tr>
				</table>
			</div>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="saveBtn()">등록</a>
			<a href="#;" class="basin_btn reg_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 임원 등록 팝업 END-->

<!-- 임원 이력 팝업 -->
<div id="history_popup" class="mres_popup">
	<div class="pop" style="width: 900px">
		<div class="pop_top">
			<p class="popup_title">임원 이력</p>
			<a class="right history_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frmHistory" name="frmHistory" method="POST"  autocomplete="off" >
		<input type="hidden" name="flagNum">
		<div class="pop_con">
			<div class="search_wrap">
				<div class="search_con">
					<div class="search_title">단체명</div>
					<div>
						<select class="default_select selResidentGroup" id="residentGroupId2" name="residentGroupId">
						</select>
					</div>
				</div>
				<div class="search_bar"></div>
				<div class="search_con">
					<div class="search_title">년도</div>
					<div class="cal_wrap">
						<input type="text" placeholder="" id="periodFrYear" name="periodFrYear" class="default_input" numberOnly>
						<p class="cal_icon">
							<img src="<%=request.getContextPath()%>/resources/img/calendaricon.png">
						</p>
					</div>
				</div>
				<a href="#;" class="basin_btn" onclick="searchHistoryBtn()">검색</a>
			</div>
			<div class="con_wrap con_wrap_100 margin_t_10">
					<table id="list4" class="jq_table"></table>
			</div>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn history_popup_close">닫기</a>
		</div>
	</div>
</div>
<!-- 임원 이력 팝업 END-->