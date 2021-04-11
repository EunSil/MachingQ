<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//request.setCharacterEncoding("euc-kr");
	@SuppressWarnings("unchecked") 
	String classId = (String) request.getParameter("classId");
	String curUserId = (String) request.getParameter("curUserId");
	System.out.println("받은 클래스아이디: " + classId);
	if(classId == "")
		classId = "0";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>새강의평쓰기</title>
<script>
function userLogout(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function myPage(targetUri) {
	form1.action = targetUri;
	form1.submit();
}
function goBack(targetUri) {
	form.action = targetUri;
	return false;
}
function check() {
	alert("후기를 작성합니다.");
	form.submit();
}
</script>
</head>
<style>
#search {padding: 20px;}
h6 {padding:10px;}
li {padding: 8px 16px;}
textarea {width:300px; height:200px;}
ul {list-style-type:none;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">새강의평쓰기</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form1" method="post" action="<c:url value='/user/logout' />">
				<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${param.curUserId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">&nbsp;
				<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
				<c:param name='stuId' value='${param.curUserId}'/></c:url>')" />
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
    	<form name="form" class="blog-pagination" method="post" action="<c:url value='/review/register'> </c:url>">
			<div class="table-responsive">
		 		<table class="table table-shopping">
		 			<tr>
					  <td>
					  	<ul>
					  	<li>
							과제
							<select name="dproject">
								<option value="많음" selected>많음</option>
								<option value="보통">보통</option>
								<option value="없음">없음</option> 
							</select>
						</li>
						<li>
							팀플
							<select name="dteamproject">
								<option value="많음" selected>많음</option>
								<option value="보통">보통</option>
								<option value="없음">없음</option> 
							</select>
						</li>
						<li>
							학점비율
							<select name="dcreditRate">
								<option value="많음" selected>많음</option>
								<option value="보통">보통</option>
								<option value="없음">없음</option> 
							</select>
						</li>
						<li>
							출결
							<select name="dAttendance">
								<option value="혼용" selected>혼용</option>
								<option value="직접호명">직접호명</option>
								<option value="지정좌석">지정좌석</option>
								<option value="전자출결">전자출결 </option>
								<option value="반영안함">반영안함</option>
							</select>
						</li>
						<li>
							시험횟수
							<select name="dnumberOfExam">
								<option value="네번이상" selected>네번이상</option>
								<option value="세번">세번</option>
								<option value="두번">두번</option> 
								<option value="한번">한번 </option>
								<option value="없음">없음</option>
							</select>
						</li>
						<li>
							총점
							<select name="lecturetotal">
								<option value="1" selected>1</option>
								<option value="2">2</option>
								<option value="3">3</option> 
								<option value="4">4 </option>
								<option value="5">5</option>
							</select>
						</li>
						<li style="display:none">
							수강학기
							<select name="semester" >
								<option value="1" selected>1학기</option>
								<option value="2">2학기</option>
							</select>
						</li>
						<li>
							총평<br/><br/>
							<textarea name="totalReview"></textarea>
							<select name="classId" style="display:none">
								<option value='${param.classId}'>${param.classId}</option> 
							</select>
						</li>
						<li></li>
						<li><button class="btn btn-outline-primary" value="취소" onClick="goBack'<c:url value='/review/list' />')">취소하기</button>&nbsp;&nbsp;<button class="btn btn-outline-primary" value="작성">작성하기</button></li>
						</ul>
					</td>
					</tr>
				</table>
				</div>
			</form>
		</main>
	</div>
	</div>
</body>
</html>