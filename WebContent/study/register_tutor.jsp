<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String curUserId = (String) request.getParameter("curUserId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>멘토 관리</title>
<script>
function tutorCreate() {
	if (form.subject.value == "") {
		alert("과목을 입력하십시오.");
		form.subject.focus();
		return false;
	}	
	if (form.grade.value == "") {
		alert("성적을 입력하십시오.");
		form.grade.focus();
		return false;
	}	
	if (form.year.value == "") {
		alert("학년을 입력하십시오.");
		form.year.focus();
		return false;
	}	
	form.submit();
}

function tutorList(targetUri) {
	form.action = targetUri;
	form.submit();
}
function userLogout(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function myPage(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
</script>
</head>
<style>
#search {padding: 20px;}
h6 {padding:10px;}
li {padding: 8px 16px;}
#r div {display: inline-block;}
</style>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">튜터 등록</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${param.curUserId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${param.curUserId}'/></c:url>')" />
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
            <a class="nav-link p-2 text-muted"  href="<c:url value='/review/list ' />">
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
	<div class="table-responsive">
	<form name="form" method="POST" action="<c:url value='/study/tutor/register' />">
	  <table class="table table-shopping">
		    <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">과목</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="subject">
			</td>
		  </tr>
		    <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">해당 과목 성적</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="grade" >
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">학년</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="year" >
			</td>
		  </tr>	 
</table>
  <table style="width: 100%">
    <tr>
      <td width="20"></td>
	  <td>	  	   
	    <br>
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" class="btn btn-outline-primary" value="등록" onClick="tutorCreate()"> &nbsp;
			</td>
		  </tr>
	    </table>
	  </td>
    </tr>
  </table>  
</form>
</div>
</main>
</div>
</div>
</body>
</html>