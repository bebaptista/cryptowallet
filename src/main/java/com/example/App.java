package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class App {
    public static void main(String[] args) throws IOException, CsvException {
        String fileName = "/home/bebaptista/Documents/Genesis/cryptowallet/src/main/resources/Untitled 1.csv";

        CryptoWallet wallet = new CryptoWallet();

        List<Asset> beans = new CsvToBeanBuilder<Asset>(new FileReader(fileName))
                .withType(Asset.class)
                .build()
                .parse();

        wallet.setAssets(beans);

        ExecutorService executorservice = Executors.newFixedThreadPool(3);
        executorservice.submit(wallet);

        executorservice.shutdown();

        try {
            if (!executorservice.awaitTermination(50, TimeUnit.SECONDS)) {
                executorservice.shutdownNow();
            }
            System.out.print("total="+wallet.getTotal());
            System.out.print(", best_asset="+wallet.getBestAsset().getSymbol());
            System.out.print(", best_performance="+wallet.getBestAsset().getPerformance());
            System.out.print(", worst_asset="+wallet.getWorstAsset().getSymbol());
            System.out.print(", worst_performance="+wallet.getWorstAsset().getPerformance());
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        // wallet.printAssets();

        // System.out.println(wallet);
    }
}
