package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Cart {
	private int _iD2;
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

	private java.util.Vector<CartItem> ensureItems() {
		if (_items == null) {
			_items = new java.util.Vector<>();
		}
		return _items;
	}

	/**
	 * Operations
	 */
	public CartItem addItem(Product aProduct, int aQty, BigDecimal aUnitPrice) {
		Objects.requireNonNull(aProduct, "Product is required");
		Objects.requireNonNull(aUnitPrice, "Unit price is required");
		if (aQty <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		if (aUnitPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Unit price cannot be negative.");
		}

		java.util.Vector<CartItem> items = ensureItems();
		for (CartItem existing : items) {
			if (existing._product == aProduct || (existing._product != null && existing._product.equals(aProduct))) {
				existing._qty += aQty;
				existing._unitPrice = aUnitPrice;
				existing.recalc();
				return existing;
			}
		}

		CartItem item = new CartItem();
		item._cart = this;
		item._product = aProduct;
		item._qty = aQty;
		item._unitPrice = aUnitPrice;
		item.recalc();
		items.add(item);
		_status = CartStatus.ACTIVE;
		return item;
	}

	public void removeItem(Product aProduct) {
		if (_items == null || aProduct == null) {
			return;
		}
		_items.removeIf(item -> item._product == aProduct || (item._product != null && item._product.equals(aProduct)));
		if (_items.isEmpty()) {
			_status = CartStatus.INACTIVE;
		}
	}

	public List<CartItem> getItems() {
		return Collections.unmodifiableList(ensureItems());
	}

	public BigDecimal subtotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : ensureItems()) {
			if (item._lineTotal == null) {
				item.recalc();
			}
			total = total.add(item._lineTotal);
		}
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	public Order checkout() {
		if (_items == null || _items.isEmpty()) {
			throw new IllegalStateException("Cannot checkout an empty cart.");
		}
		Order order = new Order();
		order._user = this._user;
		order._store = this._store;
		order._status = OrderStatus.NEW;
		order._createdAt = new Date();

		for (CartItem item : _items) {
			order.addItem(item._product, item._qty, item._unitPrice);
		}
		order.calculateTotal();

		_status = CartStatus.INACTIVE;
		_items.clear();
		return order;
	}
}
