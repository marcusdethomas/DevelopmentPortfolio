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
<title><c:out value="${books.title}"></c:out></title>
</head>
<body>
<div class = "container col-7">
<div class = "d-flex justify-content-evenly mt-4">
<a href ="/dashboard">Return Home</a>
<a href = "/create">Add a Entry</a>
<a href = "/logout">Log Out</a>
</div>
	<div>
	<h1><c:out value="${entries.title}"></c:out></h1>
	
	<c:set var = "owner" value = "${entries.user.id}"/>
	<c:set var = "user" value = "${userId.id}"/>
	
	<div>
		<div>
		<span>Description:</span>
		<c:out value="${entries.description}"></c:out>
		</div>
		   
		<div>
		<span>Tags:</span>
		<c:forEach var="tags" items="${entry.tags}">
		<c:out value="${tags.tag}"></c:out>
		<c:if test = "${entry.tags.indexOf(tag)<entry.tags.size()-1}">, </c:if>
		</c:forEach>
		</div>
		
		<div>
		<span>Created On: </span>
		<fmt:formatDate type = "date" value = "${entries.createdAt}" />
		<span> at </span>
		<fmt:formatDate type = "time" value = "${entries.createdAt}" />
		</div>
		
		<div>
		<span>Updated On: </span>
		<fmt:formatDate type = "date" value = "${entries.updatedAt}" />
		<span> at </span>
		<fmt:formatDate type = "time" value = "${entries.updatedAt}" />
		</div>
		<c:if test = "${owner == user}">
<a href ="/edit/${entries.id}">Edit Entry</a>
<a href ="/delete/${entries.id}">Delete Entry</a>
</c:if>
	</div>
</div>
</body>
</html>