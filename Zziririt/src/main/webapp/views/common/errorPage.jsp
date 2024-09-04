<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// errorMsg 라는 key값으로 에러 문구 꺼내기
	String errorMsg = (String)request.getAttribute("errorMsg"); // String 타입으로 받고 싶어서 다운캐스팅
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<br><br>
	
	<h1 align="center" style="color : red;">
		<%= errorMsg %>
	</h1>
</body>
</html>