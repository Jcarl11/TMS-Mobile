package com.example.tms;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class InitializeGraph {
    private DataPoint[] yAxisValues;
    private String[] xAxisValues;
    private GraphView graphView;

    public InitializeGraph(GraphView graphView) {
        this.graphView = graphView;
    }

    public void initialize() {
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(xAxisValues);
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(yAxisValues);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        graphView.addSeries(series);
    }


    public void setyAxisValues(ArrayList<DataPoint> yAxisValues) {
        DataPoint[] points = new DataPoint[yAxisValues.size()];
        points = yAxisValues.toArray(points);
        this.yAxisValues = points;
    }


    public void setxAxisValues(ArrayList<String> xAxisValues) {
        String[] labels = new String[xAxisValues.size()];
        labels = xAxisValues.toArray(labels);
        this.xAxisValues = labels;
    }
}
