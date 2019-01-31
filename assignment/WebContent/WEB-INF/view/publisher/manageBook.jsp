<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/theme/style.css" />" rel="stylesheet">
	<title>Σύστημα διαχείρισης συγγραμμάτων</title>
</head>
<body>
<jsp:include page="/resources/theme/navigationBar.jsp" />
<h3>Συγγράμματα</h3><br>
<h4>Κάντε κλικ στο κελί "Διαθέσιμα" για επεξεργασία του αριθμού και μετά κλικ στο κουμπί "Αλλαγή"</h4>
<table id="bookTable">
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
			<input name="booksAvailable" type="number" min="0" value="${book.booksAvailable}"/>
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