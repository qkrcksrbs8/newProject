<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1>안녕하세요</h1>

	<div class="card-body" style="padding: 40px;">
		<form id="login-form" name="login-form" class="nobottommargin" action="test.do" method="post">
			<h3>로그인</h3>
	
			<div class="col_full">
				<label>아이디:</label>
				<input type="text" id="user_id" name="user_id" class="form-control not-dark" required/>
			</div>
	
			<div class="col_full">
				<label>비밀번호:</label>
				<input type="password" id="password" name="password" class="form-control not-dark" required/>
			</div>
			
			<div class="col_full nobottommargin">								
				<button class="button button-black nomargin" id="submit" name="submit" value="login">로그인</button>
			</div>
		</form>
	</div>

</body>
</html>
