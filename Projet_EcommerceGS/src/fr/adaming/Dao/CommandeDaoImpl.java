package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {
	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;

	@Override
	public List<Commande> getAllCommandesDao(Client cl) {
		String req = "SELECT com FROM Commande as com where com.client.id=:pId";
		// creation d'un objet query pour envoyer la requette jpql
		Query query = em.createQuery(req);
		query.setParameter("pId",cl.getIdClient());

		// envoyer la requte et recup la resultat
		return query.getResultList();


	}

	@Override
	public Commande addCommande(Commande com) {
		em.persist(com);
		return com;
	}

	@Override
	public int deleteCommande(Commande com) {
		String req = "DELETE FROM Commande com WHERE com.id=:pIdCom ";
		Query query = em.createQuery(req);
		
		// passage de params
		query.setParameter("pIdCom", com.getIdCommande());
		
		
		int verif=query.executeUpdate();
		return verif;

	}

}
