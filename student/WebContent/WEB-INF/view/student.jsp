<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Σύστημα παραλαβής συγγραμμάτων</title>
<link href="<c:url value="/resources/theme/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/theme/script.js" />"></script>
</head>
<body>
<jsp:include page="/resources/theme/navigationBar.jsp" /><br>
<h3>Δηλωμένα συγγράμματα</h3>
<table border="1" id="booksTable">
	<tr>
		<th>Μάθημα</th>
		<th>Τίτλος</th>
		<th>Συγγραφέας</th>
		<th>Εκδότης</th>
		<th>Εξάμηνο</th>
		<th>Παραλήφθηκε</th>
		<th>Οδηγίες Παραλαβής</th>
	</tr>
	<c:forEach var="req" items="${studentReqs}">
		<tr>
			<td>${req.courseName}</td>
			<td>${req.title}</td>
			<td>${req.author}</td>
			<td>${req.publisher}</td>
			<td>${req.semester}</td>
			<td><c:choose>
				    <c:when test="${req.received=='true'}">
				        Ναι
				    </c:when>    
				    <c:otherwise>
				        Όχι
				    </c:otherwise>
				</c:choose></td>
			<td>${req.directions}</td>
		</tr>
	</c:forEach>
</table><br>
<h3>Δήλωση συγγραμμάτων για το τρέχον εξάμηνο</h3>
<form name="books" action="${pageContext.request.contextPath}/student/submitBooks" method="GET">
	<table border="1">
		<tr>
			<th>Μαθήμα</th>
			<th>Τίτλος</th>
			<th>Συγγραφέας</th>
			<th>Επιλογή</th>
		</tr>
		<c:set var="start" value="0" />
		<c:forEach var="courseName" items="${courses}">
			<tr>
				<td>${courseName}</td>
			</tr>
			
			<c:set var="book" value="${books}" />
			<c:forEach begin="${start}" end="${start + 1}" var="i">
				<tr>
					<td></td>
					<td>${book[i].name}</td>
					<td>${book[i].author}</td>
					<td class="checkboxClass"><input id="${courseName}_${i}" name="${courseName}" type="checkbox" value="${book[i].name}"/></td>
				</tr>
			</c:forEach>
			<c:set var="start" value="${start + 2}" />
			<script>
				checkboxlimit(document.forms.books["${courseName}"]);
			</script>
		</c:forEach>
	</table>
	<div class="submitButton">
		<button type="submit">ΥΠΟΒΟΛΗ</button>
	</div>
</form>
</body>
</html>