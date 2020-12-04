function check_onclick(){
	theForm = document.getElementById('loginform');
	if(theForm.memberId.value == ""){
		alertBox("아이디를 입력해주세요.");
		return false;
	}else if(theForm.memberPw.value == ""){
		alertBox("비밀번호를 입력해주세요.");
		return false;		
	}else{
		theForm.submit();
	}
}

function enterkey() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	check_onclick();
    }
}