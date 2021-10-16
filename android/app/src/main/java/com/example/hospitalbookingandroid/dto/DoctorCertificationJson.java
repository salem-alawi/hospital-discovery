package com.example.hospitalbookingandroid.dto;

import java.util.List;

public class DoctorCertificationJson {

    private List<CertificateItemJson> certificateItem;

    public List<CertificateItemJson> getCertificateItem() {
        return certificateItem;
    }

    public void setCertificateItem(List<CertificateItemJson> certificateItem) {
        this.certificateItem = certificateItem;
    }
}
