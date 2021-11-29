package com.example;

import java.io.IOException;
import java.time.LocalTime;

import com.opencsv.bean.CsvBindByName;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Asset implements Runnable {

    @CsvBindByName(column = "symbol")
    String symbol;

    @CsvBindByName(column = "quantity")
    double quantity;

    @CsvBindByName(column = "price")
    double purchasePrice;

    double currentPrice;

    public Asset(String symbol, double quantity, double purchasePrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Asset() {
        this.symbol = "AAA";
        this.quantity = 0;
        this.purchasePrice = 0;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void requestPrice() {
        try {
            System.out.println("Submitted request ASSET_" + getSymbol() + " at " + LocalTime.now());
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            String urlId = "https://api.coincap.io/v2/assets?search=" + getSymbol();
            Request requestId = new Request.Builder()
                    .url(urlId)
                    .method("GET", null)
                    .header("Authorization", "Bearer 86743a2a-9b0b-43c5-890d-a74aede04b69")
                    .build();
            Response responseId = client.newCall(requestId).execute();
            JSONObject jsonId = new JSONObject(responseId.body().string());
            JSONObject dataId = new JSONObject(jsonId.getJSONArray("data").get(0).toString());
            String url = "https://api.coincap.io/v2/assets/" + dataId.get("id")
                    + "/history?interval=d1&start=1617753600000&end=1617753601000";
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .header("Authorization", "Bearer 86743a2a-9b0b-43c5-890d-a74aede04b69")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            JSONObject data = new JSONObject(json.getJSONArray("data").get(0).toString());
            setCurrentPrice(data.getDouble("priceUsd"));
        } catch (IOException e) {
            System.out.print("Error in request");
        }

    }

    public double getCurrentPosition() {
        return getCurrentPrice() * getQuantity();
    }

    public double getPerformance() {
        return getCurrentPrice() / getPurchasePrice();
    }

    public void run() {
        requestPrice();
    }

}