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
public class VolumeFragment extends Fragment {
    public VolumeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);

        return view;
    }

}
