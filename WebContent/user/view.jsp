<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	StudentDTO user = (StudentDTO)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>사용자 관리</title>
<script>
function userLogout(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function myPage(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<style>
.member{ position: absolute;  left: 5%; top: 5%;}
.member h2, td {padding:0 0 10px; text-align:center;}
li {padding: 8px 16px;}
#t {position: absolute;  left: 22%;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">회원 정보</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;<%=user.getStuId()%>&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${user.getStuId()}'/></c:url>')" />
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
    <section class="member">
    <div class="table-responsive">
	<table class="table table-shopping">
	<tr>
		<td width="450"><b>아이디</b></td>
		<td width="470"><%=user.getStuId()%></td>
	</tr>
	<tr>
		<td width="450"><b>이름</b></td>
		<td width="470">&nbsp;<%=user.getStuName() %></td>
	</tr>
	<tr>
		<td width="450"><b>학년</b>&nbsp;</td>
		<td width="470"><%=user.getGrade() + "학년"%></td>
	</tr>
	<tr>
		<td width="450"><b>과제</b>&nbsp;</td>
		<td width="470"><%=user.getdProject()%></td>
	</tr>
	<tr>
		<td width="450"><b>팀플</b>&nbsp;</td>
		<td width="470"><%=user.getdTeamProject()%></td>
	</tr>
	<tr>
		<td width="450"><b>학점비율</b>&nbsp;</td>
		<td width="470"><%=user.getdCreditRate()%></td>
	</tr>
	<tr>
		<td width="450"><b>출결</b>&nbsp;</td>
		<td width="470"><%=user.getdAttendance()%></td>
	</tr>
	<tr>
		<td width="450"><b>시험횟수</b>&nbsp;</td>
		<td width="470"><%=user.getdNumberOfExam()%></td>
	</tr>
	</table>
	<br>
		<table>
			<tr>
				<td align=center width="500" id="t">
				<a class="btn btn-outline-primary" href="<c:url value='/user/update/form'>
	     		   <c:param name='stuId' value='<%=user.getStuId()%>'/>
			 	 </c:url>">수정</a> &nbsp;
				<a class="btn btn-outline-primary" href="<c:url value='/review/list' />">취소</a>
				</td>
			</tr>
		</table>
		</div>
</section>
</main>
</div>
</div>
</body>
</html>