package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.service.HospitalSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class HospitalSectionController extends AdminBaseController{


    @Autowired
    private HospitalSectionService hospitalSectionService;

    @GetMapping("/hospitals/{id}/sections/{sectionId}/doctor-count")
    public ResponseEntity findDoctorCount(@PathVariable("id") UUID hospitalId,
                                          @PathVariable("sectionId")UUID hospitalSectionId) throws Exception {

        Long count= this.hospitalSectionService.findDoctorCountByHospitalIdAndSectionId(hospitalSectionId);
        return new ResponseEntity(count, HttpStatus.OK);
    }

    @DeleteMapping("/hospitals/{id}/sections/{hospitalSectionId}")
    public ResponseEntity removeHospitalSection(@PathVariable("hospitalSectionId")UUID hospitalSectionId){


        this.hospitalSectionService.removeById(hospitalSectionId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
