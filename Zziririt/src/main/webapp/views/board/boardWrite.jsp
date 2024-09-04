<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.zziririt.board.model.vo.Category" %>
<%
	// 응답데이터인 카테고리 리스트 뽑기
	ArrayList<Category> list 
		= (ArrayList<Category>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 
	
	/* 공지글 작성 부분 */
	/* 전체를 감싸는 div */
	#contentDiv {
	    max-width : 900px;
	    margin : auto;
	    margin-top : 50px;
	    padding : 20px;
	    box-sizing : border-box;
	}
	
	/* 전체 테이블들 */
	#contentDiv table {
	    width : 100%;
	    border-collapse : collapse;
	    text-align : center;
	}
	
	/* 전체 테이블 줄(가로) 간격 */
	#contentDiv table td { padding : 10px; }
	
	/* 전체 테이블 가로 선 */
	#contentDiv table tr {
	    border : 1px solid black;
	    border-left : none;
	    border-right : none;
	    border-top : none;
	}
	
	/* 게시글 제목 */
	#contentDiv .boardTitle input[type="text"] {
	    width : 100%;
	    padding : 8px;
	    border : none;
	    background-color : rgb(230, 230, 230);
	    box-sizing : border-box;
	    height : 60px;
	    font-size : 25px;
	}
	.boardTitle::placeholder {
	    text-align : left;
	    padding-left : 5px;
	    text-size-adjust : 20px;
	}
	
	/* 카테고리, 모임 일시, 모임 정원, 모임 지역 글자 */
	#contentDiv table td label {
		font-size: 16px;
		margin-top : 5px;
	}
	/* 카테고리, 모임 일시, 모임 지역 부분 버튼 */
	#categorySelect, 
	#ddaySelect, 
	#timeSelect, 
	#regionSelect2, 
	#citySelect2 {
	    cursor : pointer;
	}
	/* 모임 정원 부분 */
	#peopleLimit {
	    width : 50px;
	    margin-left : -40px;
	}
	/* 모임 장소 부분 */
	#regionSelect { margin-left : -25px; }
	/* 모임 장소 상세 부분 */
	#detailedAddress { width : 450px; }
	
	/* 게시글 내용 */
	textarea#content {
	    resize : none;
	    border : none;
	    padding : 8px;
	    font-size : 15px;
	    width : 100%;
	    background-color : rgb(230, 230, 230);
	    box-sizing : border-box;
	}
	#content::placeholder {
	    text-align : center;
	    padding-top : 145px;
	    font-size : 20px;
	}
	
	/* 파일 선택 */
	input[type=file]::file-selector-button { cursor : pointer; }
	.file-add td {
	    list-style : none;
	    text-align : left;
	}
	
	/* 등록 버튼, 초기화 버튼 */
	#submit-button, #reset-button {
	    height : 30px;
	    cursor : pointer;
	}
</style>

</head>
<body>
	
	<%@ include file="/views/common/header.jsp" %>
	
	<div class="container" id="contentDiv" align="center">
		<form id="enroll-form" 
              action="<%= contextPath %>/insert.bo" 
              method="post" 
              enctype="multipart/form-data">
            
            <!-- 작성자의 회원번호도 넘기기 -->
			<input type="hidden" 
				   name="userNo" 
				   value="<%= loginUser.getUserNo() %>">
            
            <!-- 제목 -->
       		<table class="boardTitle">
            	<tr>
                    <td>
                      <input type="text" id="title" name="boardTitle" placeholder="제목 (30자 이내)" maxlength="30" required>
                    </td>
                </tr>
            </table>
            
            <table>
                <tr>
                	<!-- 카테고리 -->
                    <td style="width : 60px;"><label for="category">카테고리</label></td>
                    <td style="width : 70px;">
                        <select id="categorySelect" name="categoryName">
							<% for(Category c : list) { %>
								<option value="<%= c.getCategoryName() %>"><%= c.getCategoryName() %></option>
							<% } %>
						</select>
                    </td>
                    
                    <!-- 모임 일시 -->
                    <td style="width : 70px;"><label for="dday">모임 일시</label></td>
                    <td style="width : 200px;">
                        <input type="date" id="ddaySelect" name="ddaySelect" required>
                        <input type="time" id="timeSelect" name="timeSelect" required>
                    </td>
                    
                    <!-- 모임 정원 -->
                    <td style="width : 70px;"><label for="dday">모임 정원</label></td>
                    <td style="width : 90px;">
                        <input type="number" min="0" id="peopleLimit" name="peopleLimit" required>명
                    </td>
                </tr>
                <tr>
                	<!-- 모임 장소 (지역&시/군/구&상세주소) -->
                    <td style="width : 60px;"><label for="area">모임 장소</label></td>
                    <td colspan="6" align="left" style="padding-left : 35px">
                        <select id="regionSelect2" name="regionSelect2"
								onchange="populateCities2()" required>
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
						</select>
						<select id="citySelect2" name="citySelect2" required>
							<option value="">시/군/구 선택</option>
						</select>
                        <input type="text" id="detailedAddress" name="detailedAddress" placeholder="여기에 모임 장소를 상세히 작성해주세요!" required>
                    </td>
                </tr>
         		
         		<!-- 내용 -->
                <tr>
                    <td colspan="15">
                        <textarea id="content" name="boardContent" placeholder="내용" rows="15" required></textarea>
                    </td>
                </tr>
            </table>

			<!-- 첨부파일 -->
            <table class="file-add">
                <tr>
                    <td><input class="selectFiles-button" type="file" id="fileAttachment" name="fileInfo"></td>
                </tr>
            </table>
            *첨부파일은 한 개(100MB 이하)만 업로드 가능합니다! (영상은 URL로 내용에 첨부하는 것을 권장합니다!)
            
            <br><br>

            <div align="center">
				<button id="submit-button" type="submit">
					등록
				</button>
                &nbsp;&nbsp;
				<button id="reset-button" type="reset">
					초기화
				</button>
			</div>
        </form>
	</div>
	
	<!-- 지역 선택 js -->
 	<script>
		// 도시 정보를 담고 있는 객체
		let citiesByRegion2 = {
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
		let citySelect2 = document.getElementById("citySelect2");

		// 지역 선택 select 엘리먼트의 변경 이벤트 핸들러
		function populateCities2() {
		    // 선택된 지역
		    let selectedRegion2 = document.getElementById("regionSelect2").value;

		    // 해당 지역의 도시들을 가져와서 citySelect 엘리먼트에 추가
		    let cities = citiesByRegion2[selectedRegion2];
		    citySelect2.innerHTML = ""; // 기존 옵션 제거
		    if (cities) {
		        cities.forEach(city => {
		            let option = document.createElement("option");
		            option.text = city;
		            option.value = city;
		            citySelect2.add(option);
		        });
		    } else {
		        let option = document.createElement("option");
		        option.text = "시/군/구 선택";
		        citySelect2.add(option);
		    }
		}
	</script>
</body>
</html>