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
<h3>Δήλωση 2 συγγραμμάτων για το μάθημα: ${courseName}</h3>
<form action="${pageContext.request.contextPath}/professor/bookSelection/select/booksSelected/books/" method="GET">
	<table border="1" id="booksTable">
		<tr>
			<th>id</th>
			<th>Τίτλος</th>
			<th>Συγγραφέας</th>
			<th>Εκδότης</th>
			<th></th>
		</tr>
		<c:forEach var="pub" items="${publishers}">
			<c:forEach var="book" items="${pub.publisherBooks}">
				<tr>
					<td>${book.bookId}</td>
					<td>${book.book.name}</td>
					<td>${book.book.author}</td>
					<td>${pub.publisherName}</td>
					<td><input type="checkbox" name="book" value="${book.bookId}"/></td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
	<br><input type="submit" value="ΥΠΟΒΟΛΗ"/>
</form>
</body>
</html>