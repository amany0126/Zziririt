<%@page import="com.zziririt.group.model.vo.Group"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
Group g = (Group) request.getAttribute("groupInfo");
int userType = (int) request.getAttribute("userType");
System.out.print(userType);
int userNo = -1;
Member m = (Member) request.getSession().getAttribute("loginUser");
if(m!=null){
	userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();
}

%>
<head>
<meta charset="UTF-8">
<title>group detail</title>
<script
	src="/Zziririt/resources/js/bootstrap.bundle.min2.js"></script>
<style>
.image-container {
	height: 200px; /* 이미지의 원하는 높이로 조정 */
	overflow: hidden; /* 이미지가 컨테이너를 벗어나지 않도록 설정 */
	display: flex; /* 부모 요소를 플렉스 박스로 설정 */
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center;
}

.image-container img {
	width: auto;
	height: 100%; /* 높이는 자동으로 조정되어 이미지의 가로세로 비율을 유지 */
}

.ellipsis {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}
</style>
</head>

<body>
	<%@ include file="/views/common/header.jsp"%>
	<div class="container" id="contentDiv" align="center">
		<div class="row col-12">
		

			<!-- 그룹 제목 -->
			<div class="col-12 row">
				<div class="col"></div>
				<div class="col">
					<h3 <%if (userType == 3) {%> onclick="changeName()" <%}%>><%=g.getGroupName()%></h3>
				</div>
				<div class="col">
					<%
					if (userType == 0 && loginUser != null && g.getGroupLimit() > g.getCurrentMem()) {
					%><button type="button" class="btn btn-warning" data-toggle="modal"
						data-target="#joinGroup">가입</button>
					<%
					}else if(userType==3){
					%><h2>그룹장</h2> (원하는 곳을 클릭하여 수정가능)<%} %>
				</div>
			</div>
			<!-- 그룹명 변경 모달 -->
			<div class="modal" id="changeName">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">그룹명 변경</h4>
							<button type="button" class="close" data-dismiss="modal"
								onclick="closeName()">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="groupName">변경할 그룹명</label> <input type="text"
										id="groupName" name="groupName" placeholder="100자 이하로 작성해주세요"
										style="text-align: center;" maxlength="100"
										class="form-control" required>
								</div>
							</form>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-warning "
								data-dismiss="modal" onclick="changeNameSubmit()">변경</button>
						</div>

					</div>
				</div>
			</div>
			<!-- 그룹명 변경 script-->
			<script type="text/javascript">
					function changeName(){
						$('#changeName').modal('show');
					}
					function closeName(){
						$('#changeName').modal('hide');
					}
					function changeNameSubmit(){
						let gno = document.getElementById("gnoHidden").value;
						let groupName = document.getElementById("groupName").value;
						
						$.ajax({
				            url: '/Zziririt/changeGroupNames', 
				            type: 'POST', 
				            data: {groupName:groupName,gno:gno}, 
				            success: function(response) {
				                // 성공적으로 응답을 받았을 때 실행할 코드
				                if(response=='1'){
				                	alert("그룹명 변경에 성공했습니다.")
				                	history.go(0);
				                }else{
				                	alert("그룹명 변경에 실패했습니다.")
				                }
				            },
				            error: function(xhr, status, error) {
				                // 요청이 실패했을 때 실행할 코드
				                console.error('에러:', error);
				            }
				        });
						
					}
			</script>


			<!-- 그룹 프로필 이미지 -->
			<div class="image-container col-12">
				<img src="<%=g.getGroupProfile()%>" class="card-img-top" alt=""
					<%if (userType == 3) {%> onclick="changeImg()" <%}%>>
			</div>
			<!-- 이미지 변경 모달 -->
			<div class="modal" id="changeImg">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">그룹명 대표 이미지 변경</h4>
							<button type="button" class="close" data-dismiss="modal"
								onclick="closeImg()">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form id="changeGroupFile" enctype="multipart/form-data">
								<!-- gno 숨김 전 영역에서 조회 혹은 폼 데이터로 보냄 -->
								<input type="hidden" id="gnoHidden" name="gno"
									value="<%=request.getAttribute("gno")%>">
								<div class="col-12">
									<div class="image-container mx-auto">
										<img id="preview" src="<%=g.getGroupProfile()%>">
									</div>
									<div class="form-group">
										<label for="groupProfile">대표 이미지</label><br> <input
											type="file" id="groupProfile" name="groupProfile"
											accept=".jpg, .png" placeholder="그룹대표이미지를 넣어주세요"
											style="text-align: center;" onchange="renderImg(this)">
										<small class="form-text text-muted">※jpg, png 형식만
											가능합니다.</small>
									</div>
								</div>
							</form>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-danger "
								data-dismiss="modal" onclick="changeImgSubmit()">변경</button>
						</div>

					</div>
				</div>
			</div>
			<!-- 이미지변경 관련 스크립트 -->
			<script type="text/javascript">
				function renderImg(inputFile){
					if(inputFile.files.length == 1){
						let reader = new FileReader();
						reader.readAsDataURL(inputFile.files[0]);
						reader.onload = function(e){
							document.getElementById("preview").setAttribute("src",e.target.result);
						}
					}else{
						document.getElementById("preview").setAttribute("src","Zziririt/resources/images/Logo2.JPG")
					}
				}
			
				function changeImg(){
					$("#changeImg").modal('show');
				}
				function closeImg(){
					$("#changeImg").modal('hide');
				}
				function changeImgSubmit(){
					let formData = new FormData($('#changeGroupFile')[0]);				
			        $.ajax({
			            url: '/Zziririt/changeGroupImg', 
			            type: 'POST', 
			            data: formData, 
			            contentType: false,
			            processData: false,
			            success: function(response) {
			                // 성공적으로 응답을 받았을 때 실행할 코드
			                
			                if(response=='1'){
			                	alert("대표이미지가 성공적으로 변경되었습니다..")
			                	history.go(0);
			                }else{
			                	alert("변경에 실패했습니다.")
			                }
			            },
			            error: function(xhr, status, error) {
			                // 요청이 실패했을 때 실행할 코드
			                console.error('에러:', error);
			            }
			        });				    
				}
			</script>



			<!-- 그룹가입 신청 모달 -->
			<div class="modal" id="joinGroup">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">그룹 가입</h4>
							<button type="button" class="close" data-dismiss="modal">X</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="descriptSelf">*자기소개</label> <input type="text"
										id="descriptSelf" name="descriptSelf"
										placeholder="100자 이하로 작성해주세요" style="text-align: center;"
										maxlength="100" class="form-control" required>
								</div>
							</form>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-danger"
								onclick="joinGroup()">가입신청</button>
						</div>

					</div>
				</div>
			</div>
			<!-- 그룹 가입 ajax -->

			<script>
			function joinGroup(){	
				let descriptSelf = document.getElementById("descriptSelf").value;
				let gno = document.getElementById("gnoHidden").value;
			        $.ajax({
			            url: '/Zziririt/joinGroup', 
			            type: 'POST', 
			            data: {descriptSelf:descriptSelf,gno:gno}, 
			            success: function(response) {
			                // 성공적으로 응답을 받았을 때 실행할 코드
			                if(response=='1'){
			                	alert("그룹가입에 성공하셨습니다.")
			                	history.go(0);
			                }else{
			                	alert("그룹가입에 실패했습니다.")
			                }
			            },
			            error: function(xhr, status, error) {
			                // 요청이 실패했을 때 실행할 코드
			                console.error('에러:', error);
			            }
			        });
			    
			    }
			</script>




			<!-- 방장 활동지 카테고리 개설일 -->
			<div class="col-lg"><%=g.getGroupArea()%>에서 활동하는 사람들과
			</div>
			<div class="col-12"><%=g.getCategoryName().equals("기타") ? "다양하게" : g.getCategoryName()%>
				즐기기 위해
				<%=g.getGroupCreateday()%>에 만듬
			</div>
			<!-- 상세설명 -->
			<div class="col-12 m-1" <%if (userType == 3) {%>
				onclick="descriptChane()" <%}%>>
				<pre><%=g.getGroupDescript()%></pre>
			</div>
			<!-- 상세설명 변경 -->
			<div class="modal" id="chagneDescript">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">그룹설명 변경</h4>
							<button type="button" class="close" data-dismiss="modal"
								onclick="closeDescript()">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="changeGroupDescript">변경할 그룹설명</label>
									<textarea type="text" id="changeGroupDescript"
										name="changeGroupDescript" placeholder="500자 이하로 작성해주세요"
										style="text-align: center;" maxlength="100"
										class="form-control" required></textarea>
								</div>
							</form>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-danger "
								data-dismiss="modal" onclick="changeDescriptSubmit()">변경</button>
						</div>

					</div>
				</div>
			</div>
			<!-- 상세정보 변경 -->
			<script type="text/javascript">
				function descriptChane(){
					$('#chagneDescript').modal('show');
				}
				function closeDescript(){
					$('#chagneDescript').modal('hide');
				}
				function changeDescriptSubmit(){
					let gno = document.getElementById("gnoHidden").value;
					let changeGroupDescript = document.getElementById("changeGroupDescript").value;
			        $.ajax({
			            url: '/Zziririt/changeDescript', 
			            type: 'POST', 
			            data: {changeGroupDescript:changeGroupDescript,gno:gno}, 
			            success: function(response) {
			                // 성공적으로 응답을 받았을 때 실행할 코드
			                if(response=='1'){
			                	alert("그룹설명 변경에 성공하셨습니다.")
			                	history.go(0);
			                }else{
			                	alert("그룹설명 변경에 실패하셨습니다.")
			                }
			            },
			            error: function(xhr, status, error) {
			                // 요청이 실패했을 때 실행할 코드
			                console.error('에러:', error);
			            }
			        });
				}
			</script>


			<!-- 비공개 정보들 -->
			<div class="col-12 p-0" id="secret" <%if (userType == 0) {%>
				style="filter: blur(5px); opacity: 0.5;" <%}%>>
				<!-- <div class="col-12">맴버</div> -->
				<div class="col-12 row no-gutter p-0">
					<div class="col-12">정모 일정</div>

					<!-- 정모일정 캘린더 -->
					<div class="col-12 p-0">
						<div class="row justify-content-center col-12 p-0">
							<div class="col-lg-6 col-sm-12 p-0">
								<div id="calendar" class="card"></div>
								<div class="card-body">
									<table class="table table-bordered">
										<thead>
											<tr class="text-center"></tr>
										</thead>
										<tbody class="text-center"></tbody>
									</table>
								</div>
							</div>

							<div class="row col-lg-6 col-sm-12" id="meetingList">
								<div class="row col-12 p-0" id="meetingDetailHeader">
									<div class="col-4"></div>
									<div class="col-4 p-0" id="dateHolder"></div>
									<div class="col-4" align="left">
										<button type="button" class="btn btn-warning p-0"
											<%if (userType == 0) {%> hidden <%}%> id="addMeetingBtn"
											data-bs-toggle="modal" data-bs-target="#createMeeting">추가</button>
									</div>
								</div>
								<div class="row col-12" id="meetingListDiv" align="center"></div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-12"><button class="btn btn-warning" <%if(userType!=0){ %>onclick="goToBoard()"<% }%>>게시판으로</button></div>
			</div>
<!-- 정모일정 추가 모달창 -->
			<div class="modal" id="createMeeting">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">찌리릭-정모추가</h4>
							<button type="button" class="close" data-bs-dismiss="modal">x</button>
						</div>

						<!-- Modal body -->
						<form action="hellow" method="post" class="form-container"
							id="meetingForm">
							<input type="hidden" name="gno" value="<%=g.getGroupNo()%>">

							<div class="modal-body row justify-content-center text-center">
								<!-- 필수 입력 사항입니다. -->
								<div class="col-12" style="color: red;">*는 필수 입력사항입니다.</div>


								<!-- 이름 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingName">*정모이름</label> <input type="text"
											id="meetingName" name="meetingName"
											placeholder="100자 이하로 작성해주세요" style="text-align: center;"
											maxlength="100" class="form-control" required>
									</div>
								</div>

								<!-- 날짜 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingDay">*날짜</label> <input type="Date"
											id="meetingDay" name="meetingDay" style="text-align: center;"
											maxlength="100" class="form-control" required>
									</div>
								</div>

								<!-- 시간 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingTime">*시간</label> <input type="time"
											id="meetingTime" name="meetingTime"
											style="text-align: center;" maxlength="100"
											class="form-control" required>
									</div>
								</div>


								<!-- 정원 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingLimit">*정모정원</label> <input type="number"
											id="meetingLimit" name="meetingLimit" class="form-control"
											step="10" max="300" min="10" value="10"
											style="text-align: center;" required="required">
									</div>
								</div>
								<!-- 위치 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingArea">*정모위치</label> <input type="text"
											id="meetingArea" name="meetingArea"
											style="text-align: center;" maxlength="100"
											placeholder="모일 장소를 적어주세요" class="form-control"
											required="required">
									</div>
								</div>

								<!-- 정모 설명 -->
								<div class="col-12">
									<div class="form-group">
										<label for="meetingDescript">*정모설명</label>
										<textarea id="meetingDescript" name="meetingDescript"
											style="text-align: center;" maxlength="500"
											placeholder="500자 이내로 정모에 대한 설명을 해주세여" class="form-control"
											onkeyup="charCheck()" required></textarea>
										<small id="char">0</small><small>/500자</small>
									</div>
								</div>
							</div>
						</form>
						<!-- 버튼 -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-danger"
								onclick="meetingSubmit()">정모추가</button>
						</div>
					</div>

					<!-- 정모 관련 스크립트 -->
					<script>
							// 글자수
							function charCheck(){
								document.getElementById("char").innerText=document.getElementById("meetingDescript").value.length
							}
							
						    // 날짜 제한
						    function minDate() {
						        const now = new Date();
						        const year = now.getFullYear();
						        let month = now.getMonth() + 1;
						        let day = now.getDate();
						        
						        if (month < 10) {
						            month = "0" + month;
						        }
						        if (day < 10) {
						            day = "0" + day;
						        }
						        
						        return year + "-" + month + "-" + day;
						    }
						    document.getElementById("meetingDay").setAttribute("min", minDate());
						</script>
				</div>
			</div>
		</div>
		<!-- 정모 추가 ajax script -->
		<script>
				function meetingSubmit(){
					
					
					var formData = $("#meetingForm").serialize();
					console.log(formData)
					
				        $.ajax({
				            url: '/Zziririt/CreateMeeting', 
				            type: 'POST', 
				            data: formData, 
				            success: function(response) {
				                // 성공적으로 응답을 받았을 때 실행할 코드
				                if(response=='1'){
				                	alert("모임이 추가되었습니다.")
				                	$("#createMeeting").modal("hide");
				                	 let year = document.getElementById("year").value
										let month = document.getElementById("month").value-1
										let day = document.getElementById("day").value
										let gno = document.getElementById('gnoHidden').value;
										$.ajax({
									    	
									        url: '/Zziririt/getMeetingList', 
									        type: 'POST', 
									        dataType: 'json', 
									        data: { gno: gno, year: year, month: (month+1)  },
									        success: function(response) {
									        	
									        	createCalendar(year, month,response)
									        	makeMeetingList(year,month+1,day)
									        },
									        error: function(xhr, status, error) {
									            // 서버에서 오류 응답을 받았을 때 처리할 코드
									            console.error('오류 발생:', error);
									        }
									    });
				                }else{
				                	alert("모임 추가에 실패했습니다.")
				                }
				            },
				            error: function(xhr, status, error) {
				                // 요청이 실패했을 때 실행할 코드
				                console.error('에러:', error);
				            }
				        });
					   
				    }
							
			</script>

		<div id="modal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<h2 id="modalTitle"></h2>
				<p id="modalContent"></p>
				<!-- 추가적인 상세 정보 등을 여기에 표시할 수 있음 -->
			</div>
		</div>


		<!-- 정모관련 스크립트 -->
		<script type="text/javascript">
					
					// 오늘 날짜를 얻어옴
					const today = new Date();
					const year = today.getFullYear();
					const month = today.getMonth();
					let gno = document.getElementById('gnoHidden').value;
					// 오늘 날짜로 달력 생성
					
					 $.ajax({
					    	
					        url: '/Zziririt/getMeetingList', 
					        type: 'POST', 
					        dataType: 'json', 
					        data: { gno: gno, year: year, month: (month+1)  },
					        success: function(response) {
					        	createCalendar(year, month,response)
					        	makeMeetingList(year,month+1,today.getDate())
					        },
					        error: function(xhr, status, error) {
					            console.error('오류 발생:', error);
					        }
					    });
					
					
					function createCalendar(year, month, meetingList) {
						console.log(year+" "+month+" "+meetingList)
						let meetingData = meetingList;
					    const calendar = document.getElementById('calendar');
					    calendar.innerHTML = ''; // 기존 캘린더 요소 비우기					
					    // ajax로 해당 년 달에 대한 리스트 불러옴
					   
					   
					    // 새로운 캘린더 요소 생성
					    const card = document.createElement('div');
					    card.className = 'card';
					   
					    const cardBody = document.createElement('div');
					    cardBody.className = 'card-body';
					    const buttonRow = document.createElement('div');
					    buttonRow.className = 'row';
					    const buttonCol1 = document.createElement('div');
					    buttonCol1.className = 'col-4';
					    const buttonCol2 = document.createElement('div');
					    buttonCol2.className = 'col-4';
					    const buttonCol3 = document.createElement('div');
					    buttonCol3.className = 'col-4';
					    const prevButton = document.createElement('button');
					    prevButton.id = 'prevMonth';
					    prevButton.className = 'btn btn-primary';
					    prevButton.innerHTML = '&lt;';
					    const monthYearDisplay = document.createElement('div');
					    monthYearDisplay.className = 'col-4 mt-2'; // 수정: 높이를 맞추기 위해 mt-2 클래스 추가
					    const nextButton = document.createElement('button');
					    nextButton.id = 'nextMonth';
					    nextButton.className = 'btn btn-primary';
					    nextButton.innerHTML = '&gt;';
					    const table = document.createElement('table');
					    table.className = 'table table-bordered';
					    const thead = document.createElement('thead');
					    const tbody = document.createElement('tbody');
					    tbody.className = 'text-center';
					    
					    // 버튼 이벤트 리스너 추가
					    prevButton.addEventListener('click', () => {
					        month--;
					        if (month < 0) {
					            year--;
					            month = 11;
					        }
					        $.ajax({
						    	
						        url: '/Zziririt/getMeetingList', 
						        type: 'POST', 
						        dataType: 'json', 
						        data: { gno: gno, year: year, month: (month + 1) },
						        success: function(response) {						  						        	
						        	
						        	createCalendar(year, month, response)
						        },
						        error: function(xhr, status, error) {
						            // 서버에서 오류 응답을 받았을 때 처리할 코드
						            console.error('오류 발생:', error);
						        }
						    });
					    });
					    
					    nextButton.addEventListener('click', () => {
					        month++;
					        if (month > 11) {
					            year++;
					            month = 0;
					        }
					        $.ajax({
						    	
						        url: '/Zziririt/getMeetingList', 
						        type: 'POST', 
						        dataType: 'json', 
						        data: { gno: gno, year: year, month: (month + 1) },
						        success: function(response) {
						        	
						        	createCalendar(year, month,response)
						        },
						        error: function(xhr, status, error) {
						            console.error('오류 발생:', error);
						        }
						    });
					    });
					    
					    // 테이블 헤더와 바디 연결
					    table.appendChild(thead);
					    table.appendChild(tbody);
					    
					    // 요소들을 캘린더에 추가
					    calendar.appendChild(card);
					    card.appendChild(cardBody);
					    cardBody.appendChild(buttonRow);
					    buttonRow.appendChild(buttonCol1);
					    buttonRow.appendChild(monthYearDisplay);
					    buttonRow.appendChild(buttonCol3);
					    buttonCol1.appendChild(prevButton);
					    buttonCol3.appendChild(nextButton);
					    cardBody.appendChild(table);
					    
					    // 년도와 달 표시
					    monthYearDisplay.innerText = year + '년 ' + (month + 1) + '월';
					    
					    // 해당 연도와 월의 첫 번째 날을 얻어옴
					    const firstDay = new Date(year, month, 1);
					    // 해당 월의 마지막 날을 얻어옴
					    const lastDay = new Date(year, month + 1, 0);
					    
					    // 첫 번째 날이 속한 주의 시작 요일
					    const startDayOfWeek = firstDay.getDay();
					    
					    // 첫 번째 날 이전의 빈 칸 생성
					    let currentRow = document.createElement('tr');
					    for (let i = 0; i < startDayOfWeek; i++) {
					        const emptyCell = document.createElement('td');
					        currentRow.appendChild(emptyCell);
					    }
					    
					    // 달력에 날짜 추가
					    let currentDate = new Date(firstDay);
					    while (currentDate <= lastDay) {
					        const cell = document.createElement('td');
					        const currentDay = new Date(currentDate); // 현재 날짜를 새로운 변수에 복사
					        let day= currentDay.getDate()+'';
					        cell.innerText = day;
					        day = day.length<2? '0' + day : day;
					        
					        if(meetingData[day]){
					        	cell.classList.add('bg-warning');
					        }
					        cell.addEventListener('click', () => {					        
					            makeMeetingList(year,month+1,currentDay.getDate())
					        });
					        currentRow.appendChild(cell);

					        // 토요일인 경우 다음 줄로 이동
					        if (currentDate.getDay() === 6) {
					            tbody.appendChild(currentRow);
					            currentRow = document.createElement('tr');
					        }

					        // 다음 날로 이동
					        currentDate.setDate(currentDate.getDate() + 1);
					    }
					    
					    tbody.appendChild(currentRow);
					}
					//makeMeetingList 이벤트 
					function makeMeetingList(year,month, day){
						document.getElementById("year").value=year;
						document.getElementById("month").value=month;
						document.getElementById("day").value=day;
						
						let today = new Date();
						let lastday = new Date(year+"-"+month+"-"+(day)).getTime()
						console.log(today.getTime()+" "+lastday)
						console.log(year+" "+month+" "+day)
						if(today.getTime()>= lastday ){
							$("#addMeetingBtn").hide()
						}else{
							$("#addMeetingBtn").show()
						}
							
						document.getElementById("meetingDay").value= new Date(year,month-1,day+1).toISOString().substring(0, 10);
						const dateHolder = document.getElementById('dateHolder'); 
						dateHolder.innerHTML = year+"년 "+month+"월<br>"+day+"일"; 
						const meetingListDiv = document.getElementById('meetingListDiv');
						meetingListDiv.innerHTML="";
						
						$.ajax({
					    	
					        url: '/Zziririt/getMeetingListByDay', 
					        type: 'POST', 
					        dataType: 'json', 
					        data: { gno: gno, year: year, month: month,day:day },
					        success: function(response) {
					        	console.log(response);
					        	
					        	for (let key in response) {
					                let meeting = response[key];
					                let onClickName = meeting.createUser==<%=userNo%>? "changeMeeting(this.id,1,"+meeting.curMem+">)" : "changeMeeting(this.id,0,"+meeting.curMem+")";
					                
					                let meetingHTML = "<div class='col' id='"+meeting.meetingNo+"' onclick='"+onClickName+"'><p class='ellipsis'>"+meeting.meetingName+"</p><p> 정원: " + meeting.curMem + "/" + meeting.meetingLimit +"</p></div>";
					                meetingListDiv.insertAdjacentHTML('beforeend', meetingHTML);
					            }          
					            
					        },
					        error: function(xhr, status, error) {
					            console.error('오류 발생:', error);
					        }
					    });
					}
					
					function changeMeeting(meetingNo,flag,curmem){
						// flag 1 본인 모임정보 /flag 0 다른 사람 모임 정보
						alert("미구현 입니다.")
						console.log("ㅌㅌㅌㅌㅌ");
						$.ajax({					    	
					        url: '/Zziririt/getMeetingById', 
					        type: 'POST', 
					        dataType: 'json', 
					        data: { id:id },
					        success: function(response) {
					        	
					        },
					        error: function(xhr, status, error) {
					            console.error('오류 발생:', error);
					        }
					    });
					}
					</script>






		
	</div>
	<input hidden value="" id="year"/>
	<input hidden value="" id="month"/>
	<input hidden value="" id="day"/>
	</div>
	<script>
		function goToBoard(){
			location.href="/Zziririt/list.gr?currentPage=1"
		}
	</script>
	<br><br><br><br>
	<!-- 달력 관련 함수 -->
</body>

</html>