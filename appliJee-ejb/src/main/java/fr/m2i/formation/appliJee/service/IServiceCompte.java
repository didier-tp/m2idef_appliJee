package fr.m2i.formation.appliJee.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.m2i.formation.appliJee.entity.Compte;

/*
 * Service métier codé sous forme d'EJB
 * responsabilité du service:
 *     - transaction (commit/rollback)
 *     - regle de gestion (ex: verifierPosibilitéDeDecouvert )
 *     - traitement spécifique au métier (banque , assurance, ...)
 *     
 *     tout le reste est délégué au DAO .
 */

@WebService //pour permettre appel WS SOAP
//@WebParam pour que les noms des paramètres soient corrects dans la description
//WSDL générée
public interface IServiceCompte {
       public Compte rechercherCompteParNumero(@WebParam(name="numero")long numero);
       public void transferer(@WebParam(name="montant") double montant,
    		                  @WebParam(name="numCptDeb")long numCptDeb,
    		                  @WebParam(name="numCptCred")long numCptCred);//virement
       //...
	   public void saveOrUpdateCompte(Compte compte);
}
