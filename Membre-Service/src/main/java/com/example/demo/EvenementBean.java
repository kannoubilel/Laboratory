package com.example.demo;

import java.util.Date;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
public class EvenementBean {
 
	private Long id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String lieu;
	
}
