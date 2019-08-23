<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Admin</title>
<link rel="stylesheet" href="Login.css">
</head>
<body style="text-align:center" bgcolor="powderblue">
	<div class="login-box">
	<h1>Add Admin User</h1>
	<form action="AdminServlet" method="post" onsubmit="return msg()">
	<input type="hidden" name="varname" value="add_admin"/>
	<div class="textbox">
	<b>user_id:<input type="number" name="userid" id="userid"><br><br>
	</div>
	<div class="textbox">
	<b>username:<input type="text" name="username" id="name"><br><br>
	</div>
	<input type="submit" value="submit"><br><br></form>
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