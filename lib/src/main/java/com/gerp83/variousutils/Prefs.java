package com.gerp83.variousutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * A singleton class to handle SharedPreferences load and save
 */
public class Prefs {

    private static Prefs instance = null;
    private SharedPreferences sharedPreferences;

    private Prefs(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * singleton constructor
     */
    public static Prefs get(Context context) {
        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    /**
     * check if the instance is null
     */
    public static boolean isNull() {
        return instance == null;
    }

    //boolean

    /**
     * load a boolean value from SharedPreferences with a default value
     *
     * @param key          key for the boolean value
     * @param defaultValue default boolean value
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * load a boolean value from SharedPreferences with value false
     *
     * @param key key for the boolean value
     */
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * save a boolean value to SharedPreferences
     *
     * @param key   key for the boolean value
     * @param value boolean value
     */
    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    //int

    /**
     * load an int value from SharedPreferences with a default value
     *
     * @param key          key for the int value
     * @param defaultValue default int value
     */
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * load an int value from SharedPreferences with value -1
     *
     * @param key key for the int value
     */
    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    /**
     * save an int value to SharedPreferences
     *
     * @param key   key for the int value
     * @param value int value
     */
    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    //long

    /**
     * load a long value from SharedPreferences with a default value
     *
     * @param key          key for the long value
     * @param defaultValue default long value
     */
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * load a long value from SharedPreferences with value 0
     *
     * @param key key for the long value
     */
    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * save a long value to SharedPreferences
     *
     * @param key   key for the long value
     * @param value long value
     */
    public void setLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    //float

    /**
     * load a float value from SharedPreferences with a default value
     *
     * @param key          key for the float value
     * @param defaultValue default float value
     */
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * load a float value from SharedPreferences with value -1
     *
     * @param key key for the float value
     */
    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, -1);
    }

    /**
     * save a float value to SharedPreferences
     *
     * @param key   key for the float value
     * @param value float value
     */
    public void setFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    //string

    /**
     * load a String value from SharedPreferences with a default value
     *
     * @param key          key for the String value
     * @param defaultValue default String value
     */
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * load a String value from SharedPreferences with value null
     *
     * @param key key for the String value
     */
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    /**
     * save a String value to SharedPreferences
     *
     * @param key   key for the String value
     * @param value String value
     */
    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * remove a value from SharedPreferences with key
     *
     * @param key key for remove
     */
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

}