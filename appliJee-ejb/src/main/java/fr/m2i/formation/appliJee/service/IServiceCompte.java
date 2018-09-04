package fr.m2i.formation.appliJee.service;

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

public interface IServiceCompte {
       public Compte rechercherCompteParNumero(long numero);
       public void transferer(double montant,long numCptDeb,long numCptCred);//virement
       //...
}
