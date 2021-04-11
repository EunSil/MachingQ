<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	StudentDTO user = (StudentDTO)request.getAttribute("user");
%>
<%!
	String[] list1 = {"많음", "보통", "없음"};
	String[] list2 = {"혼용", "직접호명", "지정좌석", "전자출결", "반영안함"};
	String[] list3 = {"네번이상", "세번", "두번", "한번", "없음"};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<script>
function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
function userModify() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.password2.focus();
		return false;
	}
	if (form.stuName.value == "") {
		alert("이름을 입력하십시오.");
		form.stuName.focus();
		return false;
	}
	if (form.grade.value == "") {
		alert("학년을 입력하십시오.");
		return false;
	}
	if (form.dProject.value == "") {
		alert("과제를 입력하십시오.");
		return false;
	}
	if (form.dTeamProject.value == "") {
		alert("팀플을 입력하십시오.");
		return false;
	}
	if (form.dCreditRate.value == "") {
		alert("학점비율을 입력하십시오.");
		return false;
	}
	if (form.dAttendance.value == "") {
		alert("출결을 입력하십시오.");
		return false;
	}
	if (form.dNumberOfExam.value == "") {
		alert("시험횟수를 입력하십시오.");
		return false;
	}
	form.submit();
}
</script>
</head>
<style>
.member {width: 500px; position: absolute;  left: 35%; top: 5%; transform: tramslate(-50%, -50%);}
.member h3 {padding:0 0 10px; text-align:center;}
.member li {padding: 0 0 12px;}
.member #a input {width:100%; height: 46px; box-sizing: border-box;}
ul { list-style:none;}
#b {width: 49%;}
p {font-size:13px; font-weight:bold}
</style>
<body>
<form name="form" method="post" action="<c:url value='/user/update' />">
	<section class="member">
	<h3>회원정보수정</h3>
	<ul>
		<li id="a"><p>아이디</p><input type="text"  name="stuId" class="form-control" value="<%=user.getStuId()%>" readonly></li>
		<li id="a"><p>비밀번호</p><input type="password" class="form-control" value="<%=user.getPassword()%>" name="password" required></li>
		<li id="a"><p>비밀번호 재확인</p><input type="password" class="form-control" value="<%=user.getPassword()%>" name="password2" required></li>
		<li id="a"><p>이름</p><input type="text" class="form-control" value="<%=user.getStuName()%>" name="stuName" required></li>
		<li><p>학년</p>
			<c:forEach var="cnt" begin="1" end="5">
			<input type="radio" name="grade" value="${cnt}"
				<c:if test="${cnt eq user.getGrade()}"> checked="checked" </c:if>>${cnt}학년
			</c:forEach>
		</li>
		<li><p>과제</p>
			<c:forEach var="item" items="<%=list1%>">
			<input type="radio" name="dProject" value="${item}"
				<c:if test="${item eq user.getdProject()}">checked="checked"</c:if>/>${item}
			</c:forEach>
		</li>
		<li><p>팀플</p>
			<c:forEach var="item" items="<%=list1%>">
			<input type="radio" name="dTeamProject" value="${item}"
				<c:if test="${item eq user.getdTeamProject()}">checked="checked"</c:if>/>${item}
			</c:forEach>
		</li>
		<li><p>학점비율</p>
			<c:forEach var="item" items="<%=list1%>">
			<input type="radio" name="dCreditRate" value="${item}"
				<c:if test="${item eq user.getdCreditRate()}">checked="checked"</c:if>/>${item}
			</c:forEach>
		</li>
		<li><p>출결</p>
			<c:forEach var="item" items="<%=list2%>">
			<input type="radio" name="dAttendance" value="${item}"
				<c:if test="${item eq user.getdAttendance()}">checked="checked"</c:if>/>${item}
			</c:forEach>
		</li>
		<li><p>시험횟수</p>
			<c:forEach var="item" items="<%=list3%>">
			<input type="radio" name="dNumberOfExam" value="${item}"
				<c:if test="${item eq user.getdNumberOfExam()}">checked="checked"</c:if>/>${item}
			</c:forEach>
		</li>
		<li>
			<input id="b" type="button" class="btn btn-lg btn-info" value="취소" onClick="userCreate('<c:url value='/review/list' />')">&nbsp;
			<input id="b" type="button" class="btn btn-lg btn-info" value="수정" onClick="userModify()">
		</li>
	</ul>
	</section>
</form>
</body>
</html>