<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock Management</title>
</head>
<body>
	<h1>SignIn</h1>
	<form name="signin" action="Customer" method="post">
		<input type="text" name="uname" placeholder="Enter Email"><br>
		<input type="password" name="pass" placeholder="Enter Password"><br>
		<input type="hidden" name="operation" value="signin">
		<input type="submit" value="SignIn"><br><br>
	</form>
</body>
</html>