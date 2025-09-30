package com.storefront.model;

import java.math.BigDecimal;

public class OrderItem {
	private int _iD2;
	private int _iD;
	public Order _order;
	/**
	 * 1 -- 1 Order
	 */
	public Product _product;
	/**
	 * 1 -- 1 Product
	 */
	public int _qty;
	public BigDecimal _unitPrice;
	public BigDecimal _discount;
	public BigDecimal _lineTotal;

	/**
	 * Operations
	 */
	public void recalc() {
		throw new UnsupportedOperationException();
	}
}