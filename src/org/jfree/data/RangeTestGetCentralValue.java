package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestGetCentralValue {

    // Test getCentralValue with positive bounds
    @Test
    public void testGetCentralValue_PositiveBounds() {
        Range range = new Range(10.0, 20.0);
        assertEquals(15.0, range.getCentralValue(), 0.0001);
    }

    // Test getCentralValue with negative bounds
    @Test
    public void testGetCentralValue_NegativeBounds() {
        Range range = new Range(-20.0, -10.0);
        assertEquals(-15.0, range.getCentralValue(), 0.0001);
    }

    // Test getCentralValue with zero bounds
    @Test
    public void testGetCentralValue_ZeroBounds() {
        Range range = new Range(0.0, 0.0);
        assertEquals(0.0, range.getCentralValue(), 0.0001);
    }

    // Test getCentralValue with a negative lower bound and positive upper bound
    @Test
    public void testGetCentralValue_NegativeLowerPositiveUpper() {
        Range range = new Range(-10.0, 10.0);
        assertEquals(0.0, range.getCentralValue(), 0.0001);
    }
    
    // Test getCentralValue with lower bound at the lower boundary (extremely large negative value)
    @Test
    public void testGetCentralValue_LowerBoundary() {
        Range range = new Range(-Double.MAX_VALUE, -Double.MAX_VALUE + 10);
        assertEquals(-Double.MAX_VALUE + 5, range.getCentralValue(), 0.0001);
    }

    // Test getCentralValue with a positive lower bound and negative upper bound (invalid range)
    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralValue_PositiveLowerNegativeUpper() {
        new Range(10.0, -10.0); // Should throw an exception
    }

    // Test getCentralValue with upper bound at the upper boundary (extremely large positive value)
    @Test
    public void testGetCentralValue_UpperBoundary() {
        Range range = new Range(Double.MAX_VALUE - 10, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE - 5, range.getCentralValue(), 0.0001);
    }
}
