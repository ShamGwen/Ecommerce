package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieDao {

	public List<Categorie> getAllCategoriesDao();
	
	public Categorie addCategorieDao(Categorie cat);
	
	public int updateCategorieDao(Categorie cat);
	
	public int deleteCategorieDao(Categorie cat);
	
	public Categorie getCategorieById(Categorie cat);
	
}
