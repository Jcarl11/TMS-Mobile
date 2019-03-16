package com.example.tms.FragmentReports;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvgSpeedFragment extends Fragment {


    public AvgSpeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avg_speed, container, false);

        return view;
    }

}
