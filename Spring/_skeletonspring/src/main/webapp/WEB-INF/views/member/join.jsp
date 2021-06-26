<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<script type="text/javascript">
	function login() {
		if(document.getElementById("email").value == "") {
			alert("아이디 입력!!!");
			return;
		} else if(document.getElementById("password").value == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			document.getElementById("loginForm").action = "${root}/member/join";
			document.getElementById("loginForm").submit();
		}
	}
	</script>

</head>
<body>
	<form id="loginForm" method="post" action="">
		<div align="left">
			<label for="">아이디</label>
			<input type="text" id="email" name="email" placeholder="" value="${saveid}">
		</div>
		<div align="left">
			<label for="">비밀번호</label>
			<input type="password" id="password" name="password" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
		</div>
		
		<div align="center">
			<button type="button" class="genric-btn success circle arrow" onclick="javascript:login();">로그인</button>
		</div>
	</form>
</body>
</html>