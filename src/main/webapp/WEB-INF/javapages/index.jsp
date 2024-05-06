<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.contactspreadsheet.models.Contact" 
                         import="com.contactspreadsheet.dao.ContactDaoImpl" %>
                         
<%@ taglib uri="jakarta.tags.core" prefix="c"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Sheet</title>
</head>
<body>

	<h1>Contact SpreadSheet</h1>
	<br>
	
	<c:if test="${errorMessage != null}">
		<p><c:out value="${errorMessage}"></c:out></p>
	</c:if>
	
	<table border="1px">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Birth Day</th>
			<th>Actions</th>
		</tr>
		
	    <c:forEach items="${dao.list}" var="contact">
	    	<tr>
			<td>${contact.name}</td>
			<td>${contact.email}</td>
			<td>${contact.address}</td>
			<td>${contact.birthDate}</td>
			<td>
			    <a href="/contact-spreadsheet/main?action=RegisterUpdateServlet&id=${contact.id}">Update</a> |
                <a href="/contact-spreadsheet/main?action=RegisterDeleteServlet&id=${contact.id}">Delete</a>
            </td>
			</tr>
	    </c:forEach>
		
	</table>
	
	<a href="${pageContext.request.servletContext.contextPath}/main?action=RegisterInsertServlet">Insert new contact...</a>

</body>
</html>