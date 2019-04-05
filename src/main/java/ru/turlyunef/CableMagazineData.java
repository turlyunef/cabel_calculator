package ru.turlyunef;

public class CableMagazineData implements Data {
    private int ElementsQuantityInStr = 30;
    private String[] OneCableData;

    public CableMagazineData() {
        OneCableData = new String[ElementsQuantityInStr];
    }

    @Override
    public String[] getCableData() {
        return OneCableData;
    }

    @Override
    public int getElementsQuantityInStr() {
        return ElementsQuantityInStr;
    }

    @Override
    public void addCharToCableData(int arrayIndex, char element) {
        if (OneCableData[arrayIndex] == null){
            OneCableData[arrayIndex] = "";
        }
        OneCableData[arrayIndex] = OneCableData[arrayIndex] + element;
        System.out.println("OneCableData[" + arrayIndex + "] = " + OneCableData[arrayIndex]);
    }


}
