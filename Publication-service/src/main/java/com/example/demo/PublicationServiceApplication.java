package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.dao.PublicationRepository;
import com.example.demo.entity.Publication;

@SpringBootApplication
@EnableDiscoveryClient
public class PublicationServiceApplication implements CommandLineRunner{
	@Autowired
	PublicationRepository publicationRepository;
	@Autowired
    RepositoryRestConfiguration configuration;

	public static void main(String[] args) {
		SpringApplication.run(PublicationServiceApplication.class, args);
	}
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Publication.class);
//		Publication pubs1= new Publication("article", "an approach for testing soa systems", new Date(), "lien", "pdf");
//		Publication pubs2= new Publication("chapitre de livre", "towards cloud computing : issues and challenges",new Date(), "lien", "pdf");
//		Publication pubs3= new Publication("article","introducing blochain systems", new Date(), "lien", "pdf");
//		 publicationRepository.save(pubs1);
//		 publicationRepository.save(pubs2);
//		 publicationRepository.save(pubs3);
		 
	}

}
