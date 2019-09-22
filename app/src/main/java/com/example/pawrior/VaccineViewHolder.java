package com.example.pawrior;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VaccineViewHolder extends RecyclerView.ViewHolder {
    TextView vaccineName, vaccinationDate, doctorName, immunityAgainst, dogAge;
    public VaccineViewHolder(@NonNull View itemView) {
        super(itemView);
        vaccineName = itemView.findViewById(R.id.vaccine_name);
        vaccinationDate = itemView.findViewById(R.id.vaccination_date);
        doctorName = itemView.findViewById(R.id.doctor_name);
        immunityAgainst = itemView.findViewById(R.id.immunizes);
        dogAge = itemView.findViewById(R.id.dog_age);
    }

    public void populate(Vaccines parts) {
        vaccineName.setText(parts.getVaccineName());
        vaccinationDate.setText(parts.getVaccinationDate());
        doctorName.setText(parts.getDoctorName());
        immunityAgainst.setText(parts.getImmunityAgainst());
        dogAge.setText(parts.getDogAge());
    }
}
