package com.example.pawrior;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MedicalViewHolder extends RecyclerView.ViewHolder{

    private TextView doctorName, clinicName, diagnosis, symptoms, medicinies, nextConsultation, visitDate;

    public MedicalViewHolder(@NonNull View itemView) {
        super(itemView);
        doctorName = itemView.findViewById(R.id.doctor_name);
        clinicName = itemView.findViewById(R.id.clinic_name);
        diagnosis = itemView.findViewById(R.id.diagnosis);
        symptoms = itemView.findViewById(R.id.symptoms);
        medicinies = itemView.findViewById(R.id.medicines);
        nextConsultation = itemView.findViewById(R.id.next_date);
        visitDate = itemView.findViewById(R.id.visit_date);
    }

    public void populate(MedicalRecord parts) {
        doctorName.setText(parts.getDoctorName());
        clinicName.setText(parts.getClinicName());
        diagnosis.setText(parts.getDiagnosis());
        symptoms.setText(parts.getSymptoms());
        medicinies.setText(parts.getMedicinies());
        nextConsultation.setText(parts.getNextConsultation());
        visitDate.setText(parts.getVisitDate());
    }
}
