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
	<form:form action="${pageContext.request.contextPath}/loginForm" method="POST">
		<fieldset>
			<legend>Σύνδεση</legend>
			Email:<br> <input type="text" name="username"><br>
			Password:<br> <input type="password" name="password"><br>
			<br> <input type="submit" value="Είσοδος"><br>
			<p style="color:red">${message}</p>
			<c:if test="${param.error != null}">
				<i style="color:red">Λάθος username ή password.</i>
			</c:if>
		</fieldset>
	</form:form>
</body>
</html>