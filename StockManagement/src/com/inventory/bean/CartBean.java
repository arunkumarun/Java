package com.inventory.bean;

import java.time.LocalDate;
import java.util.ArrayList;

public class CartBean {
	
	public ArrayList<CartItemBean> alCartItems = new ArrayList<CartItemBean>();
	public float orderTotal;
	public LocalDate date;
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getLineItemCount(){
		return alCartItems.size();
	}

	public ArrayList<CartItemBean> getAlCartItems() {
		return alCartItems;
	}

	public void setAlCartItems(ArrayList<CartItemBean> alCartItems) {
		this.alCartItems = alCartItems;
	}
	
	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	protected void calculateOrderTotal() {
		float total = 0;
		for(int counter=0;counter<alCartItems.size();counter++) {
			CartItemBean cartItem = (CartItemBean) alCartItems.get(counter);
			total+=cartItem.getTotal();
		}
		setOrderTotal(total);
	}
	
	public ArrayList<CartItemBean> addCartItem(int pid, String pname, String pdesc, float price, float discount, int inStock,int quantity) {
		float disPrice = quantity*((price*discount)/100);
		float total= price*quantity - disPrice;
		CartItemBean cartItem = new CartItemBean();
		
		cartItem.setPid(pid);
		cartItem.setPname(pname);
		cartItem.setPdesc(pdesc);
		cartItem.setPrice(price);
		cartItem.setDiscount(discount);
		cartItem.setInStock(inStock);
		cartItem.setQuantity(quantity);
		cartItem.setTotal(total);
		
		alCartItems.add(cartItem);
		calculateOrderTotal();
		
		return alCartItems;
	}
	
	public void addCartItem(CartItemBean cartItem) {
		alCartItems.add(cartItem);
	}
	
	public CartItemBean getCartItem(int iItemIndex) {
		CartItemBean cartItem = null;
		if(alCartItems.size()>iItemIndex) {
		  cartItem = (CartItemBean) alCartItems.get(iItemIndex);
		}
		return cartItem;
	}
	
}
