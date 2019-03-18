package com.example.tms;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SyncTask extends AsyncTask<Void, Void, ArrayList<VolumeEntity>>{
    private static String TAG = SyncTask.class.getCanonicalName();
    Utility utility = new Utility();
    CloudOperations cloudOperations = new CloudOperations();
    DatabaseHelper databaseHelper;
    private Context context;
    AlertDialog dialog;
    public SyncTask(Context context) {
        this.context = context;
        Log.d(TAG, "SyncTask: initialized");
        dialog = utility.showLoading(context, "Please wait", false);
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    protected ArrayList<VolumeEntity> doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: running");
        return cloudOperations.getAll();
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: started");
        dialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<VolumeEntity> volumeEntities) {
        Log.d(TAG, "onPostExecute: started");
        dialog.dismiss();
        if(volumeEntities.size() > 0) {
            Log.d(TAG, "onPostExecute: volumeEntities = " + volumeEntities.size());
            Toast.makeText(context, String.format("%o records synced", volumeEntities.size() ), Toast.LENGTH_LONG).show();
            databaseHelper.insertData(volumeEntities);

        } else {
            Log.d(TAG, "onPostExecute: volumeEntities: Empty");
        }
    }
}
