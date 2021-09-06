package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.service.HospitalSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
