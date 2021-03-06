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

import com.anychart.AnyChartView;
import com.example.tms.DatabaseHelper;
import com.example.tms.InitializeLineGraph;
import com.example.tms.Period;
import com.example.tms.R;
import com.example.tms.TrafficVolumeDAO;
import com.example.tms.Entities.VolumeReportEntity;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolumeFragment extends Fragment {
    private static final String TAG = "VolumeFragment";

    SQLiteDatabase db;
    TrafficVolumeDAO trafficVolumeDAO;
    private static String[] PERIODS = {"All", "Last 7 days", "Last 30 days"};

    public VolumeFragment() {}

    @BindView(R.id.volume_spinner_period) BetterSpinner periodSpinner;
    @BindView(R.id.any_chart_view) AnyChartView any_chart_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: Started");

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
                Log.d(TAG, "onItemClick: Initalized");
                if (position == 0) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    db = new DatabaseHelper(getContext()).getWritableDatabase();
                    trafficVolumeDAO = new TrafficVolumeDAO(db);
                    ArrayList<VolumeReportEntity> reports = trafficVolumeDAO.getVolumeReport(Period.ALL);
                    Log.d(TAG, "onItemClick: reports size: " + String.valueOf(reports.size()));
                    InitializeLineGraph initializeLineGraph = new InitializeLineGraph(any_chart_view);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayData(reports);
                } else if (position == 1) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    db = new DatabaseHelper(getContext()).getWritableDatabase();
                    trafficVolumeDAO = new TrafficVolumeDAO(db);
                    ArrayList<VolumeReportEntity> reports = trafficVolumeDAO.getVolumeReport(Period.LAST_7_DAYS);
                    Log.d(TAG, "onItemClick: reports size: " + String.valueOf(reports.size()));
                    InitializeLineGraph initializeLineGraph = new InitializeLineGraph(any_chart_view);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayData(reports);
                } else if (position == 2) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    db = new DatabaseHelper(getContext()).getWritableDatabase();
                    trafficVolumeDAO = new TrafficVolumeDAO(db);
                    ArrayList<VolumeReportEntity> reports = trafficVolumeDAO.getVolumeReport(Period.LAST_30_DAYS);
                    Log.d(TAG, "onItemClick: reports size: " + String.valueOf(reports.size()));
                    InitializeLineGraph initializeLineGraph = new InitializeLineGraph(any_chart_view);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayData(reports);
                }
            }
        };
        return listener;
    }

}
