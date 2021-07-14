package com.finalprojectaden.hospitalbooking;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.model.Section;
import com.finalprojectaden.hospitalbooking.model.json.HospitalStaticConfig;
import com.finalprojectaden.hospitalbooking.repository.HospitalRepository;
import com.finalprojectaden.hospitalbooking.repository.SectionRepository;
import com.finalprojectaden.hospitalbooking.service.StorageProperties;
import com.finalprojectaden.hospitalbooking.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HospitalBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalBookingApplication.class, args);
	}


	@Bean
	public CommandLineRunner initDate(HospitalRepository hospitalRepository, SectionRepository sectionRepository, StorageService storageService){

		return (args -> {

			storageService.init();

			for(int i=0 ;i<10 ;i++) {
				Hospital hospital = new Hospital();
				hospital.setName("مستشفى عدن العام");
				hospital.setDescription("عدن كرتير بجانب عدن مول");
				hospital.setLatitude("12.782574009771233");
				hospital.setLongitude("45.04109439349575");
				hospital.setIsActive(true);
				HospitalStaticConfig hospitalStaticConfig = new HospitalStaticConfig();
				hospitalStaticConfig.setCoverImage("https://e7.pngegg.com/pngimages/573/405/png-clipart-architecture-drawing-building-building-hospital.png");
				hospital.setHospitalStaticConfig(hospitalStaticConfig);
				hospitalRepository.save(hospital);

				Section section=new Section();
				section.setCoverImage("https://www.fbmc.sa/wp-content/uploads/2020/09/Internal-Medicine.jpg");
				section.setIsActive(true);
				section.setName("الباطني");
				sectionRepository.save(section);

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
