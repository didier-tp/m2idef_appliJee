package fr.m2i.formation.appliJee.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.m2i.formation.appliJee.dao.IDaoCompte;
import fr.m2i.formation.appliJee.entity.Compte;

/*
 * impl�mentation du service sous forme d'EJB
 */

@Stateless //EJB Session sans �tat (EJB de traitement)
@Local //acc�s local possible (depuis autre EJB ou partie web)
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
		// ...

	}

}
