package model;

import java.math.BigDecimal;

public class App {
	public static void main(String[] args) {
		Store store = new Store();
		store._name = "Demo Store";

		User user = new User();
		user._name = "Alice";

		Product product = new Product();
		product._sku = "SKU-001";
		product._name = "Fresh Coffee Beans";
		product._price = new BigDecimal("12.50");
		product._active = true;

		Cart cart = new Cart();
		cart._user = user;
		cart._store = store;

		cart.addItem(product, 2, product._price);

		Order order = cart.checkout();
		System.out.println("Order subtotal: " + order._total);

		Cash payment = new Cash();
		payment._method = PaymentMethod.CASH;
		payment._amount = order._total;

		order.processPayment(payment);

		System.out.println("Order status: " + order._status);
		System.out.println("Payment status: " + payment._status);
	}
}
