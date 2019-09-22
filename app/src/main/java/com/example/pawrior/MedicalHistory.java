package com.example.pawrior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicalAdapter medicalAdapter;
    List<MedicalRecord> medicalRecords = new ArrayList<>();
    String[] doctorName = {" Dr. Susan Banks", " Dr.Maria Hill"};
    String[] clinicName = {" ABC Friendly Clinic", " American Kennel Clinic"};
    String[] diagnosis = {"Allergic reactions to grooming products, food, and environmental irritants, such as pollen or insect bites.", "No serious ailment found.Mild case of skin rashes under the fur"};
    String[] symptoms = {"Ugly rash under the fur coat", "Dull coat and shedding with scaly skin underneath."};
    String[] medicinies = {"No Medicine Required", "Medicated baths and topical oils"};
    String[] nextConsultation = {"None", "None"};
    String[] visitDate = {" 10:00 PM 22-09-2019", " 3:00 PM 20-03-2019"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        recyclerView = findViewById(R.id.recycler_view);
        for(int i=0;i<2;i++){
            medicalRecords.add(new MedicalRecord(
                    doctorName[i],
                    clinicName[i],
                    diagnosis[i],
                    symptoms[i],
                    medicinies[i],
                    nextConsultation[i],
                    visitDate[i]
            ));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(MedicalHistory.this));
        medicalAdapter = new MedicalAdapter(medicalRecords);
        recyclerView.setAdapter(medicalAdapter);
    }
}
