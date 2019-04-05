package ru.turlyunef;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Calculator {
    public static ArrayList<Data> calculateCableMagazineData(ArrayList<Data> data) {
        ArrayList<Data> calculatingResult = new ArrayList<>();
        for (Data x : data
        ) {
            if (calculatingResult.isEmpty()) {
                calculatingResult.add(x);
            } else {
                int count = calculatingResult.size();
                for (int j = 0; j < count; j++) {

                    String nameCableInTheOriginalData = x.getCableData()[1];
                    String nameCableInTheResultData = calculatingResult.get(j).getCableData()[1];
                    if (nameCableInTheOriginalData.equals(nameCableInTheResultData)) {
                        Data tempData = new CableMagazineData();
                        //Копировать название кабеля в результирующий массив строк!!!!
                        for (int i = 14; i <= 27; i++) {
                            int a,b,c;
                            try{
                                a = parseInt(calculatingResult.get(j).getCableData()[i]);
                            } catch (NumberFormatException e){
                                a = 0;
                            }
                            try{
                                b = parseInt(x.getCableData()[i]);
                            } catch (NumberFormatException e){
                                b = 0;
                            }
                            try{
                                c = parseInt(x.getCableData()[28]);
                            } catch (NumberFormatException e){
                                c = 0;
                            }

                            tempData.setCableData(i-1, String.valueOf(a + b * c));

                        }
                        calculatingResult.set(j, tempData);

                    } else {
                        calculatingResult.add(x);

                    }
                }
            }
        }
        return calculatingResult;
    }

}
