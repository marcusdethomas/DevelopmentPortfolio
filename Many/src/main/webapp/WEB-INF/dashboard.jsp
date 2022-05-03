<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<div>
<div class = "container col-7">
<div class = "d-flex justify-content-evenly mt-4">
<a href = "/create">Add a Entry	</a>
<a href = "/logout">Log Out</a>
</div>
	<table class="table table-striped table-bordered table-hover mt-5">
	  <h1> Welcome, <c:out value="${userId.fullName}"></c:out></h1>
	  <thead>
        <tr>
            <th>Date</th>
            <th>Title</th>
            <th>Description</th>
            <th>Tags</th>
        </tr>
    </thead>
		<tbody>
			<c:forEach var="entry" items="${entries}">
				<tr>
					<td>
					<fmt:formatDate value="${entry.createdAt}"/>
					</td>
					
					<td>
					<a href = "entries/${entry.id}"><c:out value="${entry.title}"></c:out></a>
					</td>
					
					<td>
					<c:out value="${entry.description}"></c:out>
					</td>
				
				
					<td>
					<c:forEach var="tags" items="${entry.tags}">
					<c:out value="${tags.tag}"></c:out>
					<c:if test = "${entry.tags.indexOf(tag)<entry.tags.size()-1}"></c:if>
					</c:forEach>
					</td>
				
				</tr>	
			</c:forEach>
    	</tbody>
    </table>
</div>
</div>
</body>
</html>