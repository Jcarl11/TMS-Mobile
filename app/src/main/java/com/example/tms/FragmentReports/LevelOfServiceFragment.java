package com.example.tms.FragmentReports;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.tms.DatabaseHelper;
import com.example.tms.Entities.LevelOfServiceEntity;
import com.example.tms.LevelOfServiceAdapter;
import com.example.tms.LevelOfServiceDAO;
import com.example.tms.R;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelOfServiceFragment extends Fragment {
    @BindView(R.id.lvlofservice_recyclerview)
    RecyclerView lvlofservice_recyclerview;
    @BindView(R.id.lvlofservice_date) Button lvlofservice_date;
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
        return view;
    }

    @OnClick(R.id.lvlofservice_date)
    public void dateClicked(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                AlertDialog.THEME_HOLO_LIGHT,
                listener(),
                year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener(){
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month +=  1;
                String date = String.format("%s-%s-%s", String.valueOf(year), formatNumber(month), formatNumber(dayOfMonth));
                lvlofservice_date.setText("DATE: " + date);
                List<LevelOfServiceEntity> levelOfServiceEntities = levelOfServiceDAO.getLevelOfServiceReport(date);
                levelOfServiceAdapter = new LevelOfServiceAdapter(getContext(), levelOfServiceEntities);
                lvlofservice_recyclerview.setAdapter(levelOfServiceAdapter);
            }
        };
        return listener;
    }
    private String formatNumber(int num){
        return (num - 9) <= 0 ? String.format("%02d", num) : String.valueOf(num);
    }

}
