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
function teamProjectAdd() {
	if (form.year.value == "") {
		alert("과목의 수강연도를 입력하세요");
		form.year.focus();
		return false;
	} 
	if (form.teamMemberCnt.value == "") {
		alert("팀프로젝트를 함께할 학생의 수를 입력하세요.");
		form.teamMemberCnt.focus();
		return false;
	}

	var chkbox = document.getElementsByName('day'); 
	var chk = false; 
	for(var i=0 ; i<chkbox.length ; i++) {
		if(chkbox[i].checked) { 
			chk = true; 
			break;	
		} 
	}
	if(!chk){
		alert("모임요일을 선택하세요.");
		return false;
	}

	chkbox = document.getElementsByName('place'); 
	chk = false; 
	for(var i=0 ; i<chkbox.length ; i++) {
		if(chkbox[i].checked) { 
			chk = true; 
			break;	
		} 
	}
	if(!chk){
		alert("모임장소를 선택하세요.");
		return false;
	}
	
	form.submit();
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
#checkbox div {display: inline-block;}
#search {padding: 20px;}
li {padding: 8px 16px;}
#content td {width:600px;}
ul {list-style:none;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">팀플 등록</h4>
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
      	<div class="table-responsive" id="checkbox">
      	<form name="form" class="blog-pagination" method="POST" action="<c:url value='/teamProject/create' />">
		  <table class="table table-shopping">
		  <tr>
		  <td>
		  	<ul>
				<li>
					<c:if test="${creationFailed}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>
				</li>
				<li>과목명 : ${classRemove.courseTitle}</li>
				<li>교수명 : ${classRemove.profName}</li>
				<li>분반
					<select name="division">
						<option value="0" selected>1</option>
						<option value="1">2</option>
					</select>
				</li>
				<li>년도<input type="number" name="year" id="i" class="form-control w-50"></li>
				<li>학기
					<select name="semester">
						<option value="0" selected>1학기</option>
						<option value="1">2학기</option>
					</select>
				</li>
				<li>
					모임요일<br>
					<div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="월" value="월" name="day" checked required>
			          <label class="custom-control-label" for="월">월</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="화" value="화" name="day" required>
			          <label class="custom-control-label" for="화">화</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="수" value="수" name="day" required>
			          <label class="custom-control-label" for="수">수</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="목" value="목" name="day" required>
			          <label class="custom-control-label" for="목">목</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="금" value="금" name="day" required>
			          <label class="custom-control-label" for="금">금</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="토" value="토" name="day" required>
			          <label class="custom-control-label" for="토">토</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="일" value="일" name="day" required>
			          <label class="custom-control-label" for="일">일</label>
			        </div>
				</li>
				<li>
					모임장소<br>
					<div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="학교" value="학교" name="place" checked required>
			          <label class="custom-control-label" for="학교">학교</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="추후협의" value="추후협의" name="place" required>
			          <label class="custom-control-label" for="추후협의">추후협의</label>
			        </div>
				</li>
				<li>
					키워드
					<select name="keyword">
						<option value="시간약속" selected>시간약속</option>
						<option value="제출">제출</option>
						<option value="A+">A+</option>
						<option value="온라인미팅">온라인미팅</option>
						<option value="오프라인미팅">오프라인미팅</option>
					</select>
				</li>
				<li>모집인원<input type="number" name="teamMemberCnt" id="i" class="form-control w-50"></li>
				<li><input type="button" class="btn btn-outline-primary" value="등록" onClick="teamProjectAdd()" id="i"></li>
			</ul>
				<input type="hidden" name="courseId" value="${classRemove.courseId}">
				<input type="hidden" name="profId" value="${classRemove.profId}">
				<input type="hidden" name="courseTitle" value="${classRemove.courseTitle}">
				<input type="hidden" name="profName" value="${classRemove.profName}">
			</td>
		  </tr>
		  </table>
		  </form>
		 </div>
		</div>
	</main>
</body>
</html>