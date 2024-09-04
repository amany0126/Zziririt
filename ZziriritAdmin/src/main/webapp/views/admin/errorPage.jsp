<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error {
	padding: 0px;
	text-align: center;
}
</style>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper" align="center">
		<%@ include file="adminMenubar.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<%@ include file="adminFooter.jsp"%>
				<div class="error" align="center"
					style="width: 800px; margin-top: 10%">
					<div>
						<img src="resources/img/찌리릭 로고.png" width="120" align="center">
						<h2>원활한 이용에 불편을 드려서 죄송합니다.</h2>
						<h5>빠른 시일 안에 복구하겠습니다.</h5>
						<a href="http://localhost:8888/ZziriritAdmin/"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
							onclick=""><i class="fas fa-recycle fa-sm text-white-50"></i>
							메인페이지로 가기</a>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>