package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		if (_unitPrice == null) {
			throw new IllegalStateException("Unit price must be set before recalculating.");
		}
		if (_qty <= 0) {
			throw new IllegalStateException("Quantity must be greater than zero.");
		}
		BigDecimal total = _unitPrice.multiply(BigDecimal.valueOf(_qty));
		_lineTotal = total.setScale(2, RoundingMode.HALF_UP);
	}
}
