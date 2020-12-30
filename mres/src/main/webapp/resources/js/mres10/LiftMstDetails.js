$(function() {
	
	//------------------
	//기준년도 조회
	//------------------
	$('#selectCalDate').change(function() {
		
		var selectCalDate = $(this).val();				//기준년도
		$("#selectDate").val(selectCalDate);			//업무구분
		$("#selectForm").submit();						//서브밋 
		
	}); 
	
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
		 
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합

		//-----------------
		//체크박스 반복문입니다. 
		//-----------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   		var td = tr.children();						// checkbox.parent() : checkbox의 부모는 <td>이다.

			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var payment_status_seq 	= td.eq(0).children().val();	//설비 및 수불 현황 시퀀스
			var product_name 		= td.eq(1).children().val();	//상품명
			var product_unit 		= td.eq(2).children().val();	//단위
			var carried_forward		= td.eq(3).children().val();	//이월재고
			var enter				= td.eq(4).children().val();	//입고
			var exodus				= td.eq(5).children().val();	//출고
			var this_month			= td.eq(6).children().val();	//금월재고
			var remark				= td.eq(7).children().val();	//비고

			if(payment_status_seq == 'on'){payment_status_seq = 0;};//시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();							//JsonObject를 위한 객체생성
			jsonObj.payment_status_seq = payment_status_seq;	//고유번호
			jsonObj.product_name	= product_name				//상품명
			jsonObj.product_unit	= product_unit;				//단위 
			jsonObj.carried_forward = carried_forward;			//이월재고
			jsonObj.enter			= enter;					//입고
			jsonObj.exodus			= exodus;					//출고
			jsonObj.this_month 		= this_month;				//금월재고
			jsonObj.remark			= remark;					//비고

			jsonArr[i] = jsonObj;								//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 				//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertPaymentStatus";						//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공

			//0000:정상 | 8000:디비오류 | 9000:비정상
			if("0000" == data.resultCode){				//0000:정상
				
				alert("저장이 완료되었습니다.");
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
	
   	
   	//----------------------------
   	//수정 기능을 정의한 메서드입니다.
   	//----------------------------
   	$("#tableUp").click(function(){
   		
		var checkboxCnt = $("input[name=table_check]:checked").length;	//체크박스 개수 체크		
		
   		if(checkboxCnt < 1 ){alert("수정할 데이터를 선택해 주세요.");return;	};	//수정할 데이터가 없으면 바로 종료	 
				
   		var checkbox = $("input[name=table_check]:checked");			//체크된 체크박스

		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		if("수정" == $("#tableUp").text()){
			
			checkbox.each(function(i) {

   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다. 	   		
   				var product_name 	= td.eq(1).children().val();	//상품명
   				var product_unit 	= td.eq(2).children().val();	//단위
   				var carried_forward	= td.eq(3).children().val();	//이월재고
   				var enter			= td.eq(4).children().val();	//입고
   				var exodus			= td.eq(5).children().val();	//출고
   				var this_month		= td.eq(6).children().val();	//금월재고
				var remark			= td.eq(7).children().val();	//비고
   				
   				td.eq(0).attr("disabled", true);
				td.eq(1).children().removeAttr('readonly');			//단위	수정 가능한 필드로 변경
   				td.eq(2).children().removeAttr('readonly');			//단위	수정 가능한 필드로 변경
   				td.eq(3).children().removeAttr('readonly');			//이월재고	수정 가능한 필드로 변경
   				td.eq(4).children().removeAttr('readonly');			//입고	수정 가능한 필드로 변경
   				td.eq(5).children().removeAttr('readonly');			//출고	수정 가능한 필드로 변경
   				td.eq(7).children().removeAttr('readonly');			//비고 	수정 가능한 필드로 변경
   				
   			});//checkbox.each

			
			$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
			$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
			$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
			$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
			
		} else {
			
			var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
						 
			if(result){//수정 완료
			
				var seq = 0;//시퀀스 - 0:신규
				
				//----------------------
				//td.eq(0)은 체크박스입니다.
				//----------------------
				checkbox.each(function(i) {

   	   				var tr				= checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
   	   				var td				= tr.children();					// checkbox.parent() : checkbox의 부모는 <td>이다.
   	   				seq					= td.eq(0).children().val();		//시퀀스 - 0:신규
   	   				var product_name 	= td.eq(1).children().val();		//상품명
   	   				var product_unit 	= td.eq(2).children().val();		//단위
   	   				var carried_forward	= td.eq(3).children().val();		//이월재고
   	   				var enter			= td.eq(4).children().val();		//입고
   	   				var exodus			= td.eq(5).children().val();		//출고
   	   				var this_month		= td.eq(6).children().val();		//금월재고
					var remark			= td.eq(7).children().val();		//비고
   	   				
   	   				td.eq(1).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
   	   				td.eq(2).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
   	   				td.eq(3).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
   	   				td.eq(4).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
   	   				td.eq(5).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
   	   				td.eq(7).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용

   	   			});//checkbox.each
   				
				//------------------------
				//행추가 중이면 행추가 버튼 비활성화
				//------------------------
				if(0 != seq){
					$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
				};//if
				
   				$("#tableUp").text("수정");					//수정 완료 버튼의 글자를 수정으로 변경
   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화  	 수정 중일 때 
   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 	 수정 중일 때 
   				
   				alert("저장 버튼을 눌러주세요.");					//수정 완료
   				
			} else {return;};								//수정 완료 버튼을 누르지 않으면 리턴
			
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
				seq	= td.eq(0).children().val();				//시퀀스 - 0:신규	
			};//if
			
			var payment_status_seq = td.eq(0).children().val();	//하자보수 시퀀스
			if(payment_status_seq == 'on'){payment_status_seq = 0;};//하자보수 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();							//JsonObject를 위한 객체생성
			jsonObj.payment_status_seq	= payment_status_seq;				//업무내용 
			jsonArr[i] = jsonObj;								//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 				//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deletePaymentStatus";						//url 테이블 데이터 삭제
		
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
				
				var checkbox = $("input[name=table_check]:checked");//체크된 체크박스;
				checkbox.parent().parent().next().remove();			//테이블 rowspan으로 분할되어 있으므로, 다음행(이미지)삭제
				checkbox.parent().parent().remove()					//현재 row 삭제
				alert("삭제성공!");

			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}
			
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
	$(document).on("keyup","#exodus, #enter",function(){ 
	   	
		var x = $(this).val();						//현재 입력된 값
	  	x = x.replace(/[^0-9]/g,'');   				// 입력값이 숫자가 아니면 공백
	 	x = x.replace(/,/g,'');          			// ,값 공백처리
		x = x.replace(/(^0+)/, "");					//첫 자리 0 제거
		x = x.replace(/\B(?=(\d{3})+(?!\d))/g, ",")	//금액 표기
	  	$(this).val(x); 							//현재 필드에 파싱된 값 리턴

		var tr = $(this).parent().parent();			//현재 input 부모의 부모는 현재 tr이다 
		var td = tr.children();						//현재 tr의 자식은 td이다
		var carried_forward = td.eq(3).children().val().replace(/,/g,'');//현재 row의 이월재고
		var enter  = td.eq(4).children().val().replace(/,/g,'');		//현재 row의 입고
		var exodus = td.eq(5).children().val().replace(/,/g,'');		//현재 row의 출고
		var this_month = parseInt(carried_forward + enter - exodus);	//이월재고 + 입고 - 출고
		
		//----------------------------
		//1. 재고보다 출고가 많다면 출고 불가
		//2. '재고가 부족합니다.' 메시지 노출
		//3. 입력 중인 재고 컬럼으로 포커스
		//4. 출고를 제외한 합계를 금월재고에 셋팅
		//----------------------------
		if(this_month <= -1){
			td.eq(5).children().val(0);			//현재 row의 출고
			alert("재고가 부족합니다.");
			td.eq(5).children().focus();		//현재 row의 출고 포커스
			this_month = String(parseInt(carried_forward + enter)).replace(/\B(?=(\d{3})+(?!\d))/g, ","); //(이월재고 + 재고)+금액표기 
			td.eq(6).children().val(this_month);	//출고 값을 제외한 합계
			return;
		}//if
			
		this_month = String(this_month).replace(/\B(?=(\d{3})+(?!\d))/g, ",");//금액 표기 
		td.eq(6).children().val(this_month);	//금월재고 = 이월재고 + 입고 - 출고 
			
	 });//동적 이벤트 부여

});