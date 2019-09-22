package com.example.pawrior;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.*;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    private DatabaseReference mRef;
    private FirebaseAuth auth;
    String key = "";
    private HashMap<String, Object> userData;

    private LatLng latLng;

    private static final String SERVER_IP = "192.168.43.72";
    private int cnt = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_safe_map, container, false);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(SafeMapFragment.this);
        mRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        mRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (latLng != null && postSnapshot.child("email").getValue().equals(auth.getCurrentUser().getEmail())) {
                        key = postSnapshot.getKey();
                        userData = new HashMap<>();
                        for (DataSnapshot ds : postSnapshot.getChildren())
                            userData.put(ds.getKey(), ds.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        new getMessages().execute("http://" + SERVER_IP + "/SIH/test1.txt");
                    }
                });
            }
        };

        timer.schedule(task, 0, 1000);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);
//                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

//                latLng = new LatLng(location.getLatitude(), location.getLongitude() + 0.02);
//                markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title("Safe Spot");
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
//
//                latLng = new LatLng(location.getLatitude() + 0.01, location.getLongitude() + 0.01);
//                markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title("Person A");
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
//
//                latLng = new LatLng(location.getLatitude() - 0.01, location.getLongitude() - 0.01);
//                markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title("Person B");
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

            }
        }
    };

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    class getMessages extends AsyncTask<String, Void, String> {
        String s = "";
        String dt[];
        LatLng latlng[] = new LatLng[4];
        int r;

        protected String doInBackground(String... urls) {
            try {
                URL url;
                URLConnection urlConn;
                BufferedReader dis;
                url = new URL(urls[0]);

                urlConn = url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setUseCaches(false);

                dis = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String rd = dis.readLine();
                dt = rd.split("~");
                if (Integer.valueOf(dt[0]) > cnt) cnt = Integer.valueOf(dt[0]);
                else cancel(true);
                for (int i = 0; i < 4; i++) {
                    latlng[i] = new LatLng(Double.valueOf(dt[2 * i + 1]), Double.valueOf(dt[2 * i + 2]));
                }
                r = Integer.valueOf(dt[9]);

                if (!key.equals("") && !userData.isEmpty()) {
                    userData.put("last_lat", latLng.latitude);
                    userData.put("last_lng", latLng.longitude);
                    mRef.child("Users").child(key).updateChildren(userData);
                }
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            if (latLng == null) return;
            for (int i = 0; i < 4; i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latlng[i]);
                markerOptions.title("Safe Spot " + (i + 1));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                Double dist = 0d;
                dist += (latlng[i].latitude - latLng.latitude) * (latlng[i].latitude - latLng.latitude);
                dist += (latlng[i].longitude - latLng.longitude) * (latlng[i].longitude - latLng.longitude);
                dist = Math.sqrt(dist);
                dist *= 111;
//                Log.e("xx", latLng.latitude + " " + latLng.longitude);
//                Log.e("xx", dist + "");
                if (dist < r || r == 0)
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            }
        }

    }

}