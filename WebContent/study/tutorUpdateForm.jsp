<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>커뮤니티 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<script>
function tutorModify() {
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
</style>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">튜터 수정</h4>
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
	<form name="form" method="POST" action="<c:url value='/study/tutor/update' />">
	<section class="tutor">
	  <input type="hidden" name="stuId" value="${tutor.student_number}"/>	  
	  <table class="table table-shopping">  
			  <tr height="40">
				<td width="150" align="center" bgcolor="E6ECDE">과목</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="text" style="width: 240" name="subject" value="${tutor.subject}">
				</td>
			  </tr>
			  <tr height="40">
				<td width="150" align="center" bgcolor="E6ECDE">해당 과목 성적</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="text" style="width: 240" name="grade" value="${tutor.grade}">
				</td>
			  </tr>
			  <tr height="40">
				<td width="150" align="center" bgcolor="E6ECDE">학년</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="text" style="width: 240" name="year" value="${tutor.year}">
				</td>
			  </tr>
		    </table>
		    <br>	  
		    <table style="width: 100%">
			  <tr>
				<td align="left">
				<input type="button" class="btn btn-outline-primary" value="수정" onClick="tutorModify()"> &nbsp;
				<input type="button" class="btn btn-outline-primary" value="목록" onClick="tutorList('<c:url value='/study/list' />')">
				</td>
			  </tr>
		    </table>
	 </section>  
</form>
</div>
</main>
</div>
</div>
</body>
</html>