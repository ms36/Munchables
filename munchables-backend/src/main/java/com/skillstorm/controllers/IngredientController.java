package com.skillstorm.controllers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.Ingredient;
import com.skillstorm.services.IngredientService;


@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class IngredientController {

private static final Logger log = Logger.getLogger(IngredientController.class);
	
	@Autowired
	IngredientService ingredientService;
	
	@GetMapping(value = "/{recipeId}")
	public ResponseEntity<List<Ingredient>> getAllIngredientsFromRecipe(@PathVariable int recipeId) {
		log.info("getAllIngredientsFromRecipe");
		
		List<Ingredient> ingredients = ingredientService.findAllIngredientsFromRecipe(recipeId);
		
		return new ResponseEntity<>(ingredients, HttpStatus.OK);				
	}
	
	@PutMapping("/{recipeName}")
	public ResponseEntity<Ingredient> saveIngredientsByRecipeName(@PathVariable String recipeName) {
		
		return new ResponseEntity<>(HttpStatus.OK);		
	}
}
