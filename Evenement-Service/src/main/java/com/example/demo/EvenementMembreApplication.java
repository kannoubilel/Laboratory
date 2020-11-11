package com.example.demo;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.dao.EvenementRepository;
import com.example.demo.entities.Evenement;

@SpringBootApplication
@EnableDiscoveryClient
public class EvenementMembreApplication implements CommandLineRunner{
	@Autowired
	EvenementRepository evenementRepository;
	@Autowired
	RepositoryRestConfiguration configuration;
	public static void main(String[] args) {
		SpringApplication.run(EvenementMembreApplication.class, args);
	}
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Evenement.class);
		
		Evenement event1= new Evenement("hackathon", new Date(), "Sfax");
		Evenement event2= new Evenement("conference", new Date(), "France");
		Evenement event3= new Evenement("formation", new Date(), "Tunis");
		
		evenementRepository.save(event1);
		evenementRepository.save(event2);
		evenementRepository.save(event3);
}}



