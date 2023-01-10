package com.example.coffeecom.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.helper.AddressConverter;
import com.example.coffeecom.helper.GoogleResponse;
import com.example.coffeecom.helper.Result;
import com.example.coffeecom.model.BaristaModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;

public class MapsFragment extends Fragment {
    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat, lng;
    ArrayList<LatLng>coffeeShopLocation = new ArrayList<LatLng>();
//    LatLng jayaOne = new LatLng(3.118628469625429, 101.63541564002739);
//    LatLng rumahAntarabangsa = new LatLng(3.1197533284499657, 101.6370464230398);
//    LatLng kk13 = new LatLng(3.120544491171887, 101.63991231637237);
//    LatLng kannaCurryHouse = new LatLng(3.1198374377737865, 101.62990231276797);
//    LatLng omuLab = new LatLng(3.119001828504003, 101.629698464888);
    ArrayList<String> title = new ArrayList<String>();


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
                return;
            }

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(60000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(5000);

            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
//                    Toast.makeText(getActivity().getApplicationContext(), "Location result is=" + locationResult, Toast.LENGTH_LONG).show();

                    if (locationResult == null){
//                        Toast.makeText(getActivity().getApplicationContext(), "Current location is null", Toast.LENGTH_LONG).show();
                        return;

                    }
                    for(Location location:locationResult.getLocations()){
                        if (location != null){
//                            Toast.makeText(getActivity().getApplicationContext(), "Location result is=" + location.getLongitude(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            };
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
            Log.i("Maps Fragment","Debugging Maps Fragment 9" + coffeeShopLocation.get(0));
            for (int i =0; i<coffeeShopLocation.size();i++){
                Log.i("Maps Fragment","Debugging Maps Fragment 8" + coffeeShopLocation.get(i));
                for (int j=0; j<title.size();j++){
                    googleMap.addMarker(new MarkerOptions().position(coffeeShopLocation.get(i)).title(String.valueOf(title.get(j))));
                    Log.i("Maps Fragment","Debugging Maps Fragment 7" + coffeeShopLocation.get(i) + title.get(j));
                }
            }


            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                @Override
                public boolean onMarkerClick(Marker marker){
                    String markertitle = marker.getTitle();
                    Provider.setCurrentBaristaId(marker.getTitle());
                    AppCompatActivity activity = (AppCompatActivity) MapsFragment.this.getContext();
                    BaristaListFragment baristaListFragment = new BaristaListFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,baristaListFragment).addToBackStack("MapsFragment").commit();
//                    Intent intent = new Intent(getActivity().getApplicationContext(), BaristaListFragment.class);
//                    intent.putExtra("title", markertitle);
//                    startActivity(intent);

                    return false;
                }
            });

            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        lat = location.getLatitude();
                        lng = location.getLongitude();

                        LatLng latLng = new LatLng(5.426713899999999,100.3056012);

//                        googleMap.addMarker(new MarkerOptions().position(latLng).title("Your Current Position"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                    }
                }
            });

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
//                    MarkerOptions markerOptions = new MarkerOptions();
//
//                    markerOptions.position(latLng);
//
//                    markerOptions.title(latLng.latitude+" : " + latLng.longitude);

                    googleMap.clear();

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

//                    googleMap.addMarker(markerOptions);
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity().getApplicationContext());
        try {
            generateLatLng();
        } catch (InterruptedException e) {
            e.printStackTrace() ;
        }
//        coffeeShopLocation.add(jayaOne);
//        coffeeShopLocation.add(rumahAntarabangsa);
//        coffeeShopLocation.add(kk13);
//        coffeeShopLocation.add(kannaCurryHouse);
//        coffeeShopLocation.add(omuLab);

//        title.add("b0001");
//        title.add("b0001");
//        title.add("b0001");
//        title.add("b0001");
//        title.add("b0001");
//        title.add("jayaOne");
//        title.add("rumahAntarabangsa");
//        title.add("kk13");
//        title.add("kannaCurryHouse");
//        title.add("omuLab");
    }

    private void generateLatLng() throws InterruptedException {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        Log.i("Maps Fragment","Debugging Maps Fragment 1");
            for (BaristaModel ba: Provider.getBaristas()) {
                String address = ba.getUserStreetNo() + " " + ba.getUserTaman() + " " + ba.getUserPostalCode() + " " + ba.getUserState();
                Log.i("Maps Fragment","Debugging Maps Fragment 2");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Maps Fragment","Debugging Maps Fragment 5");
                        try {
                            Log.i("Maps Fragment","Debugging Maps Fragment 4");
                            GoogleResponse res = new AddressConverter().convertToLatLong(address);

                                for (Result result : res.getResults()) {
                                    Log.i("Maps Fragment","Lattitude of address is :" + result.getGeometry().getLocation().getLat());
                                    Log.i("Maps Fragment","Longitude of address is :" + result.getGeometry().getLocation().getLng());
                                    coffeeShopLocation.add(new LatLng (Double.parseDouble(result.getGeometry().getLocation().getLat()), Double.parseDouble(result.getGeometry().getLocation().getLng())));
                                    title.add(ba.getBaristaId());
                                    Log.i("Maps Fragment","Location is " + result.getGeometry().getLocation_type() + " "+ coffeeShopLocation.size() + " " + title.size());
                                }


                        }catch (Exception e){
                            Log.e("Maps Fragment", e.toString());
                        }
                    }
                });
                thread.start();
                thread.join();


        }
    }

//    public GoogleResponse convertToLatLong(String fullAddress) throws IOException {
//
//        /*
//         * Create an java.net.URL object by passing the request URL in
//         * constructor. Here you can see I am converting the fullAddress String
//         * in UTF-8 format. You will get Exception if you don't convert your
//         * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
//         * parameter we also need to pass "sensor" parameter. sensor (required
//         * parameter) — Indicates whether or not the geocoding request comes
//         * from a device with a location sensor. This value must be either true
//         * or false.
//         */
//        URL url = new URL(URL + "?address="
//                + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
//        // Open the Connection
//        URLConnection conn = url.openConnection();
//
//        InputStream in = conn.getInputStream() ;
//        ObjectMapper mapper = new ObjectMapper();
//        GoogleResponse response = (GoogleResponse)mapper.readValue(in,GoogleResponse.class);
//        in.close();
//        return response;
//
//
//    }
//    private void getCurrentLocation(){
//        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions((Activity) getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
//            return;
//        }
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(60000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setFastestInterval(5000);
//        LocationCallback locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(@NonNull LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//                Toast.makeText(getActivity().getApplicationContext(), "Location result is=" + locationResult, Toast.LENGTH_LONG).show();
//
//                if (locationResult == null){
//                    Toast.makeText(getActivity().getApplicationContext(), "Current location is null", Toast.LENGTH_LONG).show();
//                    return;
//
//                }
//                for(Location location:locationResult.getLocations()){
//                    if (location != null){
//                        Toast.makeText(getActivity().getApplicationContext(), "Location result is=" + location.getLongitude(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        };
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);
//
//        Task<Location> task = fusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null){
//                    lat = location.getLatitude();
//                    lng = location.getLongitude();
//
//                    LatLng latLng = new LatLng(lat,lng);
//
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (Request_code){
//            case Request_code:
//                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    getCurrentLocation();
//            }
//
//        }
//    }
}