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
            int i = 0; //положение в наполняемом кабеле (ориентация относительно CableMagazineData
            int j = 0; //позиция текущего кабеля
            boolean strStarted = false;
            String tempStr = "";
            Data tempData = new CableMagazineData(); //!заглушка
            while (true) {
                symbol = br.read();
                if (symbol != -1) {
                    char c = (char) symbol;
                    if ((c == '\"') && (strStarted == false)) {
                        //Начало ячейки, начинаем считывать в следующих шагах
                        strStarted = true;
                    } else if ((c == '\"') && (strStarted == true)) {
                        //Конец ячейки, закрываем ее
                        if (i == data.getElementsQuantityInStr()) {
                            i = 0;
                            j += 1;
                        } else {
                            arrayFromFile.set(j, tempData);

                            i += 1;
                        }
                        strStarted = false;
                    } else {
                        tempData.set
                    
                }


            } else{
                break;
            }
        }
    } catch(
    IOException e)

    {
        log.error("File read error");
    }

        return arrayFromFile;
}

}
