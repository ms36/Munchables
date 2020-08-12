package com.skillstorm.munchables.RecipeController;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.munchables.Data.RecipeRepository;
import com.skillstorm.munchables.Service.RecipeService;
import com.skillstorm.munchables.beans.Ingredients;


@RestController
@RequestMapping(value = "/ingredients")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientsController {
	
	public static final Logger log = Logger.getLogger(IngredientsController.class);

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeService service;

	// Get requests
	@GetMapping(value = "/all")
	public ResponseEntity<List<Ingredients>> findAllRecipe() {
		log.info("findAllRecipe");
		return new ResponseEntity<List<Ingredients>>(recipeRepository.findAllIngredients(), HttpStatus.OK);
	}

	 // Post Request
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void create(@Valid @RequestBody Ingredients ingredient) {
		log.info("create");
		//recipeRepository.save(ingredient);
		//return null;//new ResponseEntity<Ingredients>(recipeRepository.save(ingredient), HttpStatus.CREATED);

	}
	
	
	// Put Request
//	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Ingredients> update(@Valid @RequestBody Ingredients ingredient, @PathVariable int id) {
//		log.info("update");
//		ResponseEntity<Ingredients>(recipeRepository.save(ingredient), HttpStatus.NO_CONTENT);
//
//	}
	
	// Delete Request

}
