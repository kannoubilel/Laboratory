package com.example.demo;

import com.example.demo.dao.OutilRepository;
import com.example.demo.entities.Outil;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class OutilServiceApplication implements CommandLineRunner {
    @Autowired
    private OutilRepository outilRepository;
    @Autowired
    private RepositoryRestConfiguration configuration;
    public static void main(String[] args) {
        SpringApplication.run(OutilServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        configuration.exposeIdsFor(Outil.class);
//        SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=dateFormatter.parse("2020-11-09");
//        Date date2=dateFormatter.parse("2020-10-09");
//        Date date3=dateFormatter.parse("2020-09-09");
//
//        Outil outil1=new Outil(date1,"source1");
//        outilRepository.save(outil1);
//        Outil outil2=new Outil(date2,"source2");
//        outilRepository.save(outil2);
//        Outil outil3=new Outil(date3,"source3");
//        outilRepository.save(outil3);
    }
}
