<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style> 
/* 마스크 뛰우기 */
#mask {  
    position:absolute;  
    z-index:9000;  
    background-color:#000;  
    display:none;  
    left:0;
    top:0;
} 
/* 팝업으로 뜨는 윈도우 css  */ 
.window{
    display: none;
    position:absolute;  
    left:50%;
    top:100px;
    margin-left: -300px;
    width:300px;
    height:200px;
    background-color:#FFF;
    z-index:10000;   
 }
 
</style>
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
	
	<form id="selectForm" name="selectForm"  action="<%=request.getContextPath()%>/scheduleList" autocomplete="off">
		<input id="division" name="division" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
	<table class="table">
		<tr> 
			<td rowspan="2">체크</td>
			<td rowspan="2">업무내용</td>
			<td rowspan="2">점검주기</td>
			<td colspan="12">년간 월별 업무 일정</td>
			<td rowspan="2">관리주체</td>
			<td colspan="2">파일첨부</td>
		</tr>
		<tr>
			<td>1월</td>
			<td>2월</td>
			<td>3월</td>
			<td>4월</td>
			<td>5월</td>
			<td>6월</td>
			<td>7월</td>
			<td>8월</td>
			<td>9월</td>
			<td>10월</td>
			<td>11월</td>
			<td>12월</td>
			<td>계약서</td>
			<td>그외</td>
		</tr>
		<c:set var="number" value="${scheduleCnt}" />
		<c:forEach var="scheduleList" items="${scheduleList}" varStatus="scheduleNum">
			<tr>
				<c:if test = "${scheduleList.schedule_seq == 0}">
					<td class="tableCount">		<input name="table_check"	type="checkbox"	value="${scheduleList.schedule_seq}" disabled="true" checked></td>
					<td class="" id="workInfo">	<input id="workInfo" 		type="text"		value="${scheduleList.work_info}"></td>
					<td class=""><label class="check_sycle${scheduleNum.count-1}">${scheduleList.check_cycle}</label></td>
					<td class="tableCheck"><input id="schedule_jan" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jan}" <c:if test="${scheduleList.schedule_jan ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_feb" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_feb}" <c:if test="${scheduleList.schedule_feb ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_mar" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_mar}" <c:if test="${scheduleList.schedule_mar ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_apr" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_apr}" <c:if test="${scheduleList.schedule_apr ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_may" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_may}" <c:if test="${scheduleList.schedule_may ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_jun" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jun}" <c:if test="${scheduleList.schedule_jun ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_jul" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jul}" <c:if test="${scheduleList.schedule_jul ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_aug" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_aug}" <c:if test="${scheduleList.schedule_aug ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_sep" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_sep}" <c:if test="${scheduleList.schedule_sep ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_oct" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_oct}" <c:if test="${scheduleList.schedule_oct ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_nov" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_nov}" <c:if test="${scheduleList.schedule_nov ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_dec" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_dec}" <c:if test="${scheduleList.schedule_dec ne '0'}">checked</c:if>></td>
					<td class=""><label class=""><input id="entity" type="text" value="${scheduleList.entity}"></label></td>
					<td class=""><label class="">${scheduleList.contract}</label></td>
					<td class=""><label class="filePopup">${scheduleList.file_name}</label></td>
				</c:if>	
				<c:if test = "${scheduleList.schedule_seq != 0}">
					<td class="tableCount"><input type="checkbox" name="table_check" value="${scheduleList.schedule_seq}"></td>
					<td class=""id="workInfo"><lable >${scheduleList.work_info}</lable></td>
					<td class=""><label class="check_sycle${scheduleNum.count-1}">${scheduleList.check_cycle}</label></td>
					<td class="tableCheck"><input id="schedule_jan" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jan}" <c:if test="${scheduleList.schedule_jan ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_feb" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_feb}" <c:if test="${scheduleList.schedule_feb ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_mar" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_mar}" <c:if test="${scheduleList.schedule_mar ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_apr" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_apr}" <c:if test="${scheduleList.schedule_apr ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_may" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_may}" <c:if test="${scheduleList.schedule_may ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_jun" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jun}" <c:if test="${scheduleList.schedule_jun ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_jul" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jul}" <c:if test="${scheduleList.schedule_jul ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_aug" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_aug}" <c:if test="${scheduleList.schedule_aug ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_sep" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_sep}" <c:if test="${scheduleList.schedule_sep ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_oct" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_oct}" <c:if test="${scheduleList.schedule_oct ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_nov" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_nov}" <c:if test="${scheduleList.schedule_nov ne '0'}">checked</c:if>></td>
					<td class="tableCheck"><input id="schedule_dec" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_dec}" <c:if test="${scheduleList.schedule_dec ne '0'}">checked</c:if>></td>
					<td class=""><label class="">${scheduleList.entity}</label></td>
					<td class=""><label class="">${scheduleList.contract}</label></td>
					<td class=""><label class="openMask">${scheduleList.file_name}</label></td>			
				</c:if>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 팝업 --> 
	<div id ="wrap"> 
        <div id = "container">  
            <div id="mask"></div>
            <div class="window">
            
                	<form action="file" method="post" enctype="multipart/form-data">
						<fieldset>
							<table>
								<tr>
									<th>파일</th>
									<td><input type="file" name="file" required="required"></td>
								</tr>
								<tr>
									<th>내용</th>
									<td><input type="text" name="file_content" required="required" placeholder="파일 내용"></td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="저장">
										<input type="reset" value="취소">
									</td>
								</tr>
							</table>
							<input type="hidden" id="table_seq" name="table_seq" val="">
							<input type="hidden" id="table_name" name="table_name" val="">
						</fieldset>
					</form>
                	
                <p style="text-align:center; background:#ffffff; padding:20px;"><a href="#" class="close">닫기X</a></p>
            </div>
            
        </div>
    </div>
    
    
    
    
	
<div>
</div>