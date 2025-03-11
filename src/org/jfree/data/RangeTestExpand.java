package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestExpand {

    // Test expand with valid range and positive margins
    @Test
    public void testExpand_ValidRange_PositiveMargins() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.expand(base, 0.5, 0.5);
        assertEquals(0.0, result.getLowerBound(), 0.0001);
        assertEquals(8.0, result.getUpperBound(), 0.0001);
    }

    // Test expand with valid range and zero margins
    @Test
    public void testExpand_ValidRange_ZeroMargins() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.expand(base, 0, 0); 
        assertEquals(2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }
    
    // Test expand with null range
    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NullRange() {
        Range.expand(null, 0.5, 0.5);
    }

    // Test expand with margins at the lower boundary (0)
    @Test
    public void testExpand_LowerBoundaryMargins() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.expand(base, 0.0, 0.0);
        assertEquals(2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }

    // Test expand with margins at the upper boundary (maximum margin)
    @Test
    public void testExpand_UpperBoundaryMargins() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.expand(base, 1.0, 1.0);
        assertEquals(-2.0, result.getLowerBound(), 0.0001);
        assertEquals(10.0, result.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testExpand_TriggersLowerGreaterThanUpperAdjustment() {
        Range base = new Range(-10.0, -5.0); // length = 5.0
        Range result = Range.expand(base, -10.0, -10.0);
        // lower = -10 - (5*-10) = 40
        // upper = -5 + (5*-10) = -55
        // lower > upper = true → adjustment is triggered
        assertEquals(-7.5, result.getLowerBound(), 0.0001);
        assertEquals(-7.5, result.getUpperBound(), 0.0001);
    }
}
