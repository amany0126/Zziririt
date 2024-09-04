<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.zziririt.group.model.vo.Group" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<% ArrayList<Group> newList = (ArrayList<Group>) request.getAttribute("newList");
ArrayList<Group> closeList = (ArrayList<Group>) request.getAttribute("closeList");
%>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<style>
		.wrap {
			border: 1px solid;
			display: flex;
			overflow: hidden;
		}

		.wrap .rolling-list ul {
			padding: 0px;
			display: flex;
		}

		.wrap .rolling-list ul li {
			box-sizing: border-box;
			display: flex;
			align-items: center;
			flex-shrink: 0;
		}

		.rolling-list.original {
			animation: rollingleft1 40s linear infinite;
		}

		.rolling-list.clone {
			animation: rollingleft2 40s linear infinite;
		}

		@keyframes rollingleft1 {
			0% {
				transform: translateX(0);
			}

			50% {
				transform: translateX(-100%);
			}

			50.01% {
				transform: translateX(100%);
			}

			100% {
				transform: translateX(0);
			}
		}

		@keyframes rollingleft2 {
			0% {
				transition: translateX(0);
			}

			100% {
				transform: translateX(-200%);
			}
		}
		
	</style>
</head>

<body>
	<%@ include file="views/common/header.jsp" %>
		<div class="container" id="contentDiv" align="center">

			<div class="row col-12">
				<div class="col-12 mt-5" aling="center">
					<div class="col-12 row">

						<div class="col">
							<h1>신규 그룹</h1>
						</div>

					</div>

					<div class="col-12 ">
						<div class="wrap ">
							<div class="rolling-list">
								<ul>
									<% if (!newList.isEmpty()) { for (Group g:newList) { %>
										<li>
											<div class="image-wrap" onclick="go(this)">
												<input hidden value="<%=g.getGroupNo() %>">
												<img src="<%=g.getGroupProfile()%>"
													height="150px"><br><%=g.getGroupArea() %><br>
												<%=g.getGroupName() %><%=g.getCurrentMem() %>/<%=g.getGroupLimit() %></div>
										</li>

										<% } } else { %>
											<h3>결과가 없습니다.</h3>
											<% } %>
								</ul>
							</div>
						</div>
					</div>
				</div>
						<div class="col-12 mt-5" aling="center">
							<div class="col-12 row">
								
								<div class="col">
									<h1>정원 마감 직전</h1>
								</div>
								
							</div>
							<div class="col-12">
						<div class="wrap ">
							<div class="rolling-list">
								<ul>
									<% if (!newList.isEmpty()) { for (Group g:closeList) { %>
										<li>
											<div class="image-wrap" onclick="go(this)">
												<input hidden value="<%=g.getGroupNo() %>">
												<img src="<%=g.getGroupProfile()%>"
													height="150px"><br><%=g.getGroupArea() %><br>
												<%=g.getGroupName() %><%=g.getCurrentMem() %>/<%=g.getGroupLimit() %></div>
										</li>

										<% } } else { %>
											<h3>결과가 없습니다.</h3>
											<% } %>
								</ul>
							</div>
						</div>
					</div>
						</div>
					</div>
					</div>
					<script>
						function go(x) {
							location.href = "<%=contextPath%>/group/view?gno=" + $(x).children().eq(0).val()
						}
						
						let roller1 = document.querySelectorAll('.rolling-list')[0];
						roller1.id = 'roller1'
						
						
						let clone1 = roller1.cloneNode(true);
						
						// cloneNode : 노드 복제. 기본값은 false. 자식 노드까지 복제를 원하면 true 사용
						clone1.id = 'roller2';
						
						document.querySelector('.wrap').appendChild(clone1)// wrap 하위 자식으로 부착
						
						document.querySelector('#roller1').style.left = '0px';
						document.querySelector('#roller2').style.left = (document.querySelector('.rolling-list ul').offsetWidth+200) + 'px';
						// offsetWidth : 요소의 크기 확인(margin을 제외한 padding값, border값까지 계산한 값)

						let roller2 = document.querySelectorAll('.rolling-list')[2];
						roller2.id = 'roller3'
						let clone2 = roller2.cloneNode(true);
						clone2.id = 'roller4'
						document.querySelectorAll('.wrap')[1].appendChild(clone2)
						document.querySelector('#roller3').style.left = '0px';
						document.querySelector('#roller4').style.left = (document.querySelector('.rolling-list ul').offsetWidth+200) + 'px';
						roller1.classList.add('original');
						clone1.classList.add('clone');
						roller2.classList.add('original');
						clone2.classList.add('clone');
					</script>
</body>

</html>