package ru.turlyunef;

public class DataFromFile implements Data {
    private String CableType;
    private int[] PiecesLength;
    private int Count;


    @Override
    public String getCableType() {
        return CableType;
    }

    @Override
    public int[] getPiecesLength() {
        return PiecesLength;
    }

    @Override
    public int getCount() {
        return Count;
    }
}
