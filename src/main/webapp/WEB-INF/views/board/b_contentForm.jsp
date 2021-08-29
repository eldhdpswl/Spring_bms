<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="b_setting.jsp" %>

<!-- <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css' /> -->
<link href="/bms_project/resources/style/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/bms_project/resources/style/boardstyle.css" rel="stylesheet" type="text/css" media="screen" />

<html>

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<!-- <div id="logo">
				<h1><a href="#">BMS</a></h1>
				<p>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a></p>
				<p>BMS_PROJECT</p>
			</div> -->
			<%@ include file ="../member/logo.jsp" %>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<!-- <ul>
			<li class="current_page_item"><a href="#">Home</a></li>
			<li><a href="main.do">Home</a></li>
			<li><a href="login.do">Login</a></li>
			<li><a href="#">Book</a></li>
			<li><a href="#">장바구니</a></li>
			<li><a href="#">마이페이지</a></li>
			<li><a href="#">게시판</a></li>
		</ul> -->
		<%-- <%@ include file="main_menu.jsp" %> --%>
		<c:if test="${sessionScope.memId == null}">
			<%@ include file="../member/main_menu.jsp" %>
		</c:if>
		
		<c:if test="${sessionScope.memId != null}">
			<%@ include file="../member/main_menu_connect.jsp" %>
		</c:if>
		
		<%-- <c:if test="${sessionScope.memId == host}">
			<%@ include file="hostMain_menu.jsp" %>
		
		</c:if> --%>
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div align="center">
				<h2><center>상세페이지</center></h2>
					<table align="center">
						<tr>
							<th style="width:150px">글번호</th>
							<td style="width:150px">${number}</td>
						
							<th style="width:150px">조회수</th>
							<td style="width:150px">${dto.readCnt}</td>
						</tr>
						
						<tr>
							<th style="width:150px">작성자</th>
							<td style="width:150px">${dto.writer}</td>
							
							<th style="width:150px">작성일</th>
							<td style="width:150px" align="center">
								<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
							</td>
						</tr>
						
						<tr>
							<th>글제목</th>
							<td colspan="3">${dto.subject}</td>
						</tr>
						
						<tr>
							<th>글내용</th>
							<td colspan="3">${dto.content}</td>
						</tr>
						
						<tr>
							<th colspan="4">
								<input class="inputButton" type="button" value="글수정" 
									   onclick="window.location='b_modifyForm?num=${dto.num}&pageNum=${pageNum}'">
								<input class="inputButton" type="button" value="글삭제"
									   onclick="window.location='b_deleteForm?num=${dto.num}&pageNum=${pageNum}'">	   
								<input class="inputButton" type="button" value="답글쓰기"
									   onclick="window.location='b_writeForm?num=${dto.num}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
								<input class="inputButton" type="button" value="목록보기"
										onclick="window.location='boardList?pageNum=${pageNum}'">
							
							</th>
						
						</tr>
						
					
					</table>
				
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page --> 
</div>
<div id="footer">
	<%@ include file ="../member/main_footer.jsp" %>
</div>
<!-- end #footer -->
</body>
</html>