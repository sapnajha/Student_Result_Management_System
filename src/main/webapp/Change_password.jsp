<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<div class="login-box">
		<h1>Change Password</h1>
		<form action="ChangePasswordServlet" method="post"
			onsubmit="return msg()">
			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i> <b>Enter Old
					Password:<input type="password" name="old_password"
					id="old_password"><br>
				<br>
			</div>
			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i> <b>Enter New
					Password:<input type="password" name="new_password"
					id="new_password"><br>
				<br>
			</div>
			<div>
				<%
					out.print(
							"->password should be of minimum length 8 password should contain alphabets, numbers and atleast one special character the first character of password should be an alphabet");
				%>
			</div><br><br>
			<center>
				<input type="submit" value="Change Password">
			</center>
			<br>
			<br>
		</form>
		<div>
			<%
				if (session.getAttribute("msg") != null) {
					out.print(session.getAttribute("msg"));
				}
			%>
		</div>
		<form action="Login.jsp">
			<center>
				<input type="submit" value="Logout"><br>
				<br>
			</center>
			<div>
				<%
					session.removeAttribute("msg");
				%>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	function msg() {
		if (document.getElementById("old_password").value.length == 0) {
			alert("Password cant be Empty");
			return false;
		}
		if (document.getElementById("new_password").value.length == 0) {
			alert("Password cant be Empty");
			return false;
		}
	}
</script>