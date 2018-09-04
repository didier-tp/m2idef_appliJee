package fr.m2i.formation.appliJee.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * implementation du DAO en s'appuyant sur la technologie 
 * JPA = Java Persistence Api (entityManager)
 */

import fr.m2i.formation.appliJee.entity.Compte;

@Stateless //EJB Session sans état (EJB de traitement)
@Local //accès local possible (depuis autre EJB ou partie web)
public class DaoCompteJpa implements IDaoCompte {
	
	@PersistenceContext(unitName="appliJee-ejb") //initialise entityManager 
	//via META-INF/persistence.xml
	private EntityManager entityManager; //objet principal de la techno JPA

	@Override
	public Compte createCompte(Compte cpt) {
		//en entrée: cpt nouveau (avec numéro inconnu à null)
		entityManager.persist(cpt); //auto_incr en base et .numero qui n'est plus null
		return cpt;//on retourne le compte avec numéro non null
	}

	@Override
	public Compte getCompteByNumero(Long numero) {
		return entityManager.find(Compte.class, numero);
	}

	@Override
	public List<Compte> getComptesDuClient(Long numeroClient) {
		// sera codé plus tard @OneToMany , ...
		return null;
	}

	@Override
	public void updateCompte(Compte cpt) {
		entityManager.merge(cpt);
	}

	@Override
	public void deleteCompte(Long numero) {
		Compte cptASupprimer = entityManager.find(Compte.class, numero);
        entityManager.remove(cptASupprimer);
	}

}
