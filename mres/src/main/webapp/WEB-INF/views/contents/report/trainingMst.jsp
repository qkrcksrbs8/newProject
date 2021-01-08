<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
 .rightBtn{
 	float: right;		/* 저장, 수정, 삭제, 행추가, 인쇄 버튼 우측 정렬 */
 }
  .date_input{			/* input type="date" 수정	*/
 	width:150px;		/* 넓이는 150px 			*/
 	padding-right:5px; 	/* 우측 5px만큼 간격			*/	
	
 }
</style>
<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/trainingMstDetails.js"></script>
 
<script type="text/javascript">

$(function(){

	$(".sub_title").text("교육현황");	//서브타이틀
	var selectTrainingDate = "${trainingDate}";			//업무코드		2020, 2019, 2018 ...
	$("#selectCalDate").val(selectTrainingDate);		//페이지 진입 시 selectBox 선택
	
	var addList = "${addList}";					//add:행추가 / normal:일반 출력
	
	//-----
	//행추가
	//-----
	if("add" == addList){
		
		$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
		$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
		$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
		$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
		
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
	<form id="selectForm" name="selectForm"  action="trainingList" autocomplete="off">
		<input id="trainingDate" name="trainingDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>

	<!-- 게시글 있을 때. --> 
	<table class="view_top_center_table">
		<tr> 
			<td>체크</td>
			<td>구분</td>
			<td>일자</td>
			<td>교육진행</td>
			<td>참석인원</td>
			<td>교육내용</td>
		</tr> 
	<c:set var="number" value="${trainingCnt}" />
	<c:forEach var="trainingList" items="${trainingList}" varStatus="trainingNum">
			<c:if test = "${trainingList.training_seq == 0}">
				<tr>
					<td><input type="checkbox" id="table_check" name="table_check" value="${trainingList.training_seq}" checked></td>
					<td><input class="default_input con_wrap_100" id="division" 			type="text"	value="${trainingList.division}" maxlength="12"></td>
					<td>
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap"> 
									<input type="date" class="date_input select_cal" id="training_date" name="training_date" value="${trainingList.training_date}">
								</div>
							</div>
						</div>
					</td>
					<td><input class="default_input con_wrap_100" id="training_progress" 	type="text"	value="${trainingList.training_progress}" maxlength="15"></td>
					<td><input class="default_input con_wrap_100" id="attend_count" 		type="text"	value="${trainingList.attend_count}" maxlength="21"></td>
					<td><input class="default_input w250" id="training_content" 	type="text"	value="${trainingList.training_content}" maxlength="50"></td>
				</tr>
			</c:if>
			<c:if test = "${trainingList.training_seq != 0}">
				<tr>
					<td><input type="checkbox" id="table_check" name="table_check" value="${trainingList.training_seq}" checked></td>
					<td><input class="default_input con_wrap_100" id="division" 			type="text"	value="${trainingList.division}"		readonly="readonly" maxlength="12"></td>
					<td>
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap">
									<input type="date" class="date_input select_cal" id="training_date" name="training_date" value="${trainingList.training_date}" readonly="readonly">
								</div>
							</div>
						</div>
					</td>
					<td><input class="default_input con_wrap_100" id="training_progress" 	type="text"	value="${trainingList.training_progress}" readonly="readonly" maxlength="15"></td>
					<td><input class="default_input con_wrap_100" id="attend_count" 		type="text"	value="${trainingList.attend_count}"	readonly="readonly" maxlength="21"></td>
					<td><input class="default_input w250" id="training_content" 	type="text"	value="${trainingList.training_content}"readonly="readonly" maxlength="50"></td>
				</tr>	
			</c:if>
	</c:forEach>
	</table>
	
</div>

<div>
</div>