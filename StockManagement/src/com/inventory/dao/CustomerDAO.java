package com.inventory.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.inventory.bean.CartItemBean;
import com.inventory.bean.CustomerBean;
import com.inventory.util.DBUtil;

public class CustomerDAO {
	CustomerBean cb = new CustomerBean();
	public int getRoleID(String uname,String password){
		int roleid=0;
		try {
			Connection c = DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from customer where email='"+uname+"' and password='"+password+"'");
			while(rs.next()){
				roleid = rs.getInt("roleid");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return roleid;
	}

	public boolean checkAdmin(String uname, String password) {
		boolean res = false;
		
		try {
			Connection c =DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from customer where email='"+uname+"' and password='"+password+"'");
			if(rs.next()){
				res = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public String checkPass(String newPass) {
		String res =new String();
		
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("update customer set password='"+newPass+"' where roleid = 1");
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	public String checkUser(String uname, String password) {
		String res =new String();
		try {
			Connection c =DBUtil.getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from customer where email='"+uname+"' and password='"+password+"'");
			if(rs.next()){
				res = "SUCCESS";
				cb.setCustid(rs.getInt(1));
				cb.setEmail(rs.getString(4));
				System.out.println("Customer Id:"+cb.getCustid());
				System.out.println("Customer email:"+cb.getEmail());
			}
			else{
				res = "FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int getCustID(){
		return cb.getCustid();
	}
	
	public String addCategory(String cname) {
		String res = new String();
		
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("insert into category values(nextval('catidseq'),?)");
			ps.setString(1, cname);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	//Products
	public String addProduct(String pname, int catid, float price, int instock, String pdesc, float discount) {
		String res = new String();
		
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("insert into product values(nextval('pidseq'),?,?,?,?,?,?)");
			ps.setString(1, pname);
			ps.setString(2, pdesc);
			ps.setInt(3, catid);
			ps.setFloat(4, price);
			ps.setFloat(5, discount);
			ps.setInt(6, instock);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String deleteProduct(int pid) {
		String res = new String();
		
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("delete from product where productid="+pid);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String deleteCategory(int cid) {
		String res = new String();
		
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("delete from category where catid="+cid);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String addUser(String fname, String lname, String email, String pass, String repass) {
		String res = new String();
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("insert into customer values(nextval('custidseq'),?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setInt(5, 2);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public String generateBill(LocalDate date, ArrayList<CartItemBean> bill, int custid, int billno, String paytype, Float amount, Float balance, Float paidamount) {
		String res = new String();
		String orderRes = new String();
		String detailRes = new String();
		Date date1 = java.sql.Date.valueOf(date);
		System.out.println("date::"+date);
		try {
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("insert into payment values(?,?,?,?,?,?)");
			ps.setInt(1, billno);
			ps.setString(2, paytype);
			ps.setFloat(3, amount);
			ps.setDate(4, date1);
			ps.setFloat(5, balance);
			ps.setFloat(6, paidamount);
			int r = ps.executeUpdate();
			if(r>0){
				res = "SUCCESS";
			}
			else{
				res="FAILED";
			}
			if(res.equals("SUCCESS")){
				ps=c.prepareStatement("insert into orders values(nextval('oidseq'),?,?)");
				ps.setInt(1, custid);
				ps.setDate(2, date1);
				r = ps.executeUpdate();
				if(r>0){
					orderRes = "SUCCESS";
				}
				else{
					orderRes="FAILED";
				}
				if(orderRes.equals("SUCCESS")){
					int oid=0;
					
					oid = getOrderId();
					for(int i=0;i<bill.size();i++){
						ps=c.prepareStatement("insert into orderdetail values(nextval('odetidseq'),?,?,?,?,?,?,?)");
						ps.setInt(1, oid);
						ps.setInt(2, billno);
						ps.setInt(3, bill.get(i).getPid());
						ps.setFloat(4, bill.get(i).getPrice());
						ps.setInt(5, bill.get(i).getQuantity());
						ps.setFloat(6, bill.get(i).getTotal());
						ps.setFloat(7, bill.get(i).getDiscount());
						r = ps.executeUpdate();
					}
					if(r>0){
						detailRes = "SUCCESS";
					}
					else{
						detailRes="FAILED";
					}
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return detailRes;
	}

	public int getOrderId() {
		int oid = 0;
		try{
			Connection c = DBUtil.getDBConnection();
			PreparedStatement ps = c.prepareStatement("select orderid from orders");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				oid = rs.getInt(1);
			}
		}catch(Exception e){
			System.out.println("Error Getting Order ID");
		}
		return oid;
	}
	
	
	
}
