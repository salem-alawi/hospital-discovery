package com.finalprojectaden.hospitalbooking;

import com.finalprojectaden.hospitalbooking.model.Section;

import com.finalprojectaden.hospitalbooking.repository.DoctorRepository;
import com.finalprojectaden.hospitalbooking.repository.HospitalRepository;
import com.finalprojectaden.hospitalbooking.repository.HospitalSectionRepository;
import com.finalprojectaden.hospitalbooking.repository.SectionRepository;
import com.finalprojectaden.hospitalbooking.service.StorageProperties;
import com.finalprojectaden.hospitalbooking.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HospitalBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalBookingApplication.class, args);
    }


    @Bean
    public CommandLineRunner initDate(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, SectionRepository sectionRepository, StorageService storageService, HospitalSectionRepository hospitalSectionRepository) {

        return (args -> {

            storageService.init();
            Iterable<Section> sections=sectionRepository.findAll();
            if(!sections.iterator().hasNext()){

                Section section = new Section();
                section.setCoverImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTEjPuef2mAp3DHNwm8wXbmLjXyc6zyrXKmw&usqp=CAU");
                section.setIsActive(true);
                section.setName("الباطني");
                section = sectionRepository.save(section);

                Section generalSection= new Section();
                generalSection.setCoverImage("http://essalamonline.com/wp-content/uploads/2020/05/%D8%AA%D9%88%D9%82%D9%8A%D9%81-%D8%B7%D8%A8%D9%8A%D8%A8-%D8%B9%D8%A7%D9%85-%D9%86%D8%A7%D8%A8.jpg");
                generalSection.setIsActive(true);
                generalSection.setName("طبيب عام");
                generalSection = sectionRepository.save(generalSection);
            }





        });
    }
}
