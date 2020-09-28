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

import com.skillstorm.beans.Step;
import com.skillstorm.services.StepService;

@RestController
@RequestMapping("/step")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class StepController {
	
	private static final Logger log = Logger.getLogger(StepController.class);
	
	@Autowired
	StepService stepService;
	
	@GetMapping("/{recipeId}")
	public ResponseEntity<List<Step>> getAllStepsFromRecipe(@PathVariable int recipeId) {
		log.info("getAllStepsFromRecipe");
		
		List<Step> step = stepService.findAllStepsFromRecipe(recipeId);
		
		return new ResponseEntity<>(step, HttpStatus.OK);				
	}
	
	@PostMapping("/{recipeId}")
	public ResponseEntity<Step> addNewStepToRecipe(@RequestBody Step step, @PathVariable int recipeId) {
		log.info("addNewStepToRecipe");
		
		Step newStep = stepService.saveStepByRecipeId(step, recipeId);
		
		if (newStep == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newStep, HttpStatus.OK);		
	}
	
	@PutMapping("/{recipeId}")
	public ResponseEntity<Step> saveStepByRecipeId(@RequestBody Step step, @PathVariable int recipeId) {
		log.info("saveStepByRecipeId");
		
		Step newStep = stepService.saveStepByRecipeId(step, recipeId);
		
		if (newStep == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newStep, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{stepId}")
	public ResponseEntity<Step> deleteStep(@PathVariable int stepId) {
		log.info("deleteStep");
		
		stepService.deleteStep(stepId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
