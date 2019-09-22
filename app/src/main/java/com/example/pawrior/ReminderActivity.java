package com.example.pawrior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReminderActivity extends AppCompatActivity {

    ImageView taskImage;
    TextView taskTime, taskDescription;
    FloatingActionButton fab;
    Dialog activityPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        SharedPreferences settings = getSharedPreferences("EVENT", MODE_PRIVATE);
        String event = settings.getString("eventtype", "none");
        String time = settings.getString("eventtime", "none");
        String desc = settings.getString("eventdesc", "none");
        fab = findViewById(R.id.plus_btn);
        taskImage = findViewById(R.id.activity_image);
        taskTime = findViewById(R.id.activity_time);
        taskDescription = findViewById(R.id.activity_description);
        if(!(event.equals("none"))){
            taskTime.setText(time);
            taskDescription.setText(desc);
            if(event.equals("walking")){
                taskImage.setImageDrawable(ContextCompat.getDrawable(ReminderActivity.this, R.drawable.ic_walking_with_dog));
            }
            else{
                if(event.equals("meal")){
                    taskImage.setImageDrawable(ContextCompat.getDrawable(ReminderActivity.this, R.drawable.ic_order));
                }
                else{
                    taskImage.setImageDrawable(ContextCompat.getDrawable(ReminderActivity.this, R.drawable.ic_toilet));
                }
            }
        }
        activityPopup = new Dialog(getBaseContext());

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

    }

    private void setAlarm(int year, int month, int day, int hour, int minute){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        cal.set(year, month, day, hour, minute);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 , intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private void writeGetData(String datetime, String activity, String description){
        SharedPreferences.Editor editor = getSharedPreferences("EVENT", MODE_PRIVATE).edit();
        editor.putString("eventtype", activity);
        editor.putString("eventtime", datetime);
        editor.putString("eventdesc", description);
        editor.apply();
    }
}
