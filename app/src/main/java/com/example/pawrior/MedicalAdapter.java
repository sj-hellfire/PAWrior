package com.example.pawrior;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicalAdapter extends RecyclerView.Adapter<MedicalViewHolder> {
    List<MedicalRecord> partList;
    public MedicalAdapter(List<MedicalRecord> partList) {
        this.partList = partList;
    }

    @NonNull
    @Override
    public MedicalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // view_g => name of the layout file
        View view = inflater.inflate(R.layout.view_medical_records, parent, false);
        MedicalViewHolder holder = new MedicalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalViewHolder holder, int position) {
        MedicalRecord parts = partList.get(position);
        holder.populate(parts);
    }

    @Override
    public int getItemCount() {
        return partList.size();
    }
}
