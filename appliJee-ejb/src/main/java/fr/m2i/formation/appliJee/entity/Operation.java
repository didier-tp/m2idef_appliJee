package fr.m2i.formation.appliJee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Operation effectuée sur un compte bancaire
 */

@Entity
public class Operation {
	
	@Id //pk
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numOp;
	
	private String label;
	
	private Double montant;
	
	@Temporal(TemporalType.DATE)
	private Date dateOp;
	
	@ManyToOne
	@JoinColumn(name="compte") //fk
	@JsonIgnore //de la technologie jackson (utilisé en interne par JAX-RS pour transformer Java en Json ).
	//@JsonIgnore signifie "Ne pas suivre le lien vers le sous objet compte"
	//quand un objet operation java sera transformé en Json
	//cela permet d'éviter des boucles infinies ou des "LazyException"
	private Compte compte;
	

	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

	public Operation() {
		super();
	}

	public Long getNumOp() {
		return numOp;
	}

	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	
	
}
