package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestCombineIgnoringNaN {

    // Test combining two valid ranges
    @Test
    public void testCombineIgnoringNaN_ValidRanges() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(4.0, 8.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Lower bound should be the minimum of both ranges", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be the maximum of both ranges", 8.0, result.getUpperBound(), 0.0001);
    }

    // Test combining range with NaN and a valid range
    @Test
    public void testCombineIgnoringNaN_NaNAndValidRange() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(1.0, 5.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Combining NaN range with a valid range should return the valid range", 1.0, result.getLowerBound(), 0.0001);
        assertEquals("Combining NaN range with a valid range should return the valid range", 5.0, result.getUpperBound(), 0.0001);
    }

    // Test combining two NaN ranges
    @Test
    public void testCombineIgnoringNaN_TwoNaNRanges() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertNull("Combining two NaN ranges should return null", result);
    }

    // Test combining a valid range with null
    @Test
    public void testCombineIgnoringNaN_ValidRangeAndNull() {
        Range range1 = new Range(2.0, 6.0);
        Range result = Range.combineIgnoringNaN(range1, null);
        assertEquals("Combining valid range with null should return the valid range", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Combining valid range with null should return the valid range", 6.0, result.getUpperBound(), 0.0001);
    }

    // Test combining NaN range with null
    @Test
    public void testCombineIgnoringNaN_NaNRangeAndNull() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, null);
        assertNull("Combining NaN range with null should return null", result);
    }

    // Test combining null with a valid range
    @Test
    public void testCombineIgnoringNaN_NullAndValidRange() {
        Range range2 = new Range(3.0, 7.0);
        Range result = Range.combineIgnoringNaN(null, range2);
        assertEquals("Combining null with valid range should return the valid range", 3.0, result.getLowerBound(), 0.0001);
        assertEquals("Combining null with valid range should return the valid range", 7.0, result.getUpperBound(), 0.0001);
    }

    // Test combining NaN range with NaN range that isn't exactly NaN in lower or upper bound
    @Test
    public void testCombineIgnoringNaN_NaNWithLowerBound() {
        Range range1 = new Range(Double.NaN, 6.0);
        Range range2 = new Range(3.0, 8.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Lower bound should be the minimum of both ranges", 3.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be the maximum of both ranges", 8.0, result.getUpperBound(), 0.0001);
    }

    // Test combining NaN and valid range where upper bounds are NaN
    @Test
    public void testCombineIgnoringNaN_ValidRangeAndNaNUpper() {
        Range range1 = new Range(1.0, Double.NaN);
        Range range2 = new Range(4.0, 9.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Lower bound should be the minimum of both ranges", 1.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be the maximum of both ranges", 9.0, result.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testCombineIgnoringNaN_Range1Null_Range2NaN() {
        Range range1 = null;
        Range range2 = new Range(Double.NaN, Double.NaN);
        
        Range result = Range.combineIgnoringNaN(range1, range2);
        
        // Verifying that the result is null since range2 is a NaN range
        assertNull("The result should be null when range2 is a NaN range and range1 is null.", result);
    }
}
