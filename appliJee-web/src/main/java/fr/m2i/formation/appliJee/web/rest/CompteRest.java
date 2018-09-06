package fr.m2i.formation.appliJee.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.m2i.formation.appliJee.entity.Compte;

/*
 * classe java du WS REST sur les Comptes bancaires
 */

@Path("service/compte")
@Produces("application/json")
public class CompteRest {
	
	@GET
	@Path("/{num}")
	// URL= http://localhost:8080/appliJee-web/rest/service/compte/2
	public Compte getCompteByNum(@PathParam("num") Long num) {
		//v1 sans lien avec EJB
		return new Compte(num , "compte " + num , 50.0);
	}
	
	//...

}
