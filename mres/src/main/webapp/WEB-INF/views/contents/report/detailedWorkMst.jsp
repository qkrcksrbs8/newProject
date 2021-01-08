<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
 .rightBtn{
 	float: right;		/* 저장, 수정, 삭제, 행추가, 인쇄 버튼 우측 정렬 */
 }
</style>
<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/detailedWorkMstDetails.js"></script>

<script>

$(function(){

	$(".sub_title").text("세부업무실적");		//서브타이틀
	var selectDate = "${selectDate}";		//업무코드		2020, 2019, 2018 ...
	$("#selectCalDate").val(selectDate);	//페이지 진입 시 selectBox 선택
	
	var addList = "${addList}";				//add:행추가 / normal:일반 출력
	
	//-----
	//행추가
	//-----
	if("add" == addList){
		
		$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
		$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
		$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
		$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
		$("#addList").val("normal");			//
		
	};//if
   	
});

</script>

<body>

<div class="container">

	<div class="search_wrap">
	
		<!-- 달력 -->
		<div class="search_con">
			<div class="search_title">기준년도</div>
			<select class="default_select w70" id="selectCalDate" name="selectCalDate">
				<c:forEach var="calDate" items="${calDate}" varStatus="calDateNum">
					<option value="${calDate}">${calDate}</option>
				</c:forEach>
			</select>
		</div>
		
		<!-- 버튼 모음입니다. -->
		<button id="tablePrint"	class="basin_btn rightBtn" onclick="window.print()">인쇄</button>
		<button id="tableAdd"	class="basin_btn rightBtn">행추가</button>	
		<button id="tableDel"	class="basin_btn rightBtn">삭제</button>	
		<button id="tableUp"	class="basin_btn rightBtn">수정</button>	
		<button id="tableSave"	class="basin_btn rightBtn">저장</button>	
		
	</div>
	<!-- 셀렉트박스 조회용 히든 폼 -->
	<form id="selectForm" name="selectForm"  action="detailedWorkList" autocomplete="off">
		<input id="workDate" name="workDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
		<input id="selectDate" name ="selectDate" type="hidden" value ="">
	</form>
	
<!-- 게시글 있을 때. --> 
	<table class="view_top_center_table">
		<tr> 
			<td >체크</td>
			<td >부문</td>
			<td >예정업무</td>
			<td >실시업무</td>
			<td >REMARK</td>
		</tr>
			<c:set var="number" value="${detailWorkCnt}" />
			<c:forEach var="detailWorkList" items="${detailWorkList}" varStatus="detailedWorkNum">
				<tr>
					<c:if test = "${detailWorkList.work_seq == 0}">
						<td class=" tableCount"><input type="checkbox" id="table_check" name="table_check" value="${detailWorkList.work_seq}" checked></td>
						<td class="" id="sector"><input type="text" class="default_input maxVal" value="${detailWorkList.sector}" maxlength="10"></td>
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" >${detailWorkList.fr_work}</textarea></td>
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" >${detailWorkList.to_work}</textarea></td>		
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" >${detailWorkList.remark}</textarea></td>	
					</c:if>
					<c:if test = "${detailWorkList.work_seq != 0}">
						<td class="tableCount"><input type="checkbox" name="table_check" value="${detailWorkList.work_seq}"></td>
						<td class="" id="sector"><input type="text" class="default_input maxVal" value="${detailWorkList.sector}" maxlength="10" readonly="readonly"></td>
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" readonly="readonly">${detailWorkList.fr_work}</textarea></td>
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" readonly="readonly">${detailWorkList.to_work}</textarea></td>		
						<td class=""><textarea class="default_textarea maxVal maxLen" style="resize:none;" readonly="readonly">${detailWorkList.remark}</textarea></td>		
					</c:if>
				</tr>
			</c:forEach>
	</table>

</div>

<div>
</div>