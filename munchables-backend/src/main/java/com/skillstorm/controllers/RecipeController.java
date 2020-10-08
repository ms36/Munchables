package com.skillstorm.controllers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{recipeId}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable int recipeId) {
		log.info("getRecipeById");
		
		Recipe recipe = recipeService.findById(recipeId);
		
		if (recipe != null) {
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
		log.info("addNewRecipe");
		
		Recipe newRecipe = recipeService.saveRecipe(recipe);
		
		if (newRecipe == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newRecipe, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
		log.info("saveRecipe");
		
		Recipe newRecipe = recipeService.saveRecipe(recipe);
		
		if (newRecipe == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newRecipe, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{recipeId}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable int recipeId) {
		log.info("deleteRecipe");
		
		recipeService.deleteRecipe(recipeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
