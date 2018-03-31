package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {
	@PersistenceContext(unitName = "Projet_EcommerceGS")
	private EntityManager em;

	@Override
	public List<Produit> getAllProduitsDao(Categorie cat) {

		String req1 = "SELECT prod FROM Produit as prod WHERE prod.categorie.id=:pId ";

		// creation d'un objet query pour envoyer la requette jpql
		Query query1 = em.createQuery(req1);
		query1.setParameter("pId", cat.getIdCategorie());

		// envoyer la requte et recup la resultat
		List<Produit> listeOut = query1.getResultList();
		for (Produit prod : listeOut) {
			prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));

		}
		return listeOut;

	}

	@Override
	public Produit ajouterProduitDao(Produit prod) {
		em.persist(prod);
		return prod;
	}

	@Override
	public int updateProduitDao(Produit prod) {
		
		
		String req = "UPDATE Produit prod set prod.description=:pDescription,prod.designation=:pDesignation,prod.prix=:pPrix,prod.photo=:pPhoto,prod.quantite=:pQuantite WHERE prod.id=:pId and prod.categorie.id=:pIdCategorie";
		Query query = em.createQuery(req);
		
		query.setParameter("pDescription", prod.getDescription());
		query.setParameter("pDesignation", prod.getDesignation());
		query.setParameter("pPrix", prod.getPrix());
		query.setParameter("pPhoto", prod.getPhoto());
		query.setParameter("pId", prod.getIdProduit());
		query.setParameter("pIdCategorie", prod.getCategorie().getIdCategorie());
		query.setParameter("pQuantite", prod.getQuantite());
		int verif = query.executeUpdate();
		return verif;
		
		
//		prodOut.setDescription(prod.getDescription());
//		prodOut.setDesignation(prod.getDesignation());
//		prodOut.setPrix(prod.getPrix());
//		prodOut.setPhoto(prod.getPhoto());
//		prodOut.setQuantite(prod.getQuantite());
//		prodOut.setSelectionne(prod.isSelectionne());

		//return prodOut;
	}

	@Override
	public int deleteProduitDao(Produit prod) {

		String req = "DELETE from Produit prod WHERE prod.idProduit=:pId";
		Query q = em.createQuery(req);
		q.setParameter("pId", prod.getIdProduit());
		return q.executeUpdate();
	}

	@Override
	public Produit rechercherProduitDao(Produit prod) {

		return em.find(Produit.class, prod.getIdProduit());
	}

	@Override
	public List<Produit> getAllProduitsDao() {
		// requete JPQL
		String req = "SELECT prod FROM Produit prod";
		// creation d'un objet de type query pour envoyer la requete de type
		// JPQL
		Query query = em.createQuery(req);
		// recuperation du resultat
		List<Produit> listeOut = query.getResultList();
		// chargement de la photo
		for (Produit prod : listeOut) {
			prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));

		}
		return listeOut;

	}

}
