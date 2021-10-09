package com.example.hospitalbookingandroid;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospitalbookingandroid.dto.Hospital;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompassFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "HospitalDetail";

    Hospital hospital;
    Location location;


    private Compass compass;
    private ImageView arrowView;
    private TextView sotwLabel;  // SOTW is for "side of the world"

    private float currentAzimuth;
    private SOTWFormatter sotwFormatter;

    Location targetLocation;


    public CompassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompassFragment newInstance(String param1, String param2) {
        CompassFragment fragment = new CompassFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Gson gson = new Gson();
            hospital = gson.fromJson(getArguments().getString(EXTRA_MESSAGE), Hospital.class);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ViewPager mViewPager = (ViewPager) getView().findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(getView().getContext(), hospital);
        mViewPager.setAdapter(adapterView);

        this.location=new Location(LocationManager.FUSED_PROVIDER);
        Wherebouts.instance(getView().getContext()).onChange(new Workable<GPSPoint>() {
            @Override
            public void work(GPSPoint gpsPoint) {

                if (location.getLatitude() != gpsPoint.getLocation().getLatitude() || location.getLongitude() != gpsPoint.getLocation().getLongitude()) {

                    location.setLongitude(gpsPoint.getLocation().getLongitude());
                    location.setLatitude(gpsPoint.getLocation().getLatitude());
                }


            }
        });


        sotwLabel = getView().findViewById(R.id.sotw_label);


        sotwFormatter = new SOTWFormatter(getView().getContext());

        arrowView = getView().findViewById(R.id.main_image_hands);
//        sotwLabel = findViewById(R.id.sotw_label);
        setupCompass();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compass, container, false);
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

    private void adjustSotwLabel(float azimuth) {
        sotwLabel.setText(sotwFormatter.format(azimuth));
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth( float azimuth) {
                // UI updates only in UI thread
                // https://stackoverflow.com/q/11140285/444966
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        float result=azimuth;
                        result -= bearing(location.getLatitude(), location.getLongitude(), targetLocation.getLatitude(), targetLocation.getLongitude());

                        adjustArrow(result);
                        adjustSotwLabel(result);
                    }
                });
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