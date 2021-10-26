package com.example.hospitalbookingandroid.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.adapters.DoctorCardListAdapter;
import com.example.hospitalbookingandroid.dto.Hospital;
import com.example.hospitalbookingandroid.dto.HospitalSection;
import com.google.gson.Gson;

import java.util.List;

public class SectionDetailActivity extends AppCompatActivity {

    HospitalSection hospitalSection;
    public static final String EXTRA_MESSAGE = "sectionDetail";
    private RecyclerView mRecyclerView;
    private DoctorCardListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);


        mRecyclerView= findViewById(R.id.rv_doctors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        Gson gson = new Gson();
        hospitalSection = gson.fromJson(getIntent().getStringExtra(EXTRA_MESSAGE), HospitalSection.class);

        adapter= new DoctorCardListAdapter(hospitalSection.getDoctors(),SectionDetailActivity.this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.requestLayout();


    }
}