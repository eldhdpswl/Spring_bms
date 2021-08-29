<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<script src="${project}script.js"></script>
<!-- <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css' /> --> 
<link href="/bms_project/resources/style/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/bms_project/resources/style/login.css" rel="stylesheet" type="text/css" media="screen" />

<html>
<body onload="passwdFocus()">
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<%@ include file ="logo.jsp" %>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<c:if test="${sessionScope.memId == null}">
			<%@ include file="main_menu.jsp" %>
		</c:if>
		<c:if test="${sessionScope.memId != null}">
			<%@ include file="main_menu_connect.jsp" %>
		</c:if>
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div width="1100px" align="center">    
					<h2>회원탈퇴</h2>
	
					<form action="deletePro" method="post" name="passwdform"
								onsubmit="return passwdCheck();">
						<table>
							<tr>
								<th colspan="2">비밀번호를 입력하세요</th>
							</tr>
							
							<tr>
								<th>비밀번호</th>
								<td><input class="input" type="password" name="pwd" maxlength="10"></td>
							
							</tr>
							<tr>
								<th colspan="2" align="center">
									<input class="inputButton" type="submit" value="회원탈퇴">
									<input class="inputButton" type="reset" value="탈퇴취소"
										onclick="window.history.back();">
								</th>
								
							
							</tr>
						</table>			
						
					</form>
									
				</div>
				
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div> 
			</div>
		</div>
	</div>
	<!-- end #page --> 
</div>
<div id="footer">
	<%@ include file ="main_footer.jsp" %>
</div>
<!-- end #footer -->
</body>
</html>