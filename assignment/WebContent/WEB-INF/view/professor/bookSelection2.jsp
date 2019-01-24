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
	<script type="text/javascript">
		function checkboxlimit(checkgroup, limit){
			var checkedcount=0
			for (var i=0; i<checkgroup.length; i++)
				checkedcount+=(checkgroup[i].checked)? 1 : 0
			if (checkedcount!=limit){
				alert("Πρέπει να επιλέξετε ακριβώς "+limit+" συγγράμματα")
				this.checked=false
				event.preventDefault();
			}
		}
	</script>
</head>
<body>
<h3>Δήλωση 2 συγγραμμάτων για το μάθημα: ${courseName}</h3>
<form action="${pageContext.request.contextPath}/professor/bookSelection/select/${courseName}/books/" method="GET" name="form_name" id="form_name">
	<table id="booksTable">
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
					<td id="ena">${book.bookId}</td>
					<td>${book.book.name}</td>
					<td>${book.book.author}</td>
					<td>${pub.publisherName}</td>
					<td><input class="single-checkbox" type="checkbox" name="book" value="${book.bookId}"/></td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
	<br><input id="submitInput" type="submit" value="ΥΠΟΒΟΛΗ"/>
</form>
<script type="text/javascript">
	document.getElementById("submitInput").onclick=function(){
		checkboxlimit(document.forms.form_name.book, 2);
	}
</script>
</body>
</html>