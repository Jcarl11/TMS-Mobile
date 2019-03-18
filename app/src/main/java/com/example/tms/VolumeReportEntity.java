package com.example.tms;

public class VolumeReportEntity {
    private double volume;
    private String date;

    public VolumeReportEntity() {}

    public VolumeReportEntity(double volume, String date) {
        this.volume = volume;
        this.date = date;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
