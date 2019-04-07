package ru.turlyunef;

public interface Data {
    String[] getCableData();

    int getElementsQuantityInStr();

    void addCharToCableData(int arrayIndex, char element);

    void setCableData(int indexElementInArray, String cableData);
}
