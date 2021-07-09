package com.finalprojectaden.hospitalbooking.service;

import com.finalprojectaden.hospitalbooking.dto.admin.section.CreateNewSection;
import com.finalprojectaden.hospitalbooking.exception.ItemNotFoundException;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

   Supplier<ItemNotFoundException> NOT_FOUND=()-> {
       return new ItemNotFoundException("can't find resource");
   };

    public Page<Section> findAll(Pageable pageable) {

        return this.sectionRepository.findAll(pageable);
    }

    public Section findOneById(UUID sectionId) throws ItemNotFoundException {

        return this.sectionRepository.findById(sectionId).orElseThrow(NOT_FOUND);
    }


    public Section createNewSection(CreateNewSection createNewSection) {

        Section section=new Section(createNewSection);

        return this.sectionRepository.save(section);
    }
}
