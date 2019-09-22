package com.example.pawrior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class ReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        SharedPreferences settings = getSharedPreferences("AUTHENTICATION", MODE_PRIVATE);
        String auth = settings.getString("auth", "incomplete");
    }
}
