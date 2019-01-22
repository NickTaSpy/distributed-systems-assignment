<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Σύστημα διαχείρισης συγγραμμάτων</title>
<link href="<c:url value="/resources/theme/style.css" />" rel="stylesheet">
</head>
<body>
	<table>
		<tr>
			<th>Όνομα υπηρεσίας</th>
		</tr>
		<!-- loop over and print our customers -->
		<c:forEach var="service" items="${services}">
			<tr>
				<td><a href="${pageContext.request.contextPath}${service.link}">${service.name}</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>