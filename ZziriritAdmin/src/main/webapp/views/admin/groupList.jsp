<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.zziririt.common.model.vo.PageInfo ,java.util.ArrayList, com.zziririt.admin.model.vo.Group"%>
<%
// 응답데이터 뽑기
ArrayList<String> categoryName  = (ArrayList<String>) request.getAttribute("categoryName");
PageInfo pi = (PageInfo) request.getAttribute("pi");
String search = (String) request.getAttribute("search");
String category = (String) request.getAttribute("category");
ArrayList<Group> list = (ArrayList<Group>) request.getAttribute("list");
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
.group-list {
	border: 1px solid lightgray;
	text-align: center;
}

.group-list>tbody>tr:hover {
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

.group-list {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
}
.group-list >td {
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
						<%if(category==null ||category=="") {%>
						<h3 align="center">전체 모임 리스트</h3>
						<%}else{ %>
						<h3 align="center"><%= category%>
							모임 리스트
						</h3>
						<%} %>
						<br>
						<hr>
						<br>
						<table align="center">
							<form action="<%=request.getContextPath()%>/search.gl"
								method="get"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-0 my-1 my-md-0 mw-100 navbar-search">
								<tr hieght="0px">
									<th width="1040" colspan="6"></th>
								</tr>
								<tr>
									<td colspan="4" width="540px"></td>
									<td width="250px">

										<div class="input-group">
											<input type="hidden" name="currentPage" value="1"> <select
												id="userGrupList" name="category"
												class="form-control border-3" style="width: 150px">
												<%for(String g : categoryName) {%>
												<option value="<%= g %>"><%= g %>
												</option>
												<%} %>
												<option value="" ; selected>전체</option>
											</select>
											<div class="input-group-append">
												<button class="btn btn-primary" type="submit">그룹 조회</button>
											</div>
										</div>
									</td>
									<td width="250px">
										<div align="right">

											<div class="input-group">
												<input type="text" class="form-control border-3"
													placeholder="그룹명 검색" style="background-color: white;"
													name="search">
												<div class="input-group-append">
													<button class="btn btn-primary" type="submit">검색</button>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</form>
						</table>

						<br>

						<table align="center" border="1" class="group-list"
							id="group-list">
							<thead>
								<tr>
									<th width="120">모임 번호</th>
									<th width="150">카테고리</th>
									<th width="310">모임 이름</th>
									<th width="90">모임 정원</th>
									<th width="170">모임 생성일</th>
									<th width="200">모임 운영 여부</th>
								</tr>
							</thead>
							<tbody id="group-list-td">
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
								for (Group ru : list) {
								%>
								<tr>
									<td><%=ru.getGroupNo()%></td>
									<td><%=ru.getCategoryName()%></td>
									<td><%=ru.getGroupName()%></td>
									<td><%=ru.getGroupLimit()%></td>
									<td><%=ru.getGroupCreatDay()%></td>
									<td>
										<%
										if (ru.getStatus() == 1) {
										%> 운영중인 그룹 <%
										} else {
										%> 미운영중인 그룹 <%
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
						<%if (search == null && category == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gl?currentPage=1'">&lt;&lt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gl?currentPage=1&category=<%=category%>&search=<%=search%>'">&lt;&lt;</button>
						<%}%>

						<!-- 한 페이지 앞으로 -->

						<%if (search == null && category == null) {%>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gl?currentPage=<%=currentPage - 1%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<% } else { %>
						<%if (currentPage != 1) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gl?currentPage=<%=currentPage - 1%>&category=<%=category%>&search=<%=search%>'">&lt;</button>
						<%} else {%>
						<button>&lt;</button>
						<%}%>
						<%}%>





						<%for (int p = startPage; p <= endPage; p++) {%>



						<!-- 한 페이지 씩 이동  -->
						<%if (search == null && category == null) {%>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gl?currentPage=<%=p%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<% } else { %>
						<%if (p != currentPage) {
							%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gl?currentPage=<%=p%>&category=<%=category%>&search=<%=search%>'"><%=p%></button>
						<% } else { %>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%}%>
						<%}%>


						<%}%>



						<%if (search == null && category == null) {%>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gl?currentPage=<%=currentPage + 1%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<% } else { %>
						<%if (currentPage != maxPage) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gl?currentPage=<%=currentPage + 1%>&category=<%=category%>&search=<%=search%>'">&gt;</button>
						<%} else {%>
						<button>&gt;</button>
						<%}%>


						<%}%>



						<%if (search == null && category == null) {%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gl?currentPage=<%= maxPage%>'">&gt;&gt;</button>
						<% } else { %>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gl?currentPage=<%= maxPage%>&category=<%=category%>&search=<%=search%>'">&gt;&gt;</button>
						<%}%>

					</div>
					<br> <br>
				</div>

			</div>
		</div>

		<div class="modal">
			<div class="modal_body">
				<h2>모임 정보 변경</h2>
				<hr>
				<br>
				<form action="<%=request.getContextPath()%>/delete.gl" method="get">
					<table border="1" align="center" class="rul" id="gu">
						<input type="hidden" name="groupNo">
						<tbody>
							<tr>
								<th>그룹 번호</th>
								<td id="gu_groupNo"></td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td id="gu_categoryName"></td>
							</tr>
							<tr>
								<td>모임 이름
								</th>
								<td id="gu_groupName"></td>
							</tr>
							<tr>
								<th>모임 정원</th>
								<td id="gu_groupLimit"></td>
							</tr>
							<tr>
								<th>그룹 운영 여부</th>
								<td><select id="gu_status" name="status">
										<option value="1">운영중인 그룹</option>
										<option value="0">미운영중인 그룹</option>
								</select></td>
							</tr>
						</tbody>
					</table>
					<br> <br>
					<div align="center">
						<button type="submit">모임정보 수정하기</button>
					</div>
				</form>
			</div>
		</div>




		<script>
		
		$(function() {
			$("#group-list>tbody>tr").click(function() {
				// console.log("잘 클릭되나??")
				// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
			 	let gno = $(this).children().eq(0).text();
				// console.log(uno)
				<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
				
				// selectru(uno);
				
				let categoryName = $(this).children().eq(1).text();
				let groupName = $(this).children().eq(2).text();
				let groupLimit = $(this).children().eq(3).text();
				let status = $(this).children().eq(5).text();
				
				// console.log(status)
				
				$("#gu>input[name=groupNo]").val(gno);
				
				$("#gu_groupNo").text(gno);
				$("#gu_categoryName").text(categoryName); 
				
				$("#gu_groupName").text(groupName);
				$("#gu_groupLimit").text(groupLimit);
				
				if(status.includes("미운영중인 그룹")) {
					
					$("#gu_status").children().eq(1).attr("selected", true);
					
				} else {
					$("#gu_status").children().eq(0).attr("selected", true);
	
				}
				
				
				<%-- <%for(String g : categoryName) {%>
				
				
				
				if($("#category").includes("<%= category %>") ) {
					
					$("#category").children().eq(<%= g %>).attr("selected", true);
					
				}
			
				
				<%} %>
					 --%>		
				
				

			/* 	if(status.includes("운영중인 그룹")) {
					
					$("#gu_status").children().eq(0).attr("selected", true);
					
				} else {

					$("#gu_status").children().eq(1).attr("selected", true);
				}
				 */
			});
			
			
		});
		
	</script>
		<script>
 	$(".input-group select[name=category]>option").each(function() {
		if($(this).val() == "<%= category %>") {
			
			$(this).attr("selected", true);
		}
		
	}); 
	
	</script>
		<script>
            const modal = document.querySelector('.modal');
            const btnOpenModal=document.querySelector('#group-list-td');

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
