<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add student</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<div class="login-box">
	<h1>Add Student User</h1>
	<form action="AdminServlet" method="post" onsubmit="return msg()">
	<input type="hidden" name="varname" value="add_student"/>
	<div class="textbox">
	<b>Student Id:<input type="text" name="userid" id="userid"><br><br>
	</div>
	<div class="textbox">
	<b>Student Name:<input type="text" name="username" id="username"><br><br>
	</div>
	<div class="textbox">
	<b>Maths Marks:<input type="text" name="maths" id="marks1"><br><br>
	</div>
	<div class="textbox">
	<b>Science Marks:<input type="text" name="science" id="marks2"><br><br>
	</div>
	<div class="textbox">
	<b>English Marks:<input type="text" name="english" id="marks3"><br><br>
	</div>
	<center><input type="submit" value="ADD"><br><br></center></form>
	<div><%if(session.getAttribute("msg")!=null)
	{
		out.print(session.getAttribute("msg"));
	}%>
	</div>
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
		if (document.getElementById("username").value.length == 0) {
			alert("username can't be Empty.");
			return false;
		}
		if (document.getElementById("marks1").value.length == 0) {
			alert("Marks can't be Empty.");
			return false;
		}
		if (document.getElementById("marks2").value.length == 0) {
			alert("Marks can't be Empty.");
			return false;
		}
		if (document.getElementById("marks3").value.length == 0) {
			alert("Marks can't be Empty.");
			return false;
		}
	}
</script>



