package ru.turlyunef;

import java.util.HashMap;

public interface Configure {

    public int getElementsQuantityInStr();

    public void setElementsQuantityInStr(int elementsQuantityInStr);

    public int getLeftColumnCablesSection();

    public void setLeftColumnCablesSection(int leftColumnCablesSection);

    public int getRightColumnCablesSection();

    public void setRightColumnCablesSection(int rightColumnCablesSection);

    public int getColumnPositionOfQuantitySectionCables();

    public void setColumnPositionOfQuantitySectionCables(int columnPositionOfQuantitySectionCables);

    public int getColumnPositionOfQuantityCables();

    public void setColumnPositionOfQuantityCables(int columnPositionOfQuantityCables);

    public String getWireNamesPath();

    public void setWireNamesPath(String wireNamesPath);

    public String getTypeCablingPath();

    public void setConfig(HashMap<String, String> config);

    public HashMap<String, String> getConfig();
}
