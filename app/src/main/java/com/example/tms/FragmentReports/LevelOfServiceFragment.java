package com.example.tms.FragmentReports;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelOfServiceFragment extends Fragment {


    public LevelOfServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_of, container, false);
        return view;
    }

}
