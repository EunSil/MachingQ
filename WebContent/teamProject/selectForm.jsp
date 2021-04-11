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
	if (form.year.value == "") {
		alert("과목의 수강연도를 입력하세요");
		form.year.focus();
		return false;
	}
	if (form.year.value == "") {
		alert("과목의 수강연도를 입력하세요");
		form.year.focus();
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
#content {width: 410px; position: absolute;left: 30%; top: 20%; transform: tramslate(-50%, -50%);}
#content h2 {text-align:center;}
#content li {padding: 10px;}
#content font {text-align:center;}
#content2 {width: 410px; position: absolute;left: 60%; top: 20%; transform: tramslate(-50%, -50%);}
ul {list-style:none;}
</style>
<body>
<header class="blog-header py-4">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1"> 
      	<p class="text-info">&nbsp;&nbsp;&nbsp;naibulmunyeong</p>      
      </div>
      <div class="col-4 text-center">
        <h4 class="blog-header-logo text-dark">팀플 추천</h4>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
	      <nav class="my-2 my-md-0 mr-md-3">
	      	<form name="form3" method="post" action="<c:url value='/user/logout' />">
		      	<c:choose>
				<c:when test="${selectionSuccessed}">
					<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${stuId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">
					<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
					<c:param name='stuId' value='${stuId}'/></c:url>')" />
				</c:when>
				<c:when test="${selectionFailed}">
					<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${stuId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">
					<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
					<c:param name='stuId' value='${stuId}'/></c:url>')" />
				</c:when>
				<c:otherwise>
					<input type="button" class="btn btn-sm btn-outline-secondary" value="로그아웃(&nbsp;${stuId}&nbsp;)" onClick="userLogout('<c:url value='/user/logout' />')">
					<input type="button" class="btn btn-sm btn-outline-secondary" value="내정보" onClick="myPage('<c:url value='/user/view'>
					<c:param name='stuId' value='${stuId}'/></c:url>')" />
				</c:otherwise>
				</c:choose>
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
      	<form name="form" class="blog-pagination" method="POST" action="<c:url value='/teamProject/recommend' />">
		  <table class="table table-shopping">
		  <tr>
		  <td>
		  	<ul>
		  		<li>
					<div align="right">
					<input type="reset" class="btn btn-outline-primary" value="초기화">
					</div>
				</li>
				<li>
					<c:if test="${selectionFailed}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>
				</li>
				<li>과목명 : <input type="text" name="courseTitle" <c:if test="${selectionSuccessed}">value="${teamPro.c.courseTitle}"</c:if>></li>
				<li>교수명 : <input type="text" name="profName" <c:if test="${selectionSuccessed}">value="${teamPro.c.profName}"</c:if>></li>
				<li>분반
					<select name="division">
						<option value="0" <c:if test="${teamPro.c.division eq 1}">selected</c:if>>1</option>
						<option value="1" <c:if test="${teamPro.c.division eq 2}">selected</c:if>>2</option>
					</select>
				</li>
				<li>년도 : <input type="number" name="year" <c:if test="${selectionSuccessed}">value="${teamPro.c.year}"</c:if>></li>
				<li>학기
					<select name="semester">
						<option value="0" <c:if test="${teamPro.c.semester eq 1}">selected</c:if>>1학기</option>
						<option value="1" <c:if test="${teamPro.c.semester eq 2}">selected</c:if>>2학기</option>
					</select>
				</li>
				<li>
					모임요일 <br>
					<div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="월" value="월" name="day" <c:if test="${valChecked[0]}">checked</c:if> required>
			          <label class="custom-control-label" for="월">월</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="화" value="화" name="day" <c:if test="${valChecked[1]}">checked</c:if> required>
			          <label class="custom-control-label" for="화">화</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="수" value="수" name="day" <c:if test="${valChecked[2]}">checked</c:if> required>
			          <label class="custom-control-label" for="수">수</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="목" value="목" name="day" <c:if test="${valChecked[3]}">checked</c:if> required>
			          <label class="custom-control-label" for="목">목</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="금" value="금" name="day" <c:if test="${valChecked[4]}">checked</c:if> required>
			          <label class="custom-control-label" for="금">금</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="토" value="토" name="day" <c:if test="${valChecked[5]}">checked</c:if> required>
			          <label class="custom-control-label" for="토">토</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="일" value="일" name="day" <c:if test="${valChecked[6]}">checked</c:if> required>
			          <label class="custom-control-label" for="일">일</label>
			        </div>
				</li>
				<li>
					모임장소<br>
					<div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="학교" value="학교" name="place" <c:if test="${val2Checked[0]}">checked</c:if> required>
			          <label class="custom-control-label" for="학교">학교</label>
			        </div>
			        <div class="custom-control custom-checkbox">
			          <input type="checkbox" class="custom-control-input" id="추후협의" value="추후협의" name="place" <c:if test="${val2Checked[1]}">checked</c:if> required>
			          <label class="custom-control-label" for="추후협의">추후협의</label>
			        </div>
				</li>
				<li>
					키워드
					<select name="keyword">
						<option value="시간약속" <c:if test="${teamPro.tKeyword eq '시간약속'}">selected</c:if>>시간약속</option>
						<option value="제출" <c:if test="${teamPro.tKeyword eq '제출'}">selected</c:if>>제출</option>
						<option value="A+" <c:if test="${teamPro.tKeyword eq 'A+'}">selected</c:if>>A+</option>
						<option value="온라인미팅" <c:if test="${teamPro.tKeyword eq '온라인미팅'}">selected</c:if>>온라인미팅</option>
						<option value="오프라인미팅" <c:if test="${teamPro.tKeyword eq '오프라인미팅'}">selected</c:if>>오프라인미팅</option>
					</select>
				</li>
				<li>
					<input type="button" class="btn btn-outline-primary" value="추천받기" onClick="teamProjectAdd()" id="i">
				</li>
			</ul>
		</td>
		<td id="content2" align="center">
			<c:if test="${recommendList.size() ge 0}">
				<h6><b>추천 결과</b></h6>
			</c:if>
			<c:if test="${recommendList.size() eq 0}">
				모집글이 없습니다.
			</c:if>
			<c:forEach var="recoList" items="${recommendList}">
				<table class="table table-shopping">
					<tr>			  	
						<td><b>수강학기</b></td>
						<td> ${recoList.c.year}년 ${recoList.c.semester}학기 (0${recoList.c.division}분반)</td>
					</tr>
					<tr>
						<td><b>신청인원/모집인원</b></td>
						<td>${recoList.requestMemberCnt}/${recoList.teamMemberCnt}</td>
					</tr>
					<tr>
						<td width="100" align="center" bgcolor="FFFFFF" colspan="2" style="border-top:1px solid black">
		       				<a class="btn btn-outline-primary" href="<c:url value='/teamProject/detail/view'>
					 		<c:param name='teamId' value='${recoList.teamId}'/>
							</c:url>">
							선택
					 		</a>
						</td> 
					</tr>
				</table>
			</c:forEach>
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