package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestConstrain {

    // Test constrain with a value inside the range
    @Test
    public void testConstrain_ValueInsideRange() {
        Range range = new Range(2.0, 6.0);
        double value = 4.0;
        double result = range.constrain(value);
        assertEquals("Value inside the range should remain unchanged", value, result, 0.0001);
    }

    // Test constrain with a value below the range's lower bound
    @Test
    public void testConstrain_ValueBelowRange() {
        Range range = new Range(2.0, 6.0);
        double value = 1.0;
        double result = range.constrain(value);
        assertEquals("Value below the range should be constrained to the lower bound", 2.0, result, 0.0001);
    }

    // Test constrain with a value above the range's upper bound
    @Test
    public void testConstrain_ValueAboveRange() {
        Range range = new Range(2.0, 6.0);
        double value = 7.0;
        double result = range.constrain(value);
        assertEquals("Value above the range should be constrained to the upper bound", 6.0, result, 0.0001);
    }

    // Test constrain with a value equal to the lower bound
    @Test
    public void testConstrain_ValueEqualToLowerBound() {
        Range range = new Range(2.0, 6.0);
        double value = 2.0;
        double result = range.constrain(value);
        assertEquals("Value equal to the lower bound should remain unchanged", value, result, 0.0001);
    }

    // Test constrain with a value equal to the upper bound
    @Test
    public void testConstrain_ValueEqualToUpperBound() {
        Range range = new Range(2.0, 6.0);
        double value = 6.0;
        double result = range.constrain(value);
        assertEquals("Value equal to the upper bound should remain unchanged", value, result, 0.0001);
    }

    // Test constrain with a negative range
    @Test
    public void testConstrain_NegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double value = -4.0;
        double result = range.constrain(value);
        assertEquals("Value inside the negative range should remain unchanged", value, result, 0.0001);
    }

    // Test constrain with a value exactly on the boundary of a negative range
    @Test
    public void testConstrain_ValueOnNegativeRangeBoundary() {
        Range range = new Range(-6.0, -2.0);
        double value = -6.0;
        double result = range.constrain(value);
        assertEquals("Value equal to the lower boundary of the negative range should remain unchanged", value, result, 0.0001);
    }

    // Test constrain with a value outside of a negative range
    @Test
    public void testConstrain_ValueOutsideNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double value = -7.0;
        double result = range.constrain(value);
        assertEquals("Value outside the negative range should be constrained to the lower bound", -6.0, result, 0.0001);
    }

    // Test constrain with a value larger than the upper bound of a negative range
    @Test
    public void testConstrain_ValueAboveUpperNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double value = -1.0;
        double result = range.constrain(value);
        assertEquals("Value above the upper boundary of the negative range should be constrained to the upper bound", -2.0, result, 0.0001);
    }
}
