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
<jsp:include page="/resources/theme/navigationBar.jsp" />
<h3>Μαθήματα</h3>
<table id="coursesTable">
	<tr>
		<th>Μάθημα</th>
		<th></th>
	</tr>
	<c:forEach var="course" items="${courses}">
		<tr>
			<td>${course.name}</td>
			<td><form action="${pageContext.request.contextPath}/professor/bookSelection/select/${course.name}" method="GET"><input type="submit" value="ΕΠΙΛΟΓΗ"/></form></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>