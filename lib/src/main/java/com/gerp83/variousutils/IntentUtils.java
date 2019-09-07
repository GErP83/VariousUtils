package com.gerp83.variousutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by gerp83
 * Useful methods for calling Intents
 */
public class IntentUtils {

    private static final String NO_INTENT_ERROR = "Intent call error: ";

    /**
     * Attempts to view/open an url
     *
     * @param context Context used to call the activity
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
     * Attempts to open the app Google Play Store page
     *
     * @param context Context used to call the activity
     */
    public static void openAppPlayStore(Context context) throws Throwable{
        if (context == null) {
            return;
        }
        viewURL(context, "market://details?id=" + context.getPackageName().trim());
    }

    /**
     * Attempts to open a pdf file
     *
     * @param context Context used to call the activity
     * @param file The pdf file to open
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
     * Dials a number using the built in Android intent system
     *
     * @param context Context used to call the activity
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

    /**
     * Attempts to share url
     *
     * @param context Context used to call the activity
     * @param subject subject
     * @param url url
     */
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
     * Attempts to open any valid app category, like Intent.CATEGORY_APP_EMAIL
     *
     * @param context Context used to call the activity
     * @param category category
     */
    public static void openAppCategory(Context context, String category) throws Throwable{
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(category);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }catch (Throwable e){
            throw new Throwable(NO_INTENT_ERROR + e.toString());
        }
    }

}
