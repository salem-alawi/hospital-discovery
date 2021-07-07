package com.finalprojectaden.hospitalbooking.model;

import com.finalprojectaden.hospitalbooking.model.enums.UserGenderEnum;
import com.finalprojectaden.hospitalbooking.model.enums.UserRoleEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private UserGenderEnum gender;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRoleEnum role;

    @Column(name = "active")
    private Boolean active;

}

