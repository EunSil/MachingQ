<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>스터디 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/tutorunity.css' />" type="text/css">
<style>
.tutorTable {
  color: blue;
}
.tutorHead {
  width: 120px;
  height: 22px;
  text-align: center;
  background: "E6ECDE";  
}
.tutorTable {
  background: YellowGreen;
}
.tutorCell {
  width: 470px;
  text-align: left;
  padding-left: 10px;
  background: "FFFFFF";  
}
#search {padding: 20px;}
h6 {padding:10px;}
li {padding: 8px 16px;}
</style>
<script>
function tutorRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
function apply() {
	return confirm("승인하시겠습니까?");
}
function deny() {
	return confirm("거절하시겠습니까?");		
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">스터디 관리</h4>
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
	<table class="table table-shopping">  
		  <tr>
			<td class="tutorHead">튜터 학번</td>
			<td colspan="2" class="tutorCell">
				${tutor.student_number}
			</td>
		  </tr>
		  <tr>
			<td class="tutorHead">과목</td>
			<td colspan="2" class="tutorCell">
				${tutor.subject} 
			</td>
		  </tr>		  	  
		  <tr>
		  <tr>
			<td class="tutorHead">해당 과목 성적</td>
			<td colspan="2" class="tutorCell">
				${tutor.grade} 
			</td>
		  </tr>
		   <tr rowspan = "20">
			<td class="tutorHead">승인된 튜티 목록</td>
			<td class="tutorCell">
				<table>
				  <c:forEach var="tutee" items="${tuteeList}">
				  <tr>
				  <td>
					  	${tutee.student_number} 
				  </td>
				  </tr>
	  			</c:forEach>
	  			</table>
	  		</td>
		  </tr>
		   <tr rowspan = "20">
			<td class="tutorHead">승인되지 않은 튜티 목록</td>
			<td class="tutorCell">
			<table>
			<c:forEach var="tutee2" items="${tuteeList2}">
			<tr>
					<td>			  	
					  	${tutee2.student_number}
					</td>
					 <td>
						<a href="<c:url value='/study/apply'>
					    <c:param name='tuteeId' value='${tutee2.student_number}'/>
					    <c:param name='student_number' value='${tutor.student_number}'/>
					    <c:param name='curUserId' value='${curUserId}'/>
				 	    </c:url>" onclick="return apply();">승인</a>
				 	    <a href="<c:url value='/study/deny'>
					    <c:param name='tuteeId' value='${tutee2.student_number}'/>
					    <c:param name='student_number' value='${tutor.student_number}'/>
					    <c:param name='curUserId' value='${curUserId}'/>
				 	    </c:url>" onclick="return deny();">거절</a>
					</td>
				</tr>
	  			</c:forEach>
	  			</table>
			</td>
		  </tr>
	 	</table>
	    <br>
	    <a class="btn btn-outline-primary" href="<c:url value='/study/tutor/update/form'>
	     		   <c:param name='stuId' value='${tutor.student_number}'/>
	     		   <c:param name='curUserId' value='${curUserId}'/>
			 	 </c:url>">수정</a> &nbsp;
 	    <a class="btn btn-outline-primary" href="<c:url value='/study/tutor/delete'>
				   <c:param name='student_number' value='${tutor.student_number}'/>
			 	 </c:url>" onclick="return tutorRemove();">삭제</a> &nbsp;
 	    <a class="btn btn-outline-primary" href="<c:url value='/study/list' />">튜터 목록</a>
 	    <br><br>	   
 	    
 	    <!-- 수정이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	    </div>
	    </main>
</div>
</div> 
</body>
</html>