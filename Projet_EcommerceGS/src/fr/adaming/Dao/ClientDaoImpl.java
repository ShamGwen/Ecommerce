package fr.adaming.Dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {
	@PersistenceContext(unitName="Projet_EcommerceGS")
	private EntityManager em;

	@Override
	public Client isExist(Client cl) {
		// la requete jpql
		String req = "SELECT cl from Client as cl WHERE cl.nomClient=:pNom AND cl.adresse=:pAdresse AND cl.tel=:pTel AND cl.email=:pEmail";
		// creer un objet de type Query pour envoyer la requete *
		Query query = em.createQuery(req);

		// passage de la params
		query.setParameter("pNom", cl.getNomClient());
		query.setParameter("pAdresse", cl.getAdresse());
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pTel", cl.getTel());
		// envoyer la requte et recup du resultat
		return (Client) query.getSingleResult();
		
//		return em.find(Client.class, cl.getIdClient());

	}

	@Override
	public Client ajouterClientDao(Client cl) {
		em.persist(cl);
		//em.refresh(cl);
		return cl;
	}
}
