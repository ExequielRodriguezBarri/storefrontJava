package framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import model.CartTest;
import model.OrderTest;
import model.PaymentTest;

public class TestRunner {
	private static final Class<?>[] TEST_CLASSES = new Class<?>[] {
			CartTest.class,
			OrderTest.class,
			PaymentTest.class
	};

	public static void main(String[] args) {
		int executed = 0;
		List<String> failures = new ArrayList<>();

		for (Class<?> testClass : TEST_CLASSES) {
			try {
				Object instance = testClass.getDeclaredConstructor().newInstance();
				for (Method method : testClass.getDeclaredMethods()) {
					if (method.isAnnotationPresent(Test.class)) {
						executed++;
						try {
							method.invoke(instance);
							System.out.println("[PASS] " + testClass.getSimpleName() + "." + method.getName());
						} catch (InvocationTargetException ite) {
							Throwable cause = ite.getCause() != null ? ite.getCause() : ite;
							failures.add(testClass.getSimpleName() + "." + method.getName() + " -> " + cause);
							System.out.println("[FAIL] " + testClass.getSimpleName() + "." + method.getName());
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("Failed to run tests for " + testClass.getName(), e);
			}
		}

		System.out.println();
		System.out.println("Executed: " + executed + " test(s)");
		if (failures.isEmpty()) {
			System.out.println("Status: SUCCESS");
		} else {
			System.out.println("Status: FAILED");
			for (String failure : failures) {
				System.out.println(" - " + failure);
			}
			System.exit(1);
		}
	}
}
