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
 
  .leftBtn{
 	float: left;		/* 버튼 좌측 정렬 */
 }
 
 .rightBtn{
 	float: right;		/* 저장, 수정, 삭제, 행추가, 인쇄 버튼 우측 정렬 */
 }
 
 .divCal{
 	width:150px;		/* 계약기간(시작일/종료일) 박스 크기 조정 */
 }

 .width100{
 	width:100px;		/* 예정일 박스 크기 조정 */
 }
 
 .leftText{
     text-align: left;	/* 글자 좌측 정렬 */	
 }
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/LiftContentMstDetails.js"></script>

<script type="text/javascript">

$(function(){

	$(".sub_title").text("시설물점검현황");	//서브타이틀
	var addList = "${addList}";		//add:행추가 / normal:일반 출력
	
	//-----
	//행추가
	//-----
// 	if("add" == addList){
		
// 		$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
// 		$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
// 		$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
// 		$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
		
// 	};//if
   	
});

</script>

<body>
		
	<!-- 버튼 모음입니다. --> 
	<button id="liftPage"	class="basin_btn leftBtn">&lt-  목록 보기</button>	
	<button id="tablePrint"	class="basin_btn rightBtn" onclick="window.print()">인쇄</button>
	<button id="tableAdd"	class="basin_btn rightBtn">행추가</button>	 
	<button id="tableSave"	class="basin_btn rightBtn">저장</button>	
	
<!-- 게시글 있을 때. --> 
	<table class="view_top_center_table">
		<tr>
			<td>점검분야</td>
			<td><input class="default_input w250" id="inspection_field" type="text" value="${LiftVO.inspection_field}" maxlength="25"></td>
			<td rowspan="2">확인</td>
			<td><input class="default_input w250" id="main_manager" 	type="text" value="${LiftVO.main_manager}" maxlength="12"></td>
		</tr>
		<tr>
			<td>건물명</td>
			<td><input class="default_input w250" id="building_name" 	type="text" value="${LiftVO.building_name}" maxlength="25"></td>
			<td><input class="default_input w250" id="sub_manager" 		type="text" value="${LiftVO.sub_manager}" maxlength="12"></td>
		</tr>
		<tr>
			<td>점검업체</td>
			<td><input class="default_input w250" id="inspection_company"	type="text" value="${LiftVO.inspection_company}" maxlength="50"></td>
			<td>점검일자</td>
			<td><input class="default_input w250" id="inspection_date" 		type="text" value="${LiftVO.inspection_date}"placeholder='ex. 2021-01-01'></td>
		</tr>
	</table>
	
	<table class="view_top_center_table">
		<tr>
			<td rowspan="2">체크</td>
			<td rowspan="2">구분</td>
			<td rowspan="2">주요 점검 사항</td>
			<td colspan="3">점검 결과</td>
			<td colspan="3">조치결과</td>
		</tr>
		<tr>
			<td>양호</td>
			<td>보통</td>
			<td>미흡</td>
			<td>현지시정</td>
			<td>조치계획</td>
			<td>예정일</td>
		</tr> 
		<c:set var="number" value="${liftContentCnt}" />
		<c:forEach var="liftContentList" items="${liftContentList}" varStatus="LiftNum">		
			<tr>			
				<c:if test = "${liftContentList.lift_content_seq != 0}">
					<td class=" tableCount"><input type="checkbox" id="table_check" name="table_check" value="${liftContentList.lift_content_seq}" checked></td>
					<td class="leftText">${liftContentList.division}</td>  
					<td class="leftText">${liftContentList.item_check}</td>
					<c:if test = "${liftContentList.result_check == 1}">
						<td><input type="radio" name="result_check${LiftNum.index}" value="1" checked></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2"></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3"></td>
					</c:if>
					<c:if test = "${liftContentList.result_check == 2}"> 
						<td><input type="radio" name="result_check${LiftNum.index}" value="1"></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2" checked></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3"></td>
					</c:if>
					<c:if test = "${liftContentList.result_check == 3}">
						<td><input type="radio" name="result_check${LiftNum.index}" value="1"></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2"></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3" checked></td>
					</c:if>
					<td><input class="default_input w120" id="fr_work_val" 		type="text" value="${liftContentList.fr_work}" maxlength="150"></td> 
					<td><input class="default_input w120" id="to_work_val" 		type="text" value="${liftContentList.to_work}" maxlength="150"></td>
					<td class="width100">
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap">
									<input type="date" class="date_input select_cal" id="toCal" name="toCal" value="${liftContentList.schedule_date}">
								</div>
							</div>
						</div>
					</td>
				</c:if>
				<c:if test = "${liftContentList.lift_content_seq == 0}">
					<td class=" tableCount"><input type="checkbox" id="table_check" name="table_check" value="${liftContentList.lift_content_seq}" checked></td>
					<td><input class="default_input w120" id="division_val" 		type="text" value="${liftContentList.division}"></td>  
					<td><input class="default_input w250" id="item_check_val" 		type="text" value="${liftContentList.item_check}"></td>
					<c:if test = "${liftContentList.result_check == 0}">
						<td><input type="radio" name="result_check${LiftNum.index}" value="1"></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2"></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3"></td>
					</c:if>
					<c:if test = "${liftContentList.result_check == 1}">
						<td><input type="radio" name="result_check${LiftNum.index}" value="1" checked></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2"></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3"></td>
					</c:if>
					<c:if test = "${liftContentList.result_check == 2}"> 
						<td><input type="radio" name="result_check${LiftNum.index}" value="1"></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2" checked></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3"></td>
					</c:if>
					<c:if test = "${liftContentList.result_check == 3}">
						<td><input type="radio" name="result_check${LiftNum.index}" value="1"></td>
						<td><input type="radio" name="result_check${LiftNum.index}" value="2"></td>
						<td><input type="radio"	name="result_check${LiftNum.index}" value="3" checked></td>
					</c:if>
					
					<td><input class="default_input w120" id="fr_work_val" 		type="text" value="${liftContentList.fr_work}" maxlength="150"></td> 
					<td><input class="default_input w120" id="to_work_val" 		type="text" value="${liftContentList.to_work}" maxlength="150"></td>
					<td class="width100">
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap">
									<input type="date" class="date_input select_cal" id="toCal" name="toCal" value="${liftContentList.schedule_date}">
								</div>
							</div>
						</div>
					</td>
				</c:if>	
			</tr>
		</c:forEach>
	</table> 
	
	<!-- 셀렉트박스 조회용 히든 폼 -->
	<form id="selectForm" name="selectForm"  action="liftContnet" autocomplete="off">
		<input id="addList" name ="addList" type="hidden" value	="">
		<input id="lift_seq" name ="lift_seq" type="hidden" value	="${lift_seq}">
		<input id="inspection_division" name ="inspection_division" type="hidden" value	="${inspection_division}">
	</form>

<div>
</div>