package com.finalprojectaden.hospitalbooking.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateItemJson {

    private String image;
    private String name;


}
