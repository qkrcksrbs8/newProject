$(function() {

	//------------------
	//셀렉트박스 조회
	//------------------
	$('#selectCode').change(function() {
		
		var division = $(this).val();		//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#division").val(division);		//업무구분
	    $("#addList").val("");				//행추가 변수 값 초기화
		$("#selectForm").submit();			//서브밋
		
	});
	
	 
	//------------------------------------------------------------
	//1~12월 체크박스 클릭 시 체크 여부에 따라 점검주기의 n회/년 으로 텍스트가 바뀝니다.
	//------------------------------------------------------------
	$(".tableCheck").click(function(){
			
		var rowIndex = $(this).parent().parent().children().index($(this).parent());//클릭한 row
		var checkMonth = 'checkMonth'+rowIndex;										//input name+row
		var cehckLength = $('input:checkbox[name='+checkMonth+']:checked').length;	//클릭한 row의 월 체크 개수
		var check_sycle = 'check_sycle'+rowIndex; 									//클릭한 row의 점검주기 클래스
		$('.'+check_sycle).text(cehckLength+'회/년');									//클릭한 row의 월 체크 개수 n회/년 적용
		
	});

	
   	//-----------------------------
   	//파일이름을 누르면 호출되는 팝업창 입니다.
   	//-----------------------------
	$(".filePopup").click(function(){
		
		alert("파일관련 팝업창 호출!"); 
		
	});
   	
   	
	//------------------------------
	//테이블을 저장하는 로직입니다.
	//------------------------------
	$("#tableSave").click(function(){
		
		//-------------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-------------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");	//모든 체크박스	
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합
		var division = $("#selectCode").val();			//업무구분
		
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

  			var ul = checkbox.parent().parent().eq(i);						// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   		var li = ul.children();											// checkbox.parent() : checkbox의 부모는 <li>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var schedule_seq = li.eq(0).children().val();					//연간스케쥴 시퀀스
			var work_info = li.eq(1).text();								//업무내용
			var check_cycle = li.eq(2).text();								//점검주기
			var schedule_jan = li.eq(3).children().is(":checked")? 1 : 0;	//1월
			var schedule_feb = li.eq(4).children().is(":checked")? 1 : 0;	//2월
			var schedule_mar = li.eq(5).children().is(":checked")? 1 : 0; 	//3월
			var file_name = li.eq(6).text();								//파일이름

			if(schedule_seq == 'on'){schedule_seq = 0;};//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();				//JsonObject를 위한 객체생성
			jsonObj.schedule_seq = schedule_seq;	//고유번호
			jsonObj.division	= division			//업무구분
			jsonObj.work_info	= work_info;		//업무내용 
			jsonObj.check_cycle = check_cycle;		//점검주기
			jsonObj.schedule_jan= schedule_jan;		//1월
			jsonObj.schedule_feb= schedule_feb;		//2월
			jsonObj.schedule_mar= schedule_mar;		//3월
			jsonObj.file_name	= file_name;		//파일이름

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertSchedule";			//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(){//통신 성공
			alert("저장성공!");
		    var division = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
		    $("#division").val(division);							//업무구분
		    $("#addList").val("normal");							//행추가 변수 값 add
			$("#selectForm").submit();								//서브밋
			
		}).fail(function(){//통신 실패
			alert("저장실패");
		});//ajax		
		
	})
	
   	
   	//----------------------------
   	//수정 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableUp").click(function(){
   		
		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//------------------------
   		//수정할 데이터가 없으면 바로 종료
   		//------------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("수정할 데이터를 선택해 주세요.");
   			return;
   			
   		};//if
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스

   			//-------------------------
   			//체크박스 반복문입니다.
   			//-------------------------
   			if("수정" == $("#tableUp").text()){
   				
   				checkbox.each(function(i) {

   	   				var ul = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
   	   				var li = ul.children();								// checkbox.parent() : checkbox의 부모는 <li>이다. 	   				
   	   				var work_info_val = li.eq(1).text();				//업무내용
   	   				
   	   				li.eq(1).html('<input id="workInfo" type="text" value="'+work_info_val+'">');//수정 가능한 필드로 변경

   	   			});
   				
   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화 -수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화 -수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 -수정 중일 때 
   				
   			}else{
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   				
   				if(result){
   				
   					var seq = 0;//시퀀스 - 0:신규
   					
	   				checkbox.each(function(i) {
	
	   	   				var ul = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   	   				var li = ul.children();								// checkbox.parent() : checkbox의 부모는 <li>이다.
	   	   				seq = li.eq(0).children().val();					//시퀀스 - 0:신규	   	   				
	   	   				var work_info_val = li.eq(1).children().val();		//업무내용
	   	   				
	   	   				li.eq(1).html('<label>'+work_info_val+'</label>');	//수정 완료되면 다시 label로 변경
	
	   	   			});
	   				
					//------------------------
					//행추가 중이면 행추가 버튼 비활성화
					//------------------------
					if(0 != seq){
						$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
					};//if
					
	   				$("#tableUp").text("수정");					//수정 버튼의 글자를 수정 완료로 변경
	   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화 -수정 중일 때 
	   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 -수정 중일 때 
	   				
	   				alert("저장 버튼을 눌러주세요.");	//수정 완료
	   				
   				}else{return;};//수정 완료 버튼을 누르지 않으면 리턴
   				
   			};//else
   			
   	});

	
	//----------------------------
   	//삭제 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableDel").click(function(){
   		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//------------------------
   		//삭제할 데이터가 없으면 바로 종료
   		//------------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("삭제할 데이터를 선택해 주세요.");
   			return;
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var jsonArr = new Array();							//JsonArray를 위한 배열생성
        var totalJson = new Object();						//JsonObject의 합	
       	var seq = -1;										//시퀀스 - -1:초기 값
       	
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <li>이다.
			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.
			var ul = checkbox.parent().parent().eq(i);
			var li = ul.children();
			
			// li.eq(0)은 체크박스 이므로  li.eq(1)의 값부터 가져온다.
			//-------------------------------
			//삭제 시 신규로 추가된 행을 삭제하면 담는다.
			//한 번 담으면 나머지는 패스
			//-------------------------------
			if(seq != -1){
				seq	= li.eq(0).children().val();			//시퀀스 - 0:신규	
			};//if
			
			var schedule_seq = li.eq(0).children().val();	//연간스케쥴 시퀀스
			if(schedule_seq == 'on'){schedule_seq = 0;};	//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.schedule_seq	= schedule_seq;			//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteSchedule";					//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,data: {
				totalJson:stringJson
			}
		}).done(function(response){//통신 성공
		
			//---------------------------
			//행추가를 삭제했으면 행추가 버튼 활성화
			//---------------------------
			if(0 == seq){
				$("#tableAdd").attr("disabled", false);	//행추가 버튼 활성화 
			};//if
			
			var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
			checkbox.parent().parent().remove();
			alert("삭제성공!");

			return;//삭제 성공
			
		}).fail(function(data){//통신 실패
			alert("삭제실패");
			return;
		});//fail
		
   	});
   	
	
   	//----------------------------
   	//행추가 기능입니다.
   	//----------------------------
   	$("#tableAdd").click(function(){
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 10){
			alert("10개 이상 생성 불가.");   			
   			return; 
   		};
   		
   		var selectcDivision = $("#selectCode").val();						//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#division").val(selectcDivision);								//업무구분
	    $("#addList").val("add");											//행추가 변수 값 add
		$("#selectForm").submit();											//서브밋
		$("#addList").val("");												//행추가 변수 공백
		
		
//    		var insertUl = "";//ul에 추가할 데이터를 담을 변수입니다.
//    		insertUl += "<ul class='row'>";
//    		insertUl += "<li class='col-xs-1 col-md-1 tableCount'><input type='checkbox' name='table_check'></li>";
//    		insertUl += "<li class='col-xs-3 col-md-3'><lable class=''>신규업무입니다람쥐</label></li>";
//    		insertUl += "<li class='col-xs-2 col-md-2'><label class=''>0회/년</label></li>";
//    		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_jan' name='' type='checkbox' value=''></li>";
//    		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_feb' name='' type='checkbox' value=''></li>";
//    		insertUl += "<li class='col-xs-1 col-md-1 tableCheck '><input id='schedule_mar' name='' type='checkbox' value=''></li>";
//    		insertUl += "<li class='col-xs-2 col-md-2'><label class='filePopup'>파일이름</label></li>";
//    		insertUl += "</ul>"
//    		$("#formArray").append(insertUl);
   		
   	});
	
});