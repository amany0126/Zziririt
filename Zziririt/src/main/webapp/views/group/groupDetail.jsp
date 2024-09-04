
<%@page import="com.zziririt.group.model.service.GroupService"%>
<%@page import="com.zziririt.group.model.vo.GroupBoard"%>
<%@page import="com.zziririt.group.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zziririt.group.model.vo.Group"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
// 응답데이터인 카테고리 리스트 뽑기
ArrayList<Group> list
	= (ArrayList<Group>)request.getAttribute("list");



	
GroupBoard gb = (GroupBoard)request.getAttribute("gb");



%>

    
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>찌리릭</title>

   <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/sb-admin-2.min.css">

   
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

	<!-- Custom fonts for this template-->
    <link href="<%= request.getContextPath() %>/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%= request.getContextPath() %>/resources/css/sb-admin-2.min.css" rel="stylesheet">
<!-- Bootstrap 스타일시트 포함 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">



        <style>
            #navigator { height: 5%; 
            }

          
                .dropdown-list.dropdown-menu .dropdown-header {
                    background-color:#f6c23e; /* 또는 다른 색상 */
                    border: 1px solid #f6c23e;
                    
                }
           
    
           .author {
	        margin-left: 10px; /* 작성자에 오른쪽 여백 추가 */
	    }
	    
	    .date {
	        margin-right: 10px; /* 작성일에 왼쪽 여백 추가 */
	    }   
                
            
         .outer table {
		border : 1px solid lightgray;
		border-spacing : 0px;
	}
	
			#reply-area {
	    margin-bottom: 20px;
	}
	
	#reply-area textarea {
	    width: calc(100% - 110px); /* 버튼 폭 고려하여 너비 조정 */
	    margin-right: 10px;
	    resize: none;
	}
	
	#reply-area button {
	    width: 100px;
	}
	
	.comment-section {
    margin-bottom: 20px;
}

.comment-section textarea {
    width: calc(100% - 110px); /* 버튼 폭 고려하여 너비 조정 */
    margin-right: 10px;
    resize: none;
}

.comment-section button {
    width: 100px;
}
	

    table {
    width: 100%; /* 테이블 전체 너비를 화면 너비에 맞게 설정 */
    border-collapse: separate; /* separate로 변경 */
    border-spacing: 0; /* 테두리 간격 제거 */
}

    
    th, td {
        border: 1px solid #ddd; /* 테이블 셀에 실선 테두리 적용 */
        padding: 8px; /* 셀 안의 내용과 테두리 사이의 간격 설정 */
        text-align: left; /* 텍스트를 왼쪽 정렬 */
    }
    
    th {
        background-color: #f2f2f2; /* 테이블 헤더 배경색 설정 */
    }

    /* 댓글창의 텍스트 에어리어 스타일 */
    #replyContent {
        width: calc(100% - 20px); /* 텍스트 에어리어의 너비를 테이블 셀 너비에 맞게 설정 */
        margin: 0; /* 기본 마진 제거 */
    }

    /* 댓글 등록 버튼 스타일 */
    .btn-warning {
        margin-top: 5px; /* 버튼 위쪽 마진 추가 */
    }
	
	/* 신고 버튼 색상 */
	.btn-declare-comment {
	    background-color: #ced4da !important; /* 연한 회색 */
	    border-color: #ced4da !important; /* 연한 회색 */
	}
	
    /* 삭제 버튼 색상 */
    .btn-delete-comment {
        background-color: #495057 !important; /* 진한 회색 */
        border-color: #495057 !important; /* 진한 회색 */
    }
        </style>
</head>

<body>
<%@ include file="/views/common/header.jsp" %>
    <!-- Page Wrapper -->
	<div class="container-fluid">
                
                <!-- End of Topbar -->
               
       
           <div class="container" id="contentDiv" align="center">
                <!-- Begin Page Content -->


<!-- Dropdown Card Example -->
<div class="card shadow mb-4">

    <!-- Card Header - Dropdown -->
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-dark"> 제목 : <%= gb.getBoardTitle()%></h6>
        <div class="dropdown no-arrow show">
            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in " aria-labelledby="dropdownMenuLink" x-placement="bottom-end" style="position: absolute; transform: translate3d(-158px, 19px, 0px); top: 0px; left: 0px; will-change: transform;">
                <div class="dropdown-header">글</div>
                 
			<% if(loginUser != null &&
					loginUser.getUserNickname().equals(gb.getGroupMemNo())) { %>
			
                <!-- 작성자만 보여지게끔 -->
                <a class="dropdown-item"  href="<%= request.getContextPath() %>/updateFrom.gr?gno=<%= gb.getBoardNo() %>">
                    수정하기
                </a>
                <a class="dropdown-item" href="<%= request.getContextPath() %>/delete.gr?gno=<%= gb.getBoardNo() %>">
                    삭제하기
                </a>
				<% } else { %>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<%= request.getContextPath() %>/declare.gr?gno=<%= gb.getBoardNo() %>">
                	신고하기
                </a>
                <% } %>
            </div>
        </div>
    </div>

    <!-- 작성자 및 작성일 -->
   <div class="mt-2 d-flex justify-content-between">
    <div class="text-muted small author">작성자: <%=gb.getGroupMemNo()%></div>
    <div class="text-muted small date">작성일 : <%= gb.getWriteTime().substring(0, gb.getWriteTime().indexOf(" ")) %></div>
</div>

    <div class="card-body">
        <pre><%= gb.getBoardContent() %></pre>
    </div>

   
 
 
     
     
   
</div>

<div align="center">
    <a href="<%= contextPath %>/list.gr?currentPage=1" class="btn btn-secondary btn-sm">
        목록가기
    </a>
    <br><br>
    

		
		
		
		     <!-- 댓글이 추가될 카드 바디 부분 -->
    <div class="card-body">
		<!-- 댓글창 (우선 화면구현만, AJAX 기술 배우고 기능 붙이기) -->
		<div id="reply-area">
			<table align="center" border="1" >

				<thead>
    
				<% if(loginUser != null) { %>
				
    			<!-- 로그인이 되어있을 경우 -->
						<tr>
							<th>댓글작성</th>
							 <td colspan="2">
								<textarea id="replyContent"		
										class ="form-control"								
										 cols="50"
										  rows="3" 
										  style="resize : none;"></textarea>
							</td>
							<td>
								<button type="button" class="btn btn-warning"
										onclick="insertComment();">
									댓글등록
								</button>
							</td>
						</tr>
						
					<% } else { %>
	
						<!-- 로그인이 안되어있을 경우 -->
						<tr>
							<th>댓글작성</th>
							<td colspan="2">
								<textarea 
										  id="" cols="50" 
										  rows="3"
										  style="resize : none;"
										  readonly>로그인 후 이용 가능한 서비스 입니다.</textarea>
							</td>
							 <td style="text-align: center;">
								<button type="button"
										disabled>
									댓글등록
								</button>
							</td>
						</tr>
				
				</thead>
					<% } %>
			<!-- 댓글 목록 출력부분 -->
			<tbody id="comment-list">
	
			</tbody>


			</table>
		</div>
 </div>
		<script>

			// 게시글 상세조회 페이지에서
			// 게시글 상세조회 페이지가 다 로딩된 이후에
			// 해당 게시글에 딸린 댓글들만 조회할 수 있는 요청을 ajax 로 보내기
			
			$(function() {
				
				selectCommentList();
				
				// 1초 간격마다 selectReplyList 함수를 실행			
				setInterval(selectCommentList, 1000);
				
			});
			
			// 댓글작성 요청용 함수
			function insertComment() {
				
				$.ajax({
					url : "<%= contextPath %>/rinsert.gr",
					type : "post",
					data : {
						replyContent : $("#replyContent").val(),
						gno : <%= gb.getBoardNo() %>,
						
						//
						<%-- userNo : <%= loginUser.getUserNo() %> --%>
						// > 로그인 전 게시글 상세보기 페이지 요청 시
						//   loginUser 가 null 이므로 NullPointerException 발생
					},
					success : function(result) {
						
						// console.log(result);
						
						if(result > 0) { // 성공
							
							// 갱신된 댓글 리스트를 조회
							selectCommentList();
							// > 화면이 깜빡거리지 않고도 새로고침 효과를 주기 위해
							
							// textarea 초기화
							$("#replyContent").val("");
						}
						
					},
					error : function() {
						console.log("댓글 작성용 ajax 통신 실패!");
					}
				});
				
			}
			
			// 댓글 목록 조회 요청용 함수
			function selectCommentList() {
			    $.ajax({
			        url: "<%= contextPath %>/rlist.gr",
			        type: "get",
			        data: { gno: <%=gb.getBoardNo() %> },
			        success: function (list) {
			            let resultStr = "";
			            for (let i in list) {
			            	
			                resultStr += "<tr>"
			                    + "<td>" + list[i].userNickName + "</td>"
			                    + "<td>" + list[i].content + "</td>"
			                    + "<td>" + list[i].writeTime + "</td>"
			                    + "<td>";
			                // 댓글 작성자와 로그인한 사용자가 동일한 경우에만 수정 및 삭제 버튼 표시
			                if (list[i].userNickName === "<%= loginUser.getUserNickname() %>") {
			                    resultStr += "<button type='button' class='btn btn-light' onclick='updateComment(" + list[i].commentNo + ")'>수정</button>"
			                        + "&nbsp;"+ "<button type='button' class='btn btn-sm btn-danger btn-delete-comment' onclick='deleteComment(" + list[i].commentNo + ")'>삭제</button>"  + "&nbsp;";
			                } else {
			                	resultStr += "<button btn btn-primary class='btn btn-sm btn-declare-comment' type='button' onclick='declareComment(" + list[i].commentNo + ")'>신고</button>" + "&nbsp;";
			                }
			                resultStr += "</td>"
			                    + "</tr>";
			            }
			            $("#comment-list").html(resultStr);
			        },
			        error: function () {
			            console.log("댓글리스트 조회용 ajax 통신 실패!");
			        }
			    });
			}

	
			//댓글 수정 요청용 함수
			function updateComment(commentNo) {
			    var updatedContent = prompt("수정할 내용을 입력하세요:");
			    if (updatedContent !== null) {
			        $.ajax({
			            url: "<%= request.getContextPath() %>/updateComment.gr",
			            type: "POST",
			            data: { commentNo: commentNo, updatedContent: updatedContent },
			            success: function (result) {
			                if (result === "success") {
			                    alert("댓글이 성공적으로 수정되었습니다.");
			                    // 원하는 동작 수행 (예: 수정된 내용으로 댓글 갱신)
			                    selectCommentList();
			                } else {
			                    alert("댓글 수정에 실패하였습니다.");
			                }
			            },
			            error: function () {
			                console.log("댓글 수정용 ajax 통신 실패!");
			            }
			        });
			    }
			}
			
			

			// 댓글 삭제 요청용 함수
			function deleteComment(commentNo) {
			    if(confirm("정말로 삭제하시겠습니까?")) {
			        $.ajax({
			            url: "<%= contextPath %>/deleteComment.gr",
			            type: "POST",
			            data: {commentNo: commentNo},
			            success: function(result) {
			                if(result === "success") {
			                    alert("댓글이 성공적으로 삭제되었습니다.");
			                    selectCommentList();
			                } else {
			                    alert("댓글 삭제에 실패하였습니다.");
			                }
			            },
			            error: function() {
			                console.log("댓글 삭제용 ajax 통신 실패!");
			            }
			        });
			    }
			}
			

			// 댓글 신고 요청용 함수
			function declareComment(commentNo) {
			    if (confirm("정말로 신고하시겠습니까?")) {
			        $.ajax({
			            url: "<%= contextPath %>/declareComment.gr",
			            type: "POST",
			            data: { commentNo: commentNo },
			            success: function (result) {
			                if (result === "success") {
			                    alert("댓글이 성공적으로 신고되었습니다.");
			                    
			                    selectCommentList();
			                } else {
			                    alert("댓글 신고에 실패하였습니다.");
			                }
			            },
			            error: function () {
			                console.log("댓글 신고 ajax 통신 실패!");
			            }
			        });
			    }
			}

				$(function() {
				    // 페이지 로딩 시 댓글 목록 조회
				    selectCommentList();
				    // 1초마다 댓글 목록을 업데이트
				    setInterval(selectCommentList, 1000);
				});

</script>


            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>@은서가 환승한 이유</span>
                    </div>
                </div>
            </footer> <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->


   

  <!-- Bootstrap core JavaScript-->
   	<script src="<%= request.getContextPath() %>/resources/vendor/jquery/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


  
    <!-- Custom scripts for all pages-->
 	<script src="<%= request.getContextPath() %>/resources/js/sb-admin-2.min.js"></script>



    <!-- jQuery 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script src="<%= request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
</body>

</html>