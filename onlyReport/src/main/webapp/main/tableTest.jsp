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
	
	//------------------------------
	//테이블을 저장하는 로직입니다.
	//------------------------------
	$("#tableBtn").click(function(){
		
		//-------------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-------------------------------------------
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
		
		
		var jsonArr = new Array();	//JsonArray를 위한 배열생성
        var jsonObj = new Object();	//JsonObject를 위한 객체생성
        var totalJson = new Object();//JsonObject의 합
		
		
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var ul = checkbox.parent().parent().eq(i);
			var li = ul.children();
			
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(ul.text());
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var work_info = li.eq(1).text();
			var check_cycle = li.eq(2).text();
			var schedule_jan = li.eq(3).text();
			var schedule_feb = li.eq(4).text();
			var schedule_mar = li.eq(5).text();
			var entity = li.eq(6).text();
			var file_name = li.eq(7).text();
			
			// 가져온 값을 배열에 담는다.
// 			jsonObj.work_info	= work_info;		//업무내용 
// 			jsonObj.check_cycle = check_cycle;		//점검주기
// 			jsonObj.schedule_jan= schedule_jan;		//1월
// 			jsonObj.schedule_feb= schedule_feb;		//2월
// 			jsonObj.schedule_mar= schedule_mar;		//3월
// 			jsonObj.entity		= entity;			//관리주체
// 			jsonObj.file_name	= file_name;		//파일이름
			
// 			tdArr.push(jsonObj);

			tdArr.push(work_info);
			tdArr.push(check_cycle);
			tdArr.push(schedule_jan);
			tdArr.push(schedule_feb);
			tdArr.push(schedule_mar);
			tdArr.push(entity);
			tdArr.push(file_name);
			

		});
		
		var stringJson = JSON.stringify(tdArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		/* alert(stringJson); */
       
		var url = "./tableInsert.do";//url 테이블 데이터 저장
		
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
		var scheuld_sycle = 'scheuld_sycle'+rowIndex; 								//클릭한 row의 점검주기 클래스
		$('.'+scheuld_sycle).text(cehckLength+'회/년');								//클릭한 row의 월 체크 개수 n회/년 적용
		
	});

   	//-----------------------------
   	//파일이름을 누르면 호출되는 팝업창 입니다.
   	//-----------------------------
	$(".filePopup").click(function(){
		
		alert("파일관련 팝업창 호출!"); 
		
	});
	
	/* var rowIndex = $(this).parent().parent().children().index($(this).parent()); */
	/* var colIndex = $(this).parent().children().index($(this)); */
	/* $('input:checkbox[name=name]:checked').length; */
	
	
	// 테이블의 Row 클릭시 값 가져오기
	$("#example-table-1 tr").click(function(){ 	

		var str = ""
		var tdArr = new Array();	// 배열 선언
		
		// 현재 클릭된 Row(<tr>)
		var tr = $(this);
		var td = tr.children();
		
		// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
		console.log("클릭한 Row의 모든 데이터 : "+tr.text());
		
		// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
		td.each(function(i){
			tdArr.push(td.eq(i).text());
		});
		
		console.log("배열에 담긴 값 : "+tdArr);
		
		// td.eq(index)를 통해 값을 가져올 수도 있다.
		var no = td.eq(0).text();
		var userid = td.eq(1).text();
		var name = td.eq(2).text();
		var email = td.eq(3).text();
		
		
		str +=	" * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
				", 아이디 : <font color='red'>" + userid + "</font>" +
				", 이름 : <font color='red'>" + name + "</font>" +
				", 이메일 : <font color='red'>" + email + "</font>";		
		
		$("#ex1_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());		
		$("#ex1_Result2").html(str);
	});

	
	// 상단 선택버튼 클릭시 체크된 Row의 값을 가져온다.
	$("#selectBtn").click(function(){ 
		
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $("input[name=user_CheckBox]:checked");
		
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(tr.text());
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var no = td.eq(1).text()+", "
			var userid = td.eq(2).text()+", ";
			var name = td.eq(3).text()+", ";
			var email = td.eq(4).text()+", ";
			
			// 가져온 값을 배열에 담는다.
			tdArr.push(no);
			tdArr.push(userid);
			tdArr.push(name);
			tdArr.push(email);
			
			//console.log("no : " + no);
			//console.log("userid : " + userid);
			//console.log("name : " + name);
			//console.log("email : " + email);
		});
		
		$("#ex3_Result1").html(" * 체크된 Row의 모든 데이터 = "+rowData);	
		$("#ex3_Result2").html(tdArr);	
	});
	
	
	
});

</script>

<body>

<div class="container">

	<!-- 게시판 헤더 -->
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h3 class="board-list-header"><a href="menuList.do"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>메뉴 목록</a></h3>
		</div>
	</section>
	
	<button id="tableBtn" class="btn btn-success" >저장</button>	
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
					<li class="col-xs-2 col-md-2">업무내용</li>
					<li class="col-xs-2 col-md-2">점검주기</li>
					<li class="col-xs-1 col-md-1">1월</li>
					<li class="col-xs-1 col-md-1">2월</li>
					<li class="col-xs-1 col-md-1">3월</li> 
					<li class="col-xs-2 col-md-2">관리주체</li> 
					<li class="col-xs-2 col-md-2">파일이름</li>
				</ul> 
			</div>
				<div class="board-list-wrap borard-list-con">
				<form id="formArray" name="formArray" action="/tableInsert.do" method="post" autocomplete="off">
					<c:set var="number" value="${tableCnt}" />
					<c:forEach var="tableList" items="${tableList}" varStatus="tableNum">
						<ul class="row">
						<li class="col-xs-1 col-md-1 tableCount"><input type="checkbox" name="table_check" ></li>
						<%-- <li class="col-xs-1 col-md-1 tableCount"><lable>${tableNum.count}</lable></li> --%>
						<li class="col-xs-2 col-md-2"><label>${tableList.job_content}</label></li>
						<li class="col-xs-2 col-md-2"><label class="scheuld_sycle${tableNum.count-1}">${tableList.schedule_cycle}회/년</label></li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month1" name="checkMonth${tableNum.count-1}" type="checkbox" value="${tableList.month1}" <c:if test="${tableList.month1 ne '0'}">checked</c:if>></li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month2" name="checkMonth${tableNum.count-1}" type="checkbox" value="${tableList.month2}" <c:if test="${tableList.month2 ne '0'}">checked</c:if>></li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month3" name="checkMonth${tableNum.count-1}" type="checkbox" value="${tableList.month3}" <c:if test="${tableList.month3 ne '0'}">checked</c:if>></li>
						
						<li class="col-xs-2 col-md-2">${tableList.entity}</li>
						<li class="col-xs-2 col-md-2"><label class="filePopup">${tableList.file_name}</label></li>
						</ul>
					</c:forEach>
				</form>
				</div>
		</div>
	</section>	
	
	<section>	
	<div class="row">
		<table id="example-table-1" width="100%" class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>No. </th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>				
				<tr>
					<td>1</td>
					<td>user01</td>
					<td>홍길동</td>
					<td>hong@gmail.com</td>
				</tr>
				<tr>
					<td>2</td>
					<td>user02</td>
					<td>김사부</td>
					<td>kim@naver.com</td>
				</tr>
				<tr>
					<td>3</td>
					<td>user03</td>
					<td>존</td>
					<td>John@gmail.com</td>
				</tr>
			</tbody>
		</table>
		<div class="col-lg-12" id="ex1_Result1" ></div> 
		<div class="col-lg-12" id="ex1_Result2" ></div> 
	</div>
	</section>	
	
	<section>	
	<div class="row">
	
		<button type="button" class="btn btn-outline btn-primary pull-right" id="selectBtn">선택</button>
		<table id="example-table-3" width="100%" class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>선택</th>
					<th>No. </th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>				
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>1</td>
					<td>user07</td>
					<td>NC소프트</td>
					<td>nc@gmail.com</td>
				
				</tr>
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>2</td>
					<td>user08</td>
					<td>넥슨</td>
					<td>donson@naver.com</td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>3</td>
					<td>user09</td>
					<td>넷마블</td>
					<td>net@gmail.com</td>
				</tr>
			</tbody>
		</table>
		<div class="col-lg-12" id="ex3_Result1" ></div> 
		<div class="col-lg-12" id="ex3_Result2" ></div> 
	</div>
	</section>
	
</div>

</body>
</html>