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

            Section section = new Section();
            section.setCoverImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTEjPuef2mAp3DHNwm8wXbmLjXyc6zyrXKmw&usqp=CAUءذءذ");
            section.setIsActive(true);
            section.setName("الباطني");
            section = sectionRepository.save(section);

            Section generalSection= new Section();
            generalSection.setCoverImage("http://essalamonline.com/wp-content/uploads/2020/05/%D8%AA%D9%88%D9%82%D9%8A%D9%81-%D8%B7%D8%A8%D9%8A%D8%A8-%D8%B9%D8%A7%D9%85-%D9%86%D8%A7%D8%A8.jpg");
            generalSection.setIsActive(true);
            generalSection.setName("طبيب عام");
            generalSection = sectionRepository.save(generalSection);

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





                HospitalSection hospitalSection = new HospitalSection();
                hospitalSection.setHospital(hospital);
                hospitalSection.setSection(section);
                hospitalSection = hospitalSectionRepository.save(hospitalSection);


                HospitalSection hospitalSectionGeneral = new HospitalSection();
                hospitalSectionGeneral.setHospital(hospital);
                hospitalSectionGeneral.setSection(generalSection);
                hospitalSectionGeneral = hospitalSectionRepository.save(hospitalSectionGeneral);





                Doctor doctor = new Doctor();
                doctor.setHospital(hospital);
                doctor.setName("محمد مصطفى");
                doctor.setImage("https://cdn4.vectorstock.com/i/thumb-large/51/63/flat-doctor-avatar-for-website-chat-window-vector-38795163.jpg");
                doctor.setAbout("دكتور باطني");
                doctor.setHospitalSectionId(hospitalSection);
                doctor.setCertifications(new DoctorCertificationJson());
                doctor.setIsActive(true);
                doctorRepository.save(doctor);


                Doctor doctorGeneral = new Doctor();
                doctorGeneral.setHospital(hospital);
                doctorGeneral.setImage("https://cdn3.vectorstock.com/i/thumb-large/95/77/portrait-doctor-or-health-worker-in-medical-vector-36659577.jpg");
                doctorGeneral.setName("صالح عمد");
                doctorGeneral.setAbout("دكتور عام");
                doctorGeneral.setHospitalSectionId(hospitalSectionGeneral);
                doctorGeneral.setCertifications(new DoctorCertificationJson());
                doctorGeneral.setIsActive(true);
                doctorRepository.save(doctorGeneral);

            }


        });
    }
}
