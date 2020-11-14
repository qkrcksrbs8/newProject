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
					<li class="col-xs-1 col-md-1">점검주기</li>
					<li class="col-xs-1 col-md-1">1월</li>
					<li class="col-xs-1 col-md-1">2월</li>
					<li class="col-xs-1 col-md-1">3월</li>
					<li class="col-xs-1 col-md-1">4월</li>
					<li class="col-xs-2 col-md-2">관리주체</li>
					<li class="col-xs-2 col-md-2">파일이름</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<c:set var="number" value="${tableCnt}" />
				<c:forEach var="tableList" items="${tableList}" varStatus="tableNum">
					<ul class="row">
					<li class="col-xs-1 col-md-1">${tableNum.count}</li>
					<li class="col-xs-2 col-md-2">${tableList.job_content}</li>
					<li class="col-xs-1 col-md-1">${tableList.schedule_cycle}회/년</li>
					<li class="col-xs-1 col-md-1">${tableList.month1}</li>
					<li class="col-xs-1 col-md-1">${tableList.month2}</li>
					<li class="col-xs-1 col-md-1">${tableList.month3}</li>
					<li class="col-xs-1 col-md-1">${tableList.month4}</li>
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