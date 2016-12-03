<%@page import="com.inventory.util.DBUtil"%>
<%@page import="java.sql.*" %>
<%@page import="com.inventory.bean.CartItemBean"%>
<%@page import="com.inventory.bean.CartBean"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='invoice.css' />
<title>Invoice</title>
</head>
<body>
	<%
	Float total = (Float)session.getAttribute("total");
	//System.out.println("Cart Date::"+request.getParameter("date")); 
	int billno=0;
	ArrayList<CartItemBean> a = (ArrayList<CartItemBean>)session.getAttribute("res");
	try{
		Connection c = DBUtil.getDBConnection();
		PreparedStatement ps = c.prepareStatement("select nextval('billnoseq')");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			billno = rs.getInt(1);
		}
	}catch(Exception e){
		System.out.println("Error in Invoice");
	}
	%>
	<form action="CartController" method="post">
	<input type="hidden" name="action" value="generateBill">
	<div id="page-wrap">

		<p id="header">INVOICE</p>
		
		<div id="identity">
		
            <p id="address">
            
            </p>

		
		</div>
		
		<div style="clear:both"></div>
		
		<div id="customer">

            <p id="customer-title">Widget Corp. </p>

            <table id="meta">
                <tr>
                    <td class="meta-head">Invoice #</td>
                    <td><p><%=billno %></p></td>
                </tr>
                <tr>

                    <td class="meta-head">Date</td>
                    <%
					   java.util.Date dNow = new java.util.Date( );
					   SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
					   String date = ft.format(dNow);
					%>
                    <td><p id="date"><input type="text" name="date" placeholder="yyyy-mm-dd" value=<%=date %>></p></td>
                </tr>

            </table>
		
		</div>
		<%
		if(a==null || a.size()==0){
		%>
			<h2>Cart is Empty</h2>
		<%
		}
		else{
			
		%>
		<table id="items">
		
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
		  <tr class="item-row">
		      <td class="item-name"><p><%=a.get(i).getPname() %></p></td>
		      <td class="description"><p>Rs.<%=a.get(i).getPrice()%></p></td>
		      <td><p class="cost"><%=a.get(i).getQuantity()%></p></td>
		      <td><p class="qty"><%=a.get(i).getDiscount()%></p></td>
		      <td><span class="price">Rs.<%=a.get(i).getTotal() %></span></td>
		  </tr>
		  <%
			}
		}
		  %>
		  <tr>
		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line">Subtotal</td>
		      <td class="total-value"><div id="subtotal">Rs.<%=total %></div></td>
		  </tr>
		  <tr>

		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line">Total</td>
		      <td class="total-value"><div id="total">Rs.<%=total %></div></td>
		  </tr>
		  <tr>
		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line">Amount Paid</td>

		      <td class="total-value"><p id="paid">Rs.<%=total %></p></td>
		  </tr>
		  <!-- <tr>
		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line balance">Balance Due</td>
		      <td class="total-value balance"><div class="due">$875.00</div></td>
		  </tr> -->
		
		</table>
		<input type="hidden" name="paidamount" value="<%=total %>">
		<input type="hidden" name="amount" value="<%=total %>">
		<input type="hidden" name="billno" value="<%=billno %>">
		<input type="hidden" name="custid" value="<%=session.getAttribute("customerid") %>">
		<input id="terms" type="submit" value="Pay Amount">
		<div id="terms">
		  <h5>Thank You!!!</h5>
		  <p>See You Again</p>
		</div>
	
	</div>
	</form>
</body>
</html>