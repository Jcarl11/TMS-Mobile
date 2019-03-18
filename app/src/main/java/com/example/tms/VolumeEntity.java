package com.example.tms;

import java.util.Date;

public class VolumeEntity {
    private String objectId;
    private String id;
    private String facility;
    private String facility_type;
    private double speed;
    private double count;
    private String timestamp;
    private boolean isSynced;

    public VolumeEntity() {}

    public VolumeEntity(String objectId, String id, String facility, String facility_type, double speed, double count, String timestamp, boolean isSynced) {
        this.objectId = objectId;
        this.id = id;
        this.facility = facility;
        this.facility_type = facility_type;
        this.speed = speed;
        this.count = count;
        this.timestamp = timestamp;
        this.isSynced = isSynced;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFacility_type() {
        return facility_type;
    }

    public void setFacility_type(String facility_type) {
        this.facility_type = facility_type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
}
