package com.gerp83.variousutils.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.gerp83.variousutils.R;

/**
 * custom util that can load custom font from xml layout
 */
public class FontLoader {

    public static Typeface setCustomFont(Context context, AttributeSet attrs) {
        if(context == null || attrs == null) {
            return null;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FontView);
        String customFont = array.getString(R.styleable.FontView_customFont);
        array.recycle();

        Typeface typeface = null;
        try {
            typeface = FontCache.get(context, customFont);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return typeface;
    }

}