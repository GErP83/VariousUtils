package com.gerp83.variousutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by gerp83
 * Contains methods for navigating from a fragment / activity to another activity.
 */
public class Navigator {

    private Activity activityFrom = null;
    private Fragment fragmentFrom = null;
    private Context contextFrom = null;

    private Class targetClass = null;
    private HashMap<String, Object> dataMap = null;
    private int intentFlags = -1;
    private String action = null;
    private int requestCode = -1;
    private int resultCode = Activity.RESULT_OK;

    private boolean finish = false;
    private boolean forResult = false;
    private boolean returnResult = false;
    private boolean clear = false;

    /**
     * init with an Activity
     *
     * @param activity Activity
     */
    public static Navigator from(Activity activity) {
        return new Navigator(activity);
    }

    /**
     * init with a Fragment
     *
     * @param fragment Fragment
     */
    public static Navigator from(Fragment fragment) {
        return new Navigator(fragment);
    }

    /**
     * from init a Context, Intent.FLAG_ACTIVITY_NEW_TASK will be added to Intent flags
     *
     * @param context Context
     */
    public static Navigator from(Context context) {
        return new Navigator(context);
    }

    private Navigator(Activity activity) {
        activityFrom = activity;
    }

    private Navigator(Context context) {
        contextFrom = context;
    }

    private Navigator(Fragment fragment) {
        fragmentFrom = fragment;
    }

    /**
     * set action for Intent
     *
     * @param newAction add action to the Intent
     */
    public Navigator action(String newAction) {
        action = newAction;
        return this;
    }

    /**
     * add any type of data to send with Intent
     *
     * @param key key
     * @param data Object
     */
    public Navigator data(String key, Object data) {
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
    public Navigator flags(int intentFlags) {
        this.intentFlags = intentFlags;
        return this;
    }

    /**
     * opens target Activity
     *
     * @param newTargetClass target Activity class
     */
    public void to(Class newTargetClass){
        if(newTargetClass == null) {
            return;
        }
        targetClass = newTargetClass;
        navigate();
    }

    /**
     * opens target Activity with startActivityForResult, do not use this, with 'from(Context context)'
     *
     * @param newTargetClass target Activity class
     * @param newRequestCode request code to return
     */
    public void forResult(Class newTargetClass, int newRequestCode) {
        if(newTargetClass == null) {
            return;
        }
        targetClass = newTargetClass;
        requestCode = newRequestCode;
        forResult = true;
        navigate();
    }

    /**
     * opens target Activity and finish current Activity, do not use this, with 'from(Context context)'
     *
     * @param newTargetClass target Activity class
     */
    public void finishWith(Class newTargetClass) {
        if(newTargetClass == null) {
            return;
        }
        targetClass = newTargetClass;
        finish = true;
        navigate();
    }

    /**
     * finish current Activity and intent.setResult(Activity.RESULT_OK, intent) will be called, do not use this, with 'from(Context context)'
     */
    public void returnResult() {
        returnResult = true;
        navigate();
    }

    /**
     * finish current Activity and the given new result code will be added to the returned Intent, do not use this, with 'from(Context context)'
     *
     * @param newResultCode target Activity class
     */
    public void returnResult(int newResultCode) {
        resultCode = newResultCode;
        returnResult = true;
        navigate();
    }

    /**
     * opens target Activity and clear all other opened Activities, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK will be added to Intent flags
     *
     * @param newTargetClass target Activity class
     */
    public void clearWith(Class newTargetClass) {
        targetClass = newTargetClass;
        clear = true;
        navigate();
    }

    private void navigate() {
        if(activityFrom == null && fragmentFrom == null && contextFrom == null) {
            return;
        }

        Intent intent;
        if(targetClass != null) {
            if(activityFrom != null ) {
                intent = new Intent(activityFrom, targetClass);

            } else if(fragmentFrom != null) {
                if(fragmentFrom.getActivity() == null) {
                    return;
                }
                intent = new Intent(fragmentFrom.getActivity(), targetClass);

            } else {
                intent = new Intent(contextFrom, targetClass);
            }
        } else {
            intent = new Intent();
        }

        if(action != null) {
            intent.setAction(action);
        }
        if(intentFlags != -1) {
            intent.addFlags(intentFlags);
        }
        if(dataMap != null) {
            for (String key : dataMap.keySet()) {
                intent.putExtra(key, (Serializable) dataMap.get(key));
            }
        }

        if (forResult) {
            if(contextFrom != null) {
                throw new RuntimeException("Can not use this, if 'from(Context context)' was used!");

            } else {
                if(fragmentFrom != null) {
                    fragmentFrom.startActivityForResult(intent, requestCode);

                } else {
                    activityFrom.startActivityForResult(intent, requestCode);
                }
            }

        } else if(returnResult) {
            if(contextFrom != null) {
                throw new RuntimeException("Can not use this, if 'from(Context context)' was used!");

            } else {
                if(fragmentFrom != null && fragmentFrom.getActivity() != null) {
                    fragmentFrom.getActivity().setResult(resultCode, intent);
                    fragmentFrom.getActivity().finish();

                } else if(activityFrom != null){
                    activityFrom.setResult(resultCode, intent);
                    activityFrom.finish();
                }
            }

        } else if (finish) {
            if(contextFrom != null) {
                throw new RuntimeException("Can not use this, if 'from(Context context)' was used!");

            } else {
                if(fragmentFrom != null && fragmentFrom.getActivity() != null) {
                    fragmentFrom.startActivity(intent);
                    fragmentFrom.getActivity().finish();

                } else if(activityFrom != null){
                    activityFrom.startActivity(intent);
                    activityFrom.finish();
                }
            }

        } else if (clear) {
            intentFlags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
            intent.addFlags(intentFlags);
            if(contextFrom != null) {
                contextFrom.startActivity(intent);

            } else {
                if(fragmentFrom != null) {
                    fragmentFrom.startActivity(intent);

                } else if(activityFrom != null){
                    activityFrom.startActivity(intent);
                }
            }
        } else {
            if(contextFrom != null) {
                intentFlags = intent.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK;
                intent.addFlags(intentFlags);
                contextFrom.startActivity(intent);

            } else {
                if(fragmentFrom != null) {
                    fragmentFrom.startActivity(intent);

                } else if(activityFrom != null){
                    activityFrom.startActivity(intent);
                }
            }
        }

    }

}
