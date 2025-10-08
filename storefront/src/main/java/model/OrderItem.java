package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItem {
	private int _iD2;
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
		if (_unitPrice == null) {
			throw new IllegalStateException("Unit price must be set before recalculating.");
		}
		if (_qty <= 0) {
			throw new IllegalStateException("Quantity must be greater than zero.");
		}
		BigDecimal total = _unitPrice.multiply(BigDecimal.valueOf(_qty));
		BigDecimal discount = _discount != null ? _discount : BigDecimal.ZERO;
		BigDecimal computed = total.subtract(discount);
		if (computed.compareTo(BigDecimal.ZERO) < 0) {
			computed = BigDecimal.ZERO;
		}
		_lineTotal = computed.setScale(2, RoundingMode.HALF_UP);
	}
}
