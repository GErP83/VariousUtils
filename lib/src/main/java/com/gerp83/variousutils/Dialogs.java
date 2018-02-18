package com.gerp83.variousutils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/**
 * Created by gerp83
 * class for handling ProgressDialog and AlertDialog
 */
public class Dialogs {

    /**
     * callBack for Dialog Buttons
     */
    public interface ButtonListener {
        void onPositive();
        void onNegative();
        void onNeutral();
    }

    private String title;
    private String text;

    private boolean cancelAble;
    private boolean outsideCancelAble;
    private String buttonTextNegative;
    private String buttonTextPositive;
    private String buttonTextNeutral;

    private OnCancelListener onCancelListener;
    private ButtonListener buttonListener;

    private boolean isListDialog;
    private DialogInterface.OnClickListener listItemClickListener;
    private String[] listContent;
    private int listSelectedItem = -1;

    private static ProgressDialog progressdialog;

    /**
     * create Dialogs
     */
    public static Dialogs create() {
        return new Dialogs();
    }

    private Dialogs() {
        title = null;
        text = null;
        cancelAble = false;
        outsideCancelAble = false;
        buttonTextNegative = null;
        buttonTextNeutral = null;
        buttonTextPositive = "Ok";
        isListDialog = false;
        onCancelListener = null;
        buttonListener = null;
        listItemClickListener = null;
        listContent = null;
        listSelectedItem = -1;
    }

    /**
     * set title
     *
     * @param title title
     */
    public Dialogs setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * set text
     *
     * @param text text
     */
    public Dialogs setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * set cancelable
     *
     * @param cancelAble set AlertDialog or ProgressDialog cancelAble
     */
    public Dialogs setCancelAble(boolean cancelAble) {
        this.cancelAble = cancelAble;
        return this;
    }

    /**
     * set outside cancelAble
     *
     * @param outsideCancelAble set AlertDialog or ProgressDialog cancelAble
     */
    public Dialogs setOutsideCancelAble(boolean outsideCancelAble) {
        this.outsideCancelAble = outsideCancelAble;
        return this;
    }

    /**
     * set negative button text
     *
     * @param buttonTextNegative set AlertDialog negative Button text
     */
    public Dialogs setButtonTextNegative(String buttonTextNegative) {
        this.buttonTextNegative = buttonTextNegative;
        return this;
    }

    /**
     * set positive button text
     *
     * @param buttonTextPositive set AlertDialog positive Button text
     */
    public Dialogs setButtonTextPositive(String buttonTextPositive) {
        this.buttonTextPositive = buttonTextPositive;
        return this;
    }

    /**
     * set positive button text
     *
     * @param buttonTextNeutral set AlertDialog neutral Button text
     */
    public Dialogs setButtonTextNeutral(String buttonTextNeutral) {
        this.buttonTextNeutral = buttonTextNeutral;
        return this;
    }

    /**
     * set OnCancelListener
     *
     * @param onCancelListener set onCancelListener for AlertDialog or ProgressDialog
     */
    public Dialogs setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    /**
     * set ButtonListener, for listening positive or negative response
     *
     * @param buttonListener set ButtonListener for AlertDialog
     */
    public Dialogs setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
        return this;
    }

    /**
     * set dialog type to list
     */
    public Dialogs setIsListDialog() {
        isListDialog = true;
        return this;
    }

    /**
     * set DialogInterface.OnClickListener for DIALOG_LIST
     *
     * @param listItemClickListener DialogInterface.OnClickListener for AlertDialog
     */
    public Dialogs setListItemClickListener(DialogInterface.OnClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
        return this;
    }

    /**
     * set list content for DIALOG_LIST
     *
     * @param listContent content for the list
     */
    public Dialogs setListContent(String[] listContent) {
        if (listContent == null) {
            return this;
        }
        this.listContent = listContent;
        return this;
    }

    /**
     * set list content selected index
     *
     * @param index index of the selected
     */
    public Dialogs setListSelectedItem(int index) {
        this.listSelectedItem = index;
        return this;
    }

    /**
     * showDialog ProgressDialog, AlertDialog or Toast
     *
     * @param context mandatory param
     */
    public void showProgress(Context context) {
        if (context == null) {
            return;
        }
        try {
            progressdialog = ProgressDialog.show(context, title, text, false);
            progressdialog.setCancelable(cancelAble);
            progressdialog.setOnCancelListener(onCancelListener);
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hide the ProgressDialog if any
     */
    public static void hideProgress() {
        try {
            if (progressdialog != null) {
                progressdialog.cancel();
            }
            progressdialog = null;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * showDialog ProgressDialog, AlertDialog or Toast
     *
     * @param context mandatory param
     */
    public void showDialog(Context context) {
        if (context == null) {
            return;
        }
        if (!isListDialog) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(text);
            alertDialog.setCancelable(cancelAble);
            alertDialog.setOnCancelListener(onCancelListener);
            alertDialog.setCanceledOnTouchOutside(outsideCancelAble);

            if (buttonTextNegative != null) {
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, buttonTextNegative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (buttonListener != null) {
                            buttonListener.onNegative();
                        }
                    }
                });
            }
            if (buttonTextNeutral != null) {
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonTextNeutral, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (buttonListener != null) {
                            buttonListener.onNeutral();
                        }
                    }
                });
            }
            if (buttonTextPositive == null) {
                buttonTextPositive = "Ok";
            }
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, buttonTextPositive, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    if (buttonListener != null) {
                        buttonListener.onPositive();
                    }
                }
            });
            alertDialog.show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setCancelable(cancelAble);
            builder.setOnCancelListener(onCancelListener);

            if(listSelectedItem == -1) {
                builder.setItems(listContent, listItemClickListener);

            } else {
                builder.setSingleChoiceItems(listContent, listSelectedItem, listItemClickListener);

            }

            AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(outsideCancelAble);
            alertDialog.show();
        }
    }

}