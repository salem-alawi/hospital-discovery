package com.example.hospitalbookingandroid.dto;


import java.util.UUID;

public class Doctor {

    private UUID id;

    private String name;
    private String image;
    private String about;
    private DoctorCertificationJson certifications;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public DoctorCertificationJson getCertifications() {
        return certifications;
    }

    public void setCertifications(DoctorCertificationJson certifications) {
        this.certifications = certifications;
    }
}
