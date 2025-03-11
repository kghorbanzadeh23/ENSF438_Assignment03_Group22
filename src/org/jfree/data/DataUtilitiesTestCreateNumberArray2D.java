package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesTestCreateNumberArray2D {

    // Test contains createNumberArray2D with multiple rows and columns
    @Test
    public void testCreateNumberArray2D_MultipleRowsAndColumns() {
        double[][] data = {
            {1.0, 2.0}, 
            {3.0, 4.0}
        };
        Number[][] expected = {
            {1.0, 2.0}, 
            {3.0, 4.0}
        };

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertArrayEquals(expected, result);
    }

    // Test contains createNumberArray2D with zero rows and columns
    @Test
    public void testCreateNumberArray2D_ZeroRowsAndColumns() {
        double[][] data = {};
        Number[][] expected = {};

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertArrayEquals(expected, result);
    }

    // Test contains createNumberArray2D with invalid array (null)
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_NullArray() {
        DataUtilities.createNumberArray2D(null);
    }

    // Test contains createNumberArray2D with a 2D array containing a null row
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_NullRow() {
        double[][] data = {
            {1.0, 2.0},
            null,  // Invalid row
            {3.0, 4.0}
        };

        DataUtilities.createNumberArray2D(data);
    }

    // Test contains createNumberArray2D with a 2D array containing a null column
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_NullColumn() {
        double[][] data = {
            {1.0, 2.0},
            {3.0, Double.NaN}, // Simulating a null column
            {5.0, 6.0}
        };

        DataUtilities.createNumberArray2D(data);
    }

    // Test contains createNumberArray2D with valid rows and zero columns
    @Test
    public void testCreateNumberArray2D_ValidRowsWithZeroColumns() {
        double[][] data = {
            {}, {}, {}
        };
        Number[][] expected = {
            {}, {}, {}
        };

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertArrayEquals(expected, result);
    }

    // Test contains array dimensions at the lower boundary (0 rows, 0 columns)
    @Test
    public void testCreateNumberArray2D_LowerBoundary() {
        double[][] data = new double[0][0];
        Number[][] expected = new Number[0][0];

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertArrayEquals(expected, result);
    }
    
    // Test contains array dimensions at the upper boundary (maximum size)
    @Test
    public void testCreateNumberArray2D_UpperBoundary() {
        int maxRows = 500;  // Choose a large but reasonable row size
        int maxCols = 500;  // Choose a large but reasonable column size
        double[][] data = new double[maxRows][maxCols];
        Number[][] expected = new Number[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                data[i][j] = i * j * 1.0;
                expected[i][j] = i * j * 1.0;
            }
        }

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertArrayEquals(expected, result);
    }
}
