$(function() {
	//숫자만 입력되게
	$("input:text[numberOnly]").on("keyup", function() {
	    $(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
	// 숫자만 입력되게 [ 동적생성된 input에 사용되어지는 함수 ]
	$(document).on("keyup", ".numberOnly", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/gi,"")); 
	});
	
	//숫자 및 콤마처리
	$("input:text[numberOnlyComma]").on("keyup", function() {
	    $(this).val(commaFormatter($(this).val().replace(/[^.0-9]/g,""),"","",""));
	});
	
	//숫자 및 콤마처리 [ 동적생성된 input에 사용되어지는 함수 ]
	$(document).on("keyup", ".numberOnlyComma", function() { 
		$(this).val(commaFormatter($(this).val().replace(/[^.0-9]/g,""),"","","")); 
	});

	//숫자,소수점 입력되게
	$("input:text[floatOnly]").on("keyup", function() {
	    $(this).val($(this).val().replace(/[^.0-9]/g,""));
	});
	
	//숫자,소수점 입력 및 콤마처리
	$("input:text[floatOnlyComma]").on("keyup", function() {
	    $(this).val(commaFormatter($(this).val().replace(/[^.0-9]/g,""),"","",""));
	});
	
	//숫자,소수점 입력 및 콤마처리 [ 동적생성된 input에 사용되어지는 함수 ]
	$(document).on("keyup", ".floatOnlyComma", function() { 
		$(this).val(commaFormatter($(this).val().replace(/[^.0-9]/g,""),"","","")); 
	});
	
	//핸드폰번호
	$("input:text[numberPhone]").on("keyup", function() {
		inputPhoneNumber(this);
	});
	
	//전화번호
	$("input:text[numberTel]").on("keyup", function() {
		inputTelNumber(this);
	});
});

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	var path = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	if(path == "/print"){
		path = "";
	}
	return path;
};

function color_change(flag){
	var now_class;
	now_class = $('body').attr('class');
	if(flag == 2){
		$('.'+now_class).removeClass(now_class).addClass('mg_wrap');
	}else if(flag == 3){
		$('.'+now_class).removeClass(now_class).addClass('mv_wrap');
	}else if(flag == 4){
		$('.'+now_class).removeClass(now_class).addClass('mm_wrap');
	}else if(flag == 5){
		$('.'+now_class).removeClass(now_class).addClass('mn_wrap');
	}{
		$('.'+now_class).removeClass(now_class).addClass('mb_wrap');
	}
}

function color_change_click(flag, member_id){
	color_change(flag);

	$.ajax({
		async: false,
		url : getContextPath() + "/updateColorInfo",
		method : "get",
		type : "json",
		contentType : "application/json",
		data: { 
			"color" : flag,
			"memberId" : member_id
		},
		success : function(data) {

		}
	});
}

//사업장 선택
function office_change(compId){
//	$.ajax({
//		async: false,
//		url : getContextPath() + "/updateSelOffice",
//		method : "get",
//		type : "json",
//		contentType : "application/json",
//		data: { 
//			"CompCode" : compId
//		},
//		success : function(data) {
//
//		}
//	});
}

//핸드폰번호 마이너스(-) 삽입
function inputPhoneNumber(obj) {

    var number = obj.value.replace(/[^0-9]/g, "");
    var phone = "";

    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if(number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    obj.value = phone;
}

//전화번호 마이너스(-) 삽입
function inputTelNumber(obj) {

    var number = obj.value.replace(/[^0-9]/g, "");
    var tel = "";

    // 서울 지역번호(02)가 들어오는 경우
    if(number.substring(0, 2).indexOf('02') == 0) {
        if(number.length < 3) {
            return number;
        } else if(number.length < 6) {
            tel += number.substr(0, 2);
            tel += "-";
            tel += number.substr(2);
        } else if(number.length < 10) {
            tel += number.substr(0, 2);
            tel += "-";
            tel += number.substr(2, 3);
            tel += "-";
            tel += number.substr(5);
        } else {
            tel += number.substr(0, 2);
            tel += "-";
            tel += number.substr(2, 4);
            tel += "-";
            tel += number.substr(6);
        }
    
    // 서울 지역번호(02)가 아닌경우
    } else {
        if(number.length < 4) {
            return number;
        } else if(number.length < 7) {
            tel += number.substr(0, 3);
            tel += "-";
            tel += number.substr(3);
        } else if(number.length < 11) {
            tel += number.substr(0, 3);
            tel += "-";
            tel += number.substr(3, 3);
            tel += "-";
            tel += number.substr(6);
        } else {
            tel += number.substr(0, 3);
            tel += "-";
            tel += number.substr(3, 4);
            tel += "-";
            tel += number.substr(7);
        }
    }

    obj.value = tel;
}

//기간 유효성 체크
function inputDateTermCheck(str_flag, srt_dt, end_dt, rng_day){
    var arySrtDt = srt_dt.split("-"); // ex) 시작일자(2007-10-09)
    var aryEndDt = end_dt.split("-"); // ex) 종료일자(2007-12-05)

    if( arySrtDt.length != 3 || aryEndDt.length != 3){ 
    	alertBox(str_flag + " 날짜 형식이 잘못되었습니다"); return false;
    }

    var startDt = new Date(Number(arySrtDt[0]),Number(arySrtDt[1])-1,Number(arySrtDt[2]));
    var endDt	= new Date(Number(aryEndDt[0]),Number(aryEndDt[1])-1,Number(aryEndDt[2]));
    resultDt	= Math.floor(endDt.valueOf()/(24*60*60*1000)- startDt.valueOf()/(24*60*60*1000));

    if(resultDt < 0 ){ alertBox(str_flag + " 시작날짜가 더 큽니다"); return false; }
    //if(resultDt > rng_day){ alert("입력가능 기간은 "+ rng_day +"일 입니다."); return false; }

    return true;

}

//form info ajax
function execFormInfoAjax(url, parmdata){
	$.ajax({
		async: false,
		url : getContextPath() + url,
		method : "get",
		type : "json",
		contentType : "application/json",
		data: parmdata,
		success : function(data) {
			//console.log("[execFormInfoAjax] data : ", data);
			//console.log(data.list.length);
			data = JSON.stringify(data);
			data = JSON.parse(data);
			if(data.list.length > 0){
				for(var i=0; i < data.list.length; i++){
					for(var key in data.list[i]) {
						//console.log("key : " + key + ", val : " + data.list[i][key] );
						if($("#"+key).attr('type') == 'radio' ){
							$('input:radio[name="'+key+'"][value="'+data.list[i][key]+'"]').prop("checked", true);
						}else if($("#"+key).attr('type') == 'checkbox'){
							if(key=="dongHoUse" && data.list[i][key]!=null){
								
								var tempArr = data.list[i][key].split(',');
								for(var j in tempArr){
									$('input:checkbox[name="'+key+'"][value="'+j+'"]').prop("checked", true);
								}
							}
							else{
								$('input:checkbox[name="'+key+'"][value="'+data.list[i][key]+'"]').prop("checked", true);
							}
						}
						else{
							if(data.list[i][key] != null){
								$("#"+key).val(data.list[i][key]);
							}else{
								$("#"+key).val('');
							}
						}
					}
				}
			}else{
				retVal = 0;
			}
		}
	});
}

//div txt ajax
function execTxtInfoAjax(url, parmdata){
	$.ajax({
		async: false,
		url : getContextPath() + url,
		method : "get",
		type : "json",
		contentType : "application/json",
		data: parmdata,
		success : function(data) {
			//console.log(data);
			//console.log(data.list.length);
			if(data.list.length > 0){
				for(var i=0; i < data.list.length; i++){
					for(var key in data.list[i]) {
						//console.log("key : " + key + ", val : " + data.list[i][key] );
						if(data.list[i][key] != null){
							$("#"+key).text(data.list[i][key]);
						}
					}
				}
			}
		}
	});
}

//check ajax
function execCheckAjax(url, parmdata){
	var rtn = false;
	
	$.ajax({
		async: false,
		url : getContextPath() + url,
		method : "get",
		type : "json",
		contentType : "application/json",
		data: parmdata,
		success : function(data) {
			//console.log("[execCheckAjax] data : ", data);
			//console.log(data.list.length);
			if(data.list == true){
				rtn = true;
			}else{
				rtn = false;
			}
		}
	});
	
	return rtn ;
}

//communication ajax [ CreateUpdateDelete ]
function execCommAjax(url, parmdata){	
	$.ajax({
		async: false,
		url : getContextPath() + url,
		method : "get",
		type : "json",
		contentType : "application/json",
		data: parmdata,
		success : function(data) {
			//console.log("[execCommAjax] data : ", data);
		}
	});
}

function execCommAjax2(url, parmdata){	
	$.ajax({
		async: false,
		url : getContextPath() + url,
		method : "POST",
		data: parmdata,
		success : function(data) {
			
		}
	});
}

function gup( name, url ) {
	if (!url) url = location.href;
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( url );
	return results == null ? null : results[1];
}

//자리수 맞춤
function addZeros(num, digit) {
  var zero = '';
  num = num.toString();
  if (num.length < digit) {
	for (i = 0; i < digit - num.length; i++) {
	  zero += '0';
	}
  }
  return zero + num;
}

//시간 표출
function printClock() {
var currentDate = new Date();
var year = currentDate.getFullYear();
var month = addZeros(currentDate.getMonth() + 1, 2);
var day = addZeros(currentDate.getDate(), 2);

  var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
  var amPm = 'AM'; // 초기값 AM
  var currentHours = addZeros(currentDate.getHours(),2);
  var currentMinute = addZeros(currentDate.getMinutes() ,2);
  var currentSeconds =  addZeros(currentDate.getSeconds(),2);

  if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
	amPm = 'PM';
	currentHours = addZeros(currentHours - 12,2);
  }

  $(".header_time").html(year+"-"+month+"-"+day + " " + currentHours+":"+currentMinute+":"+currentSeconds + amPm); //시간을 출력해 줌

  setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
}

$(window).on('load', function() {
	printClock();
});

function popup(){
	$('.mres_popup').popup({
		transition : 'all 0.3s',
		blur : false,
		scrolllock : true,
		opacity: 0.1
	});
}

function popupDrag(){
	$('.mres_popup').popup({
		transition : 'all 0.3s',
		blur : false,
		scrolllock : true,
		opacity: 0.1
	});
	
	$('.mres_popup').draggable({'cancel':'.pop_wrap', containment:'parent', scroll:false});
}

function grid_view(type, url, grid_id, col_name, col_model, row_num, multi_sel){
	// 변수 선언
	var i, max, myData, grid = $("#"+grid_id);

	// grid 설정
	grid.jqGrid({
		url : getContextPath() + url,
		datatype : type,
		datatype : "local",
		autowidth : true,
		height : 'auto',
		rownumbers : row_num,
		colNames : col_name,
		colModel: col_model,
		multiselect : multi_sel
	});
}

function grid_view2(type, url, w, grid_id, col_name, col_model, row_num, multi_sel){
	// 변수 선언
	var i, max, myData, grid = $("#"+grid_id);

	// grid 설정
	grid.jqGrid({
		url : getContextPath() + url,
		datatype : type,
		width : w,
		height : 'auto',
		rownumbers : row_num,
		colNames : col_name,
		colModel: col_model,
		multiselect : multi_sel
	});
}

//data, datatype, url, autowidth, widthvalue, autoheight, height, grid id, colNames[열이름], colModel[열구조], multiselect, flag, g[그룹핑여부], g_view[그룹구조]
function gridViewScroll(data, type, url, fit, w, wv, h, hv, grid_id, col_name, col_model, post_data, row_num, multi_sel, g, g_view, flag){
	// 변수 선언
	var i, max, myData, grid = $("#"+grid_id);

	// grid 설정
	grid.jqGrid({
		url : getContextPath() + url,
		data : data,
		datatype : type,
		shrinkToFit : fit,
		autowidth : w,
		width : wv,
		autoheight : h,
		height : hv,
		postData : post_data,
		scrollrows : true,
		rowNum: -1,
		rownumbers : row_num,
		colNames : col_name,
		colModel: col_model,
		multiselect : multi_sel,
		grouping : g,
		groupingView : g_view,
		cmTemplate: { sortable: false },
		onCellSelect : function(rowid, index, contents, event) {
			if(flag == 1){
				gridCellSelect1(grid_id, rowid, index, contents, event);
			}else if(flag == 2){
				gridCellSelect2(grid_id, rowid, index, contents, event);
			}else if(flag == 3){
				gridCellSelect3(grid_id, rowid, index, contents, event);
			}else if(flag == 4){
				gridCellSelect4(grid_id, rowid, index, contents, event);
			}else if(flag == 5){
				gridCellSelect5(grid_id, rowid, index, contents, event);
			}else if(flag == 6){
				gridCellSelect6(grid_id, rowid, index, contents, event);
			}else if(flag == 7){
				gridCellSelect7(grid_id, rowid, index, contents, event);
			}else if(flag == 8){
				gridCellSelect8(grid_id, rowid, index, contents, event);
			}else if(flag == 9){
				gridCellSelect9(grid_id, rowid, index, contents, event);
			}else if(flag == 10){
				gridCellSelect10(grid_id, rowid, index, contents, event);
			}else{
				
			}
		},
		gridComplete : function() {
			//$("#"+grid_id).closest(".ui-jqgrid-bdiv").scrollTop(0);
			if(flag == 1){
				gridComplete1(grid_id);
			}else if(flag == 2){
				gridComplete2(grid_id);
			}else if(flag == 3){
				gridComplete3(grid_id);
			}else if(flag == 4){
				gridComplete4(grid_id);
			}else if(flag == 5){
				gridComplete5(grid_id);
			}else if(flag == 6){
				gridComplete6(grid_id);
			}else if(flag == 7){
				gridComplete7(grid_id);
			}else if(flag == 8){
				gridComplete8(grid_id);
			}else if(flag == 9){
				gridComplete9(grid_id);
			}else if(flag == 10){
				gridComplete10(grid_id);
			}else{
				
			}
		},
		loadComplete : function() {
			if(flag == 1){
				loadComplete1(grid_id);
			}else if(flag == 2){
				loadComplete2(grid_id);
			}else if(flag == 3){
				loadComplete3(grid_id);
			}else if(flag == 4){
				loadComplete4(grid_id);
			}else if(flag == 5){
				loadComplete5(grid_id);
			}else if(flag == 6){
				loadComplete6(grid_id);
			}else if(flag == 7){
				loadComplete7(grid_id);
			}else if(flag == 8){
				loadComplete8(grid_id);
			}else if(flag == 9){
				loadComplete9(grid_id);
			}else if(flag == 10){
				loadComplete10(grid_id);
			}else{
				
			}
		}
	});
}

//data, datatype, url, autowidth, widthvalue, autoheight, height, grid id, colNames[열이름], colModel[열구조], multiselect, flag, g[그룹핑여부], g_view[그룹구조]
function gridViewFooter(data, type, url, fit, w, wv, h, hv, grid_id, col_name, col_model, post_data, row_num, multi_sel, g, g_view, flag){
	// 변수 선언
	var i, max, myData, grid = $("#"+grid_id);

	// grid 설정
	grid.jqGrid({
		url : getContextPath() + url,
		data : data,
		datatype : type,
		shrinkToFit : fit,
		autowidth : w,
		width : wv,
		autoheight : h,
		height : hv,
		postData : post_data,
		scrollrows : false,
		rowNum: -1,
		rownumbers : row_num,
		colNames : col_name,
		colModel: col_model,
		multiselect : multi_sel,
		grouping : g,
		groupingView : g_view,
		footerrow : true,
		userDataOnFooter : true,
		cmTemplate: { sortable: false },
		onCellSelect : function(rowid, index, contents, event) {
			if(flag == 1){
				gridCellSelect1(grid_id, rowid, index, contents, event);
			}else if(flag == 2){
				gridCellSelect2(grid_id, rowid, index, contents, event);
			}else if(flag == 3){
				gridCellSelect3(grid_id, rowid, index, contents, event);
			}else if(flag == 4){
				gridCellSelect4(grid_id, rowid, index, contents, event);
			}else if(flag == 5){
				gridCellSelect5(grid_id, rowid, index, contents, event);
			}else if(flag == 6){
				gridCellSelect6(grid_id, rowid, index, contents, event);
			}else if(flag == 7){
				gridCellSelect7(grid_id, rowid, index, contents, event);
			}else if(flag == 8){
				gridCellSelect8(grid_id, rowid, index, contents, event);
			}else if(flag == 9){
				gridCellSelect9(grid_id, rowid, index, contents, event);
			}else if(flag == 10){
				gridCellSelect10(grid_id, rowid, index, contents, event);
			}else{
				
			}
		},
		gridComplete : function() {
			if(flag == 1){
				gridComplete1(grid_id);
			}else if(flag == 2){
				gridComplete2(grid_id);
			}else if(flag == 3){
				gridComplete3(grid_id);
			}else if(flag == 4){
				gridComplete4(grid_id);
			}else if(flag == 5){
				gridComplete5(grid_id);
			}else if(flag == 6){
				gridComplete6(grid_id);
			}else if(flag == 7){
				gridComplete7(grid_id);
			}else if(flag == 8){
				gridComplete8(grid_id);
			}else if(flag == 9){
				gridComplete9(grid_id);
			}else if(flag == 10){
				gridComplete10(grid_id);
			}else{
				
			}
		},
		loadComplete : function() {
			if(flag == 1){
				loadComplete1(grid_id);
			}else if(flag == 2){
				loadComplete2(grid_id);
			}else if(flag == 3){
				loadComplete3(grid_id);
			}else if(flag == 4){
				loadComplete4(grid_id);
			}else if(flag == 5){
				loadComplete5(grid_id);
			}else if(flag == 6){
				loadComplete6(grid_id);
			}else if(flag == 7){
				loadComplete7(grid_id);
			}else if(flag == 8){
				loadComplete8(grid_id);
			}else if(flag == 9){
				loadComplete9(grid_id);
			}else if(flag == 10){
				loadComplete10(grid_id);
			}else{
				
			}
		}
	});
}

function datepicker_set(name){
	$('input[name="'+name+'"]').datepicker({
		 format: "yyyy-mm-dd",
		 language: "kr",
		 autoclose: true
	});
	$('input[name="'+name+'"]').prop('maxlength','10');
    $('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		var dateVal = '';
		if(number.length < 5){
			dateVal = number;
		}else if(number.length < 7){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
		}else if(number.length < 8){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
			dateVal += "-";
			dateVal += number.substr(6);
		}else if(number.length >= 8){
			dateVal = val;
		}
		$(this).val(dateVal);
    });
}

function datepicker_current_set(name){
	$('input[name="'+name+'"]').datepicker({
		 format: "yyyy-mm-dd",
		 language: "kr",
		 autoclose: true
	});
	//초기값을 오늘 날짜로 설정
    $('input[name="'+name+'"]').datepicker('setDate', 'today');
    $('input[name="'+name+'"]').prop('maxlength','10');
    $('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		var dateVal = '';
		if(number.length < 5){
			dateVal = number;
		}else if(number.length < 7){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
		}else if(number.length < 8){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
			dateVal += "-";
			dateVal += number.substr(6);
		}else if(number.length >= 8){
			dateVal = val;
		}
		$(this).val(dateVal);
    });
}

function datepicker_month_set(name){
	$('input[name="'+name+'"]').datepicker({
		format : 'yyyy-mm',
		viewMode : "months",
		minViewMode : "months",
		language: 'kr',
		autoclose: true
	});
	
	$('input[name="'+name+'"]').prop('maxlength','7');
    $('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		var dateVal = '';
		if(number.length < 5){
			dateVal = number;
		}else if(number.length < 7){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
		}else if(number.length >= 7){
			dateVal = val;
		}
		$(this).val(dateVal);
    });
}

function datepicker_month_current_set(name){
	$('input[name="'+name+'"]').datepicker({
		format : 'yyyy-mm',
		viewMode : "months",
		minViewMode : "months",
		language: 'kr',
		autoclose: true
	});
	//초기값을 오늘 날짜로 설정
    $('input[name="'+name+'"]').datepicker('setDate', 'today');
    $('input[name="'+name+'"]').prop('maxlength','7');
    $('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		var dateVal = '';
		if(number.length < 5){
			dateVal = number;
		}else if(number.length < 7){
			dateVal += number.substr(0, 4);
			dateVal += "-";
			dateVal += number.substr(4, 2);
		}else if(number.length >= 7){
			dateVal = val;
		}
		$(this).val(dateVal);
    });
}

function datepicker_year_set(name){
	$('input[name="'+name+'"]').datepicker({
		format : 'yyyy',
		viewMode : "years",
		minViewMode : "years",
		language: 'kr',
		autoclose: true
	});
	$('input[name="'+name+'"]').prop('maxlength','4');
	$('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		$(this).val(number);
    });
}

function datepicker_year_current_set(name){
	$('input[name="'+name+'"]').datepicker({
		format : 'yyyy',
		viewMode : "years",
		minViewMode : "years",
		language: 'kr',
		autoclose: true
	});
	//초기값을 오늘 날짜로 설정
    $('input[name="'+name+'"]').datepicker('setDate', 'today');
    $('input[name="'+name+'"]').prop('maxlength','4');
	$('input[name="'+name+'"]').on('propertychange change keyup paste input',function(){
    	var val = String($(this).val());
		var number = val.replace(/[^0-9]/g, "");
		$(this).val(number);
    });
}

//excel 관련
function checkFileExcelType(file_path) {
    var fileFormat = file_path.split(".");
    if (fileFormat.indexOf("xlsx") > -1) {
        return true;
    } else if (fileFormat.indexOf("xls") > -1) {
    	return true;
	}else {
        return false;
    }
}

function excelUpload(file_id){
	$('#'+file_id).click();
}

function changeExcelValue(file_id, form_id){
	var file = $("#"+file_id).val();
    if (file == "" || file == null) {
    	alertBox("파일을 선택해주세요.");
        return false;
    } else if (!checkFileExcelType(file)) {
        alertBox("엑셀 파일만 업로드 가능합니다.");
        return false;
    }
    
    
    $.confirm({
		title: '',
		theme: 'modern',
		content: '업로드 하시겠습니까 ?',
		buttons: {
			확인: {
				btnClass: 'btn-blue',
				action: function () {
					var options = {
			            success : function(data) {
			                alertBox("모든 데이터가 업로드 되었습니다.");
			                
			                $("#excelFile").val("");
			                //console.log("excelFile clear");
			                reloadGrid();
			            },
			            type : "POST"
			        };
			        $("#"+form_id).ajaxSubmit(options);
				}
			},
			취소: {
				btnClass: 'btn-red',
				action: function () {
					$("#excelFile").val("");
					//console.log("excelFile clear");
				}
			}
		}
	});
}

//인쇄옵션
function printOption(){
	$(".print_wrap").slideToggle();
}

//항목표시 체크박스
function execCbItem(cb_item_val, grid_col_val, grid_id){	
    var checked = $('input[name="'+cb_item_val+'"]').prop('checked');  // checked 상태 (true, false)
    
    if(typeof(grid_col_val) == "string"){ //type 확인 
    	var gridColValArr = [grid_col_val];
    }else{
    	var gridColValArr = grid_col_val;
    }
    
    if(checked){
    	$("#"+grid_id).jqGrid("showCol", gridColValArr);
    }else{
    	$("#"+grid_id).jqGrid("hideCol", gridColValArr);
	}
}

//동호검색
function execDongHoSearch(dong_id, ho_id, grid_id){
	var dongNum = $("#"+dong_id).val();
	var hoNum = $("#"+ho_id).val();
	
	if(!dongNum){
		alertBox("동을 입력해주세요");
		return false;
	}
	
	if(!hoNum){
		alertBox("호를 입력해주세요");
		return false;
	}
		
	var ids = $("#"+grid_id).jqGrid('getDataIDs');
	
	for (var i = 0; i < ids.length; i++) {
		var ret = $("#"+grid_id).jqGrid('getRowData', ids[i]);
		if (ret.dong == dongNum && ret.ho == hoNum) {
			$("#"+grid_id).setSelection(ids[i]);
			var list = $("#"+grid_id).getRowData(ids[i]);
			dongHoSearchFunc(grid_id, ids[i]);
			return false;
		}
		if(i == ids.length - 1){
			alertBox("일치하는 동/호가 없습니다");
		}
	}
}

//동호 셋팅
function execDongHoSet(company_id, dong_id1, dong_id2, ho_id1, ho_id2){
	//시작동호 종료동호 검색영역에 셋팅
	$.ajax({
        type : "post", 
        async: false,
        url : getContextPath() + "/dongHoSet", 
        data : {
        	"companyId" : company_id
        },                                                                                                                                   
        dataType :"json",   
        success : function(data){
        	//console.log("[execDongHoSet] data : ", data);
        	
        	if(data.list.length < 1){
        		$("#"+dong_id1).val("");
            	$("#"+dong_id2).val("");
            	$("#"+ho_id1).val("");
            	$("#"+ho_id2).val("");
        	} else {
        		$("#"+dong_id1).val(data.list[0].dongFr);
            	$("#"+dong_id2).val(data.list[0].dongTo);
            	$("#"+ho_id1).val(data.list[0].hoFr);
            	$("#"+ho_id2).val(data.list[0].hoTo);    
            	
            	//console.log("dong_id1 : " + dong_id1);
            	//console.log("data.list[0].dongFr : " +data.list[0].dongFr);
        	}
        },
        error : function(){
        }
    });
}

// 인쇄
function execPrint(con_id){
	/*document.getElementById(con_id).contentWindow;*/
	/*document.getElementById(con_id).contentWindow.print();*/
	var contentWindow = document.getElementById(con_id).contentWindow;
    var result = contentWindow.document.execCommand('print', false, null);
    if(!result)  contentWindow.print();
}

//콤마제거
function removeComma(n) {  
    if ( typeof n == "undefined" || n == null || n == "" ) {
        return "";
    }
    var txtNumber = '' + n;
    return txtNumber.replace(/(,)/g, "");
}

//금액 포맷터
function priceFormatter(cellValue, options, rowdata, action){
	var cellVal;
	
	if(cellValue != null && cellValue != undefined && cellValue.length != ""){
		//쓸데없는 소수점 제거
		var tmp = cellValue + "";
		
		if(tmp.indexOf(".") != -1){
			cellValue = parseFloat(cellValue).toFixed(5);
			cellValue = cellValue.replace(/(0+$)/, "");
		}
		
		//콤마처리
		cellVal = cellValue.toString().split(".");
		
		return cellVal[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (cellVal[1] ? "." + cellVal[1] : "");
	}
	return "";
}

//floatComma에서 사용되는 콤마함수 ( .도 필요하기때문에 )
function commaFormatter(cellValue, options, rowdata, action){
	var cellVal;
	var returnVal;
	
	if(cellValue != null && cellValue != undefined && cellValue.length != ""){
		cellVal = cellValue.toString().split(".");
		
		if(cellValue.charAt(cellValue.length-1) == "."){
			returnVal = cellVal[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ".";
		}else{
			returnVal = cellVal[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (cellVal[1] ? "." + cellVal[1] : "");
		}
		
		return returnVal;
	}
	return "";
}

//한글제거
//function delHangle(evt){
//	var objTarget = evt.srcElement || evt.target;
//	var _value = event.srcElement.value;
//	if(/[r-하)
//}

//자리수제한
function numberDigitKey(evt, intNum, decimalNum) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    // input text value    
    var _value = removeComma(event.srcElement.value);    

    // 소수점(.)이 두번 이상 나오지 못하게
    var _patternDot = /^\d*[.]\d*$/; // 현재 value값에 소수점(.) 이 있으면 . 입력불가
    if (_patternDot.test(_value)) {
        if (charCode == 46) {
            return false;
        }
    }
    
    //정수 자리수 체크
    var _patternInt = /^\d{0}$/; 
    if(intNum == 1){
    	_patternInt = /^\d{1}$/;  
    }else if(intNum == 2){
    	_patternInt = /^\d{2}$/; 
    }else if(intNum == 3){
    	_patternInt = /^\d{3}$/; 
    }else if(intNum == 4){
    	_patternInt = /^\d{4}$/; 
    }else if(intNum == 5){
    	_patternInt = /^\d{5}$/; 
    }else if(intNum == 6){
    	_patternInt = /^\d{6}$/; 
    }else if(intNum == 7){
    	_patternInt = /^\d{7}$/; 
    }else if(intNum == 8){
    	_patternInt = /^\d{8}$/; 
    }else{
    	_patternInt = /^\d{9}$/; 
    }
    
    if (_patternInt.test(_value)) {
        if (charCode != 46) {
            return false;
        }
    }
    
    // 소수점 자리수 체크
    var _patternDecimal = /^\d*[.]\d{0}$/;
    if(decimalNum == 1){
    	_patternDecimal = /^\d*[.]\d{1}$/;
    }else if(decimalNum == 2){
    	_patternDecimal = /^\d*[.]\d{2}$/;
    }else if(decimalNum == 3){
    	_patternDecimal = /^\d*[.]\d{3}$/;
    }else if(decimalNum == 4){
    	_patternDecimal = /^\d*[.]\d{4}$/;
    }else if(decimalNum == 5){
    	_patternDecimal = /^\d*[.]\d{5}$/;
    }else if(decimalNum == 6){
    	_patternDecimal = /^\d*[.]\d{6}$/;
    }else if(decimalNum == 7){
    	_patternDecimal = /^\d*[.]\d{7}$/;
    }else if(decimalNum == 8){
    	_patternDecimal = /^\d*[.]\d{8}$/;
    }else{
    	_patternDecimal = /^\d*[.]\d{9}$/;
    }
    
    if (_patternDecimal.test(_value)) {
        return false;
    }  
    return true;
}

//2개 value 덧셈 소수점보정 및 소수점 뒷자리 쓸데없는 0제거
function decimalCalc(numberA, numberB){
	if(numberA == "" || numberA == null){
		numberA = 0;
	}
	if(numberB == "" || numberB == null){
		numberB = 0;
	}
	
	var usingToFixed = parseFloat(numberA)+parseFloat(numberB);
	//console.log("numberA : " + numberA + ", numberB : " + numberB + ", usingToFixed : " + usingToFixed);
	
	var tmp = usingToFixed + "";
	if(tmp.indexOf(".") != -1){
		usingToFixed = parseFloat(usingToFixed).toFixed(5);
		usingToFixed = usingToFixed.replace(/(0+$)/, "");
	}
	return usingToFixed ;
}

//1개 value 덧셈 소수점보정 및 소수점 뒷자리 쓸데없는 0제거
function decimalSingleCalc(numberA){
	var usingToFixed = parseFloat(numberA).toFixed(5);

	var tmp = usingToFixed + "";
	if(tmp.indexOf(".") != -1){
		usingToFixed = parseFloat(usingToFixed).toFixed(5);
		usingToFixed = usingToFixed.replace(/(0+$)/, "");

	}
	return usingToFixed ;
}

//우편번호 주소 가져오기
function execDaumPostcode(zipcode, address) {
	var element_layer = document.getElementById('layer');
	
	new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("sample2_extraAddress").value = extraAddr;
            
            } else {
                //document.getElementById("sample2_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('#'+zipcode).length > 0 ? $('#'+zipcode).val(data.zonecode) : $('input[type=text][name='+zipcode+']').val(data.zonecode);
            $('#'+address).length > 0 ? $('#'+address).val(addr) : $('input[type=text][name='+address+']').val(addr);
            // 커서를 주소 필드로 이동한다.
            setTimeout(function() {
            	$('#'+address).length > 0 ? $('#'+address).focus() : $('input[type=text][name='+address+']').focus();
			}, 10);
            $("#zipcode_popup").popup('hide');
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';
    element_layer.style.width = '500px';
    element_layer.style.height = '500px';
    
    $("#zipcode_popup").popup('show');
}

//사업자등록번호 체크 기존
//function checkCorporateRegistrationNumber(value) {
//    var valueMap = value.replace(/-/gi, '').split('').map(function(item) {
//        return parseInt(item, 10);
//    });
//    
//    if (valueMap.length === 10) {
//    	var multiply = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5);
//        var checkSum = 0;
//
//        for (var i = 0; i < multiply.length; ++i) {
//            checkSum += multiply[i] * valueMap[i];
//        }
//        
//        checkSum += parseInt((multiply[8] * valueMap[8]) / 10, 10);
//        return Math.floor(valueMap[9]) === (10 - (checkSum % 10));
//    }
//    return false;
//}

//사업자등록번호 체크 변경
function checkCorporateRegistrationNumber(Num){
	Num = String(Num).split('-').join('');
	Num = $.trim(Num);
	baroVal = 0;
	comStr = "13713713";

	for( i = 0 ; i < 8 ; i++ ) {
		baroVal = baroVal + (parseFloat(Num.substring(i,i+1))*parseFloat(comStr.substring(i,i+1))) % 10;
	}

	tmpComp = parseFloat(Num.substring(8,9)) * 5 + "0";
	chkValue = parseFloat(tmpComp.substring(0,1)) + parseFloat(tmpComp.substring(1,2));
	chkDigit = (10-(baroVal+chkValue) % 10 ) % 10;

	if( Num.substring(9,10) != chkDigit ) {
		if( Num == "1328302034" || Num == "1298301331" || Num == "6198300414" || Num == "2128177509" || Num == "1278301558" ) {
			return true;
		} else {
			return false;
		}
	} else {
		return true;
	}
}

//이메일 정규표현식 체크
function checkEmail(value) {        
    var email = value;
    var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if(exptext.test(email)==false){
            //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우            
            return false;
        };
        return true;
};

//자리수계산 
function calcMath(mode, bill, pow) {
	/*
	 * 연산기준 자릿수 만들기 위하여 -4처리함
	 * -3:그대로, -2:십단위, -1:원단위, 0:소수점 1자리
	 * 1:소수점 2자리, 2:소수점 3자리, 3:소수점 4자리, 4:소수점 5자리 처리, 
	 */
	pow = pow - 4;
	if (mode == 4) { // 반올림
		if (pow < 0 && pow > -3) {
			pow = -(pow);
			bill = Math.round(bill / Math.pow(10, pow)) * Math.pow(10, pow);
		} else {
			bill = Math.round(bill * Math.pow(10, pow)) / Math.pow(10, pow);
		}
	} else if (mode == 2) { // 절상
		if (pow < 0 && pow > -3) {
			pow = -(pow);
			bill = Math.ceil(bill / Math.pow(10, pow)) * Math.pow(10, pow);
		} else {
			bill = Math.ceil(bill * Math.pow(10, pow)) / Math.pow(10, pow);
		}
	} else if (mode == 3) { // 절하
		if (pow < 0 && pow > -3) {
			pow = -(pow);
			bill = Math.floor(bill / Math.pow(10, pow)) * Math.pow(10, pow);
		} else {
			bill = Math.floor(bill * Math.pow(10, pow)) / Math.pow(10, pow);
		}
	} else { //그대로
		return Math.round((bill)*1e7) / 1e7; //double형태여서 .0이 들어가므로 정수형으로 포맷함
	}

	return Math.round((bill)*1e7) / 1e7;
}

