package fr.m2i.formation.appliJee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* Compte bancaire */
@Entity //Entité de données persitante en base (alias EJB Entité)
public class Compte {
	
	@Id	//@Id = identifiant (clef primaire)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    //pour auto_incr en base et valeur qui remonte dans l'objet java
	private Long numero;
	
	@Column(length=32)  //VARCHAR(32) dans table générée
	private String label;
	
	private Double solde;
	//+get/set , +constructeurs , +toString()

	public Compte() {
		super();
	}
	
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}

}
