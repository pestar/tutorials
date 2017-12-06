<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Form</title>
</head>
<body>
	<spring:url value="/personen/save" var="saveUrl" />

	<form:form action="${saveUrl}" method="POST"
		modelAttribute="personenForm">
		<form:hidden path="id" />
		<table>
			<tbody>

				<!-- 					<td>ID:</td> -->
				<%-- 					<td><form:input path="id" /> --%>
				<tr>
					<td>Vorname:</td>
					<td><form:input path="vorname" /></td>
				</tr>
				<tr>
					<td>Nachname:</td>
					<td><form:input path="nachname" /></td>
				</tr>
				<tr>
					<td>Titel:</td>
					<td><form:input path="titel" /></td>
				</tr>
				<tr>
					<td>Straße:</td>
					<td><form:input path="strasse" /></td>
				</tr>
				<tr>
					<td>PLZ:</td>
					<td><form:input path="plz" /></td>
				</tr>
				<tr>
					<td>Ort:</td>
					<td><form:input path="ort" /></td>
				</tr>
				<tr>
					<td>Land:</td>
					<td><form:input path="land" /></td>
				</tr>
				<tr>
					<td>Telefon:</td>
					<td><form:input path="telefon" /></td>
				</tr>
				<tr>
					<td>eMail:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="submit">Save</button>
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>

</body>
</html>