<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Σύστημα παραλαβής συγγραμμάτων</title>
</head>
<body>
<h3>Παραληφθέντα συγγράμματα</h3>
<table id="booksTable">
	<tr>
		<th>Μάθημα</th>
		<th>Τίτλος</th>
		<th>Συγγραφέας</th>
		<th>Εκδότης</th>
		<th>Εξάμηνο</th>
		<th>Παραλήφθηκε</th>
	</tr>
	<c:forEach var="book" items="${books}">
		<tr>
			<td>${course.name}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.title}</td>
			<td>${book.title}</td>
			<td>${book.title}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>