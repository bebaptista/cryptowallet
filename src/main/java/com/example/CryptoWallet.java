package com.example;

import java.util.List;

public class CryptoWallet implements Runnable {
    
    List<Asset> assets;

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public void printAssets(){
        for(Asset asset : getAssets()) {
            System.out.println(asset.getSymbol());
            System.out.println(asset.getOriginalprice());
            System.out.println(asset.getCurrentprice());
         }
    }

    double getTotal() {
        return 1;
    }

    String getBestAsset(){
        return "ABC";
    }

    String getWorstAsset(){
        return "ABC";
    }

    double getBestPerformance(){
        return 1;
    }

    double getWorstPerformance(){
        return 1;
    }

    public void run() {
        for(Asset asset : getAssets()) {
            asset.requestPrice();
         }
    }
}
