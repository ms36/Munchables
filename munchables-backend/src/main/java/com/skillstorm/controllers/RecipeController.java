package com.skillstorm.controllers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.Recipe;
import com.skillstorm.services.RecipeService;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RecipeController {
	
	private static final Logger log = Logger.getLogger(RecipeController.class);

	@Autowired
	RecipeService recipeService;
	
	@GetMapping
	public ResponseEntity<List<Recipe>> getAllRecipes() {
		log.info("getAllRecipes");
		
		List<Recipe> recipe = recipeService.findAll();
		
		return new ResponseEntity<>(recipe, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable int id) {
		
		Recipe recipe = recipeService.findById(id);
		
		if (recipe != null) {
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
}
