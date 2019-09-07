package com.gerp83.variousutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import javax.net.ssl.X509TrustManager;

/**
 * Created by gerp83
 * various useful methods
 */

public class GeneralUtils {

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
            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

    /**
     * @return an array with screen width and height in pixels or {-1, -1}
     */
    public static int[] getScreenSize(Context context) {
        if (context == null) {
            return new int[]{-1, -1};
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(wm != null) {
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return new int[]{size.x, size.y};
        }
        return new int[]{-1, -1};
    }

    /**
     * @return screen width or -1
     */
    public static int getScreenWidth(Context context) {
        if (context == null) {
            return -1;
        }
        int [] size = getScreenSize(context);
        if(size.length != 0 && size[0] != -1) {
            return size[0];
        }
        return -1;
    }

    /**
     * @return screen height or -1
     */
    public static int getScreenHeight(Context context) {
        if (context == null) {
            return -1;
        }
        int [] size = getScreenSize(context);
        if(size.length != 0 && size[1] != -1) {
            return size[1];
        }
        return -1;
    }

    /**
     * Returns the screen's density.
     *
     * @return The screen's density or -1
     */
    public static float getDisplayDensity(Context context) {
        if (context == null) {
            return -1;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(wm != null) {
            wm.getDefaultDisplay().getMetrics(metrics);
            return metrics.densityDpi <= DisplayMetrics.DENSITY_DEFAULT ? metrics.density : (metrics.density / 2);
        }
        return -1;
    }

}