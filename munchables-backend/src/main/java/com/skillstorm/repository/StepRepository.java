package com.skillstorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.skillstorm.beans.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer>{
	
	@Query("SELECT s FROM Step s WHERE recipe_id = ?1")
	public List<Step> findAllStepsFromRecipe(int recipeId);
}
