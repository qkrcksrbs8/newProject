<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">

var searchCL = '';
var searchCheck = '';
var searchTxt ='';

$(function() {
	$(".sub_office_list_wrap").hide();
	
	searchCL = '${searchCL}';
	searchTxt = '${searchT}';
	
	searchCheck = '${searchCheck}';
});
</script>


<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres06/mres0602.js"></script>

<div class="search_wrap">
	<div class="search_con">
		<div class="search_title">사업장구분</div>
		<div>
			<select class="default_select selCompanyId" id="selCompanyList" name="selCompanyList" onchange="serachChcekTrade()">
			</select>
		</div>
	</div>
	<div class="search_bar"></div>
	<div class="search_con">
		<div class="search_title">제목 및 내용</div>
		<div><input type="text" class="default_input w250" id="searchTxt" name="searchTxt" value="${searchT}" onchange="serachChcekTrade()"></div>
	</div>
	<div class="right">
		<a href="#;" class="basin_btn" onclick="searchBtn()">검색</a>
	</div>
</div>

<div class="margin_t_10 con_wrap con_wrap_100">
	<div class="con_wrap con_wrap_100 padding_l_10">
		<div class="button_wrap">
			<a href="#;" class="basin_btn" onclick="regNotice()">공지사항등록</a>
			<a href="#;" class="basin_btn" onclick="delNotice()">선택삭제</a>
		</div>	
	</div>	
</div>

<div class="margin_t_10 con_wrap con_wrap_100">
	<div class="list_wrap">
		<table id="list1" class="jq_table"></table>
	</div>
</div>

<!-- 공지사항 등록 팝업 -->
<div id="notice_reg_popup" class="mres_popup">
	<div class="pop" style="width: 545px">
		<div class="pop_top">
			<p class="popup_title">공지사항 등록</p>
			<a class="right notice_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
		</div>
		<form id="frmNotice" name="frmNotice" action="insertNotice" method="post" autocomplete="off" enctype="multipart/form-data">
		<input type="hidden" id="flagNum" name="flagNum" value="" />
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" name="selCompanyList"/>
		<input type="hidden" name="searchTxt"/>
		<input type="hidden" name="searchCheck" />
		

		<div class="pop_con">
			<table class="view_top_table">
				<tr>
					<th>프로그램 구분</th>
					<td>
						<div>
							<select class="default_select" name="mresFlag" id="mresFlag">
								<option value="a">전체</option>
								<option value="m">관리비용</option>
								<option value="r">임대비</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th>사업장 구분</th>
					<td>
						<div>
							<select class="default_select selCompanyId" name="companyId" id="companyId">
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th>중요여부</th>
					<td>
						<div class="radio_con">
							<label class="control control_radio">
								<span>일반공지</span>
								<input type="radio" id="importantFlag" name="importantFlag" value="0" checked="checked" />
								<div class="control_indicator"></div>
							</label>
						</div>
						<div class="radio_con">
							<label class="control control_radio">
								<span>중요공지</span>
								<input type="radio" id="importantFlag" name="importantFlag" value="1" />
								<div class="control_indicator"></div>
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" placeholder="" class="default_input con_wrap_100" id="title" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="default_textarea" placeholder="" id="memo" name="memo"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
					<input type="file" placeholder="" class="default_input" id="file" name="file">
					<input type="text" class="default_input" id="filename" name="filename" readonly="readonly">
					</td>
				</tr>
			</table>
		</div>
		</form>
		<div class="pop_bottom">
			<a href="#;" class="basin_btn" onclick="noticeSaveBtn()">저장</a>
			<a href="#;" class="basin_btn notice_reg_popup_close">취소</a>
		</div>
	</div>
</div>
<!-- 공지사항 등록 팝업 END-->

<form id="del" name="del" action="deleteNotice" method="post">
	<input type="hidden" name="id" />
	<input type="hidden" name="selCompanyList"/>
	<input type="hidden" name="searchTxt"/>
	<input type="hidden" name="searchCheck" />
	
</form>





