<!-- 멘티 정보에 따른 결과 출력 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>멘토와 멘티 매칭 페이지</title>
<script>
function matchingResult() {  
	if (form.subject.value == "") {
		alert("과목 명을 입력하십시오.");
		form.club_place.focus();
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
textarea {width:300px; height:200px;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">튜터 튜티 매칭</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId'/></c:url>')" />
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
	<form name="form" method="POST" action="<c:url value='/study/matching' />">
	  <table class="table table-shopping">
	  <thead>
	  <tr>
	  	<th class="text-center" width="100" bgcolor="#f1f1f1">과목명</th>
		<td width="250" bgcolor="ffffff" style="padding-left: 10">
			<input type="text" style="width: 240" name="subject">
		</td>
	  </tr>
	  <tr>
	  	<th class="text-center" width="100" bgcolor="#f1f1f1">최소 학점</th>
	  	<td>
	  	<div id="r" class="d-block my-3">
	  		<div class="custom-control custom-radio">
		    	<input id="purpose" value="A" name="purpose" type="radio" class="custom-control-input" checked required>
		        <label class="custom-control-label" for="purpose">A</label>&nbsp;
		    </div>
		    <div class="custom-control custom-radio">
		     	<input id="purpose1" value="A+" name="purpose" type="radio" class="custom-control-input" required>
		        <label class="custom-control-label" for="purpose1">A+</label>&nbsp;
		    </div>
		</div>
		</td>
	  </tr>
	  </thead>
	  </table>
	<table>
		<tr>
			<td>
				<input type="button" class="btn btn-outline-primary" value="매칭하기" onClick="matchingResult()"> &nbsp;
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