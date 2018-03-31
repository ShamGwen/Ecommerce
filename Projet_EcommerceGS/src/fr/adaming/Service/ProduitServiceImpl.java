package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.primefaces.event.RowEditEvent;

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

	@Override
	public List<Produit> getAllProduitsService(Categorie cat) {

		return prodDao.getAllProduitsDao(cat);
	}

	@Override
	public Produit ajouterProduitService(Produit prod,Categorie cat) {
		System.out.println(cat.getIdCategorie());
		Categorie catOut=catDao.getCategorieById(cat);
		
		prod.setCategorie(catOut);
		return prodDao.ajouterProduitDao(prod);
	}

	@Override
	public int deleteProduitService(Produit prod) {
		
		return prodDao.deleteProduitDao(prod);
	}

//	@Override
//	public Produit rechercherProduitService(Produit prod) {
//		// rechercher produit
//		Produit prodOut = prodDao.rechercherProduitDao(prod);
//		//if (prodOut.getCategorie().getIdCategorie() == prod.getIdCategorie()) {
//
//			//return prodOut;
//		//} else {
//			return null;
//		}
//
//	}

	@Override
	public int updateProduitService(Produit prod,Categorie cat) {
		System.out.println(cat.getIdCategorie());
		Categorie catOUT=catDao.getCategorieById(cat);
        prod.setCategorie(catOUT);
		return prodDao.updateProduitDao(prod);

	}

	@Override
	public List<Produit> getAllProduitsService() {

		return prodDao.getAllProduitsDao();
	}

	@Override
	public Produit rechercherProduitService(Produit prod) {
		// TODO Auto-generated method stub
		return null;
	}
}