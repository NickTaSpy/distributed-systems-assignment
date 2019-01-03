<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Σύστημα διαχείρισης συγγραμμάτων</title>
</head>
<body>
	<form action="loginForm" method="POST">
		<fieldset>
			<legend>Σύνδεση</legend>
			Email:<br> <input type="text" name="email"><br>
			Password:<br> <input type="password" name="password"><br>
			<br> <input type="submit" value="Submit">
		</fieldset>
	</form>
	<div id="container">
		<div id="content">
			<!--  add our html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				<!-- loop over and print our customers -->
				<c:forEach var="tempUser" items="${users}">

					<tr>
						<td>${tempUser.firstName}</td>
						<td>${tempUser.lastName}</td>
						<td>${tempUser.email}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>