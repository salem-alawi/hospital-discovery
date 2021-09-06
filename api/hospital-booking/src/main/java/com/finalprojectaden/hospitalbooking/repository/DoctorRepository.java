package com.finalprojectaden.hospitalbooking.repository;

import com.finalprojectaden.hospitalbooking.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, UUID> {


    @Query(value = "select * from doctors where hospital_id=:hospitalId",nativeQuery = true)
    List<Doctor> findAllByHospitalId(@Param("hospitalId") String  hospitalId);

    @Query(value = "select count(*) from doctors where  hospital_section_id=:hospitalSectionId ",nativeQuery = true)
    Long findCountByHospitalSectionId(@Param("hospitalSectionId") String hospitalSectionId);

    @Query(value = "select * from doctors where  hospital_section_id=:hospitalSectionId ",nativeQuery = true)
    List<Doctor> findAllByHospitalSectionId(@Param("hospitalSectionId") String hospitalSectionId);

}
