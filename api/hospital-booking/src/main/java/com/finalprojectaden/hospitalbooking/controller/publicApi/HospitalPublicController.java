package com.finalprojectaden.hospitalbooking.controller.publicApi;


import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.service.HospitalSectionService;
import com.finalprojectaden.hospitalbooking.service.HospitalService;
import com.finalprojectaden.hospitalbooking.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class HospitalPublicController extends PublicBaseApiController{

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSectionService hospitalSectionService;

    @GetMapping("/hospitals")
    public ResponseEntity findAllPublicHospitals(){

        Page<Hospital> hospitalList= this.hospitalService.findAllHospital(Pageable.ofSize(50000));


        return new ResponseEntity(hospitalList, HttpStatus.OK);
    }

    @GetMapping("/hospitals/{id}/sections")
    public ResponseEntity findAllSectionByHospitalId(@PathVariable("id") UUID hospitalId){


        List<HospitalSection> hospitalSectionList=  hospitalSectionService.findAllByHospitalId(hospitalId);

        return new ResponseEntity(hospitalSectionList,HttpStatus.OK);
    }

    @GetMapping("/hospitals/{id}/sections/{hospitalSectionId}/doctor-count")
    public ResponseEntity findDoctorCountInHospitalSection(@PathVariable("hospitalSectionId")UUID hospitalSectionId) throws Exception {

        Long count= this.hospitalSectionService.findDoctorCountByHospitalIdAndSectionId(hospitalSectionId);
        return new ResponseEntity(count, HttpStatus.OK);
    }

}
