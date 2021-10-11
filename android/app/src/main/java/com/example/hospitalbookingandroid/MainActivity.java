package com.example.hospitalbookingandroid;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingandroid.dto.Hospital;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hospitalbookingandroid.api.HospitalApi;
import com.example.hospitalbookingandroid.dto.HospitalResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    Location currentLocation = new Location(LocationManager.FUSED_PROVIDER);
    public List<Hospital> hospitalList;
    HospitalCardListAdapter adapter;
    HospitalApi service;
    Wherebouts wherebouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.hospitalRecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Picasso.get().setLoggingEnabled(true);
        service = retrofit.create(HospitalApi.class);
        Call<HospitalResponse> responseCall= service.findAllHospitals();
        responseCall.enqueue(new Callback<HospitalResponse>() {
            @Override
            public void onResponse(Call<HospitalResponse> call, Response<HospitalResponse> response) {
                if (response.isSuccessful()) {
                    hospitalList = response.body().getContent();
                    adapter = new HospitalCardListAdapter(hospitalList, currentLocation, MainActivity.this);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.requestLayout();
                }
            }
            @Override
            public void onFailure(Call<HospitalResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"حدث حخطى انثاء الاتصال بلخادم",Toast.LENGTH_SHORT);
            }
        });


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                1);

        wherebouts= Wherebouts.instance(MainActivity.this);
        wherebouts.onChange(new Workable<GPSPoint>() {
            @Override
            public void work(GPSPoint gpsPoint) {

                if (currentLocation.getLatitude() != gpsPoint.getLocation().getLatitude() || currentLocation.getLongitude() != gpsPoint.getLocation().getLongitude()) {

                    currentLocation.setLongitude(gpsPoint.getLocation().getLongitude());
                    currentLocation.setLatitude(gpsPoint.getLocation().getLatitude());

                    if (adapter != null)
                        adapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "لم يتم الحصول على الادن في استخدام بيانات موقع الهاتف الرجاء الموافقه على استخدام التطبيق لصلاحيات الموقع للعمل بصوره طبيعيه", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wherebouts.stop();
    }
}