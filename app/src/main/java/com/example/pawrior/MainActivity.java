package com.example.pawrior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pawrior.auth.LoginActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    CardView nutritionist, food_order, reminders, findmypet, vaccination_schedule, medical_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        SharedPreferences settings = getSharedPreferences("AUTHENTICATION", MODE_PRIVATE);
        String auth = settings.getString("auth", "incomplete");
        if(auth.equals("incomplete")){
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        nutritionist = findViewById(R.id.nutritionist);
//        nutritionist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,));
//            }
//        });
        food_order = findViewById(R.id.food_order);
//        food_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,));
//            }
//        });

        reminders = findViewById(R.id.reminders);
        reminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReminderActivity.class));
            }
        });
        findmypet = findViewById(R.id.find_my_pet);
//        findmypet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,));
//            }
//        });
        vaccination_schedule = findViewById(R.id.vaccination_schedule);
        vaccination_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VaccinationSchedule.class));
            }
        });
        medical_history = findViewById(R.id.visit_history);
        medical_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MedicalHistory.class));
            }
        });

    }
}
