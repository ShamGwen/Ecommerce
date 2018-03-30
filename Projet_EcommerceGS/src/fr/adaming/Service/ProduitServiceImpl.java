package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.primefaces.event.RowEditEvent;

import fr.adaming.Dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {
	// injecter produit Dao
	@EJB
	IProduitDao prodDao;

	@Override
	public List<Produit> getAllProduitsService(Categorie cat) {

		return prodDao.getAllProduitsDao(cat);
	}

	@Override
	public Produit ajouterProduitService(Produit prod, Categorie cat) {
		prod.setCategorie(cat);
		return prodDao.ajouterProduitDao(prod);
	}

	//@Override
	//public Produit updateProduitService(Produit prod, Categorie cat) {
		 //rechercher le client
		//Produit prodIn = rechercherProduitService(prod, cat);
		//System.out.println(prodIn.getCategorie().getIdCategorie());
		//System.out.println(cat.getIdCategorie());
		//if (prodIn.getCategorie().getIdCategorie() == cat.getIdCategorie()) {
			//return prodDao.updateProduitDao(prod);

		//}
		//return null;
//}

	@Override
	public Produit deleteProduitService(Produit prod, Categorie cat) {
		// rechercher le produit

		Produit prodOut = prodDao.rechercherProduitDao(prod);
		if (prodOut.getCategorie().getIdCategorie() == cat.getIdCategorie()) {
			return prodDao.deleteProduitDao(prod);
		} else {
			return prodOut;
		}
	}

	@Override
	public Produit rechercherProduitService(Produit prod, Categorie cat) {
		// rechercher produit
		Produit prodOut = prodDao.rechercherProduitDao(prod);
		if (prodOut.getCategorie().getIdCategorie() == cat.getIdCategorie()) {

			return prodOut;
		} else {
			return null;
		}

	}

	@Override
	public Produit updateProduitService(Produit prod) {
	
		return prodDao.deleteProduitDao(prod);

}

	//@Override
	//public Produit updateProduitService(Produit prod, Categorie cat) {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Override
	public List<Produit> getAllProduitsService() {
	
		return prodDao.getAllProduitsDao();
	}

	@Override
	public Produit updateProduitService(Produit prod, Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	
}