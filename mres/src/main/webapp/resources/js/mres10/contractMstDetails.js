$(function() {

	//------------------
	//날짜조회
	//1. 시작일, 종료일 값이 둘 다 정해지지 않으면 이벤트 종료
	//2. 시작일이 종료일보다 크면 이벤트 종료
	//3. 계약년수 컬럼에 년, 월 비례해서 글자 생성 - ex. 1년 2개월
	//------------------
	$('.select_cal').change(function() {
		
		var rowIndex = $(this).parent().parent().children().index($(this).parent());		//클릭한 row
		var rowData = $(this).parent().parent().parent().parent().parent().eq(rowIndex);	//클릭한 row data
	   	var td		= rowData.children();													//클릭한 row data의 자식은 <td>이다.
		var fr_day	= td.eq(3).children().children().children().children().val();			//시작일 2020-09-02
		var to_day	= td.eq(4).children().children().children().children().val();			//종료일 2020-09-02	
		var fr_cal = onlyInt(fr_day);														//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
		var to_cal = onlyInt(to_day);														//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
	
		//달력이 "yyyymmdd" 포맷으로 넘어오지 않으면 검색 중단
		if(to_cal.length != 8 || fr_cal.length != 8){return;}//if
			
		fr_cal = parseInt(fr_cal);	//숫자로 변환
		to_cal = parseInt(to_cal);	//숫자로 변환
	
		//시작일이 종료일보다 클 수 없습니다. 저장 불가능
		if(to_cal < fr_cal){
			alert("시작일이 종료일보다 클 수 없습니다.");
			td.eq(4).children().children().children().children().val(fr_day);
			td.eq(5).children().val('0개월');					//계약연수
			$("#fr_cal").focus();	//시작일 입력칸으로 포커스 이동
			return;
		}//if
		
		var frArr 			= fr_day.split('-');						//하이푼을 구분자로 배열에 담기  2020-09-02  -> {2000, 09, 02}
	    var toArr 			= to_day.split('-');						//하이푼을 구분자로 배열에 담기  2020-09-02  -> {2000, 09, 02}
		var yearToMonth		= parseInt((toArr[0]-frArr[0])*12)			//1년을 12개월로 변환
		var yearAddMonth	= parseInt((toArr[1]-frArr[1])+yearToMonth)	//변환된 년 + 월
		var resultYear 		= '';										//년 결과
		var resultMonth 	= '';										//월 결과
		resultYear = Math.floor(yearAddMonth / 12);						//년 결과
		
		//기간이 1년 이상이라면
		if(resultYear > 0){
			resultMonth = yearAddMonth - (resultYear * 12);				//월 결과
			td.eq(5).children().val(resultYear+'년 '+resultMonth+'개월');	//계약연수
			
		} else {
			resultMonth = yearAddMonth;									//월 결과
			td.eq(5).children().val(resultMonth+'개월');					//계약연수
		}//if - else
			
	});
	
	
	//이 함수에 값을 넣으면 숫자만 반환합니다.
	function onlyInt(str){
	    var res;							//반환할 변수
	    res = str.replace(/[^0-9]/g,"");	//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
	    return res;							//숫자 반환
	}//onlyInt
	
	
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
		
		//-----------------
		//체크박스 반복문입니다.
		//-----------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   		var td = tr.children();							// checkbox.parent() : checkbox의 부모는 <td>이다.
			
	   		contract_details
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var contract_seq 		= td.eq(0).children().val();	//시퀀스
			var contract_details	= td.eq(1).children().val()				//계약내용
			var contract_company	= td.eq(2).children().val()				//업체명
			var fr_day		 		= td.eq(3).children().children().children().children().val();//시작일
			var to_day	 			= td.eq(4).children().children().children().children().val();//종료일
			var contract_years 		= td.eq(5).children().val()				//계약연수+계약구분
			var payment 			= td.eq(6).children().val()				//계약금액
			var remark 				= td.eq(7).children().val()				//비고

			if(contract_seq == 'on'){contract_seq = 0;};			//주요계약현황 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();								//JsonObject를 위한 객체생성
			jsonObj.contract_seq 		= contract_seq;				//고유번호
			jsonObj.contract_details	= contract_details			//계약내용
			jsonObj.contract_company	= contract_company;			//업체명 
			jsonObj.fr_day 				= fr_day;					//시작일
			jsonObj.to_day 				= to_day;					//종료일
			jsonObj.contract_years		= contract_years;			//계약연수
			jsonObj.payment 			= payment.replace(',','')	//계약금액
			jsonObj.remark 				= remark					//비고

			jsonArr[i] = jsonObj;									//Array 배열 push

		});//checkbox.each()
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertContract";				//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공

			if("0000" == data.resultCode){
				
				alert("저장에 성공했습니다.");
			    $("#addList").val("normal");			//행추가 변수 값 add
				$("#selectForm").submit();				//서브밋
				
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
   		
		var checkboxCnt = $("input[name=table_check]:checked").length;//체크박스 개수 체크
   		
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

   	   				var tr = checkbox.parent().parent().eq(i);				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
   	   				var td = tr.children();									// checkbox.parent() : checkbox의 부모는 <td>이다. 	   		
   	   				
   	   				td.eq(0).attr("disabled", true);
   	   				td.eq(1).children().removeAttr('readonly');				//부문	수정 가능한 필드로 변경
   	   				td.eq(2).children().removeAttr('readonly');				//예정업무	수정 가능한 필드로 변경
					td.eq(3).children().children().children().children().removeAttr('readonly');			//시작일
   	   				td.eq(4).children().children().children().children().removeAttr('readonly');			//종료일
   	   				td.eq(6).children().removeAttr('readonly');				//게약금액	수정 가능한 필드로 변경
   	   				td.eq(7).children().removeAttr('readonly');				//비고	수정 가능한 필드로 변경

   	   			});//checkbox.each()

   				$("#tableUp").text("수정 완료");								//수정 버튼의 글자를	 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);						//저장 버튼 비활성화	 수정 중일 때 
   				$("#tableDel").attr("disabled", true);						//삭제 버튼 비활성화	 수정 중일 때 
   				$("#tableAdd").attr("disabled", true);						//행추가 버튼 비활성화 수정 중일 때 
   				
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
	
	   	   				var tr	= checkbox.parent().parent().eq(i);						// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   	   				var td	= tr.children();										// checkbox.parent() : checkbox의 부모는 <td>이다.
	   	   				seq		= td.eq(0).children().val();							//시퀀스 - 0:신규
	   	   				
						contract_details	= td.eq(1).children().val();
						contract_company	= td.eq(2).children().val();
						
						if(''==contract_details || null == contract_details ){
								resultMsg = checkMsg(resultMsg, '계약내용');
								resultCnt++;
							}
						if(''==contract_company || null == contract_company ){
							resultMsg = checkMsg(resultMsg, '업체명');
							resultCnt++;
						}
						if(resultCnt > 0){
							alert(resultMsg+'을(를) 입력해 주세요.');
							return;
						}

	   	   				td.eq(1).children().attr('readonly','readonly');				//수정 완료되면 다시 label로 변경
	   	   				td.eq(2).children().attr('readonly','readonly');				//수정 완료되면 다시 label로 변경
	   	   				td.eq(3).children().children().children().children().attr('readonly','readonly');//시작일
	   	   				td.eq(4).children().children().children().children().attr('readonly','readonly');//종료일
	   	   				td.eq(5).children().attr('readonly','readonly');				//수정 완료되면 다시 label로 변경
	   	   				td.eq(6).children().attr('readonly','readonly');				//수정 완료되면 다시 label로 변경
	   	   				td.eq(7).children().attr('readonly','readonly');				//수정 완료되면 다시 label로 변경
	
	   	   			});//checkbox.each()
	   				
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
						
					}//if				//수정 완료
	   				
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
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			
			//-------------------------------
			//삭제 시 신규로 추가된 행을 삭제하면 담는다.
			//한 번 담으면 나머지는 패스
			//-------------------------------
			if(seq != -1){
				seq	= td.eq(0).children().val();			//시퀀스 - 0:신규	
			};//if
			
			var contract_seq = td.eq(0).children().val();	//주요계약현황 시퀀스
			if(contract_seq == 'on'){contract_seq = 0;};	//주요계약현황 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.contract_seq = contract_seq;			//시퀀스 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteContract";						//url 테이블 데이터 삭제
		
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
					$("#tableAdd").attr("disabled", false);			//행추가 버튼 활성화 
				};//if
				
				var checkbox = $("input[name=table_check]:checked");//체크된 체크박스
				checkbox.parent().parent().remove();				//삭제
				alert("삭제성공!");
				
			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}//if -else if - else
			return;//테스트 중
			
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
   		if(checkboxCnt >= 15){
			alert("15개 이상 생성 불가.");   			
   			return; 
   		};
   		
	    $("#addList").val("add");								//행추가 변수 값 add
		$("#selectForm").submit();								//서브밋
		$("#addList").val("");									//행추가 변수 공백

   	});

	//생성된 태그에 동적 이벤트 추가
	$(document).on("keyup","#payment_val",function(){ 
	   	
		var x = $(this).val();								//현재 입력된 값
	  	x = x.replace(/[^0-9]/g,'');   						// 입력값이 숫자가 아니면 공백
	 	x = x.replace(/,/g,'');          					// ,값 공백처리
	  	$(this).val(x.replace(/\B(?=(\d{3})+(?!\d))/g, ",")); // 정규식을 이용해서 3자리 마다 , 추가 
			
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