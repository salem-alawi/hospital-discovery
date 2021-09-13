package com.example.hospitalbookingandroid.dto;


import java.util.UUID;


public class HospitalSection {

    private UUID id;

    private Hospital hospital;

    private Section section;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
