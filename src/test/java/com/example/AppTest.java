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

    @Test
    public void testGetTotal(){
        Asset one = new Asset("ABC", 2.0, 2.0);
        Asset two = new Asset("DEF", 2.0, 2.0);
        Asset three = new Asset("GHI", 2.0, 2.0);
        one.setCurrentPrice(5.0);
        two.setCurrentPrice(5.0);
        three.setCurrentPrice(5.0);
        CryptoWallet wallet = new CryptoWallet();
        wallet.getAssets().add(one);
        wallet.getAssets().add(two);
        wallet.getAssets().add(three);
        assertEquals(30.0, wallet.getTotal(), 0);
    }

    @Test
    public void testGetBestAsset(){
        Asset one = new Asset("ABC", 2.0, 2.0);
        Asset two = new Asset("DEF", 2.0, 2.0);
        Asset three = new Asset("GHI", 2.0, 2.0);
        one.setCurrentPrice(10.0);
        two.setCurrentPrice(20.0);
        three.setCurrentPrice(40.0);
        CryptoWallet wallet = new CryptoWallet();
        wallet.getAssets().add(one);
        wallet.getAssets().add(two);
        wallet.getAssets().add(three);
        assertEquals(three, wallet.getBestAsset());
    }

    @Test
    public void testGetWorstAsset(){
        Asset one = new Asset("ABC", 2.0, 2.0);
        Asset two = new Asset("DEF", 2.0, 2.0);
        Asset three = new Asset("GHI", 2.0, 2.0);
        one.setCurrentPrice(10.0);
        two.setCurrentPrice(20.0);
        three.setCurrentPrice(40.0);
        CryptoWallet wallet = new CryptoWallet();
        wallet.getAssets().add(one);
        wallet.getAssets().add(two);
        wallet.getAssets().add(three);
        assertEquals(one, wallet.getWorstAsset());
    }

    @Test
    public void testRequestPrice(){
        Asset asset = new Asset("ADA", 2.0, 2.0);
        asset.requestPrice();
        assertEquals(1.2165833386393017 , asset.getCurrentPrice(), 0);
    }

}
