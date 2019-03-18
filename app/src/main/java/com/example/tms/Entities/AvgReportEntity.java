package com.example.tms.Entities;

public class AvgReportEntity {
    private Double avg;
    private String date;

    public AvgReportEntity(Double avg, String date) {
        this.avg = avg;
        this.date = date;
    }

    public AvgReportEntity() {}

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
