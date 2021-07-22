package com.finalprojectaden.hospitalbooking.repository;


import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalSectionRepository extends PagingAndSortingRepository<HospitalSection, UUID> {
}
