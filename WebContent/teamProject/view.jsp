<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>팀프로젝트 모집 게시판</title>
<script>
function teamProjectCreate(targetUri) {
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
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">팀프로젝트 모집 게시판</h4>
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
		  	<th class="text-center" width="100" height="20">과목명</th>
		  	<td class="text-description">${classRemove.courseTitle}</td>
		  </tr>
		  <tr>
			<th class="text-center" width="100" height="20">교수명</th>
			<td class="text-description">${classRemove.profName}</td>
		  </tr>
		  <tr>
			<th class="text-center" width="100" height="20">개설학기</th>
			<td class="text-description">
				<c:forEach var="openSemester" items="${openedSemester}">  
				${openSemester.year}-${openSemester.semester}&nbsp;
				</c:forEach>
			</td>
		  </tr>
		  </table>
		  <form name="form2" method="post" class="blog-pagination">
			<br>
			<input type="button" class="btn btn-outline-primary" value="등록" onClick="teamProjectCreate('<c:url value='/teamProject/create/form' />')">
			<input type="hidden" name="courseId" value="${classRemove.courseId}">
			<input type="hidden" name="profId" value="${classRemove.profId}">
			<input type="hidden" name="courseTitle" value="${classRemove.courseTitle}">
			<input type="hidden" name="profName" value="${classRemove.profName}">
		  </form>
		  <br>
		  <div class="table-responsive">
		  <table class="table table-shopping">
		  	<tr>
		  		<td align="center" bgcolor="E6ECDE">모집글</td>
		  	</tr>
			<c:if test="${teamProList.size() eq 0}">
				<tr>
					<td align="center">모집글이 없습니다.</td>
				</tr>
			</c:if>
	  		<c:forEach var="teamPro" items="${teamProList}">
	  			<c:choose>
					<c:when test="${teamPro.teamMemberCnt ne teamPro.requestMemberCnt}">
					<table id="content">
					</c:when>
					<c:otherwise>
					<table id="content" style="color:#969696">
					</c:otherwise>
				</c:choose>
					<tr>			  	
						<td><b>수강학기</b></td>
						<td> ${teamPro.c.year}년 ${teamPro.c.semester}학기 (0${teamPro.c.division}분반)</td>
					</tr>
						<tr>
							<td><b>신청인원/모집인원</b></td>
							<td>${teamPro.requestMemberCnt}/${teamPro.teamMemberCnt}</td>
						</tr>
						<tr>
							<td width="100" align="center" bgcolor="FFFFFF" colspan="2" style="border-top:1px solid black">
							<c:choose>
								<c:when test="${teamPro.teamMemberCnt ne teamPro.requestMemberCnt}">
		        				<a href="<c:url value='/teamProject/detail/view'>
							   	<c:param name='teamId' value='${teamPro.teamId}'/>
							 	</c:url>">
					 			선택
					 			</a>
						 		</c:when>
								<c:otherwise>모집완료</c:otherwise>
							</c:choose>
							</td>
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