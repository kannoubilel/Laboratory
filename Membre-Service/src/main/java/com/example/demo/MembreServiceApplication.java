package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.demo.proxies.OutilProxy;
import com.example.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MemberRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.proxies.EvenementProxy;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;

import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHypermediaSupport(type = HypermediaType.HAL)//to resolve the pb that feign do not understand json created by data rest
public class MembreServiceApplication implements CommandLineRunner {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	IMemberService iMemberService;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	PublicationProxy publicationProxy;
	@Autowired
	EvenementProxy evenementProxy;
	@Autowired
	OutilProxy outilProxy;
	@Resource
	FileStorageService storageService;
	

	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}


	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
		
		// créer deux instances de type membre un enseignant et une autre étudiant
		//sauvegrader les 2 dans la base de données
		/*EnseignantChercheur ens1= new EnseignantChercheur("01752354", "Jmaiel", "Mohamed", new Date(), "", "jmaiel@enis.tn", "0000", "ENIS", "Professeur");
		memberRepository.save(ens1);
		
		Membre ens2= new EnseignantChercheur("01752354", "mariam", "lahami", new Date(), "",  "lahami@enis.tn", "2222", "ENIS", "MA");
	
		memberRepository.save(ens2);
		
		SimpleDateFormat dateFormatter =new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = dateFormatter.parse("2010-05-01");
		Date date2 = dateFormatter.parse("2019-05-01");
		Date date3 = dateFormatter.parse("2012-05-01");
		Date date4 = dateFormatter.parse("2012-03-01");
		Etudiant etd1= new Etudiant("081705454", "ben fekih", "rim", date1, "",  "rim@enis.rn", "11111", date1, "test", "these",null);
		Etudiant etd2= new Etudiant("885705454", "ben ahmed", "sana", date2, "",  "sana@enis.rn", "11111", date2, "test", "mastere",null);
		Etudiant etd3= new Etudiant("081454", "chaari", "rim", date3, "",  "chaari@enis.rn", "11111", date3, "test", "these", null);
		Etudiant etd4= new Etudiant("081454", "ayadi", "ali", date4, "",  "ayadi@enis.rn", "11111", date4, "test", "mastre",null);
		memberRepository.save(etd1);
		memberRepository.save(etd2);
		memberRepository.save(etd3);
		memberRepository.save(etd4);*/
		
		
		// affecter un étudiant à un enseigant
		iMemberService.affecterencadrantToetudiant(5L, 1L);
		iMemberService.affecterencadrantToetudiant(6L, 1L);
		iMemberService.affecterencadrantToetudiant(3L, 1L);
		
		// find etudiants encadré par 1
		//List<Etudiant> etds=etudiantRepository.findByEncadrant(ens1);
		//System.out.print(etds.size());
		
		//affecter une publication à un auteur
		
		//1-récupérer la publication par id en invoquant publication-service
		PublicationBean pub1=publicationProxy.recupererUnePublication(1L).getContent();
		System.out.println(pub1.getTitre()+ "  "+pub1.getId());
		PublicationBean pub2=publicationProxy.recupererUnePublication(2L).getContent();
		System.out.println(pub2.getTitre()+ "  "+pub2.getId());
		
		//2- affecter pub à member
		iMemberService.affecterauteurTopublication(1L,pub1.getId());
		iMemberService.affecterauteurTopublication(2L,pub1.getId());
		iMemberService.affecterauteurTopublication(1L,pub2.getId());
		
		//afficher le nombre de publication du membre 1
		List<PublicationBean> lstpubs=iMemberService.findPublicationparauteur(1L);
		lstpubs.forEach(r->System.out.println(r.toString()));
		
		
		PublicationBean p=publicationProxy.recupererUnePublication(1L).getContent();
		System.out.println(p);
		
		
		
		//affecter un evenement à un membre
		
				//1-récupérer l'evenement par id en invoquant evenement
				EvenementBean event1=evenementProxy.recupererUnEvenement(1L).getContent();
				System.out.println(event1.getTitre()+ "  "+event1.getId());
				EvenementBean event2=evenementProxy.recupererUnEvenement(2L).getContent();
				System.out.println(event1.getTitre()+ "  "+event2.getId());
				
				//2- affecter event à membre
				iMemberService.affectermembreToevenement(1L,event1.getId());
				iMemberService.affecterauteurTopublication(2L,event1.getId());
				iMemberService.affecterauteurTopublication(1L,event2.getId());
				
				//afficher le nombre d'évènements du membre 1
				List<EvenementBean> lstevents=iMemberService.findEvenementparmembre(1L);
				lstevents.forEach(e->System.out.println(e.toString()));
				
				
				EvenementBean ev =evenementProxy.recupererUnEvenement(1L).getContent();
				System.out.println(ev);

				CollectionModel<OutilBean> outils=outilProxy.findAll();
				outils.forEach(o->{
					System.out.println(o);
				});
				iMemberService.affectermembreToOutil(1L,1L);
				iMemberService.affectermembreToOutil(1L,2L);
				iMemberService.affectermembreToOutil(2L,1L);
	}

}
