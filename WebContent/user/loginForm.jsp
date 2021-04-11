<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<script>
function login() {
	if (form.stuId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.stuId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<style>
.login {width: 410px; position: absolute;  left: 37%; top: 25%; transform: tramslate(-50%, -50%);}
.login h3 { padding:0 0 20px; text-align:center; line-hegiht: 1; border-bottom: solid 2px;}
ul { list-style:none;}
.login ul {padding: 20px 0 33px;}
.login li {padding: 0 0 12px;}
.login li input {height: 46px;}
</style>
<body>
<form name="form" method="post" action="<c:url value='/user/login'/>">
	<section class="login">
	<h3>로그인</h3>
	<ul>
		<li><input type="text" name="stuId" class="form-control" placeholder="ID" required autofocus></li>
		<li><input type="password" name="password" class="form-control" placeholder="password" required></li>
		<li><input type="button" value="Login" class="btn btn-lg btn-info btn-block" onClick="login()"></li>
		<li><button class="btn btn-lg btn-info btn-block" onClick="userCreate('<c:url value='/user/register/form' />')">회원 가입</button></li>
		<li style="text-align:center">&copy; naibulmunyeong</li>
		<li><br><font color="red"><c:out value="${exception.getMessage()}" /></font><br></li>
	</ul>
	</section>
</form>
</body>
</html>
