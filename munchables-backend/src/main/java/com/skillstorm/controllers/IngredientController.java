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

import com.skillstorm.beans.Ingredient;
import com.skillstorm.services.IngredientService;


@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class IngredientController {

private static final Logger log = Logger.getLogger(IngredientController.class);
	
	@Autowired
	IngredientService ingredientService;
	
//	@GetMapping("/{ingredientId}")
//	public ResponseEntity<Ingredient> getIngredienteById(@PathVariable int ingredientId) {
//		log.info("getIngredienteById");
//		
//		Ingredient ingredient = ingredientService.findById(ingredientId);
//		
//		if (ingredient != null) {
//			return new ResponseEntity<>(ingredient, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}		
//	}
	
	@GetMapping("/{recipeId}")
	public ResponseEntity<List<Ingredient>> getAllIngredientsFromRecipe(@PathVariable int recipeId) {
		log.info("getAllIngredientsFromRecipe");
		
		List<Ingredient> ingredients = ingredientService.findAllIngredientsFromRecipe(recipeId);
		
		return new ResponseEntity<>(ingredients, HttpStatus.OK);				
	}
	
	@PostMapping("/{recipeId}")
	public ResponseEntity<Ingredient> addNewIngredientToRecipe(@RequestBody Ingredient ingredient, @PathVariable int recipeId) {
		log.info("addNewIngredientToRecipe");
		
		Ingredient newIngredient = ingredientService.saveIngredientByRecipeId(ingredient, recipeId);
		
		if (newIngredient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newIngredient, HttpStatus.OK);		
	}
	
	@PutMapping("/{recipeId}")
	public ResponseEntity<Ingredient> saveIngredientByRecipeId(@RequestBody Ingredient ingredient, @PathVariable int recipeId) {
		log.info("saveIngredientsByRecipeId");
		
		Ingredient newIngredient = ingredientService.saveIngredientByRecipeId(ingredient, recipeId);
		
		if (newIngredient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newIngredient, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{ingredientId}")
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int ingredientId) {
		log.info("deleteIngredient");
		
		ingredientService.deleteIngredient(ingredientId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
