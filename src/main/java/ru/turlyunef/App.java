package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Start the program");
        File myFolder = new File(".");
        File[] files = myFolder.listFiles();
        for (File x: files
             ) {
            if (x.getPath().contains(".csv")){
                try {
                    log.info("Calculating file: " + x.getPath());
                    ArrayList<Data> allData;
                    allData = FileConverter.convertFileToDataArrays(x.getPath(), new CableMagazineData());
                    ArrayList<Data> result = Calculator.calculateCableMagazineData(allData, false);
                    String[] names = x.getPath().split(".csv");
                    File outputFile = new File(names[names.length-1] + "_result.txt");
                    outputFile.delete();
                    DataSaver.saveSumResultToFile(outputFile.getName(), result);
                    ArrayList<Data> resultForVOR = Calculator.calculateCableMagazineData(allData, true);
                    DataSaver.saveVORResultToFile(outputFile.getName(), resultForVOR);
                }
                catch (NullPointerException e){
                    log.error("Error of calculating file: " + x.getPath());
                }
            }
        }
        log.info("End the program");
    }
}