<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="/js/jquery-1.12.1.js"></script>
<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">
<script type="text/javascript">
$(document).ready(function() {
	/* 로그인버튼 */
	$("#loginBtn").click(function(){
		var id = $("#loginId").val();
		var pass = $("#loginPass").val();
		$("#hiddenId").val(id);
		$("#hiddenPass").val(pass);
		if(id==""){
			alert("아이디를 입력하세요");
			return false;
		}
		if(pass==""){
			alert("비밀번호를 입력하세요");
			return false;
		}
		$("#loginId").val("");
		$("#loginPass").val("");
		$("#hiddenId").val("");
		$("#hiddenPass").val(""); 
	});
});
</script>
<body>
	<center>	
		<h2>로그인하세용가리</h2>
		<div style="text-align:center;width:270px;">
			<div style="text-align:right">	
				ID:<input type="text" id="loginId" style="align:right"><br>
				Password:<input type="password" id="loginPass" style="align:right"><br>
			</div>
		</div>
	</center>
	
	<form id="loginForm" action="/main.do" accept-charset="utf-8" method="post">
		<center>
			<button id="loginBtn" type="submit" style="width:270px;height:50px;background:green;font-size:20px;">로그인</button> 
			<input type="hidden" id="hiddenId" name="hiddenId" value="">
			<input type="hidden" id="hiddenPass" name="hiddenPass" value="">
		</center>
	</form>
</body>
</html>
