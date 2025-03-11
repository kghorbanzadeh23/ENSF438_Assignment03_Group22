package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestExpandToInclude {

    // Test expandToInclude with null range and value inside the range
    @Test
    public void testExpandToInclude_NullRange_ValueInside() {
        Range range = null;
        Range result = Range.expandToInclude(range, 4.0);
        assertEquals("When range is null, the range should be created with the given value as both lower and upper bounds.", 4.0, result.getLowerBound(), 0.0001);
        assertEquals(4.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with value smaller than lower bound
    @Test
    public void testExpandToInclude_ValueSmallerThanLower() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 1.0);
        assertEquals("When value is smaller than lower bound, the lower bound should be updated.", 1.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with value greater than upper bound
    @Test
    public void testExpandToInclude_ValueGreaterThanUpper() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 8.0);
        assertEquals("When value is greater than upper bound, the upper bound should be updated.", 2.0, result.getLowerBound(), 0.0001);
        assertEquals(8.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with value equal to lower bound
    @Test
    public void testExpandToInclude_ValueEqualToLowerBound() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 2.0);
        assertEquals("When value is equal to the lower bound, the range should remain the same.", 2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with value equal to upper bound
    @Test
    public void testExpandToInclude_ValueEqualToUpperBound() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 6.0);
        assertEquals("When value is equal to the upper bound, the range should remain the same.", 2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with value inside the range
    @Test
    public void testExpandToInclude_ValueInsideRange() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 4.0);
        assertEquals("When value is inside the range, the range should remain the same.", 2.0, result.getLowerBound(), 0.0001);
        assertEquals(6.0, result.getUpperBound(), 0.0001);
    }

    // Test expandToInclude with range null and value outside the range
    @Test
    public void testExpandToInclude_NullRange_ValueOutside() {
        Range range = null;
        Range result = Range.expandToInclude(range, 10.0);
        assertEquals("When range is null, the range should be created with the given value as both lower and upper bounds.", 10.0, result.getLowerBound(), 0.0001);
        assertEquals(10.0, result.getUpperBound(), 0.0001);
    }
}
