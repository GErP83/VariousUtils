package com.gerp83.variousutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by gerp83
 * Contains methods for navigating from a fragment / activity to another activity.
 */
public class Intents {

    private static final String NO_INTENT_ERROR = "Intent call error: ";

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
    public static Intents set(Activity activityFrom, Class clazz) {
        return new Intents(activityFrom, clazz);
    }

    private Intents(Activity activityFrom, Class clazz) {
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
    public static Intents set(Context context, Class clazz) {
        return new Intents(context, clazz);
    }

    private Intents(Context context, Class clazz) {
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
    public static Intents set(Context context, String action) {
        return new Intents(context, action);
    }

    private Intents(Context context, String action) {
        this.context = context;
        this.action = action;
    }

    /**
     * data to send with Intent
     *
     * @param key key
     * @param data Object
     */
    public Intents data(String key, Object data) {
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
    public Intents flags(int intentFlags) {
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

    /**
     * Views/Opens an url.
     *
     * @param context  A context needed to open the activity to be launched
     * @param url String url to open/view.
     */
    public static void viewURL(Context context, String url) throws Throwable{
        if (context == null || url == null) {
            return;
        }
        if(!url.startsWith("http://")) {
            url = "http://" + url;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

    /**
     * Attempts to open the Google Play Store.
     *
     * @param context A context needed for opening the application/activity.
     */
    public static void openAppPlayStore(Context context) throws Throwable{
        if (context == null) {
            return;
        }
        viewURL(context, "market://details?id=" + context.getPackageName().trim());
    }

    /**
     * Attempts to open a pdf file.
     *
     * @param context A context needed for opening the application/activity.
     * @param file The pdf file to open.
     */
    public static void openPdf(Context context, File file) throws Throwable{
        if (context == null || file == null) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            context.startActivity(intent);
        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

    /**
     * Dials a number using the built in Android intent system.
     *
     * @param context A context used to call the activity.
     * @param phone phone number to call
     */
    public static void dialNumber(Context context, String phone) throws Throwable{
        if (context == null || phone == null) {
            return;
        }
        if(!phone.startsWith("tel:")) {
            phone = "tel:" + phone;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

    public static void shareUrl(Context context, String subject, String url) throws Throwable{
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, url);
            context.startActivity(intent);
        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

    /**
     * Composes a new mail using the built in Android intent system.
     *
     * @param context      A context used to call the activity.
     * @param mailAddress  String representing destination email.
     * @param mailSubject  String representing the subject of the email.
     */
    public static void composeMail(Context context, String mailAddress, String mailSubject, String mailText) throws Throwable {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("mailto:"), "*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailAddress});
            if (mailSubject != null) {
                intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
            }
            if (mailText != null) {
                intent.putExtra(Intent.EXTRA_TEXT, mailText);
            }

            context.startActivity(intent);
        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

}
