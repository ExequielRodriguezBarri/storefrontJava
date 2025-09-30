package model;

import java.math.BigDecimal;

public class Product {
	private int _iD2;
	private int _iD;
	public String _sku;
	public String _name;
	public BigDecimal _price;
	public String _description;
	public String _brand;
	public boolean _active;
	/**
	 * Associations (reverse navigation lists to help VP detect)
	 */
	public java.util.Vector<InventoryItem> _inventoryItems;
	/**
	 * 1 -- 0..* InventoryItem
	 */
	public java.util.Vector<Offer> _offers;
	/**
	 * 1 -- 0..* Offer
	 */
	public java.util.Vector<OrderItem> _orderItems;
	/**
	 * 1 -- 0..* OrderItem
	 */
	public java.util.Vector<CartItem> _cartItems;
	/**
	 * 1 -- 0..* CartItem
	 */
	public java.util.Vector<ShoppingListItem> _shoppingItems;
	public InventoryItem _inventoryItem;
	public OrderItem _orderItem;
	public ShoppingListItem _unnamed_ShoppingListItem_;
	public CartItem _cartItem;

	/**
	 * Operations
	 */
	public boolean isActive() {
		return this._active;
	}

	public BigDecimal priceAfter(Offer aOffer) {
		throw new UnsupportedOperationException();
	}
}