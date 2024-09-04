<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.zziririt.letter.model.vo.*, java.sql.Date"%>
<%
	// 응답데이터 뽑기
	Letter l = (Letter)request.getAttribute("l");
	int letterStatus = (Integer)request.getAttribute("letterStatus");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width : 1000px;
		border : 1px dotted lightgray;
		margin : auto;
		margin-top : 50px;
	}

	.outer table {
		border : 1px solid lightgray;
		border-spacing : 0px;
	}
</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>

<div class="container" id="contentDiv" align="center">
	<div class="outer" >
	
		<br>
		<h2 align="center">받은 쪽지</h2>
		<br>
		
		<table id="detail-area" align="center" 
				border="1">

			<tr>
				<th width="70">발신자</th>
				<td width="70"><%= l.getUserSender() %></td>
				<th width="70">작성일</th>
				<td width="350"><%= l.getSendTime() %></td>
			</tr>
			<tr>
				<th>수신자</th>
				<td><%= l.getUserReceiver() %></td>
				<th>제목</th>
				<td><%= l.getLetterTitle() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<pre style="height : 200px;"><%= l.getLetterContent() %></pre>
				</td>
			</tr>

		</table>

		<br><br>

		<div align="center">
			<a href="<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=<%=letterStatus%>" 
			   class="btn btn-secondary btn-sm">
				목록가기
			</a>

			
			<a href="<%= contextPath %>/lettersDelete.lo?deleteValues=<%= l.getLetterNo()%>&letterStatus=1" 
			   class="btn btn-danger btn-sm">
				삭제하기
			</a>
				
	
		</div>
		

		<br><br>
		
	</div>
</div>
</body>
</html>





