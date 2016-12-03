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
	<jsp:include page="customer.jsp"></jsp:include>
	<form action="Category" method="post">
		<select name="category">
		<%
		Cookie ck[]=request.getCookies();  
		for(int i=0;i<ck.length;i++){  
			String custid = ck[i].getValue();
			System.out.println("Custid in Showcategory:"+custid+"Name:"+ck[i].getName());
		}
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
		</select>
		<input type="hidden" name="operation" value="showCategory">
		<input type="hidden" name="custid" value="<%=session.getAttribute("custid") %>">
		<input type="submit" value="Show Products">	
	</form>
</body>
</html>