<%@page import="com.inventory.bean.CartItemBean"%>
<%@page import="com.inventory.bean.CartBean"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<jsp:include page="customer.jsp"></jsp:include>
	<p><strong>Shopping Cart</strong></p><br>
	<form action="invoice.jsp">
		
		<%
			ArrayList<CartItemBean> a = (ArrayList<CartItemBean>)session.getAttribute("res");
			CartBean cb = new CartBean();
			Float total = (Float)session.getAttribute("total");
			//System.out.println("Cart is::"+cart);
			if(a==null || a.size()==0){
		%>
			<h2>Cart is Empty</h2>
		<%
			}else{
		%>
			<table>
				<tr>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Discount</th>
					<th>Total</th>
				</tr>
		<%
			for(int i=0;i<a.size();i++){
		%>
				<tr>
					<td><%=a.get(i).getPname() %></td>
					<td><%=a.get(i).getPrice()%></td>
					<td><%=a.get(i).getQuantity()%></td>
					<td><%=a.get(i).getDiscount()%></td>
					<td><%=a.get(i).getTotal() %></td>
				</tr>
			
			
		<%				
			}
			}
		%>
		</table>
		<br>
		<h3><%=total%></h3>
		
		<input type="submit" value="Generate Bill">
	</form>	
</body>
</html>