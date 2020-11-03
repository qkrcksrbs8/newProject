<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
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
	
	$('#updateReportBtn').on('click', function(){
		var updateBtnVal = $('#updateReportBtn').text();
		
		if('글수정'==updateBtnVal){
			
			$('#updateReportBtn').text('글저장');
			$('#detatilContentPre').attr('hidden','true');
			$('#detatilContent').removeAttr('hidden');
			$('#detatilTitlePre').attr('hidden','true');
			$('#detatilTitle').removeAttr('hidden');
		
		}else{ 
			 
			var report_seq = '${reportList.report_seq}'
			var report_title = $('#detatilTitlePre').text();
			var report_content = $('#detatilContentPre').text();
			var user_name = '관리자';
 
			var report_title_val = $('#detatilTitle').val();
			var report_content_val = $('#detatilContent').val(); 
			
			if(report_title_val.length < 1){
				
				alert('제목을 입력하세요.');
				return false;
			
			}else if(report_content_val.length < 1){
			
				alert('내용을 입력하세요.');
				return false;
			
			}else if(getByteB(report_title_val) > 25){
				
				alert('제목은 25byte 이하로 작성해주세요.');
				return false;
				
			}else if(getByteB(report_content_val) > 200){
				
				alert('내용은 200byte 이하로 작성해주세요.'	);
				return false;
				
			}//if
			var special_pattern = /[\{\}\[\]\/?;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
			if( special_pattern.test(report_title_val) == true ){
			    alert('제목에 특수문자는 사용할 수 없습니다.');
			    return false;
			}
			if( special_pattern.test(report_content_val) == true ){
			    alert('내용에 특수문자는 사용할 수 없습니다.');
			    return false;
			}
			
			if(report_content != report_content_val || report_title != report_title_val){//게시글 상세 내용 변경 시 update
				 
				$.ajax({
				    type : 'POST',
				    url : 'updateReport.do', 
				    data : {
				    	report_seq 		: report_seq
				    	, report_title 	: report_title_val
				    	, report_content: report_content_val
				    	, user_name : user_name
				    },
				    error : function(error) {
				        alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
				    },
				    success : function(data) {
				        alert("저정되었습니다.");
				    }
				});

			};
				
			$('#updateReportBtn').text('글수정');
			$('#detatilContentPre').text($('#detatilContent').val()); 
			$('#detatilContent').attr('hidden','true');
			$('#detatilContentPre').removeAttr('hidden');
			$('#detatilTitlePre').text($('#detatilTitle').val()); 
			$('#detatilTitle').attr('hidden','true');
			$('#detatilTitlePre').removeAttr('hidden');
			
		}//if
		
	});
	
	$('#deleteReportBtn').on('click', function(){
		
		var report_seq = '${reportList.report_seq}';
		var user_name = '관리자';
		
		if(confirm("삭제 하시겠습니까?")){
			
			$.ajax({
			    type : 'POST',
			    url : 'deleteReport.do', 
			    data : {
			    	report_seq 	: report_seq
			    	, user_name : user_name
			    },
			    error : function(error) {
			        alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
			    },
			    success : function(data) {
			        alert("저장되었습니다.");
			        location.href = 'reportList.do';
			    }
			});
			
		}else{
			return;
		}//else
		
	})
	
});

function getByteB(str) {//byte 체크

	var byte = 0;

	for (var i=0; i<str.length; ++i) {

		// 기본 한글 2바이트 처리
		(str.charCodeAt(i) > 127) ? byte += 2 : byte++ ;

	}

	return byte;

}

</script>
<body>
<!-- 게시글* 상세 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>게시글 상세</h3>
	</div>
</section>
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-tit">
			<pre id="detatilTitlePre">${reportList.report_title}</pre>
			<textarea class="required sm-form-control" id="detatilTitle" rows="1" cols="30" hidden="true">${reportList.report_title}</textarea> 
		</div>
	</div>
</section>		
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-sub" style="min-height: 200px; text-align: left;">
			<pre id="detatilContentPre">${reportList.report_content}</pre> 
			<textarea class="required sm-form-control" id="detatilContent" rows="10" cols="30" hidden="true">${reportList.report_content}</textarea> 
		</div>
	</div>
</section>		
	
<section id="content">
	<div class="container board-view-con">  
		<div class="board-view-sub board-view-utilbtn">
			<button id="updateReportBtn" class="btn btn-success">글수정</button>
			<button id="deleteReportBtn" class="btn btn-success">글삭제</button>
			<a href="reportList.do"><button class="btn btn-success">글목록</button></a>
		</div>
	</div>
</section>		


</body>
</html>