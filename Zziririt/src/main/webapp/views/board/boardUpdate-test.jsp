<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%@ include file="/views/common/header.jsp" %>
			<div class="container" id="contentDiv" align="center">
			<form id="enroll-form" action="" method="post">
    <div class="container">
   
        <div class="row justify-content-end" >
            <div class="col-auto">
                <input type="radio" id="Y" name="recruitingYN" value="Y" checked>
                <label for="Y">모집중</label>
                <input type="radio" id="N" name="recruitingYN" value="N">
                <label for="N">모집마감</label>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <input type="text" id="title" name="title" placeholder="제목 (30자 이내)" maxlength="30" required style="width:100%" value="같이 피방 가실 분 구함!!">
            </div>
        </div>
        <div class="row" style="margin-top:5px;">
            <div class="col-lg-4 col-sm-12 col-md-12">
                <label for="categorySelect">카테고리</label>
                <select id="categorySelect" name="categorySelect" required class="form-select">
                    <option value="">--- 선택 ---</option>
                    <option value="예술/문화">예술/문화</option>
                    <option value="운동">운동</option>
                    <option value="음식">음식</option>
                    <option value="스터디">스터디</option>
                    <option value="기타" selected>기타</option>
                </select>
            </div>
            <div class="col-lg-4 col-sm-12 col-md-12">
                <label for="ddaySelect">모임 일시</label>
                <input type="date" id="ddaySelect" name="ddaySelect" required value="2024-05-03">
                <input type="time" id="meetingTime" name="meetingTime" required value="22:00">
            </div>
            <div class="col-lg-4 col-sm-12 col-md-12">
                <label for="peopleLimit">모임 정원</label>
                <input type="number" id="peopleLimit" name="peopleLimit" required value="4" style="width:60px">명
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-sm-12 col-md-12" align="center">
                <label for="area">모임 장소</label>
                <select id="regionSelect" name="regionSelect" onchange="populateCities()" required class="form-select">
                    <option value="">지역 선택</option>
                    <!-- 이하 동일 -->
                </select>
                <select id="citySelect" name="citySelect" required class="form-select">
                    <option value="">시/군/구 선택</option>
                </select>
                <input type="text" id="detailedAddress" name="detailedAddress" placeholder="여기에 모임 장소를 상세히 작성해주세요!" required style="width:70%" value="선유로~~ ○○빌딩 3층 □□□PC방" >
            </div>
        </div>
        <div class="row">
            <div class="col">
                <textarea id="content" name="content" placeholder="내용" rows="5" required class="form-control" style="margin-top:10px; resize: none">쪽지로 연락주세요!!</textarea>
            </div>
        </div>
        <div class="row" style="margin-top:5px;">
            <div class="col">
                <input type="file" id="fileAttachment" name="fileAttachment">
            </div>
        </div>
        <div class="row justify-content-center" style="margin-top:5px;">
            <div class="col-auto">
                <button id="submit-button" type="submit" class="btn btn-primary">재등록</button>
                <button id="reset-button" type="reset" class="btn btn-secondary">초기화</button>
            </div>
        </div>
    </div>
</form>

			</div>
			
<script>
        // 도시 정보를 담고 있는 객체
        let citiesByRegion = {
            "서울": ["종로구", "중구", "용산구", "성동구", "광진구", 
                    "동대문구", "중랑구", "성북구", "강북구", "도봉구", 
                    "노원구", "은평구", "서대문구", "마포구", "양천구", 
                    "강서구", "구로구", "금천구", "영등포구", "동작구", 
                    "관악구", "서초구", "강남구", "송파구", "강동구"], 
            "경기": ["김포", "부천", "광명", "시흥", "안양", 
                    "안산", "과천", "의왕", "군포", "성남", 
                    "수원", "화성", "오산", "평택", "하남", 
                    "광주", "용인", "안성", "이천", "여주", 
                    "양평", "가평", "포천", "남양주", "구리", 
                    "의정부", "동두천", "양주", "파주", "고양", 
                    "연천"], 
            "인천": ["계양구", "부평구", "남동구", "연수구", "미추홀구", 
                    "동구", "서구", "중구", "강화", "옹진"], 
            "세종": ["세종"], 
            "충북": ["단양", "제천", "충주", "음성", "진천", 
                    "괴산", "증평", "청주", "보은", "옥천", 
                    "영동"], 
            "충남": ["천안", "아산", "서산", "당진", "공주", 
                    "보령", "논산", "계룡", "홍성", "예산", 
                    "부여", "서천", "청양", "태안", "금산"], 
            "대전": ["동구", "중구", "서구", "유성구", "대덕구"], 
            "전북": ["전주", "군산", "익산", "정읍", "남원", 
                    "김제", "완주", "진안", "무주", "장수", 
                    "임실", "순창", "고창", "부안"], 
            "전남": ["목포", "여수", "순천", "나주", "광양", 
                    "담양", "곡성", "구례", "고흥", "보성", 
                    "화순", "장흥", "강진", "해남", "영암", 
                    "무안", "함평", "영광", "장성", "완도", 
                    "진도", "신안"], 
            "광주": ["동구", "서구", "남구", "북구", "광산구"], 
            "대구": ["중구", "동구", "서구", "남구", "북구", 
                    "수성구", "달서구", "달성", "군위"], 
            "경북": ["포항", "경주", "안동", "김천", "구미", 
                    "영주", "영천", "상주", "문경", "경산", 
                    "의성", "청송", "영양", "영덕", "청도", 
                    "고령", "성주", "칠곡", "예천", "봉화", 
                    "울진", "울릉"], 
            "경남": ["창원", "진주", "김해", "양산", "거제", 
                    "통영", "사천", "밀양", "의령", "함안", 
                    "창녕", "고성", "남해", "하동", "산청", 
                    "함양", "거창", "합천"], 
            "부산": ["중구", "서구", "동구", "영도구", "부산진구", 
                    "동래구", "남구", "북구", "해운대구", "사하구", 
                    "금정구", "강서구", "연제구", "수영구", "사상구", 
                    "기장"], 
            "울산": ["중구", "남구", "동구", "북구", "울주"], 
            "강원": ["춘천", "원주", "홍천", "횡성", "영월", 
                    "평창", "정선", "철원", "화천", "양구", 
                    "인제", "강릉", "동해", "태백", "속초", 
                    "삼척", "고성", "양양"], 
            "제주": ["제주", "서귀포"]
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

    <!-- 백엔드 작업 시 수정될 부분 -->
    <script>
        // 선택되었던 지역 불러오기(라고는 하지만 지금은 강제로 지정된 것임)
        document.getElementById("regionSelect").value = "서울";
        // 도시 선택 select 엘리먼트 초기화
        populateCities();
        // 선택되었던 도시 불러오기(라고는 하지만 지금은 강제로 지정된 것임)
        document.getElementById("citySelect").value = "영등포구";
    </script>
</body>
</html>