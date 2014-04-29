<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>${Item.name}</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/inventory.css" />
	</head>

	<body>
		<!-- VIEW -->
		<c:if test="${empty action or action == 'view'}">
			<h1>Item details</h1>
			<table>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Operations</th>
				</tr>
				<tr>
					<td>${Item.name}</td>
					<td>${Item.quantity}</td>
					<td>
						<a href="${pageContext.servletContext.contextPath}/inventory/${Item.name}?action=edit">Edit</a>
					</td>
			</tr>
			</table>
		</c:if>
		
		<!-- TODO: UIs for other actions (edit, add, delete) -->
		
		<!-- POST -->
		<c:if test="${action == 'edit'}">
			<h1>Edit Item</h1>
			<form actions="${pageContext.servletContext.contextPath}/inventory/${Item.name}?action=edit" method="post">
			<table>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Operations</th>
				</tr>
				<tr>
					<td>${Item.name}</td>
					<td><input name="itemQuantity" type="numbers" value="${Item.quantity}"/></td>
			</tr>
			</table>
					<input type="submit" name="submit" value="submit"/>
			</form>
		</c:if>
		
		<c:if test="${! empty result}">
			<div class="result">${result}</div>
		</c:if>
		
		<!-- ADD -->
		<c:if test="${action == 'add'}">
			<h1>Add Item</h1>
			<form actions="${pageContext.servletContext.contextPath}/inventory/${Item.name}?action=add" method="post">
			<table>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
				</tr>
				<tr>
					<td><input name="itemName" type="text" value="${Item.name}"/></td>
					<td><input name="itemQuantity" type="numbers" value="${Item.quantity}"/></td>
			</tr>
			</table>
					<input type="submit" name="submit" value="submit"/>
			</form>
		</c:if>
		
		<!-- DELETE -->
		<c:if test="${action == 'delete'}">
			<h1>Delete Item</h1>
			<form actions="${pageContext.servletContext.contextPath}/inventory/${Item.name}?action=delete" method="post">
			<table>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
				</tr>
				<tr>
					<td>${Item.name}</td>
					<td>${Item.quantity}</td>
			</tr>
			</table>
					<input type="submit" name="submit" value="submit"/>
			</form>
		</c:if>
		
		
		
		<div class="link"><a href="${pageContext.servletContext.contextPath}/inventory">Back to inventory</a></div>
	</body>
</html>