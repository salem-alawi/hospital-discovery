package com.example.hospitalbookingandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospitalbookingandroid.dto.Hospital;
import com.example.hospitalbookingandroid.gps.Compass;
import com.example.hospitalbookingandroid.gps.GPSPoint;
import com.example.hospitalbookingandroid.gps.SOTWFormatter;
import com.example.hospitalbookingandroid.gps.Wherebouts;
import com.example.hospitalbookingandroid.gps.Workable;
import com.google.gson.Gson;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompassFragment extends DialogFragment {
    public static final String EXTRA_MESSAGE = "HospitalDetail";
    Hospital hospital;
    Location location;


    private Compass compass;
    private ImageView arrowView;
    private TextView textView;
    private float currentAzimuth;
    private ImageView imgMap;
    private SOTWFormatter sotwFormatter;

    Location targetLocation;
   View view;

    public CompassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CompassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompassFragment newInstance(Hospital hospital) {
        CompassFragment fragment = new CompassFragment();
        Bundle args = new Bundle();


        Gson gson = new Gson();
        String myJson = gson.toJson(hospital);
        args.putString(EXTRA_MESSAGE, myJson);
        
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            Gson gson = new Gson();
            hospital = gson.fromJson(getArguments().getString(EXTRA_MESSAGE), Hospital.class);


            targetLocation=new Location(LocationManager.FUSED_PROVIDER);
            targetLocation.setLongitude(Double.valueOf(hospital.getLongitude()));
            targetLocation.setLatitude(Double.valueOf(hospital.getLatitude()));

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view =  inflater.inflate(R.layout.fragment_compass, container, false);

        textView=view.findViewById(R.id.txtTest);





//        ViewPager mViewPager = (ViewPager) getView().findViewById(R.id.viewPage);
//        ImageAdapter adapterView = new ImageAdapter(getView().getContext(), hospital);
//        mViewPager.setAdapter(adapterView);

        this.location=new Location(LocationManager.FUSED_PROVIDER);
        Wherebouts.instance(view.getContext()).onChange(new Workable<GPSPoint>() {
            @Override
            public void work(GPSPoint gpsPoint) {

                if (location.getLatitude() != gpsPoint.getLocation().getLatitude() || location.getLongitude() != gpsPoint.getLocation().getLongitude()) {

                    location.setLongitude(gpsPoint.getLocation().getLongitude());
                    location.setLatitude(gpsPoint.getLocation().getLatitude());



                    double disanceInMeter= location.distanceTo(targetLocation);

                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);

                    if(disanceInMeter> 1000){
                        disanceInMeter=disanceInMeter/1000;
                        String distance=df.format(disanceInMeter) + "  كم ";
                        textView.setText(distance);

                    }else {
                        String distance=df.format(disanceInMeter) + " متر ";
                        textView.setText(distance);
                    }
                }


            }
        });

//
//        sotwLabel = getView().findViewById(R.id.sotw_label);


        sotwFormatter = new SOTWFormatter(view.getContext());
        arrowView = view.findViewById(R.id.main_image_hands);
        imgMap=view.findViewById(R.id.img_Map);
        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:"+hospital.getLatitude()+","+hospital.getLongitude()+"?q="+hospital.getLatitude()+","+hospital.getLongitude()+" (name)"));
                intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
                startActivity(intent);

            }
        });

        arrowView.setX(3);
//        sotwLabel = findViewById(R.id.sotw_label);
        setupCompass();



        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        compass.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    public void onStop() {
        super.onStop();
//        Log.d(TAG, "stop compass");
        compass.stop();
    }


    private void setupCompass() {


        compass = new Compass(getContext(),location, targetLocation);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth) {

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }


    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth( float azimuth) {
                        float result=azimuth;
                        result -= bearing(location.getLatitude(), location.getLongitude(), targetLocation.getLatitude(), targetLocation.getLongitude());
                        adjustArrow(result);
            }
        };
    }


    protected float bearing(double startLat, double startLng, double endLat, double endLng){
        double longitude1 = startLng;
        double longitude2 = endLng;
        double latitude1 = Math.toRadians(startLat);
        double latitude2 = Math.toRadians(endLat);
        double longDiff= Math.toRadians(longitude2-longitude1);
        double y= Math.sin(longDiff)*Math.cos(latitude2);
        double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

        return (float) ((Math.toDegrees(Math.atan2(y, x))+360)%360);
    }
}