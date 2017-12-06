<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Another Address Book Application</title>
</head>
<body>


	<table width="100%" border="1px">
		<thead>
			<tr>
				<th>ID</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>Titel</th>
				<th>Straße</th>
				<th>PLZ</th>
				<th>Ort</th>
				<th>Land</th>
				<th>Telefon</th>
				<th>eMail</th>
<!-- 				<th>Update</th> -->
<!-- 				<th>Delete</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="person">
				<tr>
					<td>${person.id}</td>
					<td>${person.vorname}</td>
					<td>${person.nachname}</td>
					<td>${person.titel}</td>
					<td>${person.strasse}</td>
					<td>${person.plz}</td>
					<td>${person.ort}</td>
					<td>${person.land}</td>
					<td>${person.telefon}</td>
					<td>${person.email}</td>
					<td><spring:url value="/personen/update/${person.id}"
							var="updateUrl" /><a href="${updateUrl}">Update</a></td>
					<td><spring:url value="/personen/delete/${person.id}"
							var="deleteUrl" /><a href="${deleteUrl}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<spring:url value="/personen/add" var="addUrl" />
	<a href="${addUrl}">New Person</a>

</body>
</html>