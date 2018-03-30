package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao{
	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;

	


	@Override
	public List<Produit> getAllProduitsDao(Categorie cat) {
	
		
		String req1 = "SELECT prod FROM Produit as prod WHERE prod.categorie.id=:pId ";

		// creation d'un objet query pour envoyer la requette jpql
		Query query1 = em.createQuery(req1);
		query1.setParameter("pId", cat.getIdCategorie());

		// envoyer la requte et recup la resultat
		return query1.getResultList();
		
		
	}

}
