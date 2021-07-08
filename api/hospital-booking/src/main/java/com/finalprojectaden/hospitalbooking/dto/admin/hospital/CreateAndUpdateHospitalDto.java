package com.finalprojectaden.hospitalbooking.dto.admin.hospital;

import com.finalprojectaden.hospitalbooking.model.enums.HospitalTypeEnum;
import com.finalprojectaden.hospitalbooking.model.json.HospitalStaticConfig;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class CreateAndUpdateHospitalDto {

    private String name;

    private String description;

    private String longitude;

    private String latitude;

    @Enumerated(EnumType.ORDINAL)
    private HospitalTypeEnum type;

    private HospitalStaticConfig hospitalStaticConfig;

}
