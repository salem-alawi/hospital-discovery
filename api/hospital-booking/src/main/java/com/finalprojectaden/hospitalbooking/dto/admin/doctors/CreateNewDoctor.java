package com.finalprojectaden.hospitalbooking.dto.admin.doctors;

import com.finalprojectaden.hospitalbooking.model.json.DoctorCertificationJson;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateNewDoctor {


    private String name;
    private String about;
    private String image;
    private UUID hospitalId;
    private UUID hospitalSectionId;
    private DoctorCertificationJson certifications;


}
