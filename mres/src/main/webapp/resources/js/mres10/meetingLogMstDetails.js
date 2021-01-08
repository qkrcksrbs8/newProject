$(function() {

	//------------------
	//기준년도 조회
	//------------------
	$('#selectCalDate').change(function() {
		var selectCalDate = $(this).val();				//기준년도
		$("#selectDate").val(selectCalDate);			//업무구분
	    $("#addList").val("");							//행추가 변수 값 초기화
		$("#selectForm").submit();						//서브밋 
		
	});


	//------------------------------
	//테이블을 저장하는 로직입니다.
	//------------------------------
	$("#tableSave").click(function(){
		
		//-------------------------------------------
		//List Row 만큼 반복문으로 json배열에 담는 형식입니다.
		//-------------------------------------------
// 		var checkbox = $("input[name=table_check]:checked");//체크된 체크박스	
		var checkbox = $("input[name=table_check]");		//모든 체크박스	
		
		//저장할 데이터가 없으면 리턴
		if(checkbox.length == 0){
			alert("저장할 데이터가 없습니다.");
			return;
		}//if
		
		var jsonArr = new Array();				//JsonArray를 위한 배열생성
        var totalJson = new Object();			//JsonObject의 합
		var division = $("#selectCode").val();	//업무구분
		
		//-------------------------
		//체크박스 반복문입니다.
		//-------------------------
		checkbox.each(function(i) {

  			var tr = checkbox.parent().parent().eq(i);				// checkbox.parent().parent() : <td>의 부모이므로 <>이다.	
	   		var td = tr.children();									// checkbox.parent() : checkbox의 부모는 <td>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var meeting_log_seq = td.eq(0).children().val();	//시퀀스
			var meeting_log_date = td.eq(1).children().children().children().children().val();	//일자
			var meeting_log_progress = td.eq(2).children().val();//장소
			var attend_count = td.eq(3).children().val();		//참석자
			var meeting_agenda = td.eq(5).text();				//파일이름
		
			//연간스케쥴 시퀀스가 on일경우 0으로 변환
			if(meeting_log_seq == 'on'){meeting_log_seq = 0;};
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.meeting_log_seq = meeting_log_seq;		//고유번호
			jsonObj.meeting_log_date	= meeting_log_date	//일자
			jsonObj.meeting_log_progress	= meeting_log_progress;	//장소 
			jsonObj.attend_count = attend_count;			//참석자
			jsonObj.meeting_agenda	= meeting_agenda;		//파일이름
			
			jsonArr[i] = jsonObj;							//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertMeetingLog";				//url 테이블 데이터 저장
		
		$.ajax({
			 method: "POST"
			,url : url
			,dataType : 'json'
			,data: {
				totalJson:stringJson
			}
		}).done(function(data){//통신 성공
			
			//---------
			//0000:정상 / 
			//---------
			if("0000" == data.resultCode){
				
				alert("저장이 완료되었습니다.");
				var selectCalDate = $("#selectCalDate").val();	//기준년도
				$("#selectDate").val(selectCalDate);			//기준년도
			    $("#addList").val("normal");					//행추가 변수 값 add
				$("#selectForm").submit();						//서브밋
					
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
   	   				
					td.eq(1).children().children().children().children().removeAttr('readonly');			//업무내용	수정 가능한 필드로 변경
	   				td.eq(2).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경
	   				td.eq(3).children().removeAttr('readonly');		//관리주체	수정 가능한 필드로 변경
					
   	   			});
   				
   				$("#tableUp").text("수정 완료");			//수정 버튼의 글자를 수정 완료로 변경
   				$("#tableSave").attr("disabled", true);	//저장 버튼 비활성화 -수정 중일 때 
   				$("#tableDel").attr("disabled", true);	//삭제 버튼 비활성화 -수정 중일 때 
   				$("#tableAdd").attr("disabled", true);	//행추가 버튼 비활성화 -수정 중일 때 
   				
   			}else{
   				
   				var result = confirm("수정 완료 하시겠습니까?");//수정 여부 물어보기
   				 
   				if(result){
   				
   					var seq = 0;//시퀀스 - 0:신규
					var resultCnt = 0;	//공백 체크여부			
					var resultMsg = '';	//공백이면 메시지 리턴
   					
	   				checkbox.each(function(i) {
	
	   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다.
	   	   				seq = td.eq(0).children().val();					//시퀀스 - 0:신규	   	 
						meeting_log_progress = td.eq(2).children().val();
						attend_count = td.eq(3).children().val();
						
						if(''==meeting_log_progress || null == meeting_log_progress ){
							resultMsg = checkMsg(resultMsg, '참석인원');
							resultCnt++;
						}
						if(''==attend_count || null == attend_count ){
							resultMsg = checkMsg(resultMsg, '교육내용');
							resultCnt++;
						}
						if(resultCnt > 0){
							alert(resultMsg+'을(를) 입력해 주세요.');
							return;
						}
						
	   	   				td.eq(1).children().children().children().children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(2).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	   	   				td.eq(3).children().attr('readonly','readonly');		//수정 완료되면 readonly 적용
	
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
			
			var meeting_log_seq = td.eq(0).children().val();	//관리단회의록 시퀀스
			if(meeting_log_seq == 'on'){meeting_log_seq = 0;};	//관리단회의록시퀀스가 on일경우 0으로 변환
			
			// 가져온 값을 배열에 담는다.
			var jsonObj = new Object();						//JsonObject를 위한 객체생성
			jsonObj.meeting_log_seq	= meeting_log_seq;		//업무내용 
			jsonArr[i] = jsonObj;							//Array 배열 push

		});

		var stringJson = JSON.stringify(jsonArr); 			//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./deleteMeetingLog";						//url 테이블 데이터 삭제
		
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
				var selectCalDate = $("#selectCalDate").val();	//기준년도
				$("#selectDate").val(selectCalDate);			//기준년도
			    $("#addList").val("normal");					//행추가 변수 값 add
				$("#selectForm").submit();						//서브밋
					
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
   		
   		var selectCalDate = $("#selectCalDate").val();	//기준년도
		$("#selectDate").val(selectCalDate);	//기준년도
	    $("#addList").val("add");											//행추가 변수 값 add
		$("#selectForm").submit();											//서브밋
		$("#addList").val("");												//행추가 변수 공백
   		
   	});



	popup();
	
	//----------------
	//파일첨부 팝업입니다.
	//----------------
	$(".uploadPopup").click(function(){

//		$("#fileTable").empty();//현재 파일 리스트 제거
		$(".removeTr").remove();
		
		var tr = $(this).parent().parent().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();						//클릭한 테이블의 td
		var table_seq	= td.eq(0).children().val();//시퀀스
		var table_name = "meetingLogMst";			//테이블 이름
		
		
		//테이블 리스트를 보여주는 것이 아닌 파일이 있을 때는 PDF
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
				
				//0000:파일 업로드 성공|2000:파일 업로드 중 실패|2100:pdf파일이 아님|3000:서비스 로직 진입 후 실패 |4000:파일 null|9000:fileUpload()컨트롤러 오류
				if("0000" == data.resultCode){
					alert("업로드 되었습니다.");
			
			   		var selectCalDate = $("#selectCalDate").val();	//기준년도
					$("#selectDate").val(selectCalDate);	//기준년도
				    $("#addList").val("add");						//행추가 변수 값 add
					$("#addList").val("");							//행추가 변수 공백
					$("#selectForm").submit();						//서브밋
//					$("#pdf_reg_popup").popup('hide');
					
				}else if("2000" == data.resultCode){
					alert("파일 업로드 오류. 관리자에게 문의해 주세요.");
				}else if("2100" == data.resultCode){
					alert("PDF파일만 업로드 가능합니다.");
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
	
	//append로 생성된 태그에 동적 이벤트 추가
	$(document).on("click","#fileDown",function(){ 
	   	
		var file_seq = $(this).attr('class');
		$("#file_seq").val(file_seq);
		$("#fileForm").attr("action", "fileDownload");//다운로드 경로
		$("#fileForm").submit();//서브밋 
		$("#fileForm").attr("action", "fileUpload");//다운로드 경로
			
	 });



	//----------------
	//PDF 팝업입니다.
	//----------------
	$(".pdfPopup").click(function(){
		
		var tr = $(this).parent().parent().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();						//클릭한 테이블의 td
		 
		var file_path	= '/\/'+td.eq(4).attr('class');	//파일경로
		
		var contextPath = $("#contextPath").val();	//contextPath 경로 
		var path = contextPath+file_path;		//불러올 파일 경로
		
		$("#pdfIframe").attr('src',path); 
		$("#pdf_reg_popup .popup_title").html("pdf");
		$("#fileForm")[0].reset();
		$("#filename").hide();
		$("#pdf_reg_popup").popup('show');
		
	});  
	
	
	//----------------
	//팝업 닫기 기능 입니다.
	//----------------
	$(".pdf_reg_popup_close").click(function(){
		
		$("#pdf_reg_popup").popup('hide');//팝업 종료
		
	});//
	
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