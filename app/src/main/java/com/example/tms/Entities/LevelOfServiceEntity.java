package com.example.tms.Entities;

public class LevelOfServiceEntity {
    public String hour;
    public String facility;
    public String facilityType;
    public int volume;
    public double avgSpeed;
    public String lvlOfService;

    public LevelOfServiceEntity() {}

    public LevelOfServiceEntity(String hour, String facility, String facilityType, int volume, double avgSpeed, String lvlOfService) {
        this.hour = hour;
        this.facility = facility;
        this.facilityType = facilityType;
        this.volume = volume;
        this.avgSpeed = avgSpeed;
        this.lvlOfService = lvlOfService;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getLvlOfService() {
        return lvlOfService;
    }

    public void setLvlOfService(String lvlOfService) {
        this.lvlOfService = lvlOfService;
    }
}
