package com.storefront.model;

import java.util.Vector;
import com.storefront.model.Store;

public class InventoryItem {
	private int _iD2;
	private int _iD;
	public Store _store;
	/**
	 * 1 -- 1 Store
	 */
	public Product _product;
	/**
	 * 1 -- 1 Product
	 */
	public int _quantity;
	public int _reorderLevel;
	public Vector<Store> _stores = new Vector<Store>();

	/**
	 * Operations
	 */
	public void adjustQuantity(int aDelta) {
		throw new UnsupportedOperationException();
	}

	public boolean needsReorder() {
		throw new UnsupportedOperationException();
	}
}