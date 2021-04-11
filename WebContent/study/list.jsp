<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<script>
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
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">현재 튜터 목록</h4>
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
			<td align="center">튜터 학번</td>
			<td align="center">과목</td>
			<td align="center">학점</td>
			</tr>
			  <c:forEach var="tutor" items="${tutorList}">  			  	
			  		<tr>
					  <td width="190" align="center" bgcolor="#f1f1f1" height="20">
					  <a href="<c:url value='/study/view'>
								   <c:param name='student_number' value='${tutor.student_number}'/>
								   <c:param name='curUserId' value='${curUserId}'/>
						 		 </c:url>">		
					  	${tutor.student_number}</a>      <%-- <%=user.getUserId()%> --%>
					  </td>
					  <td width="200" align="center" bgcolor="#f1f1f1" style="padding-left: 10">
						${tutor.subject}
					  </td>
					  <td width="200" align="center" bgcolor="#f1f1f1" style="padding-left: 10">
					  	${tutor.grade}
					  </td>
					</tr>
				</c:forEach>
			<tr>
				<td>
					<a class="btn btn-outline-primary" href="<c:url value='register_tutor.jsp'><c:param name='curUserId' value='${curUserId}'/></c:url>">튜터 등록</a>&nbsp;
					<a class="btn btn-outline-primary" href="<c:url value='register_tutee.jsp'><c:param name='curUserId' value='${curUserId}'/></c:url>">튜티 등록</a>
				</td>
			</tr>
			</table>
			</div>
			</div>
			</main>
</div>
</div>
</body>
</html>