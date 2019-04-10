package ru.turlyunef;

import java.util.HashMap;

public interface Configure {

    int getElementsQuantityInStr();

    void setElementsQuantityInStr(int elementsQuantityInStr);

    int getLeftColumnCablesSection();

    void setLeftColumnCablesSection(int leftColumnCablesSection);

    int getRightColumnCablesSection();

    void setRightColumnCablesSection(int rightColumnCablesSection);

    int getColumnPositionOfQuantitySectionCables();

    void setColumnPositionOfQuantitySectionCables(int columnPositionOfQuantitySectionCables);

    int getColumnPositionOfQuantityCables();

    void setColumnPositionOfQuantityCables(int columnPositionOfQuantityCables);

    String getWireNamesPath();

    void setWireNamesPath(String wireNamesPath);

    String getTypeCablingPath();

    void setCfgHashMap(CfgHashMap cfgHashMap);

    public CfgHashMap getCfgHashMap();

}
