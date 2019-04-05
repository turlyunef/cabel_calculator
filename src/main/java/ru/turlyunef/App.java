package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Start the program");
        Data data = new CableMagazineData();
        ArrayList<Data> allData;
        allData = FileConverter.convertFileToArrays("Resources/in.csv", data);

        log.info("End the program");
    }
}
