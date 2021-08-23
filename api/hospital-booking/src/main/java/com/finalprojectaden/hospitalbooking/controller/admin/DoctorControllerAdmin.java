package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.dto.admin.doctors.CreateNewDoctor;
import com.finalprojectaden.hospitalbooking.model.Doctor;
import com.finalprojectaden.hospitalbooking.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class DoctorControllerAdmin extends AdminBaseController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity findAllDoctorsAdmin(Pageable pageable) {

        Page<Doctor> doctors = this.doctorService.findAllDoctor(pageable);

        return new ResponseEntity(doctors, HttpStatus.OK);
    }

    @PostMapping("/doctors")
    public ResponseEntity createNewDoctors(@RequestBody CreateNewDoctor createNewDoctor) throws Exception {

        Doctor doctor = this.doctorService.createNewDoctor(createNewDoctor);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }




}
