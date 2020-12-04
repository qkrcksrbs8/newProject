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
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h3 class="board-list-header"><a href="mainPage.do"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>메뉴 목록</a></h3>
		</div>
	</section>
	
	<!-- 페이지 제목 -->
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h3 class="board-list-header">세부업무실적</h3>
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
					<li class="col-xs-2 col-md-2">부문</li>
					<li class="col-xs-3 col-md-3">예정업무</li>
					<li class="col-xs-3 col-md-3">실시업무</li>
					<li class="col-xs-1 col-md-1">REMARK</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<form id="formArray" name="formArray"  autocomplete="off">
					<c:set var="number" value="${detailWorkCnt}" />
					<c:forEach var="detailWorkList" items="${detailWorkList}" varStatus="detailedWorkNum">
						<ul class="row" >
							<c:if test = "${detailWorkList.work_seq == 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" id="table_check" name="table_check" value="${detailWorkList.work_seq}" checked></li>
								<li class="col-xs-2 col-md-2" id="sector"><input id="sector_val" type="text" value="${detailWorkList.sector}"></li>
								<li class="col-xs-3 col-md-3" id="fr_work"><input id="fr_work_val" type="text" value="${detailWorkList.fr_work}"></li>
								<li class="col-xs-3 col-md-3" id="to_work"><input id="to_work_val" type="text" value="${detailWorkList.to_work}"></li>
								<li class="col-xs-1 col-md-1" id="remark"><input id="remark_val" type="text" value="${detailWorkList.remark}"></li>
							</c:if>
							<c:if test = "${detailWorkList.work_seq != 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${detailWorkList.work_seq}"></li>
								<li class="col-xs-2 col-md-2" id="sector"><lable >${detailWorkList.sector}</lable></li>
								<li class="col-xs-3 col-md-3"><label>${detailWorkList.fr_work}</label></li>
								<li class="col-xs-3 col-md-3"><label>${detailWorkList.to_work}</label></li>		
								<li class="col-xs-1 col-md-1"><label>${detailWorkList.remark}</label></li>		
							</c:if>
						</ul>
					</c:forEach>
				</form>
			</div>
		</div> 
	</section>	
	
	<!-- 게시글 없을 때. -->
	<c:if test="${detailWorkCnt==0}">
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
	<form id="selectForm" name="selectForm"  action="detailedWorkList.do" autocomplete="off">
		<input id="workDate" name="workDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
</div>

<div>
</div>