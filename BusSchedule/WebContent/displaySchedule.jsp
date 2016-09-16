<%@page import="com.bus.bean.ScheduleBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Schedule</title>
</head>
<body>
	<%
		ArrayList<ScheduleBean> res = (ArrayList<ScheduleBean>)request.getAttribute("cond");
		//System.out.println(res.get(0).getDestination());
		
		if(res == null)
		{
	%>
		<h1>No matching schedules exists! Please try again!</h1> 
	<%
		}
		else
		{
	%>
		<table>
		<tr>
			<th>ScheduleId</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Start Time</th>
			<th>Arrival Time</th>
		</tr>
	<%
			for(int i = 0;i<res.size(); i+=1)
			{
	%>	
				<tr>
				<td><%=res.get(i).getScheduleId() %> </td>
				<td><%=res.get(i).getSource() %> </td>
				<td><%=res.get(i).getDestination() %> </td>
				<td><%=res.get(i).getStartTime() %> </td>
				<td><%=res.get(i).getArrivalTime() %> </td>
				</tr>
		<%
			}
		}
	%>
		</table>
	

</body>
</html>