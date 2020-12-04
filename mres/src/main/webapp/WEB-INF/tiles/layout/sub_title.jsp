<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	selOfficeList();
});

function selOfficeList(){
	$("#selOfficeList").find("option").remove();

	var chkOfficeCode = '${selOfficeCompId}';
	$('#selOfficeList').append("<option value='" + '12467' + "' selected='selected'>" + '박주임테스트0009' + "</option>");
	
// 	$.ajax({
// 		async: false,
<%-- 		url : "<%=request.getContextPath()%>/officeList", --%>
// 		method : "get",
// 		type : "json",
// 		contentType : "application/json",
// 		success : function(data) {
			//console.log(data);

			//----------------
			//임시로 사업장명 생성
			//----------------
			
			
			
// 			for(var i=0; i < data.rows.length; i++){
// 				if(chkOfficeCode == '' && i == 0){
// 					$('#selOfficeList').append("<option value='" + data.rows[i]['compCode'] + "' selected='selected'>" + data.rows[i]['name'] + "</option>");
// 				}else{
// 					if(chkOfficeCode == data.rows[i]['compCode']){
// 						$('#selOfficeList').append("<option value='" + data.rows[i]['compCode'] + "' selected='selected'>" + data.rows[i]['name'] + "</option>");
// 					}else{
// 						$('#selOfficeList').append("<option value='" + data.rows[i]['compCode'] + "'>" + data.rows[i]['name'] + "</option>");	
// 					}
// 				}
// 				if(i == 0){
//						$('#selOfficeList').append("<option value='" + data.rows[i]['compCode'] + "' selected='selected'>" + data.rows[i]['name'] + "</option>");	
// 				}else{
// 					$('#selOfficeList').append("<option value='" + data.rows[i]['compCode'] + "'>" + data.rows[i]['name'] + "</option>");	
// 				}
// 			}
// 		}
// 	});
}
</script>

<div class="sub_title_wrap con_wrap con_wrap_100">
	<div data-icon="∑" class="sub_title left"></div>
	
	<div class="sub_office_list_wrap left">
		<div class="left">
			<select class="default_select" id="selOfficeList" name="selOfficeList" onchange="office_change(this.value)">
			</select>
		</div>
	</div>
</div>