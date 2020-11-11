package com.example.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Membre_Eve_Ids;
import com.example.demo.entities.Membre_Evenement;


public interface MembreEventRepository extends JpaRepository<Membre_Evenement, Membre_Eve_Ids> {
	@Query("select m from Membre_Evenement m where m.id.membre_id=:x")
	List<Membre_Evenement> findEventId(@Param ("x") Long memId);
	
}

