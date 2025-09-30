package model;

import java.math.BigDecimal;

public class CartItem {
	private int _iD2;
	private int _iD;
	public Cart _cart;
	/**
	 * 1 -- 1 Cart
	 */
	public Product _product;
	/**
	 * 1 -- 1 Product
	 */
	public int _qty;
	public BigDecimal _unitPrice;
	public BigDecimal _lineTotal;

	/**
	 * Operations
	 */
	public void recalc() {
		throw new UnsupportedOperationException();
	}
}