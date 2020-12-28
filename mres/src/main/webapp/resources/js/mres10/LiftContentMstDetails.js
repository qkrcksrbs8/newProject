$(function() {
	
	//---------------------
	//테이블을 저장하는 로직입니다.
	//---------------------
	$("#tableSave").click(function(){
		
		//-----------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-----------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");		//모든 체크박스		
		
		//저장할 데이터가 없으면 리턴
		if(checkbox.length == 0){
			alert("저장할 데이터가 없습니다.");
			return;
		}//if
		
		var resultCnt = 0;	//공백 체크여부			
		var resultMsg = '';	//공백이면 메시지 리턴
		
		var inspection_division = $("#inspection_division").val(); //1:승강기 2:화재예방
		var lift_seq = $("#lift_seq").val();					//시퀀스 
		var inspection_field = $("#inspection_field").val();	//점검분야
		var inspection_company = $("#inspection_company").val();//점검업체
		var inspection_date = $("#inspection_date").val();		//점검일자
		var building_name = $("#building_name").val();			//건물명
		var main_manager = $("#main_manager").val();			//점검업체
		var sub_manager = $("#sub_manager").val();				//점검업체
		
		var datatimeRegexp = /[0-9]{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])/;

	    if ( !datatimeRegexp.test(inspection_date) ) {
	        alert("날짜는 yyyy-mm-dd 형식으로 입력해주세요.");
	        return false;
	    }
		
		if(''==inspection_field || null == inspection_field ){
			resultMsg = checkMsg(resultMsg, '점검분야');
			resultCnt++;
		}
		if(''==inspection_company || null == inspection_company ){
			resultMsg = checkMsg(resultMsg, '점검업체');
			resultCnt++;
		}
		if(''==building_name || null == building_name ){
			resultMsg = checkMsg(resultMsg, '건물명');
			resultCnt++;
		}
		if(''==main_manager || null == main_manager ){
			resultMsg = checkMsg(resultMsg, '확인자');
			resultCnt++;
		}
		if(resultCnt > 0){
			alert(resultMsg+'을(를) 입력해 주세요.');
			return;
		}
		
		var jsonArr = new Array();							//JsonArray를 위한 배열생성
        var totalJson = new Object();						//JsonObject의 합
		
		
		//-----------------
		//체크박스 반복문입니다. 
		//-----------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   		var td = tr.children();						// checkbox.parent() : checkbox의 부모는 <td>이다.

			var result_check_name = 'result_check'+i;    
			var result_check = $('input[name="'+result_check_name+'"]:checked').val();
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var lift_content_seq 	= td.eq(0).children().val();	//시퀀스
//			var division 			= td.eq(1).children().val();	//구분
//			var item_check 			= td.eq(2).children().val();	//점검사항
			var division 			= td.eq(1).text();	//구분
			var item_check 			= td.eq(2).text();	//점검사항
			var fr_work				= td.eq(6).children().val();	//현지시정
			var to_work				= td.eq(7).children().val();	//조치계획
			var schedule_date		= td.eq(8).children().children().children().children().val();//예정일
			 
			if(lift_content_seq == 'on'){lift_content_seq = 0;};//시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.lift_seq = lift_seq;					//고유번호
			jsonObj.lift_content_seq = lift_content_seq;	//고유번호
			jsonObj.division		= division				//구분
			jsonObj.item_check		= item_check;			//주요점검사항 
			jsonObj.fr_work			= fr_work;				//현지시정
			jsonObj.to_work			= to_work;				//조치계획
			jsonObj.schedule_date 	= schedule_date;		//예정일
			jsonObj.result_check	= result_check;			//체크박스
			jsonArr[i] = jsonObj;							//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertLift";							//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
				,lift_seq:lift_seq
				,inspection_field:inspection_field
				,inspection_company:inspection_company
				,inspection_date:inspection_date
				,building_name:building_name
				,main_manager:main_manager
				,sub_manager:sub_manager
				,inspection_division:inspection_division
			}
		}).done(function(data){//통신 성공

			//0000:정상 | 8000:디비오류 | 9000:비정상
			if("0000" == data.resultCode){				//0000:정상
				
				alert("저장이 완료되었습니다.");
				$("#lift_seq").val(data.lift_seq);		//승강기 번호 셋팅 
			    $("#addList").val("normal");			//행추가 변수 값 add
				$("#selectForm").submit();				//서브밋
				
			} else if ("8000" == data.resultCode){		//8000:디비오류
				
				alert("저장에 실패했습니다. 저장 값 오류");
				
			} else if ("9000" == data.resultCode){		//9000:비정상
				
				alert("저장에 실패했습니다. 관리자에게 문의 주세요.");
				
			} else{										//설정된 코드를 받지 못할 시 "알 수 없는 에러"
				
				alert("알 수 없는 에러. 관리자에게 문의 주세요.");	
			}
			
		}).fail(function(data){							//통신 실패(서버 통신 전에 오류 발생)
			alert("네트워크가 원활하지 않습니다. 잠시 후 시도해주세요.");
		})
		
	})
	
   
   	//--------------
   	//행추가 기능입니다.
   	//--------------
   	$("#tableAdd").click(function(){
   		
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 30){
			alert("30개 이상 생성 불가.");   			
   			return; 
   		};

	    $("#addList").val("add");								//행추가 변수 값 add
		$("#selectForm").submit();								//서브밋
		$("#addList").val("");									//행추가 변수 공백

   	});

	//승강기 목록 보러가는 버튼(뒤로가기 느낌 하지만 뒤는 아님)
	$("#liftPage").click(function(){
		var inspection_division = $("#inspection_division").val();
		location.href='./liftList?inspection_division='+inspection_division; 
	});

	$("#inspection_date").keyup(function(){
		var thisVal = $(this).val();
		
		if(thisVal.length > 10){
			$(this).val(thisVal.substring(0,10));
		}
		
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
	
});