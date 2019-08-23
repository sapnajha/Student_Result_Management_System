<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<div class="login-box">
		<h1>Login User</h1>
		<form action="LoginServlet" method="post" onsubmit="return msg()">
			<div class="textbox">
				<i class="fa fa-user" aria-hidden="true"></i> <b><input
					type="text" name="userid" placeholder="Userid" id="userid"><br>
					<br>
			</div>
			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i> <b><input
					type="password" name="password" placeholder="Password"
					id="password"><br> <br>
			</div>
			<center><input type="submit" name="action" value="login"></center>
			<div><%if(session.getAttribute("msg")!=null)
	{
		out.print(session.getAttribute("msg"));
	}%></div>
	<%session.removeAttribute("msg"); %>
	
		</form><br><br>
	</div>
</body>
</html>
<script type="text/javascript">
	function msg() {
		if (document.getElementById("userid").value.length == 0) {
			alert("userid can't be Empty.");
			return false;
		}
		if (document.getElementById("password").value.length == 0) {
			alert("password can't be Empty.");
			return false;
		}
	}
</script>