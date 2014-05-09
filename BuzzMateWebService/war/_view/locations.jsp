<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Locations</title>
</head>
<body>
			<table class="location">
				<tr>
					<th>All locatins nearby:</th>
				</tr>
				<c:forEach var="location" items="${LocationList}">
					<tr>
						<td>${location.name}</td>
						<td>
							<a href="${pageContext.servletContext.contextPath}/app/locations/${location.name}">View</a>
						</td>
					</tr>
				</c:forEach>
			</table>
</body>
</html>