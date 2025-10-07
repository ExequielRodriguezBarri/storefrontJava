package framework;

import java.math.BigDecimal;

public final class Assert {
	private Assert() {
	}

	public static void assertTrue(boolean condition, String message) {
		if (!condition) {
			throw new AssertionError(message);
		}
	}

	public static void assertTrue(boolean condition) {
		assertTrue(condition, "Expected condition to be true.");
	}

	public static void assertFalse(boolean condition, String message) {
		assertTrue(!condition, message);
	}

	public static void assertEquals(Object expected, Object actual) {
		if (expected == null && actual == null) {
			return;
		}
		if (expected != null && expected.equals(actual)) {
			return;
		}
		throw new AssertionError("Expected <" + expected + "> but was <" + actual + ">.");
	}

	public static void assertEquals(BigDecimal expected, BigDecimal actual) {
		if (expected == null && actual == null) {
			return;
		}
		if (expected != null && actual != null && expected.compareTo(actual) == 0) {
			return;
		}
		throw new AssertionError("Expected <" + expected + "> but was <" + actual + ">.");
	}

	public static void assertSame(Object expected, Object actual) {
		if (expected != actual) {
			throw new AssertionError("Expected same instance but found different references.");
		}
	}

	public static void fail(String message) {
		throw new AssertionError(message);
	}
}
