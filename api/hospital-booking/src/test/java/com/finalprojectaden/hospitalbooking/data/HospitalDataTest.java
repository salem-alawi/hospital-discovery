package com.finalprojectaden.hospitalbooking.data;


import com.finalprojectaden.hospitalbooking.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class HospitalDataTest {

    public static Page<Hospital> hospitalPage() {

        List<Hospital> hospitalList = new ArrayList<>();
        hospitalList.add(Hospital.dumpData());
        Page<Hospital> page = new PageImpl(hospitalList);
        return page;
    }

}
