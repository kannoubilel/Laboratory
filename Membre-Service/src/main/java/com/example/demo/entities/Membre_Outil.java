package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre_Outil {
    @EmbeddedId
    Membre_Outil_Ids id ;
    @ManyToOne
    @MapsId("membre_id")
    Membre membre;

}
