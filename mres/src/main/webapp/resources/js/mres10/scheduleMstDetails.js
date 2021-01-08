$(function() {

	//------------------
	//셀렉트박스 조회
	//------------------
	$('#selectCode').change(function() {
		
		var selectCode = $(this).val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
		var selectCalDate = $("#selectCalDate").val();	//기준년도
	    $("#division").val(selectCode);					//업무구분
		$("#selectCalDate").val(selectCalDate);			//업무구분
	    $("#addList").val("");							//행추가 변수 값 초기화
		$("#selectForm").submit();						//서브밋
		
	});
	
	//------------------
	//기준년도 조회
	//------------------
	$('#selectCalDate').change(function() {
		
		var selectCalDate = $(this).val();				//기준년도
		$("#selectDate").val(selectCalDate);			//업무구분
		var selectCode = $("selectCode").val();			//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#division").val(selectCode);					//업무구분
	    $("#addList").val("");							//행추가 변수 값 초기화
		$("#selectForm").submit();						//서브밋 
		
	});
	
	 
	//------------------------------------------------------------
	//1~12월 체크박스 클릭 시 체크 여부에 따라 점검주기의 n회/년 으로 텍스트가 바뀝니다.
	//------------------------------------------------------------
	$(".tableCheck").click(function(){
			
		var rowIndex = $(this).parent().parent().children().index($(this).parent());//클릭한 row
		rowIndex *= 1;																//row 숫자로 변환
		rowIndex = rowIndex - 2;													//row - 2
		var checkMonth = 'checkMonth'+rowIndex;										//input name+row
		var cehckLength = $('input:checkbox[name='+checkMonth+']:checked').length;	//클릭한 row의 월 체크 개수
		var check_sycle = 'check_sycle'+rowIndex; 									//클릭한 row의 점검주기 클래스
		$('.'+check_sycle).val(cehckLength+'회/년');			 						//클릭한 row의 월 체크 개수 n회/년 적용
		
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
		
		//저장할 데이터가 없으면 리턴
		if(checkbox.length == 0){
			alert("저장할 데이터가 없습니다.");
			return;
		}//if
		
		var jsonArr = new Array();						//JsonArray를 위한 배열생성
        var totalJson = new Object();					//JsonObject의 합
		var division = $("#selectCode").val();			//업무구분
		
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);						// checkbox.parent().parent() : <td>의 부모이므로 <>이다.	
	   		var td = tr.children();											// checkbox.parent() : checkbox의 부모는 <td>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var schedule_seq = td.eq(0).children().val();					//연간스케쥴 시퀀스
			var work_info = td.eq(1).children().val();						//업무내용
			var check_cycle = td.eq(2).children().val();					//점검주기
			var schedule_jan = td.eq(3).children().is(":checked")? 1 : 0;	//1월
			var schedule_feb = td.eq(4).children().is(":checked")? 1 : 0;	//2월
			var schedule_mar = td.eq(5).children().is(":checked")? 1 : 0; 	//3월
			var schedule_apr = td.eq(6).children().is(":checked")? 1 : 0;	//4월
			var schedule_may = td.eq(7).children().is(":checked")? 1 : 0;	//5월
			var schedule_jun = td.eq(8).children().is(":checked")? 1 : 0; 	//6월
			var schedule_jul = td.eq(9).children().is(":checked")? 1 : 0;	//7월
			var schedule_aug = td.eq(10).children().is(":checked")? 1 : 0;	//8월
			var schedule_sep = td.eq(11).children().is(":checked")? 1 : 0; 	//9월
			var schedule_oct = td.eq(12).children().is(":checked")? 1 : 0;	//10월
			var schedule_nov = td.eq(13).children().is(":checked")? 1 : 0;	//11월
			var schedule_dec = td.eq(14).children().is(":checked")? 1 : 0; 	//12월
			var entity = td.eq(15).children().val();						//계약주체
			var file_name = td.eq(16).text();								//파일이름
			
			//연간스케쥴 시퀀스가 on일경우 0으로 변환
			if(schedule_seq == 'on'){schedule_seq = 0;};
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();				//JsonObject를 위한 객체생성
			jsonObj.schedule_seq = schedule_seq;	//고유번호
			jsonObj.division	= division			//업무구분
			jsonObj.work_info	= work_info;		//업무내용 
			jsonObj.check_cycle = check_cycle;		//점검주기
			jsonObj.schedule_jan= schedule_jan;		//1월
			jsonObj.schedule_feb= schedule_feb;		//2월
			jsonObj.schedule_mar= schedule_mar;		//3월
			jsonObj.schedule_apr= schedule_apr;		//4월
			jsonObj.schedule_may= schedule_may;		//5월
			jsonObj.schedule_jun= schedule_jun;		//6월
			jsonObj.schedule_jul= schedule_jul;		//7월
			jsonObj.schedule_aug= schedule_aug;		//8월
			jsonObj.schedule_sep= schedule_sep;		//9월
			jsonObj.schedule_oct= schedule_oct;		//10월
			jsonObj.schedule_nov= schedule_nov;		//11월
			jsonObj.schedule_dec= schedule_dec;		//12월
			jsonObj.entity		= entity;			//관리주체
			jsonObj.contract	= file_name;		//계약서이름

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertSchedule";			//url 테이블 데이터 저장
		var selectCalDate = $("#selectCalDate").val();	//기준년도
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
				,selectCalDate:selectCalDate
			}
		}).done(function(data){//통신 성공
			
			//---------
			//0000:정상 / 
			//---------
			if("0000" == data.resultCode){
				
				alert("저장이 완료되었습니다.");
				var division = $("#selectCode").val();	//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
			    $("#division").val(division);			//업무구분
			    $("#addList").val("normal");			//행추가 변수 값 add
				var selectCalDate = $("#selectCalDate").val();	//기준년도
				$("#selectDate").val(selectCalDate);	//업무구분
				$("#selectForm").submit();				//서브밋
					
			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}//if - else if - else
		    
		}).fail(function(data){//통신 실패
			alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
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

   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다. 	   	
   	   				
					td.eq(1).children().removeAttr('readonly');			//업무내용	수정 가능한 필드로 변경
	   				td.eq(15).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경
					
   	   			});
   				
   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화 -수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화 -수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 -수정 중일 때 
   				
   			}else{
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   				 
   				if(result){
   				
   					var seq = 0;		//시퀀스 - 0:신규
   					var resultCnt = 0;	//공백 체크여부			
					var resultMsg = '';	//공백이면 메시지 리턴
		
	   				checkbox.each(function(i) {
	
	   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다.
	   	   				seq = td.eq(0).children().val();					//시퀀스 - 0:신규	   	 
						work_info = td.eq(1).children().val();				
						entity = td.eq(15).children().val();
						
						if(''==work_info || null == work_info ){
							resultMsg = checkMsg(resultMsg, '업무내용');
							resultCnt++;
						}
						if(''==entity || null == entity ){
							resultMsg = checkMsg(resultMsg, '관리주체');
							resultCnt++;
						}
						if(resultCnt > 0){
							alert(resultMsg+'을(를) 입력해 주세요.');
							return;
						}
						
	   	   				td.eq(1).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(15).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	
	   	   			});
	   				
					//------------------------
					//행추가 중이면 행추가 버튼 비활성화
					//------------------------
					if(0 != seq){
						$("#tableAdd").attr("disabled", false);	//행추가 버튼 비활성화 수정 중일 때 
					};//if
					
	   				
					if(resultCnt == 0){
						
		   				$("#tableUp").text("수정");					//수정 버튼의 글자를 수정 완료로 변경
		   				$("#tableSave").attr("disabled", false);	//저장 버튼 비활성화 -수정 중일 때 
		   				$("#tableDel").attr("disabled", false);		//삭제 버튼 비활성화 -수정 중일 때 
						alert("저장 버튼을 눌러주세요.");	//수정 완료	
						
					}//if
	   				
	   				
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
			
			var schedule_seq = td.eq(0).children().val();	//연간스케쥴 시퀀스
			if(schedule_seq == 'on'){schedule_seq = 0;};	//연간스케쥴 시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.schedule_seq	= schedule_seq;			//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteSchedule";						//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공
		
			//---------------------------
			//행추가를 삭제했으면 행추가 버튼 활성화
			//---------------------------
			if(0 == seq){
				$("#tableAdd").attr("disabled", false);	//행추가 버튼 활성화 
			};//if
			
			if("0000" == data.resultCode){
				
				alert("삭제되었습니다.");
				var division = $("#selectCode").val();	//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
			    $("#division").val(division);			//업무구분
			    $("#addList").val("normal");			//행추가 변수 값 add
				var selectCalDate = $(this).val();		//기준년도
				$("#selectDate").val(selectCalDate);	//업무구분
				$("#selectForm").submit();				//서브밋
					              
			};//if
			
		}).fail(function(data){//통신 실패
			alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
			return;
		});//fail
		
   	});
   	
	
   	//----------------------------
   	//행추가 기능입니다.
   	//----------------------------
   	$("#tableAdd").click(function(){
   		var checkboxCnt = $("input[name=table_check]").length;	//체크박스 개수 10개 이상이면 생성 불가
   		if(checkboxCnt >= 20){
			alert("20개 이상 생성 불가.");   			
   			return; 
   		};
   		
   		var selectcDivision = $("#selectCode").val();	//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#division").val(selectcDivision);			//업무구분
	    $("#addList").val("add");						//행추가 변수 값 add
		var selectCalDate = $("#selectCalDate").val();	//기준년도
		$("#selectDate").val(selectCalDate);			//업무구분
		$("#selectForm").submit();						//서브밋
		$("#addList").val("");							//행추가 변수 공백
   		
   	});



	popup();
	
	//----------------
	//파일첨부 팝업입니다.
	//----------------
	$(".uploadBtn").click(function(){
//	function uploadPopup(){
		
//		$("#fileTable").empty();//현재 파일 리스트 제거
		$(".removeTr").remove();
		
		var tr = $(this).parent().parent().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();						//클릭한 테이블의 td
		var table_seq	= td.eq(0).children().val();//시퀀스
		var table_name = "scheduleMst";				//테이블 이름
		
		var url = "./selectFileList";						//url 테이블 데이터 삭제
		
		$.ajax({
			 type: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				table_seq:table_seq
				,table_name:table_name
			}
		}).done(function(data){//통신 성공
			
			if("0000" == data.resultCode){
				
				if(data.fileCnt > 0){
					
					for(var i = 0; i < data.fileCnt; ++i){
						
						$("#fileTable").append('<tr class="removeTr"><td><label>'+data.fileList[i].file_date+'</label></td><td><label>'+data.fileList[i].file_content+'</label></td><td id="fileDown" class='+data.fileList[i].file_seq+' onclick="fileDown()"><label>'+data.fileList[i].file_name+'</label></td></tr>')
						
					}//for
					$(".fileDown").addClass('fileDownoald');
				}//if

			}//if 
			
		}).fail(function(data){//통신 실패
			alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
			return;
		});//fail
		
		
		$("#table_seq").val(table_seq);				//테이블 시퀀스 필드에 값 셋팅
		$("#table_name").val(table_name);			//테이블 이름 필드에 값 셋팅
		
		$("#schedule_reg_popup .popup_title").html("파일 업로드/다운로드");
		$("#fileForm")[0].reset();
		$("#filename").hide();
		$("#schedule_reg_popup").popup('show');
		
//	}
	});
	
	//----------------
	//팝업 닫기 기능 입니다.
	//----------------
	$(".schedule_reg_popup_close").click(function(){
		
//		$("#fileTable").empty();//현재 파일 리스트 제거
		$(".removeTr").remove();//현재 파일 리스트 제거
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
				
				if("0000" == data.resultCode){
					alert("업로드 되었습니다.");
					
					if(data.fileCnt > 0){
						
//						$("#fileTable").empty();//현재 파일 리스트 제거
						$(".removeTr").remove();
						
						for(var i = 0; i < data.fileCnt; ++i){
							
							$("#fileTable").append('<tr class="removeTr"><td><label>'+data.fileList[i].file_date+'</label></td><td><label>'+data.fileList[i].file_content+'</label></td><td id="fileDown" class='+data.fileList[i].file_seq+' onclick="fileDown()"><label>'+data.fileList[i].file_name+'</label></td></tr>')
							
						}//for
						$(".fileDown").addClass('fileDownoald');
					}//if
				
					$('#fileForm')[0].reset();
				}//if
				
            }//success

        });//$ajax
		
	})//imgUp click
	
	//append로 생성된 태그에 동적 이벤트 추가
	$(document).on("click","#fileDown",function(){ 
	   	
		var file_seq = $(this).attr('class');
		$("#file_seq").val(file_seq);
		$("#fileForm").attr("action", "fileDownload");//다운로드 경로
		$("#fileForm").submit();//서브밋 
		$("#fileForm").attr("action", "fileUpload");//다운로드 경로
			
	 });
	
	//특수문자 입력 불가
	$(document).on("keyup",".maxVal",function(){ 
	   	
		var x = $(this).val();						//현재 입력된 값
	  	x = x.replace( /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi,'');   				// 입력값이 숫자가 아니면 공백
	  	$(this).val(x); 							//현재 필드에 파싱된 값 리턴

		maxVal(x, 5);	
	 });//동적 이벤트 부여
	
	
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