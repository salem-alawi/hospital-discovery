package com.finalprojectaden.hospitalbooking.service;

import com.finalprojectaden.hospitalbooking.dto.admin.hospital.CreateAndUpdateHospitalDto;
import com.finalprojectaden.hospitalbooking.event.EventAction;
import com.finalprojectaden.hospitalbooking.event.EventDto;
import com.finalprojectaden.hospitalbooking.event.payloads.HOSPITAL_CREATE_PAYLOAD;
import com.finalprojectaden.hospitalbooking.exception.ItemNotFoundException;
import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private HospitalSectionService hospitalSectionService;

    @Autowired
    private StreamBridge streamBridge;

    public Page<Hospital> findAllHospital(Pageable pageable) {
        return this.hospitalRepository.findAllPage(pageable);
    }


    public Hospital createNewHospital(CreateAndUpdateHospitalDto createAndUpdateHospitalDto) {
        Hospital newHospital = new Hospital(createAndUpdateHospitalDto);
        newHospital = this.hospitalRepository.save(newHospital);

        EventDto eventDto = new EventDto(EventAction.HOSPITAL_CREATE, new HOSPITAL_CREATE_PAYLOAD(newHospital.getId()));
//        this.streamBridge.send("uploadSystemEvent-out-0", eventDto);

        return newHospital;
    }

    public Hospital findOneById(UUID hospitalId) throws Exception {

        return this.hospitalRepository.findById(hospitalId).orElseThrow(() -> new Exception("can't find hospital"));

    }

    public void save(Hospital hospital) {

        this.hospitalRepository.save(hospital);
    }

    public void updateHospital(UUID hospitalId, CreateAndUpdateHospitalDto createAndUpdateHospitalDto) throws Exception {

        Hospital hospital = this.findOneById(hospitalId);

        hospital.update(createAndUpdateHospitalDto);

        this.hospitalRepository.save(hospital);
    }

    public void disableHospital(UUID hospitalId) throws Exception {

        Hospital hospital = this.findOneById(hospitalId);
        hospital.disable();
        this.hospitalRepository.save(hospital);


    }

    public void addSectionToHospital(UUID hospitalId, UUID sectiondId) throws Exception {

        Section section=this.sectionService.findOneById(sectiondId);
        Hospital hospital=this.findOneById(hospitalId);
        HospitalSection hospitalSection=new HospitalSection();
        hospitalSection.setHospital(hospital);
        hospitalSection.setSection(section);


        this.hospitalSectionService.save(hospitalSection);

    }
}
