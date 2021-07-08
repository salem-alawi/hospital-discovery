package com.finalprojectaden.hospitalbooking.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalStaticConfig {

    private List<String> images=new ArrayList<>();
    private List<HospitalContactInfo> hospitalContactInfos=new ArrayList<>();


}
