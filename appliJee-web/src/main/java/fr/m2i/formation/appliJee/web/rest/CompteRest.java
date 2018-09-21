package fr.m2i.formation.appliJee.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.mycontrib.generic.security.rest.jaxrs.JwtTokenNeeded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.m2i.formation.appliJee.entity.Compte;
import fr.m2i.formation.appliJee.service.IServiceCompte;

/*
 * classe java du WS REST sur les Comptes bancaires
 */

@Path("service/compte")
@Produces("application/json") // en retour Java --> JSON
@Consumes("application/json") // en entree (POST) JSON --> Java
public class CompteRest {

	// le ancien @EJB n'est pas interprété ici dans la techno récente JAX-RS
	@Inject // @Inject est une annotation un peu plus récente de CDI/JEE6
	private IServiceCompte serviceCompte; // ejb vers lequel faire des appels

	@Context
	SecurityContext securityContext;// not mandatory

	private static Logger logger = LoggerFactory.getLogger(CompteRest.class);

	@GET
	@Path("/{num}")
	@JwtTokenNeeded()
	// URL= http://localhost:8080/appliJee-web/rest/service/compte/2
	public Compte getCompteByNum(@PathParam("num") Long num) {

		// v1 sans lien avec EJB
		// return new Compte(num , "compte " + num , 50.0);
		// v2 avec EJB:
		// return serviceCompte.rechercherCompteParNumero(num);
		return serviceCompte.rechercherCompteAvecOperationsParNumero(num);
	}

	// a tester avec PostMan , avec Content-Type = application/json dans Headers
	// et Body en mode "raw"
	@POST
	@Path("")
	// URL= http://localhost:8080/appliJee-web/rest/service/compte appelé en
	// method=POST
	// avec { "numero": null ou 3 , "label": "compte xy" , "solde" : 50.0 }
	// dans le corps/body invisible de la requete en mode "raw"
	// et au format Content-Type=application/json
	@JwtTokenNeeded()
	public Compte postCompte(Compte compte) {
		serviceCompte.saveOrUpdateCompte(compte);
		return compte;// en retour , copie du compte sauvegardé avec clef primaire quelquefois
						// auto_incr
	}

	@GET
	@Path("")
	// URL= http://localhost:8080/appliJee-web/rest/service/compte?numMax=2
	// @JwtTokenNeeded({ "MEMBER" })
	@RolesAllowed({ "ADMIN", "MEMBER" }) // javax.annotation.security.RolesAllowed
	public List<Compte> getComptesByNumMax(@QueryParam("numMax") Long numMax) {
		if (securityContext.isUserInRole("MEMBER"))
			logger.info("MEMBER role in securityContext");

		// v1 sans lien avec EJB , v2 avec EJB
		List<Compte> listeComptes = new ArrayList<Compte>();
		// if(numMax>=1) listeComptes.add(new Compte(1L , "compte 1" , 50.0));
		// if(numMax>=2) listeComptes.add(new Compte(2L , "compte 2" , 80.0));
		for (long i = 1; i <= numMax; i++)
			listeComptes.add(serviceCompte.rechercherCompteAvecOperationsParNumero(i));
		// System.out.println("listeComptes:"+listeComptes);
		return listeComptes;
	}

}
