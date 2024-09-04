<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.zziririt.common.model.vo.PageInfo ,java.util.ArrayList, com.zziririt.admin.model.vo.GroupBoard"%>
<%
// 응답데이터 뽑기
ArrayList<String> categoryName = (ArrayList<String>) request.getAttribute("categoryName");
PageInfo pi = (PageInfo) request.getAttribute("pi");
ArrayList<GroupBoard> list = (ArrayList<GroupBoard>) request.getAttribute("list");
String boardStatus = (String) request.getAttribute("boardStatus");
String category = (String) request.getAttribute("category");
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
	width: 600px; /*//모달의 가로크기 */
	height: 800px; /*//모달의 세로크기 */
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

.gbbrl {
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

					<div align="center">
						<br>

						<%if(category==null || category=="") {%>
						<h3 align="center">전체 모임 게시글 리스트</h3>
						<%}else{ %>
						<h3 align="center"><%= category%>
							가(이) 카테고리인 게시글
						</h3>
						<%} %>
						<br>
						<hr>
						<br>
						<table>
							<form action="<%=request.getContextPath()%>/search.gbbrl"
								method="get"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-0 my-1 my-md-0 mw-100 navbar-search">
								<tr hieght="0px">
									<th width="970" colspan="7"></th>
								</tr>
								<tr>
									<td colspan="5" width="550px"></td>
									<td width="300px">

										<div class="input-group">
											<input type="hidden" name="currentPage" value="1"> <select
												id="boardCategory" name="category"
												class="form-control border-3" style="width: 150px">
												<%
												for (String g : categoryName) {
												%>
												<option value="<%=g%>"><%=g%>
												</option>
												<%
												}
												%>
												<option value="" ; selected>전체</option>
											</select>
											<div class="input-group-append">
												<button class="btn btn-primary" type="submit">카테고리
													검색</button>
											</div>
										</div>
									</td>
									<td width="250px">
										<div align="right">

											<div class="input-group">
												<select id="userGrupList" name="boardStatus"
													class="form-control border-3" style="width: 150px">
													<option value="1">게시글 유지</option>
													<option value="0">게시글 삭제</option>
													<option value="-2">게시글 신고됨</option>
													<option value="" ; selected>전체</option>
												</select>
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
						<table align="center" border="1" class="user-list"
							id="group-board-repotr-list-td">
							<thead>
								<tr>
									<th width="100">글순번</th>
									<th width="100">카테고리</th>
									<th width="140">게시글 제목</th>
									<th width="280">게시글 내용</th>
									<th width="170">작성자</th>
									<th width="170">작성자 계정 사용 여부</th>
									<th width="140">게시글 유지 여부</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (list.isEmpty()) {
								%>
								<tr>
									<td colspan="7">조회된 리스트가 없습니다</td>
								</tr>
								<%
								} else {
								%>
								<%
								for (GroupBoard gm : list) {
								%>
								<tr class="boardReport">
									<td><%=gm.getBoardNo()%></td>
									<td><%=gm.getGroup()%></td>
									<td><%=gm.getBoardTitle()%></td>
									<td><%=gm.getBoardContent()%></td>
									<td><%=gm.getGroupMemNo()%></td>
									<td>
										<%
										if (gm.getGroupMemNoStatus() == 1) {
										%> 이용중인 회원 <%
										} else {
										%> 탈퇴한 회원 <%
										}
										%>
									</td>
									<td>
										<%
										if (gm.getStatus() == 1) {
										%> 게시글 유지 <%
										} else if (gm.getStatus() == 0) {
										%> 게시글 삭제 <%
										} else {
										%> 게시글 신고됨 <%
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
						<%
						if (boardStatus == null && category == null) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbrl?currentPage=1'">&lt;&lt;</button>
						<%
						} else {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbrl?currentPage=1&category=<%=category%>&boardStatus=<%=boardStatus%>'">&lt;&lt;</button>
						<%
						}
						%>

						<!-- 한 페이지 앞으로 -->

						<%
						if (boardStatus == null && category == null) {
						%>
						<%
						if (currentPage != 1) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbrl?currentPage=<%=currentPage - 1%>'">&lt;</button>
						<%
						} else {
						%>
						<button>&lt;</button>
						<%
						}
						%>
						<%
						} else {
						%>
						<%
						if (currentPage != 1) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbrl?currentPage=<%=currentPage - 1%>&category=<%=category%>&boardStatus=<%=boardStatus%>'">&lt;</button>
						<%
						} else {
						%>
						<button>&lt;</button>
						<%
						}
						%>
						<%
						}
						%>





						<%
						for (int p = startPage; p <= endPage; p++) {
						%>



						<!-- 한 페이지 씩 이동  -->
						<%
						if (boardStatus == null && category == null) {
						%>
						<%
						if (p != currentPage) {
						%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbrl?currentPage=<%=p%>'"><%=p%></button>
						<%
						} else {
						%>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%
						}
						%>
						<%
						} else {
						%>
						<%
						if (p != currentPage) {
						%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbrl?currentPage=<%=p%>&category=<%=category%>&boardStatus=<%=boardStatus%>'"><%=p%></button>
						<%
						} else {
						%>
						<!-- 페이지 이동 불가능하게끔 -->
						<button disabled style="background-color: black; color: white;"><%=p%></button>
						<%
						}
						%>
						<%
						}
						%>


						<%
						}
						%>



						<%
						if (boardStatus == null && category == null) {
						%>
						<%
						if (currentPage != maxPage) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbrl?currentPage=<%=currentPage + 1%>'">&gt;</button>
						<%
						} else {
						%>
						<button>&gt;</button>
						<%
						}
						%>


						<%
						} else {
						%>
						<%
						if (currentPage != maxPage) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbrl?currentPage=<%=currentPage + 1%>&category=<%=category%>&boardStatus=<%=boardStatus%>'">&gt;</button>
						<%
						} else {
						%>
						<button>&gt;</button>
						<%
						}
						%>


						<%
						}
						%>



						<%
						if (boardStatus == null && category == null) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbrl?currentPage=<%=maxPage%>'">&gt;&gt;</button>
						<%
						} else {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbrl?currentPage=<%=maxPage%>&category=<%=category%>&boardStatus=<%=boardStatus%>'">&gt;&gt;</button>
						<%
						}
						%>

					</div>
					<br> <br>


					<div class="modal">
						<div class="modal_body">
							<h2>모임 게시글 관리 창</h2>
							<hr>
							<br>
							<form action="<%=request.getContextPath()%>/report.gbbrl"
								method="get">
								<table border="1" align="center" id="gbbrl" class="gbbrl">
									<input type="hidden" name="userNo">
									<input type="hidden" name="groupBoardNo">
									<input type="hidden" name="board_title">
									<tbody>
										<tr>
											<th>카테고리</th>
											<td id="gbbrl_group"></td>
											<th width="120">게시판 번호</th>
											<td width="60" id="gbbrl_group_board_no"></td>
										</tr>
										<tr>
											<th>제목</th>
											<td colspan="3" id="gbbrl_group_board_title"></td>
										</tr>
										<tr>
											<th>작성자</th>
											<td colspan="3" id="gbbrl_group_board_user"></td>
										</tr>
										<tr>
											<th>내용</th>
											<td colspan="3">
												<p id="gbbrl_group_board_content"></p>
											</td>
										</tr>
										<tr>
											<th>조치사항</th>
											<td colspan="3">
											
											<pre><textarea cols="30" rows="10" style="resize: none; border: none"  
										id="action_result"  name="action_result"
                                    	>사용자에게 보낼 경고메시를 적어주세요.</textarea></pre>
											
                                    </td>
										</tr>
										<tr>
											<th>회원탈퇴 여부</th>
											<td colspan="3"><select id="user_status"
												name="user_status"
												style="box-sizing: border-box; width: 100%">
													<option value="1">이용중인 회원</option>
													<option value="0">탈퇴한 회원</option>
											</select></td>

										</tr>
										<tr>
											<th>게시글 유지 여부</th>
											<td colspan="3"><select id="board_status"
												name="board_status"
												style="box-sizing: border-box; width: 100%">
													<option value="1">게시글 유지</option>
													<option value="0">게시글 삭제</option>
													<option value="-2">게시글 신고됨</option>
											</select></td>
										</tr>
									</tbody>
								</table>
								<br> <br>
								<div align="center">
									<button type="submit">수정하기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
     
			$(function() {

				$("#group-board-repotr-list-td>tbody>tr").click(function() {
					// console.log("잘 클릭되나??")
					// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
					let bno = $(this).children().eq(0).text();
					// console.log(bno)
					<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
					
					let group = $(this).children().eq(1).text();
					let title = $(this).children().eq(2).text();
					let user = $(this).children().eq(4).text();
					let content = $(this).children().eq(3).text();
					let user_status = $(this).children().eq(5).text();
					let board_status = $(this).children().eq(6).text();
					
					$("#gbbrl>input[name=userNo]").val(user);
					$("#gbbrl>input[name=groupBoardNo]").val(bno);
					$("#gbbrl>input[name=board_title]").val(title);
					
					
					$("#gbbrl_group").text(group);
					$("#gbbrl_group_board_no").text(bno);
					$("#gbbrl_group_board_title").text(title);
					$("#gbbrl_group_board_user").text(user);
					$("#gbbrl_group_board_content").text(content);

					if(user_status.includes("이용중인 회원")) {
						
						$("#user_status").children().eq(0).attr("selected", true);
						
					} else {

						$("#user_status").children().eq(1).attr("selected", true);
					}
					
					if(board_status.includes("유지")) {
						$("#board_status").children().eq(0).attr("selected", true);
					} else if(board_status.includes("삭제")){
						$("#board_status").children().eq(1).attr("selected", true);
					}  else {
						$("#board_status").children().eq(2).attr("selected", true);
					}
				})
			})
		</script>
		<script>
            const modal = document.querySelector('.modal');
            const btnOpenModal=document.querySelector('#group-board-repotr-list-td');

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
		<script>
		$(".input-group select[name=category]>option").each(function() {
			if($(this).val() == "<%=category%>") {
				
				$(this).attr("selected", true);
			}
		
		});
	 	</script>
		<script>
	$(".input-group select[name=boardStatus]>option").each(function() {
			if($(this).val() == "<%=boardStatus%>") {
				
				$(this).attr("selected", true);
			}
		
	});
	</script>
	</div>
</body>
</html>

