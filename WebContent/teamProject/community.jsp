<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>커뮤니티</title>
<script>
function commentAdd() {
	if (form.comment_content.value == "") {
		alert("댓글에 내용을 입력해주세요");
		form.comment_content.focus();
		return false;
	}
	form.submit();
}

function teamProjectDelete(isOwner){
	if (confirm("정말 " + isOwner + "하시겠습니까?\n") == true){    //확인
	    form2.submit();
	}else{   
	    return;
	}
}

function deleteError(){
	alert("${exception.getMessage()}");
}

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
#btn a {text-decoration: none;}
/* #t {position: absolute;left: 30%; top: 5%; transform: tramslate(-50%, -50%);} */
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">커뮤니티</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form3" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${curUserId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${curUserId}'/></c:url>')" />
			</form>
			<br>
			<c:if test="${deleteFailed}">
				 <script> deleteError() </script>
			</c:if>
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
	  <div class="table-responsive">
	  <table id="content" class="table table-shopping">
	  <tbody>
	  	<tr>
			<td align="center"><b>교과목</b></td>
			<td>${teamPro.c.courseTitle}</td>
		</tr>
		<tr>
			<td align="center"><b>교수명</b></td>
			<td>${teamPro.c.profName}</td>
		</tr>
		<tr>
			<td align="center"><b>수강학기</b></td>
			<td>${teamPro.c.year}-${teamPro.c.semester}(0${teamPro.c.division}분반)</td>
		</tr>
		<tr>
			<td align="center"><b>개설자</b></td>
			<td>
			${teamProMatList[0].s.stuName}(${teamProMatList[0].s.grade}학년)
			</td>
		</tr>
		<tr>
			<td align="center"><b>팀원</b></td>
			<td>
			<c:forEach var="teamProMat" items="${teamProMatList}">
			${teamProMat.s.stuName}(${teamProMat.s.grade}학년)&nbsp;
			</c:forEach>
			</td>
		</tr>
	  </tbody>
	  </table>
	  <br>
	  <div class="table-responsive">
	  <form method="POST" name="form" action="<c:url value='/teamProject/comment/create' />" class="blog-pagination">
	  <table id="content" class="table table-shopping">
	  <tr>
	  	<td width = "500" align="center" bgcolor="E6ECDE" height="22">소통</td>
	  </tr>
	  <tr>
		<td>
			<div class="text_wrapper" style="background-color:#f1f1f1">
			<c:set var="day" value="0" />
			<c:forEach var="comm" items="${teamCommList}">
			<ul>
				<c:if test="${day ne comm.commentDate}">
				<c:set var="day" value="${comm.commentDate}" />
				<li style="width:550px; text-align:center;">${comm.commentDate}</li>
				</c:if>
				<li style="width:550px">${comm.s.stuName} : ${comm.comments}</li>
			</ul>
			</c:forEach>
			</div>
		</td>
	 </tr>
	 <tr>
		<td width="550">
		<div class="text_wrapper">
		<textarea name="comment_content" rows="4" cols="110"></textarea>
		</div>
		</td>
		<td width="100">
		<div id="btn">
		<input type="hidden" name="teamId" value="${teamId}">
		<p><input type="button" class="btn btn-outline-primary" value="등록" onClick="commentAdd()"></p>
		</div>
		</td>
		</tr>
		</table>
	 </form>
	 </div>
	 </div>
	 <div style="margin-top:30px; margin-left:30px">
		<form method="POST" name="form2" action="<c:url value='/teamProject/delete' />">
			<input type="hidden" name="teamId" value="${teamId}">
			<input type="hidden" name="myId" value="${stuId}">
			<input type="hidden" name="openId" value="${teamProMatList[0].s.stuId}">
		</form>
		<c:choose>
			<c:when test="${stuId eq teamProMatList[0].s.stuId}">
				<input type="button" class="btn btn-outline-primary" value="폐쇄" onClick="teamProjectDelete('폐쇄')">
			</c:when>
			<c:otherwise>
				<input type="button" class="btn btn-outline-primary" value="탈퇴" onClick="teamProjectDelete('탈퇴')">
			</c:otherwise>
		</c:choose>
	</div>
	 </main>
	 </div>
	 </div>
</body>
</html>