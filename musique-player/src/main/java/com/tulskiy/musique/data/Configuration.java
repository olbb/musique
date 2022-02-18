package com.tulskiy.musique.data;

import java.beans.PropertyChangeListener;

/**
 * @author JuanLv created at 2022/2/18
 * olbbme@gmail.com
 */
public class Configuration {


    public int getInt(String key, int defValue) {
        return defValue;
    }

    public void addPropertyChangeListener(String key, PropertyChangeListener propertyChangeListener) {

    }

    public float getFloat(String key, float defValue) {
        return defValue;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return defValue;
    }

    public String getString(String key, String defValue) {
        return defValue;
    }

    public void setBoolean(String key, boolean value) {

    }
}
