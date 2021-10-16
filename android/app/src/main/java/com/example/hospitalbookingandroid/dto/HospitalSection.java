package com.example.hospitalbookingandroid.dto;


import java.util.List;
import java.util.UUID;


public class HospitalSection {

    private UUID id;

    private Section section;
    private List<Doctor> doctors;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    //    private DoctorCertificationJson doctorCertificationJson;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

//    public DoctorCertificationJson getDoctorCertificationJson() {
//        return doctorCertificationJson;
//    }
//
//    public void setDoctorCertificationJson(DoctorCertificationJson doctorCertificationJson) {
//        this.doctorCertificationJson = doctorCertificationJson;
//    }
}
