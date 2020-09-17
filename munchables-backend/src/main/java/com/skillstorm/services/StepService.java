package com.skillstorm.services;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Step;
import com.skillstorm.repository.StepRepository;

@Service
public class StepService {
	private static final Logger log = Logger.getLogger(StepService.class);
	
	@Autowired
	StepRepository stepRepository;
	
	public List<Step> findAllStepsFromRecipe(int recipeId) {
		log.info("findAllStepsFromRecipe");
		
		return stepRepository.findAllStepsFromRecipe(recipeId);
	}
}
