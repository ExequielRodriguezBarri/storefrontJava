package com.storefront.model;

import java.util.List;

public class User {
	private int _iD2;
	private int _iD;
	public String _name;
	public String _email;
	public String _passwordHash;
	public Role _role;
	/**
	 * Associations
	 */
	public java.util.Vector<Cart> _carts;
	/**
	 * 1 -- 0..* Cart
	 */
	public java.util.Vector<Order> _orders;
	/**
	 * 1 -- 0..* Order
	 */
	public java.util.Vector<ShoppingList> _shoppingLists;
	/**
	 * 1 -- 0..* ShoppingList (createdBy)
	 */
	public java.util.Vector<Store> _managesStores;

	/**
	 * Operations
	 */
	public boolean authenticate(String aPasswordPlaintext) {
		throw new UnsupportedOperationException();
	}

	public boolean isAdmin() {
		throw new UnsupportedOperationException();
	}

	public java.util.List<Cart> getActiveCarts() {
		throw new UnsupportedOperationException();
	}

	public Order placeOrder(Cart aCart) {
		throw new UnsupportedOperationException();
	}
}