<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동아리 회원 관리</title>
<link rel=stylesheet href="<c:url value='/css/clubunity.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<script>
function clubRemove() {
	return confirm("정말 삭제하시겠습니까?");		
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
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">신청 현황</h4>
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
		   	<tr rowspan = "20">
			<td class="clubHead">회원 목록</td>
			<td class="clubCell"> 
				<table>
				  <c:forEach var="stu" items="${stuList}">
				  <tr>
				  <td>
					  	${stu} 
				  </td>
				  </tr>
	  			</c:forEach>
	  			</table>
	  		</td>
		  </tr>
		   <tr rowspan = "20">
			<td class="clubHead">가입 신청 목록</td>
			<td class="clubCell">
			<table>
			<c:forEach var="stu2" items="${stuList2}">
			<tr>
					<td>			  	
					  	${stu2}
					</td>
					 <td>
						<a class="btn btn-outline-primary" href="<c:url value='/club/apply'>
					    <c:param name='student_number' value='${stu2}'/>
					    <c:param name='club_code' value='${club.club_code}'/>
				 	    </c:url>" onclick="return apply();">승인</a>
				 	    <a class="btn btn-outline-primary" href="<c:url value='/club/deny'>
					    <c:param name='student_number' value='${stu2}'/>
					    <c:param name='club_code' value='${club.club_code}'/>
				 	    </c:url>" onclick="return deny();">거절</a>
					</td>
				</tr>
	  			</c:forEach>
	  			</table>
	  			<br>
	  			<tr>
	  			<td>
 	    <a class="btn btn-outline-primary" href="<c:url value='/club/list' />">동아리 목록</a>
 	    <br><br>	   
 	    </td>
 	    </tr>
 	    <tr>
 	    <td>
 	    <!-- 수정이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
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