package com.example.pawrior;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {

    ImageView taskImage;
    TextView taskTime, taskDescription;
    FloatingActionButton fab;
    Dialog activityPopup;

    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        Log.e("entered Notifier", "xyz");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("PAWrior - Time to Walk")
                .setContentText("Time to walk your pet!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.app_logo).setChannelId("my_channel_01")
                .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.app_logo)).getBitmap())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, ReminderActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, NotificationAlert.class);
        notificationIntent.putExtra(NotificationAlert.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(NotificationAlert.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

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
        scheduleNotification(this, 2000, 8080);
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
