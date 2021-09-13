package com.example.hospitalbookingandroid.dto;

public enum  HospitalTypeEnum {

    GOVERNMENT,
    PRIVATE;

    public int toValue(){
        return this.ordinal();
    }

}
