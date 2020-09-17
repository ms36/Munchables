package com.skillstorm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Recipe;
import com.skillstorm.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;

	public Recipe findById(int id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		
		if(recipe.isPresent()) {
			return recipe.get();
		} else {
			return null;
		}
		
	}
}
