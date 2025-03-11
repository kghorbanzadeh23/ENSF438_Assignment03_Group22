package org.jfree.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class RangeTestIntersects {

    private Range range1;
    private Range range2;

    // Test intersects() with two ranges that fully overlap
    @Test
    public void testIntersects_FullyOverlappingRanges() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(2.0, 4.0);  // Create a Range with lower = 2.0 and upper = 4.0
        boolean result = range1.intersects(range2);
        assertTrue("Ranges [1.0, 5.0] and [2.0, 4.0] fully overlap, should return true", result);
    }

    // Test intersects() with two ranges that partially overlap
    @Test
    public void testIntersects_PartiallyOverlappingRanges() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(4.0, 8.0);  // Create a Range with lower = 4.0 and upper = 8.0
        boolean result = range1.intersects(range2);
        assertTrue("Ranges [1.0, 5.0] and [4.0, 8.0] partially overlap, should return true", result);
    }

    // Test intersects() with two ranges that do not overlap at all
    @Test
    public void testIntersects_NoOverlap() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(6.0, 10.0); // Create a Range with lower = 6.0 and upper = 10.0
        boolean result = range1.intersects(range2);
        assertFalse("Ranges [1.0, 5.0] and [6.0, 10.0] do not overlap, should return false", result);
    }

    // Test intersects() with identical ranges
    @Test
    public void testIntersects_IdenticalRanges() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(1.0, 5.0);  // Create a Range with identical values: lower = 1.0 and upper = 5.0
        boolean result = range1.intersects(range2);
        assertTrue("Ranges [1.0, 5.0] and [1.0, 5.0] are identical, should return true", result);
    }

    // Test intersects() with a range that starts at the same lower bound
    @Test
    public void testIntersects_SameLowerBound() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(1.0, 7.0);  // Create a Range with the same lower bound and upper bound = 7.0
        boolean result = range1.intersects(range2);
        assertTrue("Ranges [1.0, 5.0] and [1.0, 7.0] overlap, should return true", result);
    }

    // Test intersects() with a range that starts before and ends after the current range
    @Test
    public void testIntersects_RangeStartsBeforeAndEndsAfter() {
        range1 = new Range(2.0, 4.0);  // Create a Range with lower = 2.0 and upper = 4.0
        range2 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        boolean result = range1.intersects(range2);
        assertTrue("Ranges [2.0, 4.0] and [1.0, 5.0] overlap, should return true", result);
    }

    // Test intersects() with a range that is exactly after the current range
    @Test
    public void testIntersects_ExactlyAfter() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(5.0, 8.0);  // Create a Range with lower = 5.0 and upper = 8.0 (doesn't overlap)
        boolean result = range1.intersects(range2);
        assertFalse("Ranges [1.0, 5.0] and [5.0, 8.0] are exactly adjacent, should return false", result);
    }

    // Test intersects() with a range that is exactly before the current range
    @Test
    public void testIntersects_ExactlyBefore() {
        range1 = new Range(1.0, 5.0);  // Create a Range with lower = 1.0 and upper = 5.0
        range2 = new Range(-5.0, 1.0); // Create a Range with lower = -5.0 and upper = 1.0 (doesn't overlap)
        boolean result = range1.intersects(range2);
        assertFalse("Ranges [1.0, 5.0] and [-5.0, 1.0] are exactly adjacent, should return false", result);
    }
}
