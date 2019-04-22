package com.example.tms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tms.Entities.LevelOfServiceEntity;

import java.util.ArrayList;

public class LevelOfServiceDAO {

    private static final String TAG = "LevelOfServiceDAO";
    SQLiteDatabase db;

    public LevelOfServiceDAO(SQLiteDatabase db) {
        Log.d(TAG, "LevelOfServiceDAO: Started");
        this.db = db;
    }

    public ArrayList<LevelOfServiceEntity> getLevelOfServiceReport(String period) {
        String COMMAND = "select strftime('%H', timestamp) HOUR, SUM(COUNT) VOLUME, ROUND(AVG(SPEED), 2) AVG_SPEED, FACILITY, FACILITY_TYPE FROM RAWDATA WHERE strftime('%Y-%m-%d', timestamp)= '"+period+"' group by HOUR order by timestamp asc";
        Log.d(TAG, "getLevelOfServiceReport: " + COMMAND);
        ArrayList<LevelOfServiceEntity> levelOfServiceEntities = new ArrayList<>();

        Cursor cursor = db.rawQuery(COMMAND, null);
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                LevelOfServiceEntity levelOfServiceEntity = new LevelOfServiceEntity();
                levelOfServiceEntity.setHour(cursor.getString(0));
                levelOfServiceEntity.setVolume(Integer.parseInt(cursor.getString(1)));
                levelOfServiceEntity.setAvgSpeed(Double.parseDouble(cursor.getString(2)));
                levelOfServiceEntity.setFacility(cursor.getString(3));
                levelOfServiceEntity.setFacilityType(cursor.getString(4));
                levelOfServiceEntity.setLvlOfService(Utility.getLevelOfService(levelOfServiceEntity.getAvgSpeed()));
                levelOfServiceEntities.add(levelOfServiceEntity);
                Log.d(TAG, String.format("%s, %s, %s", levelOfServiceEntity.getHour(), String.valueOf(levelOfServiceEntity.getAvgSpeed()), levelOfServiceEntity.getFacility()));
            }
        } else {
            Log.d(TAG, "getLevelOfServiceReport: cursor empty");
        }
        return levelOfServiceEntities;
    }
}
