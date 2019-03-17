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

}
