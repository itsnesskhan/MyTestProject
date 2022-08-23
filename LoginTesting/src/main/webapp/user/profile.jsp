<%@page import="com.java.entity.User"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
<% User user = (User)session.getAttribute("user"); 
	if(user==null){
		response.sendRedirect("login.jsp");
	}
%>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 my-5">
				${msg }
				<div class="card">
					<img class="card-img-top" style="width: 200px; hight: 200px;"
						src="<c:url value='/images/${user.profile }'/>">
					<div class="card-body">
						<h1>Welcome, ${user.name}</h1>
						<h2>Email: ${user.email }</h2>
						<h2>Pasword:  ${user.password }</h2>
						<h2>Gender: ${user.gender=='M'?"Male":"Female" }</h2>
						<!-- <h3>${cookie.email.value }</h3>-->
					</div>

				</div>
				<br> <br> <a class="btn btn-info"
					href="${pageContext.request.contextPath }/LogoutServlet">Logout</a>
				<a class="btn btn-warning"
					href="${pageContext.request.contextPath }/user/edit-profile.jsp">Update</a>
			</div>
		</div>
	</div>

</body>
</html>