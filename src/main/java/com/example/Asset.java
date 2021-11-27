package com.example;

import java.io.IOException;
import java.time.LocalTime;

import com.opencsv.bean.CsvBindByName;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Asset {

    @CsvBindByName(column = "symbol")
    String symbol;

    @CsvBindByName(column = "quantity")
    double quantity;

    @CsvBindByName(column = "price")
    double originalprice;

    double currentprice;

    public Asset(String symbol, double quantity, double originalprice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.originalprice = originalprice;
    }

    public Asset() {
        this.symbol = "AAA";
        this.quantity = 0;
        this.originalprice = 0;
    }

    public double getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(double originalprice) {
        this.originalprice = originalprice;
    }

    public double getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(double currentprice) {
        this.currentprice = currentprice;
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
            String url = "https://api.coincap.io/v2/assets/" + getAssetIdFromSymbol(getSymbol()) + "/history?interval=d1&start=1617753600000&end=1617753601000";
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .header("Authorization", "Bearer 86743a2a-9b0b-43c5-890d-a74aede04b69")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            JSONObject data = new JSONObject(json.getJSONArray("data").get(0).toString());
            setCurrentprice(data.getDouble("priceUsd"));
            System.out.println(getSymbol());
            System.out.println(getOriginalprice());
            System.out.println(getCurrentprice());
        } catch (IOException e) {
            System.out.print("Error in request");
        }

    }

    public String getAssetIdFromSymbol(String symbol){
        switch (symbol){
            case "BTC": return "bitcoin";
            case "ETH": return "ethereum";
            case "XRP": return "ripple";
            case "BCH": return "bitcoin-cash";
            case "EOS": return "eos";
            case "XLM": return "stellar";
            case "LTC": return "Litecoin";
            case "ADA": return "cardano";
            case "USDT": return "tether";
            case "MIOTA": return "iota";
        }
        return "unknown";
    }

}