package fr.m2i.formation.appliJee.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import fr.m2i.formation.appliJee.dao.IDaoCompte;
import fr.m2i.formation.appliJee.entity.Compte;

/*
 * impl�mentation du service sous forme d'EJB
 */

// NB: jboss doit �tre param�tr� avec l'option -b 0.0.0.0 
// pour accepter des requ�tes provenant d'autres ordinateurs
// ex: 172.28.11.24 plut�t que localhost

@Stateless //EJB Session sans �tat (EJB de traitement)
@Local //acc�s local possible (depuis autre EJB ou partie web)
//@TransactionManagement(TransactionManagementType.CONTAINER) par d�faut sur EJB
//@TransactionAttribute(TransactionAttributeType.REQUIRED)par d�faut sur EJB
@WebService(endpointInterface="fr.m2i.formation.appliJee.service.IServiceCompte")
public class ServiceCompteImpl implements IServiceCompte {
	
	@EJB //@EJB permet de demander une initialisation de "daoCompte"
	//en demandant � ce que �a r�f�rence un EJB existant compatible
	//avec l'interface 	IDaoCompte .
	//c'est une sorte d'injection de d�pendance (equivalent � @Autowired de Spring
	//ou bien @Inject de CDI)
	private IDaoCompte daoCompte;

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		// on d�l�gue au dao:
		return daoCompte.getCompteByNumero(numero);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		//en d�but de m�thode , cr�ation automatique entityManager et Transaction
		Compte cptDeb = daoCompte.getCompteByNumero(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde()-montant);
		daoCompte.updateCompte(cptDeb);
		
		Compte cptCred = daoCompte.getCompteByNumero(numCptCred);
		cptCred.setSolde(cptCred.getSolde()+montant);
		daoCompte.updateCompte(cptCred);
		//en fin de m�thode , commit automatique si aucune exeception
		//               ou rollback automatique si execption remont�e
	}

	@Override
	public void saveOrUpdateCompte(Compte compte) {
		if(compte.getNumero()==null) {
			daoCompte.createCompte(compte);//INSERT INTO , via .persist()
		}
		else {
			daoCompte.updateCompte(compte);//UPDATE , via .merge()
		}
	}

	@Override
	public void ajouterCompte(Compte nouveauCompte) {
		daoCompte.createCompte(nouveauCompte);
	}

}
