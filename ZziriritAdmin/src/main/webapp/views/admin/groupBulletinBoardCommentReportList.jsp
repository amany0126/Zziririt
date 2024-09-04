<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.zziririt.common.model.vo.PageInfo ,java.util.ArrayList, com.zziririt.admin.model.vo.GroupComment"%>
<%
// 응답데이터 뽑기
ArrayList<String> categoryName = (ArrayList<String>) request.getAttribute("categoryName");
PageInfo pi = (PageInfo) request.getAttribute("pi");
ArrayList<GroupComment> list = (ArrayList<GroupComment>) request.getAttribute("list");
String comment_Status = (String) request.getAttribute("comment_Status");
String category = (String) request.getAttribute("category");
// 페이징바 출력 시 주로 쓰이는 변수들 따로 뺴기
// (매번 getter 호출하면 구문이 햇갈림)
int currentPage = pi.getCurrentPage();
int listCount = pi.getListCount();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();

// System.out.println(commentStatus);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.list-area {
	border: 1px solid lightgray;
	text-align: center;
}

.list-area>tbody>tr:hover {
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
	height: 750px; /*//모달의 세로크기 */
	padding: 40px;
	text-align: center;
	background-color: rgb(255, 255, 255); /*//모달창 배경색 흰색*/
	border-radius: 10px; /*//테두리 */
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15); /*//테두리 그림자 */
	transform: translateY(-50%); /* //모듈창열었을때 위치설정 가운데로 */
}

.list-area {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
	
}
.list-area >td {
	overflow:hidden;
      text-overflow:ellipsis;
      white-space:nowrap;
}

.gbbcrl {
	border-spacing: 0px;
	border: 1px solid black;
	color: black;
}

h2, .modal h2 {
	color: black;
}

.gbbcrl textarea {
	box-sizing: border-box;
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
					<%if(category==null || category=="") {%>
					<h2 align="center">전체 모임 댓글 리스트</h2>
					<%}else{ %>
					<h2 align="center"><%= category%>
						가(이) 카테고리인 댓글 리스트
					</h2>
					<%} %>
					<br>

					<!-- 실제 게시글 목록이 보여질 부분 -->
					<br>
					<hr>
					<br>
					<table align="center">
						<form action="<%=request.getContextPath()%>/search.gbbcrl"
							method="get"
							class="d-none d-sm-inline-block form-inline mr-auto ml-md-0 my-1 my-md-0 mw-100 navbar-search">
							<tr hieght="0px">
								<th width="1000" colspan="7"></th>
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
											<select id="commentStatusList" name="comment_Status"
												class="form-control border-3" style="width: 150px">
												<option value="1">댓글 유지</option>
												<option value="0">댓글 삭제</option>
												<option value="-2">댓글 신고됨</option>
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

					<table align="center" class="list-area" border="1"
						id="group-board-comment-repotr-list-td">
						<thead>
							<tr>
								<th width="100px">댓글번호</th>
								<th width="100px">그룹</th>
								<th width="200px">제목</th>
								<th width="200px">댓글 내용</th>
								<th width="150px">댓글 작성자</th>
								<th width="200px">사용자 유지 여부</th>
								<th width="180px">댓글 유지 여부</th>
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
							for (GroupComment gc : list) {
							%>
							<tr class="boardReport">
								<td><%=gc.getCommentNo()%></td>
								<td><%=gc.getBoardNo()%></td>
								<td><%=gc.getBoardTitle()%></td>
								<td><%=gc.getComment()%></td>
								<td><%=gc.getGroupMemNo()%></td>
								<td>
									<%
									if (gc.getGroupMemNoStatus() == 1) {
									%> 이용중인 회원 <%
									} else {
									%> 탈퇴한 회원 <%
									}
									%>
								</td>
								<td>
									<%
									if (gc.getStatus() == 1) {
									%> 댓글 유지 <%
									} else if (gc.getStatus() == 0) {
									%> 댓글 삭제 <%
									} else {
									%> 댓글 신고됨 <%
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

					<br> <br>
					<!-- 페이징바가 보여질 부분 -->
					<div align="center" class="paging-area">

						<!-- 맨 처음으로 -->
						<%
						if (comment_Status == null && category == null) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbcrl?currentPage=1'">&lt;&lt;</button>
						<%
						} else {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbcrl?currentPage=1&category=<%=category%>&comment_Status=<%=comment_Status%>'">&lt;&lt;</button>
						<%
						}
						%>

						<!-- 한 페이지 앞으로 -->

						<%
						if (comment_Status == null && category == null) {
						%>
						<%
						if (currentPage != 1) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbcrl?currentPage=<%=currentPage - 1%>'">&lt;</button>
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
							onclick="location.href='<%=request.getContextPath()%>/search.gbbcrl?currentPage=<%=currentPage - 1%>&category=<%=category%>&comment_Status=<%=comment_Status%>'">&lt;</button>
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
						if (comment_Status == null && category == null) {
						%>
						<%
						if (p != currentPage) {
						%>
						<!-- 페이지 이동 가능하게끔 -->
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbcrl?currentPage=<%=p%>'"><%=p%></button>
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
							onclick="location.href='<%=request.getContextPath()%>/search.gbbcrl?currentPage=<%=p%>&category=<%=category%>&comment_Status=<%=comment_Status%>'"><%=p%></button>
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
						if (comment_Status == null && category == null) {
						%>
						<%
						if (currentPage != maxPage) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbcrl?currentPage=<%=currentPage + 1%>'">&gt;</button>
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
							onclick="location.href='<%=request.getContextPath()%>/search.gbbcrl?currentPage=<%=currentPage + 1%>&category=<%=category%>&comment_Status=<%=comment_Status%>'">&gt;</button>
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
						if (comment_Status == null && category == null) {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.gbbcrl?currentPage=<%=maxPage%>'">&gt;&gt;</button>
						<%
						} else {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/search.gbbcrl?currentPage=<%=maxPage%>&category=<%=category%>&comment_Status=<%=comment_Status%>'">&gt;&gt;</button>
						<%
						}
						%>

					</div>
					<br> <br>


					<div class="modal">
						<div class="modal_body">
							<h2 align="center">모임 댓글 관리 창</h2>

							<hr>
							<form action="<%=request.getContextPath()%>/report.gbbcrl"
								method="get">
								<table border="1" align="center" class="gbbcrl" id="gbbcrl">
									<input type="hidden" name="userId">
									<input type="hidden" name="Comment">
									<input type="hidden" name="board_comment_title">
									<input type="hidden" name="board_comment">
									<tbody>
										<tr>
											<th>카테고리</th>
											<td id="gbbcrl_group_category"></td>
											<th width="120">댓글 번호</th>
											<td width="60" id="gbbcrl_group_board_comment_no"></td>
										</tr>
										<tr>
											<th>댓글이 달린 게시글 제목</th>
											<td colspan="3" id="gbbcrl_group_board_comment_title"></td>
										</tr>
										<tr>
											<th>댓글 작성자</th>
											<td colspan="3" id="gbbcrl_group_board_comment_user"></td>
										</tr>
										<tr>
											<th>댓글 내용</th>
											<td colspan="3" id="gbbcrl_group_board_comment_content">
												<p></p>
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
											<th>댓글 유지 여부</th>
											<td colspan="3"><select id="comment_status"
												name="comment_status"
												style="box-sizing: border-box; width: 100%">
													<option value="1">댓글 유지</option>
													<option value="0">댓글 삭제</option>
													<option value="-2">댓글 신고됨</option>
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

			$("#group-board-comment-repotr-list-td>tbody>tr").click(function() {
				// console.log("잘 클릭되나??")
				// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
				let bno = $(this).children().eq(0).text();
				 console.log(bno)
				<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
				
				let CommentNo = $(this).children().eq(0).text();
				let category = $(this).children().eq(1).text();
				let boardTitle = $(this).children().eq(2).text();
				let commentContent = $(this).children().eq(3).text();
				let CommentUser = $(this).children().eq(4).text();
				let user_status = $(this).children().eq(5).text();
				let Comment_status = $(this).children().eq(6).text();
				
				$("#gbbcrl>input[name=Comment]").val(bno);
				
				$("#gbbcrl>input[name=userId]").val(CommentUser); 
				$("#gbbcrl>input[name=board_comment_title]").val(boardTitle);
				$("#gbbcrl>input[name=board_comment]").val(commentContent);
				
				
				$("#gbbcrl_group_category").text(category);
				$("#gbbcrl_group_board_comment_no").text(CommentNo);
				$("#gbbcrl_group_board_comment_title").text(boardTitle);
				$("#gbbcrl_group_board_comment_user").text(CommentUser);
				$("#gbbcrl_group_board_comment_content").text(commentContent);

				if(user_status.includes("이용중인 회원")) {
					
					$("#user_status").children().eq(0).attr("selected", true);
					
				} else {

					$("#user_status").children().eq(1).attr("selected", true);
				}
				
				if(Comment_status.includes("유지")) {
					$("#comment_status").children().eq(0).attr("selected", true);
				} else if(Comment_status.includes("삭제")){
					$("#comment_status").children().eq(1).attr("selected", true);
				}  else {
					$("#comment_status").children().eq(2).attr("selected", true);
				}
			})
		})
		
		</script>
		<script>
            const modal = document.querySelector('.modal');
            const btnOpenModal=document.querySelector('#group-board-comment-repotr-list-td');

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
            
			$(function() {

				$(".user-List>tbody>tr").click(function() {
					// console.log("잘 클릭되나??")
					// 글번호 : 현재 클릭된 tr의 첫번째 자손 td의 내용물
					let bno = $(this).children().eq(0).text();
					// console.log(bno)
					<%-- location.href = "<%= contextPath %>/detail.bo?bno=" + bno; --%>
					
				})
			})
		</script>
		<script>
		$(".input-group select[name=category]>option").each(function() {
			if($(this).val() == "<%=category%>") {
				
				$(this).attr("selected", true);
			}
		
		});
	 	</script>
		<script>
	$(".input-group select[name=comment_Status]>option").each(function() {
			if($(this).val() == "<%=comment_Status%>") {
				
				$(this).attr("selected", true);
			}
		
	});
	</script>
	</div>
</body>
</html>
</body>
</html>