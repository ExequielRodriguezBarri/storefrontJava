package com.storefront.model;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

public class Cart {
	private int _iD2;
	private int _iD;
	public User _user;
	/**
	 * 1 -- 1 User
	 */
	public Store _store;
	/**
	 * 1 -- 0..1 Store
	 */
	public CartStatus _status;
	public Date _createdAt;
	/**
	 * Associations
	 */
	public java.util.Vector<CartItem> _items;
	public CartItem _cartItem;

	/**
	 * Operations
	 */
	public CartItem addItem(Product aProduct, int aQty, BigDecimal aUnitPrice) {
		throw new UnsupportedOperationException();
	}

	public void removeItem(Product aProduct) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<CartItem> getItems() {
		throw new UnsupportedOperationException();
	}

	public BigDecimal subtotal() {
		throw new UnsupportedOperationException();
	}

	public Order checkout() {
		throw new UnsupportedOperationException();
	}
}