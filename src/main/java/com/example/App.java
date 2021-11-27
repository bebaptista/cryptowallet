package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        // wallet.printAssets();

        // System.out.println(wallet);
    }
}
