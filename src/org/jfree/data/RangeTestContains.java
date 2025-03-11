package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestContains {

    // Test contains with value within the range
    @Test
    public void testContains_ValueWithinRange() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(3.0));
    }

    // Test contains with value outside the range (less than lower bound)
    @Test
    public void testContains_ValueOutsideRange_LessThanLower() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(0.0));
    }

    // Test contains with value outside the range (greater than upper bound)
    @Test
    public void testContains_ValueOutsideRange_GreaterThanUpper() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(6.0));
    }

    // Test contains with value at the lower boundary
    @Test
    public void testContains_ValueAtLowerBoundary() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(1.0));
    }

    // Test contains with value at the upper boundary
    @Test
    public void testContains_ValueAtUpperBoundary() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(5.0));
    }
}
