<%@page import="com.zziririt.group.model.vo.Group"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
ArrayList<Group> joinList = (ArrayList<Group>)request.getAttribute("joinList");
ArrayList<Group> wishList = (ArrayList<Group>)request.getAttribute("wishList");   
%>
<html>
<head>
<meta charset="UTF-8">
<title>그룹관리</title>
</head>
<body>
<%@ include file="/views/common/header.jsp" %>
<div class="container" id="contentDiv" align="center">
<div class="row col-12">
	<!-- 가입한 그룹 목록 -->
	<div class="row col-12 justify-content-center"">
		<div class="col-7 mt-3 row">
			<div class="col-12">가입한 그룹 목록</div>
		
		<% if(joinList.isEmpty()){ %> <div class="col-12">결과가 없습니다.</div>
		<%}else{ 
			for(Group g : joinList){
			%><div class="col bg-warning m-1" onclick="goTo(<%=g.getGroupNo()%>)"> <%=g.getGroupName()%> <%=g.getCurrentMem() %>/<%=g.getGroupLimit() %></div>
			  
			<%}
		}
		%>
		</div>
	</div>
	<!-- 찜한 그룹 목록 -->
	<div class="row col-12 justify-content-center" >
		<div class="col-7 mt-3 row" >
			<div class="col-12">찜한 그룹 목록</div>
		
		<% if(wishList.isEmpty()){ System.out.print("xxx"+wishList.isEmpty());%> <div class="col-12">결과가 없습니다.</div>
		<%}else{ 
			for(Group g : wishList){
			%><div class="col bg-warning m-1" onclick="goTo(<%=g.getGroupNo()%>)"> <%=g.getGroupName()%> <%=g.getCurrentMem() %>/<%=g.getGroupLimit() %></div>
			<%}
		}
		%>
		</div>
	</div>
	<script>
		function goTo(no){
			location.href="/Zziririt/group/view?gno="+no;
		}
	</script>
</div>
</div>
</body>
</html>