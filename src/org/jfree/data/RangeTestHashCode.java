package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestHashCode {

    // Test hashCode for equal ranges
    @Test
    public void testHashCode_EqualRanges() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(2.0, 6.0);
        assertEquals("Hash codes for equal ranges should be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for different ranges (lower bound different)
    @Test
    public void testHashCode_DifferentLowerBounds() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(3.0, 6.0);
        assertNotEquals("Hash codes for ranges with different lower bounds should not be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for different ranges (upper bound different)
    @Test
    public void testHashCode_DifferentUpperBounds() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(2.0, 7.0);
        assertNotEquals("Hash codes for ranges with different upper bounds should not be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for ranges with negative values
    @Test
    public void testHashCode_NegativeValues() {
        Range range1 = new Range(-3.0, -1.0);
        Range range2 = new Range(-3.0, -1.0);
        assertEquals("Hash codes for equal negative ranges should be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for a range with zero values
    @Test
    public void testHashCode_ZeroRange() {
        Range range1 = new Range(0.0, 0.0);
        Range range2 = new Range(0.0, 0.0);
        assertEquals("Hash codes for equal ranges with zero bounds should be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for very large values
    @Test
    public void testHashCode_LargeValues() {
        Range range1 = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        Range range2 = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals("Hash codes for ranges with large values should be the same", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode for very small values
    @Test
    public void testHashCode_SmallValues() {
        Range range1 = new Range(Double.MIN_VALUE, Double.MIN_VALUE);
        Range range2 = new Range(Double.MIN_VALUE, Double.MIN_VALUE);
        assertEquals("Hash codes for ranges with small values should be the same", range1.hashCode(), range2.hashCode());
    }
   
}
