<%@page import="com.zziririt.common.model.vo.PageInfo"%>
<%@page import="com.zziririt.group.model.vo.Group"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
ArrayList<Group> gList = (ArrayList<Group>) request.getAttribute("list");
ArrayList<String> cList = (ArrayList<String>) request.getAttribute("categoryList");
PageInfo pi = (PageInfo) request.getAttribute("pi");
%>
<script
	src="/Zziririt/resources/js/bootstrap.bundle.min2.js"></script>
<style>
<meta charset="UTF-8">
<title>찌리릭+</title>
<style>
#contentDiv {
	margin-top: 200px;
}

.ghover {
	border: 1px solid #ccc;
}

.ghover :hover {
	cursor: pointer;
	opacity: 0.5;
}

.image-container {
	height: 100px;
	overflow: hidden;
	display: flex; 
	justify-content: center; 
	align-items: center;
}

.image-container img {
	width: auto;
	height: 100%;
}
</style>
</head>

<body>
	<%@ include file="/views/common/header.jsp"%>
	<div class="container" id="contentDiv" align="center">


		<!-- 검색용 form -->
		<div class="col-12 row">

			<form id="searchForm"
				action="<%=request.getContextPath()%>/group/search" method="get"
				class="row no-gutters" style="width: 100%;">

				<!-- 카테고리 셀렉 -->
				<div class="col-lg-4 col-sm-12 col-md-12">
					<div class="">
						<label for="category">카테고리</label>

						<!-- 카테고리 옵션 -->
						<select id="category" name="category" class="form-control"
							style="text-align: center;">
							<option value="">-선택안함-</option>
							<%
							if (cList != null) {
								for (String val : cList) {
							%>
							<option value="<%=val%>"
								<%if (val.equals(request.getAttribute("category"))) {%> selected
								<%}%>><%=val%>
							</option>
							<%
							}
							}
							%>
						</select>
					</div>
				</div>
				<div class="col-lg-4 col-sm-12 col-md-12">
					<label for="area">지역</label>
					<!-- 지역 옵션 -->
					<select id="area" name="area" class="form-control"
						style="text-align: center;">
						<option value="">-선택안함-</option>
						<option value="서울">서울</option>
						<option value="경기">경기</option>
						<option value="인천">인천</option>
						<option value="세종">세종</option>
						<option value="충북">충북</option>
						<option value="충남">충남</option>
						<option value="대전">대전</option>
						<option value="전북">전북</option>
						<option value="전남">전남</option>
						<option value="광주">광주</option>
						<option value="대구">대구</option>
						<option value="경북">경북</option>
						<option value="경남">경남</option>
						<option value="부산">부산</option>
						<option value="울산">울산</option>
						<option value="강원">강원</option>
						<option value="제주">제주</option>
					</select>
				</div>
				<script>
					let groupArea = "<%=request.getAttribute("area")%>";

					let areaSelect = document.getElementById("area");
					let options = areaSelect.getElementsByTagName("option");

					for (var i = 0; i < options.length; i++) {
						if (options[i].value == groupArea) {
							options[i].selected = true;
							break;
						}
					}
				</script>
				<!-- 검색어 입력-->
				<div class="col-lg-4 col-sm-12 col-md-12">
					<label for="keywords">검색</label>
					<div class="input-group">
						<input type="text" name="keywords" id="keywords"
							placeholder="검색어를 입력하세요" class="form-control"
							style="text-align: center;">
						<div class="input-group-append">
							<button type="button" class="btn btn-primary"
								onclick="submitForm(1)">검색</button>
						</div>
					</div>
				</div>
				<script>
					let groupKey = "<%=request.getAttribute("keywords")%>"
					console.log(groupKey)
					document.getElementById("keywords").value=groupKey!='null'?groupKey:"";
				</script>
				<input id="page" name="page" value="1" type="hidden">
			</form>
		</div>

		<!-- 그룹 생성 모달 -->
		<div class="modal" id="createGroupForm">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">찌리릭-그룹개설</h4>
						<button type="button" class="close" data-bs-dismiss="modal">x</button>
					</div>

					<!-- Modal body -->
					<form action="hellow" method="post" class="form-container"
						id="createGroup" enctype="multipart/form-data">
						<div class="modal-body row justify-content-center text-center">
							<!-- 필수 입력 사항입니다. -->
							<div class="col-12" style="color: red;">*는 필수 입력사항입니다.</div>

							<!-- 그룹대표이미지 -->
							<div class="col-12">
								<div class="image-container mx-auto">
									<img id="preview" src="/Zziririt/resources/images/logo2.JPG">
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
							<!-- 대표이미지 랜더링 스크립 -->
							<script>
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
							</script>

							<!-- 그룹명 -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupName">*그룹이름</label> <input type="text"
										id="groupName" name="groupName" placeholder="100자 이하로 작성해주세요"
										style="text-align: center;" maxlength="100"
										onchange="groupNameCheck(this.value)" class="form-control"
										required>
								</div>
							</div>
							<!-- 그룹명 중복 확인 ajax -->
							<script>
								val = groupNameFlag = false;
								function groupNameCheck(Name){
									$.ajax({
							            url: '/Zziririt/groupNameCheck', 
							            type: 'POST', 
							            data: {Name:Name}, 
							            success: function(response) {
							            	if(response=='ok'){
							                	groupNameFlag = true;
							            		 $('#groupName').css({
								            		    'border-color': 'green',
								            		    'border-width': '2px'
								            		});
							            	}else{
							            		$('#groupName').css({
							            		    'border-color': 'red',
							            		    'border-width': '2px'
							            		}).focus();
							            		groupNameFlag = false;							            		
							            	}
							            },
							            error: function(xhr, status, error) {
							               							                
							            }
							        });
								}								
							</script>
							<!-- 카테고리 -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupCategory">*카테고리</label> <select
										id="groupCategory" name="groupCategory" class="form-control"
										style="text-align: center;">
										<%
										if (cList != null) {
											for (String val : cList) {
										%>
										<option value="<%=val%>"><%=val%></option>
										<%
										}
										}
										%>
									</select>
								</div>
							</div>
							<!-- 그룹 정원 -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupLimit">*그룹정원</label> <input type="number"
										id="groupLimit" name="groupLimit" class="form-control"
										step="10" max="300" min="10" value="10"
										style="text-align: center;" required="required" onchange="limitCheck()" >
								</div>
							</div>
							<!-- 정원 입력관련 스크립트 -->
							<script>
								function limitCheck() {
							        let number = document.getElementById("groupLimit").value;
							        if (number > 300) {
							            document.getElementById("groupLimit").value = 300;
							            alert("최대 정원은 300명입니다.");
							        } else if (number < 10) {
							            document.getElementById("groupLimit").value = 10;
							            alert("최소 정원은 10명입니다.");
							        }
							    }
							</script>
							<!-- 그룹 활동지역 -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupArea">*지역</label>
									<div class="row">
										<div class="col-6 no-gutter">
											<select id="groupArea" name="groupArea" class="form-control"
												required onchange="changeGroupCity()"
												style="text-align: center;">
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
											</select>
										</div>
										<div class="col-6 no-gutter">
											<select id="groupCity" name="groupCity" required
												class="form-control" style="text-align: center;">
												<option value="종로구">종로구</option>
												<option value="중구">중구</option>
												<option value="용산구">용산구</option>
												<option value="성동구">성동구</option>
												<option value="광진구">광진구</option>
												<option value="동대문구">동대문구</option>
												<option value="중랑구">중랑구</option>
												<option value="성북구">성북구</option>
												<option value="강북구">강북구</option>
												<option value="도봉구">도봉구</option>
												<option value="노원구">노원구</option>
												<option value="은평구">은평구</option>
												<option value="서대문구">서대문구</option>
												<option value="마포구">마포구</option>
												<option value="양천구">양천구</option>
												<option value="강서구">강서구</option>
												<option value="구로구">구로구</option>
												<option value="금천구">금천구</option>
												<option value="영등포구">영등포구</option>
												<option value="동작구">동작구</option>
												<option value="관악구">관악구</option>
												<option value="서초구">서초구</option>
												<option value="강남구">강남구</option>
												<option value="송파구">송파구</option>
												<option value="강동구">강동구</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<!-- 간단한 자기 소개를 해주세요! -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupDescript">* 자기소개</label>
									<input type="text" id="introduce" name="introduce"
										maxlength="100" placeholder="자신에 대한 간단한 소개를 적어주세요"
										class="form-control" required style="text-align: center;">
								</div>
							</div>

							<!-- 그룹설명 -->
							<div class="col-12">
								<div class="form-group">
									<label for="groupDescript">* 그룹설명</label>
									<textarea id="groupDescript" name="groupDescript" style="text-align: center;"
										maxlength="500" placeholder="그룹설명은 공백 포함 500자 이내로 적어주세요!"
										class="form-control" onkeyup="charCheck()" required></textarea>
									<small id="char">0</small><small>/500자</small>
								</div>
							</div>
						</div>
						<!-- 지역선택  -->
						<script>
							let groupCitySelect = document.getElementById("groupCity");
							function changeGroupCity(){
								let selectedArea = document.getElementById("groupArea").value;
								let subcity = citiesByRegion[selectedArea];
								console.log(selectedArea)
								console.log(subcity)
								console.log(citiesByRegion['서울시'])
								groupCitySelect.innerHTML = "";
								subcity.forEach(x=>{
							        console.log(x)
							        let option = document.createElement("option");
							        option.text = x;
							        option.value = x; // 도시 이름을 할당
							        groupCitySelect.add(option);
							    })
							}
						</script>
						<!-- 글자수 체크 -->
						<script>
							function charCheck(){
								document.getElementById("char").innerText=document.getElementById("groupDescript").value.length
							}
						</script>

						<!-- Modal footer -->
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-danger"
								onclick="groupSubmit()">그룹개설</button>
						</div>
						<!-- 그룹개설 ajax script -->
						<script>
							function groupSubmit(){
								let formData = new FormData($('#createGroup')[0]);
								console.log(formData)
								if(groupNameFlag){
							        $.ajax({
							            url: '/Zziririt/CreateGroup', 
							            type: 'POST', 
							            data: formData, 
							            contentType: false,
							            processData: false,
							            success: function(response) {
							                // 성공적으로 응답을 받았을 때 실행할 코드
							                if(response=='1'){
							                	alert("그룹이 성공적으로 개설되었습니다.")
							                	$('#createGroupForm').modal('hide');
							                	document.getElementById("createGroup").reset(); // 폼 초기화
							                    document.getElementById("preview").src = "/Zziririt/resources/images/logo2.JPG"; // 이미지 초기화
							                    document.getElementById("char").innerText = "0"; // 문자수 카운터 초기화
							                    groupNameFlag = false;
							                	// 마이그룹 페이지로 이동 //
							                }else{
							                	alert("그룹 개설에 실패했습니다.")
							                }
							            },
							            error: function(xhr, status, error) {
							                // 요청이 실패했을 때 실행할 코드
							                console.error('에러:', error);
							            }
							        });
							    }else{
							        $('#groupName').focus();
							        alert("사용 불가능한 이름입니다.")
							    }
							}
						</script>
					</form>
				</div>
			</div>
		</div>


		<!-- 리스트 구역 -->
		<div class="col-12 row" style="margin-top: 15px">
			<div class="col-4"></div>
			<div class="col">그룹목록</div>
			<!-- 그룹개설 모달버튼 -->
			<div class="col-4 no-gutters">
				<%
				if (loginUser != null) {
				%><button type="button" class="btn btn-warning p-0" id="btnGroup"
					data-bs-toggle="modal" data-bs-target="#createGroupForm">그룹개설</button>
				<%
				}
				%>
			</div>
		</div>





		<div class="col-12 row gx-5" align="center" id="groupList">
			<!-- 요소한개 -->
			<%
			if (gList != null) {
				for (Group g : gList) {
			%>
			<div class="col-lg-3 col-sm-12 col-md-12 ghover">
				<div class="col-12 row no-gutters " align="center">
					<input type="hidden" value="<%=g.getGroupNo()%>"> <img
						src="<%=g.getGroupProfile()%>" width="100%"
						height="150px">
					<!-- 제목 -->
					<div class="col-12">
						<%=g.getGroupName()%>
					</div>
					<!-- 지역 -->
					<div class="col-12">
						<%=g.getGroupArea()%>
					</div>
					<!-- 현원/정원 -->
					<div class="col-12 row">
						<div class="col">
							<%=g.getCurrentMem()%>/<%=g.getGroupLimit()%>
						</div>
						<div class="col">
							<%=g.getGroupCreateday()%>
						</div>
						<!-- 찜목록 -->
						<%
						if (g.getWish() != null) {
						%>
						<div class="col-1 wishList">
							<i class="fa-solid fa-heart fa-xl"
								<%if (g.getWish().equals("0")) {%> style="color: gray;"
								<%} else {%> style="color: red;" <%}%>> </i>
						</div>
						<%
						}
						%>

					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<div class="col">
				<h1>검색결과가 없습니다</h1>
			</div>
			<%
			}
			%>
		</div>
		<!-- 상세보기/찜기능 스크립트 -->
		<script>
			$(function(){
				<!-- 상세보기 -->
				$("#groupList>div").click(function(e){
					e.stopPropagation();
					let gno =$(this).children().children().eq(0).val();
					
					if(gno != undefined){console.log(gno)
						location.href="/Zziririt/group/view?gno="+gno;					
					}
				})
				<!-- 찜기능 -->
				$(".wishList").click(function(e){
					e.stopPropagation()
					let gno = $(this).parent().siblings("input").val();
					let wishIcon = $(this).children().eq(0);
					let userNo ="<%=loginUser != null ? loginUser.getUserNo() : "logout"%>"
					$.ajax({
						url: '/Zziririt/wish', // 요청을 보낼 서버의 URL
						type: 'post', // HTTP 요청 메서드 (GET, POST 등)
						data: { gno: gno, userNo: userNo },
						success: function (data) {
							
							if(data == "add"){
								alert("그룹을 찜목록에 추가했습니다.");
								wishIcon.css("color","red");
							}
							else if(data =="remove"){
								alert("그룹을 찜목록에서 제외했습니다.");
								wishIcon.css("color","gray");
							}
							else alert("요청에 실패했습니다.")
						},
						error: function () {
							console.error("로그인에 실패하셨습니다.");
						}
					})
					console.log(gno);
				})
			})
			</script>
		<!-- 상세보기/찜기능 스크립트 끝-->

		<!-- 페이징구역 -->
		<div class="pagination col-12" align="center">
			<div class="row col-12 justify-content-center">
				<%
				if (pi != null) {
					int currentPage = pi.getCurrentPage();
					int startPage = pi.getStartPage();
					int endPage = pi.getEndPage();
					int maxPage = pi.getMaxPage();
					if (currentPage != 1) {
				%>
				<button onclick="submitForm('<%=currentPage - 1%>')">&lt;</button>
				<%
				}
				%>

				<%
				for (int p = startPage; p <= endPage; p++) {
				%>
				<!-- 현재 내가 보고있는 페이지일 경우는 클릭 안되게끔 -->
				<%
				if (p != currentPage) {
				%>
				<!-- 페이지 이동 가능하게끔 -->
				<button onclick="submitForm('<%=p%>')">
					<%=p%>
				</button>
				<%
				} else {
				%>
				<!-- 페이지 이동 불가능하게끔 -->
				<button disabled>
					<%=p%>
				</button>
				<%
				}
				%>
				<%
				}
				%>

				<%
				if (currentPage != maxPage) {
				%>
				<!-- 현재 페이지가 마지막 페이지가 아닌 경우 다음 페이지 버튼 노출 -->

				<button onclick="submitForm('<%=currentPage + 1%>')">&gt;</button>

				<%
				}
				%>
				<%
				}
				%>
			</div>
		</div>
		<!-- 페이징 끝 -->

		<!-- 페이지 이동용 스크립트 -->
		<script>
				function submitForm(pageNumber) {
					document.getElementById('page').value = pageNumber;
					document.getElementById('searchForm').submit();
				}
			</script>
		<!-- 페이지 이동용 스크립트 끝 -->
	</div>
</body>


</html>