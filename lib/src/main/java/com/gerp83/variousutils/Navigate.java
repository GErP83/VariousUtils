package com.gerp83.variousutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by gerp83
 * Contains methods for navigating from a fragment / activityFrom to another fragment / activityFrom.
 * Have the ability to send data with the navigation.
 */
public class Navigate {

    private Activity activityFrom = null;
    private Context context = null;
    private Class clazzTo = null;
    private HashMap<String, Object> dataMap = null;

    private int intentFlags = -1;
    private String action = null;
    private int resultCode = -1;

    private boolean finish = false;
    private boolean forResult = false;
    private boolean goBack = false;

    /**
     * set Activity and Class
     *
     * @param activityFrom activityFrom
     * @param clazz Class
     */
    public static Navigate set(Activity activityFrom, Class clazz) {
        return new Navigate(activityFrom, clazz);
    }

    private Navigate(Activity activityFrom, Class clazz) {
        this.activityFrom = activityFrom;
        this.clazzTo = clazz;
    }

    /**
     * set Context and Class
     * note: it is possible you will need to set flag: Intent.FLAG_ACTIVITY_NEW_TASK when Context used
     * for start an Activity
     *
     * @param context Context
     * @param clazz Class
     */
    public static Navigate set(Context context, Class clazz) {
        return new Navigate(context, clazz);
    }

    private Navigate(Context context, Class clazz) {
        this.context = context;
        this.clazzTo = clazz;
    }

    /**
     * set Context and set action for Intent
     * note: it is possible you will need to set flag: Intent.FLAG_ACTIVITY_NEW_TASK when Context used
     * for start an Activity
     *
     * @param context Context
     * @param action action
     */
    public static Navigate set(Context context, String action) {
        return new Navigate(context, action);
    }

    private Navigate(Context context, String action) {
        this.context = context;
        this.action = action;
    }

    /**
     * data to send with Intent
     *
     * @param key key
     * @param data Object
     */
    public Navigate data(String key, Object data) {
        if(dataMap == null) {
            dataMap = new HashMap<>();
        }
        dataMap.put(key, data);
        return this;
    }

    /**
     * set flags for Intent
     *
     * @param intentFlags flags
     */
    public Navigate flags(int intentFlags) {
        this.intentFlags = intentFlags;
        return this;
    }

    /**
     * need to start with startActivityForResult
     */
    public void forResultAndGo(int resultCode) {
        this.resultCode = resultCode;
        this.forResult = true;
        go();
    }

    /**
     * need to finish and go back and set setResult(Activity.RESULT_OK, intent);
     */
    public void backAndGo() {
        this.goBack = true;
        go();
    }

    /**
     * need to finish at the end
     */
    public void finishAndGo() {
        this.finish = true;
        go();
    }

    /**
     * start navigation
     */
    public void go(){

        if(activityFrom == null && context == null) {
            return;
        }

        Intent intent;
        if(clazzTo != null) {
            if(activityFrom != null) {
                intent = new Intent(activityFrom, clazzTo);
            } else {
                intent = new Intent(context, clazzTo);
            }
        } else {
            intent = new Intent();
        }

        if(intentFlags != -1) {
            intent.addFlags(intentFlags);
        }
        if(dataMap != null) {
            for (String key : dataMap.keySet()) {
                intent.putExtra(key, (Serializable) dataMap.get(key));
            }
        }

        if(action != null) {
            intent.setAction(action);
        }

        if(activityFrom != null) {
            if (forResult) {
                activityFrom.startActivityForResult(intent, resultCode);

            } else if(goBack) {
                activityFrom.setResult(Activity.RESULT_OK, intent);
                activityFrom.finish();

            } else if (finish) {
                activityFrom.startActivity(intent);
                activityFrom.finish();

            } else {
                activityFrom.startActivity(intent);

            }

        } else if (context != null) {
            if (forResult) {
                if(context instanceof Activity) {
                    ((Activity) context).startActivityForResult(intent, resultCode);
                }

            } else if(goBack) {
                if(context instanceof Activity) {
                    ((Activity) context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }

            } else if (finish) {
                if(context instanceof Activity){
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            } else {
                context.startActivity(intent);

            }

        }
    }

}
