package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

public class FileConverter {
    private static Logger log = LoggerFactory.getLogger(FileConverter.class);

    public static ArrayList<Data> convertFileToDataArrays(String fileName, Data data) {
        ArrayList<Data> arrayFromFile = new ArrayList<>();
        File file = new File(fileName);
        try (
                FileInputStream fstream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            int symbol;
            int indexOfDataForTheActualCable = 0; //положение в наполняемом кабеле (ориентация относительно CableMagazineData
            boolean strStarted = false;
            Data tempData = new CableMagazineData(); //!заглушка
            char lastSymbol;
            symbol = br.read();
            if (symbol != -1) {
                lastSymbol = (char) symbol;
            }
            else {
                return null;
            }
            boolean IsLastQuotationMarkwasData = false;
            while (true) {
                symbol = br.read();
                if (symbol != -1) {
                    char c = (char) symbol;
                    if ((lastSymbol == '\r') | (lastSymbol == '\n')) { // Отбрасываем переход на новую строку
                    } else if ((lastSymbol == '\"') && (!strStarted)) {
                        //Начало ячейки, начинаем считывать в следующих шагах
                        strStarted = true;
                        lastSymbol = c;
                    }

                    else if ((lastSymbol == '\"') & (c == '\"') & (IsLastQuotationMarkwasData)) {
                        tempData.addCharToCableData(indexOfDataForTheActualCable, lastSymbol);
                        lastSymbol = c;
                        IsLastQuotationMarkwasData = false;
                    }


                    else if ((lastSymbol == '\"') & (c != '\"') & (!IsLastQuotationMarkwasData)) {
                        //Конец ячейки, закрываем ее
                        IsLastQuotationMarkwasData = true;
                        if (indexOfDataForTheActualCable == data.getElementsQuantityInStr() - 1) {
                            arrayFromFile.add(tempData);
                            tempData = new CableMagazineData();
                            indexOfDataForTheActualCable = 0;
                        } else {
                            indexOfDataForTheActualCable += 1;
                        }
                        strStarted = false;
                        lastSymbol = c;
                    } else if ((lastSymbol == '\"') & (c != '\"') & (IsLastQuotationMarkwasData)) {
                        tempData.addCharToCableData(indexOfDataForTheActualCable, lastSymbol);
                        lastSymbol = c;
                        IsLastQuotationMarkwasData = false;

                    }
                    else if (strStarted) {
                        tempData.addCharToCableData(indexOfDataForTheActualCable, lastSymbol);
                        lastSymbol = c;

                    }
                } else {
                    break;
                }
            }
        } catch (
                IOException e) {
            log.error("File " + fileName + " read error");
        }

        return arrayFromFile;
    }

    public static ArrayList<String> convertFileToArrayList(String fileName) {
        ArrayList<String> listFromFile = new ArrayList<>();
        try (FileInputStream fstream = new FileInputStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            String strLine;
            while (true) {
                strLine = br.readLine();
                if (strLine != null) {
                    listFromFile.add(strLine);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            log.error("File " + fileName + " read error");
        }

        return listFromFile;
    }
}