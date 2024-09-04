<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zziririt.common.model.vo.PageInfo, java.util.ArrayList, com.zziririt.group.model.vo.GroupBoard" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
 <% 
 //응답데이터 뽑기
	PageInfo pi 
		= (PageInfo)request.getAttribute("pi");
 

	ArrayList<GroupBoard> list
		= (ArrayList<GroupBoard>)request.getAttribute("list");

	// 페이징바 출력 시 주로 쓰이는 변수들 따로 빼기
	// (매번 getter 호출하면 구문이 헷갈림)
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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

        <style>
            #navigator { height: 5%; 
            }

          
                .dropdown-list.dropdown-menu .dropdown-header {
                    background-color:#f6c23e; /* 또는 다른 색상 */
                    border: 1px solid #f6c23e;
                    
                }
           
                td {
                    text-align: center;
                }
              
                .list-area>tbody>tr:hover {
				/* background-color : lightgray; */
				cursor : pointer;
            
        
			    /* 글번호 숨기기 
			    th:nth-child(1),
			    td:nth-child(1) {
			        display: none;
			    }
			*/
    </style>
</head>

<body id="page-top">
<%@ include file="/views/common/header.jsp" %>

                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                       
                                    </div>
                           
                               
                            </div>
                        </li>

                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                            
                              
                           
                            </a>

                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                              
                                  
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                       
                               
                                  
                                </a>
                                    <div class="dropdown-list-image mr-3"></div>
                            </div>
                        </li>

                     

                <!-- End of Topbar -->
    <div class="container-fluid">
              <br><br>
    <!-- Page Heading -->
    			

                 
    <div class="card shadow mb-4">

        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-dark">전체 글</h6>
        </div>
     
 
        
        <div class="card-body ">

           <!-- 오른쪽: 검색 필터 -->
                    <div class="col-md-6">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="검색어를 입력하세요" id="searchInput">
                            <div class="input-group-append">
                                <button class="btn btn-warning" type="button" id="searchButton">검색</button>
                            </div>
                        </div>
                    </div>

            <!-- 리스트 부분-->
                <div class="table-responsive">
                    <table    class="list-area table table-hover" 
			   					align="center"
                            id="dataTable"
                            width="100%"
                            cellspacing="0">
            
                         
                            
                        <thead>
                            <tr align="center">

                               <!--  <th width="20px"></th> -->
                          
								<th width ="20px"  style="display: none;">글번호</th>
                                <th width="60px">제목</th>
                                <th width="60px">작성자</th>
                                <th width="20px">작성일</th>
                            </tr>
                        </thead>
            
                    <tbody>
				<% if(list.isEmpty()) { %>
					
					<tr>
						<td colspan="3">
							조회된 리스트가 없습니다.
						</td>
					</tr>
					
				<% } else { %>
				
					<% for(GroupBoard g : list) { %>
						
                            <tr>             
								<td style="display: none;"><%=g.getBoardNo()%></td>                                <td><%=g.getBoardTitle() %></td>
                                <td><%=g.getGroupMemNo() %></td>
                                <td><%= g.getWriteTime().substring(0, g.getWriteTime().indexOf(" ")) %></td>

                            </tr>
                          	
							<% } %>
							
						<% } %>
                        </tbody>
                    </table>
			<script>
				$(function() {
					
					$(".list-area>tbody>tr").click(function() {
						
						 console.log("잘 클릭되나..?");
						// 글번호 : 현재 클릭된 tr 의 첫번째 자손 td 내용물
						  let gno = $(this).children().eq(0).text();
						    location.href = "<%= contextPath %>/detail.gr?gno=" + gno;
					});
				});
				
			</script>
				<% if(loginUser != null) { %>
                  <div class="text-right">
                  <a href="<%= request.getContextPath() %>/enrollForm.gr" class="btn btn-warning btn-sm">
                  작성하기
                  </a>
                    </div>
				<br><br>
			</div>
		<% } %>
				</div>
	</div>
   </div>
  </div>   
    

<!-- 페이징바가 보여질 부분 -->
		<div align="center" class="paging-area">
			<button onclick="location.href='<%=contextPath %>/list.gr?currentPage=1'">&lt;&lt;</button>
			<%if(pi.getCurrentPage() != 1) {%>
			<button
				onclick="location.href='<%=contextPath %>/list.gr?currentPage=<%=pi.getCurrentPage() -1 %>'">&lt;</button>
			<%}else{ %>
			<button>&lt;</button>
			<%} %>
			<% for (int p =pi.getStartPage(); p<=pi.getEndPage(); p++) {%>
			<%if(p != pi.getCurrentPage()) {%>
			<!-- 페이지 이동 가능하게끔 -->
			<button
				onclick="location.href='<%=contextPath %>/list.gr?currentPage=<%=p %>'"><%=p %></button>
			<%}else{ %>
			<!-- 페이지 이동 불가능하게끔 -->
			<button disabled style="background-color: black; color: white;"><%=p %></button>
			<%} %>
			<%} %>
			<%if(pi.getCurrentPage() != pi.getMaxPage()) {%>
			<button onclick="location.href='<%=contextPath %>/list.gr?currentPage=<%=pi.getCurrentPage() +1 %>'">&gt;</button>
			<%}else{ %>
			<button>&gt;</button>
			<%} %>
			<button onclick="location.href='<%=contextPath %>/list.gr?currentPage=<%=pi.getMaxPage() %>'">&gt;&gt;</button>
		</div>




<script>
    $(document).ready(function(){
        $('#searchButton').click(function(){
            var searchText = $('#searchInput').val().toLowerCase(); // 검색어를 소문자로 변환하여 저장
            $('.list-area tbody tr').hide(); // 모든 행 숨김

            // 제목 또는 작성자 칼럼에서 검색어가 포함된 행을 보여줌
            $('.list-area tbody tr').each(function(){
                var title = $(this).find('td:eq(1)').text().toLowerCase(); // 제목
                var writer = $(this).find('td:eq(2)').text().toLowerCase(); // 작성자
                if(title.includes(searchText) || writer.includes(searchText)){
                    $(this).show(); // 검색어가 포함된 행을 보여줌
                }
            });
        });
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
        </div>
       
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->


  




    

  	<!-- Bootstrap core JavaScript-->
   	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Core plugin JavaScript-->
   <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
 	<script src="/resources/js/sb-admin-2.min.js"></script>


   <!-- jQuery 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script src="<%= request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html> 