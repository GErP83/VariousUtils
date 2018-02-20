package com.gerp83.variousutils.font;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * a class for hold custom fonts, works with fontable views
 */
public class FontCache {

    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    /**
     * returns Typeface from cache if any
     * */
    public static Typeface get(Context context, String name) {

        if (context == null || name == null) {
            return null;
        }

        synchronized (cache) {
            if (!cache.containsKey(name)) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), name);
                cache.put(name, typeface);
            }
            return cache.get(name);
        }
    }

}