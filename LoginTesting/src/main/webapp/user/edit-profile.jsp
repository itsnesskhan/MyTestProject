<%@page import="com.java.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home | edit profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
	<style type="text/css">
	.is_invalid{
	color: red;
	}
	</style>
</head>
<body>
<% User user = (User)session.getAttribute("user"); 
	if(user==null){
		response.sendRedirect("login.jsp");
	}
%>
	<div class="container">
		<div class="row">
			<div class="offset-3 col-6 my-5">
			<span class="is_invalid">${error }</span>
				<h1>Update Form</h1>
				<img alt="Not found" class="" style="width:200px;hight:200px;" src='<c:url value="/images/${user.profile }" />'>
				<form action="${pagcontext.request.contextPat}/LoginTesting/UpdateServlet" method = "post" enctype="multipart/form-data">
				<input type="hidden" value="${user.id }" name="userid">
				<div class="form-group">
						<label>Name:</label> <input
							type="text" class="form-control " placeholder="Enter name"
							name="name" value="${user.name }">
					<div id="validationServer05Feedback" class="is_invalid">
					<c:forEach items="${error_map }" var="entry">
						<c:if test="${entry.key == 'name' }">
						<c:out value="${entry.value }"></c:out>
						</c:if>
					</c:forEach>
      				</div>
					</div>
					<div class="form-group">
						<label>Email:</label> <input
							type="email" class="form-control" placeholder="Enter email"
							name="email" value="${user.email }">
							<div id="validationServer05Feedback" class="is_invalid">
					<c:forEach items="${error_map }" var="entry">
						<c:if test="${entry.key == 'email' }">
						<c:out value="${entry.value }"></c:out>
						</c:if>
					</c:forEach>
      				</div>
					</div>
					<input type="hidden" value="${user.password }" name="password">
					<div class="form-group">
						<label>Gender</label> 
						<input type="radio", name="gender" value="M" checked="checked">Male
						<input type="radio", name="gender" value="F">Female
					</div>
					
										
					<div class="form-group">
						<label >Profile:</label> <input
							type="file" class="form-control" 
							name="profile" value="${user.profile }">
					</div>
					<button type="submit" class="btn btn-primary">Update</button>
					<a href="profile.jsp" class="btn btn-warning">Back</a>
				</form>

			</div>
		</div>
	</div>
</body>
</html>