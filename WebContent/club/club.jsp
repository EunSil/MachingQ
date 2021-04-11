<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>동아리 찾기</title>
</head>
<style>
ul {list-style-type:none; margin: 0; padding:0;width:200px; background-color:#f1f1f1;}
li a {display: block; color: #000; padding: 8px 16px; text-decoration: none;}
li a.active {background-color: #000000; color: white; fond-weight:bold;}
li a:hover:not(.active) {background-color: #555; color: white; }
#t {position: absolute;left: 30%; top: 5%; transform: tramslate(-50%, -50%);}
.main {width: 1040px; padding: 10px;}
#search {padding: 40px;}
#search input {box-sizing: border-box;}
</style>
<body>
<table>
<tr>
<td>
<div id="header">
	<nav id="topMenu">
		<ul id="navi">
			<li><a href="<c:url value='/review/list' />">Home</a></li>
			<li><a href="<c:url value='/review/list' />">수업추천</a></li>
			<li><a href="<c:url value='/teamProject/list' />">팀플찾기</a></li>
			<li><a class="active" href="../club/club.jsp">동아리</a></li>
			<li><a href="../study/tutor_tutee.jsp">멘토멘티</a></li>
		</ul>
	</nav>
</div>
</td>
<td id="t">
<div class="main">
<h2>동아리 찾기</h2>
</div>
<table>
<tr>
	<FORM>
	<input type = 'BUTTON' name = 'register_mentor' 
	value = '동아리 등록' ONCLICK="location.href='register_club.jsp' ">
	<br>
	<br>
	<input type = 'BUTTON' name = "register_mentee" 
	value = "매칭" ONCLICK="location.href='matching_club.jsp' ">
	<br>
</FORM>		
</tr>
</table>
</td>
</tr>
</table>
</body>
</html>