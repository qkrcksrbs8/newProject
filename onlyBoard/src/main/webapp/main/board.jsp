<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<div class="container">

	<h1>게시판입니다.</h1>
	
	<!-- 출력할 글 데이터가 하나도 없을 때. --> 
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
	
	<!-- 출력할 글 데이터가 있을 때. --> 
	<section id="content">
		<div class="container">
			<div class="board-list-wrap board-list-header">
				<ul class="row">	
					<li class="col-xs-1 col-md-1">번호</li>
					<li class="col-xs-6 col-md-6">제목</li>
					<li class="col-xs-2 col-md-2">작성자</li>
					<li class="col-xs-2 col-md-2">작성일</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<c:set var="number" value="${count}" />
				<c:forEach var="boardList" items="${boardList}" varStatus="boardNum">
					<ul class="row">
						<li class="col-md-1 col-xs-1">${count-boardNum.index} </li> 
						<li class="col-md-6 col-xs-6"><a href="boardDetail.do?board_seq=${boardList.board_seq}">${boardList.board_title}</a></li>
						<li class="col-md-2 col-xs-2">${boardList.created_by }</li>
						<li class="col-md-2 col-xs-2">${boardList.created_date.substring(2,4)}월 ${boardList.created_date.substring(4)}일</li>	
					</ul>
				</c:forEach>
			</div>
		</div>
	</section>	
</div>

</body>
</html>