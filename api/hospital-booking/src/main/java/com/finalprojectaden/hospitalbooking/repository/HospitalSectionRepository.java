package com.finalprojectaden.hospitalbooking.repository;


import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HospitalSectionRepository extends PagingAndSortingRepository<HospitalSection, UUID> {

    @Query(value = "select * from hospital_section where hospital_id=:hospitalId",nativeQuery = true)
    List<HospitalSection> findAllByHospitalId(@Param("hospitalId") String  hospitalId);
}
