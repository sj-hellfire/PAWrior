package com.example.pawrior.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pawrior.MainActivity;
import com.example.pawrior.Pawrior;
import com.example.pawrior.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpProfileActivity extends AppCompatActivity {
    EditText dogFirstName, dogSecondName, dogAge, dogBreed, dogWeight, ownerName, ownerPhone, ownerEmail;
    RadioGroup dogGender;
    CircleImageView dogProfilePic;
    Button  submitProfile;
    Boolean dogGenderBool;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_signup);
        dogFirstName = findViewById(R.id.dog_name_first);
        dogSecondName = findViewById(R.id.dog_name_second);
        dogAge = findViewById(R.id.dog_age);
        dogBreed = findViewById(R.id.dog_breed);
        dogWeight = findViewById(R.id.dog_weight);
        ownerName = findViewById(R.id.owner_name);
        ownerPhone = findViewById(R.id.owner_phone);
        ownerEmail = findViewById(R.id.owner_email);
        dogGender = findViewById(R.id.radio_group);
        dogProfilePic = findViewById(R.id.profile_photo);
        submitProfile = findViewById(R.id.submit_profile);

        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = findViewById(dogGender.getCheckedRadioButtonId());
                String string = rb.getText().toString();
                if(string.equals("Male")){
                    dogGenderBool = Boolean.TRUE;
                }
                else{
                    dogGenderBool = Boolean.FALSE;
                }
                dog = new Dog(
                        dogFirstName.getText().toString(),
                        dogSecondName.getText().toString(),
                        dogAge.getText().toString(),
                        dogBreed.getText().toString(),
                        dogWeight.getText().toString(),
                        ownerName.getText().toString(),
                        ownerEmail.getText().toString(),
                        ownerPhone.getText().toString(),
                        dogGenderBool
                );
                Pawrior.myDog = dog;
                databaseReference.push().setValue(dog);
                startActivity(new Intent(SignUpProfileActivity.this, MainActivity.class));
            }
        });
    }
}
