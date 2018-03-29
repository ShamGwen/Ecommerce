package fr.adaming.Dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Administrateur;

@Stateless

public class AdminDaoImpl implements IAdminDao {
	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;

	@Override
	public Administrateur isExist(Administrateur admin) {

		return em.find(Administrateur.class, admin.getIdAdmin());
	}

}
