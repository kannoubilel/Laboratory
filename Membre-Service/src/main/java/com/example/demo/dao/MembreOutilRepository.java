package com.example.demo.dao;

import com.example.demo.OutilBean;
import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Outil_Ids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface MembreOutilRepository extends JpaRepository<Membre_Outil, Membre_Outil_Ids> {
    @Query("select m from Membre_Outil m where m.id.membre_id=:x")
    public List<Membre_Outil> findOutilsByMember(@Param("x") Long id);
}
