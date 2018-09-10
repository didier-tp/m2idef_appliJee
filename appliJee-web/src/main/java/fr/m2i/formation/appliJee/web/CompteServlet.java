package fr.m2i.formation.appliJee.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.formation.appliJee.entity.Compte;
import fr.m2i.formation.appliJee.entity.Operation;
import fr.m2i.formation.appliJee.service.IServiceCompte;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet(name="CompteServlet", urlPatterns="/CompteServlet")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	//@EJB //pour initialiser serviceCompte en reférençant un EJB existant
	//compatible avec IServiceCompte .
	@Inject
	private IServiceCompte serviceCompte;
   
    public CompteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		if(task.equals("virement")) {
			long numCptDeb=Long.parseLong(request.getParameter("numCptDeb"));
			long numCptCred=Long.parseLong(request.getParameter("numCptCred"));
			double montant=Double.parseDouble(request.getParameter("montant"));
			try {
				serviceCompte.transferer(montant, numCptDeb, numCptCred);
				out.println("virement bien effectué <br/>");
			} catch (Exception e) {
				out.println("echec virement <br/>");
				//e.printStackTrace();
			}
		}
		else if(task.equals("rechercherCompte")) {
			String numeroCompteAsString = request.getParameter("numeroCompte");
			long numeroCompte = Long.parseLong(numeroCompteAsString);
			Compte compte = serviceCompte.rechercherCompteParNumero(numeroCompte);
			out.println("label="+compte.getLabel()+"<br/>");
			out.println("solde="+compte.getSolde()+"<br/>");
		}else if(task.equals("testerCompte")) {
			this.testerCompte(out);
		}
		out.println("</body></html>");
	}
	
	private void testerCompte(PrintWriter out) {
		out.println("tester Compte / CRUD <br/>"); //aujourd'hui via servlet utilisant EJB
		//d'autre fois dans test JUnit utilisant Service Spring
		//Astuce : pour tester Service et DAO , la sequence suivante:
		//Ajout en base d'une nouvelle entité
		Compte nouveauCompte = new Compte(null,"nouveau compte",200.0);
		this.serviceCompte.ajouterCompte(nouveauCompte);
		Long numCpt = nouveauCompte.getNumero();
		out.println("numero compte ajouté = " + numCpt + "<br/>");
		//relecture pour vérifier l'ajout
		Compte compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		out.println("compte ajouté et relu depuis DB=" + compteRelu + "<br/>");
		//Modif en mémoire et en base
		nouveauCompte.setSolde(400.0); nouveauCompte.setLabel("compte modifié");
		this.serviceCompte.mettreAjourCompte(nouveauCompte);
		//Relecture pour verifier la mise à jour
		compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		out.println("compte ajouté et relu depuis DB=" + compteRelu + "<br/>");
		//Suppression en base et verification
		this.serviceCompte.supprimerCompte(numCpt);
		compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		if(compteRelu==null)
			out.println("compte bien supprimé <br/>");
		
		Compte cpt1 = this.serviceCompte.rechercherCompteAvecOperationsParNumero(1L);
		//avec EAGER ou LAZY sur le @OneToMany entre Compte et Operation:
		for(Operation op : cpt1.getOperations()) {
			out.println("  operation du compte 1:" + op +"<br/>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
