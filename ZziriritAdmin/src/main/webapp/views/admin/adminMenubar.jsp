<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>DN Admin 2 - Dashboard</title>

<!-- Custom fonts for this template-->
<link href="resources/css/all.css" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- 테이블 관련 스타일 밀 js -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.5/css/dataTables.dataTables.css" />
<script src="https://cdn.datatables.net/2.0.5/js/dataTables.js"></script>

<!-- Custom styles for this template-->
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body id="page-top">

	<!-- <div id="wrapper"> -->

	<!-- Sidebar -->
	<ul
		class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
		id="accordionSidebar">

		<!-- Sidebar - Brand -->
		<a
			class="sidebar-brand d-flex align-items-center justify-content-center"
			href="<%=request.getContextPath()%>">
			<div class="sidebar-brand-icon">
				<img src="resources/img/찌리릭 로고.png" width="80">
			</div>
			<div class="sidebar-brand-text mx-3">찌리릭</div>


		</a>

		<!-- Divider -->
		<hr class="sidebar-divider my-0">

		<!-- Nav Item - Dashboard -->
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>"> <i
				class="fas fa-fw fa-tachometer-alt"></i> <span>홈</span>
		</a></li>

		<!-- Divider -->
		<hr class="sidebar-divider">

		<!-- Heading -->
		<div class="sidebar-heading">관리 관련 모음</div>

		<!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#collapseUtilities1"
			aria-expanded="true" aria-controls="collapseUtilities"> <i
				class="fas fa-fw fa-table"></i> <span>일반</span>
		</a>
			<div id="collapseUtilities1" class="collapse"
				aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">일반 관련 모음</h6>
					<a class="collapse-item"
						href="<%=request.getContextPath()%>/list.rul?currentPage=1">일반
						사용자</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.gbbr?currentPage=1">일반
						게시판 목록</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.rguc?currentPage=1">일반
						댓글 목록</a>
				</div>
			</div></li>
		<li class="nav-item"><a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#collapseUtilities2"
			aria-expanded="true" aria-controls="collapseUtilities"> <i
				class="fas fa-fw fa-table"></i> <span>그룹</span>
		</a>
			<div id="collapseUtilities2" class="collapse"
				aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">그룹 관련 모음</h6>
					<a class="collapse-item"
						href="<%=request.getContextPath()%>/list.gml?currentPage=1">모임
						사용자</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.gl?currentPage=1">모임
						목록</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.gbbrl?currentPage=1">모임
						게시판 목록</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.gbbcrl?currentPage=1">모임
						댓글 목록</a>
				</div>
			</div></li>
		<li class="nav-item"><a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#collapseUtilities3"
			aria-expanded="true" aria-controls="collapseUtilities"> <i
				class="fas fa-fw fa-table"></i> <span>정모</span>
		</a>
			<div id="collapseUtilities3" class="collapse"
				aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">정모 관련 모음</h6>
					<a class="collapse-item"
						href="<%=request.getContextPath()%>/list.mt?currentPage=1">정규
						모임 목록</a> <a class="collapse-item"
						href="<%=request.getContextPath()%>/list.mtm?currentPage=1">정규
						모임 사용자 목록</a>
				</div>
			</div></li>
		<div class="sidebar-heading">페이지 이동 모음</div>
		<li class="nav-item"><a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#collapseUtilities4"
			aria-expanded="true" aria-controls="collapseUtilities"> <i
				class="fas fa-fw fa-table"></i> <span>유저용 페이지 모음</span>
		</a>
			<div id="collapseUtilities4" class="collapse"
				aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">페이지 모음</h6>
					<a class="collapse-item" href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/">사용자 메인 페이지</a> <a
						class="collapse-item" href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/list.gr?currentPage=1">사용자 그룹 게시판</a> <a
						class="collapse-item" href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/list.bo?currentPage=1">사용자 일반 게시판</a> <a
						class="collapse-item" href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/group/search">사용자 그룹 목록</a>
				</div>
			</div></li>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		<!-- Sidebar Toggler (Sidebar) -->
		<div class="text-center d-none d-md-inline">
			<button class="rounded-circle border-0" id="sidebarToggle"></button>
		</div>

	</ul>
	<!-- End of Sidebar -->

	<!-- </div> -->


</body>
</html>