<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
 .date_input{			/* input type="date" 수정	*/
 	width:150px;		/* 넓이는 150px 			*/
 	padding-right:5px; 	/* 우측 5px만큼 간격			*/
 }
 
  .rightBtn{
 	float: right;		/* 저장, 수정, 삭제, 행추가, 인쇄 버튼 우측 정렬 */
 }
 
 .textarea_size{
	resize:none;		/* 체크박스 사이즈 조정 불가 */
 	width:300px;		/* 가로300px */
 	height:210px;		/* 세로210px */
 }
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/repairMstDetails.js"></script>

<script type="text/javascript">

$(function(){

	$(".sub_title").text("하자보수현황");//서브타이틀
	var selectFrCal = "${fr_cal}";	//시작일
	var selectToCal = "${to_cal}";	//종료일
	var addList = "${addList}";		//add:행추가 / normal:일반 출력
	
	$("#frCal").val(selectFrCal);	//검색 후 시작일 셋팅
	$("#toCal").val(selectToCal);	//걸색 후 종료일 셋팅
	
	//-----
	//행추가
	//-----
	if("add" == addList){
		
		$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
		$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
		$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
		$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
		
	};//if
   	
	$("#schedule_reg_popup").popup('hide');//팝업 종료	
	
});

</script>

<body>

<div class="container">
	
	<!-- 달력 -->
	<div class="search_con clear">
		<div class="search_title">검색일자</div>
		<div>
			<div class="cal_wrap">
				<input type="date" class="date_input select_cal" id="frCal" name="frCal">
			</div>
			<p class="cal_to">~</p>
			<div class="cal_wrap">
				<input type="date" class="date_input select_cal" id="toCal" name="toCal">
			</div>
		</div>
	</div>
		
	<!-- 버튼 모음입니다. --> 
	<button id="tablePrint"	class="basin_btn rightBtn" onclick="window.print()">인쇄</button>
	<button id="tableAdd"	class="basin_btn rightBtn">행추가</button>	
	<button id="tableDel"	class="basin_btn rightBtn">삭제</button>	
	<button id="tableUp"	class="basin_btn rightBtn">수정</button>	
	<button id="tableSave"	class="basin_btn rightBtn">저장</button>	
	
<!-- 게시글 있을 때. --> 
	<table class="view_top_center_table">
		<tr> 
			<td >체크</td>
			<td >부문</td>
			<td >예정업무</td>
			<td >실시업무</td>
			<td >REMARK</td>
		</tr>
			<c:set var="number" value="${selectRepairCnt}" />
			<c:forEach var="selectRepairList" items="${selectRepairList}" varStatus="detailedWorkNum">
				<c:if test = "${selectRepairList.repair_seq == 0}">
					<tr>
						<td rowspan="2" class=" tableCount"><input type="checkbox" id="table_check" name="table_check" value="${selectRepairList.repair_seq}" checked></td>
						<td rowspan="2"><label >${selectRepairList.created_date}</label></td>
						<td class="" >	<input class="default_input con_wrap_100" id="fr_work" 	type="text"	value="${selectRepairList.fr_work}" maxlength="150"></td>
						<td class="" >	<input class="default_input con_wrap_100" id="to_work" 	type="text"	value="${selectRepairList.to_work}" maxlength="150"></td>
						<td rowspan="2"><textarea class="default_textarea maxVal maxVal" style="resize:none;">${selectRepairList.remark}</textarea></td>
					</tr>
					<tr>
						<td class=""><input class="default_input con_wrap_100"	type="text"	value="저장 후 이미지 등록을 해주세요." readonly="readonly"></td> 
						<td class=""><input class="default_input con_wrap_100"	type="text"	value="저장 후 이미지 등록을 해주세요." readonly="readonly"></td>
					</tr>
				</c:if>
				<c:if test = "${selectRepairList.repair_seq != 0}">
					<tr>
						<td rowspan="2" class=" tableCount"><input type="checkbox" id="table_check" name="table_check" value="${selectRepairList.repair_seq}"></td>
						<td rowspan="2"><label>${selectRepairList.created_date}</label></td>
						<td class="" >	<input class="default_input con_wrap_100" id="fr_work" 	type="text"	value="${selectRepairList.fr_work}" readonly="readonly" maxlength="150"></td>
						<td class="" >	<input class="default_input con_wrap_100" id="to_work" 	type="text"	value="${selectRepairList.to_work}" readonly="readonly" maxlength="150"></td>
						<td rowspan="2"><textarea class="default_textarea textarea_size maxVal maxLen" style="resize:none; " readonly="readonly">${selectRepairList.remark}</textarea></td>
					</tr>
					<tr>
					
						<c:if test = "${selectRepairList.fr_img_name != ''}">
							<td class=frUploadPopup><img src="${selectRepairList.fr_img_path}" width="300" height="210"></td> 
						</c:if>
						<c:if test = "${selectRepairList.fr_img_name == ''}">
							<td class="frUploadPopup"><label style="width:300px; height:210px;" >이미지 업로드</label></td>
						</c:if>
						<c:if test = "${selectRepairList.to_img_name != ''}">
						<td class="toUploadPopup"><img src="${selectRepairList.to_img_path}" width="300" height="210"></td> 
						</c:if>
						<c:if test = "${selectRepairList.to_img_name == ''}">
							<td class="toUploadPopup"><label class="width="300" height="210"">이미지 업로드</label></td>
						</c:if>
					</tr> 
				</c:if>
			</c:forEach>
	</table>
	
	<!-- 게시글 없을 때. -->
	<c:if test="${selectRepairCnt==0}">
		<section id="content">
			<div class="container">
				<div class="board-list-wrap board-list-header">
					<ul class="row">	
						<li>게시판에 저장된 글이 없습니다.</li>
					</ul>
				</div>
			</div>
		</section>
	</c:if>
	
	<!-- 셀렉트박스 조회용 히든 폼 -->
	<form id="selectForm" name="selectForm"  action="repairList" autocomplete="off">
		<input type="hidden" id="baseYear"	name="baseYear"	value ="">
		<input type="hidden" id="addList" 	name ="addList" value ="">
		<input type="hidden" id="fr_cal" 	name ="fr_cal"	value ="">
		<input type="hidden" id="to_cal" 	name ="to_cal"	value ="">
	</form>
	
</div>

<!-- 관리단회의록 등록 팝업 -->
	<div id="schedule_reg_popup" class="mres_popup">
		<div class="pop" style="width: 545px">
			<div class="pop_top">
				<p class="popup_title">공지사항 등록</p>
				<a class="right schedule_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
			</div>
			<form id="fileForm" name="fileForm" action="/fileUpload" method="post" autocomplete="off" enctype="multipart/form-data">
			<input type="hidden" id="table_seq" name="table_seq" value=0>
			<input type="hidden" id="table_name" name="table_name" value="none">
	 		<input type="hidden" id="file_seq" name="file_seq" value=0>
	 
			<div class="pop_con">
				<table class="view_top_table">
					<tr>
						<th>첨부파일</th>
						<td>
						<input type="file" placeholder="" class="default_input" id="fileUpId" name="fileUpId">
						<input type="text" class="default_input" id="filename" name="filename" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input type="text" class="default_input con_wrap_100" name="file_content" required="required" placeholder="파일 내용"></td>
					</tr>
				</table> 
				<table id="fileTable" class="view_top_table">
					<tr>
						<th>업로드일자</th>
						<th>내용</th>
						<th>파일이름</th>		
					</tr>
				</table>
			</div>
			</form>
			<div class="pop_bottom">
				<a href="#;" class="basin_btn" id="imgUp">저장</a>
				<a href="#;" class="basin_btn schedule_reg_popup_close">취소</a>
			</div>
		</div>
	</div>
	<!-- 공지사항 등록 팝업 END-->




<div>
</div>