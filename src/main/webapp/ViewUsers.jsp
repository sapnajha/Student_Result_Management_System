<%@ page
	import="java.sql.*,java.util.*,javax.ws.rs.client.Client,pojo.*,
 javax.ws.rs.client.ClientBuilder,
 javax.ws.rs.client.Entity,
 javax.ws.rs.client.Invocation,
 javax.ws.rs.client.WebTarget,
 javax.ws.rs.core.MediaType,
 javax.ws.rs.core.Response,pojo.*,org.glassfish.jersey.client.ClientConfig"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Users</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
<div class="login-box">
	<h1>List of Users</h1><br><br>
	<form method="get">
		<table border="1" align="center">
			<thead>
				<tr>
					<th>User_ID</th>
					<th>User_Name</th>
					<th>Role</th>
				</tr>
			</thead>
			<%
				ArrayList<User_Auth> list = (ArrayList<User_Auth>) request.getAttribute("user_review");
				for (User_Auth bean : list) {
			%>
			<tr>
				<td><%=bean.getUserid()%></td>
				<td><%=bean.getUsername()%></td>
				<td><%=bean.getType()%></td>
			</tr>
			<%
				}
			%>
		</table><br><br></form>
		<form action="Admin.jsp">
	<center><input type="submit" value="Back"><br><br></center>
	</form>
	<form action="Login.jsp">
	<center><input type="submit" value="Logout"><br><br></center>
	</form>
	</div>
</body>
</html>