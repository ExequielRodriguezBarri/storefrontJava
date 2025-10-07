package model;

import java.util.UUID;

public class ShoppingListItem {
	public UUID _id;
	public ShoppingList _list;
	/**
	 * 1 -- 1 ShoppingList
	 */
	public Product _product;
	/**
	 * 1 -- 1 Product
	 */
	public int _qty;
	public Product _unnamed_Product_;

	public void setQty(int aQty) {
		this._qty = aQty;
	}
}