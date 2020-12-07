<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
$(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/contractMstDetails.js"></script>

<script>

$(function(){

	var addList = "${addList}";				//add:행추가 / normal:일반 출력
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
			<h1 class="board-list-header">주요계약현황</h1>
		</div>
	</section> 

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
					<li class="col-xs-2 col-md-2">계약내용</li>
					<li class="col-xs-1 col-md-1">업체명</li>
					<li class="col-xs-3 col-md-3">계약기간</li>
					<li class="col-xs-2 col-md-2">계약연수</li>
					<li class="col-xs-1 col-md-1">계약금액</li>
					<li class="col-xs-2 col-md-2">비고</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<form id="formArray" name="formArray"  autocomplete="off">
					<c:set var="number" value="${contractCnt}" />
					<c:forEach var="contractList" items="${contractList}" varStatus="contractNum">
						<ul class="row" >
							<c:if test = "${contractList.contract_seq == 0}">
								<li class="col-xs-1 col-md-1 tableCount">				<input name="table_check" 			type="checkbox" value="${contractList.contract_seq}" checked></li>
								<li class="col-xs-2 col-md-2" id="contract_details">	<input id="contract_details_val" 	type="text" 	value="${contractList.contract_details}"></li>
								<li class="col-xs-1 col-md-1" id="contract_company">	<input id="contract_company_val" 	type="text" 	value="${contractList.contract_company}"></li>
								<li class="col-xs-3 col-md-3" id="contract_date">		<input id="total_date_val" 			type="text" 	value="${contractList.fr_day} ~ ${contractList.to_day}"></li>
								<li class="col-xs-2 col-md-2" id="contract_years">		<input id="total_years_val" 		type="text" 	value="${contractList.contract_years}(${contractList.contract_division})"></li>
								<li class="col-xs-1 col-md-1" id="contract_division">	<input id="payment_val" 			type="text" 	value="${contractList.payment}"></li>
								<li class="col-xs-2 col-md-2" id="payment">				<input id="remark_val" 				type="text" 	value="${contractList.remark}"></li>
							</c:if>
							<c:if test = "${contractList.contract_seq != 0}">
								<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${contractList.contract_seq}"></li>
								<li class="col-xs-2 col-md-2"><label >${contractList.contract_details}</label></li>
								<li class="col-xs-1 col-md-1"><label>${contractList.contract_company}</label></li>
								<li class="col-xs-3 col-md-3"><label/>${contractList.fr_day} ~ ${contractList.to_day}</label></li>
								
								<%-- <li class="col-xs-3 col-md-3"><label>${contractList.fr_day} ~ ${contractList.to_day}</label></li> --%>		 
								<li class="col-xs-2 col-md-2"><label>${contractList.contract_years}(${contractList.contract_division})</label></li>	
								<li class="col-xs-1 col-md-1"><label><fmt:formatNumber type="number" value="${contractList.payment}"/></label></li>		
								<li class="col-xs-2 col-md-2"><label>${contractList.remark}</label></li>			
							</c:if>
						</ul> 
					</c:forEach>
				</form>
			</div>
		</div> 
	</section>	
	
	<!-- 게시글 없을 때. --> 
	<c:if test="${contractCnt==0}"> 
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
	<form id="selectForm" name="selectForm"  action="contractList" autocomplete="off">
		<input id="workDate" name="workDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
</div>

<div>
</div>