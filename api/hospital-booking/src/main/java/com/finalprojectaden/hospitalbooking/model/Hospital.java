package com.finalprojectaden.hospitalbooking.model;

import com.finalprojectaden.hospitalbooking.model.enums.HospitalTypeEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "hospitals")
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
    private HospitalTypeEnum type;

}
