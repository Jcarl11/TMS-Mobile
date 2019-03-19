package com.example.tms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tms.Entities.VolumeReportEntity;

import java.util.ArrayList;

public class TrafficVolumeDAO {
    private static final String TAG = TrafficVolumeDAO.class.getCanonicalName();
    private static final String COMMAND_LAST7DAYS = "select SUM(COUNT) VOLUME, strftime('%m-%d', timestamp) DATE from rawdata\n" +
            "where timestamp between (SELECT DATETIME('now', '-7 day')) \n" +
            "and (SELECT date('now', '1 day')) \n" +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    private static final String COMMAND_LAST30DAYS = "select SUM(COUNT) VOLUME, strftime('%m-%d', timestamp) DATE from rawdata\n" +
            "where timestamp between (SELECT DATETIME('now', '-30 day')) \n" +
            "and (SELECT date('now', '1 day')) \n" +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    private static final String COMMAND_ALL = "select SUM(COUNT) VOLUME, strftime('%m-%d', timestamp) DATE from rawdata " +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    SQLiteDatabase db;

    public TrafficVolumeDAO(SQLiteDatabase db) {
        Log.d(TAG, "TrafficVolumeDAO: Started");
        this.db = db;
    }

    public ArrayList<VolumeReportEntity> getVolumeReport(Period period) {
        
        switch (period){
            case LAST_7_DAYS:
                return getReport(COMMAND_LAST7DAYS);
            case LAST_30_DAYS:
                return getReport(COMMAND_LAST30DAYS);
            case ALL:
                return getReport(COMMAND_ALL);
        }
        return null;
    }

    private ArrayList<VolumeReportEntity> getReport(String command) {
        Log.d(TAG, "getReport: Started");
        ArrayList<VolumeReportEntity> volumeReportEntities = new ArrayList<>();
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.getCount() > 0) {
            Log.d(TAG, "getReport: cursor " + cursor.getCount());
            while(cursor.moveToNext()) {
                Double volume = cursor.getDouble(0);
                String date = cursor.getString(1);
                volumeReportEntities.add(new VolumeReportEntity(volume, date));
                Log.d(TAG, "getReport: volume - " + String.valueOf(volume) + " - " + date);
            }
        } else {
            Log.d(TAG, "getReport: cursor Empty");
        }
        return volumeReportEntities;
    }
}
