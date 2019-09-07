package com.gerp83.variousutils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.math.BigDecimal;

public class Converters {

    /**
     * round a float value
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * round a double value
     */
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Convert pixel to dp
     */
    public static float convertPixelsToDp(float px, Context context) {
        if (context == null) {
            return -1;
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Convert dp to pixel
     */
    public static float convertDpToPixel(float dp, Context context) {
        if (context == null) {
            return -1;
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Convert pixel to sp
     */
    public static float convertPixelsToSp(float px, Context context) {
        if (context == null) {
            return -1;
        }
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    /**
     * Convert sp to pixel
     */
    public static float convertSpToPixel(float sp, Context context) {
        if (context == null) {
            return -1;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * degrees to radian
     */
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * radian to degrees
     */
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
