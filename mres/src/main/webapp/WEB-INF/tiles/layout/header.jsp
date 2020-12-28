<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
function page_alert(){
	alertBox("페이지 준비중입니다.");
}

var color = '${userColor}';
if(color != ''){
	color_change(color);
}

</script>

<div class="con_wrap con_wrap_100 header_info_wrap">
	<div class="header_date">
		<p class="header_time">2019년 01월 01일 11:35:26</p>
	</div>
	<div class="header_user">
		<i class="fa fa-user"></i>
		<span>${userName}</span>
	</div>
</div>
<nav class="header_menu">
	<div id="logo" onclick="javascript:location.href='<%=request.getContextPath()%>/main'">
		<p class="logo_icon"><img src="<%=request.getContextPath()%>/resources/img/logo_icon.png" /></p>
		<p class="logo_txt"><img src="<%=request.getContextPath()%>/resources/img/logo_txt.png" /></p>
		<p class="logo_des">관리비 관리 시스템</p>
	</div>
	
	<div class="header_logout" onclick="javascript:location.href='<%=request.getContextPath()%>/logout'">
		<i class="fa fa-power-off"></i>
	</div>
	
	<ul class="menu">
		<li>
			<a href="#">연간관리데이터보고서</a>
			<ul> 
				<li><a href="<%=request.getContextPath()%>/scheduleList">연간스케쥴</a></li>
				<li><a href="<%=request.getContextPath()%>/detailedWorkList">세부업무실적</a></li>
				<li><a href="<%=request.getContextPath()%>/repairList">하자보수현황</a></li>
				<li><a href="<%=request.getContextPath()%>/contractList">주요계약현황</a></li>
				<li><a href="<%=request.getContextPath()%>/paymentStatusList">설비및수불현황</a></li>
				<li><a href="<%=request.getContextPath()%>/liftList">시설물점검현황</a></li>
				<li><a href="<%=request.getContextPath()%>/trainingList">교육현황</a></li>
				<li><a href="<%=request.getContextPath()%>/meetingLogList">관리단회의록</a></li>
			</ul>
		</li>
		<li>  
			<a href="#">참조퍼블</a>
			<ul> 
				<li><a href="<%=request.getContextPath()%>/mres0103">mres0103</a></li>
				<li><a href="<%=request.getContextPath()%>/mres0201">mres0201</a></li>
				<li><a href="<%=request.getContextPath()%>/mres0206">mres0206</a></li>
				<li><a href="<%=request.getContextPath()%>/mres0602">mres0602</a></li>
				<li><a href="<%=request.getContextPath()%>/mres0707">mres0707</a></li>
			</ul>
		</li>
	</ul>
</nav>