package com.example.hospitalbookingandroid.dto;

import java.io.Serializable;
import java.util.UUID;

public class Hospital implements Serializable {

    private UUID id;

    private String name;

    private String description;

    private String longitude;

    private String latitude;

    private HospitalTypeEnum type = HospitalTypeEnum.PRIVATE;

    private HospitalStaticConfig hospitalStaticConfig = new HospitalStaticConfig();


    private Boolean isActive;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public HospitalTypeEnum getType() {
        return type;
    }

    public void setType(HospitalTypeEnum type) {
        this.type = type;
    }

    public HospitalStaticConfig getHospitalStaticConfig() {
        return hospitalStaticConfig;
    }

    public void setHospitalStaticConfig(HospitalStaticConfig hospitalStaticConfig) {
        this.hospitalStaticConfig = hospitalStaticConfig;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", type=" + type +
                ", hospitalStaticConfig=" + hospitalStaticConfig +
                ", isActive=" + isActive +
                '}';
    }
}
