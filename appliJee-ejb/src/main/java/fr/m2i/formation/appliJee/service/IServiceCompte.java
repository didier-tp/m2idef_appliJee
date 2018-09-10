package fr.m2i.formation.appliJee.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.m2i.formation.appliJee.entity.Compte;

/*
 * Service m�tier cod� sous forme d'EJB
 * responsabilit� du service:
 *     - transaction (commit/rollback)
 *     - regle de gestion (ex: verifierPosibilit�DeDecouvert )
 *     - traitement sp�cifique au m�tier (banque , assurance, ...)
 *     
 *     tout le reste est d�l�gu� au DAO .
 */

@WebService //pour permettre appel WS SOAP
//@WebParam pour que les noms des param�tres soient corrects dans la description
//WSDL g�n�r�e
public interface IServiceCompte {
       public Compte rechercherCompteParNumero(@WebParam(name="numero")long numero);
       public void transferer(@WebParam(name="montant") double montant,
    		                  @WebParam(name="numCptDeb")long numCptDeb,
    		                  @WebParam(name="numCptCred")long numCptCred);//virement
       //...
	   public void saveOrUpdateCompte(Compte compte);
	   public void ajouterCompte(Compte nouveauCompte);
	   public void mettreAjourCompte(Compte compte);
	   public void supprimerCompte(Long numCpt);
	   public Compte rechercherCompteAvecOperationsParNumero(long l);
	   public List<Compte> rechercherComptesDuClient(long numeroClient);
}
