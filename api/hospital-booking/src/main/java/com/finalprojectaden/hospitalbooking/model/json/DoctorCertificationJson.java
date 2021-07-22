package com.finalprojectaden.hospitalbooking.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorCertificationJson {

    private List<CertificateItemJson> certificateItem;

}
