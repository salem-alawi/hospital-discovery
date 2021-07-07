package com.finalprojectaden.hospitalbooking.repository;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitallRepository extends PagingAndSortingRepository<Hospital, UUID> {






}
