package com.namics.oss.java.tools.utils.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher to check if an operation threw an expected exception.
 *
 * @author aschaefer, Namics AG
 * @since 10.05.16 14:07
 */
public class ThrownMatcher extends TypeSafeMatcher<Runnable> {

	private final String expected;
	private String actual;

	public ThrownMatcher(String s) {
		expected = s;
	}

	public static Matcher<Runnable> thrown(Class<? extends Throwable> expected) {
		return new ThrownMatcher(expected.getName());
	}

	@Override
	public boolean matchesSafely(Runnable action) {
		try {
			action.run();
			actual = "nothing";
			return false;
		} catch (Throwable t) {
			actual = t.getClass().getName();
			return actual.equals(expected);
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Should have thrown " + expected + " but threw " + actual);
	}

}
