<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

$(function(){
	
	$('#boardUpdateBtn').on('click', function(){
		var updateBtnVal = $('#boardUpdateBtn').text();
		
		if('글수정'==updateBtnVal){
			
			$('#boardUpdateBtn').text('글저장');
			$('input[name=board_title]').removeAttr('readOnly');
		
		}else{
			
			$.ajax({
	            url:'boardUpdate.do',
	            success:function(data){
	            	
	                alert('저장이 완료되었습니다.');
	                
	                $('#boardUpdateBtn').text('글수정');
	    			$('input[name=board_title]').attr('readOnly','true');
	    			
	            },error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 

		        	alert('저장에 실패하였습니다.');

		        }//error
	            
	        })//ajax
			
		}//if
		
	});
	
});
 
</script>
<body>

	<h1>게시판입니다.</h1>
	
<section id="content">
	<div class="col_full">
		<label for="template-contactform-subject">제목 <small>*</small></label>
		<input type="text" id="template-contactform-subject" name="board_title" value="${boardList.board_title }" class="required sm-form-control" readOnly="readOnly" /> 
	</div>
</section>	
	
<section id="content">
	<div class="col_full">
		<label for="template-contactform-subject">내용 <small>*</small></label>
		<input type="text" id="template-contactform-subject" name="board_content" value="${boardList.board_content }" class="required sm-form-control" />
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