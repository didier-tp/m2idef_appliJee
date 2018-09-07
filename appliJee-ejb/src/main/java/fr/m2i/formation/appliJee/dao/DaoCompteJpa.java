package fr.m2i.formation.appliJee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * implementation du DAO en s'appuyant sur la technologie 
 * JPA = Java Persistence Api (entityManager)
 */

import fr.m2i.formation.appliJee.entity.Compte;

@Stateless //EJB Session sans état (EJB de traitement)
@Local //accès local possible (depuis autre EJB ou partie web)
//@TransactionManagement(TransactionManagementType.CONTAINER) par defaut sur EJB , transactions automatiques
@TransactionManagement(TransactionManagementType.BEAN) //transaction à coder nous même
@TransactionAttribute(TransactionAttributeType.NEVER)
public class DaoCompteJpa implements IDaoCompte {
	
	//@PersistenceContext(unitName="appliJee-ejb") //initialise entityManager 
	//via META-INF/persistence.xml
	private EntityManager entityManager; //objet principal de la techno JPA
	
	private EntityManagerFactory emf;
	
	
	@PostConstruct()
	public void init() {
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		//le provider JPA ici Hibernate correspond à la technologie d'implémentation (code concret)
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("javax.persistence.jdbc.user", "sa");
		properties.put("javax.persistence.jdbc.password", "sa");
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:~/compteDB");
		//le fichier META-INF/persistence.xml est pris en compte
		this.emf = Persistence.createEntityManagerFactory("appliJee-ejb", properties);
		//this.entityManager= emf.createEntityManager();
	}

	@Override
	public Compte createCompte(Compte cpt) {
		//en entrée: cpt nouveau (avec numéro inconnu à null)
		entityManager.persist(cpt); //auto_incr en base et .numero qui n'est plus null
		return cpt;//on retourne le compte avec numéro non null
	}

	@Override
	public Compte getCompteByNumero(Long numero) {
		this.entityManager= emf.createEntityManager();
		  Compte cpt = entityManager.find(Compte.class, numero);
		this.entityManager.close();
		   return cpt;
	}

	@Override
	public List<Compte> getComptesDuClient(Long numeroClient) {
		// sera codé plus tard @OneToMany , ...
		return null;
	}

	@Override
	public void updateCompte(Compte cpt) {
		try {
			this.entityManager= emf.createEntityManager();
			   entityManager.getTransaction().begin();
			   System.out.println("sans transaction explicite");
			   entityManager.merge(cpt);
			   entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			this.entityManager.close();
		}
	}

	@Override
	public void deleteCompte(Long numero) {
		Compte cptASupprimer = entityManager.find(Compte.class, numero);
        entityManager.remove(cptASupprimer);
	}

}
