package fr.m2i.formation.appliJee.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * implementation du DAO en s'appuyant sur la technologie 
 * JPA = Java Persistence Api (entityManager)
 */

import fr.m2i.formation.appliJee.entity.Compte;

@Stateless //EJB Session sans �tat (EJB de traitement)
@Local //acc�s local possible (depuis autre EJB ou partie web)
@TransactionManagement(TransactionManagementType.CONTAINER) //par defaut sur EJB , transactions automatiques
//@TransactionManagement(TransactionManagementType.BEAN) //transaction � coder nous m�me
public class DaoCompteJpa implements IDaoCompte {
	
	@PersistenceContext(unitName="appliJee-ejb") //initialise entityManager 
	//via META-INF/persistence.xml
	private EntityManager entityManager; //objet principal de la techno JPA
	/*
	private EntityManagerFactory emf;
	
	@PostConstruct()
	public void init() {
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		//le provider JPA ici Hibernate correspond � la technologie d'impl�mentation (code concret)
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("javax.persistence.jdbc.user", "sa");
		properties.put("javax.persistence.jdbc.password", "sa");
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:~/compteDB;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE");
		//le fichier META-INF/persistence.xml est pris en compte
		this.emf = Persistence.createEntityManagerFactory("appliJee-ejb", properties);
	}
	*/

	@Override
	public Compte createCompte(Compte cpt) {
		//en entr�e: cpt nouveau (avec num�ro inconnu � null)
		entityManager.persist(cpt); //auto_incr en base et .numero qui n'est plus null
		return cpt;//on retourne le compte avec num�ro non null
	}

	/*
	@Override
	public Compte getCompteByNumero(Long numero) {
		this.entityManager= emf.createEntityManager();
		  Compte cpt = entityManager.find(Compte.class, numero);
		this.entityManager.close();
		   return cpt;
	}*/
	
	
	

	@Override
	public List<Compte> getComptesDuClient(Long numeroClient) {
		// sera cod� plus tard @OneToMany , ...
		return null;
	}

	/*
	@Override
	public void updateCompte(Compte cpt) {
		try {
			this.entityManager= emf.createEntityManager();
			   entityManager.getTransaction().begin();
			   System.out.println("avec transaction explicite");
			   entityManager.merge(cpt);
			   entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			this.entityManager.close();
		}
	}*/
	
	@Override
	public void updateCompte(Compte cpt) {
		entityManager.merge(cpt);
	}
	

	@Override
	public void deleteCompte(Long numero) {
		Compte cptASupprimer = entityManager.find(Compte.class, numero);
        entityManager.remove(cptASupprimer);
	}
	
	@Override
	public Compte getCompteByNumero(Long numero) {
		return entityManager.find(Compte.class, numero);
	}

	@Override
	public Compte getCompteWithOperationsByNumber(long numCpt) {
		return entityManager.createQuery(
				"SELECT c FROM Compte c JOIN FETCH c.operations o WHERE c.numero = :numCpt",
				Compte.class).setParameter("numCpt",numCpt).getSingleResult();
	}

}
