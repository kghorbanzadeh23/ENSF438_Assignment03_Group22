package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;
import org.junit.Test;

public class DataUtilitiesTestGetCumulativePercentages {
	
    // Test contains valid KeyedValues object with multiple keys
    @Test
    public void testGetCumulativePercentages_MultipleKeys() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.setValue("A", 2.0);
        data.setValue("B", 3.0);
        data.setValue("C", 5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        //Access values using key names instead of indices
        assertEquals(0.2, result.getValue("A").doubleValue(), 0.0001); // 2/10
        assertEquals(0.5, result.getValue("B").doubleValue(), 0.0001); // (2+3)/10
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.0001); // (2+3+5)/10
    }

    // Test contains valid KeyedValues object with zero keys (lower boundary)
    @Test
    public void testGetCumulativePercentages_ZeroKeys() {
        DefaultKeyedValues data = new DefaultKeyedValues(); // Empty dataset

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0, result.getItemCount()); // Expecting an empty result set
    }

    // Test contains invalid KeyedValues object (null)
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_NullKeyedValues() {
        DataUtilities.getCumulativePercentages(null);
    }

    // Test contains number of keys at the lower boundary (1 key)
    @Test
    public void testGetCumulativePercentages_LowerBoundary() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.setValue("X", 4.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue("X").doubleValue(), 0.0001); // 4.0 is 100%
    }

    // Test contains large dataset at upper boundary
    @Test
    public void testGetCumulativePercentages_UpperBoundary() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        int maxKeys = 10000; // Large number of keys

        for (int i = 1; i <= maxKeys; i++) {
            data.setValue(String.valueOf(i), 1.0); // Convert int to String to avoid ambiguity
        }

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        // Verify cumulative percentage at the last key
        assertEquals(1.0, result.getValue(String.valueOf(maxKeys)).doubleValue(), 0.0001);

        // Verify cumulative percentage at halfway point
        assertEquals(0.5, result.getValue(String.valueOf(maxKeys / 2)).doubleValue(), 0.0001);
    }
}
