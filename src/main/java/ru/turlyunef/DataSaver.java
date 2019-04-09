package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DataSaver {
    private static Logger log = LoggerFactory.getLogger(DataSaver.class);
    public static final String TYPE_CABLING_PATH = "Config/TypeCabling.txt";
    private static ArrayList<String> typeCabling = FileConverter.convertFileToArrayList(TYPE_CABLING_PATH);

    public static void saveSumResultToFile(String fileName, ArrayList<Data> data) {
        ArrayList<String> wires = FileConverter.convertFileToArrayList(Calculator.WIRE_NAMES_PATH);
        for (Data x : data
        ) {
            if (Calculator.checkIsItWire(x.getCableData()[1], wires)) {
                appendStrToFile(fileName, "Провод " + x.getCableData()[1] + "; Общая сумма в проекте: " + x.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES + 1] + " м");
            } else {
                appendStrToFile(fileName, "Кабель " + x.getCableData()[1] + "; Общая сумма в проекте: " + x.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES + 1] + " м");
            }
        }
    }

    public static void saveVORResultToFile(String fileName, ArrayList<Data> data) {
        appendStrToFile(fileName, "--------------------------------------------------");
        appendStrToFile(fileName, "Информация для составления ведомости объемов работ");
        for (Data x : data
        ) {
            appendStrToFile(fileName, x.getCableData()[1] + ":");
            int indexOfTypeCabling = 0;
            for (int i = CableMagazineData.LEFT_COLUMN_CABLES_SECTION; i <= CableMagazineData.RIGHT_COLUMN_CABLES_SECTION; i++) {
                int resultingCableValue;
                try {
                    resultingCableValue = parseInt(x.getCableData()[i]);
                } catch (NumberFormatException e) {
                    resultingCableValue = 0;
                }
                if (!x.getCableData()[i].equals("0")) {
                    appendStrToFile(fileName, "Тип прокладки: " + typeCabling.get(indexOfTypeCabling) + ". Количество : " + resultingCableValue + " м");
                }
                indexOfTypeCabling++;
            }
        }
    }

    public static void appendStrToFile(String fileName, String str) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(str);
            writer.append("\r\n");
            writer.flush();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}