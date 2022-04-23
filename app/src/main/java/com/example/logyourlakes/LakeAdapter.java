package com.example.logyourlakes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LakeAdapter extends RecyclerView.Adapter<LakeAdapter.myViewHolder> {

    private Context context;
    private ArrayList<Lake> listLakes;

    public LakeAdapter(Context context, ArrayList<Lake> listLakes) {
        this.context = context;
        this.listLakes = listLakes;
    }

    @NonNull
    @Override
    public LakeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent,
                false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LakeAdapter.myViewHolder holder, int position) {
        Lake lake = listLakes.get(position);
        holder.nameText.setText(lake.getName());
        holder.municipalityText.setText(lake.getMunicipality());
        holder.depthText.setText("Deepest depth: " + lake.getDeepestDepth() + "km");
        holder.shorelineText.setText("Shoreline length: " + lake.getShorelineLength() + "km");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listLakes.size();
    }

    // Creating custom ViewHolder for cards in the RecyclerView
    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText, municipalityText, depthText, shorelineText;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.lakeNameText);
            municipalityText = itemView.findViewById(R.id.municipalityText);
            depthText = itemView.findViewById(R.id.depthText);
            shorelineText = itemView.findViewById(R.id.shorelineText);
        }
    }

}
