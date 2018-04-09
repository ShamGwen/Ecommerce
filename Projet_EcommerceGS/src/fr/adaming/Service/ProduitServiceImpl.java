package fr.adaming.Service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.RowEditEvent;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import fr.adaming.Dao.ICategorieDao;
import fr.adaming.Dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {
	// injecter produit Dao
	@EJB
	IProduitDao prodDao;

	@EJB
	ICategorieDao catDao;
	
	private EntityManager em;

	@Override
	public List<Produit> getAllProduitsService(Categorie cat) {

		return prodDao.getAllProduitsDao(cat);
	}

	@Override
	public Produit ajouterProduitService(Produit prod, Categorie cat) {
		System.out.println(cat.getIdCategorie());
		Categorie catOut = catDao.getCategorieById(cat);

		prod.setCategorie(catOut);
		return prodDao.ajouterProduitDao(prod);
	}

	@Override
	public int deleteProduitService(Produit prod) {

		return prodDao.deleteProduitDao(prod);
	}

	// @Override
	// public Produit rechercherProduitService(Produit prod) {
	// // rechercher produit
	// Produit prodOut = prodDao.rechercherProduitDao(prod);
	// //if (prodOut.getCategorie().getIdCategorie() == prod.getIdCategorie()) {
	//
	// //return prodOut;
	// //} else {
	// return null;
	// }
	//
	// }

	@Override
	public int updateProduitService(Produit prod, Categorie cat) {
		System.out.println(cat.getIdCategorie());
		Categorie catOUT = catDao.getCategorieById(cat);
		prod.setCategorie(catOUT);
		return prodDao.updateProduitDao(prod);

	}

	@Override
	public List<Produit> getAllProduitsService() {

		return prodDao.getAllProduitsDao();
	}

	@Override
	public Produit rechercherProduitService(Produit prod) {
		
		return prodDao.rechercherProduitDao(prod);
	}
	
	
	public List<Produit> getProduitsRechService(String motCle){
	
		// recuperer la liste de tous les produits
		List<Produit> listeProd = prodDao.getAllProduitsDao();
		// initialisation d'une liste de recuperation des produits dont la description contient le mot cle
		List<Produit> listeRech = new ArrayList<Produit>();
		for(Produit p : listeProd) {
			if (p.getDescription().toLowerCase().contains(motCle.toLowerCase())) {
				// si la description du produit contient le mot-cle on ajoute le produit dans la deuxieme liste
				listeRech.add(p);
			}
		}
		System.out.println("mot cle: "+motCle);
		System.out.println("Liste produits: ***********");
		listeProd.forEach(System.out::println);
		System.out.println("Liste recherches: ************");
		listeRech.forEach(System.out::println);
		
		return listeRech;
	
	}
}

	
		
