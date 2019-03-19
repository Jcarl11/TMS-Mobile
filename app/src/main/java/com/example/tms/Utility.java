package com.example.tms;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class Utility {
    private static String TAG = Utility.class.getCanonicalName();

    public AlertDialog showLoading(Context context , String title, boolean cancellable) {
        AlertDialog dialog = new SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(cancellable)
                .setMessage(title)
                .build();
        return dialog;
    }

    public static String getLevelOfService(double value) {
        String los = "";
        if (value >= 40)
            los = "EXCELLENT";
        else if (value >= 30 && value < 40)
            los = "GOOD";
        else if (value >= 20 && value < 30)
            los = "FAIR";
        else if (value >= 15 && value < 20)
            los = "PASSED";
        else if (value < 15)
            los = "FAILED";
        else
            los = "INVALID";
        return los;
    }

    public static double convertDate(String date) {
        double doubleVersion = 0;
        try {
            Date formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            Log.d(TAG, "convertDate: formattedDate: " + formattedDate.toString());
            doubleVersion = new Long(formattedDate.getTime()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "convertDate: exception: " + e.getMessage());
        }
        Log.d(TAG, "convertDate: " + String.valueOf(doubleVersion));
        return doubleVersion;
    }


}
