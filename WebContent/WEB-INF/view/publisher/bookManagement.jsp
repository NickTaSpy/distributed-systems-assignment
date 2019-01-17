<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Σύστημα διαχείρισης συγγραμμάτων</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/publisher/updateInstructions" method="GET">
		<button type="submit">Ενημέρωση οδηγιών για το σημείο παράδοσης</button><br>
		<button type="submit" formaction="${pageContext.request.contextPath}/publisher/manageBook">Διαχείριση βιβλίων</button>
	</form>
</body>
</html>