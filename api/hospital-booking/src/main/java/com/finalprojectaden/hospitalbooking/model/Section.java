package com.finalprojectaden.hospitalbooking.model;

import com.finalprojectaden.hospitalbooking.dto.admin.section.CreateNewSection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "section_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "coverImage")
    private String coverImage;

    @Column(name = "isActive")
    private Boolean isActive;

    public Section(CreateNewSection createNewSection) {

        this.name = createNewSection.getName();
        this.coverImage = createNewSection.getCoverImage();
        this.isActive = true;
    }
}


