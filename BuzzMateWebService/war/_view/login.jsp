<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Log in</title>
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/inventory.css" /> -->
	</head>
 	<%
        String backColor = request.getParameter("COLOR");       
        if (backColor == null)
           backColor = "RosyBrown";        
     %>
     <style>
            * { font-size: 19px; font-family: Verdana }    
	</style>
    <body BGCOLOR="<%= backColor %>" >
		
		<h1 align=center><p>Welcome to Buzz Mate</p></h1>
		
	</body>
		<body>
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<table>
				<tr align=center>
					<td style="font-size:12px">Username:</td>
					<td><input type="text" name="username" size="20"></input></td>
				</tr>
				<tr align=center>
					<td style="font-size:12px">Password:</td>
					<td><input type="password" name="password" size="20"></input></td>
				</tr>
				<tr align=center>
					<td style="font-size:11px"></td>
					<td><input name="loginButton" type="submit" value="Log in"></input></td>
				</tr>
				<tr align=center>
					<td style="font-size:11px"></td>
					<td><input name="newUserButton" type="submit" value="New User?"></input></td>
				</tr>
			</table>
		</form>
		<c:if test="${ ! empty message}">
			${message}
		</c:if>
	</body>
</html>