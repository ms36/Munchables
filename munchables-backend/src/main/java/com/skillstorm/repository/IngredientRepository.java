package com.skillstorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.skillstorm.beans.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

	@Query("SELECT i FROM Ingredient i WHERE recipe_id = ?1")
	public List<Ingredient> findAllIngredientsFromRecipe(int recipeId);
}
