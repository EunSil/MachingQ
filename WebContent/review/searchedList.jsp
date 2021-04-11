<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	@SuppressWarnings("unchecked") 
	List<ClassDTO> classList = (List<ClassDTO>)request.getAttribute("classList");
%>
<!DOCTYPE html>
<html>
<head>
<script>
function search() {
	if (form2.searchKeyword.value == "") {
		alert("검색어를 입력하십시오.");
		form2.searchKeyword.focus();
		return false;
	} 	
	form2.submit(); // 아 form에서 review/search로 바로 안넘어가도록 어떻게 하지
}
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>후기게시판</title>
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
        <h4 class="blog-header-logo text-dark">후기 게시판</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form" method="post" action="<c:url value='/user/logout' />">
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
      	<div id="search">
			<form class="form-inline mt-2 mt-md-0" name="form2" method="post" action="<c:url value='/review/search' />">
			    <input name = "searchKeyword" class="form-control mr-sm-2 w-90" type="text" placeholder="과목명 검색" aria-label="Search">
			    <input type="button" class="btn btn-outline-success my-2 my-sm" onClick="search()" value="Search">
			</form>
		</div>
	  </div>
	  <div class="table-responsive">
	  <h6>검색 결과</h6>
	  <table class="table table-shopping">
	  <thead>
	  <tr>
	  	<th class="text-center" width="190" height="20">과목명</th>
		<th class="text-center" width="200" height="20">교수명</th>
		<th class="text-center" width="200" height="20">개설학기</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="classDTO" items="${classList}">  			  	
  		<tr>
		  <td width="200" align="center" bgcolor="ffffff" style="padding-left: 10">
			 <a href="<c:url value='/review/view'> 
 					  	<c:param name='courseTitle' value='${classDTO.courseTitle}' /> 
 					  	<c:param name='profName' value='${classDTO.profName}' /> 
 					  	<c:param name='classId' value='${classDTO.classId}' /> 
 			 		 </c:url>"> 
			  ${classDTO.courseTitle} </a>	  
		  </td>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	${classDTO.profName}      
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${classDTO.year}-${classDTO.semester}
		  </td>
		</tr>
	  </c:forEach> 
	  </tbody>
	  </table>
	  </div>
	</main>
</div>
</div>
</body>
</html>
