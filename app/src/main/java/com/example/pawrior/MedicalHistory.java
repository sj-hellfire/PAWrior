package com.example.pawrior;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicalAdapter medicalAdapter;
    FloatingActionButton fab;
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
        fab = findViewById(R.id.plus_btn2);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm(2019, 9, 22, 11,20);
                taskImage.setImageDrawable(ContextCompat.getDrawable(ReminderActivity.this, R.drawable.ic_walking_with_dog));
                taskDescription.setText("Go for a 30 min jog with the dog");
                taskTime.setText("11:20, 22 Sept 2019");
                writeGetData("11:20, 22 Sept 2019", "walking", "Go for a 30 min jog with the dog");
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(MedicalHistory.this));
        medicalAdapter = new MedicalAdapter(medicalRecords);
        recyclerView.setAdapter(medicalAdapter);
    }
}
