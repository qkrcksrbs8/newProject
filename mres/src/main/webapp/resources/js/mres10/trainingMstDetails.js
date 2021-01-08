$(function() {

	//------------------
	//기준년도 조회
	//------------------
	$('#selectCalDate').change(function() {
		
		var trainingDate = $(this).val();		//셀렉트박스  2020, 2019, 2018 ...
		$("#trainingDate").val(trainingDate);	//업무구분
	    $("#addList").val("");					//행추가 변수 값 초기화
		$("#selectForm").submit();				//서브밋
		
	});
	
	
   	//-----------------------------
   	//파일이름을 누르면 호출되는 팝업창 입니다.
   	//-----------------------------
	$(".filePopup").click(function(){
		
		alert("파일관련 팝업창 호출!"); 
		
	});
   	
	//---------------------
	//테이블을 저장하는 로직입니다.
	//---------------------
	$("#tableSave").click(function(){
		
		//-----------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-----------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");	//모든 체크박스		
		
		//저장할 데이터가 없으면 리턴
		if(checkbox.length == 0){
			alert("저장할 데이터가 없습니다.");
			return;
		}//if
		
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합
		var work_date = $("#selectCalDate").val();		//업무구분
		
		//-----------------
		//체크박스 반복문입니다.
		//-----------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   		var td = tr.children();						// checkbox.parent() : checkbox의 부모는 <td>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var training_seq 		= td.eq(0).children().val();	//교육현황 시퀀스
			var division 			= td.eq(1).children().val();	//구분
			var training_date 		= td.eq(2).children().children().children().children().val();	//일자
			var training_progress	= td.eq(3).children().val();	//교육진행
			var attend_count 		= td.eq(4).children().val();	//참석인원
			var training_content 	= td.eq(5).children().val();	//교육내용

			if(training_seq == 'on'){training_seq = 0;};			//교육현황 실적 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();							//JsonObject를 위한 객체생성
			jsonObj.training_seq		= training_seq;			//고유번호
			jsonObj.division			= division				//부문
			jsonObj.training_date		= training_date;		//예정업무 
			jsonObj.training_progress 	= training_progress;	//실시업무
			jsonObj.attend_count		= attend_count;			//비고
			jsonObj.training_content 	= training_content		//기준년도

			jsonArr[i] = jsonObj;								//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 		//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertTraining";					//url 테이블 데이터 저장
		var trainingDate = $("#selectCalDate").val();	//셀렉트박스 2020 / 2019 / 2018
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
				,base_date:trainingDate
			}
		}).done(function(data){//통신 성공

			if("0000" == data.resultCode){
				
				alert("저장에 성공했습니다.");
			    var training_date = $("#selectCalDate").val();	//셀렉트박스  2020, 2019, 2018 ...
			    $("#training_date").val(training_date);			//업무구분
			    $("#addList").val("normal");					//행추가 변수 값 add
				$("#selectForm").submit();						//서브밋
				
			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}//if -else if - else
			
		}).fail(function(data){//통신 실패
			alert("네트워크가 원활하지 않습니다. 잠시 후 시도해주세요.");
		})
		
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
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스

   			//-------------------------
   			//체크박스 반복문입니다.
   			//-------------------------
   			if("수정" == $("#tableUp").text()){
   				
   				checkbox.each(function(i) {

   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <td>이다. 	   				
   	   				
   	   				td.eq(0).attr("disabled", true);
					td.eq(1).children().removeAttr('readonly');			//구분	수정 가능한 필드로 변경
	   				td.eq(2).children().children().children().children().removeAttr('readonly');			//일자	수정 가능한 필드로 변경
	   				td.eq(3).children().removeAttr('readonly');			//교육진행	수정 가능한 필드로 변경
	   				td.eq(4).children().removeAttr('readonly');			//참석인원	수정 가능한 필드로 변경
	   				td.eq(5).children().removeAttr('readonly');			//교육내용	수정 가능한 필드로 변경

   	   			});

   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
   				
   			}else{
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   							
   				if(result){
   				
   					var seq = 0;//시퀀스 - 0:신규
					var resultCnt = 0;	//공백 체크여부			
					var resultMsg = '';	//공백이면 메시지 리턴
   					
					//----------------------
					//li.eq(0)은 체크박스입니다.
					//----------------------
   					checkbox.each(function(i) {
	
	   	   				var tr			= checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   	   				var td			= tr.children();					// checkbox.parent() : checkbox의 부모는 <td>이다.
	   	   				seq				= td.eq(0).children().val();		//시퀀스 - 0:신규
	   	   				division		= td.eq(1).children().val();
						training_progress	= td.eq(3).children().val();
						attend_count	= td.eq(4).children().val();
						training_content= td.eq(5).children().val();
						
						if(''==division || null == division ){
							resultMsg = checkMsg(resultMsg, '구분');
							resultCnt++;
						} 
						if(''==training_progress || null == training_progress ){
							resultMsg = checkMsg(resultMsg, '교육진행');
							resultCnt++;
						}
						if(''==attend_count || null == attend_count ){
							resultMsg = checkMsg(resultMsg, '참석인원');
							resultCnt++;
						}
						if(''==training_content || null == training_content ){
							resultMsg = checkMsg(resultMsg, '교육내용');
							resultCnt++;
						}
						if(resultCnt > 0){
							alert(resultMsg+'을(를) 입력해 주세요.');
							return;
						}

	   	   				td.eq(1).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(2).children().children().children().children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(3).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(4).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(5).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	
	   	   			});
	   				
   					//------------------------
   					//행추가 중이면 행추가 버튼 비활성화
   					//------------------------
   					if(0 != seq){
   						$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
   					};//if
   					
	   				if(resultCnt == 0){ 
		
		   				$("#tableUp").text("수정");					//수정 완료 버튼의 글자를 수정으로 변경
		   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화  	 수정 중일 때 
		   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 	 수정 중일 때 
						alert("저장 버튼을 눌러주세요.");	//수정 완료	
						
					}//if
					
   				}else{return;};									//수정 완료 버튼을 누르지 않으면 리턴
   				//else
   				
   			};//else
   			
   	});
	
	
	
   	//------------------------
   	//삭제 기능을 정의한 메서드입니다.
   	//------------------------
   	$("#tableDel").click(function(){
   		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크
   		
   		//-----------------------
   		//삭제할 데이터가 없으면 바로 종료
   		//-----------------------
   		if(checkboxCnt < 1 ){
   			 
   			alert("삭제할 데이터를 선택해 주세요.");
   			return;
   			
   		};
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var jsonArr = new Array();							//JsonArray를 위한 배열생성
        var totalJson = new Object();						//JsonObject의 합
		
        var seq = -1;										//시퀀스 - -1:초기 값
		//-----------------
		//체크박스 반복문입니다.
		//-----------------
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			// li.eq(0)은 체크박스 이므로  li.eq(1)의 값부터 가져온다.
			
			//-------------------------------
			//삭제 시 신규로 추가된 행을 삭제하면 담는다.
			//한 번 담으면 나머지는 패스
			//-------------------------------
			if(seq != -1){
				seq	= td.eq(0).children().val();			//시퀀스 - 0:신규	
			};//if
			
			var training_seq = td.eq(0).children().val();	//교육현황 시퀀스
			if(training_seq == 'on'){training_seq = 0;};	//교육현황 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.training_seq	= training_seq;			//교육현황번호 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteTraining";					//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공
			
			if("0000" == data.resultCode){
				
				//--------------------------
				//행추가를 삭제하면 행추가 버튼 활성화
				//--------------------------
				if(0 == seq){
					$("#tableAdd").attr("disabled", false);	//행추가 버튼 활성화 
				};//if
				
				var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
				checkbox.parent().parent().remove();
				alert("삭제성공!");

			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}//if -else if - else
			
		}).fail(function(data){//통신 실패
			alert("네트워크가 원활하지 않습니다. 잠시 후 시도해주세요.");
			return;
		})
		
   	});
   	
	
   	
   	//--------------
   	//행추가 기능입니다.
   	//--------------
   	$("#tableAdd").click(function(){
   		
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 10){
			alert("10개 이상 생성 불가.");   			
   			return; 
   		};
   		
	    var trainingDate = $("#selectCalDate").val();	//셀렉트박스 2020 / 2019 / 2018
	    $("#trainingDate").val(trainingDate);			//업무구분
	    $("#addList").val("add");						//행추가 변수 값 add
		$("#selectForm").submit();						//서브밋
		$("#addList").val("");							//행추가 변수 공백

   	});

	//저장 시 공백 문자열체크
	//항목1, 항목2, 항목3  처럼 , 구분자 생성하는 메서드
	function checkMsg(oldStr, newStr){ 
		
		if(''==oldStr || null == oldStr){
			return newStr;
		}else{
			return oldStr+', '+newStr;
		}//if - else
		
	}//checkMsg
	
	
	//생성된 태그에 동적 이벤트 추가
	$(document).on("keyup","#attend_count",function(){ 
	   	
		var x = $(this).val();						//현재 입력된 값
	  	x = x.replace(/[^0-9]/g,'');   				// 입력값이 숫자가 아니면 공백
	 	x = x.replace(/,/g,'');          			// ,값 공백처리
		x = x.replace(/(^0+)/, "");					//첫 자리 0 제거
	  	$(this).val(x); 							//현재 필드에 파싱된 값 리턴

	});
   	
});