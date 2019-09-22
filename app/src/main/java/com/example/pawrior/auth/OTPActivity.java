package com.example.pawrior.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pawrior.Pawrior;
import com.example.pawrior.R;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class OTPActivity extends AppCompatActivity {
    OtpView otpView;
    String enteredOTP;
    Button verifyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otpView = findViewById(R.id.otp_view);
        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                enteredOTP = otp;
            }
        });
        verifyButton = findViewById(R.id.otp_verify_button);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP();
            }
        });

    }

    private void verifyOTP() {
        if(enteredOTP.equals(Pawrior.otp)){
            SharedPreferences.Editor editor = getSharedPreferences("AUTHENTICATION", MODE_PRIVATE).edit();
            editor.putString("auth", "completed");
            editor.apply();
            startActivity(new Intent(OTPActivity.this, SignUpProfileActivity.class));
        }
        else{
            Toast.makeText(OTPActivity.this, "OTP does not match", Toast.LENGTH_LONG).show();
        }
    }
}
