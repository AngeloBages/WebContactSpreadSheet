<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c"%>   
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Update</title>
</head>
<body>

	<fmt:formatDate type="date" value="${contactBirthDate}" pattern="dd/MM/yyyy" var="formattedDate"/>

	<form method="POST" action="main">
		<input type="hidden" name="id" value="${contact.id}">
		<label>Name:</label>
		<input type="text" name="name" value="${contact.name}"><br>
		<label>Email:</label>
		<input type="text" name="email" value="${contact.email}"><br>
		<label>Address:</label>
		<input type="text" name="address" value="${contact.address}"><br>
		<label>Birth Date (E.g.: dd/mm/yyyy):</label>
		<input type="text" name="birth_date" value="${formattedDate}"><br>
		<button type="submit">Update</button>
	</form>
	
</body>
</html>