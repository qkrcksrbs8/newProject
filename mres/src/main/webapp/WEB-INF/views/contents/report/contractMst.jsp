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
<style>
 .date_input{			/* input type="date" 수정	*/
 	width:150px;		/* 넓이는 150px 			*/
 	padding-right:5px; 	/* 우측 5px만큼 간격			*/	
	
 }
 
 .rightBtn{
 	float: right;		/* 저장, 수정, 삭제, 행추가, 인쇄 버튼 우측 정렬 */
 }
 
 .divCal{
 	width:150px;		/* 계약기간(시작일/종료일) 박스 크기 조정 */
 }

</style>
<script>

$(function(){

	$(".sub_title").text("주요계약현황");	//서브타이틀
	var addList = "${addList}";			//add:행추가 / normal:일반 출력
	
	//행추가
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
		
	<!-- 버튼 모음입니다. --> 
	<button id="tablePrint"	class="basin_btn rightBtn" onclick="window.print()">인쇄</button>
	<button id="tableAdd"	class="basin_btn rightBtn">행추가</button>	
	<button id="tableDel"	class="basin_btn rightBtn">삭제</button>	
	<button id="tableUp"	class="basin_btn rightBtn">수정</button>	
	<button id="tableSave"	class="basin_btn rightBtn">저장</button>	
	
<table class="view_top_center_table">
		<tr>
			<td>체크</td>
			<td>계약내용</td>
			<td>업체명</td>
			<td>계약기간(시작일)</td>
			<td>계약기간(종료일)</td>
			<td>계약연수</td>
			<td>계약금액</td>
			<td>비고</td>
		</tr>
		<c:set var="number" value="${contractCnt}" />
		<c:forEach var="contractList" items="${contractList}" varStatus="contractNum">
		<tr>
			<c:if test = "${contractList.contract_seq == 0}">
				<td><input name="table_check" 			type="checkbox" value="${contractList.contract_seq}" checked></td>
				<td><input class="default_input w120" id="contract_details_val" type="text" value="${contractList.contract_details}" maxlength="25"></td>
				<td><input class="default_input w120" id="contract_company_val" type="text" value="${contractList.contract_company}" maxlength="25"></td>
				<td>
					<div class="search_con clear divCal">
						<div>
							<div class="cal_wrap">
								<input type="date" class="date_input select_cal" id="frCal" name="frCal">
							</div>
							
						</div>
					</div>
				</td>
				<td>
					<div class="search_con clear divCal">
						<div>
							<div class="cal_wrap">
								<input type="date" class="date_input select_cal" id="toCal" name="toCal">
							</div>
						</div>
					</div>
				</td>
				<td><input class="default_input w120" id="total_years_val" 	type="text" value="${contractList.contract_years}" readonly="readonly"></td>
				<td><input class="default_input w120" id="payment_val" 		type="text" value="${contractList.payment}" maxlength="21"></td>
				<td><input class="default_input w120" id="remark_val" 		type="text" value="${contractList.remark}" maxlength="150"></td> 
			</c:if>
			<c:if test = "${contractList.contract_seq != 0}">
				<td><input type="checkbox" name="table_check" value="${contractList.contract_seq}"></td>
				<td><input class="default_input w120" id="contract_details_val" type="text" value="${contractList.contract_details}" readonly="readonly" maxlength="25"></td>
				<td><input class="default_input w120" id="contract_company_val" type="text" value="${contractList.contract_company}" readonly="readonly" maxlength="25"></td>
				<td>
					<div class="search_con clear divCal">
						<div>
							<div class="cal_wrap">
								<input type="date" class="date_input select_cal" id="frCal" name="frCal" value="${contractList.fr_day}" readonly="readonly">
							</div>
							
						</div>
					</div>
				</td>
				<td>
					<div class="search_con clear divCal">
						<div>
							<div class="cal_wrap">
								<input type="date" class="date_input select_cal" id="toCal" name="toCal" value="${contractList.to_day}" readonly="readonly">
							</div>
						</div>
					</div>
				</td>
				<td><input class="default_input w120" id="total_years_val" 	type="text" value="${contractList.contract_years}"	readonly="readonly"></td>
				<td><input class="default_input w120" id="payment_val" 		type="text" value="${contractList.payment}" 		readonly="readonly" maxlength="21"></td>
				<td><input class="default_input w120" id="remark_val" 		type="text" value="${contractList.remark}" 			readonly="readonly" maxlength="150"></td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	
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