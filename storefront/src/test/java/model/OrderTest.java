package model;

import java.math.BigDecimal;

import framework.Assert;
import framework.Test;

public class OrderTest {

	private static class StubPayment extends Payment {
		@Override
		public boolean authorize() {
			_status = PaymentStatus.AUTHORIZED;
			return true;
		}

		@Override
		public boolean capture() {
			_status = PaymentStatus.CAPTURED;
			return true;
		}
	}

	@Test
	public void processPaymentWithStubMarksOrderPaid() {
		Order order = new Order();
		order._user = new User();
		order._store = new Store();

		Product product = new Product();
		order.addItem(product, 2, new BigDecimal("7.50"));

		StubPayment payment = new StubPayment();
		payment._amount = new BigDecimal("15.00");
		payment._method = PaymentMethod.CASH;

		order.processPayment(payment);

		Assert.assertEquals(OrderStatus.PAID, order._status);
		Assert.assertEquals(PaymentStatus.CAPTURED, payment._status);
		Assert.assertSame(order, payment._order);
		Assert.assertEquals(1, order._payments.size());
		Assert.assertSame(payment, order._payment);
	}
}
