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
	
	var workDate = "${workDate}";			//업무코드		AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	$("#selectCode").val(workDate);			//페이지 진입 시 selectBox 선택
	
	var addList = "${addList}";				//add:행추가 / normal:일반 출력
	//-----
	//행추가
	//-----
	if("add" == addList){
		
		$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
		$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
		$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
		$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
		
	}
	
	//------------------
	//셀렉트박스 조회
	//------------------
	$('#selectCode').change(function() {
		
		var workDate = $(this).val();		//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
		$("#workDate").val(workDate);		//업무구분
	    $("#addList").val("");				//행추가 변수 값 초기화
		$("#selectForm").submit();			//서브밋
		
	});
	

	
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
   	
   	
  
	//---------------------
	//테이블을 저장하는 로직입니다.
	//---------------------
	$("#tableSave").click(function(){
		
		//-----------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-----------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");	//모든 체크박스	
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합
		var work_date = $("#selectCode").val();			//업무구분
		
		//-----------------
		//체크박스 반복문입니다.
		//-----------------
		checkbox.each(function(i) {

  			var ul = checkbox.parent().parent().eq(i);		// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   		var li = ul.children();							// checkbox.parent() : checkbox의 부모는 <li>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var work_seq 	= li.eq(0).children().val();	//세부업무 실적 시퀀스
			var sector 		= li.eq(1).text();				//부문
			var fr_work		= li.eq(2).text();				//예정업무
			var to_work 	= li.eq(3).text();				//실시업무
			var remark 		= li.eq(4).text();				//remark

			if(work_seq == 'on'){work_seq = 0;};			//세부업무 실적 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();		//JsonObject를 위한 객체생성
			jsonObj.work_seq = work_seq;	//고유번호
			jsonObj.sector	= sector		//부문
			jsonObj.fr_work	= fr_work;		//예정업무 
			jsonObj.to_work = to_work;		//실시업무
			jsonObj.remark= remark;			//비고
			jsonObj.work_date = work_date	//기준년도

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertDetailedWork.do";		//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공
			alert("저장성공!");
		    var workDate = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
		    $("#workDate").val(workDate);							//업무구분
		    $("#addList").val("normal");							//행추가 변수 값 add
			$("#selectForm").submit();								//서브밋
			
		}).fail(function(data){//통신 실패
			alert("저장실패");
		})
		
	})
	
   	
		
   	//----------------------------
   	//수정 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableUp").click(function(){
   		
		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//------------------------
   		//수정할 데이터가 없으면 바로 종료
   		//------------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("수정할 데이터를 선택해 주세요.");
   			return;
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스

   			//-------------------------
   			//체크박스 반복문입니다.
   			//-------------------------

   			if("수정" == $("#tableUp").text()){
   				
   				checkbox.each(function(i) {

   	   				var ul = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
   	   				var li = ul.children();								// checkbox.parent() : checkbox의 부모는 <li>이다. 	   				
   	   				var sector_val	= li.eq(1).text();					//부문
   	   				var fr_work_val = li.eq(2).text();					//예정업무
   	   				var to_work_val = li.eq(3).text();					//실시업무
   	   				var remark_val	= li.eq(4).text();					//비고
   	   				
   	   				li.eq(0).attr("disabled", true);
   	   				li.eq(1).html('<input id="sector_val"	type="text" value="'+sector_val+'">');	//부문	수정 가능한 필드로 변경
   	   				li.eq(2).html('<input id="fr_work_val"	type="text" value="'+fr_work_val+'">');	//예정업무	수정 가능한 필드로 변경
   	   				li.eq(3).html('<input id="to_work_val"	type="text" value="'+to_work_val+'">');	//실시업무	수정 가능한 필드로 변경
   	   				li.eq(4).html('<input id="remark_val"	type="text" value="'+remark_val+'">');	//비고	수정 가능한 필드로 변경

   	   			});

				
   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
   				
   			}else{
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   							
   				if(result){
   				
   					var seq = 0;//시퀀스 - 0:신규
   					
					//----------------------
					//li.eq(0)은 체크박스입니다.
					//----------------------
   					checkbox.each(function(i) {
	
	   	   				var ul			= checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   	   				var li			= ul.children();					// checkbox.parent() : checkbox의 부모는 <li>이다.
	   	   				seq				= li.eq(0).children().val();		//시퀀스 - 0:신규
	   	   				var sector_val	= li.eq(1).children().val();		//부문
	   	   				var fr_work_val = li.eq(2).children().val();		//예정업무
	   	   				var to_work_val = li.eq(3).children().val();		//실시업무
	   	   				var remark_val	= li.eq(4).children().val();		//비고
	   	   				
	   	   				li.eq(1).html('<label>'+sector_val+'</label>');		//수정 완료되면 다시 label로 변경
	   	   				li.eq(2).html('<label>'+fr_work_val+'</label>');	//수정 완료되면 다시 label로 변경
	   	   				li.eq(3).html('<label>'+to_work_val+'</label>');	//수정 완료되면 다시 label로 변경
	   	   				li.eq(4).html('<label>'+remark_val+'</label>');		//수정 완료되면 다시 label로 변경
	
	   	   			});
	   				
   					//------------------------
   					//행추가 중이면 행추가 버튼 비활성화
   					//------------------------
   					if(0 != seq){
   						$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
   					};//if
   					
	   				$("#tableUp").text("수정");					//수정 완료 버튼의 글자를 수정으로 변경
	   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화  	 수정 중일 때 
	   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 	 수정 중일 때 
	   				
	   				alert("저장 버튼을 눌러주세요.");					//수정 완료
	   				
   				}else{return;};									//수정 완료 버튼을 누르지 않으면 리턴
   				//else
   				
   			};//else
   			
   	});
	
	
	
   	//------------------------
   	//삭제 기능을 정의한 메서드입니다.
   	//------------------------
   	$("#tableDel").click(function(){
   		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//-----------------------
   		//삭제할 데이터가 없으면 바로 종료
   		//-----------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("삭제할 데이터를 선택해 주세요.");
   			return;
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var jsonArr = new Array();							//JsonArray를 위한 배열생성
        var totalJson = new Object();						//JsonObject의 합
		
        var seq = -1;										//시퀀스 - -1:초기 값
		//-----------------
		//체크박스 반복문입니다.
		//-----------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <li>이다.
			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.
			var ul = checkbox.parent().parent().eq(i);
			var li = ul.children();
			
			// li.eq(0)은 체크박스 이므로  li.eq(1)의 값부터 가져온다.
			
			//-------------------------------
			//삭제 시 신규로 추가된 행을 삭제하면 담는다.
			//한 번 담으면 나머지는 패스
			//-------------------------------
			if(seq != -1){
				seq	= li.eq(0).children().val();			//시퀀스 - 0:신규	
			};//if
			
			var work_seq = li.eq(0).children().val();		//연간스케쥴 시퀀스
			if(work_seq == 'on'){work_seq = 0;};			//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.work_seq	= work_seq;					//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteDetailedWork.do";				//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(response){//통신 성공
			
			//--------------------------
			//행추가를 삭제하면 행추가 버튼 활성화
			//--------------------------
			if(0 == seq){
				$("#tableAdd").attr("disabled", false);	//행추가 버튼 활성화 
			};//if
			
			var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
			checkbox.parent().parent().remove();
			alert("삭제성공!");

			return;//테스트 중
			
		}).fail(function(data){//통신 실패
			alert("삭제실패");
			return;
		})
		
   	});
   	
	
   	                                           
   	//--------------
   	//행추가 기능입니다.
   	//--------------
   	$("#tableAdd").click(function(){
   		
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 10){
			alert("10개 이상 생성 불가.");   			
   			return; 
   		};
   		
	    var workDate = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#workDate").val(workDate);							//업무구분
	    $("#addList").val("add");								//행추가 변수 값 add
		$("#selectForm").submit();								//서브밋
		$("#addList").val("");									//행추가 변수 공백

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
	
	<!-- 페이지 제목 -->
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h3 class="board-list-header">하자보수현황</h3>
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

</body>
</html>