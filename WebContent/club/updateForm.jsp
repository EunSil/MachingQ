<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<script>
function clubModify() {
	if (form.club_name.value == "") {
		alert("이름을 입력하십시오.");
		form.club_name.focus();
		return false;
	} 
	if (form.description.value == "") {
		alert("설명을 입력하십시오.");
		form.description.focus();
		return false;
	}	
	form.submit();
}

function clubList(targetUri) {
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
        <h4 class="blog-header-logo text-dark">동아리 정보 수정</h4>
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
		  <form name="form" class="blog-pagination" method="POST" action="<c:url value='/club/update' />">
		  	<input type="hidden" name="clubId" value="${club.club_code}"/>
      		<table class="table table-shopping">
		<!-- Update Form  -->
			<tr>
			  <td width="20"></td>
			  <td>
			    <table>
				  <tr>
					<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>동아리 관리 - 수정</b>&nbsp;&nbsp;</td>
				  </tr>
			    </table>  
			    <br>	  
			    <table style="background-color: YellowGreen">
			  	  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">동아리 ID</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="club_code" value="${club.club_code}" readonly>
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">동아리 이름</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="club_name" value="${club.club_name}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">부장 학번</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="president_code" value="${club.president_code}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">부장 연락처</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="president_phone" value="${club.president_phone}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">교수 코드</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="professor_code" value="${club.professor_code}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">교수 연락처</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="professor_phone" value="${club.professor_phone}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">동아리 장소</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="club_place" value="${club.club_place}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">모임 시간</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="text" style="width: 240" name="club_time" value="${club.club_time}">
					</td>
				  </tr>
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">활동 반경</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="radio" name="activity" value="onCampus" checked>교내활동
						<input type="radio" name="activity" value="outCampus">대외활동
					</td>
				  </tr>	
				  <tr height="40">
					<td width="150" align="center" bgcolor="E6ECDE">활동 목적</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<input type="radio" name="purpose" value="hobby" checked>취미
						<input type="radio" name="purpose" value="spec">스펙
					</td>
				  </tr>	
				  <tr height="40">
					<td width="150" align="center" bgcolor="#f1f1f1">설명</td>
					<td width="250" bgcolor="ffffff" style="padding-left: 10">
						<textarea size="100" rows="10" cols="50" name="description">
						"${club.description}"
						</textarea>
					</td>
					</tr>
			    </table>
			    <br>	  
			    <table style="width: 100%">
				  <tr>
					<td align="left">
					<input class="btn btn-outline-primary" type="button" value="수정" onClick="clubModify()"> &nbsp;
					<input class="btn btn-outline-primary" type="button" value="목록" onClick="clubList('<c:url value='/club/list' />')">
					</td>
				  </tr>
			    </table>
	  </td>
	</tr>
  </table>  
</form>
</div>
</div>
</main>
</div>
</div>
</body>
</html>