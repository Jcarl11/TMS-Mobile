package com.example.tms.FragmentReports;


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

import com.example.tms.R;
import com.example.tms.R2;
import com.weiwangcn.betterspinner.library.BetterSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolumeFragment extends Fragment {
    private static String TAG = "VolumeFragment";
    private static String[] PERIODS = {"All", "Last 7 days", "Last 30 days"};
    public VolumeFragment() {}

    @BindView(R2.id.volume_spinner_period)
    BetterSpinner periodSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
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

}
