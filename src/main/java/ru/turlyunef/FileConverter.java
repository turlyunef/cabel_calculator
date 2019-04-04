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

    public static ArrayList<Data> convertFileToArrays(String fileName) {
        ArrayList<Data> arrayFromFile = new ArrayList<>();
        try (FileInputStream fstream = new FileInputStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            int symbol;
            int i = 0; //положение в наполняемом кабеле (ориентация относительно DataFromFile
            int j = 0; //позиция текущего кабеля
            boolean strStarted = false;
            String tempStr = "";
            while (true) {
                symbol = br.read();
                if (symbol != -1) {
                    char c = (char) symbol;
                    if ((c == '\"') && (strStarted == false)) {
                        //Начало ячейки, начинаем считывать в tempStr
                        strStarted = true;
                    } else if ((c == '\"') && (strStarted == true)) {
                        //Конец ячейки, закрываем ее
                        if (i == 2) {
                            i = 0;
                        } else {
                            //приравнять последнему элементу arrayFromFile значение tempStr и очистить tempStr
                            i += 1;
                        }
                        strStarted = false;
                    } else {
                        switch (i){
                            case 0:{
                                //наполняем тип кабеля
                                //добавить к tempStr текущий элемент с
                            }
                            case 1:{
                                //наполняем длины отрезков
                                //добавить к tempStr текущий элемент с
                            }
                            case 3:{
                                //наполняем количество этого кабеля
                                //добавить к tempStr текущий элемент с
                            }
                        }
                    }


                } else {
                    break;
                }
            }
        } catch (IOException e) {
            log.error("File read error");
        }

        return arrayFromFile;
    }

}
