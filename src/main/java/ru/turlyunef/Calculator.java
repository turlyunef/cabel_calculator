package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Calculator {


    public static String WIRE_NAMES_PATH = "Config/Wires.txt";
    private static Logger log = LoggerFactory.getLogger(Calculator.class);

    public static ArrayList<Data> calculateCableMagazineData(ArrayList<Data> data, boolean isItForVOR) {
        ArrayList<Data> calculatingResult = new ArrayList<>();
        ArrayList<String> wires = FileConverter.convertFileToArrayList(WIRE_NAMES_PATH);
        for (Data x : data
        ) {
            String nameCableInTheOriginalData;
            if (isItForVOR) {
                if (checkIsItWire(x.getCableData()[1], wires)) {
                    nameCableInTheOriginalData = "Провод";
                } else {
                    nameCableInTheOriginalData = "Кабель";
                }
            } else {
                nameCableInTheOriginalData = x.getCableData()[1];
            }
            if (calculatingResult.isEmpty()) {
                Data tempData1 = sumCableToResultTempData(nameCableInTheOriginalData, x, new ArrayList<Data>(), 0);
                calculatingResult.add(tempData1);
            } else {
                int count = calculatingResult.size();
                boolean cableSummed = false;
                for (int j = 0; j < count; j++) {

                    String nameCableInTheResultData = calculatingResult.get(j).getCableData()[1];
                    if (nameCableInTheOriginalData.equals(nameCableInTheResultData)) {
                        Data tempData = sumCableToResultTempData(nameCableInTheOriginalData, x, calculatingResult, j);

                        calculatingResult.set(j, tempData);
                        cableSummed = true;
                    }
                }
                if (!cableSummed) {
                    Data tempData2 = sumCableToResultTempData(nameCableInTheOriginalData, x, new ArrayList<Data>(), 0);
                    calculatingResult.add(tempData2);
                }
            }
        }

        return calculatingResult;
    }

    public static Data sumCableToResultTempData(String cableName, Data currentCable, ArrayList<Data> calculatingResult, int indexCableInCalculatingResult) {
        Data tempData = new CableMagazineData();
        tempData.setCableData(1, cableName);
        for (int i = CableMagazineData.LEFT_COLUMN_CABLES_SECTION; i <= CableMagazineData.RIGHT_COLUMN_CABLES_SECTION; i++) {
            int resultingCableValue, currentCableValue, quantityOfCurrentCable, totalLengthCurrentCable, totalLengthResultingCable;
            try {
                resultingCableValue = parseInt(calculatingResult.get(indexCableInCalculatingResult).getCableData()[i]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                resultingCableValue = 0;
            }
            try {
                currentCableValue = parseInt(currentCable.getCableData()[i]);
            } catch (NumberFormatException e) {
                currentCableValue = 0;
            }
            try {
                quantityOfCurrentCable = parseInt(currentCable.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES]);
            } catch (NumberFormatException e) {
                quantityOfCurrentCable = 0;
            }
            try {
                totalLengthCurrentCable = parseInt(currentCable.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_CABLES]);
            } catch (NumberFormatException e) {
                totalLengthCurrentCable = 0;
            }
            try {
                totalLengthResultingCable = parseInt(calculatingResult.get(indexCableInCalculatingResult).getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_CABLES]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                totalLengthResultingCable = 0;
            }
            tempData.setCableData(i, String.valueOf(resultingCableValue + currentCableValue * quantityOfCurrentCable));
            tempData.setCableData(CableMagazineData.COLUMN_POSITION_OF_QUANTITY_CABLES, String.valueOf(totalLengthResultingCable + totalLengthCurrentCable));
        }
        return tempData;
    }

    public static boolean checkIsItWire(String cableName, ArrayList<String> wires) {
        for (String x : wires
        ) {
            if (cableName.contains(x)) {
                return true;
            }
        }
        return false;
    }

    public static void calculateAllFilesInFolder (String pathFolder){
        File myFolder = new File(pathFolder);
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
    }
}