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
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form:form action="${pageContext.request.contextPath}/loginForm" method="POST" class="login-form">
				<input type="text" name="username" placeholder="Email">
				<br>
				<input type="password" name="password" placeholder="Κωδικός">
				<br>
				<br>
				<button type="submit">Είσοδος</button>
				<br>
				<p style="color: red">${message}</p>
				<c:if test="${param.error != null}">
					<i style="color: red">Λάθος username ή password.</i>
				</c:if>
			</form:form>
		</div>
	</div>
</body>
</html>