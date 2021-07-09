package com.finalprojectaden.hospitalbooking;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.model.json.HospitalStaticConfig;
import com.finalprojectaden.hospitalbooking.repository.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalBookingApplication.class, args);
	}


	@Bean
	public CommandLineRunner initDate(HospitalRepository hospitalRepository){

		return (args -> {

			for(int i=0 ;i<10 ;i++) {
				Hospital hospital = new Hospital();
				hospital.setName("مستشفى عدن العام");
				hospital.setDescription("عدن كرتير بجانب عدن مول");
				hospital.setLatitude("12.782574009771233");
				hospital.setLongitude("45.04109439349575");
				hospital.setIsActive(true);
				HospitalStaticConfig hospitalStaticConfig = new HospitalStaticConfig();
				hospitalStaticConfig.setCoverImage("https://lh3.googleusercontent.com/proxy/AHu7dUX_eN0TWhyzjnbhPPwWp4Is4e3jSSm6EuQ7QFmnKrUbSTVf438uT_YY57LrpIpzyDedxsFpGclhU2CUXSbHtvpdVdgrKj7A-hDd8lAA30B1rPZeFr9XxISuFt8IcI_Z01Mnv9G9tmg");
				hospital.setHospitalStaticConfig(hospitalStaticConfig);
				hospitalRepository.save(hospital);
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
