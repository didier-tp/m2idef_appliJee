package fr.m2i.formation.appliJee.service;

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

public interface IServiceCompte {
       public Compte rechercherCompteParNumero(long numero);
       public void transferer(double montant,long numCptDeb,long numCptCred);//virement
       //...
}
