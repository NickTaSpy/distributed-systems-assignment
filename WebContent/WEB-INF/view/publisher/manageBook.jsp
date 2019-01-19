<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<spring:url value="/resources/publisher/script.js" var="mainJs" />
	<spring:url value="/resources/publisher/style.css" var="mainCss" />
	<link href="${mainCss}" rel="stylesheet" />
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
			<td><form action="${pageContext.request.contextPath}/publisher/manageBook/update/${book.book.id}/booksAvailable/" method="GET">
			<input name="booksAvailable" type="text" value="${book.booksAvailable}"/>
			<input type="submit" value="ΑΛΛΑΓΗ"/>
			</form></td>
			<td><form action="${pageContext.request.contextPath}/publisher/manageBook/delete/${book.bookId}" method="GET"><button type="submit">ΔΙΑΓΡΑΦΗ</button></form></td>
		</tr>
	</c:forEach>
</table>
<br>
<h3>Προσθήκη Συγγράμματος</h3>
<form action="${pageContext.request.contextPath}/publisher/manageBook/add/" method="GET">
	Τίτλος Βιβλίου<br><input name="bookName" type="text" value=""/><br><br>
	Συγγραφέας Βιβλίου<br><input name="bookAuthor" type="text" value=""/><br><br>
	Διαθέσιμα Βιβλία<br><input name="booksAvailable" type="text" value=""/><br><br>
	<input type="submit" value="ΠΡΟΣΘΗΚΗ"/>
</form>

<h3>Ενημέρωση οδηγιών για το σημείο παράδοσης των βιβλίων</h3>
<form action="${pageContext.request.contextPath}/publisher/manageBook/updateDirections/" method="GET">
	<input name="directions" type="text" value="${currentDirections}"/><br><br>
	<input type="submit" value="ΕΝΗΜΕΡΩΣΗ"/>
</form>
	
</body>
</html>