<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.zziririt.board.model.vo.Category, com.zziririt.board.model.vo.Board, com.zziririt.common.model.vo.PageInfo" %>
<%
	// 응답데이터 뽑기
	PageInfo pi 
		= (PageInfo)request.getAttribute("pi");

	// 응답데이터인 카테고리 리스트 뽑기
	/* ArrayList<Category> list
		= (ArrayList<Category>)request.getAttribute("list"); */

	// 응답데이터인 게시글 목록 리스트 뽑기
	ArrayList<Board> list2 
		= (ArrayList<Board>)request.getAttribute("list");

	// 페이징바 출력 시 주로 쓰이는 변수들 따로 빼기
	// (매번 getter 호출하면 구문이 헷갈림)
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
        /* 전체를 감싸는 div */
        .outer {
            width : 1200px;
            /* border : 1px dotted lightgray; */
            margin : auto;
            margin-top : 50px;
			box-sizing : border-box;
        }

		/* 게시글 정렬 버튼 */
		#category {
			height : 30px;
			cursor : pointer;
		}
		#category>option { text-align : center; }

        /* 게시글 작성 버튼 */
		/* 여기는 나중에 공간은 차지하되 안 보이게 처리하기(로그인 안한 경우 보여질 시) */
        #write-enrollForm {
			margin-left : 600px;
            height : 30px;
            cursor : pointer;
        }
    
        /* 게시글 목록 테이블 관련 */
        .list-area {
            /* border : 1px solid lightgray; */
            text-align : center;
            width : 90%;
            border-collapse : collapse; /* 세로선 사라짐 */
            margin-top : -15px;
        }
		.list-area tr {  height : 35px }
		.list-area th { background-color : lightgray; }
		.list-area th, td {
            border-bottom : 1px solid lightgray;
            border-left : none;
        }
		.list-area td {
			white-space : nowrap;
			overflow : hidden;
			text-overflow : ellipsis;
		}
        .list-area>tbody>tr:hover {
            background-color : rgb(247, 247, 247);
            cursor : pointer;
        }

        /* 페이징 버튼들 */
        .paging {
        	margin : 0px 2px;
            cursor : pointer;
        }
        
        #listSort {
		    margin: 0; /* 폼 요소의 기본 마진을 제거합니다. */
		    padding: 0; /* 폼 요소의 기본 패딩을 제거합니다. */
        }
    </style>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>
	
	<div class="container" id="contentDiv" align="center">
        <div align="center" style="padding-top : 20px">
        
        	 <!-- <form id="listSort" action="<%= contextPath %>/sort.bo" method="get"> -->
			    <!-- 카테고리별 정렬 버튼 -->
			  <!--   <select id="category" name="category" onchange="sortList()">
			        <option value="">-- 카테고리별 --</option>
			        <option value="예술/문화">예술/문화</option>
			        <option value="운동">운동</option>
			        <option value="음식">음식</option>
			        <option value="스터디">스터디</option>
			        <option value="기타">기타</option>
			    </select>
			    
			    &nbsp;
			    
			    <!-- 모집여부별 정렬 체크박스 
			    <input type="checkbox" id="exceptClosed" name="exceptClosed" value="1" style="cursor : pointer;" onchange="sortList()">
			    <label for="exceptClosed" style="cursor : pointer;">모집마감 제외하고 보기</label>
			    <input type="hidden" id="boxChecked" name="boxChecked">  -->
	 		 <!-- </form> -->
			
			<script>
			    function sortList() {
			        // 선택된 카테고리 값
			        var category = document.getElementById("category").value;
			        // 모집여부 체크 여부
			        var exceptClosed = document.getElementById("exceptClosed").checked ? 1 : 0;
			        // 폼 전송
			        document.getElementById("listSort").submit();
			    }
			</script>

			<!-- 게시글 작성 버튼 -->
			<!-- 이 부분은 로그인한 경우에만 보임 -->
			<% if(loginUser != null) { %>
	            <button id="write-enrollForm" onclick="openEnrollForm();">
	                게시글 작성
	            </button>
            <% } else { %>
				<button id="write-enrollForm" onclick="openEnrollForm();" style="visibility : hidden;">
				    게시글 작성
				</button>
			<% } %>
            <br><br>
        </div>
       
        <!-- 게시글 목록 -->
		<table align="center" class="list-area">
			<thead>
				<tr id="boardContext-top">
					<th width="60px">글번호</th>
					<th width="100px">카테고리</th>
					<th width="400px">제목</th>
					<th width="120px">작성자</th>
					<th width="60px">조회수</th>
					<th width="120px">작성일</th>
                    <th width="60px">좋아요</th>
					<th width="100px">모집중 여부</th>
				</tr>
			</thead>
			<tbody>
				<% if(list2.isEmpty()) { %>
					<tr>
						<td colspan="8">
							조회된 리스트가 없습니다.
						</td>
					</tr>
				<% } else { %> 
					<% for(Board b : list2) { %>
						<tr>
							<td><%= b.getBoardNo() %></td>
							<td><%= b.getCategoryName() %></td>
							<td><%= b.getBoardTitle() %> (<%= b.getReplyCount() %>)</td>
							<td><%= b.getBoardWriter() %></td>
							<td><%= b.getCount() %></td>
							<td><%= b.getCreateTime() %></td>
							<td><%= b.getLikeCount() %></td>
							<td>
								<% if(b.getMeetingStatus() == 1) { %> 
									모집중
								<% } else { %>
									모집완료
								<% } %>
							</td>
						</tr>
					<% } %>
				<% } %>
				<!-- <tr>
					<td>15</td>
					<td>운동</td>
					<td>같이 축구하실 분 (3)</td>
					<td>벤틀리남</td>
					<td>100</td>
					<td>2024-04-12</td>
                    <td>5</td>
					<td>모집중</td>
				</tr>
				<tr>
					<td>14</td>
					<td>음식</td>
					<td>알밥 맛집 탐방♡♡ (5)</td>
					<td>트위티쨩</td>
					<td>200</td>
					<td>2024-04-11</td>
                    <td>10</td>
					<td>모집중</td>
				</tr>
				<tr>
					<td>13</td>
					<td>예술/문화</td>
					<td>이누야샤 더빙할 파티원 구합니다!! 남2여2 (2)</td>
					<td>네즈코사랑꾼</td>
					<td>120</td>
					<td>2024-04-10</td>
                    <td>3</td>
					<td>모집마감</td>
				</tr>
				<tr>
					<td>12</td>
					<td>기타</td>
					<td>유포테이블 카페 같이 가실 분 구합니다!! 선착순 1명!!!</td>
					<td>네즈코사랑꾼</td>
					<td>250</td>
					<td>2024-04-09</td>
                    <td>20</td>
					<td>모집마감</td>
				</tr>
				<tr>
					<td>11</td>
					<td>운동</td>
					<td>4월 27일 기아LG전 잠실 직관 같이 가실 분!!! (10)</td>
					<td>네즈코사랑꾼</td>
					<td>300</td>
					<td>2024-04-08</td>
                    <td>100</td>
					<td>모집마감</td>
				</tr>
				<tr>
					<td>10</td>
					<td>예술/문화</td>
					<td>노래방 파티원 구함 (2)</td>
					<td>인팁좌</td>
					<td>342</td>
					<td>2024-04-07</td>
                    <td>7</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>9</td>
					<td>스터디</td>
					<td>☆C언어 스터디☆ (3)</td>
					<td>kh0126</td>
					<td>212</td>
					<td>2024-04-06</td>
                    <td>91</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>8</td>
					<td>기타</td>
					<td>여의도 한강 공원 쓰레기 줍기 운동</td>
					<td>user01</td>
					<td>100</td>
					<td>2024-04-05</td>
                    <td>3</td>
					<td>모집마감</td>
				</tr>
                <tr>
					<td>7</td>
					<td>운동</td>
					<td>같이 헬스할래요?(찡긋)</td>
					<td>무진기행</td>
					<td>256</td>
					<td>2024-04-04</td>
                    <td>27</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>6</td>
					<td>기타</td>
					<td>마라톤 짐 보관 도우미 활동 (1)</td>
					<td>정화수목금토일</td>
					<td>470</td>
					<td>2024-04-03</td>
                    <td>17</td>
					<td>모집마감</td>
				</tr>
                <tr>
					<td>5</td>
					<td>기타</td>
					<td>같이 피방 가실 분 구함!! (2)</td>
					<td>삿된희망</td>
					<td>550</td>
					<td>2024-04-02</td>
                    <td>120</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>4</td>
					<td>기타</td>
					<td>○○제과 신제품 관능테스트 같이 하실 분 (7)</td>
					<td>yamyamgood</td>
					<td>698</td>
					<td>2024-04-01</td>
                    <td>55</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>3</td>
					<td>음식</td>
					<td>술모임(본인 회오리 폭탄주 가능) (10)</td>
					<td>iamlord</td>
					<td>467</td>
					<td>2024-03-31</td>
                    <td>22</td>
					<td>모집중</td>
				</tr>
                <tr>
					<td>2</td>
					<td>스터디</td>
					<td>네즈코사랑꾼이 알려주는 CSS 강의 (4)</td>
					<td>네즈코사랑꾼</td>
					<td>572</td>
					<td>2024-03-30</td>
                    <td>177</td>
					<td>모집마감</td>
				</tr>
                <tr>
					<td>1</td>
					<td>예술/문화</td>
					<td>귀멸의 칼날 도공마을편 영화 같이 보러 가실 분ㅠㅠ</td>
					<td>네즈코사랑꾼</td>
					<td>332</td>
					<td>2024-03-29</td>
                    <td>158</td>
					<td>모집마감</td>
				</tr> -->
			</tbody>
		</table>
		
		<script>
		    $(function() {
		        $(".list-area>tbody>tr").click(function() {
		            <% if(loginUser != null) { %>
		                // 로그인한 사용자인 경우
		                // 글번호 : 현재 클릭된 tr의 첫 번째 자손 td 내용물
		                let bno = $(this).children().eq(0).text();
		                location.href = "<%= contextPath %>/detail.bo?bno=" + bno;
		            <% } else { %>
		                // 로그인하지 않은 사용자인 경우
		                alert("게시글은 로그인한 회원만 열람할 수 있습니다.");
		            <% } %>
		        });
		    });
		</script>

       	<br><br>

		<!-- 페이징바 -->
		<div align="center" class="paging-area">
			<% if(currentPage > 10) { %>
				<!-- 10개의 이전 페이지로 이동 버튼(시프트) -->
            	<button class="paging" 
            		onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage - 10 %>';">
            		&lt;&lt;
            	</button>
			<% } else if(currentPage > 1 && currentPage <= 10) { %>
				<!-- 현재 페이지가 1이 아니고 10번째 이하 페이지라면 무조건 1번째 페이지로 이동 -->
            	<button class="paging" 
            		onclick="location.href = '<%= contextPath %>/list.bo?currentPage=1';">
            		&lt;&lt;
            	</button>
			<% } %>
            <% if(currentPage != 1) { %>
				<!-- 이전페이지로 이동 버튼 -->
				<button class="paging" 
					onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage - 1 %>';">
					&lt;
				</button>
			<% } %>
			
			<% for(int p = startPage; p <= endPage ; p++) { %>
			
				<!-- 현재 내가 보고있는 페이지일 경우는 클릭 안되게끔 -->
				<% if(p != currentPage) { %>
					<!-- 페이지 이동 가능하게끔 -->
					<button class="paging" 
						onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= p %>';">
						<%= p %>
					</button>
				<% } else { %>
					<!-- 페이지 이동 불가능하게끔 -->
					<button disabled><%= p %></button>
				<% } %>
			<% } %>
			
			<% if(currentPage != maxPage) { %>
				<!-- 현재 페이지가 마지막 페이지가 아닌 경우 다음 페이지 버튼 노출 -->
				<button class="paging" 
					onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage + 1 %>';">
					&gt;
				</button>
			<% } %>
			<% if(maxPage - currentPage >= 10) { %>
				<!-- 10개의 다음 페이지로 이동 버튼(시프트) -->
            	<button class="paging" 
            		onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage + 10 %>';">
            		&gt;&gt;
            	</button>
			<% } else if(maxPage - currentPage > 0 && maxPage - currentPage < 10) { %>
				<!-- 맨끝 페이지에서 9번째 앞 페이지까지는 이거 누르면 맨끝 페이지로 이동 -->
				<button class="paging" 
            		onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= maxPage %>';">
            		&gt;&gt;
            	</button>
			<% } %>
		</div>
		
		<br><br>
		
	</div>
	
	<!-- 게시글 작성 버튼 클릭 시 (게시글 작성 페이지로) -->
	<script>
		function openEnrollForm() {
		    location.href = "<%= contextPath %>/enrollForm.bo";
		}
	</script>
</body>
</html>