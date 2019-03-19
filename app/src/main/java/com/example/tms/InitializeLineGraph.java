package com.example.tms;

import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.tms.Entities.AvgReportEntity;
import com.example.tms.Entities.VolumeReportEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InitializeLineGraph {
    private static String TAG = InitializeLineGraph.class.getCanonicalName();
    private AnyChartView chartView;
    private Cartesian cartesian;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
    public InitializeLineGraph(AnyChartView chartView) {
        this.chartView = chartView;
    }

    public void initializeChart() {
        cartesian = AnyChart.line();
        cartesian.xScroller(true);
        cartesian.animation(true);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
    }

    public void displayData(ArrayList<VolumeReportEntity> data) {
        Log.d(TAG, "displayData: started");
        Log.d(TAG, "displayData: data size " + String.valueOf(data.size()));
        List<DataEntry> seriesData = new ArrayList<>();
        for(VolumeReportEntity volumeReportEntity : data) {
            seriesData.add(new ValueDataEntry(volumeReportEntity.getDate(), volumeReportEntity.getVolume()));
        }
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Average Volume");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        chartView.setChart(cartesian);
    }

    public void displayDataSpeed(ArrayList<AvgReportEntity> data) {
        List<DataEntry> seriesData = new ArrayList<>();
        for(AvgReportEntity avgReportEntity : data) {
            seriesData.add(new ValueDataEntry(avgReportEntity.getDate(), avgReportEntity.getAvg()));
        }
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Average Speed");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        chartView.setChart(cartesian);
    }
}
