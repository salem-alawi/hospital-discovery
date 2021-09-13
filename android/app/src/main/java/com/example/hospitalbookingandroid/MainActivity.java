package com.example.hospitalbookingandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.hospitalbookingandroid.api.HospitalApi;
import com.example.hospitalbookingandroid.dto.HospitalResponse;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView= findViewById(R.id.hospitalRecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        HospitalApi service = retrofit.create(HospitalApi.class);
        Call<HospitalResponse> callSync = service.findAllHospitals();


        Location loc1 = new Location(LocationManager.GPS_PROVIDER);
        Picasso.get().setLoggingEnabled(true);


        // dump location
        loc1.setLatitude(12.7762108);
        loc1.setLongitude(80.0345091);
        callSync.enqueue(new Callback<HospitalResponse>() {
            @Override
            public void onResponse(Call<HospitalResponse> call, Response<HospitalResponse> response) {

                if(response.isSuccessful()){

                    HospitalCardListAdapter adapter= new HospitalCardListAdapter(response.body().getContent(),loc1,MainActivity.this);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.requestLayout();

                }

            }

            @Override
            public void onFailure(Call<HospitalResponse> call, Throwable t) {

                System.out.printf("not success");
            }
        });


    }
}