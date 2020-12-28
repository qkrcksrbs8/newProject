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
 
 .divCal{
 	width:150px;		/* 계약기간(시작일/종료일) 박스 크기 조정 */
 }
 
  .pdfMax{
 	width:100%;			/* pdf 맥스 뷰 가로 */
 	height:800px;		/* pdf 맥스 뷰 세로 */ 
 }

</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mres10/meetingLogMstDetails.js"></script>
<script type="text/javascript">

$(function(){

	$(".sub_title").text("관리단회의록");	//서브타이틀
	var selectDate = "${selectDate}";	//업무코드	
	$("#selectCode").val(selectDate);	//페이지 진입 시 selectBox 선택
	var addList = "${addList}";			//add:행추가 / normal:일반 출력
	
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
	
	<!-- 기준년도 셀렉트박스 -->
	<select class="default_select w70" id="selectCode" name="selectCode">
	    <option value="2020">2020</option>
	    <option value="2019">2019</option> 
	    <option value="2018">2018</option>
	    <option value="2017">2017</option>
	</select>
	
	<!-- 버튼 모음입니다. -->
	<button id="tablePrint"	class="basin_btn rightBtn" onclick="window.print()">인쇄</button>
	<button id="tableAdd"	class="basin_btn rightBtn">행추가</button>	
	<button id="tableDel"	class="basin_btn rightBtn">삭제</button>	
	<button id="tableUp"	class="basin_btn rightBtn">수정</button>	
	<button id="tableSave"	class="basin_btn rightBtn">저장</button>	
	
	<form id="selectForm" name="selectForm"  action="<%=request.getContextPath()%>/meetingLogList" autocomplete="off">
		<input id="selectDate" name="selectDate" type="hidden" value ="">
		<input id="addList" name ="addList" type="hidden" value	="">
	</form>
	
	<table class="view_top_center_table marginLeft">
		<tr> 
			<td>체크</td> 
			<td>일자</td>
			<td>장소</td>
			<td>참석자</td> 
			<td>교육내용</td>
		</tr>
		<c:set var="number" value="${meetingLogCnt}" />
		<c:forEach var="meetingLogList" items="${meetingLogList}" varStatus="meetingLogNum">
			<tr>
				<c:if test = "${meetingLogList.meeting_log_seq == 0}">
					<td><input type="checkbox" id="table_check" name="table_check" value="${meetingLogList.meeting_log_seq}" checked></td>
					<td>
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap">
									<input type="date" class="date_input select_cal" id="meeting_log_date" name="meeting_log_date" value="${meetingLogList.meeting_log_date }">
								</div>
								
							</div>
						</div>
					</td>
					<td class=""><input class="default_input w250" type="text" value="${meetingLogList.meeting_log_progress}" maxlength="15"></td>
					<td class=""><input class="default_input w250" type="text" value="${meetingLogList.attend_count}" maxlength="25"></td>
					<td class=""><label>${meetingLogList.meeting_agenda}"</label></td>
				</c:if>	
				<c:if test = "${meetingLogList.meeting_log_seq != 0}">
					<td><input type="checkbox" id="table_check" name="table_check" value="${meetingLogList.meeting_log_seq}"></td>
					<td>
						<div class="search_con clear divCal">
							<div>
								<div class="cal_wrap">
									<input type="date" class="date_input select_cal" id="meeting_log_date" name="meeting_log_date" value="${meetingLogList.meeting_log_date }" readonly="readonly">
								</div>
							</div>
						</div>
					</td>
					<td class=""><input class="default_input w250" type="text" value="${meetingLogList.meeting_log_progress}"	readonly="readonly" maxlength="15"></td>
					<td class=""><input class="default_input w250" type="text" value="${meetingLogList.attend_count}" 			readonly="readonly" maxlength="25"></td>
						
					<c:if test = "${meetingLogList.file_name != ''}">
						<td class="${meetingLogList.file_path}"><label class="pdfPopup">${meetingLogList.file_name}</label></td>
					</c:if>
					<c:if test = "${meetingLogList.file_name == ''}">
						<td class=""><label class="uploadPopup">회의록 업로드</label></td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 게시글 없을 때. --> 
	<c:if test="${meetingLogCnt==0}"> 
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
	    
	<!-- 관리단회의록 등록 팝업 -->
	<div id="schedule_reg_popup" class="mres_popup">
		<div class="pop" style="width: 545px">
			<div class="pop_top">
				<p class="popup_title">공지사항 등록</p>
				<a class="right schedule_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
			</div>
			<form id="fileForm" name="fileForm" action="/fileUpload" method="post" autocomplete="off" enctype="multipart/form-data">
			<input type="hidden" id="table_seq" name="table_seq" value=0>
			<input type="hidden" id="table_name" name="table_name" value="">
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
    
	<!-- 관리단회의록 pdf 팝업 -->
	<div id="pdf_reg_popup" class="mres_popup"style="width: 100%">
		<div class="pop" style="width: 100%"> 
			<div class="pop_top">
				<p class="popup_title">공지사항 등록</p>
				<a class="right pdf_reg_popup_close" href="#;"><img src="<%=request.getContextPath()%>/resources/img/close.png" alt="닫기" width="35px" /></a>
			</div>
			<iframe src="" style="width:100%;height:800px;" id="pdfIframe" ></iframe>
			<!-- /resources\daonn\meetingLogMst\12467\1\testPdf.pdf -->
		</div>
	</div>
	<!-- 공지사항 등록 팝업 END-->
	
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">  
	