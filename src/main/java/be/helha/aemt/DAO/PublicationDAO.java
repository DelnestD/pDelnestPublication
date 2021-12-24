package be.helha.aemt.DAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Publication;

@Stateless
@LocalBean
public class PublicationDAO {
	
	@PersistenceContext(unitName = "pDelnestPublication")
	private EntityManager em;
	
	public Publication find(Integer id) {
		if(id == null) {
			return null;
		}
		Publication found = em.find(Publication.class, id);
		em.detach(found);
		return found;
	}

	public List<Publication> findAll() {
		String sFind = "SELECT p FROM Publication p";
		
		Query query = em.createQuery(sFind);
		List<Publication> publicationList = query.getResultList();
		
		return publicationList.isEmpty() ? null : publicationList;
	}

	public Publication add(Publication t) {
		if(t==null) {
			return null;
		}
		em.persist(t);
		return t;
	}

	public Publication update(Publication t1, Publication t2) {
		if(t1 == null || t1.getId() == null || t2==null) {
			return null;
		}
		
		Publication found = find(t1.getId());
		
		if(found == null) {
			return null;
		}
		
		found.setTitre(t2.getTitre());
		found.setPrix(t2.getPrix());
		found.setAnnee(t2.getAnnee());
		em.merge(found);
		return null;
	}

	public Publication delete(Publication t) {
		if(t==null) {
			return null;
		}
		
		if(this.find(t.getId()) == null) {
			return null;
		}

		em.remove(em.merge(t));
		return t;
	}
}
