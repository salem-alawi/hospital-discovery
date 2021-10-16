package com.example.hospitalbookingandroid.api;

import com.example.hospitalbookingandroid.dto.HospitalResponse;
import com.example.hospitalbookingandroid.dto.HospitalSection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HospitalApi {


    @GET("/public/hospitals")
    public Call<HospitalResponse> findAllHospitals();

    @GET("/public/hospitals/{id}/sections")
    Call<List<HospitalSection>> findAllHospitalSection(@Path("id") String hospitalId);
}
