package com.example.alino4ka.test5.test2;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapFragment extends SupportMapFragment implements OnMapClickListener{
    private GoogleMap map;
    private static final String TAG = "Map";
    private MarkerOptions markerOptions;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                listenMap();
            }
        });


    }

    private void listenMap(){
        map.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        map.clear();
        map.addMarker(markerOptions);

        AddressGetter addressGetter = new AddressGetter(latLng.latitude, latLng.longitude);
                addressGetter.execute();
    }


    private class AddressGetter extends AsyncTask<Void, Void, Void>{
        private Geocoder geocoder;
        private List<Address>addresses;
        private double lat;
        private double lon;
        private String address;

        AddressGetter(double lat, double lon){
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        protected Void doInBackground(Void... params) {
            addresses = new ArrayList<>();
            geocoder = new Geocoder(getActivity(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat, lon, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(addresses.size()!=0) {
                address = addresses.get(0).getAddressLine(0);
                Log.i(TAG, "getAddress: " + addresses.get(0).getAddressLine(0));
                map.clear();
                String forLat = String.format("%.2f", lat);
                String forLon = String.format("%.2f", lon);
                markerOptions.title(address + " Ш:"+forLat +" Д:" +forLon );
                map.addMarker(markerOptions);
            }
        }
    }
}

