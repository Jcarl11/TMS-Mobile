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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.example.tms.DatabaseHelper;
import com.example.tms.Entities.AvgReportEntity;
import com.example.tms.InitializeLineGraph;
import com.example.tms.Period;
import com.example.tms.R;
import com.example.tms.TrafficSpeedDAO;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvgSpeedFragment extends Fragment {
    private static String TAG = AvgSpeedFragment.class.getCanonicalName();
    SQLiteDatabase db;
    TrafficSpeedDAO trafficSpeedDAO;
    private static String[] PERIODS = {"All", "Last 7 days", "Last 30 days"};
    ArrayList<AvgReportEntity> reports;
    InitializeLineGraph initializeLineGraph;
    @BindView(R.id.avgspeed_period) BetterSpinner avgspeed_period;
    @BindView(R.id.chart_view) AnyChartView chart_view;
    public AvgSpeedFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avg_speed, container, false);
        ButterKnife.bind(this, view);
        initializeLineGraph = new InitializeLineGraph(chart_view);
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        trafficSpeedDAO = new TrafficSpeedDAO(db);
        avgspeed_period.setOnItemClickListener(spinnerListener());
        avgspeed_period.setAdapter(spinnerAdapter());

        return view;
    }

    private ArrayAdapter<String> spinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                PERIODS);
        return adapter;
    }

    private AdapterView.OnItemClickListener spinnerListener() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficSpeedDAO.getAvgSpeedReport(Period.ALL);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayDataSpeed(reports);
                } else if(position == 1){
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficSpeedDAO.getAvgSpeedReport(Period.LAST_7_DAYS);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayDataSpeed(reports);
                } else if(position == 2){
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                    reports = trafficSpeedDAO.getAvgSpeedReport(Period.LAST_30_DAYS);
                    initializeLineGraph.initializeChart();
                    initializeLineGraph.displayDataSpeed(reports);
                }
            }
        };
        return listener;
    }

}
