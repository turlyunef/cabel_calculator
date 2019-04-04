package ru.turlyunef;

public class CableMagazineData implements Data {
    private int ElementsQuantityInStr = 30;
    private String[] OneCableData = new String[ElementsQuantityInStr];

    @Override
    public String[] getCableType() {
        return OneCableData;
    }

    @Override
    public int getElementsQuantityInStr() {
        return 0;
    }


}
