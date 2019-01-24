<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/theme/style.css" />" rel="stylesheet">	
	<script type="text/javascript">
		function onRoleChange() {
			var roleSelection = document.getElementById("roleSelection");
			var role = roleSelection.options[roleSelection.selectedIndex].value;
			console.log("test");
			if (role == 1){
				document.getElementById("roleColumn").innerHTML = "Όνομα Εκδότη";
				console.log("123");
			}else{
				document.getElementById("roleColumn").innerHTML = "Τμήμα";
			}
		}
	</script>
	<title>Σύστημα διαχείρισης χρηστών</title>
</head>
<body>

<h3>Προσθήκη Χρήστη</h3>
<form action="${pageContext.request.contextPath}/admin/add/" method="GET">
<table id="userTable3">
	<tr>
		<th>Όνομα</th>
		<th>Επώνυμο</th>
		<th>Email</th>
		<th>Password</th>
		<th>Τηλέφωνο</th>
		<th>Ρόλος</th>
		<th id="roleColumn"></th>
	</tr>
	<tr>
		<td><input name="firstName" type="text"/></td>
		<td><input name="lastName" type="text"/></td>
		<td><input name="email" type="text"/></td>
		<td><input name="password" type="text"/></td>
		<td><input name="phone" type="text"/></td>
		<td>
			<select id="roleSelection" name="role" onchange="onRoleChange()">
				<option value="0">Καθηγητής</option>
				<option value="1">Εκδότης</option>
				<option value="2">Γραμματέας</option>
				<option value="3">Φοιτητής</option>
			</select>
		</td>
		<td><input name="roleColumn" type="text"/></td>
		<td><button type="submit">ΠΡΟΣΘΗΚΗ</button></td>
	</tr>
</table><br>
</form>
<script type="text/javascript">onRoleChange();</script>
	<table align="right">
		<tr>
			<th>Τμήματα</th>
		</tr>
		<tr><td>πληροφορική_και_τηλεματική</td></tr>
		<tr><td>οικιακής_οικονομίας</td></tr>
		<tr><td>γεωγραφίας</td></tr>
		<tr><td>διαιτολογίας</td></tr>
	</table>
<h3>Χρήστες Συστήματος</h3>
<table id="userTable">
	<tr>
		<th>id</th>
		<th>Όνομα</th>
		<th>Επώνυμο</th>
		<th>Email</th>
		<th>Τηλέφωνο</th>
		<th>Ρόλος</th>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.email}</td>
			<td>${user.phone}</td>
			<td>${user.role}</td>
			<td><form action="${pageContext.request.contextPath}/admin/update/${user.id}" method="GET"><button type="submit">ΕΠΕΞΕΡΓΑΣΙΑ</button></form></td>
			<c:if test="${user.role ne 'admin'}">
				<td><form action="${pageContext.request.contextPath}/admin/delete/${user.id}" method="GET"><button type="submit">ΔΙΑΓΡΑΦΗ</button></form></td>
			</c:if>
		</tr>
	</c:forEach>
</table><br>
<c:set var = "showDepartments" value = "false"/>
<h3>Ενημέρωση Στοιχείων</h3>
<form action="${pageContext.request.contextPath}/admin/update/${selectedUser.id}/finalUpdate/" method="GET">
	<table id="userTable2">
		<tr>
			<th>id</th>
			<th>Όνομα</th>
			<th>Επώνυμο</th>
			<th>Email</th>
			<th>Τηλέφωνο</th>
			<c:choose>
			    <c:when test="${selectedUser.role=='professor'}">
			        <th>Τμήμα</th>
			    </c:when>
			    <c:when test="${selectedUser.role=='publisher'}">
					<th>Όνομα Εκδότη</th>
			    </c:when>
	   		    <c:when test="${selectedUser.role=='secretariat'}">
					<th>Τμήμα</th>
			    </c:when>
	    		<c:when test="${selectedUser.role=='student'}">
					<th>Τμήμα</th>
			    </c:when>
			</c:choose>
		</tr>
		<tr>
			<td>${selectedUser.id}</td>
			<td><input name="firstName" type="text" value="${selectedUser.firstName}"/></td>
			<td><input name="lastName" type="text" value="${selectedUser.lastName}"/></td>
			<td><input name="email" type="text" value="${selectedUser.email}"/></td>
			<td><input name="phone" type="text" value="${selectedUser.phone}"/></td>
			<c:choose>
			    <c:when test="${selectedUser.role=='professor'}">
			    	<td><input name="department" type="text" value="${specialUser.department}"/></td>
			    	<c:set var = "showDepartments" value = "true"/>
			    </c:when>
			    <c:when test="${selectedUser.role=='publisher'}">
					<td><input name="publisherName" type="text" value="${specialUser.publisherName}"/></td>
			    </c:when>
	   		    <c:when test="${selectedUser.role=='secretariat'}">
					<td><input name="department" type="text" value="${specialUser.department}"/></td>
					<c:set var = "showDepartments" value = "true"/>
			    </c:when>
	    		<c:when test="${selectedUser.role=='student'}">
					<td><input name="department" type="text" value="${specialUser.department}"/></td>
					<c:set var = "showDepartments" value = "true"/>
			    </c:when>
			</c:choose>
		</tr>
	</table><br>
	<button type="submit">ΕΝΗΜΕΡΩΣΗ</button>
</form><br>

</body>
</html>