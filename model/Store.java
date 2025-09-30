package com.storefront.model;

import java.math.BigDecimal;
import java.util.List;

public class Store {
	private int _iD2;
	private int _iD;
	public String _name;
	public String _location;
	/**
	 * Associations
	 */
	public java.util.Vector<InventoryItem> _inventoryItems;
	/**
	 * 1 -- 0..* InventoryItem
	 */
	public java.util.Vector<Offer> _offers;
	/**
	 * 1 -- 0..* Offer
	 */
	public java.util.Vector<Order> _orders;
	/**
	 * 1 -- 0..* Order
	 */
	public java.util.Vector<ShoppingList> _shoppingLists;
	/**
	 * 1 -- 0..* ShoppingList
	 */
	public java.util.Vector<User> _managers;

	/**
	 * Operations
	 */
	public InventoryItem addInventoryItem(Product aProduct, int aQuantity) {
		throw new UnsupportedOperationException();
	}

	public InventoryItem findInventory(Product aProduct) {
		throw new UnsupportedOperationException();
	}

	public Offer createOffer(Product aProduct, String aTitle, DiscountType aType, BigDecimal aValue) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<Order> getOrders() {
		throw new UnsupportedOperationException();
	}
}