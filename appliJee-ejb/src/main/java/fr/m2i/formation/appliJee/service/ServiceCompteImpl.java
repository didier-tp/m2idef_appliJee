package fr.m2i.formation.appliJee.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import fr.m2i.formation.appliJee.dao.IDaoCompte;
import fr.m2i.formation.appliJee.entity.Compte;

/*
 * implémentation du service sous forme d'EJB
 */

@Stateless //EJB Session sans état (EJB de traitement)
@Local //accès local possible (depuis autre EJB ou partie web)
//@TransactionManagement(TransactionManagementType.CONTAINER) par défaut sur EJB
//@TransactionAttribute(TransactionAttributeType.REQUIRED)par défaut sur EJB
public class ServiceCompteImpl implements IServiceCompte {
	
	@EJB //@EJB permet de demander une initialisation de "daoCompte"
	//en demandant à ce que ça référence un EJB existant compatible
	//avec l'interface 	IDaoCompte .
	//c'est une sorte d'injection de dépendance (equivalent à @Autowired de Spring
	//ou bien @Inject de CDI)
	private IDaoCompte daoCompte;

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		// on délègue au dao:
		return daoCompte.getCompteByNumero(numero);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		//en début de méthode , création automatique entityManager et Transaction
		Compte cptDeb = daoCompte.getCompteByNumero(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde()-montant);
		daoCompte.updateCompte(cptDeb);
		
		Compte cptCred = daoCompte.getCompteByNumero(numCptCred);
		cptCred.setSolde(cptCred.getSolde()+montant);
		daoCompte.updateCompte(cptCred);
		//en fin de méthode , commit automatique si aucune exeception
		//               ou rollback automatique si execption remontée
	}

}
