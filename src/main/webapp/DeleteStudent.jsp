<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Student</title>
<link rel="stylesheet" href="Login.css">
</head>
<body style="text-align:center" bgcolor="powderblue">
	<div class="login-box">
	<h1>Delete Student User</h1>
	<form action="AdminServlet" method="post" onsubmit="return msg()">
	<input type="hidden" name="varname" value="delete_student"/>
	<div class="textbox">
	<b>Student_Id:<input type="text" name="userid" id="userid"><br><br>
	</div>
	<div class="textbox">
	<b>Student Name:<input type="text" name="username" id="name" ><br><br>
	</div>
	<input type="submit" value="Delete"><br><br></form>
	<div><%if(session.getAttribute("msg")!=null)
	{
		out.print(session.getAttribute("msg"));
	}%></div>
	<form action="Admin.jsp">
	<center><input type="submit" value="Back"><br><br></center>
	<div><%session.removeAttribute("msg"); %></div>
	</form>
	</div>
	</body>
</html>
<script type="text/javascript">
	function msg() {
		if (document.getElementById("userid").value.length == 0) {
			alert("userid can't be Empty.");
			return false;
		}
		if (document.getElementById("name").value.length == 0) {
			alert("username can't be Empty.");
			return false;
		}
	}
</script>