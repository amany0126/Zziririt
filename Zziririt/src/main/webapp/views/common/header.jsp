<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import="com.zziririt.member.model.vo.Member"%>
<% 
	// /ZZiririt 하드 코딩 방지 contextPath
	String contextPath = request.getContextPath();
	
	// session 에 담은 응답 데이터
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	// session 에 담았던 일회성 알람 문구 (서비스 요청 성공시)
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	// 쿠키 불러오기 (saveId key값으로 저장된 아이디값 골라내기)
	Cookie[] cookies = request.getCookies();
	String saveId = "";
	if(cookies != null) {
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("saveId")) {
				saveId = cookies[i].getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>

<head>
	<title>header</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="/Zziririt/resources/js/jquery-3.7.1.js"></script>
	 <link rel="stylesheet" href="/Zziririt/resources/css/bootstrap.min.css"/> 
	<script src="/Zziririt/resources/js/bootstrap.bundle.min.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
	<style>

		/* 전체를 감싸는 div 
      #join-membership {
          width : 620px;
          margin : auto;
          border : 1px solid black;
          padding : 15px;
      }*/

	

		/* 프로필 이미지를 감싸는 div */
		#profile-img-container {
			/* border : 1px solid black; */
			width: 90px;
			height: 90px;
			float: left;
		}

		/* 프로필 이미지 */
		.profile-image {
			width: 100%;
			height: 100%;
			border-radius: 50%;
		}



		input[type=file]::file-selector-button {
			cursor: pointer;
		}

		/* 중복확인 버튼 */
		#duplicate-config_btn, 
		#duplicate-config_btn2 {
			cursor: pointer;
		}

		/* 지역 선택 옵션 버튼 */
		#regionSelect,
		#citySelect {
			margin-bottom: 5px;
			cursor: pointer;
		}

		/* 자기소개 입력란 */
		#introduceContent {
			resize: none;
		}

		/* 회원가입(제출) 버튼 */


		* {
			font-family: "Single Day", cursive;
			font-weight: 700;
			font-style: normal;
		}

		#naviDiv {
			margin-top: 100px;
		}

		#naviLogo {
			display: none;
		}
		#contentDiv{
			margin-top: 165px;
		}

		/* 화면 너비가 540px 이하인 경우에만 적용될 스타일 */
		@media screen and (max-width: 540px) {
			#naviDiv {
				margin-top: 0px;
			}
			#contentDiv {
				margin-top: 55px;
			}

			#naviLogo {
				display: block;
			}

			#logo {
				display: none;
			}
			
		}
	</style>
</head>

<body>
	<!-- 알람 문구 -->
	<script>
				let msg = "<%=alertMsg%>";
				
				if(msg != "null") {
					// 알람 문구가 있다면
					// 알림창으로 해당 알람 문구 띄워주기 (alert 함수)
					alert(msg);
					<%session.removeAttribute("alertMsg");
// 이제 로그인 후 새로고침 할 때마다 알림창 계속 안 뜸!!%>
				}
			</script>

	<!-- 로고 이미지용 디브임 -->
	<div class="row" id="logo" align="center"
		style="position: fixed; top: 0; left: 0; right: 0; z-index: 1000; background-color: white;"
		align="center">

		<div class="col">
			<img alt src="<%=contextPath%>/resources/images/logo2.JPG"
				height="100px">
		</div>

	</div>

	<!-- 노란 네비 영역임 -->
	<div
		class="navbar navbar-expand-sm fixed-top navbar-light bg-secondary"
		id="naviDiv">
		<div class="container">
			<!--  건들 필요 없음 버튼생기는 부분임 -->
			<a class="navbar-brand" href="#" id="naviLogo">찌리릭</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon "></span>
			</button>
			<div class="collapse navbar-collapse row" id="navbarResponsive">
				<div class="navbar-nav row col" align="center">
					<!-- 1 아이템-->
					<div class="col nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>">홈</a>
					</div>
					<!-- 2 아이템-->
					<div class="beforeLogin col nav-item">
						<a class="nav-link"
							href="<%=request.getContextPath()%>/group/search">그룹검색</a>
					</div>

					<div class="col nav-item dropdown afterLogin">
						<a class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="#" id="download">그룹</a>


						<div class="dropdown-menu" aria-labelledby="download">
							<!-- 로그아웃시 보임 -->

							<!-- 로그인시 보임 -->
							<div>
								<a class="dropdown-item"
									href="<%=request.getContextPath()%>/group/search">그룹검색/개설</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item"
									href="<%=request.getContextPath()%>/group/care">그룹관리</a>
							</div>
						</div>

					</div>
					<!-- 3 아이템-->
					<div class="col nav-item">
						<a class="nav-link"
							href="<%=contextPath%>/list.bo?currentPage=1">게시판</a>
					</div>
					<!-- 4 쪽지-->
					<div class="col nav-item afterLogin">
						<a class="nav-link"
							href="<%=contextPath%>/letterList.lo?currentPage=1&letterStatus=1">쪽지</a>
					</div>
					<!-- 5 마이페이지-->
					<div class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="#" id="download">마이페이지 <span class="caret"></span></a>
						<div class="dropdown-menu" aria-labelledby="download">
							<!-- 로그아웃시 보임 -->
							<div class="beforeLogin">
								<button class="dropdown-item" data-toggle="modal"
									data-target="#loginModal">로그인</button>
								<div class="dropdown-divider"></div>
								<button class="dropdown-item" data-toggle="modal"
									data-target="#regModal">회원가입</button>
							</div>
							<!-- 로그인시 보임 -->
							<div class="afterLogin">
								<button class="dropdown-item">마이페이지</button>
								<div class="dropdown-divider"></div>
								<button class="dropdown-item" id="logoutBtn">로그아웃</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 로그인 모달 -->
	<div class="modal" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">찌리릭-로그인</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body row">
					<div class="col-4" align="right">
						<label for="userId">아이디 :</label>
					</div>
					<div class="col-8" align="left">
						<input type="text" id="userId" style="width: 60%">
					</div>
					<div class="col-4" align="right">
						<label for="userPwd">비밀번호 :</label>
					</div>
					<div class="col-8" align="left">
						<input type="password" id="userPwd" style="width: 60%">
					</div>
				</div>

				<div align="center">
					<button type="button" class="btn btn-warning" id="loginBtn">로그인</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 회원가입 모달 -->
	<div class="modal" id="regModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">찌리릭-회원가입</h4>
					<button type="button" class="close" data-dismiss="modal"
						onclick="resetModal();">×</button>

					<script>
								function resetModal() {
									$("#profile-image").attr("src", "<%=contextPath%>/resources/images/profile_ex5.png");
									$('#reset').click();
								}
							</script>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div id="join-membership">
						<form id="enroll-form" action="<%=contextPath%>/insert.me"
							method="post" class="form-container" enctype="multipart/form-data" >
							<div class="container">

								<!-- 필수 입력 사항입니다.-->
								<div class="col-12" align="center">
									<div id="need" style="margin-bottom: 5px;">
										<span style="color: red;">* 는 필수 입력사항입니다!!</span>
									</div>
								</div>

								<!-- 프로필 이미지 -->
								<div class="col-12 row">
									<div class="form-group col-5">
										<label for="profile-input">프로필 이미지</label>
										<div id="profile-img-container">
											<img id="profile-image" class="profile-image"
												name="profile-image"
												src="<%=contextPath%>/resources/images/profile_ex5.png"
												alt="Profile-Image">
										</div>
									</div>
									<div class="col-7">
										<div>
											<input type="file" id="userProfile" name="userProfile"
												onchange="loadImg(this);" class="form-control-file">
											<small class="form-text text-muted">※jpg, png 형식만
												가능합니다.</small>
										</div>
									</div>
								</div>

								<!-- 이름 -->
								<div class="col-12">
									<div class="form-group">
										<label for="userName">*이름</label> <input type="text"
											id="userName" name="userName" placeholder="한글 6글자 이내"
											required class="form-control">
									</div>
								</div>

								<!-- 닉네임 -->
								<div class="col-12">
									<div class="form-group">
										<label for="userNickname">*닉네임</label> <input type="text"
											id="userNickname" name="userNickname"
											placeholder="*1~8글자 (특수문자 제외)" required class="form-control">
										<button id="duplicate-config_btn" type="button"
											onclick="nicknameCheck();">중복확인</button>
										<!-- 닉네임 중복확인 AJAX -->
										<script>
													function nicknameCheck() {
														// 닉네임을 입력받는 input 요소 객체
														let $userNickname = $("#enroll-form input[name=userNickname]");
														var regExp2 = /^[A-Za-z0-9가-힣]{1,8}$/; // 닉네임 정규표현식 제약 조건
														
														// 닉네임 정규표현식 제약 조건 통과 시 ajax로 닉네임 중복확인
														if(regExp2.test($userNickname.val())) {
															$.ajax({
																url : "<%=contextPath%>/nicknameCheck.me", 
																type : "get", 
																data : {checkNickname : $userNickname.val()}, 
																success : function(result) {
																	// console.log(result);
																	if(result == "NNNNN") { // 사용 불가한 닉네임
																		alert("이미 존재하거나 탈퇴한 회원의 닉네임입니다.");
																		// 닉네임 재입력 유도
																		$userNickname.focus();
																	} else { // 사용 가능한 닉네임
																		// alert("사용 가능한 닉네임입니다.");
																		if(confirm("사용 가능한 닉네임입니다. 사용하시겠습니까?")) {
																			// 이 닉네임을 사용하겠다 라고 했을 경우
																			// 닉네임을 더이상 변경 못하게 막기
																			$userNickname.attr("readonly", true);
																		} else { // 이 닉네임을 사용하지 않겠다
																			// 닉네임 재입력 유도
																			$userNickname.focus();
																		}
																	}
																}, 
																error : function() {
																	console.log("닉네임 중복체크용 ajax 통신 실패");
																}
															});
														} else {
															alert("유효한 닉네임이 아닙니다. 다시 입력해주세요.");
														}
													}
												</script>
									</div>
								</div>
								<!-- 아이디 -->
								<div class="col-12">
									<div class="form-group">
										<label for="userId">*아이디</label> <input type="text"
											id="userId" name="userId" placeholder="*영문, 숫자 포함 6~15글자"
											required class="form-control">
										<button id="duplicate-config_btn2" type="button"
											onclick="idCheck();">중복확인</button>
										<!-- 아이디 중복확인 AJAX -->
										<script>
													function idCheck() {
														// 아이디를 입력받는 input 요소 객체
														let $userId = $("#enroll-form input[name=userId]");
														var regExp3 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,15}$/; // 아이디 정규표현식 제약 조건
														
														if(regExp3.test($userId.val())) {
															$.ajax({
																url : "<%=contextPath%>/idCheck.me", 
																type : "get", 
																data : {checkId : $userId.val()}, 
																success : function(result) {
																	// console.log(result);
																	if(result == "NNNNN") { // 사용 불가한 아이디
																		alert("이미 존재하거나 탈퇴한 회원의 아이디입니다.");
																		// 아이디 재입력 유도
																		$userId.focus();
																	} else { // 사용 가능한 아이디
																		// alert("사용 가능한 아이디입니다.");
																		if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")) {
																			// 이 아이디를 사용하겠다 라고 했을 경우
																			
																			// 회원가입 버튼 활성화시키기
																			$("#enroll-form button[type=submit]")
																				.removeAttr("disabled");
																			
																			// 아이디값을 더이상 변경 못하게 막기
																			$userId.attr("readonly", true);
																		} else { // 이 아이디를 사용하지 않겠다
																			// 아이디 재입력 유도
																			$userId.focus();
																		}
																	}
																}, 
																error : function() {
																	console.log("아이디 중복체크용 ajax 통신 실패");
																}
															});
														} else {
															alert("유효한 아이디가 아닙니다. 다시 입력해주세요.");
														}
													}
												</script>
									</div>
								</div>

								<!-- 비밀번호 -->
								<div class="col-12">
									<div class="form-group">
										<label for="userPwd">*비밀번호</label> <input type="password"
											id="userPwd" name="userPwd"
											placeholder="*영문, 숫자, 특수문자(@,#,$,%,^,&,*) 포함 8~15글자" required
											class="form-control">
									</div>
								</div>

								<!-- 비밀번호 확인 -->
								<div class="col-12">
									<div class="form-group">
										<label for="userPwd2">*비밀번호 확인</label> <input type="password"
											id="userPwd2" name="userPwd2" required class="form-control">
									</div>
								</div>

								<!-- 생년월일 -->
								<div class="col-12">
									<div class="form-group">
										<label for="birth">*생년월일</label> <input type="date"
											id="birthDate" name="birthDate" required class="form-control">
									</div>
								</div>

								<!-- 전화번호 -->
								<div class="col-12">
									<div class="form-group">
										<label for="phone">*전화번호</label> <input type="text"
											id="userPhone" name="userPhone" placeholder="-은 제외하고 입력해주세요!"
											required class="form-control">
									</div>
								</div>

								<!-- 이메일 -->
								<div class="col-12">
									<div class="form-gorup">
										<label for="email">이메일</label> <input type="email"
											id="userMail" name="userMail" placeholder="형식에 맞게 입력해주세요!"
											class="form-control">
									</div>
								</div>

								<!-- 성별 -->
								<div class="col-12">
									<div class="form-group">
										<label for="gender">성별 *</label>
										<div>
											<input type="radio" id="M" name="userGender" value="M"
												style="cursor: pointer;"> <label for="M"
												style="cursor: pointer;">남자</label> &nbsp; <input
												type="radio" id="F" name="userGender" value="F"
												style="cursor: pointer;"> <label for="F"
												style="cursor: pointer;">여자</label>
										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label for="regionSelect">지역 *</label>
										<div>
											<select id="regionSelect" name="regionSelect"
												onchange="populateCities()" required class="form-control">
												<option value="">지역 선택</option>
												<option value="서울시">서울시</option>
												<option value="경기도">경기도</option>
												<option value="인천시">인천시</option>
												<option value="세종시">세종시</option>
												<option value="충청북도">충청북도</option>
												<option value="충청남도">충청남도</option>
												<option value="대전시">대전시</option>
												<option value="전라북도">전라북도</option>
												<option value="전라남도">전라남도</option>
												<option value="광주시">광주시</option>
												<option value="대구시">대구시</option>
												<option value="경상북도">경상북도</option>
												<option value="경상남도">경상남도</option>
												<option value="부산시">부산시</option>
												<option value="울산시">울산시</option>
												<option value="강원도">강원도</option>
												<option value="제주도">제주도</option>
											</select> <select id="citySelect" name="citySelect" required
												class="form-control">
												<option value="">시/군/구 선택</option>
											</select>
										</div>
									</div>
								</div>

								<div class="col-12">
									<div class="form-group">
										<label for="introduce">자기소개</label>
										<textarea id="introduceContent" name="introduceContent"
											cols="50" rows="15" maxlength="500"
											placeholder="자기소개는 공백 포함 500자 이내로 적어주세요!"
											class="form-control"></textarea>
										<small id="count">0</small>/500자
									</div>
								</div>
								<!-- 버튼용 div -->
								<div class="row col-12">
									<div class="col-12" align="center">
										<button type="submit" id="submit" class="btn btn-danger"
											onclick="return validate();" disabled>회원가입하기</button>
									</div>
									<div class="col-12" align="center">
										<button type="reset" id="reset" class="btn btn-danger"
											style="margin-top: 5px;">취소</button>
										<script>
												    $(document).ready(function() {
												        $("#reset").on("click", function() {
												            resetModal2();
												        });
												    });
												
												    function resetModal2() {
												        $("#profile-image").attr("src", "resources/images/profile_ex5.png");
												    }
												</script>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 로그인/로그아웃 ajax -->
	<script type="text/javascript">
				$("#loginBtn").click(function () {
					console.log("XXXXXX")
					let userId = $("#userId").val();
					let userPwd = $("#userPwd").val();
					$.ajax({
						url: '/Zziririt/login', // 요청을 보낼 서버의 URL
						type: 'post', // HTTP 요청 메서드 (GET, POST 등)
						data: { userId: userId, userPwd: userPwd },
						success: function (data) {
							if (data != "fail") {
								alert(data + "님 환영합니다.");
								$("#loginModal").modal('hide');
								history.go(0);
							} else {
								alert("로그인에 실패하셨습니다.");
								history.go(0);
							}
						},
						error: function () {
							console.error("로그인에 실패하셨습니다.");
						}
					})
				})
				$("#logoutBtn").click(function () {
					$.ajax({
						url: '/Zziririt/login',
						type: 'post',
						data: { logout: "OUT" },
						success: function (data) {
							alert("로그아웃이 완료되셨습니다.");
							history.go(0);
						},
						error: function () {

						}
					})
				})
			</script>

	<!-- 로그인시 변화 -->
	<script>
				let loginCheck = "<%=loginUser%>"
				if (loginCheck != "null") {
					$("#naviDiv").removeClass("bg-secondary").addClass("bg-warning");
					$(".beforeLogin").hide();
					$(".afterLogin").show();
				} else {
					$("#naviDiv").removeClass("bg-warning").addClass("bg-secondary");
					$(".beforeLogin").show();
					$(".afterLogin").hide();
				}
			</script>

	<!-- 프로필 이미지 삽입 관련 js -->
	<script> 
		       let profileInput = document.getElementById("userProfile");
		       // let defaultImageSrc = document.querySelector(".profile-image").src; // 기본 이미지의 src 속성 값
		
		       profileInput.addEventListener("change", function() {
		           let file = profileInput.files[0];
		           let reader = new FileReader();
		           
		           // 허용된 확장자 목록
		           let allowedExtensions = ['jpg', 'jpeg', 'png'];
		
		           // 파일을 선택한 경우
		           if (file) {
		               // 파일 이름에서 확장자 추출
		               let fileName = file.name;
		               let fileExtension = fileName.split('.').pop().toLowerCase();
		
		               // 추출한 확장자가 허용된 확장자 목록에 있는지 확인
		               if (allowedExtensions.includes(fileExtension)) {
		                   reader.onloadend = function() {
		                       if (reader.result) {
		                           let profileImage = document.querySelector(".profile-image");
		                           if (profileImage) {
		                               profileImage.src = reader.result;
		                           }
		                       }
		                   };
		                   reader.readAsDataURL(file);
		               } else {
		                   // 허용되지 않은 확장자인 경우 경고 메시지 출력 등의 처리 가능
		                   alert("허용된 파일 형식은 jpg 또는 png 입니다.");
		                   // 파일 입력(input 태그) 초기화 (선택한 파일 제거)
		                   this.value = null;
		               }
		           }
		           /*
		           else { // 파일을 선택하지 않은 경우
		               // 기본 이미지를 사용 (기본 이미지의 경로를 넘김)
		               // 허용되지 않은 확장자의 파일을 삽입해도 기본 이미지가 삽입된 상태로 두기
		               let formData = new FormData();
		               formData.append("profile-input", defaultImageSrc);
		           }
		           */
		           // 파일을 선택하지 않은 경우 데이터는 넘어가지 않고(null) 
		           // 그냥 나중에 기본 이미지를 '보이게만' 하면 됨
		           // 고로 이 관련해서는 아무 짓도 안해도 됨
		       });
		    </script>
	<script>
		       function loadImg(inputFile) {
		           if (inputFile.files.length == 1) {
		               // 선택된 파일이 있는 경우
		               // 선택된 파일을 읽어들여서 
		               // 그 영역에 맞는 곳에 미리보기
		   
		               // 파일을 읽어들일 FileReader 객체 생성
		               let reader = new FileReader(); // 생성자함수
		   
		               // FileReader 객체에서 제공하는 
		               // 파일을 읽어들이는 메서드 속성 호출
		               reader.readAsDataURL(inputFile.files[0]);
		               // > 해당 파일을 읽어들이는 순간 
		               //   해당 그 파일만의 고유한 url 주소 같은 것이 
		               //   만들어져서 부여됨
		               // > 각 자리에 맞는 img 태그의 src 속성을 
		               //   해당 url 주소값으로 넣어주면 미리보기됨
		   
		               // 파일 읽기가 완료되었을 때 실행할 함수 정의
		               // (즉, url 주소가 부여되었을 때)
		               reader.onload = function (e) {
		                   // e.target == reader == this (파일 읽음 당한 놈)
		   
		                   // e 의 target.result 에 
		                   // 파일의 url 주소가 담겨있음
		                   // 영역에 맞춰서 이미지 미리보기
		                   document.getElementById("profile-image").setAttribute("src", e.target.result);
		               };
		           } else {
		               // 선택된 파일이 없는 경우
		               // 기본 이미지로 보여주기
		               document.getElementById("profile-image").setAttribute("src", "resources/images/profile_ex5.png");
		           }
		       }
		    </script>

	<!-- 지역 선택 js -->
	<script>
		       // 도시 정보를 담고 있는 객체
		       let citiesByRegion = {
		           "서울시": ["종로구", "중구", "용산구", "성동구", "광진구", 
		                   "동대문구", "중랑구", "성북구", "강북구", "도봉구", 
		                   "노원구", "은평구", "서대문구", "마포구", "양천구", 
		                   "강서구", "구로구", "금천구", "영등포구", "동작구", 
		                   "관악구", "서초구", "강남구", "송파구", "강동구"], 
		           "경기도": ["김포시", "부천시", "광명시", "시흥시", "안양시", 
		                   "안산시", "과천시", "의왕시", "군포시", "성남시", 
		                   "수원시", "화성시", "오산시", "평택시", "하남시", 
		                   "광주시", "용인시", "안성시", "이천시", "여주시", 
		                   "양평군", "가평군", "포천시", "남양주시", "구리시", 
		                   "의정부시", "동두천시", "양주시", "파주시", "고양시", 
		                   "연천군"], 
		           "인천시": ["계양구", "부평구", "남동구", "연수구", "미추홀구", 
		                   "동구", "서구", "중구", "강화군", "옹진군"], 
		           "세종시": [" "], 
		           "충청북도": ["단양군", "제천시", "충주시", "음성군", "진천군", 
		                   "괴산군", "증평군", "청주시", "보은군", "옥천군", 
		                   "영동군"], 
		           "충청남도": ["천안시", "아산시", "서산시", "당진시", "공주시", 
		                   "보령시", "논산시", "계룡시", "홍성군", "예산군", 
		                   "부여군", "서천군", "청양군", "태안군", "금산군"], 
		           "대전시": ["동구", "중구", "서구", "유성구", "대덕구"], 
		           "전라북도": ["전주시", "군산시", "익산시", "정읍시", "남원시", 
		                   "김제시", "완주군", "진안군", "무주군", "장수군", 
		                   "임실군", "순창군", "고창군", "부안군"], 
		           "전라남도": ["목포시", "여수시", "순천시", "나주시", "광양시", 
		                   "담양군", "곡성군", "구례군", "고흥군", "보성군", 
		                   "화순군", "장흥군", "강진군", "해남군", "영암군", 
		                   "무안군", "함평군", "영광군", "장성군", "완도군", 
		                   "진도군", "신안군"], 
		           "광주시": ["동구", "서구", "남구", "북구", "광산구"], 
		           "대구시": ["중구", "동구", "서구", "남구", "북구", 
		                   "수성구", "달서구", "달성군", "군위군"], 
		           "경상북도": ["포항시", "경주시", "안동시", "김천시", "구미시", 
		                   "영주시", "영천시", "상주시", "문경시", "경산시", 
		                   "의성군", "청송군", "영양군", "영덕군", "청도군", 
		                   "고령군", "성주군", "칠곡군", "예천군", "봉화군", 
		                   "울진군", "울릉군"], 
		           "경상남도": ["창원시", "진주시", "김해시", "양산시", "거제시", 
		                   "통영시", "사천시", "밀양시", "의령군", "함안군", 
		                   "창녕군", "고성군", "남해군", "하동군", "산청군", 
		                   "함양군", "거창군", "합천군"], 
		           "부산시": ["중구", "서구", "동구", "영도구", "부산진구", 
		                   "동래구", "남구", "북구", "해운대구", "사하구", 
		                   "금정구", "강서구", "연제구", "수영구", "사상구", 
		                   "기장군"], 
		           "울산시": ["중구", "남구", "동구", "북구", "울주군"], 
		           "강원도": ["춘천시", "원주시", "홍천군", "횡성군", "영월군", 
		                   "평창군", "정선군", "철원군", "화천군", "양구군", 
		                   "인제군", "강릉시", "동해시", "태백시", "속초시", 
		                   "삼척시", "고성군", "양양군"], 
		           "제주도": ["제주시", "서귀포시"]
		       };
		       
		    	// 도시 선택 select 엘리먼트
		        let citySelect = document.getElementById("citySelect");
		        
		        // 지역 선택 select 엘리먼트의 변경 이벤트 핸들러
		        function populateCities() {
		            // 선택된 지역
		            let selectedRegion = document.getElementById("regionSelect").value;
		            
		            // 해당 지역의 도시들을 가져와서 citySelect 엘리먼트에 추가
		            let cities = citiesByRegion[selectedRegion];
		            citySelect.innerHTML = ""; // 기존 옵션 제거
		            if (cities) {
		                cities.forEach(city => {
		                    let option = document.createElement("option");
		                    option.text = city;
		                    option.value = city;
		                    citySelect.add(option);
		                });
		            } else {
		                let option = document.createElement("option");
		                option.text = "시/군/구 선택";
		                citySelect.add(option);
		            }
		        }
		    </script>

	<!-- 자기소개 글자 수 세는 js -->
	<script>
		       document.addEventListener("DOMContentLoaded", function() {
		           let introduceInput = document.getElementById("introduceContent");
		           let countDisplay = document.getElementById("count");
		
		           introduceInput.addEventListener("input", function() {
		               countDisplay.textContent = this.value.length; // 공백 포함
		           });
		       });
		    </script>

	<!-- 입력 요소 제약사항들(정규표현식 등) -->
	<!-- 제출 버튼을 눌렀을 때 실행됨 -->
	<script>
		       function validate() {
		           let userName = document.getElementById("userName").value; // 이름
		           let userPwdInput = document.getElementById("enroll-form").querySelector("#userPwd");
		           let userPwd = userPwdInput.value; // 비밀번호
		           let userPwd2 = document.getElementById("userPwd2").value; // 확인용 비밀번호
		           let userPhone = document.getElementById("userPhone").value; // 전화번호
		           let userMail = document.getElementById("userMail").value; // 이메일
		           let radios = document.getElementsByName("userGender"); // 성별
		       	   
		           // 정규표현식을 이용한 각 제약사항들
		           var regExp = /^[가-힣]{2,6}$/; // 이름 체크
		           
		           // 닉네임과 아이디는 중복체크 이전에 해야 하기 때문에 위에 있음
		           
		           // 비번 체크 : 영문, 숫자, 특수기호 포함 8-15사이
		           //            숫자, 특수기호 최소 1개씩 이상
		           var regExp4 = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&*])[A-Za-z\d@#$%^&*]{8,15}$/;

		           var regExp5 = /^010+[0-9]{8}$/; // 전화번호 체크
		           var regExp6 = /^[a-zA-Z0-9._-]+@[a-z]+\.[a-z]{2,3}$/; // 이메일 체크
		           var genderSelect = false; // 성별 체크

		           if(!regExp.test(userName)) {
		               alert("유효한 이름이 아닙니다. 다시 입력해주세요.");
		               document.getElementById("userName").select();
		               return false;
		           }
		
		           if(!regExp4.test(userPwd)) {
		               alert("유효한 비밀번호가 아닙니다. 다시 입력해주세요.");
		               document.getElementById("userPwd").select();
		               return false;
		           }
		
		           if(userPwd != userPwd2) {
		               alert("비밀번호가 일치하지 않습니다.");
		               return false;
		           }
		
		           if(!regExp5.test(userPhone)) {
		               alert("유효한 전화번호가 아닙니다. 다시 입력해주세요.");
		               document.getElementById("userPhone").select();
		               return false;
		           }
		
		           if (!regExp6.test(userMail)) {
		               alert("유효한 이메일주소가 아닙니다. 다시 입력해주세요.");
		               document.getElementById("userMail").select();
		               return false;
		           }
		           
		           for(let i = 0; i < radios.length; i++) {
		               if(radios[i].checked) {
		                   genderSelect = true;
		                   break;
		               }
		           }
		           if(!genderSelect) {
		               alert("성별을 선택해주세요.");
		               return false;
		           }
		       }
		  	</script>
</body>
</html>