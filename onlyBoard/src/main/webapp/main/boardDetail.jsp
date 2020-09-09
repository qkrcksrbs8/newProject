<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
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
	
	$('#boardUpdateBtn').on('click', function(){
		var updateBtnVal = $('#boardUpdateBtn').text();
		
		if('글수정'==updateBtnVal){
			
			$('#boardUpdateBtn').text('글저장');
			$('input[name=board_title]').removeAttr('readOnly');
		
		}else{

		}//if
		
	});
	
});
 
</script>
<body>
<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>게시판 상세</h3>
	</div>
</section>
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-tit">
			${boardList.board_title }
		</div>
	</div>
</section>		
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-sub">
			<pre>${boardList.board_content }</pre>
		</div>
	</div>
</section>		
	
<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-sub board-view-utilbtn">
			<button id="boardUpdateBtn" class="btn btn-success">글수정</button>
			<button id="boardDeleteBtn" class="btn btn-success">글삭제</button>
			<a href="boardList.do"><button class="btn btn-success">글목록</button></a>
		</div>
	</div>
</section>		


</body>
</html>