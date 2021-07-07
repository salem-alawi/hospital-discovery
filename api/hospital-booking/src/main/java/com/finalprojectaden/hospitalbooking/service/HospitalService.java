package com.finalprojectaden.hospitalbooking.service;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.repository.HospitallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private HospitallRepository hospitallRepository;

    public Page<Hospital> findAllHospital(Pageable pageable) {
        return this.hospitallRepository.findAll(pageable);
    }


}
