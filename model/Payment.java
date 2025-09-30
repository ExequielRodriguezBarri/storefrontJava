package com.storefront.model;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
	private int _iD2;
	private int _iD;
	public Order _order;
	/**
	 * 1 -- 1 Order
	 */
	public PaymentMethod _method;
	public BigDecimal _amount;
	public PaymentStatus _status;
	public Date _paidAt;

	/**
	 * Operations
	 */
	public boolean authorize() {
		throw new UnsupportedOperationException();
	}

	public boolean capture() {
		throw new UnsupportedOperationException();
	}

	public boolean refund(BigDecimal aAmount) {
		throw new UnsupportedOperationException();
	}
}