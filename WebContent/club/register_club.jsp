<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>동아리 등록 페이지</title>
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
function clubCreate() {
	if (form.club_name.value == "") {
		alert("클럽 이름을 입력하십시오.");
		form.club_name.focus();
		return false;
	} 
	if (form.president_code.value == "") {
		alert("부장 학번을 입력하십시오.");
		form.president_code.focus();
		return false;
	}	
	if (form.president_phone.value == "") {
		alert("부장 연락처를 입력하십시오.");
		form.president_phone.focus();
		return false;
	} 
	if (form.professor_code.value == "") {
		alert("교수 코드를 입력하십시오.");
		form.professor_code.focus();
		return false;
	}	
	if (form.professor_phone.value == "") {
		alert("교수 연락처를 입력하십시오.");
		form.professor_phone.focus();
		return false;
	} 
	if (form.club_place.value == "") {
		alert("모임 장소를 입력하십시오.");
		form.club_place.focus();
		return false;
	}	
	if (form.club_time.value == "") {
		alert("모임 시간을 입력하십시오.");
		form.club_time.focus();
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
	if (form.category.value == "") {
		alert("카테고리 체크하십시오.");
		return false;
	}
	if (form.description.value == "") {
		alert("설명을 입력하십시오.");
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
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">동아리 등록</h4>
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
      	<form name="form" class="blog-pagination" method="POST" action="<c:url value='/club/register' />">
      		<table class="table table-shopping">
	    	<tr height="40">
				<td width="150" align="center" bgcolor="#f1f1f1">동아리 아이디</td>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<input type="text" style="width: 240" name="club_code" >
				</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">동아리 이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="club_name" >
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">부장 학번</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="president_code" >
			</td>
		  </tr>	 
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">부장 연락처</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="president_phone" >
			</td>
		  </tr>
		  	  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">교수 코드</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="professor_code" >
			</td>
		  </tr>	 
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">교수 연락처</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="professor_phone" >
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">동아리 장소</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="club_place" >
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">모임 시간</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="club_time" >
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">활동 반경</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="activity" value="onCampus" checked>교내활동
				<input type="radio" name="activity" value="outCampus">대외활동
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">활동 목적</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="purpose" value="hobby" checked>취미
				<input type="radio" name="purpose" value="spec">스펙
			</td>
		  </tr>
		  <tr>
			<td width="150" align="center" bgcolor="#f1f1f1">분야</td>
			<td width="340" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="category" value="cook" checked>베이킹, 요리
				<input type="radio" name="category" value="programing">프로그래밍
				<input type="radio" name="category" value="language">언어, 외국어
				<input type="radio" name="category" value="culture">문화, 예술
				<input type="radio" name="category" value="economy">경제
				<input type="radio" name="category" value="health">생활, 건강
				<input type="radio" name="category" value="etc">기타
			</td>
			</tr>	  
		  <tr height="40">
			<td width="150" align="center" bgcolor="#f1f1f1">설명</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<textarea size="100" rows="10" cols="50" name="description"></textarea>
			</td>
			</tr>
			<tr>
			<td colspan="2" align="right">
			<input type="button" class="btn btn-outline-primary" value="생성" onClick="clubCreate()"> &nbsp;
			<input type="button" class="btn btn-outline-primary" value="동아리 목록" onClick="clubList('<c:url value='/club/list' />')">
			</td>
		  </tr>
		  </table>
		  </form>
		  </div>
		 </div>
	</main>
</div>
</div>
</form>
</body>
</html>