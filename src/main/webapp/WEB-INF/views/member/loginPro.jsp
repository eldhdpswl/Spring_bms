<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<script src="${project}script.js"></script>
<link href="/bms_project/resources/style/login.css" rel="stylesheet" type="text/css" media="screen" />

<html>
<body>
	<h2>로그인페이지(loginPro.jsp)</h2>
	<%
		int cnt = (Integer) request.getAttribute("cnt");
		String id = (String) request.getAttribute("id");
		String pwd = (String) request.getAttribute("pwd");
	%>
	
</body>
</html>