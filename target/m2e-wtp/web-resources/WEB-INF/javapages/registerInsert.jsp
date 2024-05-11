<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Insert</title>
</head>
<body>

	<form method="POST" action="main">
		<label>Name:</label>
		<input type="text" name="name"><br>
		<label>Email:</label>
		<input type="text" name="email"><br>
		<label>Address:</label>
		<input type="text" name="address"><br>
		<label>Birth Date (E.g.: dd/mm/yyyy):</label>
		<input type="text" name="birth_date"><br>
		<button type="submit">Insert</button>
	</form>
	
</body>
</html>