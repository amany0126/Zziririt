<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.zziririt.common.model.vo.PageInfo ,java.util.ArrayList, com.zziririt.admin.model.vo.RegularUser"%>
<%
// 응답데이터 뽑기
PageInfo pi = (PageInfo) request.getAttribute("pi");
String searchUser = (String) request.getAttribute("searchUser");
ArrayList<RegularUser> list = (ArrayList<RegularUser>) request.getAttribute("list");
// 페이징바 출력 시 주로 쓰이는 변수들 따로 뺴기
// (매번 getter 호출하면 구문이 햇갈림)
int currentPage = pi.getCurrentPage();
int listCount = pi.getListCount();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.user-list {
	border: 1px solid lightgray;
	text-align: center;
}

.user-list>tbody>tr:hover {
	background-color: lightgrey;
	cursor: pointer;
}

.modal {
	position: absolute;
	display: none;
	justify-content: center;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal_body {
	position: absolute;
	top: 50%; /*모달을 화면가운데 놓기위함. */
	width: 400px; /*//모달의 가로크기 */
	height: 500px; /*//모달의 세로크기 */
	padding: 40px;
	text-align: center;
	background-color: rgb(255, 255, 255); /*//모달창 배경색 흰색*/
	border-radius: 10px; /*//테두리 */
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15); /*//테두리 그림자 */
	transform: translateY(-50%); /* //모듈창열었을때 위치설정 가운데로 */
}

h2, h3, .modal h2 {
	color: black;
}

.user-list {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
}.user-list >td {
	overflow:hidden;
      text-overflow:ellipsis;
      white-space:nowrap;
}

.rul {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
}
</style>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<%@ include file="adminMenubar.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<%@ include file="adminFooter.jsp"%>
				<div class="outer">

					<br>

					<div align="center">
						<%if(searchUser==null || searchUser=="") {%>
						<h2 align="center">전체 사용자 검색</h2>
						<%}else{ %>
						<h2 align="center"><%= searchUser%>
							가(이) 아이디/이름에 포함된 사용자 리스트
						</h2>
						<%} %>

						<br>
						<hr>
						<br>
						<table>
							<tr hieght="0px">
								<th width="120"></th>
								<th width="160"></th>
								<th width="140"></th>
								<th width="200"></th>
								<th width="220"></th>
								<th width="200"></th>
							</tr>
							<tr>
								<td colspan="6">
									<div align="right">
										<form
											action="<%=request.getContextPath()%>/search.rul?currentPage=1"
											method="get"
											class="d-none d-sm-inline-block form-inline mr-auto ml-md-0 my-1 my-md-0 mw-100 navbar-search">
											<div class="input-group">
												<input type="hidden" name="currentPage" value="1"> <input
													type="text" class="form-control border-3" name="searchUser"
													placeholder="아이디/이름검색" style="background-color: white;">
												<div class="input-group-append">
													<button class="btn btn-primary" type="submit">검색</button>
												</div>
											</div>
										</form>
									</div>
								</td>
							</tr>
						</table>

						<br>

						<table align="center" border="1" class="user-list" id="user-list">
							<thead>
								<tr>
									<th width="120">회원번호</th>
									<th width="160">아이디</th>
									<th width="140">이름</th>
									<th width="200">닉네임</th>
									<th width="220">회원가입일</th>
									<th width="200">회원가입여부</th>
								</tr>
							</thead>
							<tbody id="user-list-td">
								<%
								if (list.isEmpty()) {
								%>
								<tr>
									<td colspan="6">조회된 리스트가 없습니다</td>
								</tr>
								<%
								} else {
								%>
								<%
								for (RegularUser ru : list) {
								%>
								<tr>
									<td><%=ru.getUserNo()%></td>
									<td><%=ru.getUserId()%></td>
									<td><%=ru.getUserName()%></td>
									<td><%=ru.getUserNickName()%></td>
									<td><%=ru.getJoinDate()%></td>
									<td>
										<%
										if (ru.getStatus() == 1) {
										%> 이용중인 회원 <%
										} else {
										%> 탈퇴한 회원 <%
										}
										%>
									</td>

								</tr>

								<%
								}
								%>
								<%
								}
								%>

							</tbody>
						</table>
					</div>
					<br> <br>

					<!-- 페이징바가 보여질 부분 -->
					<div align="center" class="paging-area">

						<!-- 맨 처음으로 -->
						<%if (searchUser == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.rul?currentPage=1'">&lt;&lt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.rul?currentPage=1&searchUser=<%=searchUser%>'">&lt;&lt;</button>
						<%}%>

						<!-- 한 페이지 앞으로 -->

						<%if (searchUser == null) {%>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.rul?currentPage=<%=currentPage - 1%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<% } else { %>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.rul?currentPage=<%=currentPage - 1%>&searchUser=<%=searchUser%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<%}%>





						<%for (int p = startPage; p <= endPage; p++) {%>



						<!-- 한 페이지 씩 이동  -->
						<%if (searchUser == null) {%>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.rul?currentPage=<%=p%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<% } else { %>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.rul?currentPage=<%=p%>&searchUser=<%=searchUser%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<%}%>


						<%}%>



						<%if (searchUser == null) {%>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.rul?currentPage=<%=currentPage + 1%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<% } else { %>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.rul?currentPage=<%=currentPage + 1%>&searchUser=<%=searchUser%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<%}%>



						<%if (searchUser == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.rul?currentPage=<%= maxPage%>'">&gt;&gt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.rul?currentPage=<%= maxPage%>&searchUser=<%=searchUser%>'">&gt;&gt;</button>
						<%}%>

					</div>
					<br> <br>
				</div>

			</div>
		</div>

		<div class="modal">
			<div class="modal_body">
				<h2>일반 회원 정보 변경</h2>
				<hr>
				<br>
				<form action="<%=request.getContextPath()%>/delete.rul" method="get">
					<table border="1" align="center" class="rul" id="rul">
						<input type="hidden" name="userNo">
						<tbody>
							<tr>
								<th>회원번호</th>
								<td id="rul_userNo"></td>
							</tr>
							<tr>
								<th>아이디</th>
								<td id="rul_userId"></td>
							</tr>
							<tr>
								<td>이름
								</th>
								<td id="rul_userName"></td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td id="rul_userNickName"></td>
							</tr>
							<tr>
								<th>회원탈퇴 여부</th>
								<td><select id="rul_status" name="status">
										<option value="1">이용가능</option>
										<option value="0">탈퇴</option>
								</select></td>
							</tr>
						</tbody>
					</table>
					<br> <br>
					<div align="center">
						<button type="submit">회원정보 수정하기</button>
					</div>
				</form>
			</div>
		</div>




		<script>

		$(function() {
			$(".user-list>tbody>tr").click(function() {
				// console.log("잘 클릭되나??")
				// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
			 	let uno = $(this).children().eq(0).text();
				// console.log(uno)
				<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
				
				// selectru(uno);
				
				let userId = $(this).children().eq(1).text();
				let userName = $(this).children().eq(2).text();
				let userNickName = $(this).children().eq(3).text();
				let status = $(this).children().eq(5).text();
				
				$("#rul_userNo").text(uno);
				$("#rul>input[name=userNo]").val(uno);
				$("#rul_userId").text(userId);
				$("#rul_userName").text(userName);
				$("#rul_userNickName").text(userNickName);

				if(status.includes("이용중인 회원")) {
					
					$("#rul_status").children().eq(0).attr("selected", true);
					
				} else {

					$("#rul_status").children().eq(1).attr("selected", true);
				}
				
			});
			
			
		});
		
	</script>
		<script>
            const modal = document.querySelector('.modal');
            const btnOpenModal=document.querySelector('#user-list-td');

            btnOpenModal.addEventListener("click", ()=>{
            modal.style.display="flex";
            });
            modal.addEventListener("click", e => {
            const evTarget = e.target
            if(evTarget.classList.contains("modal")) {
                modal.style.display = "none"
            }
            });
			
		</script>

	</div>
</body>
</html>
