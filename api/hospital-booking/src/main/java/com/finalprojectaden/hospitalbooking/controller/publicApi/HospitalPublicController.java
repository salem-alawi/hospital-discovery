package com.finalprojectaden.hospitalbooking.controller.publicApi;


import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin    
@RestController
public class HospitalPublicController extends PublicBaseApiController{

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public ResponseEntity findAllPublicHospitals(){

        Page<Hospital> hospitalList= this.hospitalService.findAllHospital(Pageable.ofSize(50000));


        return new ResponseEntity(hospitalList, HttpStatus.OK);
    }

}
