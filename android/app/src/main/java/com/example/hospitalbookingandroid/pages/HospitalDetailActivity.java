package com.example.hospitalbookingandroid.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hospitalbookingandroid.ServerDomain;
import com.example.hospitalbookingandroid.adapters.ImageAdapter;
import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.adapters.SectionCardListAdapter;
import com.example.hospitalbookingandroid.api.HospitalApi;
import com.example.hospitalbookingandroid.dto.Hospital;
import com.example.hospitalbookingandroid.dto.HospitalSection;
import com.example.hospitalbookingandroid.CompassFragment;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalDetailActivity extends AppCompatActivity {

    private static final String TAG = HospitalDetailActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "HospitalDetail";
    Button callNumber;
    Button messageNumber;
    Hospital hospital;
    Button showCompass;
    SectionCardListAdapter sectionCardListAdapter;
    List<HospitalSection> hospitalSectionList;
    private RecyclerView mRecyclerView;

    HospitalApi service;


    Location targetLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);



        mRecyclerView= findViewById(R.id.rv_sections);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        callNumber = findViewById(R.id.call);
        messageNumber = findViewById(R.id.txt);
        showCompass=findViewById(R.id.showCompass);
        Gson gson = new Gson();
        hospital = gson.fromJson(getIntent().getStringExtra(EXTRA_MESSAGE), Hospital.class);


        showCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompassFragment compassFragment = CompassFragment.newInstance(hospital);
                compassFragment.show(getSupportFragmentManager(),"Tagessss");
//                getFragmentManager().beginTransaction().replace(R.layout.activity_hospital_detail, compassFragment).commit();
            }
        });

        targetLocation=new Location(LocationManager.FUSED_PROVIDER);
        targetLocation.setLongitude(Double.valueOf(hospital.getLongitude()));
        targetLocation.setLatitude(Double.valueOf(hospital.getLatitude()));

        callNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String phoneNumber = hospital.getHospitalStaticConfig().getHospitalContactInfos().get(0).getPhoneNumber().replace("+", "00");
                callIntent.setData(Uri.parse("tel:" + phoneNumber));//change the number
                startActivity(callIntent);

            }
        });

        messageNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", "احتاج الى مشفى في الحال ...");
                sendIntent.putExtra("address", hospital.getHospitalStaticConfig().getHospitalContactInfos().get(0).getPhoneNumber());
                startActivity(sendIntent);

            }
        });


        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this, hospital);
        mViewPager.setAdapter(adapterView);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerDomain.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(HospitalApi.class);
        Call<List<HospitalSection>> responseCall= service.findAllHospitalSection(hospital.getId().toString());
        responseCall.enqueue(new Callback<List<HospitalSection>>() {
            @Override
            public void onResponse(Call<List<HospitalSection>> call, Response<List<HospitalSection>> response) {
                if (response.isSuccessful()) {
                    hospitalSectionList = response.body();
                    sectionCardListAdapter = new SectionCardListAdapter(hospitalSectionList, HospitalDetailActivity.this);
                    mRecyclerView.setAdapter(sectionCardListAdapter);
                    mRecyclerView.requestLayout();
                }
            }
            @Override
            public void onFailure(Call<List<HospitalSection>> call, Throwable t) {
                Toast.makeText(HospitalDetailActivity.this,"حدث حخطى انثاء الاتصال بلخادم",Toast.LENGTH_SHORT);
            }
        });


    }



}