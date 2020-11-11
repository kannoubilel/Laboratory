package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.OutilBean;
import com.example.demo.dao.*;
import com.example.demo.entities.*;
import com.example.demo.proxies.OutilProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EvenementBean;
import com.example.demo.PublicationBean;
import com.example.demo.proxies.EvenementProxy;
import com.example.demo.proxies.PublicationProxy;
@Service
public class MemberImpl implements IMemberService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	@Autowired
	Membrepubrepository membrepubrepository;
	@Autowired
	MembreEventRepository membreeventrepository;
	@Autowired
	PublicationProxy proxy;
	@Autowired
	EvenementProxy eveproxy;
	@Autowired
	OutilProxy outilProxy;
	@Autowired
	MembreOutilRepository membreOutilRepository;
	public Membre addMember(Membre m) {
		memberRepository.save(m);
		return m;
	}

	
	public void deleteMember(Long id) {
		
		memberRepository.deleteById(id);

	}
	public Membre updateMember(Membre m) {
		
		return memberRepository.saveAndFlush(m);
	}
	public Membre findMember(Long id) {
	Membre m= (Membre)memberRepository.findById(id).get();
		return m;
	}
	public List<Membre> findAll() {
		
		return memberRepository.findAll();
	}

	public Membre findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}
	public Membre findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	public List<Membre> findByNom(String nom) {
		return memberRepository.findByNom(nom);
	}
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}
	public List<EnseignantChercheur> findByGrade(String grade) {
		
		return enseignantChercheurRepository.findByGrade(grade);
	}
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
	
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}
	public List<Etudiant> findAllEtudiants() {
		return etudiantRepository.findAll();
	}
	public List<EnseignantChercheur> findAllEnseignants() {
		return enseignantChercheurRepository.findAll();
	}


	@Override
	public Etudiant affecterencadrantToetudiant(Long idetd, Long idens) {
		// TODO Auto-generated method stub
		Etudiant etd= etudiantRepository.findById(idetd).get();
		EnseignantChercheur ens= enseignantChercheurRepository.findById(idens).get();
		etd.setEncadrant(ens);

		return etudiantRepository.save(etd);
	}


	@Override
	public void affecterauteurTopublication(Long idauteur, Long idpub) {
		Membre mbr= memberRepository.findById(idauteur).get();
		Membre_Publication mbs= new Membre_Publication();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Pub_Ids(idpub, idauteur));
		membrepubrepository.save(mbs);
	}
	@Override
	public void affectermembreToevenement(Long idmembre, Long idevent) {
		Membre mbr= memberRepository.findById(idmembre).get();
		Membre_Evenement mvs= new Membre_Evenement();
		mvs.setMembre(mbr);
		mvs.setId(new Membre_Eve_Ids(idevent, idmembre));
		membreeventrepository.save(mvs);
	}

	@Override
	public List<PublicationBean> findPublicationparauteur(Long idauteur) {
		List<PublicationBean> pubs=new ArrayList<PublicationBean>();
	
		List< Membre_Publication> idpubs=membrepubrepository.findpubId(idauteur);
		
		idpubs.forEach(s->{
			System.out.println(s);
			pubs.add(proxy.recupererUnePublication(s.getId().getPublication_id()).getContent());
			
		}
		);
		
		return pubs;
	}
	
	@Override
	public List<EvenementBean> findEvenementparmembre(Long idmembre) {
		List<EvenementBean> events=new ArrayList<EvenementBean>();
	
		List< Membre_Evenement> idevents=membreeventrepository.findEventId(idmembre);
		
		idevents.forEach(e->{
			System.out.println(e);
			events.add(eveproxy.recupererUnEvenement(e.getId().getEvenement_id()).getContent());
			
		}
		);
		
		return events;
	}

	@Override
	public void affectermembreToOutil(Long idMembre, Long idOutil) {
		Membre membre=memberRepository.findById(idMembre).get();
		Membre_Outil_Ids id=new Membre_Outil_Ids(idMembre,idOutil);
		Membre_Outil membre_outil=new Membre_Outil();
		membre_outil.setMembre(membre);
		membre_outil.setId(id);
		membreOutilRepository.save(membre_outil);
	}

	@Override
	public List<OutilBean> findOutilparmembre(Long idMembre) {
		List<Membre_Outil>idOutils =membreOutilRepository.findOutilsByMember(idMembre);
		List<OutilBean>outils=new ArrayList<OutilBean>();
		idOutils.forEach(id->{
			outils.add(outilProxy.findById(id.getId().getOutil_id()));
		});
		return 	outils;
	}

}
