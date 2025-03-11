package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class DataUtilitiesTestClone {

    // Equivalence Class: Valid 2D array with multiple rows and columns
    @Test
    public void testClone_Valid2DArray_MultipleRowsColumns() {
        double[][] original = {
            {1.0, 2.0},
            {3.0, 4.0},
            {5.0, 6.0}
        };

        double[][] cloned = DataUtilities.clone(original);

        assertNotSame("Cloned object should be a different reference", original, cloned);
        assertArrayEquals("Cloned array content should match", original, cloned);
    }

    // Equivalence Class: Valid 2D array with zero rows (boundary case)
    @Test
    public void testClone_Empty2DArray_ZeroRows() {
        double[][] original = new double[0][];
        double[][] cloned = DataUtilities.clone(original);

        assertNotSame(original, cloned);
        assertEquals(0, cloned.length);
    }

    // Equivalence Class: 2D array with a null row (should not throw exception)
    @Test
    public void testClone_ArrayWithNullRow() {
        double[][] original = {
            {1.0, 2.0},
            null,
            {3.0, 4.0}
        };

        double[][] cloned = DataUtilities.clone(original);

        assertNotSame(original, cloned);
        assertArrayEquals(original[0], cloned[0], 0.0001);
        assertNull(cloned[1]);
        assertArrayEquals(original[2], cloned[2], 0.0001);
    }

    // Boundary Value Test: 2D array with a single element
    @Test
    public void testClone_SingleElementArray() {
        double[][] original = {
            {42.0}
        };

        double[][] cloned = DataUtilities.clone(original);

        assertNotSame(original, cloned);
        assertArrayEquals(original[0], cloned[0], 0.0001);
    }

    // Boundary Value Test: Jagged array (rows of different lengths)
    @Test
    public void testClone_JaggedArray() {
        double[][] original = {
            {1.0},
            {2.0, 3.0},
            {4.0, 5.0, 6.0}
        };

        double[][] cloned = DataUtilities.clone(original);

        assertNotSame(original, cloned);
        for (int i = 0; i < original.length; i++) {
            assertArrayEquals(original[i], cloned[i], 0.0001);
        }
    }
}
