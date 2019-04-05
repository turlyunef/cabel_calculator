package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileConverter {
    private static Logger log = LoggerFactory.getLogger(FileConverter.class);

    public static ArrayList<Data> convertFileToArrays(String fileName, Data data) {
        ArrayList<Data> arrayFromFile = new ArrayList<>();
        try (FileInputStream fstream = new FileInputStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            int symbol;
            int indexOfDataForTheActualCable = 0; //положение в наполняемом кабеле (ориентация относительно CableMagazineData
            boolean strStarted = false;
            Data tempData = new CableMagazineData(); //!заглушка
            while (true) {
                symbol = br.read();
                if (symbol != -1) {
                    char c = (char) symbol;
                    if (((char) symbol == '\r') | ((char) symbol == '\n')) { // Отбрасываем переход на новую строку
                    } else if ((c == '\"') && (strStarted == false)) {
                        //Начало ячейки, начинаем считывать в следующих шагах
                        strStarted = true;
                    } else if ((c == '\"') && (strStarted == true)) {
                        //Конец ячейки, закрываем ее
                        if (indexOfDataForTheActualCable == data.getElementsQuantityInStr() - 1) {
                            arrayFromFile.add(tempData);
                            tempData = new CableMagazineData();
                            indexOfDataForTheActualCable = 0;
                        } else {
                            indexOfDataForTheActualCable += 1;
                        }
                        strStarted = false;
                    } else if (strStarted == true) {
                        tempData.addCharToCableData(indexOfDataForTheActualCable, c);
                    }
                } else {
                    break;
                }
            }
        } catch (
                IOException e) {
            log.error("File read error");
        }

        return arrayFromFile;
    }

}
