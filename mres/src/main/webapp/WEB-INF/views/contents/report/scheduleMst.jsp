<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/scheduleMstDetails.js"></script>

<script>

$(function(){

	var selectDivision = "${division}";			//업무코드		AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	$("#selectCode").val(selectDivision);		//페이지 진입 시 selectBox 선택
	
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

	<!-- 게시판 헤더 -->
	<section id="content" class="">
		<div class="container">
			<h3 class=""><a href="mainPage"><i class="" style="margin-right: 10px;"></i>메뉴 목록</a></h3>
		</div>
	</section>
	
	<!-- 페이지 제목 -->
	<section id="content" class="">
		<div class="container">
			<h3 class="">연간스케쥴</h3>
		</div>
	</section>
	
	<select id="selectCode" name="selectCode">
	    <option value="AS01">행정 업무</option>
	    <option value="AS02">회계 업무</option> 
	    <option value="AS03">조경 업무</option>
	    <option value="AS04">시설 업무</option>
<!-- 	    <option value="ALL">전체</option> -->
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
					<li class="col-xs-1 col-md-1">번호</li>
					<!-- <li class="col-xs-1 col-md-1">번호</li>	 -->
					<li class="col-xs-3 col-md-3">업무내용</li>
					<li class="col-xs-2 col-md-2">점검주기</li>
					<li class="col-xs-1 col-md-1">1월</li>
					<li class="col-xs-1 col-md-1">2월</li>
					<li class="col-xs-1 col-md-1">3월</li> 
					<li class="col-xs-2 col-md-2">파일이름</li>
				</ul> 
			</div>
			<div class="">
				<form id="formArray" name="formArray"  autocomplete="off">
					<c:set var="number" value="${scheduleCnt}" />
					<c:forEach var="scheduleList" items="${scheduleList}" varStatus="scheduleNum">
						<ul class="row" >
							<c:if test = "${scheduleList.schedule_seq == 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${scheduleList.schedule_seq}" disabled="true" checked></li>
								<li class="col-xs-3 col-md-3" id="workInfo"><input id="workInfo" type="text" value="${scheduleList.work_info}"></li>				
								<li class="col-xs-2 col-md-2"><label class="check_sycle${scheduleNum.count-1}">${scheduleList.check_cycle}</label></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_jan" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jan}" <c:if test="${scheduleList.schedule_jan ne '0'}">checked</c:if>></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_feb" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_feb}" <c:if test="${scheduleList.schedule_feb ne '0'}">checked</c:if>></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_mar" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_mar}" <c:if test="${scheduleList.schedule_mar ne '0'}">checked</c:if>></li>			
								<li class="col-xs-2 col-md-2"><label class="filePopup">${scheduleList.file_name}</label></li>	
							</c:if>
							<c:if test = "${scheduleList.schedule_seq != 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${scheduleList.schedule_seq}"></li>
								<li class="col-xs-3 col-md-3" id="workInfo"><lable >${scheduleList.work_info}</lable></li>
								<li class="col-xs-2 col-md-2"><label class="check_sycle${scheduleNum.count-1}">${scheduleList.check_cycle}</label></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_jan" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jan}" <c:if test="${scheduleList.schedule_jan ne '0'}">checked</c:if>></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_feb" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_feb}" <c:if test="${scheduleList.schedule_feb ne '0'}">checked</c:if>></li>
								<li class="col-xs-1 col-md-1 tableCheck"><input id="schedule_mar" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_mar}" <c:if test="${scheduleList.schedule_mar ne '0'}">checked</c:if>></li>			
								<li class="col-xs-2 col-md-2"><label class="filePopup">${scheduleList.file_name}</label></li>	
							</c:if>
						</ul>
					</c:forEach>
				</form>
			</div>
		</div>
	</section>	
	
	<!-- 게시글 없을 때. --> 
	<c:if test="${scheduleCnt==0}"> 
		<section id="content">
			<div class="container">
				<div class="">
					<ul class="row">	
						<li>게시판에 저장된 글이 없습니다.</li>
					</ul>
				</div>
			</div>
		</section>
	</c:if>
	
	<form id="selectForm" name="selectForm"  action="<%=request.getContextPath()%>/scheduleMst" autocomplete="off">
		<input id="division" name="division" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
</div>

<div>
</div>