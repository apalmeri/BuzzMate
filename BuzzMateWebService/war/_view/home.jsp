<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Home page</title>
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/inventory.css" /> -->
	</head>
     <%
        String backColor = request.getParameter("COLOR");       
        if (backColor == null)
           backColor = "RosyBrown";        
     %>
    <body BGCOLOR="<%= backColor %>" >
		<form action="${pageContext.servletContext.contextPath}/home" method="post">
			
			<h1 align=center><p>Welcome to Buzz Mate</p></h1>
			<table>
			<tr align=center>
				<td style="font-size:11px"></td>
				<td><input name="logOut" type="submit" value="Log Out"></input></td>
			</tr>
			</table>
		</form>
	</body>
	
</html>