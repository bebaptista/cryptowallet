package com.example;

import java.util.ArrayList;
import java.util.List;

public class CryptoWallet {

    List<Asset> assets;

    public CryptoWallet(){
        this.assets = new ArrayList<Asset>();
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public double getTotal() {
        double total = 0;
        for (Asset asset : getAssets()) {
            total += asset.getCurrentPosition();
        }
        return total;
    }

    public Asset getBestAsset() {
        double bestPerformance = 0;
        Asset bestAsset = null;
        for (Asset asset : getAssets()) {
            if (asset.getPerformance() > bestPerformance) {
                bestPerformance = asset.getPerformance();
                bestAsset = asset;
            }
        }
        return bestAsset;
    }

    public Asset getWorstAsset() {
        double worstPerformance = getAssets().get(0).getPerformance();
        Asset worstAsset = getAssets().get(0);
        for (Asset asset : getAssets()) {
            if (asset.getPerformance() < worstPerformance) {
                worstPerformance = asset.getPerformance();
                worstAsset = asset;
            }
        }
        return worstAsset;
    }
}
