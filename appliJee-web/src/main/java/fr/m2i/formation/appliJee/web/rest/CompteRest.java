package fr.m2i.formation.appliJee.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import fr.m2i.formation.appliJee.entity.Compte;
import fr.m2i.formation.appliJee.service.IServiceCompte;

/*
 * classe java du WS REST sur les Comptes bancaires
 */

@Path("service/compte")
@Produces("application/json") //en retour Java --> JSON
@Consumes("application/json") //en entree (POST) JSON --> Java
public class CompteRest {
	
	//le ancien @EJB n'est pas interpr�t� ici dans la techno r�cente JAX-RS
	@Inject //@Inject est une annonation un peu plus r�cente de CDI/JEE6
	private IServiceCompte serviceCompte; //ejb vers lequel faire des appels
	
	@GET
	@Path("/{num}")
	//@JwtTokenNeeded
	// URL= http://localhost:8080/appliJee-web/rest/service/compte/2
	public Compte getCompteByNum(@PathParam("num") Long num) {
		//v1 sans lien avec EJB
		//return new Compte(num , "compte " + num , 50.0);
		//v2 avec EJB:
		return serviceCompte.rechercherCompteParNumero(num);
	}
	
	@POST
	@Path("")
	// URL= http://localhost:8080/appliJee-web/rest/service/compte appel� en method=POST
	// avec { "numero":3 ou null , "label": "compte xy" , "solde" : 50.0 } dans le corps invisible de la requete
	public Compte postCompte(Compte compte) {
		serviceCompte.saveOrUpdateCompte(compte);
		return compte;//en retour , copie du compte sauvegard� avec clef primaire quelquefois auto_incr
	}
	
	
	@GET
	@Path("")
	// URL= http://localhost:8080/appliJee-web/rest/service/compte?numMax=2
	public List<Compte> getComptesByNumMax(@QueryParam("numMax") Long numMax) {
		//v1 sans lien avec EJB
		List<Compte> listeComptes = new ArrayList<Compte>();
		  if(numMax>=1) listeComptes.add(new Compte(1L , "compte 1"  , 50.0));
		  if(numMax>=2) listeComptes.add(new Compte(2L , "compte 2"  , 80.0));
		return listeComptes;
	}

}
