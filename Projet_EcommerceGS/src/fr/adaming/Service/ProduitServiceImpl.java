package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

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

}
