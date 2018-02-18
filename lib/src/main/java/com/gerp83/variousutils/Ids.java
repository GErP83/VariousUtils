package com.gerp83.variousutils;

import android.content.Context;

/**
 * Created by gerp83
 *
 */

public class Ids {

    /**
     * returns layout id from string
     *
     * @param context mandatory param
     * @param id      layout id
     */
    public static int getLayoutId(String id, Context context) {
        return getIdFromString("layout/" + id, context);
    }

    /**
     * returns drawable id from string
     *
     * @param context mandatory param
     * @param id      drawable id
     */
    public static int getDrawableId(String id, Context context) {
        return getIdFromString("drawable/" + id, context);
    }

    /**
     * returns raw id from string
     *
     * @param context mandatory param
     * @param id      raw id
     */
    public static int getRawId(String id, Context context) {
        return getIdFromString("raw/" + id, context);
    }

    /**
     * returns anim id from string
     *
     * @param context mandatory param
     * @param id      animation id
     */
    public static int getAnimId(String id, Context context) {
        return getIdFromString("anim/" + id, context);
    }

    /**
     * returns color id from string
     *
     * @param context mandatory param
     * @param id      color id
     */
    public static int getColorId(String id, Context context) {
        return getIdFromString("color/" + id, context);
    }

    /**
     * returns string id from string
     *
     * @param context mandatory param
     * @param id      string id
     */
    public static int getStringId(String id, Context context) {
        return getIdFromString("string/" + id, context);
    }

    /**
     * returns dimens id from string
     *
     * @param context mandatory param
     * @param id      dimens id
     */
    public static int getDimenId(String id, Context context) {
        return getIdFromString("dimen/" + id, context);
    }

    /**
     * returns id from string
     *
     * @param context mandatory param
     * @param id      id
     */
    public static int getId(String id, Context context) {
        if (id == null || context == null) {
            return -1;
        }
        return context.getResources().getIdentifier(id, "id", context.getPackageName());
    }

    private static int getIdFromString(String string, Context context) {
        if (string == null || context == null) {
            return -1;
        }
        return context.getResources().getIdentifier(string, null, context.getPackageName());
    }

}