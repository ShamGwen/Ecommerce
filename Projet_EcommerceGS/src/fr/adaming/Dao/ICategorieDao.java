package fr.adaming.Dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {

	public List<Categorie> getAllCategoriesDao();
	
	public Categorie addCategorieDao(Categorie cat);
	
	public Categorie updateCategorieDao(Categorie cat);
	
	public int deleteCategorieDao(Categorie cat);
	
	public Categorie getCategorieById(Categorie cat);
	
}
