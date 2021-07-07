package com.finalprojectaden.hospitalbooking.controller;

import com.finalprojectaden.hospitalbooking.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {


    @Autowired
    private HospitalService hospitalService;

    @GetMapping("")
    public ResponseEntity findAllHospital(Pageable pageable){


        return new ResponseEntity(this.hospitalService.findAllHospital(pageable), HttpStatus.OK);
    }

}
