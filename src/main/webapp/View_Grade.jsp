<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Grade</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<div class="login-box">
		<h1>Student Results Based On Grade</h1>
		<form action="AdminServlet" method="POST">
			<input type="hidden" name="varname" value="view_grade" /> <b>
				<div class="textbox">
					Select Grade: <select name="value">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
				 </select><br> <br></div> 
				<center><input type="submit" value="View"><br></center>
			<br>
	</form>
	<form action="Admin.jsp">
	<center><input type="submit" value="Back"><br><br></center>
	</form>
	</div>
</body>
</html>