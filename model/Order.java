package com.storefront.model;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Vector;
import com.storefront.model.Payment;

public class Order {
	private int _iD2;
	private int _iD;
	public User _user;
	/**
	 * 1 -- 1 User
	 */
	public Store _store;
	/**
	 * 1 -- 1 Store
	 */
	public OrderStatus _status;
	public Date _createdAt;
	public BigDecimal _total;
	/**
	 * Associations
	 */
	public java.util.Vector<OrderItem> _items;
	/**
	 * 1 -- 1..* OrderItem
	 */
	public Payment _payment;
	public Vector<Payment> _payments = new Vector<Payment>();
	public Vector<OrderItem> _orderItems = new Vector<OrderItem>();

	/**
	 * Operations
	 */
	public OrderItem addItem(Product aProduct, int aQty, BigDecimal aUnitPrice) {
		throw new UnsupportedOperationException();
	}

	public void calculateTotal() {
		throw new UnsupportedOperationException();
	}

	public void setStatus(OrderStatus aStatus) {
		this._status = aStatus;
	}
}