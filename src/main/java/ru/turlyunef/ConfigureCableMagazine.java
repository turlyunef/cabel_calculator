package ru.turlyunef;

import java.util.HashMap;

public class ConfigureCableMagazine implements Configure {
    private int elementsQuantityInStr = 30;
    private int leftColumnCablesSection = 14;
    private int rightColumnCablesSection = 27;
    private int columnPositionOfQuantitySectionCables = 28;
    private int columnPositionOfQuantityCables = 29;
    private String wireNamesPath = "Config/Wires.txt";
    private final String typeCablingPath = "Config/TypeCabling.txt";

    @Override
    public int getElementsQuantityInStr() {
        return elementsQuantityInStr;
    }

    @Override
    public void setElementsQuantityInStr(int elementsQuantityInStr) {
        this.elementsQuantityInStr = elementsQuantityInStr;
    }

    @Override
    public int getLeftColumnCablesSection() {
        return leftColumnCablesSection;
    }

    @Override
    public void setLeftColumnCablesSection(int leftColumnCablesSection) {
        this.leftColumnCablesSection = leftColumnCablesSection;
    }

    @Override
    public int getRightColumnCablesSection() {
        return rightColumnCablesSection;
    }

    @Override
    public void setRightColumnCablesSection(int rightColumnCablesSection) {
        this.rightColumnCablesSection = rightColumnCablesSection;
    }

    @Override
    public int getColumnPositionOfQuantitySectionCables() {
        return columnPositionOfQuantitySectionCables;
    }

    @Override
    public void setColumnPositionOfQuantitySectionCables(int columnPositionOfQuantitySectionCables) {
        this.columnPositionOfQuantitySectionCables = columnPositionOfQuantitySectionCables;
    }

    @Override
    public int getColumnPositionOfQuantityCables() {
        return columnPositionOfQuantityCables;
    }

    @Override
    public void setColumnPositionOfQuantityCables(int columnPositionOfQuantityCables) {
        this.columnPositionOfQuantityCables = columnPositionOfQuantityCables;
    }

    @Override
    public String getWireNamesPath() {
        return wireNamesPath;
    }

    @Override
    public void setWireNamesPath(String wireNamesPath) {
        this.wireNamesPath = wireNamesPath;
    }

    @Override
    public String getTypeCablingPath() {
        return typeCablingPath;
    }

    @Override
    public void setCfgHashMap(CfgHashMap cfgHashMap) {

    }

    @Override
    public CfgHashMap getCfgHashMap() {
        return null;
    }
}

