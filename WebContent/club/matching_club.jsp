<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>Insert title here</title>
<script>
function userLogout(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function myPage(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function matchingResult() {
	
	var isCategoryCheck = false;
	var arr_category = document.getElementsByName("category");
	
	for (var i = 0; i < arr_category.length; i++) {
		if(arr_category[i].checked == true) {
			isCategoryCheck = true;
			break;
		}
	}
	
	if (!isCategoryCheck) {
		alert("분야를 체크하십시오.");
		return false;
	} 
	if (form.activity.value == "") {
		alert("활동 반경을 체크하십시오.");
		return false;
	}
	if (form.purpose.value == "") {
		alert("활동 목적을 체크하십시오.");
		return false;
	}
	form.submit();
}

function clubList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<style>
#search {padding: 20px;}
h6 {padding:10px;}
li {padding: 8px 16px;}
textarea {width:300px; height:200px;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">매칭 결과</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${stuId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${stuId}'/></c:url>')" />
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
      	<div class="table-responsive" id="checkbox">
      	<form name="form" class="blog-pagination" method="POST" action="<c:url value='/club/matching' />">
      		<table class="table table-shopping">
			<tr>
				<td width="150" align="center" bgcolor="E6ECDE">활동 반경</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="radio" name="activity" value="onCampus" checked>교내활동
					<input type="radio" name="activity" value="outCampus">대외활동
				</td>
				</tr>
				<tr>
				<td width="150" align="center" bgcolor="E6ECDE">활동 목적</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="radio" name="purpose" value="hobby" checked>취미
					<input type="radio" name="purpose" value="spec">스펙
				</td>
				</tr>
				<tr>
				<td width="150" align="center" bgcolor="E6ECDE">분야</td>
				<td width="340" bgcolor="ffffff" style="padding-left: 10">
					<input type="checkbox" name="category" value="cook" checked>베이킹, 요리
					<input type="checkbox" name="category" value="programing">프로그래밍
					<input type="checkbox" name="category" value="language">언어, 외국어
					<input type="checkbox" name="category" value="culture">문화, 예술
					<input type="checkbox" name="category" value="economy">경제
					<input type="checkbox" name="category" value="health">생활, 건강
					<input type="checkbox" name="category" value="etc">기타
				</td>
			</tr>
			<tr>
					<td colspan="2" align="left">
					<input type="button" class="btn btn-outline-primary" value="매칭하기" onClick="matchingResult()"> &nbsp;
					<input type="button" class="btn btn-outline-primary" value="커뮤니티 목록" onClick="clubList('<c:url value='/club/list' />')">
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