package com.colormaster.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by oZBo on 28.01.2015.
 */
public class PreferenceUtil {

    public static boolean getBoolean(Context context, String preferenceKey, boolean defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(preferenceKey, defaultValue);
    }

    public static boolean putBoolean(Context context, String preferenceKey, boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editorSp = sp.edit();
        editorSp.putBoolean(preferenceKey, value);
        return editorSp.commit();
    }

    public static int getInt(Context context, String preferanceKey, int defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(preferanceKey, defaultValue);
    }

    public static boolean putInt(Context context, String preferenceKey, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editorSp = sp.edit();
        editorSp.putInt(preferenceKey, value);
        return editorSp.commit();
    }

}
