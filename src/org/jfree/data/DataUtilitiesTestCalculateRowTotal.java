package org.jfree.data;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;

public class DataUtilitiesTestCalculateRowTotal {

    private Mockery context;
    private Values2D values2D;

    @Before
    public void setUp() {
        context = new Mockery();
        values2D = context.mock(Values2D.class);
    }

    // Tests contains valid Values2D object with multiple columns
    @Test
    public void testCalculateRowTotal_MultipleColumns() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount();
            will(returnValue(2));

            allowing(values2D).getValue(0, 0);
            will(returnValue(2.0));

            allowing(values2D).getValue(0, 1);
            will(returnValue(3.0));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 0);
        assertEquals(5.0, result, 0.0001); // Expected: 2.0 + 3.0 = 5.0
    }

    // Test contains valid Values2D object with zero columns
    @Test
    public void testCalculateRowTotal_ZeroColumns() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount();
            will(returnValue(0));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 1);
        assertEquals(0.0, result, 0.0001); // No columns, so sum is 0
    }

    // Test contains row index at the lower boundary (0)
    @Test
    public void testCalculateRowTotal_LowerBoundaryRowIndex() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount();
            will(returnValue(2));

            allowing(values2D).getValue(0, 0);
            will(returnValue(5.0));

            allowing(values2D).getValue(0, 1);
            will(returnValue(7.0));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 0);
        assertEquals(12.0, result, 0.0001); // 5.0 + 7.0 = 12.0
    }
    
    // Test contains row index at the upper boundary (number of rows - 1)
    @Test
    public void testCalculateRowTotal_UpperBoundaryRowIndex() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(3)); // Assume 3 rows (index 0, 1, 2)

            allowing(values2D).getColumnCount();
            will(returnValue(2));

            allowing(values2D).getValue(2, 0);
            will(returnValue(8.0));

            allowing(values2D).getValue(2, 1);
            will(returnValue(6.0));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 2);
        assertEquals(14.0, result, 0.0001); // 8.0 + 6.0 = 14.0
    }
    
    // Test with multiple columns, all validCols in range
    @Test
    public void testCalculateRowTotal_AllValidCols() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(3));
            allowing(values2D).getValue(0, 0); will(returnValue(2.0));
            allowing(values2D).getValue(0, 1); will(returnValue(3.0));
            allowing(values2D).getValue(0, 2); will(returnValue(4.0));
        }});

        int[] validCols = {0, 1, 2};
        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
        assertEquals(9.0, result, 0.0001);
    }

    // Test with some validCols out of range
    @Test
    public void testCalculateRowTotal_SomeInvalidCols() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(2));
            allowing(values2D).getValue(0, 0); will(returnValue(1.0));
            allowing(values2D).getValue(0, 1); will(returnValue(2.0));
            // No value expected for column index 3 (out of range)
        }});

        int[] validCols = {0, 1, 3};  // 3 is out of range, should be ignored
        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
        assertEquals(3.0, result, 0.0001);
    }

    // Test with validCols containing only out-of-range indices
    @Test
    public void testCalculateRowTotal_AllColsOutOfRange() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(2));
        }});

        int[] validCols = {5, 6}; // all out of range
        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
        assertEquals(0.0, result, 0.0001);
    }

    // Test with null values in validCols
    @Test
    public void testCalculateRowTotal_WithNullValues() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(2));
            allowing(values2D).getValue(0, 0); will(returnValue(null));
            allowing(values2D).getValue(0, 1); will(returnValue(3.0));
        }});

        int[] validCols = {0, 1};
        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
        assertEquals(3.0, result, 0.0001); // null value should be skipped
    }

    // Test with empty validCols array
    @Test
    public void testCalculateRowTotal_EmptyValidCols() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(2));
        }});

        int[] validCols = {};
        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
        assertEquals(0.0, result, 0.0001);
    }

    // Test with validCols = null (should throw NullPointerException)
    @Test(expected = NullPointerException.class)
    public void testCalculateRowTotal_ValidColsNull() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount(); will(returnValue(2));
        }});

        DataUtilities.calculateRowTotal(values2D, 0, null);
    }

    // Test with null Values2D (should throw IllegalArgumentException due to ParamChecks)
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotal_Values2DNull() {
        DataUtilities.calculateRowTotal(null, 0, new int[]{0, 1});
    }
    
    @Test
    public void testCalculateRowTotal_ColCountNegative() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount();
            will(returnValue(-1));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 0, new int[] {0, 1});
        assertEquals(0.0, result, 0.0001); // Should still return 0
    }
    
    @Test
    public void testCalculateRowTotal_NegativeColumnCountToForceSecondLoop() {
        context.checking(new Expectations() {{
            allowing(values2D).getColumnCount();
            will(returnValue(-1));  // forces second loop to run

            allowing(values2D).getValue(0, 0);
            will(returnValue(2.0)); // won't be used in first loop

            // Needed for second loop to run at least once
            allowing(values2D).getValue(0, 0);  // c2 = 0
            will(returnValue(5.0));
        }});

        double result = DataUtilities.calculateRowTotal(values2D, 0);
        assertEquals(5.0, result, 0.0001);
    }

}

