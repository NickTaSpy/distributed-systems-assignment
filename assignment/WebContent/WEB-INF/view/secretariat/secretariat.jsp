<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Σύστημα διαχείρισης συγγραμμάτων</title>
	<link href="<c:url value="/resources/theme/style.css" />" rel="stylesheet">
</head>
<body>
<h3>Φοιτητές που δεν έχουν τελειώσει την δήλωση</h3>
<table id="table">
	<tr>
		<th>Όνομα</th>
		<th>Επίθετο</th>
		<th>Τηλέφωνο</th>
		<th>Email</th>
	</tr>
	<c:forEach var="student" items="${students}">
		<tr>
			<td>${student.firstName}</td>
			<td>${student.lastName}</td>
			<td>${student.phone}</td>
			<td>${student.email}</td>
		</tr>
	</c:forEach>
</table><br>
<h3>Emails παραπάνω φοιτητών</h3>
<input type="text" value="${emails}" readonly/>
</body>
</html>