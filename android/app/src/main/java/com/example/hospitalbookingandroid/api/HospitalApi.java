package com.example.hospitalbookingandroid.api;

import com.example.hospitalbookingandroid.dto.HospitalResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HospitalApi {


    @GET("/public/hospitals")
    public Call<HospitalResponse> findAllHospitals();
}
