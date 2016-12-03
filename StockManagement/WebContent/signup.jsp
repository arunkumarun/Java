<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp</title>
</head>
<body>
	<h1>SignUP</h1><br>
	<form action="Customer" method="post">
		<input type="text" placeholder="First Name" name="fname"><br>
		<input type="text" placeholder="Last Name" name="lname"><br>
		<input type="email" placeholder="Email" name="email"><br>
		<input type="password" placeholder="New Password" name="pass"><br>
		<input type="password" placeholder="Retype Password" name="repass"><br>
		<input type="hidden" name="operation" value="signup">
		<input type="submit" value="Create"><br><br>
	</form>
	
</body>
</html>