package com.example.pawrior;

import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class MapFragment extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    private DatabaseReference mRef;
    private FirebaseAuth auth;
    String key = "";
    private HashMap<String, Object> userData;

    private LatLng latLng;

    private static final String SERVER_IP = "192.168.43.72";
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}