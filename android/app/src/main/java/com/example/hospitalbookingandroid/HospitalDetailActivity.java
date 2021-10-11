package com.example.hospitalbookingandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospitalbookingandroid.dto.Hospital;
import com.google.gson.Gson;

public class HospitalDetailActivity extends AppCompatActivity {

    private static final String TAG = HospitalDetailActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "HospitalDetail";
    Button callNumber;
    Button messageNumber;
    Hospital hospital;
    Button showCompass;


    private Compass compass;
    private ImageView arrowView;
    private TextView sotwLabel;  // SOTW is for "side of the world"

    private float currentAzimuth;
    private SOTWFormatter sotwFormatter;

    Location targetLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);




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

    }



}