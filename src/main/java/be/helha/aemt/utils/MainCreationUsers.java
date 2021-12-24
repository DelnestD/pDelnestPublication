package be.helha.aemt.utils;

import be.helha.aemt.DAO.UtilisateurDAO;
import be.helha.aemt.entities.Utilisateur;

public class MainCreationUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			//creation Admin
		
			Utilisateur utilisateurAdmin = new Utilisateur("ADMIN","HELHA","ADMIN");
			System.out.println(utilisateurAdmin.toString());
			utilisateurDAO.add(utilisateurAdmin);
		
			//Creation User
			Utilisateur utilisateurUser = new Utilisateur("USER","HELHA","USER");
			utilisateurDAO.add(utilisateurUser);
		
			System.out.println(utilisateurDAO.findAll());
			
			UtilisateurDAO.close();
	}

}