package com.example.alino4ka.test5.test4;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alino4ka.test5.R;


public class Test4Activity extends AppCompatActivity {
    TextView getTemp;
    TextView getHumidity;
    TextView getPressure;
    TextView getVector;
    TextView getPower;
    TextView getClouds;
    private RequestSender requestSender;
    Button getData;
    public static final String TAG = "Test4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_activity);
        init();
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WeatherGetter().execute();
            }
        });
    }

    private void init(){
        getTemp = (TextView) findViewById(R.id.getTemp);
        getHumidity = (TextView) findViewById(R.id.getHumidity);
        getPressure = (TextView) findViewById(R.id.getPressure);
        getVector = (TextView) findViewById(R.id.getVector);
        getPower = (TextView) findViewById(R.id.getWindPower);
        getClouds = (TextView) findViewById(R.id.getClouds);
        getData = (Button) findViewById(R.id.get_data);
    }

    public Weather getLocation(){
        final Weather[] weather = new Weather[1];
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(final Location location) {
                String lat = String.valueOf((int)location.getLatitude());
                String lon = String.valueOf((int)location.getLongitude());
                weather[0] = new RequestSender().sendRequest(lat, lon);
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
        while(true) {
            if (weather[0] != null) {
                Log.i(TAG, "getLocation: " + weather[0].getTemp());
                return weather[0];
            }
        }
    }

    private class WeatherGetter extends AsyncTask<Void, Void, Weather>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Weather doInBackground(Void... params) {
            Looper.prepare();
            return getLocation();
        }

        @Override
        protected void onPostExecute(Weather aVoid) {
            Weather weather = aVoid;
            if(weather!=null) {
                Log.i(TAG, "onPostExecute: " + aVoid.getTemp());
                getTemp.setText(weather.getTemp());
                getPressure.setText(weather.getPressure());
                getHumidity.setText(weather.getHumidity());
                getVector.setText(weather.getDirection());
                getPower.setText(weather.getSpeed());
                getClouds.setText(weather.getClouds());
            }
            super.onPostExecute(aVoid);
        }
    }
}
