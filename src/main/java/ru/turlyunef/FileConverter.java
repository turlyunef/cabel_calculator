package ru.turlyunef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
            } else {
                return null;
            }
            boolean IsLastQuotationMarkwasData = false;
            while (true) {
                symbol = br.read();
                if (symbol != -1) {
                    char nextSymbol = (char) symbol;
                    if ((lastSymbol == '\r') | (lastSymbol == '\n')) { // Отбрасываем переход на новую строку
                        lastSymbol = nextSymbol;
                        continue;
                    }
                    if (IsLastQuotationMarkwasData) { // Эти кавычки – данные, записываем их в tempData
                        IsLastQuotationMarkwasData = false; // меняем флаг о том, что след кавычки - уже не данные
                        lastSymbol = nextSymbol;
                        continue;
                    } else {
                        if (lastSymbol == '\"') {
                            if (!strStarted) {
                                // Начинаем считывание строки
                                strStarted = true;
                                lastSymbol = nextSymbol;
                                continue;
                            } else {
                                //Нужно проверить конец это элемента данных или нет
                                if (nextSymbol == '\"') {
                                    //Эти кавычки – данные, записываем их в tempData
                                    tempData.addCharToCableData(indexOfDataForTheActualCable, lastSymbol);
                                    //След. Кавычки - тоже данные, ставим флаг об этом
                                    IsLastQuotationMarkwasData = true;
                                } else {
                                    //Эти lastSymbol  кавычки – окончание ячейки данных
                                    strStarted = false;
                                    if (indexOfDataForTheActualCable == data.getElementsQuantityInStr() - 1) { // Эти кавычки  последнего элемента данных объекта данных?
                                        arrayFromFile.add(tempData); //Записываем полученный объект в массив объектов, обнуляем индекс положения считывания данных объекта
                                        tempData = new CableMagazineData();
                                        indexOfDataForTheActualCable = 0;
                                    } else {
                                        indexOfDataForTheActualCable += 1; //Смещаем индекс положения считывания данных объекта на 1 вперед
                                    }
                                }
                                lastSymbol = nextSymbol;
                                continue;
                            }
                        } else {
                            if (strStarted) {
                                //Значит элемент lastSymbol - данные
                                //Записываем lastSymbol В tempData
                                tempData.addCharToCableData(indexOfDataForTheActualCable, lastSymbol);
                            }
                            //Значит элемент lastSymbol - разделитель
                            //Пропускаем элемент

                            lastSymbol = nextSymbol;
                            continue;
                        }
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

    public static HashMap<String, String> parseConfig(String fileName){
        HashMap<String, String> config = new HashMap<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fileReader)) {
            String strLine;
            while (true) {
                strLine = br.readLine();
                if (strLine != null) {
                    String[] hashMap = strLine.split("=", 2);
                    config.put(hashMap[0], hashMap [1]);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            log.error("File " + fileName + " read error");
        }

        return config;
    }
}