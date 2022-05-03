<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Read Share</title>
</head>
<body>
<div class = "container col-5">
 <form:form action="/register"  method="post" modelAttribute="newUser">
	<div class="form-group">
				<div>
				<form:label path="fullName">Name:</form:label>
				<form:input path="fullName" type="text" class="form-control"/>	
				<form:errors path="fullName" class="error text-danger"/>	
				</div>
				
				<div>
				<form:label path="email">Email:</form:label>
				<form:input path="email" type="email" class="form-control"/>	
				<form:errors path="email" class="error text-danger"/>	
				</div>
				
				<div>
				<form:label path="password">Password:</form:label>
				<form:input path="password" type="password" class="form-control"/>
				<form:errors path="password" class="error text-danger"/>	
				</div>
				
				<div>
				<form:label path="confirm">Confirm Password:</form:label>
				<form:input path="confirm" type="password" class="form-control"/>	
				<form:errors path="confirm" class="error text-danger"/>
				</div>
	</div>
  		<div class ="d-flex justify-content-center mt-4">
  		<button type="submit" class="btn btn-primary">Submit</button>
  		</div>
  		
</form:form>

	<div class = "container col-5">
		<form:form action="/login" method="post" modelAttribute="newLogin">
				<div>
				<form:label path="email">Email:</form:label>
				<form:input path="email" type="email" class="form-control"/>	
				<form:errors path="email" class="error text-danger"/>	
				</div>
				
				<div>
				<form:label path="password">Password:</form:label>
				<form:input path="password" type="password" class="form-control"/>
				<form:errors path="password" class="error text-danger"/>	
				</div>
		<div class ="d-flex justify-content-center mt-4">
  		<button type="submit" class="btn btn-primary">Submit</button>
  		</div>
  		</form:form>
</div>
</div>
</body>
</html>