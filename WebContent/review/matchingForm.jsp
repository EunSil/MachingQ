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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>수업스타일</title>
<script>
function userLogout(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function myPage(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
function userModify() {
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
ul {list-style:none;}
#search {padding: 20px;}
h6 {padding:10px;}
li {padding: 8px 16px;}
.member p {font-size:13px; font-weight:bold}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">내가 원하는 수업 스타일은?</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${curUserId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${curUserId}'/></c:url>')" />
			</form>
		  </nav>
      </div>
    </div>
  </header>
<div class="container-fluid">
  <div class="row">
    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
      <div class="sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active p-2 text-muted"  href="<c:url value='/review/list ' />">
              HOME
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-2 text-muted" href="<c:url value='/review/list' />">
            	수업추천
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-2 text-muted" href="<c:url value='/teamProject/list' />">
              	팀플찾기
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-2 text-muted" href="<c:url value='/club/list' />">
              	동아리 추천
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-2 text-muted" href="<c:url value='/study/list' />">
              	튜터튜티
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<form name="form" method="post" class="blog-pagination" action="<c:url value='/review/match' />">
		<section class="member">
		<ul>
			<li>
			<%-- <div class="d-block my-3">
	          <div class="custom-control custom-radio">
	          	<c:forEach var="cnt" begin="1" end="5">
					<input type="radio" name="grade" class="custom-control-input" id="${cnt}" value="${cnt}"
					<c:if test="${cnt eq user.getGrade()}"> checked="checked" </c:if> required>
					<label class="custom-control-label" for="${cnt}">${cnt}학년</label>
				</c:forEach>
	          </div>
	        </div> --%>
	        </li>
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
			<p>키워드</p>
				<select name="dKeyword">
					<option value=1 selected>빡센</option>
					<option value=2>교재 없는</option>
					<option value=3>재미있는</option>
					<option value=4>암기 </option>
					<option value=5>남는게 많은</option>
					<option value=6>열정적인</option>
					<option value=7>친절한</option>
					<option value=8>널널한</option>
				</select>
			</li>
			<li>
				<input type="button" class="btn btn-outline-primary" value="추천" onClick="userModify()"> &nbsp;
				<input type="button" class="btn btn-outline-primary" value="취소" onClick="userCreate('<c:url value='/review/list' />')">
			</li>
		</ul>
		</section>
	</form>
	</main>
</div>
</div>
</body>
</html>