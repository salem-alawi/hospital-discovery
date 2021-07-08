package com.finalprojectaden.hospitalbooking.event.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HOSPITAL_CREATE_PAYLOAD {

    private UUID id;

}
