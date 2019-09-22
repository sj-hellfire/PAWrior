package com.example.pawrior;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

                final AlertDialog.Builder alert = new AlertDialog.Builder(MedicalHistory.this);
                alert.setTitle("Select Event");
                final LinearLayout LL = new LinearLayout(MedicalHistory.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                LL.setLayoutParams(params);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                final TextView tName = new TextView(MedicalHistory.this);
                final EditText sName = new EditText(MedicalHistory.this);
                tName.setText("Diagnosis:  ");
                tName.setTextColor(Color.BLACK);
                tName.setTextSize(18.0f);
                LL.setPadding(64, 32, 0, 0);
                LL.addView(tName, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                LL.addView(sName, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                alert.setView(LL);
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(MedicalHistory.this,
//                        android.R.layout.simple_spinner_item, medicalRecords);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                sName.setAdapter(dataAdapter);
                sName.setSelection(0);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        medicalRecords.add(new MedicalRecord(
                                doctorName[0],
                                clinicName[0],
                                sName.getEditableText().toString(),
                                symptoms[0],
                                medicinies[0],
                                nextConsultation[0],
                                visitDate[0]
                        ));
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(MedicalHistory.this));
        medicalAdapter = new MedicalAdapter(medicalRecords);
        recyclerView.setAdapter(medicalAdapter);
    }
}
