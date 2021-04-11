<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	@SuppressWarnings("unchecked") 
	ClassDTO choosedClass = (ClassDTO)request.getAttribute("choosedClass");
	@SuppressWarnings("unchecked") 
	List<Review> reviewList = (List<Review>)request.getAttribute("reviewList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>후기게시판</title>
<script>
function userReview(targetUri) {
	form2.action = targetUri;
	form2.submit();
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
#content td {width:600px;}
th {width:200px;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">후기 게시판</h4>
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
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap pt-3 pb-2 mb-3">
		<div class="table-responsive">
		  <table class="table table-shopping">
		  <tr>
			<th class="text-center" width="100" height="20"><b>교과목</b></td>
			<td class="text-description">${choosedClass.courseTitle}</td>
		  </tr>
		  <tr>
			<th class="text-center" width="100" height="20"><b>교수명</b></td>
			<td class="text-description">${choosedClass.profName}</td>
		  </tr>
		 <tr>
			<th class="text-center" width="100" height="20"><b>수강학기</b></td>
			<td class="text-description">${choosedClass.year}년 ${choosedClass.semester}학기</td>
		 </tr>
		 </table>
		 <form name="form2" method="post" action="<c:url value='/review/register'/>" class="blog-pagination">
			<div align="center"><br><input type="button" class="btn btn-outline-primary" value="강의평 등록" onClick="userReview('<c:url value='/review/register/form'><c:param name='classId' value='${choosedClass.classId}'/>
			<c:param name='curUserId' value='${curUserId}'/> 
		 		</c:url>')">
			<br>
			</div>
		 </form>
		 <br>
		 <div class="table-responsive">
		 <table class="table table-shopping">
		 	<tr>
				<td width = "500" align="center" bgcolor="E6ECDE" height="22">강의평</td>
			</tr>
			<c:forEach var="review" items="${reviewList}">  		
			<hr>	  	
		  		<table class="table table-shopping" id="content">
					<tr>
						<th align="center">강의평::</th>
						<td align="center"></td>
					</tr>
					<tr>
						<th>과제</b></th>
						<td>${review.project}</td>
					</tr>
					<tr>
						<th>조모임</th>
						<td>${review.teamProject}</td>
					</tr>
					<tr>
						<th>학점비율</th>
						<td>${review.creditRate}</td>
					</tr>
					<tr>
						<th>총점</th>
						<td>${review.lectureTotal}</td>
					</tr>
					<tr>
						<th>시험 횟수</th>
						<td>${review.numberOfExam}</td>
					</tr>
					<tr>
						<th>총평</th>
						<td>${review.lectureReview} </td>
					</tr>
				</table>
			  </c:forEach> 
		</table>
</div>
</div>
</div>
</main>
</div>
</div>
</body>
</html>