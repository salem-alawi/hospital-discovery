package com.finalprojectaden.hospitalbooking.model;

import com.finalprojectaden.hospitalbooking.dto.admin.doctors.CreateNewDoctor;
import com.finalprojectaden.hospitalbooking.dto.admin.doctors.UpdateDoctor;
import com.finalprojectaden.hospitalbooking.model.json.DoctorCertificationJson;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "doctor_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "hospital_section_id")
    private HospitalSection hospitalSection;

    @Column(name = "is_active")
    private Boolean isActive;

    @Type(type = "json")
    @Column(name = "certification", columnDefinition = "json")
    private DoctorCertificationJson certifications;

    public Doctor(CreateNewDoctor createNewDoctor, Hospital hospital, HospitalSection hospitalSection) {
        this.name=createNewDoctor.getName();
        this.about=createNewDoctor.getAbout();
        this.hospitalSection=hospitalSection;
        this.hospital=hospital;
        this.certifications=createNewDoctor.getCertifications();
        this.isActive=true;

    }

    public void update(UpdateDoctor updateDoctor, HospitalSection hospitalSection) {
        this.name = name;
        this.about = updateDoctor.getAbout();
        this.hospitalSection = hospitalSection;
        this.certifications = updateDoctor.getCertifications();
    }
}
