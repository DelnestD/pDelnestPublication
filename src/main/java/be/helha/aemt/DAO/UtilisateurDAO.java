package be.helha.aemt.DAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Utilisateur;

public class UtilisateurDAO {
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pDelnestPublication");
	protected static EntityManager em = emf.createEntityManager();
	protected static EntityTransaction tr = em.getTransaction();
	
	public static void close() { //RESOURCE LOCALE
		em.close();
		emf.close();
	}
	
	private void submit() {
		tr.begin();
		tr.commit();
	}
	
	public Utilisateur find(Integer id) {
		if(id == null) {
			return null;
		}
		Utilisateur found = em.find(Utilisateur.class, id);
		em.detach(found);
		return found;
	}

	public List<Utilisateur> findAll() {
		String sFind = "SELECT u FROM Utilisateur u";
		
		Query query = em.createQuery(sFind);
		List<Utilisateur> utilisateurList = query.getResultList();
		
		return utilisateurList.isEmpty() ? null : utilisateurList;
	}

	public Utilisateur add(Utilisateur t) {
		if(t==null) {
			return null;
		}
		em.persist(t);
		this.submit();
		em.detach(t);
		return t;
	}

	public Utilisateur update(Utilisateur t1, Utilisateur t2) {
		if(t1 == null || t1.getId() == null || t2==null) {
			return null;
		}
		
		Utilisateur found = find(t1.getId());
		
		if(found == null) {
			return null;
		}
		
		found.setLogin(t2.getLogin());
		found.setPassword(t2.getPassword());
		found.setRole(t2.getRole());
		em.merge(found);
		this.submit();
		em.detach(found);
		return null;
	}

	public Utilisateur delete(Utilisateur t) {
		if(t==null) {
			return null;
		}
		
		if(this.find(t.getId()) == null) {
			return null;
		}

		em.remove(t);
		this.submit();
		em.detach(t);
		
		return t;
	}
}
