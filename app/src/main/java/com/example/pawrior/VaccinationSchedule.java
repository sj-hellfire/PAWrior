package com.example.pawrior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class VaccinationSchedule extends AppCompatActivity {
    RecyclerView recyclerView;
    VaccineAdapter vaccineAdapter;
    List<Vaccines> vaccineRecords = new ArrayList<>();
    String[] doctorName = {" Dr. Susan Banks", " Dr.Maria Hill"};
    String[] vaccineName = {" ABC Friendly Clinic", " American Kennel Clinic"};
    String[] immunizes = {
            "DHPP, Leptospirosis, Canine Influenza, Lyme Disease, Rabies",
            "DHPP, Kennel Cough"
    };
    String[] dogAge = {"27wk", "10wk"};
    String[] vaccinationDate = {" 11:00 AM 09-03-2018", " 7:00 PM 02-11-2017"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_schedule);
        recyclerView = findViewById(R.id.recycler_view);
        for(int i=0;i<2;i++){
            vaccineRecords.add(new Vaccines(
                    vaccineName[i],
                    vaccinationDate[i],
                    doctorName[i],
                    immunizes[i],
                    dogAge[i]
            ));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(VaccinationSchedule.this));
        vaccineAdapter = new VaccineAdapter(vaccineRecords);
        recyclerView.setAdapter(vaccineAdapter);
    }
}
