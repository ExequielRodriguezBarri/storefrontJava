package com.storefront.model;

import java.math.BigDecimal;
import java.util.Date;

public class Offer {
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
	public String _title;
	public DiscountType _discountType;
	public BigDecimal _discountValue;
	public Date _startDate;
	public Date _endDate;
	public boolean _isActive;

	/**
	 * Operations
	 */
	public boolean isApplicable(Product aP, Date aOnDate) {
		throw new UnsupportedOperationException();
	}

	public BigDecimal apply(BigDecimal aBase) {
		throw new UnsupportedOperationException();
	}
}