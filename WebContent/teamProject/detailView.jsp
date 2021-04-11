<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>팀플등록</title>
<script>
function userLogout(targetUri) {
	form3.action = targetUri;
	form3.submit();
}

function myPage(targetUri) {
	form3.action = targetUri;
	form3.submit();
}
</script>
</head>
<style>
#search {padding: 20px;}
li {padding: 8px 16px;}
ul {list-style:none;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">팀플 게시판</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form3" method="post" action="<c:url value='/user/logout' />">
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
      	<form name="form" class="blog-pagination" method="POST" action="<c:url value='/teamProject/create' />">
		  <table class="table table-shopping">
		  <tr>
		  <td>
		  	<ul>
			  	<li>과목명 : ${teamPro.c.courseTitle}	</li>
				<li>교수명 : ${teamPro.c.profName}</li>
				<li>분반 : ${teamPro.c.division}</li>
				<li>년도 : ${teamPro.c.year}</li>
				<li>학기 : ${teamPro.c.semester}</li>
				<li>신청인원/모집인원 : ${teamPro.requestMemberCnt}/${teamPro.teamMemberCnt}</li>
				<li>모임요일 : ${teamPro.tMeetSchedule}</li>
				<li>모임장소 : ${teamPro.tMeetPlace}</li>
				<li>선호 키워드 : ${teamPro.tKeyword}</li>
				<c:if test="${isJoined}">
		      		<li> 
		      			<a class="btn btn-outline-primary" href="<c:url value='/teamProject/matching/create'>
						<c:param name='teamId' value='${teamPro.teamId}'/>
						</c:url>">
					  	신청
					  	</a>
					</li>
		    	</c:if>
		    	<c:if test="${!isJoined}">
		      		<li style="color:red">이미 가입된 프로젝트입니다.</li>
		    	</c:if>
			</ul>
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