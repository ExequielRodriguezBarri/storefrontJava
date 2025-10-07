package model;

import java.math.BigDecimal;

import framework.Assert;
import framework.Test;

public class CartTest {

	@Test
	public void addItemMergesQuantitiesAndRecalculates() {
		Product product = new Product();
		product._price = new BigDecimal("5.00");

		Cart cart = new Cart();
		cart.addItem(product, 2, product._price);
		cart.addItem(product, 3, new BigDecimal("4.50"));

		Assert.assertEquals(1, cart.getItems().size());

		CartItem item = cart.getItems().get(0);
		Assert.assertEquals(product, item._product);
		Assert.assertEquals(5, item._qty);
		Assert.assertEquals(new BigDecimal("4.50"), item._unitPrice);
		Assert.assertEquals(new BigDecimal("22.50"), item._lineTotal);
	}

	@Test
	public void checkoutBuildsOrderAndClearsCart() {
		Product product = new Product();
		product._price = new BigDecimal("2.00");

		Cart cart = new Cart();
		cart._user = new User();
		cart._store = new Store();
		cart.addItem(product, 4, product._price);

		Order order = cart.checkout();

		Assert.assertEquals(0, cart.getItems().size());
		Assert.assertEquals(OrderStatus.NEW, order._status);
		Assert.assertEquals(1, order._orderItems.size());
		Assert.assertEquals(new BigDecimal("8.00"), order._total);

		OrderItem item = order._orderItems.get(0);
		Assert.assertSame(product, item._product);
		Assert.assertEquals(4, item._qty);
		Assert.assertEquals(new BigDecimal("8.00"), item._lineTotal);
	}
}
