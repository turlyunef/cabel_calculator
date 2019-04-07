package ru.turlyunef;

public class CableMagazineData implements Data {
    private static final int ELEMENTS_QUANTITY_IN_STR = 30;
    public static final int LEFT_COLUMN_CABLES_SECTION = 14;
    public static final int RIGHT_COLUMN_CABLES_SECTION = 27;
    public static final int COLUMN_POSITION_OF_QUANTITY_SECTION_CABLES = 28;
    public static final int COLUMN_POSITION_OF_QUANTITY_CABLES = 29;

    private String[] OneCableData;

    public CableMagazineData() {

        OneCableData = new String[ELEMENTS_QUANTITY_IN_STR];
    }

    @Override
    public String[] getCableData() {
        return OneCableData;
    }

    @Override
    public int getElementsQuantityInStr() {
        return ELEMENTS_QUANTITY_IN_STR;
    }

    @Override
    public void addCharToCableData(int arrayIndex, char element) {
        if (OneCableData[arrayIndex] == null) {
            OneCableData[arrayIndex] = "";
        }
        OneCableData[arrayIndex] = OneCableData[arrayIndex] + element;
    }

    @Override
    public void setCableData(int indexElementInArray, String cableData) {
        this.OneCableData[indexElementInArray] = cableData;
    }
}
