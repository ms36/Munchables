package com.skillstorm.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Recipe;
import com.skillstorm.repository.RecipeRepository;

@Service
public class RecipeService {
	
	private static final Logger log = Logger.getLogger(RecipeService.class);
	
	@Autowired
	RecipeRepository recipeRepository;

	public List<Recipe> findAll() {
		log.info("findAllRecipes");
		
		return recipeRepository.findAll();
	}
	
	public Recipe findById(int id) {
		log.info("find recipe with id: " + id);
		
		Optional<Recipe> recipe = recipeRepository.findById(id);
		
		if(recipe.isPresent()) {
			return recipe.get();
		} else {
			return null;
		}
	}
	
	public Recipe saveRecipe(Recipe recipe) {
		log.info("\"" + recipe.getName() + "\"" + " recipe saved");
		
		return recipeRepository.save(recipe);
	}
	
	public void deleteRecipe(int recipeId) {
		log.info("deleteRecipe with id: " + recipeId);
		
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isPresent()) {
			recipeRepository.delete(recipe.get());
		}
	}
}
