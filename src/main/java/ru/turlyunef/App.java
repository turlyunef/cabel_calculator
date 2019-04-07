package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Start the program");
        Data data = new CableMagazineData();
        ArrayList<Data> allData;
        allData = FileConverter.convertFileToDataArrays("Resources/in.csv", data);
        ArrayList<Data> result = Calculator.calculateCableMagazineData(allData, false);
        File outputFile = new File(DataSaver.OUTPUT_FILE_NAME);
        outputFile.delete();
        DataSaver.saveSumResultToFile(result);
        ArrayList<Data> resultForVOR = Calculator.calculateCableMagazineData(allData, true);
        DataSaver.saveVORResultToFile(resultForVOR);
        log.info("End the program");
    }
}