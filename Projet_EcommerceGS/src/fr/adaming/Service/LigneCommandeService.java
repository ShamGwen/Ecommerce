package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import fr.adaming.Dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeService implements ILigneCommandeService{
	@EJB
	ILigneCommandeDao lcomDao;

	@Override
	public List<LigneCommande> getAllLigneCommandesService(Produit prod) {
		
		return lcomDao.getAllLigneCommandesDao(prod);
	}

	@Override
	public LigneCommande addLigneCommandeService(LigneCommande lcom, Produit prod) {
		lcom.setProduit(prod);
		return lcomDao.addLigneCommande(lcom);
	}

	@Override
	public int deleteLigneCommandeService(LigneCommande lcom, Produit prod) {
		lcom.setProduit(prod);
		return lcomDao.deleteLigneCommande(lcom);
	}

	
	
	
	
	
}
