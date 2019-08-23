<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" href="Login.css">
</head>
<style>
a{
text-decoration:none;
color: white;
}
</style>
<body>
	<div class="login-box">
		<h1>Welcome</h1>

		<a href="AddStudent.jsp" color="red"><b>Add Student</a><br> <br>
		<a href="AddAdmin.jsp">Add Admin</a><br> <br> 
		<a href="DeleteStudent.jsp">Delete Student</a><br><br>
		<a href="DeleteAdmin.jsp">Delete Admin</a><br> <br> 
		<a href="AdminServlet?varname=view_user">View Users</a><br><br>
		<a href="View_Grade.jsp">Grade Based Student List</a><br> <br>
		<a href="Change_password.jsp">Change Password</a><br> <br>
		<%if(session.getAttribute("msg")!=null)
	{
		out.print(session.getAttribute("msg"));
	}%>
	
	<%session.removeAttribute("msg"); %>
	</div>
</body>
</html>