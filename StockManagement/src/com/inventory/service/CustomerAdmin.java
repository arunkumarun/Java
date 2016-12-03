package com.inventory.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.inventory.bean.CartItemBean;
import com.inventory.dao.CustomerDAO;

public class CustomerAdmin {
	CustomerDAO cd = new CustomerDAO();
	
	public boolean checkAdmin(String uname,String password){
		boolean res = false;
		res = cd.checkAdmin(uname,password);
		//System.out.println("CustAdmin "+res);
		return res;
	}

	public String checkPass(String newPass, String rePass) {
		String res = new String();
		
		if(newPass.isEmpty() || rePass.isEmpty()){
			res = "Field cannot be empty";
			return res;
		}
		else if(newPass.length()<5){
			res="Password is short";
			return res;
		}
		else if(newPass.equals(rePass)){
			res = cd.checkPass(newPass);
		}
		return res;
	}
	
	//Category
	public String addCategory(String cname) {
		String res = new String();
		
		if(cname.isEmpty()){
			res = "Field cannot be empty";
			return res;
		}
		else{
			res = cd.addCategory(cname);
		}
		return res;
	}

	public String addProduct(String pname, int catid, float price, int instock, String pdesc, float discount) {
		String res = new String();
		
		if(pname.isEmpty()){
			res = "Product Name cannot be empty";
			return res;
		}
		if(price == 0.0){
			res = "Price cannot be zero";
			return res;
		}
		
		res = cd.addProduct(pname,catid,price,instock,pdesc,discount);
		
		return res;
	}
	//SignUp
	public String addUser(String fname, String lname, String email, String pass, String repass) {
		String res = new String();
		if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty()){
			res = "Field cannot be empty";
			return res;
		}
		if(email.length()<8){
			res = "Invalid email";
			return res;
		}
		if(pass.length()<5){
			res = "Password is short";
			return res;
		}
		String check = cd.checkUser(email, pass);
		if(check.equals("SUCCESS")){
			res = "User Already Exist";
			return res;
		}
		if(pass.equals(repass)){
			res=cd.addUser(fname,lname,email,pass,repass);
		}
		else{
			res = "Passwords not match";
		}
		
		return res;
	}

	public String generateBill(LocalDate date, ArrayList<CartItemBean> bill, int custid, int billno, String paytype, Float amount, Float balance, Float paidamount) {
		String res = new String();
		
		res = cd.generateBill(date,bill,custid,billno,paytype,amount,balance,paidamount);
		
		return res;
	}
	
}
