package ru.turlyunef;

import java.util.HashMap;

public class CfgHashMapMagazineData implements CfgHashMap {
    private HashMap<String, String> cfgHashMap;

    @Override
    public void setConfigHashMap(HashMap<String, String> config) {
        this.cfgHashMap = config;
    }

    @Override
    public HashMap<String, String> getConfigHashMap() {
        return cfgHashMap;
    }
}
