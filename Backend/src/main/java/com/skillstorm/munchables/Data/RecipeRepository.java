package com.skillstorm.munchables.Data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.skillstorm.munchables.RecipeController.RecipeController;
import com.skillstorm.munchables.beans.Ingredients;
import com.skillstorm.munchables.beans.Measurements;
import com.skillstorm.munchables.beans.Recipe;
import com.skillstorm.munchables.beans.RecipeIngredients;
import com.skillstorm.munchables.beans.Steps;

@Repository
public class RecipeRepository {

private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

//adding Recipes, Ingredients, measurements, and Steps
public static final Logger log = Logger.getLogger(RecipeRepository.class);

	public void insert(Recipe recipe ) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(recipe);
		tx.commit();
		session.close();
	}
	
	public void insert(Ingredients ingredient ) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(ingredient);
		tx.commit();
		session.close();
	}
	
	public void insert(Steps step ) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(step);
		tx.commit();
		session.close();
	}
	
	public void insert(Measurements measurements) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(measurements);
		tx.commit();
		session.close();
	}
	
	//Get Recipe and Ingredients Id
	
	public Recipe findByIdRecipe(int id) {
		Session session = sessionFactory.openSession();
		return (Recipe) session.get(Recipe.class, id);
	}
	
	public Ingredients findByIdIngredient(int id) {
		Session session = sessionFactory.openSession();
		return (Ingredients) session.get(Ingredients.class, id);
	}
	
	public Steps findByIdSteps(int id) {
		Session session = sessionFactory.openSession();
		return (Steps) session.get(Steps.class, id);
	}
	
	public Measurements findByIdMeasurement(int id) {
		Session session = sessionFactory.openSession();
		return (Measurements) session.get(Measurements.class, id);
	}
	
	//Find all Recipe, Measurements, Steps, and Ingredients
	
	public List<Recipe> findAllRecipe(){
		log.info("findAllRecipe");
		return sessionFactory.openSession().createQuery("from Recipe").list();
		
	}
	
	public List<RecipeIngredients> findAllRecipeIngredients(){
		log.info("RecipeIngredients");
		return sessionFactory.openSession().createQuery("from RecipeIngredients").list();
		
	}
	
	public List<Ingredients> findAllIngredients(){		
		log.info("findAllIngredients");
		return sessionFactory.openSession().createQuery("from Ingredients").list();
		
	}
	
	public List<Measurements> findAllMeasurements(){
		return sessionFactory.openSession().createQuery("from Measurements").list();
		
	}
	
	public List<Steps> findAllSteps(){
		return sessionFactory.openSession().createQuery("select s from Steps s inner join s.recipe").list();
		
	}
	
	// Save Recipe, RecipeIngredients, Steps, and Ingredients
	
	public void save(Ingredients ingredient, int ingredientId, String name ) {
		Session session = sessionFactory.openSession();
		Ingredients ingredredient = new Ingredients();
		ingredient.setIngredientId(ingredientId);
		ingredient.setName(name);
		session.update(ingredredient);
		session.close();
		
		
	}
	
	// Delete Recipe, RecipeIngredients, Steps, and Ingredients
	
	public void delete(Ingredients ingredient) {
		
	}
	}
	
