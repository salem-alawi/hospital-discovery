package com.finalprojectaden.hospitalbooking;

import com.finalprojectaden.hospitalbooking.model.Hospital;
import com.finalprojectaden.hospitalbooking.repository.HospitallRepository;
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
	public CommandLineRunner initDate(HospitallRepository hospitallRepository){

		return (args -> {

			Hospital hospital=new Hospital();
			hospital.setName("مستشفى عدن العام");
			hospitallRepository.save(hospital);

		});
	}
}
