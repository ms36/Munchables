package com.skillstorm.munchables.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.munchables.Data.RecipeRepository;
import com.skillstorm.munchables.beans.Ingredients;
import com.skillstorm.munchables.beans.Measurements;
import com.skillstorm.munchables.beans.Recipe;
import com.skillstorm.munchables.beans.Steps;

@Service

public class RecipeService {

	@Autowired
	public RecipeRepository recipeRepository;

	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Recipe save(Recipe Recipe) {

		return Recipe;

	}

	public Ingredients save(Ingredients ingredient) {

		return ingredient;

	}
	
	public Measurements save(Measurements measurement) {

		return measurement;

	}
///test
	public Steps save(Steps step) {

		return step;

	}
}
