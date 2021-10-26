package com.finalprojectaden.hospitalbooking.service;

import com.finalprojectaden.hospitalbooking.dto.admin.section.CreateNewSection;
import com.finalprojectaden.hospitalbooking.exception.ItemNotFoundException;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    Supplier<ItemNotFoundException> NOT_FOUND = () -> {
        return new ItemNotFoundException("can't find resource");
    };

    public Page<Section> findAll(Pageable pageable) {

        return this.sectionRepository.findAll(pageable);
    }

    public Section findOneById(UUID sectionId) throws ItemNotFoundException {

        return this.sectionRepository.findById(sectionId.toString()).orElseThrow(NOT_FOUND);
    }


    public Section createNewSection(CreateNewSection createNewSection) {

        Section section = new Section(createNewSection);

        return this.sectionRepository.save(section);
    }

    public List<Section> findAllExceptListOfId(List<String> hospitalSectionList) {
        if (hospitalSectionList.size() > 0)
            return this.sectionRepository.findAllExceptionListOfId(hospitalSectionList);
        else
            return this.sectionRepository.findAllSection();

    }
}
