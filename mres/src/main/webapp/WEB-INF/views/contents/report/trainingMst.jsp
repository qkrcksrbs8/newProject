<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/detailedWorkMstDetails.js"></script>

<script>

$(function(){

	var selectTrainingDate = "${trainingDate}";			//업무코드		2020, 2019, 2018 ...
	$("#selectCode").val(selectWorkDate);		//페이지 진입 시 selectBox 선택
	
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

	<!-- 페이지 제목 -->
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h1 class="board-list-header">세부업무실적</h1>
		</div>
	</section> 
	
	<!-- 기준년도 셀렉트박스 -->
	<select id="selectCode" name="selectCode">
	    <option value="2020">2020</option>
	    <option value="2019">2019</option> 
	    <option value="2018">2018</option>
	    <option value="2017">2017</option>
	</select>
	<button id="tableSave" class="btn btn-success" >저장</button>	
	<button id="tableUp" class="btn btn-success" >수정</button>
	<button id="tableDel" class="btn btn-success" >삭제</button>		
	<button id="tableAdd" class="btn btn-success" >행추가</button>	
	
	<!-- 게시글 있을 때. --> 
	<section id="content">
		<div class="container">
			<div class="board-list-wrap board-list-header">
				<ul class="row">
					<li class="col-xs-1 col-md-1">체크</li>
					<li class="col-xs-2 col-md-2">구분</li>
					<li class="col-xs-2 col-md-2">일자</li>
					<li class="col-xs-2 col-md-2">교육진행</li>
					<li class="col-xs-2 col-md-2">참석인원</li>
					<li class="col-xs-1 col-md-1">교육내용</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<form id="formArray" name="formArray"  autocomplete="off">
					<c:set var="number" value="${trainingCnt}" />
					<c:forEach var="trainingList" items="${trainingList}" varStatus="trainingNum">
						<ul class="row" >
							<c:if test = "${trainingList.training_seq == 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" id="table_check" name="table_check" value="${trainingList.training_seq}" checked></li>
								<li class="col-xs-2 col-md-2" id="division"><input id="division_val" type="text" value="${trainingList.division}"></li>
								<li class="col-xs-2 col-md-2" id="training_date"><input id="training_date_val" type="text" value="${trainingList.training_date}"></li>
								<li class="col-xs-2 col-md-2" id="training_progress"><input id="training_progress_val" type="text" value="${trainingList.training_progress}"></li>
								<li class="col-xs-2 col-md-2" id="attend_count"><input id="attend_count_val" type="text" value="${trainingList.attend_count}"></li>
								<li class="col-xs-1 col-md-1" id="training_content"><input id="training_content_val" type="text" value="${trainingList.training_content}"></li>
							</c:if>
							<c:if test = "${trainingList.training_seq != 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${trainingList.training_seq}"></li>
								<li class="col-xs-2 col-md-2"><lable >${trainingList.division}</lable></li>
								<li class="col-xs-2 col-md-2"><lable >${trainingList.training_date}</lable></li>
								<li class="col-xs-2 col-md-2"><label>${trainingList.training_progress}</label></li>
								<li class="col-xs-2 col-md-2"><label>${trainingList.attend_count}</label></li>		
								<li class="col-xs-1 col-md-1"><label>${trainingList.training_content}</label></li>		
							</c:if>
						</ul>
					</c:forEach>
				</form>
			</div>
		</div> 
	</section>	
	
	<!-- 게시글 없을 때. -->
	<c:if test="${trainingCnt==0}">
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
	<form id="selectForm" name="selectForm"  action="trainingList" autocomplete="off">
		<input id="trainingDate" name="trainingDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
</div>

<div>
</div>