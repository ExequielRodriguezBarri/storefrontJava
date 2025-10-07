package model;

import java.math.BigDecimal;

import framework.Assert;
import framework.Test;

public class PaymentTest {

	private static class MockPayment extends Payment {
		boolean authorizeCalled;
		boolean captureCalled;

		@Override
		public boolean authorize() {
			authorizeCalled = true;
			return super.authorize();
		}

		@Override
		public boolean capture() {
			captureCalled = true;
			return super.capture();
		}
	}

	@Test
	public void processPaymentInvokesLifecycleMethodsOnMock() {
		Order order = new Order();
		order._user = new User();
		order._store = new Store();

		Product product = new Product();
		order.addItem(product, 1, new BigDecimal("12.00"));

		MockPayment payment = new MockPayment();
		payment._amount = new BigDecimal("12.00");
		payment._method = PaymentMethod.CASH;

		order.processPayment(payment);

		Assert.assertTrue(payment.authorizeCalled, "Authorize should be invoked.");
		Assert.assertTrue(payment.captureCalled, "Capture should be invoked.");
		Assert.assertEquals(PaymentStatus.CAPTURED, payment._status);
	}
}
