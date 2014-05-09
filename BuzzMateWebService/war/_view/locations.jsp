<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/Locations.css" />
		<title>Locations</title>
</head>
<body>

			<table class="locations">
				<tr>
					<th>All locations nearby:</th>
				</tr>
				<c:forEach var="locations" items="${Locations}">
					<tr>
						<td>${location.name}</td>
						<td>${location.type}</td>
						<td>${location.state}</td>
						<td>${location.city}</td>
						<td>${location.phonenumber}</td>
						<td>${location.mailcode}</td>
						<td>${location.street1}</td>
					</tr>
				</c:forEach>
			</table>
	</body>
</html>