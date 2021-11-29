package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testRoundPriceHalfUp(){
        double result = App.roundPriceHalfUp(5.678);
        assertEquals(5.68, result, 0);
    }

    @Test
    public void testGetPerformance(){
        Asset asset = new Asset("ABC", 2.0, 5.5555);
        asset.setCurrentPrice(10.5555);
        double result = 10.5555/5.5555;
        assertEquals(asset.getPerformance(), result, 0);
    }

    @Test
    public void testGetCurrentPosition(){
        Asset asset = new Asset("ABC", 2.0, 5.5555);
        asset.setCurrentPrice(10.5555);
        double result = 10.5555*2;
        assertEquals(asset.getCurrentPosition(), result, 0);
    }

}
