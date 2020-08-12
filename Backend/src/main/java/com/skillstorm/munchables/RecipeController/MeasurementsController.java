package com.skillstorm.munchables.RecipeController;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.munchables.Data.RecipeRepository;
import com.skillstorm.munchables.Service.RecipeService;
import com.skillstorm.munchables.beans.Measurements;

@RestController
@RequestMapping(value = "api/measurements")
public class MeasurementsController {

	public static final Logger logger = Logger.getLogger(MeasurementsController.class);

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeService service;

	// get requests
	@GetMapping
	public ResponseEntity<List<Measurements>> findAllRecipe() {
		return new ResponseEntity<List<Measurements>>(recipeRepository.findAllMeasurements(), HttpStatus.OK);
	}

	@GetMapping(value = "/measurements/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Measurements> findByIdMeasurement(@PathVariable int id) {
		return new ResponseEntity<Measurements>(recipeRepository.findByIdMeasurement(id), HttpStatus.OK);

	}
	
	// Post Request

		@PostMapping(value = "/measurements", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		//@Transactional(propagation = Propagation.REQUIRES_NEW)
		public ResponseEntity<Measurements> create(@Valid @RequestBody Measurements measurements) {
			return new ResponseEntity<Measurements>(service.save(measurements), HttpStatus.CREATED);

		}

		// Put Request

		@PutMapping(value = "/measurements/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Measurements> update(@Valid @RequestBody Measurements measurements, @PathVariable int id) {
			return new ResponseEntity<Measurements>(service.save(measurements), HttpStatus.NO_CONTENT);

			// Delete REquest
		}
	
}
