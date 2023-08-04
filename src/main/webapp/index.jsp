<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버웹</title>
</head>
<body>
 로그인이 성공했으면 보여요 : ${sessionScope.memberId }
	<h1>멤버웹페이지</h1>
	
	<c:if test="${sessionScope.memberId ne null}">
	 ${sessionScope.memberName }님 환영합니다.<br>
	 	<a href="/member/Logout.do">로그아웃</a> 
<!-- 	 SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? -->
<!-- 폼태그가 아니니까 ?뒤에 쿼리스트링을 넣어준다 -->
	<a href="/member/mypage.do?memberId=${memberId }">마이페이지 </a>
	</c:if>
	<c:if test="${sessionScope.memberId eq null}">
	<h2>로그인 페이지</h2>
		<fieldset>
			<legend>로그인</legend>
			<form action="/member/login.do" method="post">
				<input type="text" name="member-id"><br> <input
					type="password" name="member-pw"><br>
				<div>
					<input type="submit" value="로그인"> 
					<input type="reset"value="취소">
					<a href="/member/register.do">회원가입</a>
				</div>
			</form>
		</fieldset>
	</c:if>
</body>
</html>
