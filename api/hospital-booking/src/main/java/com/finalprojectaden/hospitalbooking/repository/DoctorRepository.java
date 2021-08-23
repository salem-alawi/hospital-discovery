package com.finalprojectaden.hospitalbooking.repository;

import com.finalprojectaden.hospitalbooking.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, UUID> {


    @Query(value = "select * from doctors where hospital_id=:hospitalId",nativeQuery = true)
    List<Doctor> findAllByHospitalId(String  hospitalId);


}
