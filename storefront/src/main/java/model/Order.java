package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Order {
	private int _iD2;
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

	private Vector<OrderItem> ensureItems() {
		if (_orderItems == null) {
			_orderItems = new Vector<>();
		}
		if (_items == null) {
			_items = _orderItems;
		} else if (_items != _orderItems) {
			_items.clear();
			_items.addAll(_orderItems);
		}
		return _orderItems;
	}

	/**
	 * Operations
	 */
	public OrderItem addItem(Product aProduct, int aQty, BigDecimal aUnitPrice) {
		Objects.requireNonNull(aProduct, "Product is required");
		Objects.requireNonNull(aUnitPrice, "Unit price is required");
		if (aQty <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		if (aUnitPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Unit price cannot be negative.");
		}

		Vector<OrderItem> items = ensureItems();
		for (OrderItem existing : items) {
			if (existing._product == aProduct || (existing._product != null && existing._product.equals(aProduct))) {
				existing._qty += aQty;
				existing._unitPrice = aUnitPrice;
				existing.recalc();
				calculateTotal();
				return existing;
			}
		}

		OrderItem item = new OrderItem();
		item._order = this;
		item._product = aProduct;
		item._qty = aQty;
		item._unitPrice = aUnitPrice;
		item._discount = BigDecimal.ZERO;
		item.recalc();
		items.add(item);
		ensureItems(); // keep _items in sync
		calculateTotal();
		return item;
	}

	public void calculateTotal() {
		Vector<OrderItem> items = ensureItems();
		BigDecimal sum = BigDecimal.ZERO;
		for (OrderItem item : items) {
			if (item._lineTotal == null) {
				item.recalc();
			}
			sum = sum.add(item._lineTotal);
		}
		_total = sum.setScale(2, RoundingMode.HALF_UP);
	}

	public Payment processPayment(Payment payment) {
		Objects.requireNonNull(payment, "Payment is required");
		if (_total == null) {
			calculateTotal();
		}
		if (payment._amount == null) {
			payment._amount = _total;
		}
		if (payment._amount.compareTo(_total) < 0) {
			throw new IllegalArgumentException("Payment amount cannot be less than order total.");
		}
		payment._order = this;
		if (payment.authorize() && payment.capture()) {
			_payments.add(payment);
			_payment = payment;
			setStatus(OrderStatus.PAID);
			return payment;
		}
		payment._status = PaymentStatus.FAILED;
		throw new IllegalStateException("Payment could not be processed.");
	}

	public void setStatus(OrderStatus aStatus) {
		this._status = aStatus;
	}
}
