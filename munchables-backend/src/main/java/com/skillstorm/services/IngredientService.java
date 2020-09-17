package com.skillstorm.services;

import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.beans.Ingredient;
import com.skillstorm.repository.IngredientRepository;

@Service
public class IngredientService {
	
private static final Logger log = Logger.getLogger(IngredientService.class);
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	public List<Ingredient> findAllIngredientsFromRecipe(int recipeId) {
		log.info("findAllStepsFromRecipe");
		
		return ingredientRepository.findAllIngredientsFromRecipe(recipeId);
	}
}
