package com.example.logyourlakes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.myViewHolder> {

    private Context context;
    private ArrayList<Activity> listActivities;

    public ActivityAdapter(Context context, ArrayList<Activity> listActivities) {
        this.context = context;
        this.listActivities = listActivities;
    }

    @NonNull
    @Override
    public ActivityAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_card_layout,
                parent, false);
        return new myViewHolder(view);
    }

    // Putting values in cards
    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.myViewHolder holder, int position) {
        Activity activity = listActivities.get(position);
        holder.lakeName.setText(activity.getLakeName());
        holder.date.setText(activity.getDate());
        holder.type.setText(activity.getType());
        holder.timeSpent.setText("Time spent: " + activity.getTimeSpent());
    }

    @Override
    public int getItemCount() {
        return listActivities.size();
    }

    // Creating a custom viewHolder for items in RecyclerView
    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView lakeName, date, type, timeSpent;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            lakeName = itemView.findViewById(R.id.lakeNameActivity);
            date = itemView.findViewById(R.id.dateText);
            type = itemView.findViewById(R.id.typeText);
            timeSpent = itemView.findViewById(R.id.timeText);
        }
    }
}