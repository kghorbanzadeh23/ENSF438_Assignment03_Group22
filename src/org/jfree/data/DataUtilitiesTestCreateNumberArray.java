package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesTestCreateNumberArray {

	// Test contains valid double array with multiple elements
    @Test
    public void testCreateNumberArray_MultipleElements() {
        double[] data = {1.0, 2.0, 3.0}; // Adjusted to match test case exactly
        Number[] expected = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(data);

        assertArrayEquals(expected, result);
    }

    // Test contains valid double array with zero elements
    @Test
    public void testCreateNumberArray_ZeroElements() {
        double[] data = {}; // Empty array
        Number[] expected = {};

        Number[] result = DataUtilities.createNumberArray(data);

        assertArrayEquals(expected, result);
    }

    // Test contains invalid double array (null) - should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray_NullArray() {
        DataUtilities.createNumberArray(null);
    }

    // Test contains array length at the lower boundary (0 elements)
    @Test
    public void testCreateNumberArray_LowerBoundary() {
        double[] data = new double[0]; // Empty array
        Number[] expected = new Number[0];

        Number[] result = DataUtilities.createNumberArray(data);

        assertArrayEquals(expected, result);
    }

    // Test contains array length at the upper boundary (large array size)
    @Test
    public void testCreateNumberArray_UpperBoundary() {
        int maxSize = 100000; // Choose a large but reasonable size
        double[] data = new double[maxSize];
        Number[] expected = new Number[maxSize];

        for (int i = 0; i < maxSize; i++) {
            data[i] = i * 1.0;
            expected[i] = i * 1.0;
        }

        Number[] result = DataUtilities.createNumberArray(data);

        assertArrayEquals(expected, result);
    }
}
