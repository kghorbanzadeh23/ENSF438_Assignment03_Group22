package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestCombine {

    // Test combine with two valid ranges
    @Test
    public void testCombine_TwoValidRanges() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(4.0, 8.0);
        Range result = Range.combine(range1, range2);

        assertNotNull(result);
        assertEquals(2.0, result.getLowerBound(), 0.0001);
        assertEquals(8.0, result.getUpperBound(), 0.0001);
    }

    // Test combine with first range null and second range valid
    @Test
    public void testCombine_FirstRangeNull() {
        Range range1 = null;
        Range range2 = new Range(3.0, 7.0);
        Range result = Range.combine(range1, range2);

        assertNotNull(result);
        assertEquals(3.0, result.getLowerBound(), 0.0001);
        assertEquals(7.0, result.getUpperBound(), 0.0001);
    }

    // Test combine with first range valid and second range null
    @Test
    public void testCombine_SecondRangeNull() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = null;
        Range result = Range.combine(range1, range2);

        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), 0.0001);
        assertEquals(5.0, result.getUpperBound(), 0.0001);
    }

    // Test combine with both ranges null
    @Test
    public void testCombine_BothRangesNull() {
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combine(range1, range2);

        assertNull(result);
    }

    // Test combine with range bounds at the lower boundary
    @Test
    public void testCombine_LowerBoundary() {
        Range range1 = new Range(Double.NEGATIVE_INFINITY, -10.0);
        Range range2 = new Range(-20.0, -5.0);
        Range result = Range.combine(range1, range2);

        assertNotNull(result);
        assertEquals(Double.NEGATIVE_INFINITY, result.getLowerBound(), 0.0001);
        assertEquals(-5.0, result.getUpperBound(), 0.0001);
    }

    // Test combine with range bounds at the upper boundary
    @Test
    public void testCombine_UpperBoundary() {
        Range range1 = new Range(10.0, Double.POSITIVE_INFINITY);
        Range range2 = new Range(5.0, 20.0);
        Range result = Range.combine(range1, range2);

        assertNotNull(result);
        assertEquals(5.0, result.getLowerBound(), 0.0001);
        assertEquals(Double.POSITIVE_INFINITY, result.getUpperBound(), 0.0001);
    }
}
