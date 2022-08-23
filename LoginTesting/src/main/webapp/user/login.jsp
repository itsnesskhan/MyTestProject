<%@page import="com.java.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home | Session Tracking</title>
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
	if(user!=null){
		response.sendRedirect("user/profile.jsp");
	}
%>
	<div class="container">
		<div class="row">
			<div class="offset-3 col-6 my-5">
			<span class="is_invalid">${error }</span>
				<h1>Login Here</h1>
				<form action="${pagcontext.request.contextPath}/LoginTesting/LoginServlet" method = "post">
					<div class="form-group">
						<label>Email:</label> <input
							type="email" class="form-control" placeholder="Enter email"
							name="email" value="${login.email }">
							<div id="validationServer05Feedback" class="is_invalid">
					<c:forEach items="${error_map }" var="entry">
						<c:if test="${entry.key == 'email' }">
						<c:out value="${entry.value }"></c:out>
						</c:if>
					</c:forEach>
      				</div>
					</div>
					
					<div class="form-group">
						<label >Password:</label> <input
							type="password" class="form-control" placeholder="Password"
							name="password" value="${login.password }">
							<div id="validationServer05Feedback" class="is_invalid">
					<c:forEach items="${error_map }" var="entry">
						<c:if test="${entry.key == 'password' }">
						<c:out value="${entry.value }"></c:out>
						</c:if>
					</c:forEach>
      				</div>
					</div>
										
					
					<button type="submit" class="btn btn-primary my-5">Login</button>
				<a href="${pageContext.request.contextPath }/user/index.jsp" class="btn btn-warning">Sign Up</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>