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
	
   //------
   //저장버튼 
   //------
	$("#buttonId").click(function(){
		
		var inputListCnt = $("input[name=month1]").length;//리스트 개수
		var jsonArr = new Array();	//JsonArray를 위한 배열생성
        var jsonObj = new Object();	//JsonObject를 위한 객체생성
        var totalJson = new Object();//JsonObject의 합
        
		jsonObj.month1 = $("#month1").val();
		jsonObj.month2 = $("#month2").val();
		jsonObj.month3 = $("#month3").val();
		jsonArr.push(jsonObj)
		totalJson.arr = jsonArr;
		var stringJson = JSON.stringify(totalJson);
		alert(stringJson);
       

		var url = "./tableInsert.do";//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,data: {
				totalJson:totalJson
			}
		}).done(function(data){//통신 성공
			alert("저장성공!");
		}).fail(function(data){//통신 실패
			alert("저장실패");
		})
		
/*         jobj .name = "송강호";
        jobj .age = "25";
        jobj .gender = "남자";
        jobj .nickname = "남궁민수";
        jArray .push(jobj );
 
        var totalInfo = new Object();
        totalInfo.arr1= jArray ;

        var stringJson = JSON.stringify(totalInfo);

		alert(stringJson); */
	})
	
	//------------------------------
	//테이블을 저장하는 로직입니다.
	//------------------------------
	$("#tableBtn").click(function(){
		
		var url = "./tableInsert.do";//url 테이블 데이터 저장
		
		var month1 = $("#month1").val();//1월 체크박스 입니다.
		var month2 = $("#month2").val();//2월 체크박스 입니다.
		var month3 = $("#month3").val();//3월 체크박스 입니다.
		
		var data = jQuery("#gridid").getRowData();
		alert("data : "+data);
		
		$.ajax({
			 method: "POST"
			,url : url
			,data: {
				 month1:month1
				,month2:month2
				,month3:month3
			}
		}).done(function(data){//통신 성공
			alert("저장성공!");
		}).fail(function(data){//통신 실패
			alert("저장실패");
		})
		
		
	})
   
	$(".tableCheck").click(function(){
		var rowIndex = $(this).parent().parent().children().index($(this).parent());//로우
/* 		var month_check = "month_check"+rowIndex;//체크박스 이름 */
		var cehckLength = $("#month1").html();
		
		alert(cehckLength);
		return;
		var scheuld_sycle = 'scheuld_sycle'+rowIndex; //점검주기
		$('.'+scheuld_sycle).text(cehckLength+'회/년');
	})
   		
	/* var rowIndex = $(this).parent().parent().children().index($(this).parent()); */
	/* var colIndex = $(this).parent().children().index($(this)); */
	/* $('input:checkbox[name=name]:checked').length; */
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
	
	<input type="button" id="buttonId" value="버튼입니다.">
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
					<c:set var="number" value="${tableCnt}" />
					<c:forEach var="tableList" items="${tableList}" varStatus="tableNum">
						<ul class="row">
						<li class="col-xs-1 col-md-1 tableCount">${tableNum.count}</li>
						<li class="col-xs-2 col-md-2">${tableList.job_content}</li>
						<li class="col-xs-2 col-md-2 scheuld_sycle${tableNum.count-1}">${tableList.schedule_cycle}회/년</li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month1" name="month1" type="checkbox" value="${tableList.month1}" <c:if test="${tableList.month1 ne '0'}">checked</c:if>></li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month2" name="month2" type="checkbox" value="${tableList.month2}" <c:if test="${tableList.month2 ne '0'}">checked</c:if>></li>
						<li class="col-xs-1 col-md-1 tableCheck tableCheck${tableNum.count-1}"><input id="month3" name="month3" type="checkbox" value="${tableList.month3}" <c:if test="${tableList.month3 ne '0'}">checked</c:if>></li>
						<li class="col-xs-2 col-md-2">${tableList.entity}</li>
						<li class="col-xs-2 col-md-2">${tableList.file_name}</li>
						</ul>
					</c:forEach>
				</div>
		</div>
	</section>	
	
</div>

</body>
</html>