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
	<jsp:include page="showcategory.jsp"></jsp:include>
	<a href="cart.jsp">Show Cart</a><br>
	<%
		int catid = Integer.valueOf((String) request.getAttribute("cid"));
		try {
			Connection c = DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from product where catid="+catid);
			int size= 0;
			if (rs != null)   
			{  
				while (rs.next()) {
				    size++;
				}  
			}
			rs = s.executeQuery("select * from product where catid="+catid);
			System.out.println(size);
			if(size !=0){
	%>			
				<table>
					<tr>
						<th>Product Name</th>
						<th>Price</th>
						<th>InStock</th>
						<th>Discount</th>
						<th>Quantity</th>
					</tr>
	<%
			while(rs.next())
			{	
				if(rs.getInt(7)!=0){	
	%>			
				<tr>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getFloat(5)%></td>
				<td><%=rs.getInt(7)%></td>
				<td><%=rs.getFloat(6)%></td>
				<td>
				
						<form action="CartController" method="post">
						<input type="number" placeholder="Enter Quantity" name="quantity" value="0">
				<%
					String quantity = request.getParameter("quantity");
					System.out.println(request.getParameter("quantity"));
				%>
						<input type="hidden" name="pid" value="<%=rs.getString(1)%>">
						<input type="hidden" name="pname" value="<%=rs.getString(2)%>">
						<input type="hidden" name="pdesc" value="<%=rs.getString(3)%>">
						<input type="hidden" name="cid" value="<%=rs.getString(4)%>">
						<input type="hidden" name="price" value="<%=rs.getString(5)%>">
						<input type="hidden" name="inStock" value="<%=rs.getString(7)%>">
						<input type="hidden" name="discount" value="<%=rs.getString(6)%>">
						<%-- <input type="hidden" name="quantity" value="<%=request.getParameter("quantity")%>"> --%>
						<input type="hidden" name="action" value="add">
						<input type="hidden" name="custid" value="<%=session.getAttribute("custid") %>">
						<input type="submit" value="Add to Cart">
						</form>	
				</td>
				</tr>
				
	<%
				}
			}
	%>
		</table>
		<br>
	<%
			}
			else{
	%>
			<h3>No Products</h3>
	<%			
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	%>
</body>
</html>