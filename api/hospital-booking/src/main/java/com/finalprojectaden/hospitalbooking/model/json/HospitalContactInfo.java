package com.finalprojectaden.hospitalbooking.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalContactInfo {

    private String name;
    private String phoneNumber;
    private String email;
}
