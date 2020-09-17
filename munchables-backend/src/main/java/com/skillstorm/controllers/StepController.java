package com.skillstorm.controllers;

import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skillstorm.beans.Step;
import com.skillstorm.services.StepService;

@RestController
@RequestMapping("/step")
public class StepController {
	
	private static final Logger log = Logger.getLogger(StepController.class);
	
	@Autowired
	StepService stepService;
	
	@GetMapping(value = "/{recipeId}")
	public ResponseEntity<List<Step>> getAllStepsFromRecipe(@PathVariable int recipeId) {
		log.info("getAllStepsFromRecipe");
		
		List<Step> step = stepService.findAllStepsFromRecipe(recipeId);
		
		return new ResponseEntity<>(step, HttpStatus.OK);				
	}
}
