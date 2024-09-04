<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zziririt.common.model.vo.PageInfo, java.util.ArrayList, com.zziririt.letter.model.vo.Letter, java.sql.Date" %>
<%
	// 응답데이터 뽑기
	PageInfo pi 
		= (PageInfo)request.getAttribute("pi");

	ArrayList<Letter> list
		= (ArrayList<Letter>)request.getAttribute("list");
	
	int listCount = (Integer)request.getAttribute("listCount");
	int listCountRe = (Integer)request.getAttribute("listCountRe");
	int listCountSe = (Integer)request.getAttribute("listCountSe");
	int listCountSa = (Integer)request.getAttribute("listCountSa");
	int listReadNot = (Integer)request.getAttribute("listReadNot");

	// 페이징바 출력 시 주로 쓰이는 변수들 따로 빼기
	// (매번 getter 호출하면 구문이 헷갈림)
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
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

	.list-area {
		border : 1px solid lightgray;
		text-align : center;
	}

	.list-area>tbody>tr:hover {
		background-color : lightgray;
		cursor : pointer;
	}
	
	.notReadLetter {
		background-color : gray;
	}
</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
<div class="container" id="contentDiv" align="center">
	<div class="outer">
		
		<br>
		<h2 align="center">받은 쪽지함 (<%=listCountRe%>)</h2>
		<br>
		
		<button onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=1'">전체쪽지 (<%=listCountRe%>)</button>
		<button class="notReadLetter" onclick="location.href = '<%= contextPath %>/readNotYet.lo?currentPage=1&letterStatus=1'">안읽은쪽지 (<%=listReadNot%>)</button>
		
		<br><br>
		
		<form action="letterSearch.lo" method="GET">
		<input type="hidden" name="letterStatus" value="1">
		<input type="hidden" name="currentPage" value="1">
        <input type="text" id="keyword" name="keyword" placeholder="제목 / 작성자 검색" style="width:280px">
        <button type="submit">검색</button>
    	</form>
    	<br>
		
		<div align="center">

			
			<form id="deleteForm" action="lettersDelete.lo?letterStatus=1" method="post">
    			<input type="hidden" name="deleteValues" id="deleteValues" value="">
			</form>
			<button onclick="location.href = '<%= contextPath %>/letterEnrollForm.lo'">새쪽지 작성하기</button>
			<button onclick="deleteSelected()">삭제하기</button>
			<button class="notReadLetter" onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=1'">슈신함 (<%=listCountRe%>)</button>
			<button onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=2'">발신함 (<%=listCountSe%>)</button>
			<button onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=4'">임시보관함 (<%=listCountSa%>)</button>
				
	
		</div>

		<br><br>
		
		<!-- 실제 게시글 목록이 보여질 부분 -->
		<table align="center" class="list-area">
			<thead>
				<tr>
					<th width="70"><input type="checkbox" name="all" onclick="letterCheckAll(this.checked);">전체선택</th>
					<th width="300">제목</th>
					<th width="150">작성자</th>
					<th width="150">작성일</th>
				</tr>
			</thead>
			<tbody>
				<% if(list.isEmpty()) { %>
					
					<tr>
						<td colspan="4">
							조회된 리스트가 없습니다.
						</td>
					</tr>
					
				<% } else { %>
				
					<% for(Letter l : list) { %>
						
						<tr id="tr">
							<td><input type="checkbox" name="letterNo" value="<%=l.getLetterNo() %>"></td>
							<td><%= l.getLetterTitle() %></td>
							<td><%= l.getUserSender() %></td>
							<td><%= l.getSendTime() %></td>
						</tr>
					
					<% } %>
					
				<% } %>
			</tbody>
		</table>
		

		<br><br>

		<!-- 페이징바가 보여질 부분 -->
		<div align="center" class="paging-area">
		
			<% if(currentPage != 1) { %>
				
				<!-- 이전페이지로 이동 버튼이 보여져야함 -->
				<button 
					onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=<%= currentPage - 1 %>&letterStatus=1';">
					&lt;
				</button>
			
			<% } %>
			
			
			<% for(int p = startPage; p <= endPage ; p++) { %>
			
				<!-- 현재 내가 보고있는 페이지일 경우는 클릭 안되게끔 -->
				<% if(p != currentPage) { %>
					<!-- 페이지 이동 가능하게끔 -->
					
					<button 
						onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=<%= p %>&letterStatus=1';">
						<%= p %>
					</button>
					
				<% } else { %>
					<!-- 페이지 이동 불가능하게끔 -->
				
					<button style="background-color:pink"><%= p %></button>
				
				<% } %>
			
			<% } %>
			
			<% if(currentPage != maxPage) { %>
			
				<!-- 현재 페이지가 마지막 페이지가 아닌 경우 다음 페이지 버튼 노출 -->
				<button 
					onclick="location.href = '<%= contextPath %>/letterList.lo?currentPage=<%= currentPage + 1 %>&letterStatus=1';">
					&gt;
				</button>
				
			<% } %>

		</div>

		<br><br>
				<script>
			$(function() {
				
				$("tr td:not(:first-child)").click(function() {
					
					// console.log("잘 클릭되나..?");
					// 글번호 : 현재 클릭된 tr 의 첫번째 자손 td 내용물
					 let letterNo = $(this).siblings().find('input[name="letterNo"]').val();
					
					// console.log(bno);
					
					location.href = "<%= contextPath %>/letterDetail.lo?letterNo="+letterNo+"&letterStatus=1";
				});
			});

		
			function letterCheckAll(checked) {
		    	var letterNoCheckboxes = document.getElementsByName("letterNo");
		        for(let i = 0; i < letterNoCheckboxes.length; i++) {
		            	letterNoCheckboxes[i].checked = checked;
		            }
		    }
			
			function deleteSelected() {
			    var checkboxes = document.getElementsByName("letterNo");
			    var deleteValues = [];
			    for (var i = 0; i < checkboxes.length; i++) {
			        if (checkboxes[i].checked) {
			            deleteValues.push(checkboxes[i].value);
			        }
			    }
			    document.getElementById("deleteValues").value = deleteValues.join(",");
			    console.log(document.getElementById("deleteValues").value)
			    document.getElementById("deleteForm").submit();
			       
			}
		</script>

	</div>
</div>
</body>
</html>




