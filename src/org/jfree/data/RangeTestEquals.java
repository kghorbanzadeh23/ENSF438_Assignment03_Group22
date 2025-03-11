package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestEquals {

    // Test equals with two identical ranges
    @Test
    public void testEquals_IdenticalRanges() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(2.0, 6.0);
        assertTrue("Ranges with identical lower and upper bounds should be equal", range1.equals(range2));
    }

    // Test equals with different lower bounds
    @Test
    public void testEquals_DifferentLowerBounds() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(3.0, 6.0);
        assertFalse("Ranges with different lower bounds should not be equal", range1.equals(range2));
    }

    // Test equals with different upper bounds
    @Test
    public void testEquals_DifferentUpperBounds() {
        Range range1 = new Range(2.0, 6.0);
        Range range2 = new Range(2.0, 7.0);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }

    // Test equals with a null object
    @Test
    public void testEquals_NullObject() {
        Range range = new Range(2.0, 6.0);
        assertFalse("The range should not be equal to null", range.equals(null));
    }

    // Test equals with an object of a different type
    @Test
    public void testEquals_DifferentObjectType() {
        Range range = new Range(2.0, 6.0);
        String notARange = "Not a Range";
        assertFalse("The range should not be equal to an object of a different type", range.equals(notARange));
    }

    // Test equals with identical but not equal objects (same bounds but different types)
    @Test
    public void testEquals_IdenticalButNotEqualObjects() {
        Object obj1 = new Range(2.0, 6.0);
        Object obj2 = new Range(2.0, 6.0);
        assertTrue("Identical ranges should be equal", obj1.equals(obj2));
    }

    // Test equals with different values, including negative bounds
    @Test
    public void testEquals_DifferentValues_NegativeBounds() {
        Range range1 = new Range(-6.0, -2.0);
        Range range2 = new Range(-6.0, -3.0);
        assertFalse("Ranges with different negative upper bounds should not be equal", range1.equals(range2));
    }

    // Test equals with identical ranges with negative bounds
    @Test
    public void testEquals_IdenticalRanges_NegativeBounds() {
        Range range1 = new Range(-6.0, -2.0);
        Range range2 = new Range(-6.0, -2.0);
        assertTrue("Identical negative ranges should be equal", range1.equals(range2));
    }
}
