package fr.m2i.formation.appliJee.dao;

import java.util.List;

import fr.m2i.formation.appliJee.entity.Compte;

/*
 * DAO = Data Access Object avec 
 * methodes CRUD
 * avec throws RuntimeException implicites
 */
public interface IDaoCompte {
    public Compte createCompte(Compte cpt);//en retour le Compte avec  la clef primaire
                                           // auto incrémentée
    public Compte getCompteByNumero(Long numero);
    public List<Compte> getComptesDuClient(Long numeroClient);
    public void updateCompte(Compte cpt);
    public void deleteCompte(Long numero);
	public Compte getCompteWithOperationsByNumber(long numCpt);
}
