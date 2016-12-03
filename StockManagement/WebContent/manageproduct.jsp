<%@page import="com.inventory.util.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<br>
	<h1>Available Products</h1><br>
	<%
		try {
			Connection c = DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from product,category where product.catid=category.catid");
	%>
		<table>
			<tr>
				<th>ProductId</th>
				<th>Product Name</th>
				<th>Category</th>
				<th>Price</th>
				<th>InStock</th>
				<th>Discount</th>
				<th>Action</th>
			</tr>
	<%
			while(rs.next())
			{	
	%>
			<tr>
				<td><%=rs.getInt(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(9)%></td>
				<td><%=rs.getFloat(5)%></td>
				<td><%=rs.getInt(7)%></td>
				<td><%=rs.getFloat(6)%></td>
				<td>
					<form action="Category" method="post">
						<input type="submit" name="operation" value="Delete">
						<input type="hidden" name="pid" value="<%=rs.getInt(1)%>">
					</form>
				</td>
			</tr>
	<%
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	%>
		</table>
		<br>
	

</body>
</html>