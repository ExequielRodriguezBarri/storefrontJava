package com.storefront.model;

import java.util.UUID;
import java.util.Date;
import java.util.List;

public class ShoppingList {
	public UUID _id;
	public Store _store;
	/**
	 * 1 -- 1 Store
	 */
	public User _createdBy;
	/**
	 * 1 -- 1 User
	 */
	public Date _createdAt;
	/**
	 * Associations
	 */
	public java.util.Vector<ShoppingListItem> _items;

	/**
	 * Operations
	 */
	public ShoppingListItem addItem(Product aProduct, int aQty) {
		throw new UnsupportedOperationException();
	}

	public void removeItem(Product aProduct) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<ShoppingListItem> getItems() {
		throw new UnsupportedOperationException();
	}
}