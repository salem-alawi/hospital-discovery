package com.finalprojectaden.hospitalbooking.service;


import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import com.finalprojectaden.hospitalbooking.repository.HospitalSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalSectionService {

    @Autowired
    private HospitalSectionRepository hospitalSectionRepository;

    public HospitalSection findOneById(UUID hospitalSectionId) throws Exception {
        return hospitalSectionRepository.findById(hospitalSectionId).orElseThrow(()-> new Exception("can't find hospital section"));
    }

    public List<HospitalSection> findAllByHospitalId(UUID hospitalId){
        return this.hospitalSectionRepository.findAllByHospitalId(hospitalId.toString());

    }

}
