<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="menu.css">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h1>Add Category</h1>
	<form action="Category" method="post">
		Category Name:<input type="text" name="cname">
		<input type="hidden" name="operation" value="add">
		<input type="submit" value="Add Category">
	</form>	
	
	<jsp:include page="managecategory.jsp"></jsp:include>
	
</body>
</html>