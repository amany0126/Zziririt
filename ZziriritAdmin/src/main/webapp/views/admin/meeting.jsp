<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.zziririt.common.model.vo.PageInfo ,java.util.ArrayList, com.zziririt.admin.model.vo.Meeting"%>
<%
// 응답데이터 뽑기
PageInfo pi = (PageInfo) request.getAttribute("pi");
String search = (String) request.getAttribute("search");
ArrayList<Meeting> list = (ArrayList<Meeting>) request.getAttribute("list");
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
.meeting-list {
	border: 1px solid lightgray;
	text-align: center;
}

.meeting-list>tbody>tr:hover {
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

.meeting-list {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
}
.meeting-list >td {
	overflow:hidden;
      text-overflow:ellipsis;
      white-space:nowrap;
}


.mt {
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
						<%if(search==null || search=="") {%>
						<h2 align="center">전체 정기 모임 리스트</h2>
						<%}else{ %>
						<h2 align="center"><%= search%>
							가(이) 소속 그룹 명에 포함된 정기 모임 리스트
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
											action="<%=request.getContextPath()%>/search.mt?currentPage=1"
											method="get"
											class="d-none d-sm-inline-block form-inline mr-auto ml-md-0 my-1 my-md-0 mw-100 navbar-search">
											<div class="input-group">
												<input type="hidden" name="currentPage" value="1"> <input
													type="text" class="form-control border-3" name="search"
													placeholder="소속 그룹명 검색" style="background-color: white;">
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

						<table align="center" border="1" class="meeting-list"
							id="meeting-list">
							<thead>
								<tr>
									<th width="120">정규모임 번호</th>
									<th width="160">소속 그룹 이름</th>
									<th width="140">정기 모임 이름</th>
									<th width="200">장소</th>
									<th width="220">정기 모임 생성자</th>
									<th width="200">모임 유지 여부</th>
								</tr>
							</thead>
							<tbody id="meeting-list-td">
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
								for (Meeting m: list) {
								%>
								<tr>
									<td><%=m.getMeetingNo()%></td>
									<td><%=m.getGroupNo()%></td>
									<td><%=m.getMeetingName()%></td>
									<td><%=m.getMeetingSpot()%></td>
									<td><%=m.getCreateUser()%></td>
									<td>
										<%
										if (m.getStatus() == 1) {
										%> 이용중인 정규모임 <%
										} else {
										%> 종료된 정규모임 <%
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
						<%if (search == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.mt?currentPage=1'">&lt;&lt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.mt?currentPage=1&search=<%=search%>'">&lt;&lt;</button>
						<%}%>

						<!-- 한 페이지 앞으로 -->

						<%if (search == null) {%>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.mt?currentPage=<%=currentPage - 1%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<% } else { %>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.mt?currentPage=<%=currentPage - 1%>&search=<%=search%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<%}%>





						<%for (int p = startPage; p <= endPage; p++) {%>



						<!-- 한 페이지 씩 이동  -->
						<%if (search == null) {%>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.mt?currentPage=<%=p%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<% } else { %>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.mt?currentPage=<%=p%>&search=<%=search%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<%}%>


						<%}%>



						<%if (search == null) {%>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.mt?currentPage=<%=currentPage + 1%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<% } else { %>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.mt?currentPage=<%=currentPage + 1%>&search=<%=search%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<%}%>



						<%if (search == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.mt?currentPage=<%= maxPage%>'">&gt;&gt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.mt?currentPage=<%= maxPage%>&search=<%=search%>'">&gt;&gt;</button>
						<%}%>

					</div>
					<br> <br>
				</div>

			</div>
		</div>

		<div class="modal">
			<div class="modal_body">
				<h2>정기 모임 정보 변경</h2>
				<hr>
				<br>
				<form action="<%=request.getContextPath()%>/delete.mt" method="get">
					<table border="1" align="center" class="mt" id="mt">
						<input type="hidden" name="meetingNo">
						<tbody>
							<tr>
								<th>정기 모임 번호</th>
								<td id="mt_meetingNo"></td>
							</tr>
							<tr>
								<th>소속 그룹 이름</th>
								<td id="mt_groupNo"></td>
							</tr>
							<tr>
								<td>정기 모임 이름
								</th>
								<td id="mt_meetingName"></td>
							</tr>
							<tr>
								<th>모임 생성자</th>
								<td id="mt_createUser"></td>
							</tr>
							<tr>
								<th>정기 모임 여부</th>
								<td><select id="mt_status" name="status">
										<option value="1">이용중인 정규모임</option>
										<option value="0">종료된 정규모임</option>
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
			$("#meeting-list>tbody>tr").click(function() {
				// console.log("잘 클릭되나??")
				// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
			 	let mno = $(this).children().eq(0).text();
				// console.log(mno)
				<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
				
				// selectru(uno);
				
				let groupNo = $(this).children().eq(1).text();
				let meetingName = $(this).children().eq(2).text();
				
				let createUser = $(this).children().eq(4).text();
				let status = $(this).children().eq(5).text();
				
				$("#mt>input[name=meetingNo]").val(mno);
				$("#mt_meetingNo").text(mno);
				$("#mt_meetingName").text(meetingName);
				$("#mt_groupNo").text(groupNo);
				$("#mt_createUser").text(createUser);

				if(status.includes("이용중인")) {
					
					$("#mt_status").children().eq(0).attr("selected", true);
					
				} else {

					$("#mt_status").children().eq(1).attr("selected", true);
				}
				
			});
			
			
		});
		
	</script>
		<script>
            const modal = document.querySelector('.modal');
            const btnOpenModal=document.querySelector('#meeting-list-td');

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
