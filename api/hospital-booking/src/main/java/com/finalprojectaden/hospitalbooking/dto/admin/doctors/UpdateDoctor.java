package com.finalprojectaden.hospitalbooking.dto.admin.doctors;

import com.finalprojectaden.hospitalbooking.model.json.DoctorCertificationJson;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateDoctor {

    @NotNull
    private String name;
    @NotNull
    private String about;
    @NotNull
    private UUID sectionId;
    @NotNull
    private DoctorCertificationJson certifications;
}
