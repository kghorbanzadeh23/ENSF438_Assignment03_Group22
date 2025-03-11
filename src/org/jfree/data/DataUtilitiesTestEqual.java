package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesTestEqual {

    // Both arrays are null (should return true)
    @Test
    public void testEqual_BothArraysNull() {
        assertTrue(DataUtilities.equal(null, null));
    }

    // One array is null, other is not (should return false)
    @Test
    public void testEqual_OneArrayNull() {
        double[][] array = {{1.0, 2.0}};
        assertFalse(DataUtilities.equal(array, null));
        assertFalse(DataUtilities.equal(null, array));
    }

    // Both arrays empty (zero rows) - should return true
    @Test
    public void testEqual_EmptyArrays() {
        double[][] a = new double[0][];
        double[][] b = new double[0][];
        assertTrue(DataUtilities.equal(a, b));
    }

    // Same structure and same values - should return true
    @Test
    public void testEqual_SameValues() {
        double[][] a = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        double[][] b = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        assertTrue(DataUtilities.equal(a, b));
    }

    // Different values - should return false
    @Test
    public void testEqual_DifferentValues() {
        double[][] a = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        double[][] b = {
            {1.0, 2.0},
            {3.0, 5.0}
        };
        assertFalse(DataUtilities.equal(a, b));
    }

    // Same values but different row lengths (jagged array mismatch)
    @Test
    public void testEqual_DifferentRowLengths() {
        double[][] a = {
            {1.0},
            {2.0, 3.0}
        };
        double[][] b = {
            {1.0},
            {2.0}
        };
        assertFalse(DataUtilities.equal(a, b));
    }

    // Different number of rows
    @Test
    public void testEqual_DifferentNumberOfRows() {
        double[][] a = {
            {1.0, 2.0}
        };
        double[][] b = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        assertFalse(DataUtilities.equal(a, b));
    }

    // One array contains a null row while the other does not
    @Test
    public void testEqual_NullRowInOneArray() {
        double[][] a = {
            {1.0, 2.0},
            null
        };
        double[][] b = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        assertFalse(DataUtilities.equal(a, b));
    }

    // Both arrays contain null rows in the same places
    @Test
    public void testEqual_BothArraysWithNullRowsSamePlace() {
        double[][] a = {
            {1.0, 2.0},
            null
        };
        double[][] b = {
            {1.0, 2.0},
            null
        };
        assertTrue(DataUtilities.equal(a, b));
    }

    // Boundary test: Single element arrays with same value
    @Test
    public void testEqual_SingleElementSameValue() {
        double[][] a = {{42.0}};
        double[][] b = {{42.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    // Boundary test: Single element arrays with different value
    @Test
    public void testEqual_SingleElementDifferentValue() {
        double[][] a = {{42.0}};
        double[][] b = {{99.0}};
        assertFalse(DataUtilities.equal(a, b));
    }
}
