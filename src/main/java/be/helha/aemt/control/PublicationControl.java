package be.helha.aemt.control;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.helha.aemt.EJB.PublicationEJB;
import be.helha.aemt.entities.Publication;

@Named
@RequestScoped 
public class PublicationControl {
	
	private String titre;
	private int annee;
	private double prix;
	
	private Publication myPublication;
	
	Context ctx;
	
	public List<Publication> getAllPublications() {
		try {
			ctx = new InitialContext();
			PublicationEJB bean =(PublicationEJB)
			ctx.lookup("java:global/pDelnestPublication/PublicationEJB");
			return bean.findAll();
		} catch (NamingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public String addPublication() {
		myPublication = new Publication(titre, annee, prix);
		try {
			ctx = new InitialContext();
			PublicationEJB bean =(PublicationEJB)
			ctx.lookup("java:global/pDelnestPublication/PublicationEJB");
			bean.add(myPublication);
		} catch (NamingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "index";
	}
	
	public void deletePublication(Publication p) {
		try {
			ctx = new InitialContext();
			PublicationEJB bean =(PublicationEJB)
			ctx.lookup("java:global/pDelnestPublication/PublicationEJB");
			bean.delete(p);
		} catch (NamingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String doListPublications() {
		return "listePublications?faces-redirect=true";
	}
	
	public String doAjoutPublication() {
		return "ajoutPublication?faces-redirect=true";
	}
	public String doIndex() {
		return "index";
	}


	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
