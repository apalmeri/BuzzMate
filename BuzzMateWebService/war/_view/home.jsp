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

		<h1 align=center><p>Welcome to Buzz Mate</p></h1>
		
	</body>
	
</html>