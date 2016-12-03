package com.inventory.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventory.bean.CustomerBean;
import com.inventory.dao.CustomerDAO;
import com.inventory.service.CustomerAdmin;

@WebServlet("/Customer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Customer() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String op = request.getParameter("operation");
    	String uname,password;
    	HttpSession session=request.getSession(true);
    	
    	CustomerDAO cd = new CustomerDAO();
    	CustomerAdmin ca = new CustomerAdmin();
    	CustomerBean cb = new CustomerBean();
    	uname = request.getParameter("uname");
    	password = request.getParameter("pass");
    	
    	System.out.println(op);
    	if(op.equals("signin")){
    		
    		if(!uname.isEmpty() || !password.isEmpty()){
    			int roleid = cd.getRoleID(uname, password);
    			//System.out.println(roleid);
    			if(roleid==1){
    				boolean res = checkAdmin(uname,password);
    				//System.out.println(res);
    				if(res){
    					  
    			        session.setAttribute("name",uname); 
    			        response.sendRedirect("dashboard.jsp");
    				}
    				else{
    					response.sendRedirect("signin.jsp");
    				}
    			}
    			else if(roleid==2){
    				String res = cd.checkUser(uname,password);
    				if(res.equals("SUCCESS")){
    					session.setAttribute("name",uname);
    					session.setAttribute("custid", cb.getCustid());
    					System.out.println("CustId Customer servlet:"+cb.getCustid());
    					Cookie c = new Cookie("custid",String.valueOf(cb.getCustid()));
    					response.addCookie(c);
    			        request.setAttribute("success",res);
    					request.getRequestDispatcher("/showcategory.jsp").forward(request, response);
    	    		}
    	    		else{
    	    			request.setAttribute("err",res);
    	    			RequestDispatcher rd = request.getRequestDispatcher("/signin.jsp");
    	    			rd.forward(request,response);
    	    		}
    				
    			}
    		}	
    	}
    	if(op.equals("changePassword")){
    		String res = new String();
    		String newPass = request.getParameter("newPass");
    		String rePass = request.getParameter("rePass");
    		
    		res = ca.checkPass(newPass,rePass);
    		
    		if(res.equals("SUCCESS")){
    			request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/changepass.jsp").forward(request, response);
    		}
    		else if(res.equals("FAILED")){
    			response.getWriter().append(res);
    		}
    		else{
    			request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/changepass.jsp");
    			rd.forward(request,response);
    		}
    	}
    	if(op.equals("signup")){
    		String res = new String();
    		String fname = request.getParameter("fname");
    		String lname = request.getParameter("lname");
    		String email = request.getParameter("email");
    		String pass = request.getParameter("pass");
    		String repass = request.getParameter("repass");
    		
    		res = ca.addUser(fname,lname,email,pass,repass);
    		System.out.println(res);
    		if(res.equals("SUCCESS")){
    			request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/index.html").forward(request, response);
    		}
    		else{
    			request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
    			rd.forward(request,response);
    		}
    	}
    	//doGet(request, response);
    	
	}

	public boolean checkAdmin(String uname, String password) {
		CustomerAdmin ca = new CustomerAdmin();
		
		boolean res = ca.checkAdmin(uname,password);
		
		return res;
	}

}
