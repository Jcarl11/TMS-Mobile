package com.example.tms.FragmentReports;


import android.database.sqlite.SQLiteDatabase;
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

import com.example.tms.DatabaseHelper;
import com.example.tms.InitializeGraph;
import com.example.tms.Period;
import com.example.tms.R;
import com.example.tms.TrafficVolumeDAO;
import com.example.tms.Entities.VolumeReportEntity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;

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
    InitializeGraph initializeGraph;
    public VolumeFragment() {}

    @BindView(R.id.volume_spinner_period) BetterSpinner periodSpinner;
    @BindView(R.id.volume_graph) GraphView volume_graph;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        trafficVolumeDAO = new TrafficVolumeDAO(db);
        periodSpinner.setAdapter(spinnerAdapter());
        periodSpinner.setOnItemClickListener(spinnerListener());
        initializeGraph = new InitializeGraph(volume_graph);
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
                    initializeGraph.setxAxisValues(xValue);
                    initializeGraph.setyAxisValues(yValue);
                    initializeGraph.initialize();
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
                    initializeGraph.setxAxisValues(xValue);
                    initializeGraph.setyAxisValues(yValue);
                    initializeGraph.initialize();
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
                    initializeGraph.setxAxisValues(xValue);
                    initializeGraph.setyAxisValues(yValue);
                    initializeGraph.initialize();
                }
            }
        };
        return listener;
    }

}
