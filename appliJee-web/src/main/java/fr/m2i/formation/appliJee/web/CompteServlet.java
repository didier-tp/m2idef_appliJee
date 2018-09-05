package fr.m2i.formation.appliJee.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.formation.appliJee.entity.Compte;
import fr.m2i.formation.appliJee.service.IServiceCompte;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet(name="CompteServlet", urlPatterns="/CompteServlet")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB //pour initialiser serviceCompte en reférençant un EJB existant
	//compatible avec IServiceCompte .
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
		}
		out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
