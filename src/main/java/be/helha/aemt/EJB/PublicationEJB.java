package be.helha.aemt.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import be.helha.aemt.DAO.PublicationDAO;
import be.helha.aemt.entities.Publication;

@Stateless
@LocalBean
@Remote
public class PublicationEJB{

	@EJB
	private PublicationDAO publicationDAO;
	
	public Publication find(Integer id) {
		// TODO Auto-generated method stub
		return publicationDAO.find(id);
	}

	public List<Publication> findAll() {
		// TODO Auto-generated method stub
		return publicationDAO.findAll();
	}

	public Publication add(Publication p) {
		// TODO Auto-generated method stub
		return publicationDAO.add(p);
	}

	public Publication update(Publication p1, Publication p2) {
		// TODO Auto-generated method stub
		return publicationDAO.update(p1, p2);
	}

	public Publication delete(Publication p) {
		// TODO Auto-generated method stub
		return publicationDAO.delete(p);
	}

}
