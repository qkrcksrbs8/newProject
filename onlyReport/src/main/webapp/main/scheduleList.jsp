<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/dark.css" type="text/css" />
<link rel="stylesheet" href="css/font-icons.css" type="text/css" />
<link rel="stylesheet" href="css/animate.css" type="text/css" />
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css" />
<link rel="stylesheet" href="css/responsive.css" type="text/css" />
<link rel="stylesheet" href="css/tdl.css" type="text/css" />
<link rel="stylesheet" href="css/imports/shortcodes/misc.css" type="text/css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

$(function(){
	
	//------------------
	//셀렉트박스 조회
	//------------------
	$('#selectCode').change(function() {
		
	    var division = $(this).val();	//셀렉트박스 값
	    $("#division").val(division);	//업무구분
	    
	    var url = "scheduleList.do";	//조회

// 		$("#selectForm").submit();//서브밋
		
		$.ajax({
			 method: "post"
			,url : url
			,data: division
			,contentType: 'application/json; charset=utf-8'
			,dataType:"json"
			,async: true
		}).done(function(data){//통신 성공
			alert("저장성공!");
		}).fail(function(data){//통신 실패
			alert("저장실패");
		})
	});
	
	//------------------------------
	//테이블을 저장하는 로직입니다.
	//------------------------------
	$("#tableBtn").click(function(){
		
		//-------------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-------------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");	//모든 체크박스	
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합
		var division = $("#selectCode").val();			//업무구분
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var ul = checkbox.parent().parent().eq(i);
			var li = ul.children();
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var schedule_seq = li.eq(0).children().val();					//연간스케쥴 시퀀스
			var work_info = li.eq(1).text();								//업무내용
			var check_cycle = li.eq(2).text();								//점검주기
			var schedule_jan = li.eq(3).children().is(":checked")? 1 : 0;	//1월
			var schedule_feb = li.eq(4).children().is(":checked")? 1 : 0;	//2월
			var schedule_mar = li.eq(5).children().is(":checked")? 1 : 0; 	//3월
			var file_name = li.eq(7).text();								//파일이름

			if(schedule_seq == 'on'){schedule_seq = 0;};//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();				//JsonObject를 위한 객체생성
			jsonObj.schedule_seq = schedule_seq;	//고유번호
			jsonObj.division	= division			//업무구분
			jsonObj.work_info	= work_info;		//업무내용 
			jsonObj.check_cycle = check_cycle;		//점검주기
			jsonObj.schedule_jan= schedule_jan;		//1월
			jsonObj.schedule_feb= schedule_feb;		//2월
			jsonObj.schedule_mar= schedule_mar;		//3월
			jsonObj.file_name	= file_name;		//파일이름

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		alert(stringJson);
       
		var url = "./insertSchedule.do";//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공
			alert("저장성공!");
		}).fail(function(data){//통신 실패
			alert("저장실패");
		})
		
		
	})
   
	//------------------------------------------------------------
	//1~12월 체크박스 클릭 시 체크 여부에 따라 점검주기의 n회/년 으로 텍스트가 바뀝니다.
	//------------------------------------------------------------
	$(".tableCheck").click(function(){
			
		var rowIndex = $(this).parent().parent().children().index($(this).parent());//클릭한 row
		var checkMonth = 'checkMonth'+rowIndex;										//input name+row
		var cehckLength = $('input:checkbox[name='+checkMonth+']:checked').length;	//클릭한 row의 월 체크 개수
		var check_sycle = 'check_sycle'+rowIndex; 									//클릭한 row의 점검주기 클래스
		$('.'+check_sycle).text(cehckLength+'회/년');									//클릭한 row의 월 체크 개수 n회/년 적용
		
	});

   	//-----------------------------
   	//파일이름을 누르면 호출되는 팝업창 입니다.
   	//-----------------------------
	$(".filePopup").click(function(){
		
		alert("파일관련 팝업창 호출!"); 
		
	});
	
   	//----------------------------
   	//행추가 기능입니다.
   	//----------------------------
   	$("#tableAdd").click(function(){
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 10){
			alert("10개 이상 생성 불가.");   			
   			return; 
   		};
   		
   		var insertUl = "";//ul에 추가할 데이터를 담을 변수입니다.
   		insertUl += "<ul class='row'>";
   		insertUl += "<li class='col-xs-1 col-md-1 tableCount'><input type='checkbox' name='table_check'></li>";
   		insertUl += "<li class='col-xs-3 col-md-3'><lable class=''>신규업무입니다람쥐</label></li>";
   		insertUl += "<li class='col-xs-2 col-md-2'><label class=''>0회/년</label></li>";
   		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_jan' name='' type='checkbox' value=''></li>";
   		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_feb' name='' type='checkbox' value=''></li>";
   		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_mar' name='' type='checkbox' value=''></li>";
   		insertUl += "<li class='col-xs-2 col-md-2'><label class='filePopup'>파일이름</label></li>";
   		insertUl += "</ul>"
   		$("#formArray").append(insertUl);
   		
   	});
   	
   	//----------------------------
   	//수정 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableUp").click(function(){
   		
   		alert("수정기능 개발 중");
   		
   	});

   	//----------------------------
   	//삭제 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableDel").click(function(){
   		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//------------------------
   		//삭제할 데이터가 없으면 바로 종료
   		//------------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("삭제할 데이터를 선택해 주세요.");
   			return;
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var jsonArr = new Array();							//JsonArray를 위한 배열생성
        var totalJson = new Object();						//JsonObject의 합
		
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <li>이다.
			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.
			var ul = checkbox.parent().parent().eq(i);
			var li = ul.children();
			
			// li.eq(0)은 체크박스 이므로  li.eq(1)의 값부터 가져온다.
			var schedule_seq = li.eq(0).children().val();	//연간스케쥴 시퀀스
			if(schedule_seq == 'on'){schedule_seq = 0;};	//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.schedule_seq	= schedule_seq;			//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteSchedule.do";					//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(response){//통신 성공
			
			var jsonData = JSON.stringify(response);
			alert(jsonData.resultCode);
		
			var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
			checkbox.parent().parent().remove();
			alert("삭제성공!");
			
			return;//테스트 중
			
		}).fail(function(data){//통신 실패
			alert("삭제실패");
			return;
		})
		
   	});
   	
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
	
	<select id="selectCode" name="selectCode">
	    <option value="AS01">행정 업무</option>
	    <option value="AS02">회계 업무</option> 
	    <option value="AS03">조경 업무</option>
	    <option value="AS04">시설 업무</option>
	    <option value="ALL">전체</option>
	</select>
	<button id="tableBtn" class="btn btn-success" >저장</button>	
	<button id="tableUp" class="btn btn-success" >수정</button>
	<button id="tableDel" class="btn btn-success" >삭제</button>		
	<button id="tableAdd" class="btn btn-success" >행추가</button>	
	
	<!-- 게시글 없을 때. --> 
	<c:if test="${count==0}"> 
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
			<div class="board-list-wrap borard-list-con">
				<form id="formArray" name="formArray"  autocomplete="off">
					<c:set var="number" value="${scheduleCnt}" />
					<c:forEach var="scheduleList" items="${scheduleList}" varStatus="scheduleNum">
						<ul class="row" >
							<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" value="${scheduleList.schedule_seq}"></li>
							<li class="col-xs-3 col-md-3"><label>${scheduleList.work_info}</label></li>
							<li class="col-xs-2 col-md-2"><label class="check_sycle${scheduleNum.count-1}">${scheduleList.check_cycle}</label></li>
							<li class="col-xs-1 col-md-1 tableCheck "><input id="schedule_jan" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_jan}" <c:if test="${scheduleList.schedule_jan ne '0'}">checked</c:if>></li>
							<li class="col-xs-1 col-md-1 tableCheck "><input id="schedule_feb" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_feb}" <c:if test="${scheduleList.schedule_feb ne '0'}">checked</c:if>></li>
							<li class="col-xs-1 col-md-1 tableCheck "><input id="schedule_mar" name="checkMonth${scheduleNum.count-1}" type="checkbox" value="${scheduleList.schedule_mar}" <c:if test="${scheduleList.schedule_mar ne '0'}">checked</c:if>></li>			
							<li class="col-xs-2 col-md-2"><label class="filePopup">${scheduleList.file_name}</label></li>
							
						</ul>
					</c:forEach>
				</form>
			</div>
		</div>
	</section>	
	
	<form id="selectForm" name="selectForm"  action="scheduleList.do" autocomplete="off">
		<input id="division" name="division" type="hidden" value ="">
	</form>
	
</div>

</body>
</html>