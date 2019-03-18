package com.example.tms.FragmentReports;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tms.DatabaseHelper;
import com.example.tms.Entities.LevelOfServiceEntity;
import com.example.tms.LevelOfServiceAdapter;
import com.example.tms.LevelOfServiceDAO;
import com.example.tms.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelOfServiceFragment extends Fragment {
    @BindView(R.id.lvlofservice_recyclerview)
    RecyclerView lvlofservice_recyclerview;
    LevelOfServiceAdapter levelOfServiceAdapter;
    LevelOfServiceDAO levelOfServiceDAO;
    SQLiteDatabase db;
    public LevelOfServiceFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_of, container, false);
        ButterKnife.bind(this, view);
        lvlofservice_recyclerview.setHasFixedSize(true);
        lvlofservice_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        levelOfServiceDAO = new LevelOfServiceDAO(db);
        List<LevelOfServiceEntity> levelOfServiceEntities = levelOfServiceDAO.getLevelOfServiceReport();
        levelOfServiceAdapter = new LevelOfServiceAdapter(getContext(), levelOfServiceEntities);
        lvlofservice_recyclerview.setAdapter(levelOfServiceAdapter);
        return view;
    }

}
