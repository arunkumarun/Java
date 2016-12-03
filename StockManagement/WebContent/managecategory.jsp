<%@page import="com.inventory.util.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="menu.css">
</head>
<body>
	<%-- <jsp:include page="menu.jsp"></jsp:include> --%>
	<br>
	<h1>Available Category</h1><br>
	<%
		try {
			Connection c = DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from category");
	%>
		<table>
			<tr>
				<th>CategoryID</th>
				<th>Category Name</th>
				<th>Action</th>
			</tr>
	<%
			while(rs.next())
			{	
	%>
			<tr>
				<td><%=rs.getInt(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td>
					<form action="Category" method="post">
						<input type="hidden" name="operation" value="deleteCategory">
						<input type="hidden" name="cid" value="<%=rs.getInt(1)%>">
						<input type="submit" value="Delete">
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