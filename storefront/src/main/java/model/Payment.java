package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

	public Payment() {
		this._status = PaymentStatus.CREATED;
	}

	/**
	 * Operations
	 */
	public boolean authorize() {
		if (_status == PaymentStatus.CAPTURED) {
			throw new IllegalStateException("Payment already captured.");
		}
		if (_status == PaymentStatus.REFUNDED) {
			throw new IllegalStateException("Payment already refunded.");
		}
		Objects.requireNonNull(_amount, "Amount must be set before authorization.");
		if (_amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalStateException("Amount must be greater than zero.");
		}
		_status = PaymentStatus.AUTHORIZED;
		return true;
	}

	public boolean capture() {
		if (_status == PaymentStatus.CAPTURED) {
			return true;
		}
		if (_status != PaymentStatus.AUTHORIZED && _status != PaymentStatus.CREATED) {
			throw new IllegalStateException("Payment must be authorized before capture.");
		}
		if (_status != PaymentStatus.AUTHORIZED) {
			authorize();
		}
		_paidAt = new Date();
		_status = PaymentStatus.CAPTURED;
		return true;
	}

	public boolean refund(BigDecimal aAmount) {
		Objects.requireNonNull(aAmount, "Refund amount is required.");
		if (_status != PaymentStatus.CAPTURED) {
			throw new IllegalStateException("Only captured payments can be refunded.");
		}
		if (aAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Refund amount must be greater than zero.");
		}
		if (aAmount.compareTo(_amount) > 0) {
			throw new IllegalArgumentException("Refund amount cannot exceed captured amount.");
		}
		_status = PaymentStatus.REFUNDED;
		_amount = _amount.subtract(aAmount);
		return true;
	}
}
