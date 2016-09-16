<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Schedule</title>
</head>
<body>
	<h1>Enter Schedule Details</h1><br><br>
	<form action="MainServlet" method="post">
	
	Source	   	 :<input type="text" name="source"><br>
	Destination	 :<input type="text" name="destination"><br>
	Start time   :<input type="text" name="startTime"><br>
	Arrival time :<input type="text" name="arrivalTime"><br>
	
	<input type="hidden" name="operation" value="newSchedule">
	<input type="submit" value="Create Schedule">
	</form>
</body>
</html>