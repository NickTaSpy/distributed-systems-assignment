<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Σύστημα διαχείρισης συγγραμμάτων</title>
</head>
<body>
<h3>Συγγράμματα</h3>
<table border="1" id="bookTable">
	<tr>
		<th>id</th>
		<th>Τίτλος</th>
		<th>Συγγραφέας</th>
		<th>Διαθέσιμα</th>
	</tr>
	<c:forEach var="book" items="${books}">
		<tr>
			<td>${book.book.id}</td>
			<td>${book.book.name}</td>
			<td>${book.book.author}</td>
			<td>${book.booksAvailable}</td>
		</tr>
	</c:forEach>
</table><br><br>
<h3>Επιβεβαίωση παραλαβής συγγράμματος</h3>
<form action="${pageContext.request.contextPath}/publisher/deliveryConfirmation/confirm/" method="GET">
<table border="1" id="bookTable">
	<tr>
		<th>id βιβλίου</th>
		<th>email φοιτητή</th>
	</tr>
	<tr>
		<td><input name="bookId" type="text"/></td>
		<td><input name="email" type="text"/></td>
	</tr>
</table><br>
<input type="submit" value="ΕΠΙΒΕΒΑΙΩΣΗ"/>
</form>
	
</body>
</html>