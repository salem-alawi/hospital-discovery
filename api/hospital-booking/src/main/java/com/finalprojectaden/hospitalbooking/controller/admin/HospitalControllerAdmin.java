package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.dto.admin.hospital.CreateAndUpdateHospitalDto;
import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HospitalControllerAdmin extends AdminBaseController {


    @Autowired
    private HospitalService hospitalService;


    @GetMapping("/hospitals")
    public ResponseEntity findAllHospitalAdmin(Pageable pageable) {
        return new ResponseEntity(this.hospitalService.findAllHospital(pageable), HttpStatus.OK);
    }

    @PostMapping("/hospitals")
    public ResponseEntity createNewHospitalAdmin(@RequestBody CreateAndUpdateHospitalDto createAndUpdateHospitalDto) {

        Hospital hospital = this.hospitalService.createNewHospital(createAndUpdateHospitalDto);

        return new ResponseEntity(hospital, HttpStatus.OK);

    }

}
