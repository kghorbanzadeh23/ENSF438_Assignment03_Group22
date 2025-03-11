package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTestScale {

    // Test scale with valid range and positive factor
    @Test
    public void testScale_ValidRange_PositiveFactor() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.scale(base, 2.0);
        assertEquals("Lower bound should be scaled by factor", 4.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled by factor", 12.0, result.getUpperBound(), 0.0001);
    }

    // Test scale with valid range and factor of 1 (no scaling)
    @Test
    public void testScale_ValidRange_FactorOne() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.scale(base, 1.0);
        assertEquals("Scaling by 1 should not change the range", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Scaling by 1 should not change the range", 6.0, result.getUpperBound(), 0.0001);
    }

    // Test scale with valid range and factor of 0 (shrinks to zero range)
    @Test
    public void testScale_ValidRange_ZeroFactor() {
        Range base = new Range(2.0, 6.0);
        Range result = Range.scale(base, 0.0);
        assertEquals("Lower bound should be scaled to zero", 0.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled to zero", 0.0, result.getUpperBound(), 0.0001);
    }

    // Test scale with valid range and negative factor (throws exception)
    @Test(expected = IllegalArgumentException.class)
    public void testScale_ValidRange_NegativeFactor() {
        Range base = new Range(2.0, 6.0);
        Range.scale(base, -2.0); // Should throw IllegalArgumentException
    }

    // Test scale with range that has negative values and positive factor
    @Test
    public void testScale_NegativeValues_PositiveFactor() {
        Range base = new Range(-4.0, -2.0);
        Range result = Range.scale(base, 3.0);
        assertEquals("Lower bound should be scaled by factor", -12.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled by factor", -6.0, result.getUpperBound(), 0.0001);
    }

    // Test scale with range that has negative values and factor of 0
    @Test
    public void testScale_NegativeValues_ZeroFactor() {
        Range base = new Range(-4.0, -2.0);
        Range result = Range.scale(base, 0.0);
        assertEquals("Lower bound should be scaled to zero", 0.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled to zero", 0.0, result.getUpperBound(), 0.0001);
    }

    // Test scale with very large factor
    @Test
    public void testScale_LargeFactor() {
        Range base = new Range(1.0, 3.0);
        double largeFactor = Double.MAX_VALUE;
        Range result = Range.scale(base, largeFactor);
        assertEquals("Lower bound should be scaled by large factor", 1.0 * largeFactor, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled by large factor", 3.0 * largeFactor, result.getUpperBound(), 0.0001);
    }

    // Test scale with very small factor
    @Test
    public void testScale_SmallFactor() {
        Range base = new Range(2.0, 6.0);
        double smallFactor = Double.MIN_VALUE;
        Range result = Range.scale(base, smallFactor);
        assertEquals("Lower bound should be scaled by small factor", 2.0 * smallFactor, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be scaled by small factor", 6.0 * smallFactor, result.getUpperBound(), 0.0001);
    }
}
