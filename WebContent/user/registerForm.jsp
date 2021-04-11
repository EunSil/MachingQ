<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/bootstrap.css' />">
<title>회원가입</title>
<script>
function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
function check() {
	if (form.stuId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.stuId.focus();
		from.action="#"
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.password2.focus();
		return false;
	}
	if (form.stuName.value == "") {
		alert("이름을 입력하십시오.");
		form.stuName.focus();
		return false;
	}
	if (form.grade.value == "") {
		alert("학년을 입력하십시오.");
		return false;
	}
	if (form.dProject.value == "") {
		alert("과제를 입력하십시오.");
		return false;
	}
	if (form.dTeamProject.value == "") {
		alert("팀플을 입력하십시오.");
		return false;
	}
	if (form.dCreditRate.value == "") {
		alert("학점비율을 입력하십시오.");
		return false;
	}
	if (form.dAttendance.value == "") {
		alert("출결을 입력하십시오.");
		return false;
	}
	if (form.dNumberOfExam.value == "") {
		alert("시험횟수를 입력하십시오.");
		return false;
	}
	form.submit();
}
</script>
</head>
<style>
.member {width: 500px; position: absolute;  left: 35%; top: 5%; transform: tramslate(-50%, -50%);}
.member h3 {padding:0 0 10px; text-align:center;}
.member li {padding: 0 0 12px;}
.member div {display: inline-block;}
.member #a input {height: 46px;}
ul {list-style:none;}
#btn {width: 49%;}
p {font-size:13px; font-weight:bold}
</style>
<body>
<form name="form" method="post" action="<c:url value='/user/register' />">
	<section class="member">
	<h3>회원가입</h3>
	<ul>
		<li id="a"><p>아이디</p><input type="text" class="form-control" placeholder="아이디  입력" name="stuId" required></li>
		<li id="a"><p>비밀번호</p><input type="password" class="form-control" placeholder="비밀번호" name="password" required></li>
		<li id="a"><p>비밀번호 재확인</p><input type="password" class="form-control" placeholder="비밀번호 재입력" name="password2" required></li>
		<li id="a"><p>이름</p><input type="text" class="form-control" placeholder="이름을 입력해주세요." name="stuName" required></li>
		<li><p>학년</p>
		<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="1" value="1" name="grade" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="1">1학년</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="2" value="2" name="grade" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="2">2학년</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="3" value="3" name="grade" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="3">3학년</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="4" value="4" name="grade" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="4">4학년</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="5" value="5" name="grade" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="5">5학년</label>
          </div>
        </div>
		</li>
		<li><p>과제</p>
		<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="많음" value="많음" name="dProject" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="많음">많음</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="보통" value="보통" name="dProject" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="보통">보통</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="없음" value="없음" name="dProject" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="없음">없음</label>
          </div>
        </div>
		</li>
		<li><p>팀플</p>
		<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="많음1" value="많음" name="dTeamProject" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="많음1">많음</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="보통1" value="보통" name="dTeamProject" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="보통1">보통</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="없음1" value="없음" name="dTeamProject" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="없음1">없음</label>
          </div>
        </div>
		</li>
		<li><p>학점비율</p>
				<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="많음2" value="많음" name="dCreditRate" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="많음2">많음</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="보통2" value="보통" name="dCreditRate" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="보통2">보통</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="없음2" value="없음" name="dCreditRate" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="없음2">없음</label>
          </div>
        </div>
		</li>
		<li><p>출결</p>
		<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="혼용" value="혼용" name="dAttendance" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="혼용">혼용</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="직접호명" value="직접호명" name="dAttendance" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="직접호명">직접호명</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="지정좌석" value="지정좌석" name="dAttendance" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="지정좌석">지정좌석</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="전자출결" value="전자출결" name="dAttendance" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="전자출결">전자출결</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="반영안함" value="반영안함" name="dAttendance" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="반영안함">반영안함</label>
          </div>
         </div>
		</li>
		<li><p>시험횟수</p>
		<div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="네번이상" value="네번이상" name="dNumberOfExam" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="네번이상">네번이상</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="세번" value="세번" name="dNumberOfExam" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="세번">세번</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="두번" value="두번" name="dNumberOfExam" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="두번">지정좌석</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="한번" value="한번" name="dNumberOfExam" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="한번">한번</label>&nbsp;
          </div>
          <div class="custom-control custom-radio">
            <input id="없음3" value="없음" name="dNumberOfExam" type="radio" class="custom-control-input" required>
            <label class="custom-control-label" for="없음3">없음</label>
          </div>
         </div>
		</li>
		<li>
		<input type="button" id="btn" value="취소" class="btn btn-lg btn-info" onClick="userCreate('<c:url value='/user/login/form' />')"> <input type="button" id="btn" value="가입하기" class="btn btn-lg btn-info" onClick="check()">
		</li>
	</ul>
	</section>
</form>
</body>
</html>