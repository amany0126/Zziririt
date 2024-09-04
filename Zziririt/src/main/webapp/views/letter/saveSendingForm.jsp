<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.zziririt.letter.model.vo.*"%>
<%
	// 응답데이터 뽑기
	Letter l = (Letter)request.getAttribute("l");
	

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

	#enroll-form>table { 
		border : 1px solid lightgray; 
	}

	#enroll-form input,
	#enroll-form textarea {
		width : 100%;
		box-sizing : border-box;
	}

</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
<div class="container" id="contentDiv" align="center">
	<div class="outer">
		
		<br>
		<h2 align="center">쪽지 보내기</h2>
		<br>

		
		<form id="enroll-form" 
			  action="<%= contextPath %>/letterSending.lo" 
			  method="post" >

			<!-- 작성자의 회원번호도 넘기기 -->
			<input type="hidden" 
				   name="userSender"
				   value="<%= l.getUserSender() %>">
			
			<input type="hidden"
				   id = "letterNo" 
				   name="letterNo"
				   value="<%=l.getLetterNo()%>">
								  

			<!--
				* 게시글 작성 시 입력받아야 하는 것들
				제목, 내용, 수신자
			-->
			<table align="center" id="letterNo">
				
				<tr>
					<th>받는 사람</th>
					<td>
						<input type="text"
							   name="userReceiver"
							   id="userReceiver"
							   value="<%= (l.getSaveReceiver()==null)?"":l.getSaveReceiver() %>"
							   required>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text"
							   name="letterTitle"
							   id="letterTitle"
							   value="<%=l.getLetterTitle()==null?"":l.getLetterTitle()%>"
							   required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<pre><textarea name="letterContent" rows="10" id="letterContent" required style="resize : none;"><%=l.getLetterContent()==null?"":l.getLetterContent()%></textarea></pre>
					</td>
				</tr>
			</table>

			<br><br>

			<div align="center">
				<button type="submit"
						class="btn btn-secondary btn-sm">
					보내기
				</button>
				<button type="button" onclick="saveData()">
					임시저장
				</button>
				<button type="button" id="deleteletter">
					삭제하기
				</button>
				<button type="button" id="goSaveList" onclick="funtion();">
					목록가기
				</button>
				
			</div>

		</form>
		
		<script>
			
			function saveData() {
			
				// 입력받은 회원번호 먼저 추출
				let saveReceiver = $("#userReceiver").val();
				let letterTitle = $("#letterTitle").val();
				let letterContent = $("#letterContent").val();
				let letterNo = $("#letterNo").val();
				$.ajax({
					url : "/Zziririt/letterSaving.lo",
					type : "post",
					data : {
							saveReceiver: saveReceiver,
							letterTitle: letterTitle,
							letterContent: letterContent,
							letterNo: letterNo
							},
					success : function(result) {
						console.log("ajax 통신 성공!");
						alert("임시저장 되었습니다.");
					},
					error : function() {
						console.log("ajax 통신 실패!");
					}
				});
				
		}
			
			$(function() {
				
				$("#deleteletter").click(function() {
					
					
					location.href = "<%= contextPath %>/lettersDelete.lo?deleteValues=<%=l.getLetterNo()%>&letterStatus=4";
				});
			});
			
			$(function() {
				
				$("#goSaveList").click(function() {
					
				
					location.href = "<%= contextPath %>/letterList.lo?currentPage=1&letterStatus=4";
					
				});
			});
		</script>

		<br><br>
		
	</div>
</div>
</body>
</html>





