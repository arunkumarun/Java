<%@page import="com.inventory.util.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="menu.css">
<title>Products</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h1>Add Product</h1>
	<form action="Category" method="post">
		Product Name:<input type="text" name="pname"><br>
		Product Description:<input type="text" name="pdesc">
		Category:<select name="catid">
		<%
		try {
			Connection c = DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from category");
			while(rs.next())
			{	
		%>
				<option value=<%=rs.getInt(1)%>> <%=rs.getString(2)%></option>
		<%
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		%>
		</select><br>
		Price:<input type="text" name="price"><br>
		Stock Quantity:<input type="text" name="instock"><br>
		Discount:<input type="text" name="discount">
		<input type="hidden" name="operation" value="addProduct"><br>
		<input type="submit" value="Add Product">
	</form>
	
	<jsp:include page="manageproduct.jsp"></jsp:include>
	
</body>
</html>