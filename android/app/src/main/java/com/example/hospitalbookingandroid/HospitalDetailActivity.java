package com.example.hospitalbookingandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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




    private Compass compass;
    private ImageView arrowView;
    private TextView sotwLabel;  // SOTW is for "side of the world"

    private float currentAzimuth;
    private SOTWFormatter sotwFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        callNumber=findViewById(R.id.call);
        messageNumber= findViewById(R.id.txt);
        Gson gson = new Gson();
        hospital= gson.fromJson(getIntent().getStringExtra(EXTRA_MESSAGE), Hospital.class);


        callNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String phoneNumber=hospital.getHospitalStaticConfig().getHospitalContactInfos().get(0).getPhoneNumber().replace("+","00");
                callIntent.setData(Uri.parse("tel:"+phoneNumber));//change the number
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
        ImageAdapter adapterView = new ImageAdapter(this,hospital);
        mViewPager.setAdapter(adapterView);

        sotwLabel= findViewById(R.id.sotw_label);


        sotwFormatter = new SOTWFormatter(HospitalDetailActivity.this);

        arrowView = findViewById(R.id.main_image_hands);
//        sotwLabel = findViewById(R.id.sotw_label);
        setupCompass();


    }



    @Override
    protected void onStart() {
        super.onStart();
//        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth) {
//        Log.d(TAG, "will set rotation from " + currentAzimuth + " to "
//                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }

    private void adjustSotwLabel(float azimuth) {
        sotwLabel.setText(sotwFormatter.format(azimuth));
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(final float azimuth) {
                // UI updates only in UI thread
                // https://stackoverflow.com/q/11140285/444966
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adjustArrow(azimuth);
                        adjustSotwLabel(azimuth);
                    }
                });
            }
        };
    }



}