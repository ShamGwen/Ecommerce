package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;
	
	
	@Override
	public List<Categorie> getAllCategoriesDao() {
		// requete JPQL
		String req = "SELECT cat FROM Categorie cat";
		// creation d'un objet de type query pour envoyer la requete de type JPQL
		Query query = em.createQuery(req);
		// recuperation du resultat
		List<Categorie> listeOut = query.getResultList();
		return listeOut;
		
	}


	@Override
	public Categorie addCategorieDao(Categorie cat) {
		em.persist(cat);
		return cat;
	}


	

}
