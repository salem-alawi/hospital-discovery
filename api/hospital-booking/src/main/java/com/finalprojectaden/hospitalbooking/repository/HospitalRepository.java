package com.finalprojectaden.hospitalbooking.repository;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends PagingAndSortingRepository<Hospital, UUID> {


    @Query(value = "select * from hospitals", countQuery = "select count(*) from hospitals", nativeQuery = true)
    Page<Hospital> findAllPage(Pageable pageable);


}
