package com.example.tms;

import android.util.Log;

import com.example.tms.Entities.LevelOfServiceEntity;
import com.example.tms.Entities.VolumeEntity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CloudOperations {
    public static String TAG = CloudOperations.class.getCanonicalName();
    public static String OBJECT_ID = "objectId";
    public static String ID = "id";
    public static String FACILITY = "facility";
    public static String FACILITY_TYPE = "facility_type";
    public static String SPEED = "speed";
    public static String COUNT = "count";
    public static String TIMESTAMP = "timestamp";
    public static String IS_SYNCED = "isSynced";
    private boolean UNSYNCED = true;
    private boolean SYNCED = false;
    public CloudOperations() {
        Log.d(TAG, "CloudOperations: started");
    }

    public ArrayList<VolumeEntity> getAll() {
        Log.d(TAG, "getAll: started");
        ArrayList<VolumeEntity> volumeEntityArrayList = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("RAWDATA");
        query.orderByAscending("timestamp");
        query.setLimit(1000);
        try {
            List<ParseObject> result = query.find();
            Log.d(TAG, "getAll: result size: " + result.size());
            for( ParseObject object : result) {
                VolumeEntity volumeEntity = new VolumeEntity();
                volumeEntity.setObjectId(object.getObjectId());
                volumeEntity.setId(object.getString(ID));
                volumeEntity.setFacility(object.getString(FACILITY));
                volumeEntity.setFacility_type(object.getString(FACILITY_TYPE));
                volumeEntity.setSpeed(object.getNumber(SPEED).doubleValue());
                volumeEntity.setCount((Integer) object.getNumber(COUNT));
                volumeEntity.setTimestamp(object.getString(TIMESTAMP));

                Log.d(TAG, "timestamp - " + volumeEntity.getTimestamp() );
                volumeEntity.setSynced(object.getBoolean(IS_SYNCED));
                volumeEntityArrayList.add(volumeEntity);
            }
            Log.d(TAG, "getAll: Retrieve finished");
            //boolean updateResult = updateSyncedRecords(result);
            //Log.d(TAG, "getAll: updateSyncedRecords = " + String.valueOf(updateResult));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "getAll: Exception: " + e.getMessage());
        }
        return volumeEntityArrayList;
    }

    private boolean updateSyncedRecords(List<ParseObject> objects) {
        int failedOperations = 0;
        Log.d(TAG, "updateSyncedRecords: starting");
        for ( ParseObject object : objects ) {
            object.put(IS_SYNCED, SYNCED);
            try {
                object.save();
                Log.d(TAG, "updateSyncedRecords: " + object.getObjectId() );
            } catch (ParseException e) {
                e.printStackTrace();
                failedOperations++;
                Log.d(TAG, "updateSyncedRecords: " + e.getMessage());
            }
        }
        Log.d(TAG, "updateSyncedRecords: failed: " + String.valueOf(failedOperations) );
        return failedOperations == 0 ? true : false;
    }

}
