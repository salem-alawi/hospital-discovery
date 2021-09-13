package com.example.hospitalbookingandroid.dto;


import java.util.ArrayList;
import java.util.List;


public class HospitalStaticConfig {

    private String coverImage = "";
    private List<String> images = new ArrayList<>();
    private List<HospitalContactInfo> hospitalContactInfos = new ArrayList<>();

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<HospitalContactInfo> getHospitalContactInfos() {
        return hospitalContactInfos;
    }

    public void setHospitalContactInfos(List<HospitalContactInfo> hospitalContactInfos) {
        this.hospitalContactInfos = hospitalContactInfos;
    }
}
