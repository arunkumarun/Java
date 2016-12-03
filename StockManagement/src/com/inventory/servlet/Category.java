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

import com.inventory.dao.CustomerDAO;
import com.inventory.service.CustomerAdmin;

@WebServlet("/Category")
public class Category extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    CustomerAdmin ca = new CustomerAdmin();
    CustomerDAO cd = new CustomerDAO();
	
	public Category() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println("Category add Cust iD:"+request.getParameter("custid"));
		session.setAttribute("custid", request.getParameter("custid"));
		String op = request.getParameter("operation");
		String cname = new String();
		String res = new String();
		String pname = new String();
		String pdesc = new String();
		int catid,instock;
		float price,discount;
		
		
		if(op.equals("add")){
			cname = request.getParameter("cname");
			res = ca.addCategory(cname);
			if(res.equals("SUCCESS")){
				request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/category.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
    			rd.forward(request,response);
			}
		}
		
		if(op.equals("deleteCategory")){
			int cid = Integer.parseInt(request.getParameter("cid"));
			//System.out.println(cid);
			res = cd.deleteCategory(cid);
			if(res.equals("SUCCESS")){
				request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/category.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
    			rd.forward(request,response);
			}
		
		}
		
		if(op.equals("showCategory")){
			String cid = request.getParameter("category");
			System.out.println(cid);
			request.setAttribute("cid",cid);
			request.getRequestDispatcher("/showproduct.jsp").forward(request, response);
		}

		//Products
		if(op.equals("addProduct")){
			//System.out.println(request.getParameter("cid"));
			pname = request.getParameter("pname");
			catid = Integer.parseInt(request.getParameter("catid"));
			System.out.println(catid);
			price = Float.parseFloat(request.getParameter("price"));
			instock = Integer.parseInt(request.getParameter("instock"));
			pdesc = request.getParameter("pdesc");
			discount = Float.parseFloat(request.getParameter("discount"));
			
			res=ca.addProduct(pname,catid,price,instock,pdesc,discount);
			//System.out.println("Product res::"+res);
			if(res.equals("SUCCESS")){
				request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/product.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
    			rd.forward(request,response);
			}
		}
		
		if(op.equals("Delete")){
			int pid = Integer.parseInt(request.getParameter("pid"));
			System.out.println(pid);
			res = cd.deleteProduct(pid);
			if(res.equals("SUCCESS")){
				request.getSession().setAttribute("success",res);
				request.getRequestDispatcher("/product.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err",res);
    			RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
    			rd.forward(request,response);
			}
		
		}
		
		//doGet(request, response);
	}

}
