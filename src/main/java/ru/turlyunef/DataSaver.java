package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DataSaver {
    public static final String OUTPUT_FILE_NAME = "Resources/Out.txt";
    private static Logger log = LoggerFactory.getLogger(DataSaver.class);
    private static ArrayList<String> typeCabling = FileConverter.convertFileToArrayList("Resources/TypeCabling.txt");

    public static void saveSumResultToFile(ArrayList<Data> data) {
        ArrayList<String> wires = FileConverter.convertFileToArrayList("Resources/Wires.txt");
        for (Data x : data
        ) {
            if (Calculator.checkIsItWire(x.getCableData()[1], wires)) {
                appendStrToFile("Провод " + x.getCableData()[1] + "; Общая сумма в проекте: " + x.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES + 1] + " м");
            } else {
                appendStrToFile("Кабель " + x.getCableData()[1] + "; Общая сумма в проекте: " + x.getCableData()[CableMagazineData.COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES + 1] + " м");
            }
        }
    }

    public static void saveVORResultToFile(ArrayList<Data> data) {
        appendStrToFile("--------------------------------------------------");
        appendStrToFile("Информация для составления ведомости объемов работ");
        for (Data x : data
        ) {
            appendStrToFile(x.getCableData()[1] + ":");
            int indexOfTypeCabling = 0;
            for (int i = CableMagazineData.LEFT_COLUMN_CABLES_SECTION; i <= CableMagazineData.RIGHT_COLUMN_CABLES_SECTION; i++) {
                int resultingCableValue;
                try {
                    resultingCableValue = parseInt(x.getCableData()[i]);
                } catch (NumberFormatException e) {
                    resultingCableValue = 0;
                }
                if (!x.getCableData()[i].equals("0")) {
                    appendStrToFile("Тип прокладки: " + typeCabling.get(indexOfTypeCabling) + ". Количество : " + resultingCableValue + " м");
                }
                indexOfTypeCabling++;
            }
        }
    }

    public static void appendStrToFile(String str) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE_NAME, true)) {
            writer.append(str);
            writer.append("\r\n");
            writer.flush();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}