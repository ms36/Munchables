package com.skillstorm.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Recipe;
import com.skillstorm.beans.Step;
import com.skillstorm.repository.RecipeRepository;
import com.skillstorm.repository.StepRepository;

@Service
public class StepService {
	private static final Logger log = Logger.getLogger(StepService.class);
	
	@Autowired
	StepRepository stepRepository;
	@Autowired
	RecipeRepository recipeRepository;
	
	public List<Step> findAllStepsFromRecipe(int recipeId) {
		log.info("findAllStepsFromRecipe");
		
		return stepRepository.findAllStepsFromRecipe(recipeId);
	}
	
	public Step saveStepByRecipeId(Step step, int recipeId) {
		log.info("\"" + step.getStepProcess() + "\"" + " step,  to recipe with id " + recipeId);
		
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isPresent()) {
			step.setRecipe(recipe.get());
		} else {
			return null;
		}
		return stepRepository.save(step);
	}
	
	public void deleteStep(int stepId) {
		log.info("deleteStep with id: " + stepId);
		
		Optional<Step> step = stepRepository.findById(stepId);
		if (step.isPresent()) {
			stepRepository.delete(step.get());
		}
	}
}
