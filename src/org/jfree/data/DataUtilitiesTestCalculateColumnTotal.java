package org.jfree.data;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;

public class DataUtilitiesTestCalculateColumnTotal {

    private Mockery context;
    private Values2D values2D;

    @Before
    public void setUp() {
        context = new Mockery();
        values2D = context.mock(Values2D.class);
    }

    // Test contains multiple rows, summing column correctly
    @Test
    public void testCalculateColumnTotal_ValidColumn() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(3));

            allowing(values2D).getValue(0, 0);
            will(returnValue(1.5));

            allowing(values2D).getValue(1, 0);
            will(returnValue(2.5));

            allowing(values2D).getValue(2, 0);
            will(returnValue(3.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals(7.0, result, 0.0001); // 1.5 + 2.5 + 3.0 = 7.0
    }

    // Test contains with no rows in Values2D (should return 0)
    @Test
    public void testCalculateColumnTotal_EmptyData() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(0));
        }});

        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals(0.0, result, 0.0001); // No rows, so sum is 0
    }


    // Test contains handling null values in the column (should ignore nulls)
    @Test
    public void testCalculateColumnTotal_WithNullValues() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(3));

            allowing(values2D).getValue(0, 0);
            will(returnValue(null)); // Should be ignored

            allowing(values2D).getValue(1, 0);
            will(returnValue(4.0));

            allowing(values2D).getValue(2, 0);
            will(returnValue(6.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals(10.0, result, 0.0001); // Null ignored, so 4.0 + 6.0 = 10.0
    }

    // Test contains out-of-bounds column index (should throw IndexOutOfBoundsException)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotal_InvalidColumnIndex() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(3));

            allowing(values2D).getValue(0, 5);
            will(throwException(new IndexOutOfBoundsException()));

            allowing(values2D).getValue(1, 5);
            will(throwException(new IndexOutOfBoundsException()));

            allowing(values2D).getValue(2, 5);
            will(throwException(new IndexOutOfBoundsException()));
        }});

        DataUtilities.calculateColumnTotal(values2D, 5);
    }
    
    // Test valid rows in range, summing selected rows in a column
    @Test
    public void testCalculateColumnTotal_ValidRowsInRange() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(3));
            allowing(values2D).getValue(0, 1); will(returnValue(1.0));
            allowing(values2D).getValue(2, 1); will(returnValue(4.0));
        }});

        int[] validRows = {0, 2}; // Only rows 0 and 2 included
        double result = DataUtilities.calculateColumnTotal(values2D, 1, validRows);
        assertEquals(5.0, result, 0.0001); // 1.0 + 4.0
    }

    // Test with some validRows out of range
    @Test
    public void testCalculateColumnTotal_SomeInvalidRows() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(2));
            allowing(values2D).getValue(0, 1); will(returnValue(2.5));
            allowing(values2D).getValue(1, 1); will(returnValue(3.5));
            // Row 3 is out of bounds, should be ignored
        }});

        int[] validRows = {0, 1, 3}; // Row 3 is invalid
        double result = DataUtilities.calculateColumnTotal(values2D, 1, validRows);
        assertEquals(6.0, result, 0.0001);
    }

    // Test with all validRows out of range
    @Test
    public void testCalculateColumnTotal_AllInvalidRows() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(2));
        }});

        int[] validRows = {5, 6};
        double result = DataUtilities.calculateColumnTotal(values2D, 1, validRows);
        assertEquals(0.0, result, 0.0001);
    }

    // Test with null values in selected rows (should be skipped)
    @Test
    public void testCalculateColumnTotal_WithNullValues1() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(3));
            allowing(values2D).getValue(0, 0); will(returnValue(null));
            allowing(values2D).getValue(1, 0); will(returnValue(5.0));
        }});

        int[] validRows = {0, 1};
        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
        assertEquals(5.0, result, 0.0001); // null ignored
    }

    // Test with empty validRows array
    @Test
    public void testCalculateColumnTotal_EmptyValidRows() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(2));
        }});

        int[] validRows = {};
        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
        assertEquals(0.0, result, 0.0001);
    }

    // Test with validRows = null (should throw NullPointerException)
    @Test(expected = NullPointerException.class)
    public void testCalculateColumnTotal_ValidRowsNull() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount(); will(returnValue(3));
        }});

        DataUtilities.calculateColumnTotal(values2D, 0, null);
    }

    // Test with Values2D = null (should throw IllegalArgumentException)
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotal_Values2DNull() {
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1});
    }
    
    // Artificial test to hit total > 0 and trigger total = 100
    @Test
    public void testCalculateColumnTotal_ForceTotalEquals100Branch() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(2));

            allowing(values2D).getValue(0, 0);
            will(returnValue(10.0));

            allowing(values2D).getValue(1, 0);
            will(returnValue(20.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values2D, 0, new int[]{0, 1});
        assertEquals(100.0, result, 0.0001); // Forced by logic to return 100 if total > 0
    }
    
    @Test
    public void testCalculateColumnTotal_NegativeRowCountToForceSecondLoop() {
        context.checking(new Expectations() {{
            allowing(values2D).getRowCount();
            will(returnValue(-1));  // forces second for loop to run

            // Force the second loop to execute at r2=0
            allowing(values2D).getValue(0, 0);
            will(returnValue(10.0)); // only used by second loop
        }});

        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals(10.0, result, 0.0001);
    }
    
    
}
