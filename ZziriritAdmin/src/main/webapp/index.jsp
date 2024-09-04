<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<style type="text/css">
.atag {
	text-align: center;
	color: white;
	/* 	text-decoration : none; */
	text-decoration-line: none
}

.atag:hover {
	text-align: center;
	color: white;
	/* text-decoration : none; */
	text-decoration-line: none
}

.atag1 {
	text-align: center;
	color: black;
	/* 	text-decoration : none; */
	text-decoration-line: none
}

.atag1:hover {
	text-align: center;
	color: black;
	/* text-decoration : none; */
	text-decoration-line: none
}
</style>
<title>찌리릭 관리자 페이지</title>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<%@ include file="../../views/admin/adminMenubar.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<%@ include file="../../views/admin/adminFooter.jsp"%>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">찌리릭 관리페이지</h1>
						<a href="<%=request.getContextPath()%>"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
							onclick=""><i class="fas fa-recycle fa-sm text-white-50"></i>
							새로고침</a>
					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">
												사용자 총 인원수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="UserCount">

												<input type="hidden" name="UserCount">
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												생성된 모임 수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="GroupCount"></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-laugh-squint fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												작성된 게시판 총 갯수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="TotalBoard"></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-fw fa-table"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-warning text-uppercase mb-1">
												작성된 총 댓글 갯수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="TotalComment"></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-fw fa-table"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>



					<!-- Content Row -->
					<div class="row">

						<!-- Content Column -->
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">카테고리별 생성된 팀</h6>
									<!-- <div class="dropdown no-arrow show">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in show"
											aria-labelledby="dropdownMenuLink"
											style="position: absolute; transform: translate3d(-158px, 19px, 0px); top: 0px; left: 0px; will-change: transform;"
											x-placement="bottom-end">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div> -->
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-area">
										<div class="chartjs-size-monitor">
											<div class="chartjs-size-monitor-expand">
												<div class=""></div>
											</div>
											<div class="chartjs-size-monitor-shrink">
												<div class=""></div>
											</div>
										</div>
										<canvas id="myBarChart"
											style="display: block; width: 472px; height: 320px;"
											width="472" height="320" class="chartjs-render-monitor"></canvas>
									</div>
								</div>
							</div>
						</div>


						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">신청된 신고 비율</h6>

								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-pie pt-4 pb-2">
										<div class="chartjs-size-monitor">
											<div class="chartjs-size-monitor-expand">
												<div class=""></div>
											</div>
											<div class="chartjs-size-monitor-shrink">
												<div class=""></div>
											</div>
										</div>
										<canvas id="myPieChart"
											style="display: block; width: 246px; height: 245px;"
											width="246" height="245" class="chartjs-render-monitor"></canvas>
									</div>
									<div class="mt-4 text-center small">
										<span class="mr-2"> <i
											class="fas fa-circle text-secondary"></i> 일반 게시판 신고
										</span> <span class="mr-2"> <i
											class="fas fa-circle text-primary"></i> 일반 게시판 댓글 신고
										</span> <span class="mr-2"> <i
											class="fas fa-circle text-success"></i> 그룹 게시판 신고
										</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
											그룹 게시판 댓글 신고
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Color System -->
					<div class="row">

						<div class="col-lg-6 mb-4">
							<div class="card bg-primary text-white shadow">
								<div class="card-body">
									사용자 메인 페이지로 이동
									<div class="text-white-50 small">
										<a href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/" class="atag" target='_blank'>바로가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 mb-4">
							<div class="card bg-warning text-white shadow">
								<div class="card-body">
									일반 게시판
									<div class="text-white-50 small">
										<a href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/list.bo?currentPage=1" class="atag" target='_blank'>바로가기</a>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-6 mb-4">
							<div class="card bg-secondary text-white shadow">
								<div class="card-body">
									그룹 게시판
									<div class="text-white-50 small">
										<a href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/list.gr?currentPage=1"" class="atag" target='_blank'>바로가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 mb-4">
							<div class="card bg-success text-white shadow">
								<div class="card-body">
									모임 리스트
									<div class="text-white-50 small">
										<a href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/group/search" class="atag" target='_blank'>바로가기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- End of Main Content -->

	<!-- Footer -->
	<footer class="sticky-footer bg-white">
		<div class="container my-auto">
			<div class="copyright text-center my-auto">
				<span>Copyright &copy; Your Website 2021</span>
			</div>
		</div>
	</footer>
	<!-- End of Footer -->

	
	<!-- End of Content Wrapper -->



	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- 로그아웃 Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">정말 로그아웃 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">로그아웃 페이지를 누르면 로그인 페이지로 이동합니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-warning" href="https://b0ed-1-220-236-77.ngrok-free.app/Zziririt/">로그아웃</a>

				</div>
			</div>
		</div>
	</div>
	<script>
			// 게시글 상세조회 페이지에서
			// 게시글 상세조회 페이지가 다 로딩된 이후에
			// 해당 게시글에 딸린 댓글들만 조회할 수 있는 요청을 ajax 로 보내기
			
			
			
			$(function() {
				
				UserCount();
				GroupCount();
				TotalBoard();
				TotalComment();
				report();
				reportPie();
				category();
				bar();
				// 1초 간격마다 selectReplyList 함수를 실행
				 setInterval(UserCount, 1000);
				 setInterval(GroupCount, 1000);
				 setInterval(TotalBoard, 1000);
				 setInterval(TotalComment, 1000);
				 


			});
			// 총 사용자 조회용
			function UserCount(){
				$.ajax({
					url : "<%= request.getContextPath() %>/UserCount",
					type : "get",
					data : { },
					success : function(result){
						$("#UserCount>input[name=UserCount]").val(result);
							
					
						 $("#UserCount").html(result);
						 
						 function userMax() {
							 
							 return result;
							
						}
					},
					error : function() {
						console.log("총 사용자 조회용 ajax 통신 실패!")
					}
				});
			
			}
			
			
			// 총 그룹 갯수 조회용
			function GroupCount() {
				
				$.ajax({
					url : "<%= request.getContextPath() %>/GroupCount",
					type : "get",
					data : {},
					success : function(result) {
						
						
						 $("#GroupCount").html(result);
						 
						
					},
					error : function() {
						console.log("총 그룹 갯수 조회용 ajax 통신 실패!")
					}
				});
				
			}
			// 총 그룹 갯수 조회용
			function TotalBoard() {
				
				$.ajax({
					url : "<%= request.getContextPath() %>/TotalBoard",
					type : "get",
					data : {},
					success : function(result) {
						
						
						 $("#TotalBoard").html(result);
						 
						 
						
					},
					error : function() {
						console.log("총 그룹 갯수 조회용 ajax 통신 실패!")
					}
				});
				
			}
			// 총 그룹 갯수 조회용
			function TotalComment() {
				
				$.ajax({
					url : "<%= request.getContextPath() %>/TotalComment",
					type : "get",
					data : {},
					success : function(result) {
						
						
						 $("#TotalComment").html(result);
						 
						
					},
					error : function() {
						console.log("총 그룹 갯수 조회용 ajax 통신 실패!")
					}
				});
				
			}
			
			function report() {
							
				$.ajax({
					url : "<%= request.getContextPath() %>/report" ,
					type : "get",
					data : {},
					success : function(result) {
						
						// console.log(result);
						
						let commonBoard = result[0];
						let commonComent = result[1];
						let groupBoard = result[2];
						let groupComent = result[3];
						
					/* 	console.log(commonBoard)
						console.log(commonComent)
						console.log(groupBoard)
						console.log(groupComent)
						 */
						reportPie(commonBoard, commonComent, groupBoard, groupComent);
						 
					
					},
					error : function() {
						console.log("총 그룹 갯수 조회용 ajax 통신 실패!")
					}
				});
				
			}
			function reportPie( commonBoard,  commonComent,  groupBoard,  groupComent) {
				// Set new default font family and font color to mimic Bootstrap's default styling
				Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
				Chart.defaults.global.defaultFontColor = '#858796';
		
				// Pie Chart Example
				var ctx = document.getElementById("myPieChart");
				var myPieChart = new Chart(ctx, {
				  type: 'doughnut',
				  data: {
				    labels: ["일반 게시판 신고", "일반 게시판 댓글 신고", "그룹 게시판 신고", " 그룹 게시판 댓글 신고"],
				    datasets: [{
				      data: [commonBoard, commonComent, groupBoard, groupComent],
				      backgroundColor: ['#858796','#4e73df', '#1cc88a', '#36b9cc'],
				      hoverBackgroundColor: ['#5a5c69','#2e59d9', '#17a673', '#2c9faf'],
				      hoverBorderColor: "rgba(234, 236, 244, 1)",
				    }],
				  },
				  options: {
				    maintainAspectRatio: false,
				    tooltips: {
				      backgroundColor: "rgb(255,255,255)",
				      bodyFontColor: "#858796",
				      borderColor: '#dddfeb',
				      borderWidth: 1,
				      xPadding: 15,
				      yPadding: 15,
				      displayColors: false,
				      caretPadding: 10,
				    },
				    legend: {
				      display: false
				    },
				    cutoutPercentage: 80,
				  },
				});
			}
			function category() {
				
				$.ajax({
					url : "<%= request.getContextPath() %>/category" ,
					type : "get",
					data : {},
					success : function(result) {
						
						console.log(result);
						
						let category1 = result[0];
						let category2 = result[1];
						let category3 = result[2];
						let category4 = result[3];
						let category5 = result[4];
						
						
						
						bar(category1, category2, category3, category4,category5);
						 
					
					},
					error : function() {
						console.log("총 그룹 갯수 조회용 ajax 통신 실패!")
					}
				});
				
			}
			
		</script>
	<script>
		
function bar(category1, category2, category3, category4,category5) {
	

//Set new default font family and font color to mimic Bootstrap's default styling
 Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
 Chart.defaults.global.defaultFontColor = '#858796';

 function number_format(number, decimals, dec_point, thousands_sep) {
   // *     example: number_format(1234.56, 2, ',', ' ');
   // *     return: '1 234,56'
   number = (number + '').replace(',', '').replace(' ', '');
   var n = !isFinite(+number) ? 0 : +number,
     prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
     sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
     dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
     s = '',
     toFixedFix = function(n, prec) {
       var k = Math.pow(10, prec);
       return '' + Math.round(n * k) / k;
     };
   // Fix for IE parseFloat(0.55).toFixed(0) = 0;
   s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
   if (s[0].length > 3) {
     s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
   }
   if ((s[1] || '').length < prec) {
     s[1] = s[1] || '';
     s[1] += new Array(prec - s[1].length + 1).join('0');
   }
   return s.join(dec);
 }

 // Bar Chart Example
 var ctx = document.getElementById("myBarChart");
 var myBarChart = new Chart(ctx, {
   type: 'bar',
   data: {
     labels: ["예술/문화", "운동", "음식", "스터디", "기타"],
     datasets: [{
       label: "그룹 갯수",
       backgroundColor: "#4e73df",
       hoverBackgroundColor: "#2e59d9",
       borderColor: "#4e73df",
       data: [category1, category2, category3, category4,category5],
     }],
   },
   options: {
     maintainAspectRatio: false,
     layout: {
       padding: {
         left: 10,
         right: 25,
         top: 25,
         bottom: 0
       }
     },
     scales: {
       xAxes: [{
         time: {
           unit: 'month'
         },
         gridLines: {
           display: false,
           drawBorder: false
         },
         ticks: {
           maxTicksLimit: 6
         },
         maxBarThickness: 25,
       }],
       yAxes: [{
         ticks: {
           min: 0,
           max: 20,
           maxTicksLimit: 5,
           padding: 10,
           // Include a dollar sign in the ticks
           callback: function(value, index, values) {
             return  number_format(value) + '팀';
           }
         },
         gridLines: {
           color: "rgb(234, 236, 244)",
           zeroLineColor: "rgb(234, 236, 244)",
           drawBorder: false,
           borderDash: [2],
           zeroLineBorderDash: [2]
         }
       }],
     },
     legend: {
       display: false
     },
     tooltips: {
       titleMarginBottom: 10,
       titleFontColor: '#6e707e',
       titleFontSize: 14,
       backgroundColor: "rgb(255,255,255)",
       bodyFontColor: "#858796",
       borderColor: '#dddfeb',
       borderWidth: 1,
       xPadding: 15,
       yPadding: 15,
       displayColors: false,
       caretPadding: 10,
       callbacks: {
         label: function(tooltipItem, chart) {
           var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
           return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + '팀';
         }
       }
     },
   }
 });
}
		</script>


</body>

</html>