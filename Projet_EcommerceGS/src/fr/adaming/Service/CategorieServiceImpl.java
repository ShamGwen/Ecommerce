package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.Dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{

	//transformation de l'association UML en Java
	@EJB
	private ICategorieDao catDao;
	
	@Override
	public List<Categorie> getAllCategoriesService() {
		
		return catDao.getAllCategoriesDao();
	}

	@Override
	public Categorie addCategorieService(Categorie cat) {
		
		return catDao.addCategorieDao(cat);
	}

	@Override
	public int updateCategorieService(Categorie cat) {
		
		
		return catDao.updateCategorieDao(cat);
	}

	@Override
	public int deleteCategorieService(Categorie cat) {
				
		return catDao.deleteCategorieDao(cat);
	}

	@Override
	public Categorie getCategorieByIdService(Categorie cat) {
		
		return catDao.getCategorieById(cat);
	}

}
