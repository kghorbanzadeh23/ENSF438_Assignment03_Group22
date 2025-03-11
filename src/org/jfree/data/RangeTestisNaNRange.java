package org.jfree.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RangeTestisNaNRange {

    private Range range;

    @Before
    public void setUp() {
        // This method will run before each test case to set up the environment.
    }

    // Test isNaNRange() with both lower and upper bounds as NaN
    @Test
    public void testIsNaNRange_BothNaN() {
        range = new Range(Double.NaN, Double.NaN);  // Create a Range with both lower and upper as NaN
        boolean result = range.isNaNRange();
        assertTrue("Both lower and upper bounds are NaN, so isNaNRange should return true", result);
    }

    // Test isNaNRange() with lower bound as NaN and upper bound as a number
    @Test
    public void testIsNaNRange_LowerNaN() {
        range = new Range(Double.NaN, 5.0);  // Create a Range with lower as NaN and upper as 5.0
        boolean result = range.isNaNRange();
        assertFalse("Lower bound is NaN but upper bound is a number, so isNaNRange should return false", result);
    }

    // Test isNaNRange() with upper bound as NaN and lower bound as a number
    @Test
    public void testIsNaNRange_UpperNaN() {
        range = new Range(5.0, Double.NaN);  // Create a Range with lower as 5.0 and upper as NaN
        boolean result = range.isNaNRange();
        assertFalse("Upper bound is NaN but lower bound is a number, so isNaNRange should return false", result);
    }

    // Test isNaNRange() with neither lower nor upper bound as NaN
    @Test
    public void testIsNaNRange_NeitherNaN() {
        range = new Range(1.0, 5.0);  // Create a Range with valid numerical values for both lower and upper
        boolean result = range.isNaNRange();
        assertFalse("Neither lower nor upper bounds are NaN, so isNaNRange should return false", result);
    }

    // Test isNaNRange() with lower bound as NaN and upper bound as negative number
    @Test
    public void testIsNaNRange_LowerNaN_NegativeUpper() {
        range = new Range(Double.NaN, -5.0);  // Create a Range with lower as NaN and upper as -5.0
        boolean result = range.isNaNRange();
        assertFalse("Lower bound is NaN but upper bound is a number, so isNaNRange should return false", result);
    }

    // Test isNaNRange() with upper bound as NaN and lower bound as negative number
    @Test
    public void testIsNaNRange_UpperNaN_NegativeLower() {
        range = new Range(-5.0, Double.NaN);  // Create a Range with lower as -5.0 and upper as NaN
        boolean result = range.isNaNRange();
        assertFalse("Upper bound is NaN but lower bound is a number, so isNaNRange should return false", result);
    }

    // Test isNaNRange() with lower and upper as zero
    @Test
    public void testIsNaNRange_ZeroBounds() {
        range = new Range(0.0, 0.0);  // Create a Range with both lower and upper as 0.0
        boolean result = range.isNaNRange();
        assertFalse("Neither lower nor upper bounds are NaN, so isNaNRange should return false", result);
    }
}
