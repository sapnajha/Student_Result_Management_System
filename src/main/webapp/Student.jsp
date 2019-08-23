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
<title>Result</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<div class="login-box">
	<h1>Your Result</h1>
	<br>
	<br>
	<form action="Change_password.jsp" method="post">
		<table border="1" align="center">
			<thead>
				<tr>

					<th>User_ID</th>
					<th>Maths</th>
					<th>Science</th>
					<th>English</th>
					<th>Total</th>
					<th>Grade</th>
				</tr>
			</thead>
			<%
				ArrayList<Student_Info> list = (ArrayList<Student_Info>)request.getAttribute("view_details");
				for (Student_Info student_info : list) {
			%>
			<tr>
				<td><%=student_info.getUserid()%></td>
				<td><%=student_info.getMaths()%></td>
				<td><%=student_info.getScience()%></td>
				<td><%=student_info.getEnglish()%></td>
				<td><%=student_info.getTotal()%></td>
				<td><%=student_info.getGrade()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			session = request.getSession();
			int userid = (int)session.getAttribute("userid");
			session.setAttribute("userid", userid);
		%>
		<center><br> <br> <input type="submit" value="Change Password"><br><br></center>
		</form>
		<form action="Login.jsp">
	<center><input type="submit" value="Logout"><br><br></center>
	</form>
	</div>
</body>
</html>