<%@page import="com.zziririt.group.model.vo.GroupBoard"%>
<%@page import="com.zziririt.group.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zziririt.group.model.vo.Group"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

	GroupBoard gb = (GroupBoard)request.getAttribute("gb");

	ArrayList<Group> list = (ArrayList<Group> )request.getAttribute("list");



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

   <link rel="stylesheet" type="text/css" href="/resources/css/sb-admin-2.min.css">

   
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

	<!-- Custom fonts for this template-->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

        <style>
        
      
	          .outer {
            margin-top: 50px;
        }
        h2 {
            color: #4e73df;
        }
        table {
            width: 50%;
            margin: auto;
        }
        th, td {
            padding: 10px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }
       .btn-custom {
    background-color: #ffd700 !important;
    border-color: #ffd700 !important;
    color: black !important;
}

.btn-custom:hover {
    background-color: #f8f9fa !important; /* 라이트 그레이 배경색 */
    border-color: #f8f9fa !important;
    color: black !important;
}

   
    
       
            
        </style>
</head>


<%@ include file="/views/common/header.jsp" %>


<div class="outer">
		<h2 align="center">게시판 수정하기</h2>
		<br>

    
    <div class="container" id="contentDiv" align="center">
     <!-- Begin Page Content -->
<div class="container-fluid">          
    <!-- Page Heading -->
<h1 class="h3 mb-2 font-weight-bold text-gray-800">그룹게시판</h1>

	<form id="update-form"
	      action="<%= request.getContextPath() %>/update.gr<%-- ?gno=<%= gb.getBoardNo() %> --%>"
	      method="post">
	
	  <!--  글 번호  -->
	 <input type = "hidden" name="gno"
			  		value="<%=gb.getBoardNo() %>">
	 <input type = "hidden" name="userId"
			  		value="<%=loginUser.getUserNickname() %>">
	 
	
	<table align="center">
		

        
   <tr>
      <th>제목</th> 
    <td>
      <input type="text" 
		   name="boardTitle"
		   required 
		   placeholder="<%= gb.getBoardTitle()%>">
		</td>
	</tr>
	<tr>	 
		 <th>내용</th>
	    <td>
	     	<pre><textarea name="boardContent" rows="10" required style="resize : none;" ><%= gb.getBoardContent()%></textarea></pre>
			</td>
		</tr>
		  <tr>
	</table>
	<br><br>
	
<div align="center">
    <button type="submit" class="btn btn-warning btn-custom btn-sm">수정하기</button>
</div>
</div>
</form>
<br><br>
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


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
</body>

</html> 