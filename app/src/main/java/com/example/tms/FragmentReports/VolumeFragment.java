package com.example.tms.FragmentReports;


import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tms.CloudOperations;
import com.example.tms.DatabaseHelper;
import com.example.tms.Period;
import com.example.tms.R;
import com.example.tms.R2;
import com.example.tms.SyncTask;
import com.example.tms.TrafficVolumeDAO;
import com.example.tms.Utility;
import com.example.tms.VolumeEntity;
import com.example.tms.VolumeReportEntity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolumeFragment extends Fragment {
    private static String TAG = VolumeFragment.class.getCanonicalName();
    int index = 0;
    SQLiteDatabase db;
    TrafficVolumeDAO trafficVolumeDAO;
    private static String[] PERIODS = {"All", "Last 7 days", "Last 30 days"};
    ArrayList<VolumeReportEntity> reports;
    ArrayList<DataPoint> yValue;
    ArrayList<String> xValue;
    public VolumeFragment() {}

    @BindView(R.id.volume_spinner_period) BetterSpinner periodSpinner;
    @BindView(R.id.volume_graph)
    GraphView volume_graph;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        trafficVolumeDAO = new TrafficVolumeDAO(db);
        periodSpinner.setAdapter(spinnerAdapter());
        periodSpinner.setOnItemClickListener(spinnerListener());
        return view;
    }
    private ArrayAdapter spinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                PERIODS);
        return adapter;
    }

    private AdapterView.OnItemClickListener spinnerListener(){
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficVolumeDAO.getVolumeReport(Period.ALL);
                    yValue = new ArrayList<>();
                    xValue = new ArrayList<>();
                    index = 0;
                    reports.size();
                    reports.forEach(report -> {
                        yValue.add(new DataPoint(index, report.getVolume()));
                        xValue.add(report.getDate());
                        index++;
                    });
                    initializeGraph(yValue, xValue);
                } else if (position == 1) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficVolumeDAO.getVolumeReport(Period.LAST_7_DAYS);
                    yValue = new ArrayList<>();
                    xValue = new ArrayList<>();
                    index = 0;
                    reports.size();
                    reports.forEach(report -> {
                        yValue.add(new DataPoint(index, report.getVolume()));
                        xValue.add(report.getDate());
                        index++;
                    });
                    initializeGraph(yValue, xValue);
                } else if (position == 2) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficVolumeDAO.getVolumeReport(Period.LAST_30_DAYS);
                    yValue = new ArrayList<>();
                    xValue = new ArrayList<>();
                    index = 0;
                    reports.size();
                    reports.forEach(report -> {
                        yValue.add(new DataPoint(index, report.getVolume()));
                        xValue.add(report.getDate());
                        index++;
                    });
                    initializeGraph(yValue, xValue);
                }
            }
        };
        return listener;
    }

    private void initializeGraph(ArrayList<DataPoint> yValues, ArrayList<String> xValues){
        String[] xVal = new String[xValues.size()];
        xVal = xValues.toArray(xVal);
        DataPoint[] yVal = new DataPoint[yValues.size()];
        yVal = yValues.toArray(yVal);

        volume_graph.getViewport().setYAxisBoundsManual(true);
        volume_graph.getViewport().setMinY(0);
        volume_graph.getViewport().setMaxY(20);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(volume_graph);
        staticLabelsFormatter.setHorizontalLabels(xVal);

        volume_graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(yVal);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        volume_graph.addSeries(series);
    }


}
