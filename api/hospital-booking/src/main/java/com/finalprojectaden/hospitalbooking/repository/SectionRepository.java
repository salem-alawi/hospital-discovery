package com.finalprojectaden.hospitalbooking.repository;

import com.finalprojectaden.hospitalbooking.model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SectionRepository extends PagingAndSortingRepository<Section, UUID> {


    @Query(value = "select * from sections where section_id=:sectionId ",nativeQuery = true)
    Optional<Section> findById(@Param("sectionId") String sectionId);

}
