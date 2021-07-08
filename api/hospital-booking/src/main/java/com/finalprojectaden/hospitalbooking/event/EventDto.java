package com.finalprojectaden.hospitalbooking.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDto<T,Y> implements Serializable {

    private T action;
    private Y payload;
}
