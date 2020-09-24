package com.skillstorm.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.beans.Ingredient;
import com.skillstorm.beans.Recipe;
import com.skillstorm.repository.IngredientRepository;
import com.skillstorm.repository.RecipeRepository;

@Service
public class IngredientService {
	
private static final Logger log = Logger.getLogger(IngredientService.class);
	
	@Autowired
	IngredientRepository ingredientRepository;
	@Autowired
	RecipeRepository recipeRepository;
	
	public Ingredient findById(int ingedientId) {
		log.info("find ingredient with id: " + ingedientId);
		
		Optional<Ingredient> ingredient = ingredientRepository.findById(ingedientId);
		
		if(ingredient.isPresent()) {
			return ingredient.get();
		} else {
			return null;
		}
	}
	
	public List<Ingredient> findAllIngredientsFromRecipe(int recipeId) {
		log.info("findAllStepsFromRecipe");
		
		return ingredientRepository.findAllIngredientsFromRecipe(recipeId);
	}
	
	public Ingredient saveIngredientByRecipeId(Ingredient ingredient, int recipeId) {
		log.info(ingredient.getName() + "saved ToRecipe");
		
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isPresent()) {
			ingredient.setRecipe(recipe.get());
		} else {
			return null;
		}
		return ingredientRepository.save(ingredient);
	}
	
	public void deleteIngredient(int ingredientId) {
		log.info("deleteIngredient with id: " + ingredientId);
		
		Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
		if (ingredient.isPresent()) {
			ingredientRepository.delete(ingredient.get());
		}
	}
}
