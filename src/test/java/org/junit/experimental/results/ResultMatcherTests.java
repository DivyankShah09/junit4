package org.junit.experimental.results;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;

public class ResultMatcherTests {
    /**
     * This test case tests the hasSingleFailureMatching matcher from the
     * {@link ResultMatchers} class. This matcher checks if a PrintableResult has exactly one failure
     * that matches a given exception condition.
     */
    @Test
    public void testHasSingleFailureMatchingWithMatchingException() {
        List<Failure> failures = new ArrayList<Failure>();
        Exception expectedException = new IllegalArgumentException("Expected Exception");
        failures.add(new Failure(null, expectedException));

        PrintableResult result = new PrintableResult(failures);

        Matcher<Throwable> exceptionMatcher = instanceOf(IllegalArgumentException.class);

        Matcher<PrintableResult> singleFailureMatcher = ResultMatchers.hasSingleFailureMatching(exceptionMatcher);

        assertThat(result, singleFailureMatcher);
    }

    /**
     * This test case tests the hasSingleFailureMatching matcher from the
     * {@link ResultMatchers} class. This matcher checks if a PrintableResult has exactly one failure
     * that doesnot matches a given exception condition.
     */
    @Test
    public void testHasSingleFailureMatchingWithNonMatchingException() {
        List<Failure> failures = new ArrayList<Failure>();
        Exception unexpectedException = new NullPointerException("Unexpected Exception");
        failures.add(new Failure(null, unexpectedException));

        PrintableResult result = new PrintableResult(failures);

        Matcher<Throwable> exceptionMatcher = instanceOf(IllegalArgumentException.class);

        Matcher<PrintableResult> singleFailureMatcher = ResultMatchers.hasSingleFailureMatching(exceptionMatcher);

        assertThat(result, not(singleFailureMatcher));
    }

    /**
     * The test case tests the describeTo method of hasSingleFailureMatching matcher of {@link ResultMatchers} class.
     * This method is used to describe what the matcher is checking for.
     * The test ensures that the describeTo method generates the expected description for a hasSingleFailureMatching matcher
     * that checks if there is a single failure with an exception that matches a specified class.
     */
    @Test
    public void testDescribeToOfHasSingleFailureMatching() {
        Description description = new StringDescription();
        Description expectedDescription = new StringDescription();

        Matcher<Throwable> throwableMatcher = Matchers.instanceOf(Throwable.class);
        ResultMatchers.hasSingleFailureMatching(throwableMatcher).describeTo(expectedDescription);
        ResultMatchers.hasSingleFailureMatching(throwableMatcher).describeTo(description);

        String expected = expectedDescription.toString();
        String actual = description.toString();
        org.junit.Assert.assertEquals(expected, actual);
    }
}
