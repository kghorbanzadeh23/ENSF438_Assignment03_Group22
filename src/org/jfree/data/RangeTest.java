package org.jfree.data;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeTest {
	
	//This test is for getting the length of the range between two positive integers. Expected length = 5
	@Test 
   	public void getLength_positiveIntegers() {
        Range range= new Range(31,36);
    	double result= range.getLength();
    	assertEquals("Length between 31 and 36 is",  5, result, .000000001d);
    }

    //This test is for getting the length of the range between a negative and positive integer. Expected length = 200
	@Test 
    public void getLength_negativeAndPositiveIntegers() {
     Range range= new Range(-100,100);
     double result= range.getLength();
     assertEquals("Length between -100 and 100 is",  200, result, .000000001d);
 }

    //This test is for getting the length of the range between a negative integer and positive double. Expected length = 440.5
    @Test 
    public void getLength_negativeAndPositiveDoubles() {
    Range range= new Range(-28,440.5);
    double result= range.getLength();
    assertEquals("Length between -28 and 440.5 is",  468.5, result, .000000001d);
    }

    //This test is for getting the length of the range between a large negative and large positive. Expected length = 2000000
    @Test 
    public void getLength_largeNegativeAndPositive() {
    Range range= new Range(-1000000,1000000);
    double result= range.getLength();
    assertEquals("Length between -1000000 and 1000000 is",  2000000, result, .000000001d);
    }
    
    //This test is for getting the length of 0. Expected length = 0
    @Test 
    public void getLength_zero() {
    Range range= new Range(2,2);
    double result= range.getLength();
    assertEquals("Length between 2 and 2 is",  0, result, .000000001d);
    }

    @Test
    public void getLength_invalid() {
        try {
            // Create a Range object where lower > upper
            Range brokenRange = new Range(1.0, -1.0);  // This should throw an IllegalArgumentException
            // Try to get the length, which should throw an exception
            brokenRange.getLength();
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected IllegalArgumentException: " + e.getMessage());
        }
    }
    
    @Test
    public void getCentralValue_zero() {
    	Range range= new Range(-1,1);
        double result= range.getCentralValue();
    	assertEquals("The central value between -1 and 1 is", 0, result, .000000001d);
    	
    }
    
    //This test will check the min and max functions through the combineIgnoringNaN() function.
    @Test
	public void minAndMax_combineIgnoringNaN() {
		double NaN1 = Math.sqrt(-200); // first not-a-number 
		double NaN2 = Math.sqrt(-8); //second not-a-number
		Range range = new Range(0, 8); // valid test range
		Range result = Range.combineIgnoringNaN(range, new Range(NaN1, NaN2)); // will call min and max functions
		assertEquals("The lower bound is", result.getLowerBound(), 0, .000000001d);
		
	}

    @Test
	public void expand_marginsWithDecimals() { 
		Range range = new Range(2, 6); 
		Range expandedRange = Range.expand(range, 0.25, 0.5); 

		assertEquals("The lower margin range is", 1, expandedRange.getLowerBound(), .000000001d);
		assertEquals("The upper margin range is", 8, expandedRange.getUpperBound(), .000000001d);
		
    }
    
    @Test
	public void expand_NegativeRange() {
    	Range range = new Range(-6, -2); 
    	Range expandedRange = Range.expand(range, 0.44, 0.33);
		
    	assertEquals("The upper margin range is", -0.68, expandedRange.getUpperBound(), .000000001d);
		
		
	}
    
    @Test
	public void expand_NegativeMargins() {
    	Range range = new Range(-6, -2); 
    	Range expandedRange = Range.expand(range, -0.29, -0.35);
		
    	assertEquals("The upper margin range is", -3.4, expandedRange.getUpperBound(), .000000001d);
		
		
	}
    
    // This test verifies that the combine function works as expected with vaid ranges
    @Test
    public void combine_valid(){
      Range range1 = new Range(-5,0);
      Range range2 = new Range(0,5);
      Range range = Range.combine(range1, range2);
      assertEquals("The lower range is", -5, range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", 5, range.getUpperBound(), .000000001d);
    }

    // This test verifies that the combine function returns the second valid range if the first range is null
    @Test
    public void combine_range1Null(){
      Range range1 = null;
      Range range2 = new Range(0,5);
      Range range = Range.combine(range1, range2);
      assertEquals("The lower range is", range2.getLowerBound(), range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", range2.getUpperBound(), range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the combine function returns the first valid range if the second range is null
    @Test
    public void combine_range2Null(){
      Range range1 = new Range(0,5);
      Range range2 = null;
      Range range = Range.combine(range1, range2);
      assertEquals("The lower range is", range1.getLowerBound(), range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", range1.getUpperBound(), range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the combineIgnoringNaN function returns the second valid range if the first range is null
    @Test 
    public void combineIgnoringNaN_range1Null(){
      Range range1 = null;
      Range range2 = new Range(0,5);
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertEquals("The lower range is", range2.getLowerBound(), range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", range2.getUpperBound(), range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the combineIgnoringNaN function returns null if the first range is null and the second range contains NaN's
    @Test 
    public void combineIgnoringNaN_range1Null_range2NaN(){
      Range range1 = null;
      Range range2 = new Range(Double.NaN,Double.NaN);
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertNull(range);
    }
    
    // This test verifies that the combineIgnoringNaN function returns the first valid range if the second range is null
    @Test
    public void combineIgnoringNaN_range2Null(){
      Range range1 = new Range(0,5);
      Range range2 = null;
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertEquals("The lower range is", range1.getLowerBound(), range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", range1.getUpperBound(), range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the combineIgnoringNaN function returns null if the second range is null and the first range contains NaN's
    @Test 
    public void combineIgnoringNaN_range1NaN_range2Null(){
      Range range1 = new Range(Double.NaN,Double.NaN);
      Range range2 = null;
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertNull(range);
    }

    // This test verifies that the combineIgnoringNaN function returns null if the both ranges are null
    @Test
    public void combineIgnoringNaN_bothNull(){
      Range range1 = null;
      Range range2 = null;
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertNull(range);
    }
    
    // This test verifies that the combineIgnoringNaN function returns null if the both ranges have NaN's
    @Test
    public void combineIgnoringNaN_bothNaN(){
      Range range1 = new Range(Double.NaN,Double.NaN);
      Range range2 = new Range(Double.NaN,Double.NaN);
      Range range = Range.combineIgnoringNaN(range1, range2);
      assertNull(range);
    }
    
    // This test verifies that the scale function works with positive factors 
    @Test 
    public void scale_positiveFactor(){
      Range range1 = new Range(-10,10);
      Range range = Range.scale(range1,1.1);
      assertEquals("The lower range is", -11, range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", 11, range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the scale function raises IllegalArgumentException if a negative factor is given 
    @Test(expected=IllegalArgumentException.class)
    public void scale_negativeFactor(){
      Range range1 = new Range(-10,10);
      Range range = Range.scale(range1,-1);
    }

     // This test verifies that the Range constructor works as intended with a valid range
    @Test 
    public void Range_valid(){
      Range range = new Range(-10,10);
      assertEquals("The lower range is", -10, range.getLowerBound(), .000000001d);
      assertEquals("The upper range is", 10, range.getUpperBound(), .000000001d);
    }
    
    // This test verifies that the Range constructor raises IllegalArgumentException if a invalid range is given 
    @Test(expected=IllegalArgumentException.class)
    public void Range_invalid(){
      Range range = new Range(10,-10);
    }
    
    // This test verifies that the hashCode function works as intended with a valid range
    @Test
    public void hashCode_valid(){
      Range range = new Range(-10,10);
      int result = range.hashCode();
      assertEquals("The hashcode is", 7.077888E7, result, .000000001d);
    }
    
    // This test verifies that the hashCode function still works with NaN ranges
    @Test
    public void hashCode_NaN(){
        Range range = new Range(Double.NaN,Double.NaN);
        int result = range.hashCode();
        assertEquals("The hashcode is", -1.572864E7, result, .000000001d);
      }

    // getLowerBound() tests
    @Test
    public void lowerBoundShouldBeMinusOne() {
        Range exampleRange = new Range(-1, 1);
        assertEquals("Lower bound should be -1", -1, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundShouldBeOne() {
        Range exampleRange = new Range(1, 500);
        assertEquals("Lower bound should be 1", 1, exampleRange.getLowerBound(), .000000001d);
    }

    // getUpperBound() tests
    @Test
    public void upperBoundShouldBeMinusOne() {
        Range exampleRange = new Range(-240, -1);
        assertEquals("Upper bound should be -1", -1, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeOne() {
        Range exampleRange = new Range(-100, 1);
        assertEquals("Upper bound should be 1", 1, exampleRange.getUpperBound(), .000000001d);
    }
 
    // constrain() tests

    @Test
    public void constrainedInRange() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 0", 0, exampleRange.constrain(0), 0.0001d);
    }

    @Test
    public void constrainedUpperBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 1", 1, exampleRange.constrain(1), 0.0001d);
    }

    @Test
    public void constrainedLowerBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be -1", -1, exampleRange.constrain(-1), 0.0001d);
    }

    @Test
    public void constrainedTowardLowerBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be -1", -1, exampleRange.constrain(-51239.34), 0.0001d);
    }

    @Test
    public void constrainedTowardUpperBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 1", 1, exampleRange.constrain(51239.34), 0.0001d);
    }
    
    @Test
    public void testShiftWithNoZeroCrossing_positiveValuePositiveDelta() {
        double result = Range.shiftWithNoZeroCrossing(5.0, 3.0);
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_positiveValueNegativeDelta() {
        double result = Range.shiftWithNoZeroCrossing(5.0, -3.0);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_negativeValuePositiveDelta() {
        double result = Range.shiftWithNoZeroCrossing(-5.0, 3.0);
        assertEquals(-2.0, result, 0.0001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_negativeValueNegativeDelta() {
        double result = Range.shiftWithNoZeroCrossing(-5.0, -3.0);
        assertEquals(-8.0, result, 0.0001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_zeroValuePositiveDelta() {
        double result = Range.shiftWithNoZeroCrossing(0.0, 3.0);
        assertEquals(3.0, result, 0.0001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_zeroValueNegativeDelta() {
        double result = Range.shiftWithNoZeroCrossing(0.0, -3.0);
        assertEquals(-3.0, result, 0.0001);
    }
}

