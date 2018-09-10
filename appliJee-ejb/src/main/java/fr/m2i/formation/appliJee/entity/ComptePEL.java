package fr.m2i.formation.appliJee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ComptePEL")//valeur de typeCompte pour les instances de cette classe
public class ComptePEL extends Compte {
	
	private Double tauxInteret;
	//...

	public Double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(Double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	@Override
	public String toString() {
		return "ComptePEL [tauxInteret=" + tauxInteret + ", toString()=" + super.toString() + "]";
	}
	
	

}
