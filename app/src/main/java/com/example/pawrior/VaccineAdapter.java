package com.example.pawrior;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineViewHolder> {
        List<Vaccines> partList;
        
        public VaccineAdapter(List<Vaccines> partList) { 
            this.partList = partList;
        }

        @NonNull
        @Override
        public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.view_vaccines, parent, false);
            VaccineViewHolder holder = new VaccineViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
            Vaccines parts = partList.get(position);
            holder.populate(parts);
        }

        @Override
        public int getItemCount() {
            return partList.size();
            }
}

