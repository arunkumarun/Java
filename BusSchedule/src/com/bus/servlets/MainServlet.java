package com.bus.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bus.bean.ScheduleBean;
import com.bus.service.Administrator;
import com.bus.util.InvalidInputException;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainServlet() {
        super();
    }
    
    public String addSchedule(HttpServletRequest request) throws ClassNotFoundException, SQLException, InvalidInputException{
    	ScheduleBean sb = new ScheduleBean();
    	Administrator a = new Administrator();
    	String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		String startTime=request.getParameter("startTime");
		String arrivalTime=request.getParameter("arrivalTime");
	
		sb.setSource(source);
		sb.setDestination(destination);
		sb.setStartTime(startTime);
		sb.setArrivalTime(arrivalTime);
		
    	String s = a.addSchedule(sb);
    	
    	return s;
    	
    }
    
    public ArrayList<ScheduleBean> viewSchedule(HttpServletRequest request) throws ClassNotFoundException, SQLException, InvalidInputException{
    	ArrayList<ScheduleBean> arr = new ArrayList<ScheduleBean>();
    	Administrator a = new Administrator();
    	String source=request.getParameter("source");
		String destination=request.getParameter("destination");
    	arr = a.viewSchedule(source, destination);
    	//System.out.println("Mainservlet::"+arr.get(0).getSource()+" "+arr.get(0).getDestination());
    	return arr;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String adRes = new String();
		ArrayList<ScheduleBean> viewRes = null;
    	String operation = request.getParameter("operation");
    	String success = "success.jsp";
    	String error = "errorInserting.html";
    	String display ="displaySchedule.jsp";
    	if(operation.equals("newSchedule")){
    		try {
				adRes = addSchedule(request);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
    		if(adRes.equals("SUCCESS")){
    			response.sendRedirect(success);
    		}
    		else if(adRes.equals("FAIL")){
    			response.sendRedirect(error);
    		}
    	}
    	else if(operation.equals("viewSchedule")){
    		try {
				viewRes = viewSchedule(request);
				//System.out.println(viewRes.get(0).getSource());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
    		if(viewRes.isEmpty()){
    			request.getSession().setAttribute("cond","false");
    			request.getRequestDispatcher(display).forward(request, response);
    		}
    		else{
    			request.setAttribute("cond",viewRes);
    			request.getRequestDispatcher(display).forward(request, response);
    		}
    	}
    	
    }

}
