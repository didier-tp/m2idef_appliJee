package fr.m2i.formation.appliJee.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* Compte bancaire */
@Entity //Entité de données persitante en base (alias EJB Entité)
@NamedQueries({
  @NamedQuery(name="Compte.findWithOperations",
              query="SELECT c FROM Compte c JOIN FETCH c.operations WHERE c.numero = :numCpt")
})
public class Compte {
	
	@Id	//@Id = identifiant (clef primaire)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    //pour auto_incr en base et valeur qui remonte dans l'objet java
	private Long numero;
	
	@Column(length=32)  //VARCHAR(32) dans table générée
	private String label;
	
	private Double solde;
	
	@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)//EAGER temporairement en V1 , LAZY en V2
	//@JsonIgnore
	private List<Operation> operations; //relation inverse (FALCULTATIVE) 
	                                    // vis à vis de @ManyToOne au dessus de compte dans Operation
	
	@ManyToMany(mappedBy="comptes",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Client> clients; //avec get/set
	
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

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	

}
