<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

$(function(){
	

	
});

</script>

<body>

 <div class="container">
  <h2>메인페이지</h2>
  <p>박주임 테스트 페이지</p>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th>항목</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="scheduleList.do">연간스케쥴 현황</a></td>
      </tr>
      <tr>
        <td><a href="detailedWorkList.do">세부업무 실적</a></td>
      </tr>
      <tr>
        <td><a href="repairList.do">하자보수현황</a></td>
      </tr>
      <tr>
        <td><a href="#">주요계약현황</a></td>
      </tr>
      <tr>
        <td><a href="#">설비공구수불현황</a></td>
      </tr>
      <tr>
        <td><a href="#">승강기안전점검표</a></td>
      </tr>
      <tr>
        <td><a href="reportList.do">화제예방</a></td> 
      </tr>
      <tr>
        <td><a href="#">교육현황</a></td>
      </tr>
      <tr>
        <td><a href="#">회의록</a></td>
      </tr>
      <tr>
        <td>개발 중</td>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>