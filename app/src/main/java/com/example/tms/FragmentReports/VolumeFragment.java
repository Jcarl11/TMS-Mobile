package com.example.tms.FragmentReports;


import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.tms.R;
import com.example.tms.R2;
import com.example.tms.SyncTask;
import com.example.tms.Utility;
import com.example.tms.VolumeEntity;
import com.jjoe64.graphview.GraphView;
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
    private static String[] PERIODS = {"All", "Last 7 days", "Last 30 days"};
    public VolumeFragment() {}

    @BindView(R.id.volume_spinner_period) BetterSpinner periodSpinner;
    @BindView(R.id.volume_graph)
    GraphView volume_graph;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
        periodSpinner.setAdapter(spinnerAdapter());
        periodSpinner.setOnItemClickListener(spinnerListener());
        initializeGraph();
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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                } else if (position == 1) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));

                } else if (position == 2) {
                    Log.d(TAG, "onItemClick: " + (String)parent.getItemAtPosition(position));
                }
            }
        };
        return listener;
    }

    private void initializeGraph(){

        // activate horizontal scrolling
        volume_graph.getViewport().setScrollable(true);
        // activate horizontal and vertical zooming and scrolling

        // activate vertical scrolling
        volume_graph.getViewport().setScrollableY(true);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(volume_graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Monday", "Tuesday", "Wednesday"});
        volume_graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        volume_graph.addSeries(series);
    }


}
