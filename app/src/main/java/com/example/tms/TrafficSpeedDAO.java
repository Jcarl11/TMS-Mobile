package com.example.tms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tms.Entities.AvgReportEntity;

import java.util.ArrayList;

public class TrafficSpeedDAO {
    private static final String TAG = TrafficSpeedDAO.class.getCanonicalName();
    private static final String COMMAND_LAST7DAYS = "select round(avg(SPEED), 2) AVG_SPEED, strftime('%m-%d', timestamp) DATE from rawdata\n" +
            "where timestamp between (SELECT DATETIME('now', '-7 day')) \n" +
            "and (SELECT date('now', '1 day')) \n" +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    private static final String COMMAND_LAST30DAYS = "select round(avg(SPEED), 2) AVG_SPEED, strftime('%m-%d', timestamp) DATE from rawdata\n" +
            "where timestamp between (SELECT DATETIME('now', '-30 day')) \n" +
            "and (SELECT date('now', '1 day')) \n" +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    private static final String COMMAND_ALL = "select round(avg(SPEED), 2) AVG_SPEED, strftime('%m-%d', timestamp) DATE from rawdata " +
            "group by strftime('%m-%d', timestamp) order by timestamp asc";
    SQLiteDatabase db;

    public TrafficSpeedDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<AvgReportEntity> getAvgSpeedReport(Period period) {

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

    private ArrayList<AvgReportEntity> getReport(String command) {
        Log.d(TAG, "getReport: Started");
        ArrayList<AvgReportEntity> avgReportEntities = new ArrayList<>();
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.getCount() > 0) {
            Log.d(TAG, "getReport: cursor " + cursor.getCount());
            while(cursor.moveToNext()) {
                Double avg = cursor.getDouble(0);
                String date = cursor.getString(1);
                avgReportEntities.add(new AvgReportEntity(avg, date));
                Log.d(TAG, "getReport: volume - " + String.valueOf(avg) + " - " + date);
            }
        } else {
            Log.d(TAG, "getReport: cursor Empty");
        }
        return avgReportEntities;
    }
}
