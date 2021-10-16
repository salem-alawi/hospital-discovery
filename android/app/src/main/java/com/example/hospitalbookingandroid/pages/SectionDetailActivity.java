package com.example.hospitalbookingandroid.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.dto.Hospital;
import com.example.hospitalbookingandroid.dto.HospitalSection;
import com.google.gson.Gson;

import java.util.List;

public class SectionDetailActivity extends AppCompatActivity {

    HospitalSection hospitalSection;
    public static final String EXTRA_MESSAGE = "sectionDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);

        Gson gson = new Gson();
        hospitalSection = gson.fromJson(getIntent().getStringExtra(EXTRA_MESSAGE), HospitalSection.class);


        Toast.makeText(SectionDetailActivity.this,hospitalSection.getSection().getName(),Toast.LENGTH_SHORT).show();

    }
}