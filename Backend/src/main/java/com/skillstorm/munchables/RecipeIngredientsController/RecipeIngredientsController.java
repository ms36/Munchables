package com.skillstorm.munchables.RecipeIngredientsController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.munchables.Data.RecipeRepository;
import com.skillstorm.munchables.Service.RecipeService;
import com.skillstorm.munchables.beans.Ingredients;
import com.skillstorm.munchables.beans.RecipeIngredients;

@RestController
@RequestMapping(value = "/recipeIngredients")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeIngredientsController {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeService service;
	
	// Get requests
	@GetMapping(value = "/all")
	public ResponseEntity<List<RecipeIngredients>> findAllRecipeIngredients() {
		return new ResponseEntity<List<RecipeIngredients>>(recipeRepository.findAllRecipeIngredients(), HttpStatus.OK);
	}
}
