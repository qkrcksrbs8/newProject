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
	
	$('#boardInsertBtn').on('click', function(){
		
		location.href='writeBoard.do';
		
	});
	
});

</script>

<style>
ul {
	position:relative;
}
button {
	position : absolute;
	top:50%;  
	right:0;
	transform:translate(0, -50%);
	-webkit-transform:translate(0, -50%);
} 
select {
	position : absolute;
	top:50%;  
	right:0;
	transform:translate(0, -50%);
	-webkit-transform:translate(0, -50%);
}
</style>

<body>

<div class="container">

	<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>게시판 목록</h3>
	</div>
</section>
	
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
						<li class="col-md-1 col-xs-1">${boardList.rnum} </li> 
						<li class="col-md-6 col-xs-6"><a href="boardDetail.do?board_seq=${boardList.board_seq}">${boardList.board_title}</a></li>
						<li class="col-md-2 col-xs-2">${boardList.created_by }</li>
						<li class="col-md-2 col-xs-2">${boardList.created_date.substring(2,4)}월 ${boardList.created_date.substring(4)}일</li>	
					</ul>
				</c:forEach>
			</div>
		</div>
	</section>	
	
	<!-- 자유게시판 list page paging -->
	<section id="content" class="board-list-paging">
		<div class="container">
			<div class="row position:relative;">
				<div class="col-md-12">
					<ul class="pagination">
						<li>
							${pagingHtml}
						</li> 
						
					</ul>		 
					<div class="form-group  col-4 col-md-3 offset-md-8">
						<div class="white-section">
							<select id="inputState" name="keyField" class="form-control selectpicker">					
								<option value="board_title">제목</option>
								<option value="board_content">제목+본문</option>
								<option value="user_name">작성자</option>
							</select> 
						</div>
					</div>
					<button id="boardInsertBtn" class="btn btn-success" >글작성</button>
				</div>
			</div>
		</div>
	</section>		
	
</div>

</body>
</html>