package com.finalprojectaden.hospitalbooking.controller.admin;

import com.finalprojectaden.hospitalbooking.data.HospitalDataTest;
import com.finalprojectaden.hospitalbooking.repository.HospitalRepository;
import com.finalprojectaden.hospitalbooking.service.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class HospitalControllerAdminTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalRepository hospitalRepository;

//
//    @BeforeEach
//    public void setupMock() throws Exception {
//        MockitoAnnotations.openMocks(this);
//    }


//    @Test
//    void findAllHospitalAdmin() throws Exception {
//
//        doReturn(HospitalDataTest.hospitalPage()).when(hospitalRepository).findAllPage(Pageable.ofSize(1));
//        String url = "/admin/hospitals";
//        MockHttpServletRequestBuilder requestBuilder = get(url);
//        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content", hasSize(1))).andDo(print());
//        verify(hospitalRepository, times(1)).findAllPage(any());
//    }

//    @Test
//    void createNewHospitalAdmin() throws Exception {





//    }
}