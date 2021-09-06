package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.dto.admin.section.CreateNewSection;
import com.finalprojectaden.hospitalbooking.exception.ItemNotFoundException;
import com.finalprojectaden.hospitalbooking.model.Doctor;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.service.DoctorService;
import com.finalprojectaden.hospitalbooking.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class SectionControllerAdmin extends AdminBaseController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/sections")
    public ResponseEntity findAllSectionAdmin(Pageable pageable) {

        return new ResponseEntity(this.sectionService.findAll(pageable), HttpStatus.OK);

    }

    @PostMapping("/sections")
    public ResponseEntity createNewSection(@RequestBody CreateNewSection createNewSection){
        return new ResponseEntity(this.sectionService.createNewSection(createNewSection),HttpStatus.OK);
    }

    @GetMapping("/sections/{id}")
    public ResponseEntity findOneByIdAdmin(@PathVariable("id") UUID sectionId) throws ItemNotFoundException {
        return new ResponseEntity(this.sectionService.findOneById(sectionId), HttpStatus.OK);
    }



//    @PutMapping("/sections/{id}")
//    public ResponseEntity updateOneSection



}
