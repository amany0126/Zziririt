<%@page import="com.zziririt.group.model.vo.GroupBoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zziririt.group.model.vo.Group"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%

	
GroupBoard gb = (GroupBoard)request.getAttribute("gb");
ArrayList<Group> list = (ArrayList<Group> )request.getAttribute("list");

%>
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

        <style>
     
           
    
              
                
            
        </style>
</head>

<body>
<%@ include file="/views/common/header.jsp" %>

     <div class="container-fluid">    
    
        <div class="container" id="contentDiv" align="center">          
                <!-- End of Topbar -->
      
                
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            <h6 class="m-0 font-weight-bold text-dark">그룹 게시판 작성하기</h6>
                            
                            
                            
                            
                        </div>
                
                        <!-- 게시글 작성 폼 -->
                        <div class="card-body">
          <form id="enroll-form" 
    action="<%= request.getContextPath() %>/insert.gr"
    method="post">
    
			<!-- 작성자의 회원번호도 넘기기 -->
			
			<input type="hidden" 
				   name="userNo"
				   value="<%= loginUser.getUserNo() %>">
	
    <div class="form-group">
        <label for="groupList">그룹선택</label>
        <select
			id="groupList" name="groupList"
			class="form-control border-3" >
			<%
			for (Group g : list) {
			%>
			<option value="<%=g.getGroupNo()%>"><%=g.getGroupName()%>
			</option>
			<%
			}
			%>
		</select>
    </div>
    <div class="form-group">
        <label for="groupTitle">제목:</label>
        <input type="text" 
            name="boardTitle" 
            class="form-control" 
            required >
    </div>
    <div class="form-group">
        <label for="groupContent">내용:</label>
        
        <pre><textarea name="boardContent" class="form-control" rows="6" required style="resize: none;"></textarea></pre> 
      
    </div>
    <div align="center">
        <button type="submit"
            class="btn btn-warning btn-sm">
            작성하기
        </button>
        <button type="reset"
            class="btn btn-secondary btn-sm">
            초기화
        </button>
    </div>

</form>


                        </div>
                    </div>
                </div>
                
 
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


    <!-- Core plugin JavaScript-->
   <script src="<%= request.getContextPath() %>/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
 	<script src="<%= request.getContextPath() %>/resources/js/sb-admin-2.min.js"></script>

<!-- jQuery 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script src="<%= request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>

</html>