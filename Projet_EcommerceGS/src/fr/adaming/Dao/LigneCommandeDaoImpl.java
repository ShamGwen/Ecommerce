package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;
	
	
	
	
	@Override
	public List<LigneCommande> getAllLigneCommandesDao(Produit prod) {
		String req = "SELECT com FROM LigneCommande as lcom where lcom.produit.id=:pId";
		// creation d'un objet query pour envoyer la requette jpql
		Query query = em.createQuery(req);
		query.setParameter("pId",prod.getIdProduit());

		// envoyer la requte et recup la resultat
		return query.getResultList();
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lcom) {
		em.persist(lcom);
		return lcom;
	}

	@Override
	public int deleteLigneCommande(LigneCommande lcom) {
		String req = "DELETE FROM LigneCommande lcom WHERE lcom.id=:pId and lcom.produit.id=:pIdProd";
		Query query = em.createQuery(req);
		
		// passage de params
		query.setParameter("pId", lcom.getIdLC());
		query.setParameter("pIdProd",lcom.getProduit().getIdProduit());
		
		int verif=query.executeUpdate();
		return verif;
	}

}
