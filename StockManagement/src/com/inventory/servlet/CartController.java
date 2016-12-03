package com.inventory.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventory.bean.CartBean;
import com.inventory.bean.CartItemBean;
import com.inventory.service.CustomerAdmin;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		
		
		if(action.equals("add")){
			
			HttpSession session = request.getSession(false);
			ArrayList<CartItemBean> res = addToCart(request);
			float total = 0;
			ArrayList<CartItemBean> alCartItems = res;
			try{
				for(int counter=0;counter<alCartItems.size();counter++) {
					CartItemBean cartItem = (CartItemBean) alCartItems.get(counter);
					total+=cartItem.getTotal();
				}
			}catch(Exception e){
				System.out.println("Please Enter Quantity");
			}
			if(res == null){
				request.setAttribute("err","Quantity Cannot be zero");
				request.setAttribute("cid",request.getParameter("cid"));
				request.getRequestDispatcher("/showproduct.jsp").forward(request, response);
			}
			else{
				session.setAttribute("res",res); 
				session.setAttribute("total",total);
				//session.setAttribute("custid",cb.getCustid());
				System.out.println("Cart add Cust iD:"+request.getParameter("custid"));
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
		}
		
		
		else if(action.equals("update")){
			updateCart(request);
		}
		else if(action.equals("delete")){
			deleteCart(request);
		}
		
		if(action.equals("generateBill")){
			HttpSession session = request.getSession(true);
			@SuppressWarnings("unchecked")
			ArrayList<CartItemBean> bill = (ArrayList<CartItemBean>) session.getAttribute("res");
			LocalDate date = null;
			String res = new String();
			request.setAttribute("custid", request.getParameter("custid"));
			try{
				date = LocalDate.parse(request.getParameter("date"));
				System.out.println("Date in Invoice::"+date);
				//res = generateBill(date,bill,request);
			}catch (Exception e) {
				System.out.println("Enter the date");
			}
			
			res = generateBill(date,bill,request);
			if(res.equals("SUCCESS")){

				request.setAttribute("err", res);
				request.setAttribute("cid",request.getParameter("cid"));
				request.getRequestDispatcher("/showproduct.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err", res);
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			
		}
		//doGet(request, response);
	}


	
	
	
	
	private String generateBill(LocalDate date, ArrayList<CartItemBean> bill, HttpServletRequest request) {
		//HttpSession session = request.getSession(false);
		CustomerAdmin ca = new CustomerAdmin();
		//CustomerDAO cd = new CustomerDAO();
		int billno = Integer.parseInt(request.getParameter("billno"));
		String paytype = "CASH";
		int custid = Integer.parseInt((String)request.getAttribute("custid"));
		System.out.println("Cart generate bill Cust iD:"+custid);
		Float amount = Float.parseFloat(request.getParameter("amount"));
		Float paidamount = Float.parseFloat(request.getParameter("paidamount"));
		Float balance = amount - paidamount;
		
		
		String res=ca.generateBill(date,bill,custid,billno,paytype,amount,balance,paidamount);
		
		return res;
	}

	private void deleteCart(HttpServletRequest request) {
		
	}

	private void updateCart(HttpServletRequest request) {
		
	}

	private ArrayList<CartItemBean> addToCart(HttpServletRequest request) {
		ArrayList<CartItemBean> res;
		
		HttpSession session = request.getSession();
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pname = request.getParameter("pname");
		String pdesc = request.getParameter("pdesc");
		float price = Float.parseFloat(request.getParameter("price"));
		float discount = Float.parseFloat(request.getParameter("discount"));
		int inStock = Integer.parseInt(request.getParameter("inStock"));
		int quantity = 0;
		if(request.getParameter("quantity").equals("0")){
			return null;
		}
		else{
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		
		if(quantity>inStock){
			return null;
		}
		
		CartBean cartBean = null;
		Object objCartBean = session.getAttribute("cart");
		if(objCartBean!=null){
			cartBean = (CartBean) objCartBean ;
		}else{
			cartBean = new CartBean();
			session.setAttribute("cart", cartBean);
		}		   
		res = cartBean.addCartItem(pid,pname,pdesc,price,discount,inStock,quantity);

		
		return res;
	}

}
