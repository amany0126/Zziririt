<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.zziririt.member.model.vo.*, com.zziririt.board.model.vo.*"%>
<%
	// 응답데이터 뽑기
	Member m = (Member)request.getAttribute("m");
	Board b = (Board)request.getAttribute("b");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	a {
		text-decoration : none; /* 링크의 밑줄 제거 */
		color : inherit; /* 링크의 색상 제거 */
	}

	/* 전체를 감싸는 div */
	.container2 {
	    width : 1000px;
	    margin : auto;
	    margin-top : 50px;
	    box-sizing : border-box;
	}

	/* 전체 테이블 */
	.container2 table {
	    width : 100%;
	    border-collapse : collapse;
	    font-size : 16px;
	    text-align : center;
	}
	
	.container2 table td {
	    padding : 10px;
	    white-space : nowrap; /* 줄바꿈 방지 */
	    overflow : hidden; /* 넘치는 부분을 숨김 */
	    text-overflow : ellipsis; /* 넘치는 부분을 ...으로 표시 */
	}
	
	.border-table tr {
	    border : 2px solid black;
	    width : 110px;
	    border-left : none;
	    border-right : none;
	    border-top : none;
	}
	
	.container2 label {
	    font-size : 16px;
	    text-align : center;
	}
	
	/* 상단부 (프로필&제목 부분) */
	.boardHeader { width : 80px; }
	
	/* 프로필 */
	.rounded-profile {
	    border-radius : 50%;
	    width : 50px;
	    height : 50px;
	}
	
	.rounded-profile-container {
	    display : flex;
	    justify-content : center;
	    align-items : center;
	    margin-top : 20px;
	}
	
	.title-container {
	    display : flex;
	    align-items : center;
	}
	
	.title {
	    font-size : 28px;
	    margin : 0;
	    text-align : left;
	    width : 850px;
	    white-space : nowrap; /* 줄바꿈 방지 */
	    overflow : hidden; /* 넘치는 부분을 숨김 */
	    text-overflow : ellipsis; /* 넘치는 부분을 ...으로 표시 */
	}
	
	/* 닉네임 */
	.nickname {
	    font-size : 14px;
	    margin-top : 5px;
	    margin-bottom : 5px;
	    text-align : center;
	}
	
	/* 제목 */
	.boardTitle border-table tr {
	    height : 80px;
	    box-sizing : border-box;
	}
	
	/* 모집중 / 모집마감 딱지 */
	/* 모집중인 경우의 태그 */
	.price-info-ok {
	    text-align : right;
	    white-space : nowrap;
	    background-color : yellow;
	    padding : 2px 8px;
	    border-radius : 4px;
	    font-size : 16px;
	    color : black;
	    margin-left : -65px;
	}
	
	/* 모집마감인 경우의 태그 */
	.price-info-no {
	    text-align : right;
	    white-space : nowrap;
	    background-color : #888;
	    padding : 2px 8px;
	    border-radius : 4px;
	    font-size : 16px;
	    color : #fff;
	    margin-left : -75px;
	}

	/* 드롭다운 버튼 */
	.board-dropdown {
	    position : absolute;
	    margin-left : 870px;
	}
	
	.board-dropdown-toggle {
	    cursor : pointer;
	    color : #000;
	    font-size : 30px;
	}
	
	.board-dropdown-content {
	    display : none;
	    position : absolute;
	    background-color : #f9f9f9;
	    min-width : 120px;
	    box-shadow : 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	    z-index : 1;
	    padding : 10px;
	    text-align : center;
	}
	
	.board-dropdown-content a {
	    padding : 5px 0;
	    text-decoration : none;
	    color : black;
	    display : block;
	}

	.board-dropdown-content a:hover {
		background-color : rgb(230, 230, 230);
		text-decoration : none;
	    color : black;
	}
	
	/* 작성시간 */
	.date {
	    text-align : right;
	    padding : 0px 30px;
	    margin-bottom : -20px
	}
	
	/* 카테고리, 모임 일시, 모임 정원, 모임 장소 부분 */
	.info-table {
	    width : 1000px;
	    height : 100px;
	    margin : 20px auto;
	    overflow : hidden;
	}

	.info-table td,
	.info-table tr { border : 2px solid black; }
	
	.info-table td:nth-child(odd) { background-color : rgb(230, 230, 230); }
	
	/* 내용 */
	.contents {
	    text-align : center;
	    margin-top : -15px;
	}

	.container2 p#content {
	    width : 980px;
	    height : 300px;
	    resize : none;
	    /* padding : 8px; */
	    font-size : 16px;
	    box-sizing : border-box;
	    border : none;
	    text-align : left;
	}
	
	/* 좋아요 버튼 */
	#likeBtn { cursor : pointer; }
	/* 버튼이 disabled 상태일 때 배경색 유지 */
	#likeBtn:disabled {
	    color : black;
	    border-color : black;
	    cursor : default; /* 커서를 변경하여 사용자에게 비활성 상태임을 알림 */
	}
	/* 클릭 시 버튼 스타일 */
	.clicked { background-color : lightpink; }

	/* 댓글 */
	#comment-div {
	    border : 1px solid black;
	    padding : 10px;
	    padding-bottom : 7px;
	    margin-top : 10px;
	    width : 950px;
	}
	
	.comment-row #comment2-div { padding : 10px; }
	
	#comment-person {
	    width : 100%;
	    margin-bottom : 10px;
	}

	#board-comment,
	.comment-row #comments { resize : none; }
	
	.comment-row #comments { margin-top : 5px; }
	
	.comment-row #comments:focus { outline : none; }
	
	.rounded-profile-container2 { width : 30px; }	
	.comment-row .rounded-profile-container3 { width : 30px; }
	
	.rounded-profile2 {
	    border-radius : 50%;
	    width : 30px;
	    height : 30px;
	}
	
	.comment-row .rounded-profile3 {
	    border-radius : 50%;
	    width : 30px;
	    height : 30px;
	}

	/* 댓글을 쓴 작성자의 닉네임 */
	.nickname2 {
	    display : inline-block;
	    margin-left : 5px;
	    margin-top : 6px;
	}
	
	.comment-row .nickname3 {
	    display : inline-block;
	    margin-left : 5px;
	    margin-top : 6px;
	}
	
	.comment-row .comment-date {
	    display : inline-block;
	    /* text-align : right; */
	}
	
	#comment-personImage { margin-bottom : -30px; }
	.comment-row #comment-personImage2 { margin-bottom : -30px; }

	/* 댓글 등록 버튼 */
	#comment-submit { cursor : pointer; }
	#comment-submit:disabled { cursor : default; }
	
	.comment-row #comments { border : 1px solid black; }
	
	/* 신고 기능 드롭다운 버튼 */
	.comment-row .comment-dropdown { display : inline-block; }
	
	.comment-row .dropdown-toggle2 {
	    cursor : pointer;
	    color : #000;
	    font-size : 15px;
	}

	.comment-row .dropdown-content2 {
	    display : none;
	    position : absolute;
	    background-color : #f9f9f9;
	    min-width : 30px;
	    box-shadow : 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	    z-index : 1;
	    padding : 5px;
	    text-align : center;
	}

	.comment-row .dropdown-content2 a:hover {
		background-color : rgb(230, 230, 230);
		text-decoration : none;
	    color : black;
	}
	
	.comment-row .dropdown-content2 a {
	    text-decoration : none;
	    color : black;
	    display : block;
	}

	/* "목록으로" 버튼 */
	.container2 .list-button {
	    cursor : pointer;
	    font-size : 16px;
	    width : 120px;
	    height : 40px;
	    margin-top : 20px;
	}
	
	/* 첨부파일 */
	.list-td { width : 560px; }

	.upFiles td {
	    font-size : 14px;
	    text-align : left;
	}

	.upFiles tr:last-child { border-bottom : none; }
</style>
</head>
<body>
	
	<%@ include file="/views/common/header.jsp" %>
		
		<div class="container2" id="contentDiv" align="center">

			<table class="boardTitle border-table">
	            <tr>
	                <td class="boardHeader">
	                    <div class="rounded-profile-container">
	                    	<!-- 게시글 작성자가 프로필이 없는 사람이면 기본 이미지 띄우기 -->
	                    	<% if(b.getWriterProfile() == null || b.getWriterProfile().equals("resources/member_profiles/null")) { %>
	                    		<img src="resources/images/profile_ex5.png" alt="Profile Image" class="rounded-profile">
	                        <% } else { %>
	                        	<img src="<%= b.getWriterProfile() %>" alt="Profile Image" class="rounded-profile">
	                        <% } %>
	                    </div>
	                    <p class="nickname"><%= b.getBoardWriter() %></p>
	                </td>
	                <td style="margin-top : 30px;">
	                    <div class="title-container">
	                        <h2 class="title"><%= b.getBoardTitle() %></h2>
	                        <% if(b.getMeetingStatus() == 1) { %>
		                        <div class="price-info-ok">
		                            모집중
		                        </div>
		                    <% } else { %>
		                    	<div class="price-info-no">
		                            모집완료
		                        </div>
		                    <% } %>
		                    
	                        <!-- 드롭다운 - 이건 본인이 쓴 글에만 노출됨 -->
	                        <div class="board-dropdown">
	                            <span class="board-dropdown-toggle">⋮</span>
	                            <div id="mypageDropdown" class="board-dropdown-content">
	                                <% if(loginUser != null &&
										loginUser.getUserNickname().equals(b.getBoardWriter())) { %>
								
										<!-- 작성자만 보여지게끔 -->
										<a href="<%= contextPath %>/updateForm.bo?bno=<%= b.getBoardNo() %>"> 
											수정
										</a>
										<a href="#" onclick="confirmDelete();">
											삭제
										</a>
									<% } else { %>
										<a href="#" onclick="confirmReport();">
											신고
										</a>
									<% } %>
	                            </div>
	                            
	                            <!-- 게시글 삭제/신고 확인차 묻기 -->
	                            <script>
								    function confirmDelete() {
								        if (confirm("이 게시글을 정말로 삭제하시겠습니까?")) {
								            // 사용자가 확인을 선택한 경우에만 삭제 작업 수행
								            location.href 
								            	= "<%= contextPath %>/delete.bo?bno=<%= b.getBoardNo() %>";
								        }
								    }
								    
								    function confirmReport() {
								        if (confirm("이 댓글을 정말로 신고하시겠습니까?")) {
								            // 사용자가 확인을 선택한 경우에만 신고 작업 수행
								            location.href 
								            	= "<%= contextPath %>/rpcb?bno=<%= b.getBoardNo() %>";
								        }
								    }
								</script>
	                        </div>
	                    </div>
	                    <div class="date">조회수 <%= b.getCount() %> &nbsp;|&nbsp; <%= b.getCreateTime() %></div>
	                </td>
	            </tr>
	        </table>
	        
	        <!-- 게시글 수정/삭제 및 신고 드롭다운 버튼 js -->
			<script>
		        let dropdownToggle = document.querySelector(".board-dropdown-toggle");
		        let dropdownContent = document.querySelector(".board-dropdown-content");
		        
				<% if(loginUser != null) { %>
					dropdownToggle.addEventListener("click", function () {
					    if (dropdownContent.style.display === "block") {
					        dropdownContent.style.display = "none";
					    } else {
					        dropdownContent.style.display = "block";
					    }
					});
					
					document.addEventListener("click", function (event) {
					    if (!dropdownToggle.contains(event.target) && !dropdownContent.contains(event.target)) {
					        dropdownContent.style.display = "none";
					    }
					});
				<% } else { %>
					dropdownToggle.style.cursor = "default";
				<% } %>
		    </script>
	
	        <table class="info-table">
	            <tr>
	                <td style="width : 10%;">
	                    카테고리
	                </td>
	                <td style="width : 20%;"><%= b.getCategoryName() %></td>
	                <td style="width : 10%;">
	                    모임 일시
	                </td>
	                <td style="width : 40%;"><%= b.getMeetingTime() %></td>
	                <td style="width : 10%;">
	                    모임 정원
	                </td>
	                <td style="width : 10%;"><%= b.getPeopleLimit() %>명</td>
	            </tr>
	            <tr>
	                <td>
	                    모임 장소
	                </td>
	                <td colspan="5"><%= b.getMeetingSpot() %></td>
	            </tr>
	        </table>
	
	        <table class="contents border-table">
	            <tr style="border : none;">
	                <td colspan="6">
	                    <p id="content"><%= b.getBoardContent() %></p>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="6">
	                	<% if(loginUser == null) { %>
	                    	<button type="button" id="likeBtn" style="float : right;" disabled>좋아요 <span id="likeCount">0</span></button>
	                	<% } else { %>
	                		<button type="button" id="likeBtn" style="float : right;">좋아요 <span id="likeCount">0</span></button>
	                	<% } %>
	                </td>
	            </tr>
	        </table>

	        <table id="uploads-area" class="upFiles border-table">
	        	<thead>
		            <tr>
		                <td class="td-file">
		                    <span class="span-file">
		                        첨부파일:&nbsp;
	                        	<% if(b.getFileOriginName() == null) { %>
	                            	<span></span>
	                            <% } else { %>
	                            	<%-- <span><%= b.getFileOriginName() %></span> --%>
	                            	<a download="<%= b.getFileOriginName() %>" 
									   href="<%= contextPath %>/<%= b.getFileInfo() %>" 
									   style="text-decoration : none;">
										<%= b.getFileOriginName() %>
									</a>
	                            <% } %>
		                    </span>
		                </td>
		            </tr>
		            
		            <!-- 댓글 작성 -->
		            <tr>
		                <td>
		                    총 <span id="replyTotal">0</span>개의 댓글
		                    <% if(loginUser != null) { %>
		                    
		                    	<!-- 로그인이 되어있을 경우 -->
			                    <fieldset id="comment-div">
			                        <div id="comment-person">
			                            <div class="rounded-profile-container2" style="float : left;">
			                                <!-- 댓글 작성자가 프로필이 없는 사람이면 기본 이미지 띄우기 -->
						                   	<% if(loginUser.getUserProfile() == null || loginUser.getUserProfile().equals("resources/member_profiles/null")) { %>
						                   		<img src="resources/images/profile_ex5.png" alt="Profile Image" class="rounded-profile2">
						                    <% } else { %>
						                       	<img src="<%= loginUser.getUserProfile() %>" alt="Profile Image" class="rounded-profile2">
						                    <% } %>
			                            </div>
			                            <div class="nickname2">
			                                <%= loginUser.getUserNickname() %>
			                            </div>
			                            <br clear="both">
			                        </div>
			                        <hr>
			                        <div>
			                            <textarea id="board-comment" name="comment" style="width : 99%;" rows="4" maxlength="300"
			                                placeholder="댓글은 공백 포함 300자까지만 작성 가능합니다. 비방이나 욕설을 쓰면 신고 당할 수 있습니다."></textarea>
			                            <br>
			                            <hr>
			                            <span id="board-count" style="color : black;">0</span> / 300자
			                            <input type="submit" id="comment-submit" style="float : right;" value="등록" onclick="insertReply();">
			                        </div>
			                    </fieldset>
			            	<% } else { %>
			            	
			            		<!-- 로그인이 안되어있을 경우 -->
			            		<fieldset id="comment-div">
			                        <div id="comment-person">
			                            <div class="rounded-profile-container2" style="float : left;">
			                                <img src="resources/images/profile_ex5.png" alt="Profile Image" class="rounded-profile2">
			                            </div>
			                            <div class="nickname2">
			                                
			                            </div>
			                            <br clear="both">
			                        </div>
			                        <hr>
			                        <div>
			                            <textarea id="board-comment" name="comment" style="width : 99%;" rows="4" maxlength="300"
			                                	  readonly>로그인 후 이용 가능한 서비스 입니다.</textarea>
			                            <br>
			                            <hr>
			                            <span id="board-count" style="color : black;">0</span> / 300자
			                            <button type="button" id="comment-submit" style="float : right;" disabled>
			                            	등록
			                            </button>
			                        </div>
			                    </fieldset>
			            	<% } %>
		                </td>
		            </tr>
	            </thead>
	            <tbody>
	            </tbody>
	            
	            <!-- 댓글 기능 -->
	           	<script>
		         	// 게시글 상세조회 페이지에서
					// 게시글 상세조회 페이지가 다 로딩된 이후에
					// 해당 게시글에 딸린 댓글들만 조회할 수 있는 요청을 ajax 로 보내기
		           	$(function() {
		           		
		           		console.log("<%= b %>");
		           		
						// selectReplyList();
						
						// 1초 간격마다 selectReplyList 함수를 실행			
						// setInterval(selectReplyList, 5000);
						
		           		// 초기화할 때 한 번만 호출될 selectReplyList 함수
		           	    // selectReplyList();

		           	    // 주기적인 댓글 목록 조회
		           	    /*  let intervalId = setInterval(selectReplyList, 1000); */

		           	    // 드롭다운 토글 클릭 이벤트 핸들러
		           	    /* $(".comment-dropdown .dropdown-toggle2").on("click", function () {
		           	        let notifyDropdown = $(this).closest(".comment-dropdown").find(".dropdown-content2");

		           	        if (notifyDropdown.css("display") === "block") {
		           	            notifyDropdown.css("display", "none");
		           	            // 드롭다운이 닫힐 때 setInterval을 다시 시작
		           	            intervalId = setInterval(selectReplyList, 1000);
		           	        } else {
		           	            notifyDropdown.css("display", "block");
		           	            // 드롭다운이 열릴 때 setInterval을 해제
		           	            clearInterval(intervalId);
		           	        }
		           	    });

		           	    // 문서 클릭 이벤트 핸들러
		           	    $(document).on("click", function (event) {
		           	        let dropdownToggle2 = $(".comment-dropdown .dropdown-toggle2");
		           	        let notifyDropdown = $(".comment-dropdown .dropdown-content2");

		           	        if (!dropdownToggle2.is(event.target) && !notifyDropdown.is(event.target) && notifyDropdown.has(event.target).length === 0) {
		           	            notifyDropdown.css("display", "none");
		           	            // 문서 클릭 시 setInterval을 다시 시작
		           	            intervalId = setInterval(selectReplyList, 1000);
		           	        }
		           	    }); */
					});
		           	
		         	// 댓글작성 요청용 함수
		           	function insertReply() {
		           	    var replyContent = $("#board-comment").val().trim(); // 공백 제거 후 입력 값 가져오기
		           	    // textarea에 입력된 값이 한 글자 이상인 경우에만 처리
		           	    if (replyContent.length > 0) {
		           	        $.ajax({
		           	            url : "<%= contextPath %>/rinsert.bo",
		           	            type : "post",
		           	            data : {
		           	                replyContent : replyContent,
		           	                bno : <%= b.getBoardNo() %>
		           	            },
		           	            success : function(result) {
		           	                // console.log(result);
		           	                if (result > 0) { // 성공
		           	                	// replyCount(); // 댓글 개수 세기
		           	                	
		           	                    // 갱신된 댓글 리스트 조회
		           	                    selectReplyList();
		           	                    // > 화면이 깜빡거리지 않고도 새로고침 효과를 줌

		           	                    // 댓글 글자수, textarea 초기화(비우기)
		           	                    $("#board-count").text(0);
		           	                    $("#board-comment").val("");
		           	                }
		           	            },
		           	            error : function() {
		           	                console.log("댓글 작성용 ajax 통신 실패!");
		           	            }
		           	        });
		           	    } else {
		           	        // textarea에 입력이 없는 경우에 대한 처리
		           	        alert("댓글을 입력해주세요.");
		           	    }
		           	}
		         	
					// 댓글목록 조회 요청용 함수
					// $(document).ready(function() {
						function selectReplyList() {
					    	$.ajax({
					        	url: "<%= contextPath %>/rlist.bo",
					            type: "get",
					            data: { bno: <%= b.getBoardNo() %> },
					            success: function(list) {
					               let resultStr = "";
					               for (let i in list) {
					                   resultStr += "<tr class='comment-row' data-reply-no='" + list[i].replyNo + "' style='border-top : 2px solid black;'>" +
					                       "<td>" +
					                       "<div id='comment-person' style='border : none; display: flex;'>" +
					                       "<div style='width : 50%; display: flex;' align='left'>" +
					                       "<div id='comment-personImage2' class='rounded-profile-container3' style='float : left;'>" +
					                       (list[i].replyWriterProfile ? "<img src='" + list[i].replyWriterProfile + "' alt='Profile Image' class='rounded-profile3'>" : "<img src='resources/images/profile_ex5.png' alt='Profile Image' class='rounded-profile3'>") +
					                       "</div>" +
					                       "<div class='nickname3'>" +
					                       list[i].replyWriter +
					                       "</div>" +
					                       "</div>" +
					                       "<div style='width : 50%; padding-top : 7px;' align='right'>" +
					                       "<div class='comment-date'>" +
					                       list[i].createTime +
					                       "</div>" +
					                       "<div class='comment-dropdown'>" +
					                       "<span class='dropdown-toggle2'>&nbsp;⋮&nbsp;</span>" +
					                       "<div class='dropdown-content2'>";
					
					                   if (<%= loginUser != null %> && "<%= loginUser.getUserNickname() %>" === list[i].replyWriter) {
					                       // 작성자일 경우 (삭제)
					                       resultStr += "<a href='#' class='delete-link'>삭제</a>";
					                   } else {
					                       // 작성자가 아닐 경우 (신고)
					                       resultStr += "<a href='#' class='report-link'>신고</a>";
					                   }
					
					                   resultStr += "</div>" +
					                       "</div>" +
					                       "</div>" +
					                       "<br clear='both'>" +
					                       "</div>" +
					                       "<div>" +
					                       "<textarea id='comments' name='comments' style='width : 99%;' rows='4' maxlength='300' readonly>" + list[i].replyContent + "</textarea>" +
					                       "</div>" +
					                       "</td>" +
					                       "</tr>";
					               }
					               $("#uploads-area tbody").html(resultStr);
					
					               // 각 댓글의 드롭다운 토글 클릭 이벤트 핸들러
					               $(".dropdown-toggle2").on("click", function() {
					                   // 모든 드롭다운 숨기기
					                   $(".dropdown-content2").hide();
					
					                   // 클릭한 드롭다운이 속한 댓글의 번호 출력
					                   let replyNo = $(this).closest('.comment-row').data('reply-no');
					                   console.log("댓글 번호:", replyNo);
					
					                   let notifyDropdown = $(this).closest(".comment-row").find(".dropdown-content2");
					
					                   if (notifyDropdown.css("display") === "block") {
					                       notifyDropdown.css("display", "none");
					                   } else {
					                       notifyDropdown.css("display", "block");
					                   }
					               });
					
					               // 외부 클릭 시 드롭다운 닫기
					               $(document).on("click", function(event) {
					                   if (!$(event.target).closest('.comment-dropdown').length) {
					                       $(".dropdown-content2").hide();
					                   }
					               });
					
					               // 댓글 삭제 요청
					               $(".delete-link").on("click", function(event) {
					            	   confirm("정말 이 댓글을 삭제하시겠습니까?")
					                   event.preventDefault();
					                   let replyNo = $(this).closest('.comment-row').data('reply-no');
					                   deleteReply(replyNo);
					               });
					               
					               // 댓글 신고 요청
					               $(".report-link").on("click", function(event) {
					            	   confirm("정말 이 댓글을 신고하시겠습니까?");
					            	   event.preventDefault();
					            	   let replyNo = $(this).closest('.comment-row').data('reply-no');
					            	   reportReply(replyNo);
					               });
					               
					           	   // 댓글 개수 찍어내기
					          	   $("#replyTotal").text(list.length);
					           },
					           error: function() {
					               console.log("댓글리스트 조회용 ajax 통신 실패!");
					           }
					       });
					   }
					
					   // 댓글 삭제 요청용 함수
					   function deleteReply(replyNo) {
					       $.ajax({
					       		url : "<%= contextPath %>/rdelete.bo",
					            type : "post",
					            data : { replyNo: replyNo },
					            success : function(result) {
					                if (result > 0) {
					                	// replyCount();
					                    selectReplyList();
					                    alert("성공적으로 댓글이 삭제되었습니다.");
					                }
					            },
					            error : function() {
					                console.log("댓글 삭제용 ajax 통신 실패!");
					            }
					        });
					   }
					   
					   // 댓글 신고 요청용 함수
					   function reportReply(replyNo) {
						   $.ajax({
							   url : "<%= contextPath %>/rpcr",
							   type : "post",
							   data : { replyNo : replyNo },
							   success : function(result) {
								   if (result > 0) {
									   selectReplyList();
									   alert("성공적으로 댓글 신고가 접수되었습니다.");
								   }
							   },
							   error : function() {
								   console.log("댓글 삭제용 ajax 통신 실패!");
							   }
						   });
					   }
					   
						// 현재 이 게시글의 댓글 수 세기 (사용 안함)
						<%-- function replyCount() {
							$.ajax({
								url : "<%= contextPath %>/rselect.bo",
								type : "get",
								data : {bno : <%= b.getBoardNo() %> },
								success : function(result) {
									console.log(result);
									$("#replyTotal").text(result);
								},
								error : function() {
									console.log("댓글 조회용 ajax 통신 실패!");
								}
							});
						} --%>
					   
					    // 초기 댓글목록 조회
					    selectReplyList();
					// });
	           	</script>

 	            <!-- <tr>
	                <td>
	                    <div id="comment-person" style="border : none; display: flex;"> -->
	                        <!-- 프로필과 아이디 -->
	                        <!-- <div style="width : 50%; display: flex;" align="left">
	                            <div id="comment-personImage" class="rounded-profile-container2" style="float : left;">
	                                <img src="resources/images/koongya.jpg" alt="Profile Image" class="rounded-profile2">
	                            </div>
	                            <div class="nickname2">
	                                쿵야
	                            </div>
	                        </div> -->
	                        <!-- 날짜와 드롭다운 -->
	                        <%-- <div style="width : 50%; padding-top : 7px;" align="right">
	                            <div class="comment-date">
	                                2024. 04. 02. 오후 5:12
	                            </div>
	                            <div class="comment-dropdown">
	                                <span class="dropdown-toggle2">&nbsp;⋮&nbsp;</span>
	                                <div id="notifyDropdown" class="dropdown-content2">
	                                    <% if(loginUser != null &&
	                                    		loginUser.getUserNickname().equals(b.getBoardWriter())) { %> --%>
								
											<!-- 작성자만 보여지게끔 -->
											<%-- <a href="">
												삭제
											</a>
										<% } else { %>
											<a href="">신고</a>
										<% } %>
		                        	</div>
	                            </div>
	                        </div>
	                        
	                        <br clear="both">
	                    </div>
	                    <div>
	                        <textarea id="comments" name="comments" style="width : 99%;" rows="4" maxlength="300"
	                            readonly>거기 음식 ㅈㄴ 맛없어요</textarea>
	                    </div>
	                </td>
	            </tr> --%>
	            <!-- <tr>
	                <td>
	                    <div id="comment-person" style="border : none; display: flex;" > -->
	                        <!-- 프로필과 아이디 -->
	                        <!-- <div style="width : 50%; display: flex;" align="left">
	                            <div id="comment-personImage" class="rounded-profile-container2" style="float : left;">
	                                <img src="resources/images/flower.jpg" alt="Profile Image" class="rounded-profile2">
	                            </div>
	                            <div class="nickname2">
	                                뎡미니
	                            </div>
	                        </div> -->
	                        <!-- 날짜와 드롭다운 -->
	                        <!-- <div style="width : 50%; padding-top : 7px;" align="right">
	                            <div class="comment-date">
	                                2024. 04. 02. 오후 1:57
	                            </div>
	                            <div class="comment-dropdown">
	                                <span class="dropdown-toggle3">&nbsp;⋮&nbsp;</span>
	                                <div id="notifyDropdown2" class="dropdown-content2">
	                                    수정 및 삭제는 본인이 작성한 댓글일 경우
	                                    <a href="">수정</a>
	                                    <a href="">삭제</a>
	                                    <a href="">신고</a>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <br clear="both">
	                    </div>
	                    <div>
	                        <textarea id="comments" name="comments" style="width : 99%;" rows="4" maxlength="300"
	                            readonly>님 롤 티어 어딤?</textarea>
	                    </div>
	                </td>
	            </tr> -->
	            <tfoot>
		            <tr style="border-top : 2px solid black">
		                <th class="list-th"><button class="list-button" onclick="openBoardList();">목록으로</button></th>
		            </tr>
	            </tfoot>
	        </table>
			
			<br><br>
		</div>

    <!-- 댓글 신고 드롭다운 버튼 js -->
    <%--  <script>
	    $(document).ready(function() {
	        let dropdownToggle2 = $(".dropdown-toggle2");
	        let notifyDropdown = $("#notifyDropdown");
	
	        <% if(loginUser != null) { %>
	            dropdownToggle2.on("click", function () {
	                if (notifyDropdown.css("display") === "block") {
	                    notifyDropdown.css("display", "none");
	                } else {
	                    notifyDropdown.css("display", "block");
	                }
	            });
	            
	            $(document).on("click", function (event) {
	                if (!dropdownToggle2.is(event.target) && !notifyDropdown.is(event.target) && notifyDropdown.has(event.target).length === 0) {
	                    notifyDropdown.css("display", "none");
	                }
	            });
	        <% } else { %>
	            dropdownToggle2.css("cursor", "default");
	        <% } %>
	    });
    </script> --%>
    <!-- <script>
        let dropdownToggle3 = document.querySelector(".dropdown-toggle3");
        let notifyDropdown2 = document.querySelector("#notifyDropdown2");

        dropdownToggle3.addEventListener("click", function () {
            if (notifyDropdown2.style.display === "block") {
                notifyDropdown2.style.display = "none";
            } else {
                notifyDropdown2.style.display = "block";
            }
        });

        document.addEventListener("click", function (event) {
            if (!dropdownToggle3.contains(event.target) && !notifyDropdown2.contains(event.target)) {
                notifyDropdown2.style.display = "none";
            }
        });
    </script> -->

    <!-- 좋아요 기능 js -->
    <script>
    
     	// 좋아요 기능 > ajax 로 구현
		$(function() {
			selectLikeCount();
			
			<% if(loginUser != null) { %>
				likeCheck();
			<% } %>
		});
     	
		// 현재 이 게시글에 로그인한 사용자가 좋아요를 눌렀었나 검사
		function likeCheck() {
		    $.ajax({
		        url : "<%= contextPath %>/likeCheck.bo",
		        type : "get",
		        data : {bno : <%= b.getBoardNo() %> },
		        success : function(result) {
		            // 세션에 좋아요를 눌렀는지 여부를 확인하여 버튼의 배경색을 변경
		            if(result == 1) { // 좋아요 누른 상태
		                $("#likeBtn").addClass("clicked");
		            } else { // 좋아요 안 누른 상태
		                $("#likeBtn").removeClass("clicked");
		            }
		        },
		        error : function() {
		            console.log("좋아요 확인용 ajax 통신 실패!");
		        }
		    });
		}

		// 좋아요 버튼 클릭 이벤트 핸들러
		$("#likeBtn").click(function () {
		    // 좋아요 버튼에 clicked 클래스 추가 (토글 기능)
		    $(this).toggleClass("clicked");

		    // clicked 클래스가 추가되었는지 확인하여 요청 처리
		    if ($(this).hasClass("clicked")) {
		        console.log("좋아요 insert 요청");
		        // TB_LIKE 테이블에 좋아요 insert 요청
		        $.ajax({
		            url : "<%= contextPath %>/linsert.bo",
		            type : "get",
		            data : {bno : <%= b.getBoardNo() %> },
		            success : function() {
		                likeCheck();
		                selectLikeCount();
		            },
		            error : function() {
		                console.log("좋아요 ajax 통신 실패!");
		            }
		        });
		    } else {
		        console.log("좋아요 delete 요청");
		        // TB_LIKE 테이블에 좋아요 delete 요청
		        $.ajax({
		            url : "<%= contextPath %>/ldelete.bo",
		            type : "get",
		            data : {bno : <%= b.getBoardNo() %> },
		            success : function(result) { 
		                likeCheck();
		                selectLikeCount();
		            },
		            error : function() {
		                console.log("좋아요 ajax 통신 실패!");
		            }
		        });
		    }
		});

		// 좋아요 버튼 초기화
		$(document).ready(function() {
		    // 페이지가 로드될 때 좋아요 상태를 확인하여 버튼의 배경색을 변경
		    likeCheck();
		});
		
		// 현재 이 게시글의 좋아요 수 세기
		function selectLikeCount() {
			$.ajax({
				url : "<%= contextPath %>/lselect.bo",
				type : "get",
				data : {bno : <%= b.getBoardNo() %> },
				success : function(result) {
					$("#likeCount").text(result);
				},
				error : function() {
					console.log("좋아요 조회용 ajax 통신 실패!");
				}
			});
		}
    </script>

    <!-- 댓글 글자 수 세는 js -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            let introduceInput = document.getElementById("board-comment");
            let countDisplay = document.getElementById("board-count");

            introduceInput.addEventListener("input", function () {
                countDisplay.textContent = this.value.length; // 공백 포함
            });
        });
    </script>

    <!-- 목록으로 버튼 js -->
    <script>
	    function openBoardList() {
	        location.href = "<%= contextPath %>/list.bo?currentPage=1";
	    }
    </script>
</body>
</html>