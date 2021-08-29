<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!-- <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css' /> -->
<link href="/bms_project/resources/style/hoststyle.css" rel="stylesheet" type="text/css" media="screen" />

<html>

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<!-- <div id="logo">
				<h1><a href="#">BMS</a></h1>
				<p>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a></p>
				<p>BMS_PROJECT</p>
			</div>  -->
			<%@ include file ="hostlogo.jsp" %> 
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<%@ include file="host_stockMenu.jsp" %> 
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				
				<!-- 게시판 시작부분 -->	
				<div width="1100px" align="center">
					
					<h2><center>상세페이지</center></h2>
					<hr><br>
					
					<table align="center" >
						<tr>
							<td rowspan="7">
								<img src="/bms_project/resources/images/${dto.kimg}" width="100%" height="100%">
							</td>
							<th style="width:150px">책번호</th>
							<td>${dto.bookNum}</td>
						</tr>
						
						<tr>
							<th>제목</th>
							<td>${dto.bookName}</td>
						</tr>
						
						<tr>
							<th>저자</th>
							<td>${dto.author}</td>
						</tr>
						
						<tr>
							<th>출판사</th>
							<td>${dto.publisher}</td>
						</tr>
						
						<tr>
							<th>가격</th>
							<td>${dto.price}</td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td>${dto.bookName}</td>
						</tr>
						
						<tr>
							<th>권수</th>
							<td>${dto.bookcount}</td>
						</tr>
						
						<tr>
						
							<th colspan="3" align="center">
								<input class="inputButton" type="button" value="글수정" 
									   onclick="window.location='host_stockmodifyForm?bookNum=${dto.bookNum}&pageNum=${pageNum}'">
									  
								<input class="inputButton" type="button" value="글삭제"
									   onclick="window.location='host_stockdeleteForm?bookNum=${dto.bookNum}&pageNum=${pageNum}'">	   
									   
								<input class="inputButton" type="button" value="목록보기"
										onclick="window.location='host_stockList?pageNum=${pageNum}'">
							
							</th>
						
						</tr>
						
					
					</table>
					
					
				</div>
				
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page --> 
</div>
<div id="footer">
	<%@ include file ="hostMain_footer.jsp" %>
</div>
<!-- end #footer -->
</body>
</html>