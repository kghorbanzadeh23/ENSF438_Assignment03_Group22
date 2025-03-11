package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestShift {

    // Test shift with valid range and positive delta
    @Test
    public void testShift_ValidRange_PositiveDelta() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.shift(base, 3.0);
        assertEquals(5.0, result.getLowerBound(), 0.0001);
        assertEquals(9.0, result.getUpperBound(), 0.0001);
    }

    // Test shift with valid range and negative delta
    @Test
    public void testShift_ValidRange_NegativeDelta() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.shift(base, -2.0);
        assertEquals(0.0, result.getLowerBound(), 0.0001);
        assertEquals(4.0, result.getUpperBound(), 0.0001);
    }
    
    // Test shift with delta at the lower boundary (0)
    @Test
    public void testShift_LowerBoundary() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.shift(base, 0.0);
        assertEquals(2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }
    
    // Test shift with delta at the upper boundary (large value)
    @Test
    public void testShift_UpperBoundary() {
        Range base = new Range(2.0, 6.0);
        double maxDelta = Double.MAX_VALUE / 2; // Prevent overflow
        Range result = Range.shift(base, maxDelta);
        assertEquals(2.0 + maxDelta, result.getLowerBound(), 0.0001);
        assertEquals(6.0 + maxDelta, result.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testShift_AllowZeroCrossingExplicitTrue() {
        Range base = new Range(10.0, 20.0);
        Range result = Range.shift(base, 5.0, true);
        assertEquals(15.0, result.getLowerBound(), 0.0001);
        assertEquals(25.0, result.getUpperBound(), 0.0001);
    }
    
}
