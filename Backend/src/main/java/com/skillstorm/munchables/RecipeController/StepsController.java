package com.skillstorm.munchables.RecipeController;

import java.util.List;

import javax.validation.Valid;

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
import com.skillstorm.munchables.beans.Steps;

@RestController
@RequestMapping(value = "/steps")
@CrossOrigin(origins = "http://localhost:4200")
public class StepsController {

	
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeService service;

	// get requests
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Steps>> findAllSteps() {
		return new ResponseEntity<List<Steps>>(recipeRepository.findAllSteps(), HttpStatus.OK);
	}

	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Steps> findByIdSteps(@PathVariable int id) {
		return new ResponseEntity<Steps>(recipeRepository.findByIdSteps(id), HttpStatus.OK);

	}
	
	// Post Request

		@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		//@Transactional(propagation = Propagation.REQUIRES_NEW)
		public ResponseEntity<Steps> create(@Valid @RequestBody Steps steps) {
			return new ResponseEntity<Steps>(service.save(steps), HttpStatus.CREATED);

		}

		// Put Request

		@PutMapping(value = "/steps/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Steps> update(@Valid @RequestBody Steps steps, @PathVariable int id) {
			return null;

			// Delete REquest
		}
}
