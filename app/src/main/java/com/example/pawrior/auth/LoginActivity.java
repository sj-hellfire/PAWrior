package com.example.pawrior.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pawrior.Pawrior;
import com.example.pawrior.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.HttpUrl;

public class LoginActivity extends AppCompatActivity {
    Button loginNow;
    EditText phoneNoInput;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginNow = findViewById(R.id.login_button);
        phoneNoInput = findViewById(R.id.input_phone_auth);
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(LoginActivity.this);
                Pawrior.phoneNo = phoneNoInput.getText().toString();
                Random random = new Random();
                String id = String.format("%04d", random.nextInt(10000));
                Pawrior.otp = id;
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme("https")
                        .host("api.msg91.com")
                        .addPathSegment("api")
                        .addPathSegment("sendhttp.php")
                        .addQueryParameter("mobiles", phoneNoInput.getText().toString())
                        .addQueryParameter("authkey", "295319AKbYq8CPk75d865a4b")
                        .addQueryParameter("route", "4")
                        .addQueryParameter("sender", "PAWROR")
                        .addQueryParameter("country", "91")
                        .build();
                String url = httpUrl.toString() + "&message=Your one time password for Pawrior is:"+ id;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(LoginActivity.this, OTPActivity.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(stringRequest);
            }
        });
     }
}
