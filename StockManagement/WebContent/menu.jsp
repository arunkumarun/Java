<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="menu.css">
</head>
<body>
	
	<%
		if(session != null){
			if(session.getAttribute("name")!=null){
				
	%>
		<h1>Admin Dashboard</h1>
		
		<ul>
  			<li><a href="dashboard.jsp" class="tablinks" id="defaultOpen">Dashboard</a></li>
  			<li><a href="category.jsp" class="tablinks">Category</a></li>
  			<li><a href="product.jsp" class="tablinks">Products</a></li>
  			<li><a href="order.jsp" class="tablinks">Orders</a></li>
  			<li class="dropdown">
  		  		<a href="javascript:void(0)" class="dropbtn" onclick="myFunction()">Admin &#9660;</a>
    			<div class="dropdown-content" id="myDropdown">
      				<a href="changepass.jsp" class="tablinks" onclick="myFunction()" id="cpass">Change Password</a>
   	 				<a href="Logout">SignOut</a>
    			</div>
  			</li>
		</ul>

		<%-- <div id="Dashboard" class="tabcontent">
  			<h5>DashBoard</h5>
  			<p>Welcome to Dashboard</p>
		</div>
		
		<div id="Category" class="tabcontent">
		  <jsp:include page="category.jsp"></jsp:include>
		</div>
		
		<div id="Products" class="tabcontent">
		  <jsp:include page="product.jsp"></jsp:include>
		</div>

		<div id="Orders" class="tabcontent">
		  <h5>Orders</h5>
		  <p>All Orders</p>
		</div>
		
		<div id="ChangePassword" class="tabcontent">
		  <form action="Customer" method="post">
		  	New Password:<input type="password" name="newPass"><br>
		  	Retype Password:<input type="password" name="rePass"><br>
		  	<input type="hidden" name="operation" value="changePassword">
		  	<input type="submit" value="Change Password"><br>
		  </form>
		</div>
 --%>		
	<%
			}
		}
	%>

	
	
		
		<script>
			
			/* document.getElementById("defaultOpen").click();
			 */
			/* When the user clicks on the button, 
			toggle between hiding and showing the dropdown content */
			function myFunction() {
			    document.getElementById("myDropdown").classList.toggle("show");
			}

			// Close the dropdown if the user clicks outside of it
			window.onclick = function(e) {
			  if (!e.target.matches('.dropbtn')) {

			    var dropdowns = document.getElementsByClassName("dropdown-content");
			    for (var d = 0; d < dropdowns.length; d++) {
			      var openDropdown = dropdowns[d];
			      if (openDropdown.classList.contains('show')) {
			        openDropdown.classList.remove('show');
			      }
			    }
			  }
			}
			
		</script>
	
	
</body>
</html>