package com.example.tms;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tms.Entities.VolumeEntity;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getCanonicalName();
    private static final String DB_NAME= "TMS.db";
    private static final String TABLE_NAME = "RAWDATA";
    private static final String ID = "ID";
    private static final String COUNT = "COUNT";
    private static final String SPEED = "SPEED";
    private static final String TIMESTAMP = "TIMESTAMP";
    private static final String FACILITY = "FACILITY";
    private static final String FACILITY_TYPE = "FACILITY_TYPE";
    private static final String COMMAND_CREATE = "CREATE TABLE %s (" +
            "%s VARCHAR(50) PRIMARY KEY, " +
            "%s INT, " +
            "%s DECIMAL(4, 2)," +
            "%s DATE," +
            "%s VARCHAR(20), " +
            "%s VARCHAR(20))";
    private static final String COMMAND_DROP = "DROP TABLE IF EXISTS %s";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        Log.d(TAG, "DatabaseHelper: Initialized");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: Initialized");
        Log.d(TAG, "onCreate: CREATING TABLE");
        db.execSQL(String.format(COMMAND_CREATE,TABLE_NAME,
                ID,
                COUNT,
                SPEED,
                TIMESTAMP,
                FACILITY,
                FACILITY_TYPE));
        Log.d(TAG, "onCreate: TABLE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: Initializing");
        db.execSQL(String.format(COMMAND_DROP, TABLE_NAME));
        onCreate(db);
    }

    public boolean insertData(ArrayList<VolumeEntity> objects) {
        Log.d(TAG, "insertData: Started");
        long result = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        for( VolumeEntity volumeEntity : objects) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, volumeEntity.getId());
            contentValues.put(COUNT, volumeEntity.getCount());
            contentValues.put(SPEED, volumeEntity.getSpeed() );
            contentValues.put(TIMESTAMP, volumeEntity.getTimestamp().toString());
            contentValues.put(FACILITY, volumeEntity.getFacility());
            contentValues.put(FACILITY_TYPE, volumeEntity.getFacility_type());
            result = db.insert(TABLE_NAME, null, contentValues);
        }
        Log.d(TAG, "insertData: result - " + String.valueOf(result));
        return result > 0 ? true : false;
    }
}
