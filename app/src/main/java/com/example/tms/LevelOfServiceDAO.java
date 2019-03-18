package com.example.tms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tms.Entities.LevelOfServiceEntity;

import java.util.ArrayList;

public class LevelOfServiceDAO {

    private static final String TAG = LevelOfServiceDAO.class.getCanonicalName();
    private static String COMMAND = "select strftime('%H', timestamp) HOUR, \n" +
            "SUM(COUNT) VOLUME, \n" +
            "round(AVG(SPEED), 2) AVG_SPEED, \n" +
            "FACILITY, FACILITY_TYPE from rawdata group by HOUR order by timestamp asc;";
    SQLiteDatabase db;

    public LevelOfServiceDAO(SQLiteDatabase db) {
        Log.d(TAG, "LevelOfServiceDAO: Started");
        this.db = db;
    }

    public ArrayList<LevelOfServiceEntity> getLevelOfServiceReport() {
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
            }
        } else {
            Log.d(TAG, "getLevelOfServiceReport: cursor empty");
        }
        return levelOfServiceEntities;
    }
}
