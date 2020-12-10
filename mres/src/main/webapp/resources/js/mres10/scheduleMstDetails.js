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
		rowIndex *= 1;																//row 숫자로 변환
		rowIndex = rowIndex - 2;													//row - 2
		var checkMonth = 'checkMonth'+rowIndex;										//input name+row
		var cehckLength = $('input:checkbox[name='+checkMonth+']:checked').length;	//클릭한 row의 월 체크 개수
		var check_sycle = 'check_sycle'+rowIndex; 									//클릭한 row의 점검주기 클래스
		$('.'+check_sycle).text(cehckLength+'회/년');									//클릭한 row의 월 체크 개수 n회/년 적용
		
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

  			var tr = checkbox.parent().parent().eq(i);						// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   		var td = tr.children();											// checkbox.parent() : checkbox의 부모는 <li>이다.
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var schedule_seq = td.eq(0).children().val();					//연간스케쥴 시퀀스
			var work_info = td.eq(1).text();								//업무내용
			var check_cycle = td.eq(2).text();								//점검주기
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
			var entity = td.eq(15).text();									//계약주체
			var contract = td.eq(16).text();								//계약서
			var file_name = td.eq(17).text();								//파일이름

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
			jsonObj.contract	= contract;			//계약서이름
			jsonObj.file_name	= file_name;		//파일이름

			jsonArr[i] = jsonObj;					//Array 배열 push

		});
		
		var stringJson = JSON.stringify(jsonArr); 	//메서드에 들어온 매개변수를 문자열로 변환
		var url = "./insertSchedule";			//url 테이블 데이터 저장
		
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
				var division = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
			    $("#division").val(division);							//업무구분
			    $("#addList").val("normal");							//행추가 변수 값 add
				$("#selectForm").submit();								//서브밋
					
			} else if ("9000" == data.resultCode){
				
				alert("관리자에게 문의주세요.");
				
			} else{
				
				alert("알 수 없는 에러. 관리자에게 문의해주세요.");
				
			}
		    
			
			
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
   	   				var work_info_val = td.eq(1).text();				//업무내용
					var entity_val = td.eq(15).text();					//계약주체
   	   				
   	   				td.eq(1).html('<input id="workInfo" type="text" value="'+work_info_val+'">');//수정 가능한 필드로 변경
					td.eq(15).html('<input id="entity" type="text" value="'+entity_val+'">');	//수정 가능한 필드로 변경
					
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
	
	   	   				var tr = checkbox.parent().parent().eq(i);			// checkbox.parent().parent() : <li>의 부모이므로 <ul>이다.	
	   	   				var td = tr.children();								// checkbox.parent() : checkbox의 부모는 <li>이다.
	   	   				seq = td.eq(0).children().val();					//시퀀스 - 0:신규	   	   				
	   	   				var work_info_val = td.eq(1).children().val();		//업무내용	
						var entity_val = td.eq(15).children().val();		//계약주체
						
	   	   				td.eq(1).html('<label>'+work_info_val+'</label>');	//수정 완료되면 다시 label로 변경
	   	   				td.eq(15).html('<label>'+entity_val+'</label>');	//수정 완료되면 다시 label로 변경
	
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
				var division = $("#selectCode").val();					//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
			    $("#division").val(division);							//업무구분
			    $("#addList").val("normal");							//행추가 변수 값 add
				$("#selectForm").submit();								//서브밋
					
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
   		if(checkboxCnt >= 10){
			alert("10개 이상 생성 불가.");   			
   			return; 
   		};
   		
   		var selectcDivision = $("#selectCode").val();						//셀렉트박스  AS01:행정업무 / AS02:회계업무 / AS03:조경업무 / AS04:시설업무
	    $("#division").val(selectcDivision);								//업무구분
	    $("#addList").val("add");											//행추가 변수 값 add
		$("#selectForm").submit();											//서브밋
		$("#addList").val("");												//행추가 변수 공백
   		
   	});



	
   	//-----------------------------
   	//파일이름을 누르면 호출되는 팝업창 입니다.
   	//-----------------------------
	function wrapWindowByMask(){
		
		//화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();  
        var maskWidth = $(window).width();  
 
        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#mask").css({"width":maskWidth,"height":maskHeight});  
 
        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.
 
        $("#mask").fadeIn(0);      
        $("#mask").fadeTo("slow",0.6);    
 
        //윈도우 같은 거 띄운다.
        $(".window").show();
		
	};
	
	//검은 막 띄우기
    $(".openMask").click(function(e){
		var tr = $(this).parent().parent().eq(0);	//클릭한 테이블의 tr
		var td = tr.children();						//클릭한 테이블의 td
		var table_seq	= td.eq(0).children().val();//시퀀스
		var table_name = "schedule_mst";			//테이블 이름
		
		$("#table_seq").val(table_seq);				//테이블 시퀀스 필드에 값 셋팅
		$("#table_name").val(table_name);			//테이블 이름 필드에 값 셋팅
		
        e.preventDefault();
        wrapWindowByMask();
    });
 
    //닫기 버튼을 눌렀을 때
    $(".window .close").click(function (e) {  
        //링크 기본동작은 작동하지 않도록 한다.
        e.preventDefault();  
        $("#mask, .window").hide();  
    });       
 
    //검은 막을 눌렀을 때
    $("#mask").click(function () {  
        $(this).hide();  
        $(".window").hide();  
 
    });    

	//파일 업로드
	$("#imgUp").click(function(){
		
		var form = jQuery("#fileForm")[0];
        var formData = new FormData(form);
        formData.append("message", "ajax로 파일 전송하기");
        formData.append("file", jQuery("#fileUpId")[0].files[0]);
		
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
				}//if
				
            }//success

        });//$ajax
		
	})//imgUp click
	
	//파일 다운로드
	$("#imgDown").click(function(){
		
		$("#fileForm").attr("action", "fileDownload");//다운로드 경로
		$("#fileForm").submit();//서브밋
		
	})//imgDown click
	
});