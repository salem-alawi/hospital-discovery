package com.finalprojectaden.hospitalbooking.model;

import com.finalprojectaden.hospitalbooking.dto.admin.hospital.CreateAndUpdateHospitalDto;
import com.finalprojectaden.hospitalbooking.model.enums.HospitalTypeEnum;
import com.finalprojectaden.hospitalbooking.model.json.HospitalStaticConfig;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "hospitals")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Hospital {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "hospital_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private HospitalTypeEnum type = HospitalTypeEnum.PRIVATE;

    @Type(type = "json")
    @Column(name = "hospital_config", columnDefinition = "json")
    private HospitalStaticConfig hospitalStaticConfig = new HospitalStaticConfig();


    @Column(name = "active")
    private Boolean isActive;


    public Hospital(CreateAndUpdateHospitalDto createAndUpdateHospitalDto) {
        copyHospitalDto(createAndUpdateHospitalDto);
    }

    private void copyHospitalDto(CreateAndUpdateHospitalDto createAndUpdateHospitalDto) {
        this.name = createAndUpdateHospitalDto.getName();
        this.description = createAndUpdateHospitalDto.getDescription();
        this.longitude = createAndUpdateHospitalDto.getLongitude();
        this.latitude = createAndUpdateHospitalDto.getLatitude();
        this.type = createAndUpdateHospitalDto.getType();
        this.hospitalStaticConfig = createAndUpdateHospitalDto.getHospitalStaticConfig();
        this.isActive = true;
    }

    public static Hospital dumpData() {
        Hospital hospital=new Hospital();
        hospital.setName("مستشفى عدن العام");
        hospital.setDescription("قسم خاص بامراض القلب");
        hospital.setId(UUID.randomUUID());
        hospital.setIsActive(true);
        hospital.setLatitude("");
        hospital.setLongitude("");
        hospital.setType(HospitalTypeEnum.GOVERNMENT);
        hospital.setHospitalStaticConfig(new HospitalStaticConfig());
        return hospital;
    }



    public void update(CreateAndUpdateHospitalDto createAndUpdateHospitalDto) {
        copyHospitalDto(createAndUpdateHospitalDto);
    }

    public void disable() {

        this.isActive=false;
    }
}
