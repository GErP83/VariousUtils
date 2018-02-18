package com.gerp83.variousutils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerp83
 * Utils for handling permissions.
 */
public class PermissionUtils {


    /**
     * Calculates the list of permissions which is not already granted for the app.
     *
     * @param context     For checking the permission's availability.
     * @param permissions The list of permissions to check for.
     * @return An array of permissions which are not already granted.
     */
    private static String[] getNeededPermissionsList(Context context, String... permissions) {
        if (context == null || permissions == null || permissions.length == 0) {
            return null;
        }
        List<String> permissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(permission);
            }
        }
        return (String[]) permissionsNeeded.toArray();
    }

    /**
     * Checks whether the permission is granted for the app or not.
     *
     * @param context     For checking the permissions.
     * @param permission  Permission to check for.
     * @return If the permission is granted, then true. Otherwise false.
     */
    public static boolean isGranted(Context context, String permission) {
        if (context == null || permission == null || permission.length() == 0) {
            return false;
        }
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * Use this if the request callback is handled in an activity.
     *
     * @param object The activity or fragment where the request callback is handled.
     * @param resultCode Result code.
     * @param permissions The needed permissions.
     */
    public static void request(Object object, Context context, int resultCode, String... permissions) {
        String [] neededPermissions = getNeededPermissionsList(context, permissions);
        if(neededPermissions == null || neededPermissions.length == 0) {
            return;
        }
        if(object instanceof ActivityCompat) {
            ActivityCompat.requestPermissions((Activity) object, neededPermissions, resultCode);

        } else if(object instanceof Fragment) {
            ((Fragment)object).requestPermissions(neededPermissions, resultCode);

        }
    }

}