package com.gerp83.variousutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.math.BigDecimal;
import java.security.cert.CertificateException;

import javax.net.ssl.X509TrustManager;

/**
 * Created by gerp83
 * various useful methods
 */

public class GeneralUtils {

    public static int getColorFromString(String str){
        return Color.parseColor(str);
    }

    public static int getTextWithFromString(int textSize, String text, Typeface typeface){
        Paint paint = new Paint();
        Rect bounds = new Rect();
        if(typeface != null) {
            paint.setTypeface(typeface);
        }
        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    public static X509TrustManager getX509(){
        return new X509TrustManager() {
            @SuppressLint ("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

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
     * hide keyboard
     */
    public static void hideActivityKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * @return an array with screen width and height in pixels
     */
    public static int[] getScreenSize(Context context) {
        if (context == null) {
            return null;
        }
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x, size.y};
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
     * Returns the screen's density.
     *
     * @param context Context for getting screen metrics.
     * @return The screen's density.
     */
    public static float getDisplayDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi <= DisplayMetrics.DENSITY_DEFAULT ? metrics.density : (metrics.density / 2);
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