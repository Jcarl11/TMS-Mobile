package com.example.tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tms.Entities.LevelOfServiceEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LevelOfServiceAdapter extends RecyclerView.Adapter<LevelOfServiceAdapter.LevelOfServiceViewHolder> {

    private Context context;
    private List<LevelOfServiceEntity> levelOfServiceEntityList;

    public LevelOfServiceAdapter(Context context, List<LevelOfServiceEntity> levelOfServiceEntityList) {
        this.context = context;
        this.levelOfServiceEntityList = levelOfServiceEntityList;
    }

    @NonNull
    @Override
    public LevelOfServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.lvlofservice_row_layout, parent, false);
        return new LevelOfServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelOfServiceViewHolder holder, int position) {
        LevelOfServiceEntity levelOfServiceEntity = levelOfServiceEntityList.get(position);
        holder.mHour.setText(levelOfServiceEntity.getHour());
        holder.mFacility.setText(levelOfServiceEntity.getFacility());
        holder.mFacilityType.setText(levelOfServiceEntity.getFacilityType());
        holder.mVolume.setText(String.valueOf(levelOfServiceEntity.getVolume()));
        holder.mAverageSpeed.setText(String.valueOf(levelOfServiceEntity.getAvgSpeed()));
        holder.mLos.setText(levelOfServiceEntity.getLvlOfService());
    }

    @Override
    public int getItemCount() {
        return levelOfServiceEntityList.size();
    }

    public class LevelOfServiceViewHolder extends RecyclerView.ViewHolder{
        TextView mHour, mFacility, mFacilityType, mVolume, mAverageSpeed, mLos;
        public LevelOfServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            mHour = itemView.findViewById(R.id.lvlofservice_hour);
            mFacility = itemView.findViewById(R.id.lvlofservice_facility);
            mFacilityType = itemView.findViewById(R.id.lvlofservice_facilitytype);
            mVolume = itemView.findViewById(R.id.lvlofservice_volume);
            mAverageSpeed = itemView.findViewById(R.id.lvlofservice_avgspeed);
            mLos = itemView.findViewById(R.id.lvlofservice_los);
        }
    }
}
