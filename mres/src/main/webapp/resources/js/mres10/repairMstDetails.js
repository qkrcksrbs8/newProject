$(function() {

	//------------------
	//날짜조회
	//------------------
	$('.select_cal').change(function() {
		
		// fr_cal / to_cal = 서브밋 변수 이름
		// frCal / toCal  = 달력 변수 이름
		var fr_cal = $("#frCal").val();//시작일 2020-10-10
		var to_cal = $("#toCal").val();//종료일 2020-10-11
		
		fr_cal = onlyInt(fr_cal);//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
		to_cal = onlyInt(to_cal);//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
	
		//달력이 "yyyymmdd" 포맷으로 넘어오지 않으면 검색 중단
		if(to_cal.length != 8 || fr_cal.length != 8){
			return;
		}//if
			
		fr_cal = parseInt(fr_cal);//숫자로 변환
		to_cal = parseInt(to_cal);//숫자로 변환
	
		//시작일이 종료일보다 클 수 없습니다. 저장 불가능
		if(to_cal < fr_cal){
			alert("시작일이 종료일보다 클 수 없습니다.");
			$("#fr_cal").focus();
			return;
		}//if
		
		//서브밋 할 변수에 값 셋팅
		$("#fr_cal").val(fr_cal);//시작일
		$("#to_cal").val(to_cal);//종료일
		$("#selectForm").submit();//날짜로 조회
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
		var baseYear = $("#selectCode").val();			//업무구분

		//-----------------
		//체크박스 반복문입니다. 
		//-----------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   		var td = tr.children();							// checkbox.parent() : checkbox의 부모는 <td>이다.

			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var repair_seq 	= td.eq(0).children().val();	//세부업무 실적 시퀀스
			var created_date= td.eq(1).children().val();	//부문
			var fr_work		= td.eq(2).children().val();	//예정업무
			var to_work 	= td.eq(3).children().val();	//실시업무
			var remark 		= td.eq(4).children().val();	//remark

			if(repair_seq == 'on'){repair_seq = 0;};		//하자보수현황 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();				//JsonObject를 위한 객체생성
			jsonObj.repair_seq = repair_seq;		//고유번호
			jsonObj.created_date	= created_date	//일자
			jsonObj.fr_work	= fr_work;				//예정업무 
			jsonObj.to_work = to_work;				//실시업무
			jsonObj.remark= remark;					//비고

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertRepair";					//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공

			if("0000" == data.resultCode){				//0000:정상 | 9000:오류
				
				alert("저장이 완료되었습니다.");
			    var baseYear = $("#selectCode").val();	//셀렉트박스  2020, 2019, 2018 ...
			    $("#baseYear").val(baseYear);			//업무구분
			    $("#addList").val("normal");			//행추가 변수 값 add
				$("#selectForm").submit();				//서브밋
				
			} else if ("9000" == data.resultCode){		//0000:정상 | 9000:오류
				
				alert("관리자에게 문의주세요.");
				
			} else{										//설정된 코드를 받지 못할 시 "알 수 없는 에러"
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");	
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
   		
   		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스

   			//-------------------------
   			//체크박스 반복문입니다.
   			//-------------------------
   			if("수정" == $("#tableUp").text()){
   				
   				checkbox.each(function(i) {

   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다. 	 	
	   				td.eq(2).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경
	   				td.eq(3).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경
	   				td.eq(4).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경		
   	   				td.eq(0).attr("disabled", true);

   	   			});//checkbox.each

   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를	 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화	 수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화	 수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 수정 중일 때 
   				
   			} else {
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   							 
   				if(result){//수정 완료
   				
   					var seq = 0;//시퀀스 - 0:신규
					var resultCnt = 0;	//공백 체크여부			
					var resultMsg = '';	//공백이면 메시지 리턴
   					
					//----------------------
					//td.eq(0)은 체크박스입니다.
					//----------------------
   					checkbox.each(function(i) {
	
	   	   				var tr			= checkbox.parent().parent().eq(i);	// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.	
	   	   				var td			= tr.children();					// checkbox.parent() : checkbox의 부모는 <td>이다.
	   	   				seq				= td.eq(0).children().val();		//시퀀스 - 0:신규

						fr_work			= td.eq(2).children().val();
						to_work			= td.eq(3).children().val();
						
						if(''==fr_work || null == fr_work ){
							resultMsg = checkMsg(resultMsg, '예정업무');
							resultCnt++;
						}
						if(''==to_work || null == to_work ){
							resultMsg = checkMsg(resultMsg, '실시업무');
							resultCnt++;
						}
						if(resultCnt > 0){ 
							alert(resultMsg+'을(를) 입력해 주세요.');
							return;
						}

	   	   				td.eq(2).children().attr('readonly','readonly');	//수정 완료되면 readonly 적용
	   	   				td.eq(3).children().attr('readonly','readonly');	//수정 완료되면 readonly 적용 
	   	   				td.eq(4).children().attr('readonly','readonly');	//수정 완료되면 readonly 적용
	
	   	   			});//checkbox.each
	   				
   					//------------------------
   					//행추가 중이면 행추가 버튼 비활성화
   					//------------------------
   					if(0 != seq){
   						$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
   					};//if
   					
	   				if(resultMsg < 0){ 
		
		   				$("#tableUp").text("수정");					//수정 완료 버튼의 글자를 수정으로 변경
		   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화  	 수정 중일 때 
		   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 	 수정 중일 때 
						alert("저장 버튼을 눌러주세요.");	//수정 완료	
						
					}//if
	   				
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

			// checkbox.parent() : checkbox의 부모는 <li>이다.
			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			// td.eq(0)은 체크박스 이므로  li.eq(1)의 값부터 가져온다.
			
			//-------------------------------
			//삭제 시 신규로 추가된 행을 삭제하면 담는다.
			//한 번 담으면 나머지는 패스
			//-------------------------------
			if(seq != -1){
				seq	= td.eq(0).children().val();			//시퀀스 - 0:신규	
			};//if
			
			var repair_seq = td.eq(0).children().val();		//하자보수 시퀀스
			if(repair_seq == 'on'){repair_seq = 0;};		//하자보수 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.repair_seq	= repair_seq;				//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteRepair";							//url 테이블 데이터 삭제
		
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
   		
	    var baseYear = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#baseYear").val(baseYear);							//업무구분
	    $("#addList").val("add");								//행추가 변수 값 add
		$("#selectForm").submit();								//서브밋
		$("#addList").val("");									//행추가 변수 공백

   	});


	popup();
	
	//----------------
	//예정업무 파일첨부 팝업입니다.
	//----------------
	$(".frUploadPopup").click(function(){
		
		var tr = $(this).parent().prev().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();					//클릭한 테이블의 td
		var table_seq	= td.eq(0).children().val();//시퀀스
		var table_name = "frRepairMst";			//테이블 이름
	
		alert(table_seq); 
	
		$("#table_seq").val(table_seq);				//테이블 시퀀스 필드에 값 셋팅
		$("#table_name").val(table_name);			//테이블 이름 필드에 값 셋팅
		
		$("#schedule_reg_popup .popup_title").html("파일 업로드/다운로드");
		$("#fileForm")[0].reset();
		$("#filename").hide();
		$("#schedule_reg_popup").popup('show');
		
	});
	
	//----------------
	//실시업무 파일첨부 팝업입니다.
	//----------------
	$(".toUploadPopup").click(function(){
		
		var tr = $(this).parent().prev().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();					//클릭한 테이블의 td
		var table_seq	= td.eq(0).children().val();//시퀀스
		var table_name = "toRepairMst";			//테이블 이름
	
		$("#table_seq").val(table_seq);				//테이블 시퀀스 필드에 값 셋팅
		$("#table_name").val(table_name);			//테이블 이름 필드에 값 셋팅
		
		$("#schedule_reg_popup .popup_title").html("파일 업로드/다운로드");
		$("#fileForm")[0].reset();
		$("#filename").hide();
		$("#schedule_reg_popup").popup('show');
		
	});
	
	//----------------
	//팝업 닫기 기능 입니다.
	//----------------
	$(".schedule_reg_popup_close").click(function(){
		$("#schedule_reg_popup").popup('hide');//팝업 종료	
	});//
   	
	//파일 업로드
	$("#imgUp").click(function(){
		
		var form = $("#fileForm")[0];
        var formData = new FormData(form);
        formData.append("message", "ajax로 파일 전송하기");
        formData.append("file", $("#fileUpId")[0].files[0]);
		
		$.ajax({ 
              url : "./fileUpload"
            , type : "POST"
            , processData : false
            , contentType : false
            , data : formData
			, dataType : 'json'
            , success:function(data) { 
				
				//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2200:이미지 파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
				if("0000" == data.resultCode){
					alert("업로드 되었습니다.");
			
			   		var selectcDivision = $("#selectCode").val();	//셀렉트박스  날짜
				    $("#division").val(selectcDivision);			//업무구분
				    $("#addList").val("add");						//행추가 변수 값 add
					$("#addList").val("");							//행추가 변수 공백
					$("#selectForm").submit();						//서브밋
//					$("#pdf_reg_popup").popup('hide');
					
				}else if("2000" == data.resultCode){
					alert("파일 업로드 오류. 관리자에게 문의해 주세요.");
				}else if("2200" == data.resultCode){
					alert("이미지 파일만 업로드 가능합니다.");
				}else if("3000" == data.resultCode){
					alert("파일 업로드 오류. 관리자에게 문의해 주세요.");
				}else if("4000" == data.resultCode){
					alert("파일이 전송되지 않았습니다. 다시 시도해 주세요.");
				}else if("9000" == data.resultCode){
					alert("파일 업로드 오류. 관리자에게 문의해 주세요.");
				}
				
            }//success

        });//$ajax
		
	})//imgUp click
		
	
	//이 함수에 값을 넣으면 숫자만 반환합니다.
	function onlyInt(str){
	    var res;							//반환할 변수
	    res = str.replace(/[^0-9]/g,"");	//숫자를 제외한 나머지 공백. ex)2020-10-10 -> 20201010
	    return res;							//숫자 반환
	}//onlyInt

	//특수문자 입력 불가
	$(document).on("keyup",".maxVal",function(){ 
	   	
		var x = $(this).val();						//현재 입력된 값
	  	x = x.replace( /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi,'');   				// 입력값이 숫자가 아니면 공백
	  	$(this).val(x); 							//현재 필드에 파싱된 값 리턴

		maxVal(x, 5);	
	 });//동적 이벤트 부여

	//예정업무, 실시업무, remark 150자 제한
	$('.maxLen').on('keyup', function() {
		if($(this).val().length > 150) {
	
			alert("글자수는 150자로 이내로 제한됩니다.");
	
			$(this).val($(this).val().substring(0, 150));
	
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