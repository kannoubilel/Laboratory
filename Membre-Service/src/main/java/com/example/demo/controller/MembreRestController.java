package com.example.demo.controller;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.PublicationBean;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.proxies.EvenementProxy;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;



@RestController
public class MembreRestController {
	@Autowired
	IMemberService iMemberService;
	@Autowired
	PublicationProxy publicationproxy;
	@Autowired
	EvenementProxy evenementproxy;
	
	@GetMapping(value = "/membres")
	public List<Membre> findAllmembres()
	{
		return iMemberService.findAll();

	}
	
	@GetMapping(value = "/membres/etudiants")
	public List<Etudiant> findAllEtudiants()
	{
		return iMemberService.findAllEtudiants();

	}
	@GetMapping(value = "/membres/enseignants")
	public List<EnseignantChercheur> findAllEnseignants()
	{
		return iMemberService.findAllEnseignants();

	}



	@GetMapping(value = "/membres/{id}")
	public Membre findoneMembre(@PathVariable Long id)
	{
		return iMemberService.findMember(id);
	}
	@GetMapping(value = "/membres/authentification/{email}")
	public Membre findMembreByEmail(@PathVariable String email) {
		return iMemberService.findByEmail(email);
	}
	
	@PostMapping(value = "/membres/etudiant")
	public Membre addMembre(@RequestBody Etudiant etd)
	{
		return iMemberService.addMember(etd);
	}

	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur ens)
	{
		return iMemberService.addMember(ens);
	}
	@PutMapping(value="/membres/etudiant/{id}")
	public Membre updatemembre(@PathVariable Long id, @RequestBody Etudiant p)
	{
		p.setId(id);
		return iMemberService.updateMember(p);
	}

	@PutMapping(value="/membres/enseignant/{id}")
	public Membre updateMembre(@PathVariable Long id, @RequestBody EnseignantChercheur p)
	{
		p.setId(id);
	       return iMemberService.updateMember(p);
	}
	@GetMapping(value="/membres/affecteretudiant/{idetd}/{idens}")
	public Membre affecter(@PathVariable Long idetd , @PathVariable Long idens )
	{
		
	       return iMemberService.affecterencadrantToetudiant(idetd, idens);
	}
	@DeleteMapping(value = "/membres/{id}")
	public void deleteMember(@PathVariable  Long id){
		iMemberService.deleteMember(id);
	}
	@GetMapping("/publications")
	public Collection<PublicationBean>listerpublication()
	{
		return publicationproxy.listeDesPublications().getContent();
		
	}
	@GetMapping("/publications/{id}")
	public PublicationBean listerunepublication(@PathVariable Long id)
	{
		return publicationproxy.recupererUnePublication(id).getContent();
		
	}
	@GetMapping("/publications/auteur/{id}")
	public List<PublicationBean>listerpublicationbymembre(@PathVariable(name="id") Long idaut)
	{
		return iMemberService.findPublicationparauteur(idaut);		
	}
	
	
	@GetMapping("/evenements")
	public CollectionModel<EvenementBean>listerevenement()
	{
		return evenementproxy.listeDesEvenements();
		
	}
	@GetMapping("/evenements/{id}")
	public EntityModel<EvenementBean> listerunevenement(@PathVariable Long id)
	{
		return evenementproxy.recupererUnEvenement(id);
		
	}
	@GetMapping("/evenements/membre/{id}")
	public List<EvenementBean> listerevenementbymembre(@PathVariable(name="id") Long id)
	{
		return iMemberService.findEvenementparmembre(id);
	}
	@GetMapping("/outils/membre/{id}")
	public List<OutilBean> listerOutilsByMembre(@PathVariable Long id){
		return iMemberService.findOutilparmembre(id);
	}
	
	@GetMapping("/fullmember/{id}")
	public Membre findAFullMember(@PathVariable(name="id") Long id)
	{
		Membre mbr=iMemberService.findMember(id);
		mbr.setPubs(iMemberService.findPublicationparauteur(id));
		mbr.setEvents(iMemberService.findEvenementparmembre(id));
		mbr.setOutils(iMemberService.findOutilparmembre(id));
		return mbr;		
	}
	@GetMapping("membre/affecterpub/{idMembre}/{idPub}")
	public void affecterMembrePublication(@PathVariable Long idMembre,@PathVariable Long idPub) {
		iMemberService.affecterauteurTopublication(idMembre, idPub);
	}
	@GetMapping("membre/affecterevent/{idMembre}/{idEvent}")
	public void affecterMembreEvent(@PathVariable Long idMembre,@PathVariable Long idEvent) {
		iMemberService.affectermembreToevenement(idMembre, idEvent);
	}
	@GetMapping("membre/affecteroutil/{idMembre}/{idOutil}")
	public void affecterMembreOutil(@PathVariable Long idMembre,@PathVariable Long idOutil) {
		iMemberService.affectermembreToOutil(idMembre, idOutil);
		
	}
	
}
