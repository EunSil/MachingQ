<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String curUserId = (String) request.getParameter("curUserId");
%>
<html>
<head>
<title>동아리 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<link rel=stylesheet href="<c:url value='/css/clubunity.css' />" type="text/css">
<style>
.clubTable {
  color: blue;
}
.clubHead {
  width: 120px;
  height: 22px;
  text-align: center;
  background: "E6ECDE";  
}
.clubTable {
  background: YellowGreen;
}
.clubCell {
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
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">동아리 정보 보기</h4>
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
			<td class="clubHead">동아리 이름</td>
			<td class="clubCell">
				${club.club_name}
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">부장 학번</td>
			<td class="clubCell">
				${club.president_code} 
			</td>
		  </tr>		  	  
		  <tr>
		  <tr>
			<td class="clubHead">부장 연락처</td>
			<td class="clubCell">
				${club.president_phone} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">담당 교수</td>
			<td class="clubCell">
				${club.professor_code} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">교수 연락처</td>
			<td class="clubCell">
				${club.professor_phone} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">주 활동 장소</td>
			<td class="clubCell">
				${club.club_place} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">활동 시간</td>
			<td class="clubCell">
				${club.club_time} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">활동 반경</td>
			<td class="clubCell">
				${club.activity} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">활동 목적</td>
			<td class="clubCell">
				${club.purpose} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">분야</td>
			<td class="clubCell">
				${club.category} 
			</td>
		  </tr>
		  <tr>
			<td class="clubHead">설명</td>
			<td class="clubCell">
				${club.description} 
			</td>
		  </tr>  
	 	</table>
	    <br>
	    <a class="btn btn-outline-primary" href="<c:url value='/club/update/form'>
	     		   <c:param name='club_code' value='${club.club_code}'/>
	     		   <c:param name='president_code' value='${club.president_code}'/>
	     		   <c:param name='stuId' value='${curUserId}'/>
			 	 </c:url>">수정</a> &nbsp;
			 	 
 	    <a class="btn btn-outline-primary" href="<c:url value='/club/delete'>
				   <c:param name='club_code' value='${club.club_code}'/>
				   <c:param name='president_code' value='${club.president_code}'/>
			 	 </c:url>" onclick="return clubRemove();">삭제</a> &nbsp;
			 	 
 	    <a class="btn btn-outline-primary" href="<c:url value='/club/list' />">동아리 목록</a>&nbsp;&nbsp;
 	    
	  	<a class="btn btn-outline-primary" href="<c:url value='/club/applyList'>
	  	<c:param name='club_code' value='${club.club_code}'/>
	  	<c:param name='curUserId' value='${curUserId}'/>
	  	</c:url>">신청 현황</a>
 	    <br><br>	   
 	    
 	    <!-- 수정이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	    </div>
	    </div>
	    </main>
</div>
</div>  
</body>
</html>