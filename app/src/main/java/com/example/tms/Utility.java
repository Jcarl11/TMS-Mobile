package com.example.tms;

import android.app.AlertDialog;
import android.content.Context;

import dmax.dialog.SpotsDialog;

public class Utility {


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

}
