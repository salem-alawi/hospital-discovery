package com.finalprojectaden.hospitalbooking;

import com.finalprojectaden.hospitalbooking.model.Doctor;
import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.model.HospitalSection;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.model.json.DoctorCertificationJson;
import com.finalprojectaden.hospitalbooking.model.json.HospitalContactInfo;
import com.finalprojectaden.hospitalbooking.model.json.HospitalStaticConfig;
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

            for (int i = 0; i < 10; i++) {
                Hospital hospital = new Hospital();
                hospital.setName("مستشفى عدن العام");
                hospital.setDescription("عدن كرتير بجانب عدن مول");
                hospital.setLatitude("12.782574009771233");
                hospital.setLongitude("45.04109439349575");
                hospital.setIsActive(true);
                HospitalStaticConfig hospitalStaticConfig = new HospitalStaticConfig();

                HospitalContactInfo hospitalContactInfo = new HospitalContactInfo();
                hospitalContactInfo.setName("الاستقبال");
                hospitalContactInfo.setEmail("hope@gmail.com");
                hospitalContactInfo.setPhoneNumber("+967736265898");

                List<HospitalContactInfo> hospitalContactInfos = new ArrayList<>();
                hospitalContactInfos.add(hospitalContactInfo);

                hospitalStaticConfig.setHospitalContactInfos(hospitalContactInfos);
                hospitalStaticConfig.setCoverImage("https://e7.pngegg.com/pngimages/573/405/png-clipart-architecture-drawing-building-building-hospital.png");
                hospital.setHospitalStaticConfig(hospitalStaticConfig);
                hospital = hospitalRepository.save(hospital);

                Section section = new Section();
                section.setCoverImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTEjPuef2mAp3DHNwm8wXbmLjXyc6zyrXKmw&usqp=CAUءذءذ");
                section.setIsActive(true);
                section.setName("الباطني");
                section = sectionRepository.save(section);


                HospitalSection hospitalSection = new HospitalSection();
                hospitalSection.setHospital(hospital);
                hospitalSection.setSection(section);
                hospitalSection = hospitalSectionRepository.save(hospitalSection);

                Doctor doctor = new Doctor();
                doctor.setHospital(hospital);
                doctor.setName("محمد مصطفى");
                doctor.setAbout("دكتور باطني");
                doctor.setHospitalSectionId(hospitalSection);
                doctor.setCertifications(new DoctorCertificationJson());
                doctor.setIsActive(true);
                doctorRepository.save(doctor);

            }


//			Hospital hospital2=new Hospital();
//			hospital2.setName("مستشفى عدن العام");
//			HospitalStaticConfig hospitalStaticConfig2=new HospitalStaticConfig();
//			hospitalStaticConfig.setCoverImage("https://lh3.googleusercontent.com/proxy/AHu7dUX_eN0TWhyzjnbhPPwWp4Is4e3jSSm6EuQ7QFmnKrUbSTVf438uT_YY57LrpIpzyDedxsFpGclhU2CUXSbHtvpdVdgrKj7A-hDd8lAA30B1rPZeFr9XxISuFt8IcI_Z01Mnv9G9tmg");
//			hospital2.setHospitalStaticConfig(hospitalStaticConfig2);
//			hospitalRepository.save(hospital2);

        });
    }
}
