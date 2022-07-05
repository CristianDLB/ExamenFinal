package com.example.examenfinal;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.examenfinal.databinding.ActivityMapsBinding;
import com.google.gson.Gson;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public float Lat,Long;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*
        Intent intent = getIntent();
        String prueba = intent.getStringExtra("Prueba1");

        Log.i("AppCatalogo",String.valueOf(prueba));

        String Latitud1 = "";
        String Longitud1 ="";
        Latitud1=prueba.split(",")[0];
        Longitud1=prueba.split(",")[1];

        float guaroo1 = Float.parseFloat(Latitud1);
        float guardo2 = Float.parseFloat(Longitud1);
        Lat=guaroo1;
        Long=guardo2;
        Log.i("AppCatalogo",String.valueOf(Latitud1));
        Log.i("AppCatalogo",String.valueOf(Longitud1));*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       // Log.i("AppCatalogo",String.valueOf(Lat));
       // Log.i("AppCatalogo",String.valueOf(Long));

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(Lat, Long);
        LatLng sydney = new LatLng(-6.6769018, -78.5246082);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}